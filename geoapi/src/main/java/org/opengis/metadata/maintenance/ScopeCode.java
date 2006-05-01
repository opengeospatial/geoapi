/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2004-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.maintenance;

// J2SE directdependencies
import java.util.List;
import java.util.ArrayList;

// OpenGIS direct dependencies
import org.opengis.util.CodeList;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Class of information to which the referencing entity applies.
 *
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.0
 *
 * @see org.opengis.metadata.quality.Scope
 */
@UML(identifier="MD_ScopeCode", specification=ISO_19115)
public final class ScopeCode extends CodeList<ScopeCode> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -4542429199783894255L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<ScopeCode> VALUES = new ArrayList<ScopeCode>(15);

    /**
     * Information applies to the attribute class.
     *
     * @todo Should we said "... to the attribute object"?
     */
    @UML(identifier="attribute", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ScopeCode ATTRIBUTE = new ScopeCode("ATTRIBUTE");

    /**
     * Information applies to the characteristic of a feature.
     */
    @UML(identifier="attributeType", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ScopeCode ATTRIBUTE_TYPE = new ScopeCode("ATTRIBUTE_TYPE");

    /**
     * Information applies to the collection hardware class.
     */
    @UML(identifier="collectionHardware", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ScopeCode COLLECTION_HARDWARE = new ScopeCode("COLLECTION_HARDWARE");

    /**
     * Information applies to the collection session.
     */
    @UML(identifier="collectionSession", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ScopeCode COLLECTION_SESSION = new ScopeCode("COLLECTION_SESSION");

    /**
     * Information applies to the dataset.
     */
    @UML(identifier="dataset", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ScopeCode DATASET = new ScopeCode("DATASET");

    /**
     * Information applies to the series.
     */
    @UML(identifier="series", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ScopeCode SERIES = new ScopeCode("SERIES");

    /**
     * information applies to non-geographic data;
     */
    @UML(identifier="nonGeographicDataset", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ScopeCode NON_GEOGRAPHIC_DATASET = new ScopeCode("NON_GEOGRAPHIC_DATASET");

    /**
     * Information applies to a dimension group.
     */
    @UML(identifier="dimensionGroup", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ScopeCode DIMENSION_GROUP = new ScopeCode("DIMENSION_GROUP");

    /**
     * Information applies to a feature.
     */
    @UML(identifier="feature", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ScopeCode FEATURE = new ScopeCode("FEATURE");

    /**
     * Information applies to a feature type.
     */
    @UML(identifier="featureType", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ScopeCode FEATURE_TYPE = new ScopeCode("FEATURE_TYPE");

    /**
     * Information applies to a property type.
     */
    @UML(identifier="propertyType", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ScopeCode PROPERTY_TYPE = new ScopeCode("PROPERTY_TYPE");

    /**
     * Information applies to a field session.
     */
    @UML(identifier="fieldSession", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ScopeCode FIELD_SESSION = new ScopeCode("FIELD_SESSION");

    /**
     * Information applies to a computer program or routine.
     */
    @UML(identifier="software", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ScopeCode SOFTWARE = new ScopeCode("SOFTWARE");

    /**
     * Information applies to a capability which a service provider entity makes available
     * to a service user entity through a set of interfaces that define a behaviour, such as
     * a use case.
     */
    @UML(identifier="service", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ScopeCode SERVICE = new ScopeCode("SERVICE");

    /**
     * Information applies to a copy or imitation of an existing or hypothetical object.
     */
    @UML(identifier="model", obligation=CONDITIONAL, specification=ISO_19115)
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
     * Returns the list of {@code ScopeCode}s.
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
