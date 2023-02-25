/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2005-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.opengis.geometry.coordinate;

import java.util.List;
import java.util.ArrayList;

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
     * Constructs an element of the given name. The new element is
     * automatically added to the list returned by {@link #values()}.
     *
     * @param  name  the name of the new element.
     *        This name must not be in use by another element of this type.
     */
    private BSplineSurfaceForm(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code BSplineSurfaceForm}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static BSplineSurfaceForm[] values() {
        synchronized (VALUES) {
            return VALUES.toArray(new BSplineSurfaceForm[VALUES.size()]);
        }
    }

    /**
     * Returns the list of codes of the same kind than this code list element.
     * Invoking this method is equivalent to invoking {@link #values()}, except that
     * this method can be invoked on an instance of the parent {@code CodeList} class.
     *
     * @return all code {@linkplain #values() values} for this code list.
     */
    @Override
    public BSplineSurfaceForm[] family() {
        return values();
    }

    /**
     * Returns the B-spline surface form that matches the given string, or returns a
     * new one if none match it. More specifically, this methods returns the first instance for
     * which <code>{@linkplain #name() name()}.{@linkplain String#equals equals}(code)</code>
     * returns {@code true}. If no existing instance is found, then a new one is created for
     * the given name.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static BSplineSurfaceForm valueOf(String code) {
        return valueOf(BSplineSurfaceForm.class, code);
    }
}
