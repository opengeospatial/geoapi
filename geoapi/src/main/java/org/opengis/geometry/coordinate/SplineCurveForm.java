/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.geometry.coordinate;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import org.opengis.metadata.spatial.SpatialRepresentationType;
import org.opengis.util.CodeList;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Indicates which sort of curve may be approximated by a particular B-spline.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/as">ISO 19107</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.0
 */
@UML(identifier="GM_SplineCurveForm", specification=ISO_19107)
public final class SplineCurveForm extends CodeList<SplineCurveForm> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = 7692137703533158212L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<SplineCurveForm> VALUES = new ArrayList<SplineCurveForm>(5);

    /**
     * A connected sequence of line segments represented by a 1 degree B-spline (a line string).
     */
    @UML(identifier="polylineForm", obligation=CONDITIONAL, specification=ISO_19107)
    public static final SplineCurveForm POLYLINE_FORM = new SplineCurveForm(
                                       "POLYLINE_FORM");

    /**
     * An arc of a circle or a complete circle.
     */
    @UML(identifier="circularArc", obligation=CONDITIONAL, specification=ISO_19107)
    public static final SplineCurveForm CIRCULAR_ARC = new SplineCurveForm(
                                       "CIRCULAR_ARC");

    /**
     * An arc of an ellipse or a complete ellipse.
     */
    @UML(identifier="ellipticalArc", obligation=CONDITIONAL, specification=ISO_19107)
    public static final SplineCurveForm ELLIPTICAL_ARC = new SplineCurveForm(
                                       "ELLIPTICAL_ARC");

    /**
     * An arc of a finite length of a parabola.
     */
    @UML(identifier="parabolicArc", obligation=CONDITIONAL, specification=ISO_19107)
    public static final SplineCurveForm PARABOLIC_ARC = new SplineCurveForm(
                                       "PARABOLIC_ARC");

    /**
     * An arc of a finite length of one branch of a hyperbola.
     */
    @UML(identifier="hyperbolicArc", obligation=CONDITIONAL, specification=ISO_19107)
    public static final SplineCurveForm HYPERBOLIC_ARC = new SplineCurveForm(
                                       "HYPERBOLIC_ARC");

    /**
     * Constructs an enum with the given name. The new enum is
     * automatically added to the list returned by {@link #values}.
     *
     * @param name The enum name. This name must not be in use by an other enum of this type.
     */
    private SplineCurveForm(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code SplineCurveForm}s.
     */
    public static SplineCurveForm[] values() {
        synchronized (VALUES) {
            return (SplineCurveForm[]) VALUES.toArray(new SplineCurveForm[VALUES.size()]);
        }
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public /*{SplineCurveForm}*/ CodeList[] family() {
        return values();
    }

    /**
     * Returns the SplineCurveForm that matches the given string, or returns a
     * new one if none match it.
     */
    public static synchronized SplineCurveForm valueOf(String code) {
        if (code == null) {
            return null;
        }
        Iterator iter = VALUES.iterator();
        while (iter.hasNext()) {
            SplineCurveForm type = (SplineCurveForm) iter.next();
            if (code.equalsIgnoreCase(type.name())) {
                return type;
            }
        }
        return new SplineCurveForm(code);
    }
}
