/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2014-2019 Open Geospatial Consortium, Inc.
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
package org.opengis.feature;

import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;
import org.opengis.annotation.UML;
import org.opengis.util.GenericName;
import org.opengis.util.InternationalString;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.ISO_19109;


/**
 * Identification and description information inherited by property types and feature types.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@Classifier(Stereotype.METACLASS)
@UML(identifier="IdentifiedType", specification=ISO_19109)
public interface IdentifiedType {
    /**
     * Returns the name of this type. The namespace can be either explicit
     * ({@link org.opengis.util.ScopedName}) or implicit
     * ({@link org.opengis.util.LocalName}).
     *
     * The name is optional for {@link Operation}, but mandatory for other types.
     * For {@link AttributeType}, the name shall be unique in the {@code FeatureType}.
     * For {@link FeatureType}, the name shall be unique in the unit processing the data.
     *
     * @return the type name, or {@code null} if none.
     */
    @UML(identifier="name", obligation=OPTIONAL, specification=ISO_19109)
    GenericName getName();

    /**
     * Returns a concise definition of the element.
     *
     * @return concise definition of the element.
     */
    @UML(identifier="definition", obligation=MANDATORY, specification=ISO_19109)
    InternationalString getDefinition();

    /**
     * Returns a natural language designator for the element.
     * This can be used as an alternative to the {@linkplain #getName() name} in user interfaces.
     *
     * @return natural language designator for the element, or {@code null} if none.
     */
    @UML(identifier="designation", obligation=OPTIONAL, specification=ISO_19109)
    InternationalString getDesignation();

    /**
     * Returns optional information beyond that required for concise definition of the element.
     * The description may assist in understanding the element scope and application.
     *
     * @return information beyond that required for concise definition of the element, or {@code null} if none.
     */
    @UML(identifier="description", obligation=OPTIONAL, specification=ISO_19109)
    InternationalString getDescription();

    /*
     * ISO 19109 properties omitted for now:
     *
     *   - constrainedBy : CharacterString
     *
     * Rational: a CharacterString is hardly programmatically usable.
     * We may want for a {@code org.opengis.filter} package to be defined, and use it.
     */
}
