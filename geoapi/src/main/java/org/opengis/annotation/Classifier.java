/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2013-2019 Open Geospatial Consortium, Inc.
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
package org.opengis.annotation;

import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import java.lang.annotation.Documented;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;


/**
 * An annotation specifying the stereotype (abstract, datatype, union, <i>etc.</i>) of an interface.
 * The UML class diagrams in ISO/OGC specifications declare some members as abstract, meaning that
 * instances of those interfaces are expected to implement one of their sub-interfaces.
 * While there is nothing like "abstract interface" and "concrete interface" in the Java language,
 * we nevertheless communicate ISO/OGC intent using this annotation.
 *
 * <p>Implementations are not required to represent "abstract interfaces" by Java abstract classes.
 * This annotation is provided merely for informative purpose for testing tools, implementations
 * based on Java reflection, or widgets among other usages.</p>
 *
 * <p>If this annotation is not present, then the default value is {@link Stereotype#TYPE}.</p>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 *
 * @see <a href="http://en.wikipedia.org/wiki/Classifier_%28UML%29">Classifier on Wikipedia</a>
 */
@Documented
@Retention(RUNTIME)
@Target(TYPE)
public @interface Classifier {
    /**
     * Returns the type of modeling element (type, datatype, abstract or union).
     *
     * @return the type of modeling element as declared in the OGC/ISO UML diagram.
     */
    Stereotype value();
}
