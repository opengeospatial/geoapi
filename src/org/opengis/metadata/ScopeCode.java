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

// J2SE directdependencies
import java.util.List;
import java.util.ArrayList;

// OpenGIS direct dependencies
import org.opengis.util.CodeList;


/**
 * Class of information to which the referencing entity applies.
 *
 * @UML codelist MD_ScopeCode
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 *
 * @see org.opengis.metadata.quality.Scope
 */
public final class ScopeCode extends CodeList {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -4542429199783894255L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List VALUES = new ArrayList(15);

    /**
     * Information applies to the attribute class.
     *
     * @UML conditional attribute
     *
     * @revisit Should we said "... to the attribute object"?
     */
    public static final ScopeCode ATTRIBUTE = new ScopeCode("ATTRIBUTE");

    /**
     * Information applies to the characteristic of a feature.
     *
     * @UML conditional attributeType
     */
    public static final ScopeCode ATTRIBUTE_TYPE = new ScopeCode("ATTRIBUTE_TYPE");

    /**
     * Information applies to the collection hardware class.
     *
     * @UML conditional collectionHardware
     */
    public static final ScopeCode COLLECTION_HARDWARE = new ScopeCode("COLLECTION_HARDWARE");

    /**
     * Information applies to the collection session.
     *
     * @UML conditional collectionSession
     */
    public static final ScopeCode COLLECTION_SESSION = new ScopeCode("COLLECTION_SESSION");

    /**
     * Information applies to the dataset.
     *
     * @UML conditional dataset
     */
    public static final ScopeCode DATASET = new ScopeCode("DATASET");

    /**
     * Information applies to the series.
     *
     * @UML conditional series
     */
    public static final ScopeCode SERIES = new ScopeCode("SERIES");

    /**
     * information applies to non-geographic data;
     *
     * @UML conditional nonGeographicDataset
     */
    public static final ScopeCode NON_GEOGRAPHIC_DATASET = new ScopeCode("NON_GEOGRAPHIC_DATASET");

    /**
     * Information applies to a dimension group.
     *
     * @UML conditional dimensionGroup
     */
    public static final ScopeCode DIMENSION_GROUP = new ScopeCode("DIMENSION_GROUP");

    /**
     * Information applies to a feature.
     *
     * @UML conditional feature
     */
    public static final ScopeCode FEATURE = new ScopeCode("FEATURE");

    /**
     * Information applies to a feature type.
     *
     * @UML conditional featureType
     */
    public static final ScopeCode FEATURE_TYPE = new ScopeCode("FEATURE_TYPE");

    /**
     * Information applies to a property type.
     *
     * @UML conditional propertyType
     */
    public static final ScopeCode PROPERTY_TYPE = new ScopeCode("PROPERTY_TYPE");

    /**
     * Information applies to a field session.
     *
     * @UML conditional fieldSession
     */
    public static final ScopeCode FIELD_SESSION = new ScopeCode("FIELD_SESSION");

    /**
     * Information applies to a computer program or routine.
     *
     * @UML conditional software
     */
    public static final ScopeCode SOFTWARE = new ScopeCode("SOFTWARE");

    /**
     * Information applies to a capability which a service provider entity makes available
     * to a service user entity through a set of interfaces that define a behaviour, such as
     * a use case.
     *
     * @UML conditional service
     */
    public static final ScopeCode SERVICE = new ScopeCode("SERVICE");

    /**
     * Information applies to a copy or imitation of an existing or hypothetical object.
     *
     * @UML conditional model
     */
    public static final ScopeCode MODEL = new ScopeCode("MODEL");

    /**
     * Constructs an enum with the given name. The new enum is
     * automatically added to the list returned by {@link #values}.
     *
     * @param name The enum name. This name must not be in use by an other enum of this type.
     */
    public ScopeCode(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of <code>ScopeCode</code>s.
     */
    public static ScopeCode[] values() {
        synchronized (VALUES) {
            return (ScopeCode[]) VALUES.toArray(new ScopeCode[VALUES.size()]);
        }
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public /*{ScopeCode}*/ CodeList[] family() {
        return values();
    }
}
