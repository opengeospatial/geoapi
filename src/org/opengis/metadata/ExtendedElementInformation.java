/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.metadata;

// J2SE direct dependencies
import java.util.Locale;

// OpenGIS direct dependencies
import org.opengis.metadata.citation.ResponsibleParty;


/**
 * New metadata element, not found in ISO 19115, which is required to describe geographic data.
 *
 * @UML datatype MD_ExtendedElementInformation
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
public interface ExtendedElementInformation {
    /**
     * Name of the extended metadata element.
     *
     * @UML mandatory name
     */
    String getName();

    /**
     * Short form suitable for use in an implementation method such as XML or SGML.
     * NOTE: other methods may be used.
     * Returns <code>null</code> if the {@linkplain #getDataType data type}
     * is {@linkplain Datatype#CODE_LIST_ELEMENT code list element}.
     *
     * @UML conditional shortName
     */
    String getShortName();

    /**
     * Three digit code assigned to the extended element.
     * Returns a non-null value only if the {@linkplain #getDataType data type}
     * is {@linkplain Datatype#CODE_LIST_ELEMENT code list element}.
     *
     * @UML conditional domainCode
     */
    Integer getDomainCode();

    /**
     * Definition of the extended element.
     *
     * @param  locale The desired locale for the definition to be returned, or <code>null</code>
     *         for a definition in some default locale (may or may not be the
     *         {@linkplain Locale#getDefault() system default}).
     * @return The definition in the given locale.
     *         If no definition is available in the given locale, then some default locale is used.
     * @UML mandatory definition
     */
    String getDefinition(Locale locale);

    /**
     * Obligation of the extended element.
     *
     * @UML conditional obligation
     */
    Obligation getObligation();

    /**
     * Condition under which the extended element is mandatory.
     * Returns a non-null value only if the {@linkplain #getObligation obligation}
     * is {@linkplain Obligation#CONDITIONAL conditional}.
     *
     * @UML conditional condition
     */
    String getCondition(Locale locale);

    /**
     * Code which identifies the kind of value provided in the extended element.
     *
     * @UML mandatory dataType
     */
    Datatype getDataType();

    /**
     * Maximum occurrence of the extended element.
     * Returns <code>null</code> if it doesn't apply, for example if the
     * {@linkplain #getDataType data type} is {@linkplain Datatype#ENUMERATION enumeration},
     * {@linkplain Datatype#CODE_LIST code list} or {@linkplain Datatype#CODE_LIST_ELEMENT
     * code list element}.
     *
     * @UML conditional maximumOccurrence
     */
    Integer getMaximumOccurrence();

    /**
     * Valid values that can be assigned to the extended element.
     * Returns <code>null</code> if it doesn't apply, for example if the
     * {@linkplain #getDataType data type} is {@linkplain Datatype#ENUMERATION enumeration},
     * {@linkplain Datatype#CODE_LIST code list} or {@linkplain Datatype#CODE_LIST_ELEMENT
     * code list element}.
     *
     * @param  locale The desired locale for the description to be returned, or <code>null</code>
     *         for a description in some default locale (may or may not be the
     *         {@linkplain Locale#getDefault() system default}).
     * @return The description in the given locale.
     *         If no description is available in the given locale, then some default locale is used.
     * @UML conditional domainValue
     */
    String getDomainValue(Locale locale);

    /**
     * Name of the metadata entity(s) under which this extended metadata element may appear.
     * The name(s) may be standard metadata element(s) or other extended metadata element(s).
     *
     * @UML mandatory parentEntity
     */
    String[] getParentEntity();

    /**
     * Specifies how the extended element relates to other existing elements and entities.
     *
     * @UML mandatory rule
     */
    String getRule(Locale locale);

    /**
     * Reason for creating the extended element.
     *
     * @param  locale The desired locale for the rationale to be returned, or <code>null</code>
     *         for a rationale in some default locale (may or may not be the
     *         {@linkplain Locale#getDefault() system default}).
     * @return The rationale in the given locale.
     *         If no rationale is available in the given locale, then some default locale is used.
     * @UML optional rationale
     */
    String[] getRationales(Locale locale);

    /**
     * Name of the person or organization creating the extended element.
     *
     * @UML mandatory source
     */
    ResponsibleParty[] getSources();
}
