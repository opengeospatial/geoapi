/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2004-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis;

import org.junit.*;


/**
 * Base class of all GeoAPI tests. Subclasses define a constructor expecting a factory argument,
 * which must be provided by implementors wanting to run the tests. For example the
 * {@link org.opengis.util.NameTest} class performs tests on instances created by a
 * {@link org.opengis.util.NameFactory}. Implementors wanting to test their factory
 * should subclass {@code NameTest} like below:
 *
 * <blockquote><pre>
 * public class MyTest extends NameTest {
 *     public MyTest() {
 *         super(new MyFactory());
 *     }
 * }
 * </pre></blockquote>
 *
 * @param <T> The interface of the factory to be used for creating instances to be tested.
 *
 * @author Martin Desruisseaux (Geomatys)
 * @since GeoAPI 2.2
 */
public abstract class TestCase<T> extends Assert {
    /**
     * Creates an unitialized test.
     */
    protected TestCase() {
    }

    /**
     * Returns the interface of the factory to be used for creating instances to be tested.
     *
     * @return The factory interface (<strong>not</strong> the implementation class).
     */
    public abstract Class<T> getFactoryClass();
}
