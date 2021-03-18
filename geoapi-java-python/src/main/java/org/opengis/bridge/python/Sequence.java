/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2018-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Permission to use, copy, and modify this software and its documentation, with
 *    or without modification, for any purpose and without fee or royalty is hereby
 *    granted, provided that you include the following on ALL copies of the software
 *    and documentation or portions thereof, including modifications, that you make:
 *
 *    1. The full text of this NOTICE in a location viewable to users of the
 *       redistributed or derivative work.
 *    2. Notice of any changes or modifications to the OGC files, including the
 *       date changes were made.
 *
 *    THIS SOFTWARE AND DOCUMENTATION IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE
 *    NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *    TO, WARRANTIES OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT
 *    THE USE OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
 *    PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
 *
 *    COPYRIGHT HOLDERS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL OR
 *    CONSEQUENTIAL DAMAGES ARISING OUT OF ANY USE OF THE SOFTWARE OR DOCUMENTATION.
 *
 *    The name and trademarks of copyright holders may NOT be used in advertising or
 *    publicity pertaining to the software without specific, written prior permission.
 *    Title to copyright in this software and any associated documentation will at all
 *    times remain with copyright holders.
 */
package org.opengis.bridge.python;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.AbstractSequentialList;
import java.util.NoSuchElementException;
import java.lang.reflect.Array;
import org.jpy.PyObject;


/**
 * A Python sequence, represented as a read-only Java list. While we are mapping a sequence
 * (the main collection type used in GeoAPI Python interfaces) this implementation accepts
 * any container capable to provide an iterator.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 4.0
 * @since   4.0
 */
final class Sequence<E> extends AbstractSequentialList<E> {
    /**
     * Information about the Python environment (builtin functions, etc).
     */
    private final Environment environment;

    /**
     * Function to apply on each element in the list for
     * converting from Python objects to Java objects.
     */
    private final Converter<? extends E> converter;

    /**
     * The Python sequence. Can actually be any collection capable to provide an iterator.
     */
    private final PyObject collection;

    /**
     * Iterator over the elements returned by {@link #get(int)}, cached for performance reasons.
     * This is {@code null} if not yet requested.
     */
    private transient Iterator<E> iter;

    /**
     * Index of the next element to be returned by {@link #iter}.
     */
    private transient int nextIndex;

    /**
     * Creates a new sequence for the given Python collection.
     *
     * @param type        the class of elements in this list.
     * @param collection  the Python sequence. Can actually be any collection capable to provide an iterator.
     */
    Sequence(final Environment environment, final Class<E> type, final PyObject collection) {
        this.environment = environment;
        this.collection  = collection;
        this.converter   = Converter.verifiedInstance(environment, type);
    }

    /**
     * Returns the length of the Python sequence wrapped by this list.
     */
    @Override
    public int size() {
        return environment.builtins.call("len", collection).getIntValue();
    }

    /**
     * Returns the element at the given index.
     * This method is optimized for accesses with increasing indices.
     */
    @Override
    public E get(final int index) {
        if (index >= 0) {
            if (iter == null || index < nextIndex) {
                iter = iterator();
                nextIndex = 0;
            }
            try {
                E element;
                do {
                    element = iter.next();
                } while (++nextIndex <= index);
                return element;
            } catch (NoSuchElementException e) {
                iter = null;
                // Exception will be thrown below.
            }
        }
        throw new IndexOutOfBoundsException(index);
    }

    /**
     * Returns an iterator over the elements in the Python sequence.
     * This method is preferred to {@link #listIterator()} if there is no need to move backward.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iter();
    }

    /**
     * Returns a bidirectional iterator over the elements in the Python sequence.
     */
    @Override
    public ListIterator<E> listIterator() {
        return new BIter();
    }

