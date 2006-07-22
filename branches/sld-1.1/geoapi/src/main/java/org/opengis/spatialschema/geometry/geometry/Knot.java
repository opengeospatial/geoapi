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
package org.opengis.spatialschema.geometry.geometry;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Controls the constructive parameter space for spline curves and surfaces. Each knot sequence
 * is used for a dimension of the spline's parameter space. Thus, in a surface spline, there will
 * be two knot sequences, one for each parameter (<var>u</var>, <var>v</var>).
 * The <var>i</var><sup>th</sup>, <var>j</var><sup>th</sup> would be (<var>u<sub>i</sub></var>,
 * <var>v<sub>j</sub></var>), where the original knot sequences were (<var>u<sub>i</sub></var>)
 * and (<var>v<sub>j</sub></var>). Each knot of a spline curve or surface is described using a
 * {@code Knot}.
 *
 * @version <A HREF="http://www.opengis.org/docs/01-101.pdf">Abstract specification 5</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.0
 */
@UML(identifier="GM_Knot", specification=ISO_19107)
public interface Knot {
    /**
     * The value of the parameter at the knot of the spline. The sequence of knots shall be a
     * nondecreasing sequence. That is, each knot's value in the sequence shall be equal to or
     * greater than the previous knot's value. The use of equal consecutive knots is normally
     * handled using the multiplicity.
     */
    @UML(identifier="value", obligation=MANDATORY, specification=ISO_19107)
    double getValue();

    /**
     * The multiplicity of this knot used in the definition of the spline (with the same weight).
     */
    @UML(identifier="multiplicity", obligation=MANDATORY, specification=ISO_19107)
    int getMultiplicity();

    /**
     * The value of the averaging weight used for this knot of the spline.
     */
    @UML(identifier="weight", obligation=MANDATORY, specification=ISO_19107)
    double getWeight();
}
