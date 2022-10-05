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

import java.util.Collection;
import java.util.Collections;
import org.opengis.util.InternationalString;
import org.opengis.annotation.UML;
import org.opengis.metadata.Identifier;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Identifier of a measure fully described elsewhere.
 * At least one of {@linkplain #getMeasureIdentification() measure identification}
 * and {@linkplain #getNamesOfMeasure() measure names} shall be provided.
 * The whole description can be found within a measure register or catalogue.
 *
 * @author  Alexis Gaillard (Geomatys)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 *
 * @see Element#getMeasure()
 * @see Measure
 *
 * @since 3.1
 */
@UML(identifier="DQ_MeasureReference", specification=ISO_19157)
public interface MeasureReference {
    /**
     * Identifier of the measure, value uniquely identifying the measure within a namespace.
     *
     * @return code identifying a registered standard procedure, or {@code null} if none.
     *
     * @see Measure#getMeasureIdentifier()
     */
    @UML(identifier="measureIdentification", obligation=OPTIONAL, specification=ISO_19157)
    default Identifier getMeasureIdentification() {
        return null;
    }

    /**
     * Name of the test applied to the data.
     * Mandatory if {@linkplain #getMeasureIdentification() measure identification} is not provided.
     *
     * @return name of the test applied to the data.
     *
     * @see Measure#getName()
     */
    @UML(identifier="nameOfMeasure", obligation=CONDITIONAL, specification=ISO_19157)
    default Collection<? extends InternationalString> getNamesOfMeasure() {
        return Collections.emptyList();
    }

    /**
     * Description of the measure.
     *
     * @return description of the measure being determined, or {@code null} if none.
     *
     * @see Measure#getDescription()
     */
    @UML(identifier="measureDescription", obligation=OPTIONAL, specification=ISO_19157)
    default InternationalString getMeasureDescription() {
        return null;
    }
}
