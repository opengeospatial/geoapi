/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2021 Open Geospatial Consortium, Inc.
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
package org.opengis.metadata;

import java.util.Collection;
import java.util.Collections;
import org.opengis.util.InternationalString;
import org.opengis.metadata.citation.Responsibility;
import org.opengis.metadata.citation.ResponsibleParty;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * New metadata element, not found in ISO 19115, which is required to describe geographic data.
 * Metadata elements are contained in a {@linkplain MetadataExtensionInformation metadata extension information}.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.1
 * @since   2.0
 */
@UML(identifier="MD_ExtendedElementInformation", specification=ISO_19115)
public interface ExtendedElementInformation {
    /**
     * Name of the extended metadata element.
     *
     * @return name of the extended metadata element.
     *
     * @departure historic
     *    This property has been kept conform to ISO 19115:2003 for simplicity.
     *    The 2014 revision defines two mutually exclusive names depending on the data type:
     *    {@code "conceptName"} for {@code ENUMERATION}, {@code CODE_LIST} or {@code CODE_LIST_ELEMENT},
     *    and {@code "name"} for all other data types. GeoAPI keeps the {@code "name"} property for all
     *    data types and let developers inspect the {@code "dataType"} property if needed.
     */
    @UML(identifier="name", obligation=MANDATORY, specification=ISO_19115)
    String getName();

    /**
     * Short form suitable for use in an implementation method such as XML or SGML.
     * Returns {@code null} if the {@linkplain #getDataType() data type}
     * is {@linkplain Datatype#CODE_LIST_ELEMENT code list element}, in which case
     * {@link #getDomainCode()} may be used instead.
     *
     * @return short form suitable for use in an implementation method such as XML or SGML,
     *         or {@code null}.
     *
     * @condition The {@linkplain #getDataType() data type} is not
     *            {@link Datatype#CODE_LIST_ELEMENT CODE_LIST_ELEMENT}.
     *
     * @deprecated Removed as of ISO 19115:2014.
     */
    @Deprecated
    @UML(identifier="shortName", obligation=CONDITIONAL, specification=ISO_19115, version=2003)
    default String getShortName() {
        return null;
    }

    /**
     * Three digit code assigned to the extended element.
     * Returns a non-null value only if the {@linkplain #getDataType() data type}
     * is {@linkplain Datatype#CODE_LIST_ELEMENT code list element}, otherwise
     * {@link #getShortName()} may be used instead.
     *
     * @return three digit code assigned to the extended element, or {@code null}.
     *
     * @condition The {@linkplain #getDataType() data type} is
     *            {@link Datatype#CODE_LIST_ELEMENT CODE_LIST_ELEMENT}.
     *
     * @deprecated Removed as of ISO 19115:2014.
     */
    @Deprecated
    @UML(identifier="domainCode", obligation=CONDITIONAL, specification=ISO_19115, version=2003)
    default Integer getDomainCode() {
        return null;
    }

    /**
     * Definition of the extended element.
     *
     * @return definition of the extended element.
     */
    @UML(identifier="definition", obligation=MANDATORY, specification=ISO_19115)
    InternationalString getDefinition();

    /**
     * Obligation of the extended element.
     *
     * <div class="warning"><b>Upcoming API change — type change</b><br>
     * According ISO 19115, {@code Obligation} shall be an enumeration, not a code list.
     * Such enumeration already exists in the {@link org.opengis.annotation} package.
     * Consequently the {@code org.opengis.metadata.Obligation} return type may be
     * replaced by {@code org.opengis.annotation.Obligation} in GeoAPI 4.0.
     * See <a href="http://jira.codehaus.org/browse/GEO-199">GEO-199</a> for more information.</div>
     *
     * @return obligation of the extended element, or {@code null}.
     *
     * @condition The {@linkplain #getDataType() data type} is not {@link Datatype#ENUMERATION ENUMERATION},
     *            {@link Datatype#CODE_LIST CODE_LIST} or {@link Datatype#CODE_LIST_ELEMENT CODE_LIST_ELEMENT}.
     */
    @UML(identifier="obligation", obligation=CONDITIONAL, specification=ISO_19115)
    Obligation getObligation();

