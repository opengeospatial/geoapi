/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2018-2019 Open Geospatial Consortium, Inc.
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
package org.opengis.geoapi;

import java.util.HashMap;
import java.util.Map;


/**
 * Departures in GeoAPI interfaces compared to OGC/ISO schemas.
 * Each {@code Departures} instance is initialized with new {@link Map} instances.
 * Those maps are modifiable, e.g. for allowing users to remove items as they are processed.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @since   3.1
 * @version 3.1
 */
public class Departures {
    /**
     * ISO 19115-2 classes merged with ISO 19115-1 classes. For example ISO 19115-2 defines {@code MI_Band}
     * as an extension of ISO 19115-1 {@code MD_Band}, but GeoAPI merges those two types in a single interface
     * for simplicity.
     *
     * <p>Keys or extension types (e.g. the {@code "MI_*"} types defined by the metadata extension for imagery)
     * and values are the base types in which the extension has been merged (e.g. the {@code "MD_*"} types defined
     * by metadata fundamentals).</p>
     */
    public final Map<String,String> mergedTypes;

    /**
     * Changes in the spelling of an identifier. The differences may be for historical reasons,
     * or in a few cases because of a misspelling on the XSD compared to the UML.
     *
     * <p>Keys are the spellings used in GeoAPI {@link org.opengis.annotation.UML} annotations
     * and values are the spellings used in XSD files.</p>
     */
    public final Map<String,String> spellingChanges;

    /**
     * Creates new collections of departure information. All maps in public fields ({@link #mergedTypes},
     * <i>etc.</i>) are initialized with new instances and populated.
     */
    public Departures() {
        Map<String,String> m = new HashMap<>(12);
        // ………Merge what…………………………………………………………Into……………………………………………
        m.put("MI_Band_Type",                 "MD_Band_Type");
        m.put("MI_CoverageDescription_Type",  "MD_CoverageDescription_Type");
        m.put("MI_Georectified_Type",         "MD_Georectified_Type");
        m.put("MI_Georeferenceable_Type",     "MD_Georeferenceable_Type");
        m.put("LE_Source_Type",               "LI_Source_Type");
        m.put("LE_ProcessStep_Type",          "LI_ProcessStep_Type");
        m.put("AbstractMX_File_Type",         "MX_DataFile_Type");
        m.put("Abstract_DataQuality_Type",    "DQ_DataQuality_Type");
        m.put("Abstract_QualityElement_Type", "AbstractDQ_Element_Type");
        mergedTypes = m;

        m = new HashMap<>(12);
        m.put("MI_PolarizationOrientationCode",                  "MI_PolarisationOrientationCode");
        m.put("MI_Band.detectedPolarization",                    "detectedPolarisation");
        m.put("MI_Band.transmittedPolarization",                 "transmittedPolarisation");
        m.put("MI_EnvironmentalRecord.meteorologicalConditions", "meterologicalConditions");    // Misspelling in ISO 19115-3:2016
        m.put("MI_Requirement.satisfiedPlan",                    "satisifiedPlan");             // Misspelling in ISO 19115-3:2016
        m.put("LI_ProcessStep.stepDateTime",                     "dateTime");                   // Spelling change in XSD files
        m.put("MX_DataFile.featureType",                         "featureTypes");               // Spelling change in XSD files
        m.put("DQ_Result.valueType",                             "valueRecordType");            // TODO: verify in ISO 19157
        spellingChanges = m;
    }
}
