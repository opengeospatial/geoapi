/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 */

/**
 * Provides examples of simple GeoAPI implementations. The classes provided in this package are
 * simpler than GeoAPI interfaces, in that they merge many concepts that GeoAPI keep distinct.
 * For example, many existing projection libraries make no distinction between
 * <cite>Coordinate System</cite> (CS) and <cite>Coordinate Reference System</cite> (CRS).
 * This package follows this simplified model by implementing the CS and CRS interfaces in
 * a single class, {@code SimpleCRS}. The table below summarizes the classes in this package
 * which merge different GeoAPI concepts:
 * <p>
 * <table><tr>
 * <th>Simple class</th>
 * <th colspan="2">Implements</th>
 * </tr><tr>
 * <td>{@link org.opengis.example.simple.SimpleCRS}</td>
 * <td>{@link org.opengis.referencing.crs.CoordinateReferenceSystem}</td>
 * <td>{@link org.opengis.referencing.cs.CoordinateSystem}</td>
 * </tr><tr>
 * <td>{@link org.opengis.example.simple.SimpleDatum}</td>
 * <td>{@link org.opengis.referencing.datum.GeodeticDatum}</td>
 * <td>{@link org.opengis.referencing.datum.Ellipsoid}</td>
 * </tr><tr>
 * <td>{@link org.opengis.example.simple.SimpleTransform}</td>
 * <td>{@link org.opengis.referencing.operation.CoordinateOperation}</td>
 * <td>{@link org.opengis.referencing.operation.MathTransform}</td>
 * </tr><tr>
 * <td>{@link org.opengis.example.simple.SimpleIdentifiedObject}</td>
 * <td>{@link org.opengis.referencing.IdentifiedObject}</td>
 * <td>{@link org.opengis.referencing.ReferenceIdentifier}</td>
 * </tr><tr>
 * <td>{@link org.opengis.example.simple.SimpleCitation}</td>
 * <td>{@link org.opengis.metadata.citation.Citation}</td>
 * <td>{@link org.opengis.util.InternationalString}</td>
 * </tr>
 * </table>
 * <p>
 * Every classes in this package are hereby placed into the Public Domain.
 * This means anyone is free to do whatever they wish with those files.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
package org.opengis.example.simple;
