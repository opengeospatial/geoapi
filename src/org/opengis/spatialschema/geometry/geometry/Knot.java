/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.spatialschema.geometry.geometry;

// Annotations
///import org.opengis.annotation.UML;
///import static org.opengis.annotation.Obligation.*;


/**
 * Controls the constructive parameter space for spline curves and surfaces. Each knot sequence
 * is used for a dimension of the spline's parameter space. Thus, in a surface spline, there will
 * be two knot sequences, one for each parameter (<var>u</var>, <var>v</var>).
 * The <var>i</var><sup>th</sup>, <var>j</var><sup>th</sup> would be (<var>u<sub>i</sub></var>,
 * <var>v<sub>j</sub></var>), where the original knot sequences were (<var>u<sub>i</sub></var>)
 * and (<var>v<sub>j</sub></var>). Each knot of a spline curve or surface is described using a
 * <code>Knot</code>.
 *
 * @author ISO/DIS 19107
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 */
/// @UML (identifier="GM_Knot")
public interface Knot {
    /**
     * The value of the parameter at the knot of the spline. The sequence of knots shall be a
     * nondecreasing sequence. That is, each knot's value in the sequence shall be equal to or
     * greater than the previous knot's value. The use of equal consecutive knots is normally
     * handled using the multiplicity.
     */
/// @UML (identifier="value", obligation=MANDATORY)
    double getValue();

    /**
     * The multiplicity of this knot used in the definition of the spline (with the same weight).
     */
/// @UML (identifier="multiplicity", obligation=MANDATORY)
    int getMultiplicity();

    /**
     * The value of the averaging weight used for this knot of the spline.
     */
/// @UML (identifier="weight", obligation=MANDATORY)
    double getWeight();
}
