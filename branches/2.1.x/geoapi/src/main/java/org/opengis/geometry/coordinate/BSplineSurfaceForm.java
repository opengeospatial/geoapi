/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2005-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.geometry.coordinate;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import org.opengis.referencing.cs.AxisDirection;
import org.opengis.util.CodeList;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Indicates a particular geometric form represented by a {@link BSplineSurface}.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/as">ISO 19107</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.1
 */
@UML(identifier="GM_BSplineSurfaceForm", specification=ISO_19107)
public class BSplineSurfaceForm extends CodeList<BSplineSurfaceForm> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -5066463171878030795L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<BSplineSurfaceForm> VALUES = new ArrayList<BSplineSurfaceForm>(6);

    /**
     * A bounded portion of a plane represented by a B-spline surface of degree 1 in each parameter.
     */
    @UML(identifier="planar", obligation=CONDITIONAL, specification=ISO_19107)
    public static final BSplineSurfaceForm PLANAR = new BSplineSurfaceForm("PLANAR");

    /**
     * A bounded portion of a cylindrical surface represented by a B-spline surface.
     */
    @UML(identifier="cylindrical", obligation=CONDITIONAL, specification=ISO_19107)
    public static final BSplineSurfaceForm CYLINDRICAL = new BSplineSurfaceForm("CYLINDRICAL");

    /**
     * A bounded portion of the surface of a right circular cone represented by a B-spline surface.
     */
    @UML(identifier="conical", obligation=CONDITIONAL, specification=ISO_19107)
    public static final BSplineSurfaceForm CONICAL = new BSplineSurfaceForm("CONICAL");

    /**
     * A bounded portion of a sphere, or a complete sphere represented by a B-spline surface.
     */
    @UML(identifier="spherical", obligation=CONDITIONAL, specification=ISO_19107)
    public static final BSplineSurfaceForm SPHERICAL = new BSplineSurfaceForm("SPHERICAL");

    /**
     * A torus or a portion of a torus represented by a B-spline surface.
     */
    @UML(identifier="toroidal", obligation=CONDITIONAL, specification=ISO_19107)
    public static final BSplineSurfaceForm TOROIDAL = new BSplineSurfaceForm("TOROIDAL");

    /**
     * No particular surface is specified..
     */
    @UML(identifier="unspecified", obligation=CONDITIONAL, specification=ISO_19107)
    public static final BSplineSurfaceForm UNSPECIFIED = new BSplineSurfaceForm("UNSPECIFIED");

    /**
     * Constructs an enum with the given name. The new enum is
     * automatically added to the list returned by {@link #values}.
     *
     * @param name The enum name. This name must not be in use by an other enum of this type.
     */
    private BSplineSurfaceForm(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code BSplineSurfaceForm}s.
     */
    public static BSplineSurfaceForm[] values() {
        synchronized (VALUES) {
            return (BSplineSurfaceForm[]) VALUES.toArray(new BSplineSurfaceForm[VALUES.size()]);
        }
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public /*{BSplineSurfaceForm}*/ CodeList[] family() {
        return values();
    }

    /**
     * Returns the BSplineSurfaceForm that matches the given string, or returns a
     * new one if none match it.
     */
    public static synchronized BSplineSurfaceForm valueOf(String code) {
        if (code == null) {
            return null;
        }
        Iterator iter = VALUES.iterator();
        while (iter.hasNext()) {
            BSplineSurfaceForm type = (BSplineSurfaceForm) iter.next();
            if (code.equalsIgnoreCase(type.name())) {
                return type;
            }
        }
        return new BSplineSurfaceForm(code);
    }
}
