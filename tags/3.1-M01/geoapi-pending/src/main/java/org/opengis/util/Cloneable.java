/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2011 Open Geospatial Consortium, Inc.
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
package org.opengis.util;


/**
 * Indicates that it is legal to make a field-for-field copy of instances of implementing classes.
 * A cloneable class implements the J2SE's {@link java.lang.Cloneable} standard interface and
 * additionally overrides the {@link Object#clone()} method with public access.
 * <p>
 * Because the {@link Object#clone()} method has protected access, containers wanting to clone
 * theirs elements need to 1) use Java reflection (which is less efficient than standard method
 * calls), or 2) cast every elements to a specific type like {@link java.util.Date} (which may
 * require a large amount of "{@code if (x instanceof y)}" checks if arbitrary classes are
 * allowed). This {@code Cloneable} interface had a third alternative: checks only for this
 * interface instead of a list of particular cases.
 * <p>
 * Implementors of cloneable classes may consider implementing this interface, but this is not
 * mandatory. A large amount of independent classes like {@link java.util.Date} will continue to
 * ignore this interface, so no rule can be enforced anyway. However this interface may help the
 * work of containers in some case. For example a container may checks for this interface first,
 * and uses Java reflection as a fallback.
 *
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 1.0
 *
 * @see java.lang.Cloneable
 * @see <A HREF="http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=4098033">&quot;<cite>Cloneable
 *      doesn't define <code>clone()</code></cite>&quot; on Sun's bug parade</A>
 *
 * @deprecated The need for this interface in GeoAPI is weaker than it was at the beginning of this
 *      project, since the interfaces which were originally extending {@code Cloneable} do not
 *      extend it anymore today. Consequently this interface will be removed and the handling
 *      of the {@code Cloneable} issue left to implementors.
 */
@Deprecated
public interface Cloneable extends java.lang.Cloneable {
    /**
     * Creates and returns a copy of this object.
     * The precise meaning of "copy" may depend on the class of the object.
     *
     * @return A copy of this object.
     *
     * @see Object#clone
     */
    Object clone();
}