    /**
     * Condition under which the extended element is mandatory.
     * Returns a non-null value only if the {@linkplain #getObligation() obligation}
     * is {@linkplain Obligation#CONDITIONAL conditional}.
     *
     * @return the condition under which the extended element is mandatory, or {@code null}.
     *
     * @condition The {@linkplain #getObligation() Obligation} is {@link Obligation#CONDITIONAL CONDITIONAL}.
     */
    @UML(identifier="condition", obligation=CONDITIONAL, specification=ISO_19115)
    InternationalString getCondition();

    /**
     * Code which identifies the kind of value provided in the extended element.
     *
     * @return the kind of value provided in the extended element.
     */
    @UML(identifier="dataType", obligation=MANDATORY, specification=ISO_19115)
    Datatype getDataType();

    /**
     * Maximum occurrence of the extended element.
     * Returns {@code null} if it doesn't apply, for example if the
     * {@linkplain #getDataType() data type} is {@linkplain Datatype#ENUMERATION enumeration},
     * {@linkplain Datatype#CODE_LIST code list} or {@linkplain Datatype#CODE_LIST_ELEMENT
     * code list element}.
     *
     * @return maximum occurrence of the extended element, or {@code null}.
     *
     * @condition The {@linkplain #getDataType() data type} is not {@link Datatype#ENUMERATION ENUMERATION},
     *            {@link Datatype#CODE_LIST CODE_LIST} or {@link Datatype#CODE_LIST_ELEMENT CODE_LIST_ELEMENT}.
     */
    @UML(identifier="maximumOccurrence", obligation=CONDITIONAL, specification=ISO_19115)
    Integer getMaximumOccurrence();

    /**
     * Valid values that can be assigned to the extended element.
     * Returns {@code null} if it doesn't apply, for example if the
     * {@linkplain #getDataType() data type} is {@linkplain Datatype#ENUMERATION enumeration},
     * {@linkplain Datatype#CODE_LIST code list} or {@linkplain Datatype#CODE_LIST_ELEMENT
     * code list element}.
     *
     * @return valid values that can be assigned to the extended element, or {@code null}.
     *
     * @condition The {@linkplain #getDataType() data type} is not {@link Datatype#ENUMERATION ENUMERATION},
     *            {@link Datatype#CODE_LIST CODE_LIST} or {@link Datatype#CODE_LIST_ELEMENT CODE_LIST_ELEMENT}.
     */
    @UML(identifier="domainValue", obligation=CONDITIONAL, specification=ISO_19115)
    InternationalString getDomainValue();

    /**
     * Name of the metadata entity(s) under which this extended metadata element may appear.
     * The name(s) may be standard metadata element(s) or other extended metadata element(s).
     *
     * @return name of the metadata entity(s) under which this extended metadata element may appear.
     */
    @UML(identifier="parentEntity", obligation=MANDATORY, specification=ISO_19115)
    Collection<String> getParentEntity();

    /**
     * Specifies how the extended element relates to other existing elements and entities.
     *
     * @return how the extended element relates to other existing elements and entities.
     */
    @UML(identifier="rule", obligation=MANDATORY, specification=ISO_19115)
    InternationalString getRule();

    /**
     * Reason for creating the extended element.
     *
     * @return reason for creating the extended element.
     *
     * @since 3.1
     */
    @UML(identifier="rationale", obligation=OPTIONAL, specification=ISO_19115)
    default InternationalString getRationale() {
        return null;
    }

    /**
     * @deprecated As of ISO 19115:2014, replaced by {@link #getRationale()}.
     *
     * @return reason for creating the extended element.
     */
    @Deprecated
    default Collection<? extends InternationalString> getRationales() {
        InternationalString rationale = getRationale();
        return (rationale != null) ? Collections.singletonList(rationale) : Collections.emptyList();
    }

    /**
     * Name of the person or organization creating the extended element.
     *
     * <div class="warning"><b>Upcoming API change — generalization</b><br>
     * As of ISO 19115:2014, {@code ResponsibleParty} is replaced by the {@link Responsibility} parent interface.
     * This change may be applied in GeoAPI 4.0.
     * </div>
     *
     * @return name of the person or organization creating the extended element.
     */
    @UML(identifier="source", obligation=MANDATORY, specification=ISO_19115, version=2003)
    Collection<? extends ResponsibleParty> getSources();

    /*
     * No 'getConceptName()' method. See 'getName()' for explanation.
     */
}
