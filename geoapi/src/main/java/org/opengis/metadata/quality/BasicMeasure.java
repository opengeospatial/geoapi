/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2021 Open Geospatial Consortium, Inc.
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
package org.opengis.metadata.quality;

import org.opengis.util.InternationalString;
import org.opengis.util.TypeName;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Data quality basic measure.
 *
 * @author  Alexis Gaillard (Geomatys)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@UML(identifier="DQM_BasicMeasure", specification=ISO_19157)
public interface BasicMeasure {
    /**
     * Name of the data quality basic measure applied to the data.
     *
     * @return name of the data quality basic measure.
     */
    @UML(identifier="name", obligation=MANDATORY, specification=ISO_19157)
    InternationalString getName();

    /**
     * Definition of the data quality basic measure.
     *
     * @return definition of the data quality basic measure.
     */
    @UML(identifier="definition", obligation=MANDATORY, specification=ISO_19157)
    InternationalString getDefinition();

    /**
     * Illustration of the use of a data quality measure.
     *
     * @return usage example, or {@code null} if none.
     */
    @UML(identifier="example", obligation=OPTIONAL, specification=ISO_19157)
    default Description getExample() {
        return null;
    }

    /**
     * Value type for the result of the basic measure.
     *
     * @return value type of the result for the basic measure.
     */
    @UML(identifier="valueType", obligation=MANDATORY, specification=ISO_19157)
    TypeName getValueType();
}
