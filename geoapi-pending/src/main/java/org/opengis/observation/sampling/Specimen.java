/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2009-2021 Open Geospatial Consortium, Inc.
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
package org.opengis.observation.sampling;

import org.opengis.observation.Measure;
import org.opengis.observation.ProcessModel;
import org.opengis.temporal.TemporalGeometricPrimitive;
import org.opengis.util.GenericName;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Specification.*;
import static org.opengis.annotation.Obligation.*;

/**
 * A located object on which measurements may be made.
 *
 * A basic material classification is provided using the "material" property.
 * Its value may be relatively generic (rock, pulp) or may reflect a detailed classification (calcrete, adamellite, biotite-schist).
 * In the latter case it is wise to use the codeSpace attribute to provide a link to the classification scheme/vocabulary used.
 *
 * Note that if this specimen is a "processed" version of another (e.g. by grinding, sieving, etc) then
 * the predecessor (if known) may be recorded as a relatedSamplingFeature.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/om">Implementation specification 1.0</A>
 * @author Open Geospatial Consortium
 * @author Guilhem Legal (Geomatys)
 * @since GeoAPI 2.3
 */
@UML(identifier="Specimen", specification=OGC_07022)
public interface Specimen extends SamplingFeature {

    /**
     * Material type, usually taken from a controlled vocabulary.
     * Specialised domains may choose to fix the vocabulary to be used.
     */
    @UML(identifier="materialClass", obligation=MANDATORY, specification=OGC_07022)
    GenericName getMaterialClass();

    /**
     * Storage location of specimen if it still exists.
     * If destroyed in analysis, then either omit or use xlink:href to point to a suitable URN, e.g. urn:cgi:def:nil:destroyed.
     */
    @UML(identifier="currentLocation", obligation=OPTIONAL, specification=OGC_07022)
    Location getCurrentLocation();

    /**
     * Method used when retrieving specimen from host sampledFeature
     */
    @UML(identifier="samplingMethod", obligation=OPTIONAL, specification=OGC_07022)
    ProcessModel getSamplingMethod();

    /**
     * Time and date when the specimen was initially retrieved
     */
    @UML(identifier="samplingTime", obligation=MANDATORY, specification=OGC_07022)
    TemporalGeometricPrimitive getSamplingTime();

    /**
     * The size of the specimen: mass, length, volume, etc.
     */
    @UML(identifier="size", obligation=OPTIONAL, specification=OGC_07022)
    Measure getSize();
    
}
