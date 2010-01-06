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
package org.opengis.metadata.content;

import java.util.ArrayList;
import java.util.List;

import org.opengis.annotation.UML;
import org.opengis.util.CodeList;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Polarization of the antenna relative to the waveform.
 *
 * @author Cédric Briançon (Geomatys)
 *
 * @since GeoAPI 2.3
 */
@UML(identifier="MI_PolarizationOrientationCode", specification=ISO_19115_2)
public final class PolarizationOrientation extends CodeList<PolarizationOrientation> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -8653877364510456891L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<PolarizationOrientation> VALUES = new ArrayList<PolarizationOrientation>(6);

    /**
     * Polarization of the sensor oriented in the horizontal plane in relation to swath direction.
     */
    @UML(identifier="horizontal", obligation=CONDITIONAL, specification=ISO_19115_2)
    public static final PolarizationOrientation HORIZONTAL = new PolarizationOrientation("HORIZONTAL");

    /**
     * Polarization of the sensor oriented in the vertical plane in relation to swath direction.
     */
    @UML(identifier="vertical", obligation=CONDITIONAL, specification=ISO_19115_2)
    public static final PolarizationOrientation VERTICAL = new PolarizationOrientation("VERTICAL");

    /**
     * Polarization of the sensor oriented in the left circular plane in relation to swath direction.
     */
    @UML(identifier="leftCircular", obligation=CONDITIONAL, specification=ISO_19115_2)
    public static final PolarizationOrientation LEFT_CIRCULAR = new PolarizationOrientation("LEFT_CIRCULAR");

    /**
     * Polarization of the sensor oriented in the right circular plane in relation to swath direction.
     */
    @UML(identifier="rightCircular", obligation=CONDITIONAL, specification=ISO_19115_2)
    public static final PolarizationOrientation RIGHT_CIRCULAR = new PolarizationOrientation("RIGHT_CIRCULAR");

    /**
     * Polarization of the sensor oriented in the angle between +90 ° and 0 ° parallel to swath direction.
     */
    @UML(identifier="theta", obligation=CONDITIONAL, specification=ISO_19115_2)
    public static final PolarizationOrientation THETA = new PolarizationOrientation("THETA");

    /**
     * Polarization of the sensor oriented in the +90 ° and 0 ° perpendicular to swath direction.
     */
    @UML(identifier="phi", obligation=CONDITIONAL, specification=ISO_19115_2)
    public static final PolarizationOrientation PHI = new PolarizationOrientation("PHI");

    /**
     * Constructs an enum with the given name. The new enum is
     * automatically added to the list returned by {@link #values}.
     *
     * @param name The enum name. This name must not be in use by an other enum of this type.
     */
    private PolarizationOrientation(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code PolarizationOrientation}s.
     *
     * @return The list of codes declared in the current JVM.
     */
    public static PolarizationOrientation[] values() {
        synchronized (VALUES) {
            return VALUES.toArray(new PolarizationOrientation[VALUES.size()]);
        }
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public PolarizationOrientation[] family() {
        return values();
    }

    /**
     * Returns the transfer function type that matches the given string, or returns a
     * new one if none match it.
     *
     * @param code The name of the code to fetch or to create.
     * @return A code matching the given name.
     */
    public static PolarizationOrientation valueOf(String code) {
        return valueOf(PolarizationOrientation.class, code);
    }
}
