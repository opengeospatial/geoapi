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

/**
 * FeatureVisitor interface to allow for container optimised traversal.
 * <p>
 * The iterator construct from the Collections api is well understood and
 * loved, but breaks down for working with large GIS data volumes. By using a
 * visitor we allow the implementor of a Feature Collection to make use of
 * additional resources (such as multiple processors or tiled data)
 * concurrently.
 * </p>
 * This interface is most often used for calculations and data
 * transformations and an implementations may intercept known visitors
 * (such as "bounds" or reprojection) and engage an alternate workflow.
 * </p>
 * @author Cory Horner, Refractions
 */
public interface FeatureVisitor {
	/**
	 * Visit the provided feature.
	 * <p>
	 * Please consult the documentation for the FeatureCollection you are visiting
	 * to learn more - the provided feature may be invalid, or read only.
	 * @param feature
	 */
	public void visit(Feature feature);
}