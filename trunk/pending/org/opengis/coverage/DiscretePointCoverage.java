/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.coverage;

// J2SE direct dependencies
import java.util.Set;
import java.util.List;

// OpenGIS direct dependencies
import org.opengis.spatialschema.geometry.Geometry;
import org.opengis.spatialschema.geometry.DirectPosition;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A discrete coverage characterized by a finite domain consisting of points. Generally, the domain
 * is a set of irregularly distributed points. However, the principal use of discrete point coverages
 * is to provide a basis for continuous coverage functions, where the evaluation of the continuous
 * coverage function is accomplished by interpolation between the points of the discrete point coverage.
 * Most interpolation algorithms depend upon a structured pattern of spatial relationships between the
 * points. This requires either that the points in the spatial domain of the discrete point coverage
 * be arranged in a regular way, or that the spatial domain of the continuous coverage be partitioned
 * in a regular way in relation to the points of the discrete point coverage. Grid coverages employ
 * the first method; Thiessen polygon coverages and TIN’s employ the second.
 * <p>
 * <strong>EXAMPLE:</strong> A set of hydrographic soundings is a discrete point coverage.
 * <p>
 * {@code DiscretePointCoverage} inherits the association {@link #getCoverageFunction CoverageFunction}
 * and the operations {@link #locate locate}, {@link #find find}, and {@link #list list} from
 * {@link DiscreteCoverage}, with the restriction that the associated {@kink GeometryValuePair}s and
 * those returned by the operations shall be limited to {@link PointValuePair}s.
 *
 * @author Martin Desruisseaux
 */
@UML(identifier="CV_DiscretePointCoverage", specification=ISO_19123)
public interface DiscretePointCoverage extends DiscreteCoverage {
    /**
     * {@inheritDoc}
     */
    @UML(identifier="CoverageFunction", obligation=OPTIONAL, specification=ISO_19123)
    Set<PointValuePair> getCoverageFunction();

    /**
     * {@inheritDoc}
     */
    @UML(identifier="locate", obligation=OPTIONAL, specification=ISO_19123)
    Set<PointValuePair> locate(DirectPosition p);

    /**
     * {@inheritDoc}
     */
    @UML(identifier="list", obligation=MANDATORY, specification=ISO_19123)
    Set<PointValuePair> list();

    /**
     * {@inheritDoc}
     */
    @UML(identifier="select", obligation=MANDATORY, specification=ISO_19123)
    Set<PointValuePair> select(Geometry s/*, TM_Period t*/); // TODO

    /**
     * {@inheritDoc}
     */
    @UML(identifier="find", obligation=MANDATORY, specification=ISO_19123)
    List<PointValuePair> find(DirectPosition p, int limit);

    /**
     * {@inheritDoc}
     */
/// @UML(identifier="find", obligation=MANDATORY, specification=ISO_19123)
/// PointValuePair find(DirectPosition p);
}
