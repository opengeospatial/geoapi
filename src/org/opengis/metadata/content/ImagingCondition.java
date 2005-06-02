/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2004-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.content;

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
 * Code which indicates conditions which may affect the image.
 *
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.0
 */
@UML(identifier="MD_ImagingConditionCode", specification=ISO_19115)
public final class ImagingCondition extends CodeList<ImagingCondition> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -1948380148063658761L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<ImagingCondition> VALUES = new ArrayList<ImagingCondition>(11);

    /**
     * Portion of the image is blurred.
     */
    @UML(identifier="blurredImage", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ImagingCondition BLURRED_IMAGE = new ImagingCondition("BLURRED_IMAGE");

    /**
     * Portion of the image is partially obscured by cloud cover
     */
    @UML(identifier="cloud", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ImagingCondition CLOUD = new ImagingCondition("CLOUD");

    /**
     * Acute angle between the plane of the ecliptic (the plane of the Earth’s orbit) and
     * the plane of the celestial equator.
     */
    @UML(identifier="degradingObliquity", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ImagingCondition DEGRADING_OBLIQUITY = new ImagingCondition("DEGRADING_OBLIQUITY");

    /**
     * Portion of the image is partially obscured by fog.
     */
    @UML(identifier="fog", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ImagingCondition FOG = new ImagingCondition("FOG");

    /**
     * Portion of the image is partially obscured by heavy smoke or dust.
     */
    @UML(identifier="heavySmokeOrDust", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ImagingCondition HEAVY_SMOKE_OR_DUST = new ImagingCondition("HEAVY_SMOKE_OR_DUST");

    /**
     * Image was taken at night.
     */
    @UML(identifier="night", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ImagingCondition NIGHT = new ImagingCondition("NIGHT");

    /**
     * Image was taken during rainfall.
     */
    @UML(identifier="rain", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ImagingCondition RAIN = new ImagingCondition("RAIN");

    /**
     * Image was taken during semi-dark conditions—twilight conditions.
     */
    @UML(identifier="semiDarkness", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ImagingCondition SEMI_DARKNESS = new ImagingCondition("SEMI_DARKNESS");

    /**
     * Portion of the image is obscured by shadow.
     */
    @UML(identifier="shadow", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ImagingCondition SHADOW = new ImagingCondition("SHADOW");

    /**
     * Portion of the image is obscured by snow.
     */
    @UML(identifier="snow", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ImagingCondition SNOW = new ImagingCondition("SNOW");

    /**
     * The absence of collection data of a given point or area caused by the relative
     * location of topographic features which obstruct the collection path between the
     * collector(s) and the subject(s) of interest.
     */
    @UML(identifier="terrainMasking", obligation=CONDITIONAL, specification=ISO_19115)
    public static final ImagingCondition TERRAIN_MASKING = new ImagingCondition("TERRAIN_MASKING");

    /**
     * Constructs an enum with the given name. The new enum is
     * automatically added to the list returned by {@link #values}.
     *
     * @param name The enum name. This name must not be in use by an other enum of this type.
     */
    public ImagingCondition(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code ImagingCondition}s.
     */
    public static ImagingCondition[] values() {
        synchronized (VALUES) {
            return (ImagingCondition[]) VALUES.toArray(new ImagingCondition[VALUES.size()]);
        }
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public /*{ImagingCondition}*/ CodeList[] family() {
        return values();
    }
}
