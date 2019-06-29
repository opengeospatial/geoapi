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

import java.util.Map;
import java.util.HashMap;


/**
 * Departures in GeoAPI interfaces compared to OGC/ISO schemas.
 * Each {@code Departures} instance is initialized with a default set of departure information.
 * More departure can be added by calls to {@code add(…)} methods.
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
    private final Map<String,String> mergedTypes;

    /**
     * Changes in the spelling of an identifier. The differences may be for historical reasons,
     * or in a few cases because of a misspelling in the XSD file compared to the UML.
     *
     * <p>Keys are the spellings used in GeoAPI {@link org.opengis.annotation.UML} annotations
     * and values are the spellings used in XSD files.</p>
     */
    final Map<String,String> spellingChanges;

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
        m.put("MI_EnvironmentalRecord.meteorologicalConditions", "meterologicalConditions");    // Misspelling in ISO 19115-3:2016
        m.put("MI_Requirement.satisfiedPlan",                    "satisifiedPlan");             // Misspelling in ISO 19115-3:2016
        m.put("LI_ProcessStep.stepDateTime",                     "dateTime");                   // Spelling change in XSD files
        m.put("DQ_Result.valueType",                             "valueRecordType");            // TODO: verify in ISO 19157
        spellingChanges = m;
    }

    /**
     * Adds a class to be retrofitted into another class. For example ISO 19115-2 defines {@code MI_Band} as
     * an extension of ISO 19115-1 {@code MD_Band}, but GeoAPI merges those two types in a single interface
     * for simplicity.
     *
     * @param  toRetrofit  name of the type to retrofit into another type. Example: {@code MI_Band}.
     * @param  target      name of a type which will receive the properties of the retrofitted type.
     *                     Example: {@code MD_Band}.
     */
    public void addMergedType(final String toRetrofit, final String target) {
        if (mergedTypes.putIfAbsent(toRetrofit, target) != null) {
            throw new IllegalArgumentException(toRetrofit + " is already retrofitted.");
        }
    }

    /**
     * Changes the spelling of an identifier. The differences may be for historical reasons,
     * or in a few cases because of a misspelling in the XSD file compared to the UML.
     *
     * @param  uml  the spelling in the UML, including class name. Example: {@code "LI_ProcessStep.stepDateTime"}.
     * @param  xsd  the spelling in the XSD file. Example: {@code "dateTime"}.
     */
    public void addSpellingChange(final String uml, final String xsd) {
        if (spellingChanges.put(uml, xsd) != null) {
            throw new IllegalArgumentException("A spelling change is already declared for " + uml);
        }
    }

    /**
     * Returns the name of a class merging the given class and its (usually) parent class.
     * For example {@code "MI_Band_Type"} is renamed as {@code "MD_Band_Type"}.
     * We do that because we use only one class for representing those two distinct ISO types.
     * Note that not all ISO 19115-2 types extend an ISO 19115-1 type, so we need to apply a case-by-case approach.
     * If there is no merge to apply, then this method returns the given name unchanged.
     *
     * @param  name  name of a class to potentially merge with its parent class.
     * @return the merged class name (may be the given name) together with other information.
     */
    final MergeInfo nameOfMergedType(final String name) {
        String target = mergedTypes.remove(name);
        if (target == null) {
            return new MergeInfo(name, false);
        } else {
            return new MergeInfo(target, name.startsWith("Abstract"));
        }
    }

    /**
     * Information about a type that may have been retrofitted into another type.
     * For example ISO 19115-2 defines {@code MI_Band} as an extension of ISO 19115-1 {@code MD_Band},
     * but GeoAPI merges those two types in a single interface for simplicity. Sometime the merge also
     * implies to change properties order.
     */
    static final class MergeInfo {
        /**
         * Name of the merged type.
         */
        final String typeName;

        /**
         * Whether we will need to reorder properties. Reordering will be needed if the properties of
         * parent type are retrofitted into the properties of the child type instead than the converse.
         * We identifies this situation by the {@code "Abstract"} prefix in type to retrofit.
         */
        private final boolean needToReorderProperties;

        /**
         * Names of properties to keep last, or {@code null} if none.
         * This is set to a non-null value only if {@link #needToReorderProperties} is {@code true}.
         */
        private String[] propertiesToKeepLast;

        /**
         * Creates information for a type.
         */
        private MergeInfo(final String typeName, final boolean reorder) {
            this.typeName = typeName;
            needToReorderProperties = reorder;
        }

        /**
         * Invoked before properties are added in the given map. This method does nothing
         * in the common case where there is no merge operation to prepare.
         */
        final void beforeAddProperties(final Map<String,?> properties) {
            if (needToReorderProperties) {
                propertiesToKeepLast = properties.keySet().toArray(new String[properties.size()]);
            }
        }

        /**
         * Invoked after properties are added in the given map. If a merge operation has been applied,
         * then this method may reorder entries in the given map by moving last the properties recorded
         * in this {@code MergeInfo}.
         */
        final <E> void afterAddProperties(final Map<String,E> properties) {
            if (propertiesToKeepLast != null) {
                for (final String p : propertiesToKeepLast) {
                    if (p != null) {
                        final E e = properties.remove(p);
                        if (e == null) {
                            throw new IllegalArgumentException("Missing property for " + p);
                        }
                        properties.put(p, e);
                    }
                }
            }
        }
    }
}
