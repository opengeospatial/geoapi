/*
 *    Geotools2 - OpenSource mapping toolkit
 *    http://geotools.org
 *    (C) 2002-2005, Geotools Project Managment Committee (PMC)
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation;
 *    version 2.1 of the License.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 */
package org.opengis.feature;

import org.opengis.feature.Feature;
import org.opengis.feature.type.FeatureCollectionType;

/**
 * FeatureVisitor interface to allow for container optimized opperations.
 * <p>
 * The iterator construct from the Collections api is well understood and
 * loved, but breaks down for working with large GIS data volumes. By using a
 * visitor we allow the implementor of a Feature Collection to make use of
 * additional resources (such as multiple processors or tiled data)
 * concurrently.
 * </p>
 * This interface is most often used for calculations and data
 * transformations and an implementaiton may intercept commands it knows about
 * (such as "bounds" or reprojection) and engage an alternate workflow.
 * </p>
 * @author Cory Horner, Refractions
 */
public interface FeatureVisitor {
	/**
	 * Called before accepting Features to allow the calculation to prepair
	 * any state required.
	 */
    public void init( FeatureCollectionType collection );
    
	public void visit(Feature feature);
}