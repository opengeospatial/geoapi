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
import java.util.List;
import java.util.Set;

// OpenGIS direct dependencies
import org.opengis.coverage.grid.GridPoint;
import org.opengis.coverage.grid.GridPointValuePair;
import org.opengis.coverage.grid.GridCoordinates;
import org.opengis.coverage.grid.GridValuesMatrix;
import org.opengis.spatialschema.geometry.DirectPosition;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A {@linkplain DiscreteCoverage discrete coverage} with a domain defined as a set of
 * {@linkplain  GridPoint grid points} that are associated with records of feature attribute
 * values through a {@linkplain grid values matrix GridValuesMatrix}.
 * <p>
 * {@code DiscreteGridPointCoverage} inherits the association {@link #getCoverageFunction CoverageFunction}
 * and the operations {@link #locate locate}, {@link #find find}, and {@link #list list}, from
 * {@link DiscreteCoverage}, with the restriction that the associated {@link GeometryValuePair}s
 * and those returned by the operations shall be limited to {@link GridPointValuePair}s. The
 * association {@link #getCoverageFunction CoverageFunction} is shown as derived in this case
 * because the elements may be generated from the {@link GridValuesMatrix} through the association
 * {@link #getPointFunction PointFunction}. The inherited operations {@link #evaluate evaluate} and
 * {@link #evaluateInverse evaluateInverse} use {@link GridValuesMatrix} to assign values to the
 * {@link GeometryValuePair}s.
 * 
 * @author Wim Koolhoven
 * @author Martin Desruiseaux
 */
@UML(identifier="CV_DiscreteGridPointCoverage", specification=ISO_19123)
public interface DiscreteGridPointCoverage extends DiscreteCoverage {
    /**
     * {@inheritDoc}
     */
    @UML(identifier="CoverageFunction", obligation=OPTIONAL, specification=ISO_19123)
    Set<GridPointValuePair> getCoverageFunction();

    /**
     * Link this {@code DiscreteGridPointCoverage} to the {@code GridValuesMatrix}
     * for which it is an evaluator.
     */
    @UML(identifier="PointFunction", obligation=MANDATORY, specification=ISO_19123)
    GridValuesMatrix getPointFunction();

    /**
     * {@inheritDoc}
     */
    @UML(identifier="locate", obligation=OPTIONAL, specification=ISO_19123)
    Set<GridPointValuePair> locate(DirectPosition p);

    /**
     * {@inheritDoc}
     */
/// @UML(identifier="find", obligation=MANDATORY, specification=ISO_19123)
/// GridPointValuePair find(DirectPosition p); 

    /**
     * {@inheritDoc}
     */
    @UML(identifier="find", obligation=MANDATORY, specification=ISO_19123)
    List<GridPointValuePair> find(DirectPosition p, int limit);

    /**
     * {@inheritDoc}
     */
    @UML(identifier="list", obligation=MANDATORY, specification=ISO_19123)
    Set<GridPointValuePair> list();

    /**
     * Uses data from the associated {@link GridValuesMatrix} to construct and return the
     * grid point value pair associated with the specified grid position.
     */
    @UML(identifier="point", obligation=MANDATORY, specification=ISO_19123)
    GridPointValuePair point(GridCoordinates g);

    @UML(identifier="evaluateInverse", obligation=MANDATORY, specification=ISO_19123)
    Set<GridPoint> evaluateInverse(Object /*<Record>*/ v); // TODO    
}