    /**
     * Returns a bidirectional iterator over the elements in the Python sequence starting at the given index.
     *
     * @param index  index of first element to be returned from the list.
     */
    @Override
    public ListIterator<E> listIterator(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException(index);
        }
        final ListIterator<E> iter = listIterator();
        if (index != 0) try {
            do iter.next();
            while (--index != 0);
        } catch (NoSuchElementException e) {
            throw new IndexOutOfBoundsException();
        }
        return iter;
    }

    /**
     * Re-throws the given exception if it is not a Python "StopIteration" exception.
     */
    private static void requireStopIteration(final RuntimeException e) {
        final String msg = e.getMessage();
        if (msg == null || !msg.contains("StopIteration")) {
            throw e;
        }
    }

    /**
     * Implementation of the iterator returned by {@link Sequence#iterator()}.
     * This implementation does not cache anything.
     */
    private class Iter implements Iterator<E> {
        /**
         * The Python iterator.
         */
        private final PyObject iter;

        /**
         * The next object to return, or {@link Sequence#DONE} if we reached the iteration end.
         * Valid only if {@link #fetched} is {@code true}.
         */
        private PyObject next;

        /**
         * Whether the next Python element to return has been stored in {@link #next}.
         * We use this flag because {@code null} may be a valid value for {@code next}.
         * A negative value means that the iteration is finished.
         */
        private byte fetched;

        /**
         * Creates a new iterator.
         */
        Iter() {
            iter = environment.builtins.call("iter", collection);
        }

        /**
         * Get the next element from Python iterator, or throws {@link RuntimeException} if there is no more elements.
         */
        private void fetch() {
            next = environment.builtins.call("next", iter);
            fetched = 1;
        }

        /**
         * Returns {@code true} if there is another object to return from the iterator.
         */
        @Override
        public final boolean hasNext() {
            if (fetched == 0) try {
                fetch();
            } catch (RuntimeException e) {
                fetched = -1;
                requireStopIteration(e);
            }
            return fetched >= 0;
        }

        /**
         * Returns the next element in iteration order.
         */
        @Override
        public E next() {
            if (fetched == 0) try {
                fetch();
            } catch (RuntimeException e) {
                fetched = -1;
                requireStopIteration(e);
                throw (NoSuchElementException) new NoSuchElementException().initCause(e);
            } else if (fetched < 0) {
                throw new NoSuchElementException();
            }
            final E element = converter.apply(next);
            fetched = 0;
            next = null;
            return element;
        }

        /**
         * Unsupported operation.
         */
        @Override
        public final void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /**
     * Implementation of the iterator returned by {@link Sequence#listIterator()}.
     * This implementation caches the elements seen during the iteration, in case
     * the caller wants to invoke {@link #previous()}. We do not share the cache
     * between iterators because the content of the Python collection may change.
     */
    private final class BIter extends Iter implements ListIterator<E> {
        /**
         * Index of the next element to store in the {@link #elements} array.
         */
        private int nextIndex;

        /**
         * Index of the next element to fetch.
         */
        private int fetchIndex;

        /**
         * The elements as Java object, filled when first needed.
         * In the case of metadata objects, this array is usually short (typically of length 1).
         */
        private final E[] elements;

        /**
         * Creates a new iterator.
         */
        @SuppressWarnings("unchecked")
        BIter() {
            elements = (E[]) Array.newInstance(converter.type, size());
        }

        /**
         * Returns the next element in the list and advances the cursor position.
         */
        @Override
        public E next() {
            final E element;
            if (nextIndex < fetchIndex) {
                element = elements[nextIndex++];
            } else {
                element = super.next();
                elements[fetchIndex] = element;
                nextIndex = ++fetchIndex;
            }
            return element;
        }

        /**
         * Returns the previous element in the list and moves the cursor position backwards.
         */
        @Override
        public E previous() {
            if (nextIndex != 0) {
                return elements[--nextIndex];
            }
            throw new NoSuchElementException();
        }

        @Override public int     nextIndex()     {return nextIndex;}
        @Override public int     previousIndex() {return nextIndex - 1;}
        @Override public boolean hasPrevious()   {return nextIndex != 0;}
        @Override public void    set(E e) {throw new UnsupportedOperationException();}
        @Override public void    add(E e) {throw new UnsupportedOperationException();}
    }
}
