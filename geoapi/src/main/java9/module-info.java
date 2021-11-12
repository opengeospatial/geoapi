/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2018-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Permission to use, copy, and modify this software and its documentation, with
 *    or without modification, for any purpose and without fee or royalty is hereby
 *    granted, provided that you include the following on ALL copies of the software
 *    and documentation or portions thereof, including modifications, that you make:
 *
 *    1. The full text of this NOTICE in a location viewable to users of the
 *       redistributed or derivative work.
 *    2. Notice of any changes or modifications to the OGC files, including the
 *       date changes were made.
 *
 *    THIS SOFTWARE AND DOCUMENTATION IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE
 *    NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *    TO, WARRANTIES OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT
 *    THE USE OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
 *    PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
 *
 *    COPYRIGHT HOLDERS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL OR
 *    CONSEQUENTIAL DAMAGES ARISING OUT OF ANY USE OF THE SOFTWARE OR DOCUMENTATION.
 *
 *    The name and trademarks of copyright holders may NOT be used in advertising or
 *    publicity pertaining to the software without specific, written prior permission.
 *    Title to copyright in this software and any associated documentation will at all
 *    times remain with copyright holders.
 */


/**
 * Java interfaces derived from specifications of <cite>International Organization for Standardization</cite> (<abbr>ISO</abbr>)
 * and <cite><a href="http://www.opengeospatial.org">Open Geospatial Consortium</a></cite> (<abbr>OGC</abbr>).
 * The interfaces were created from UML diagrams found in the <abbr>ISO</abbr> and <abbr>OGC</abbr> specifications.
 *
 * <h2>Links</h2>
 * <ul>
 *   <li><a href="http://www.geoapi.org/">GeoAPI home page</a></li>
 *   <li><a href="http://www.opengeospatial.org/standards/geoapi/"><abbr>OGC</abbr> GeoAPI specification</a></li>
 *   <li><a href="content.html">GeoAPI content (with mapping to <abbr>OGC</abbr>/<abbr>ISO</abbr> <abbr>UML</abbr>)</a></li>
 *   <li><a href="UML-Java.html">Mapping from <abbr>UML</abbr> diagrams to Java interfaces</a></li>
 *   <li><a href="departures.html">Departures in Java interfaces compared to <abbr>UML</abbr> diagrams</a></li>
 * </ul>
 *
 * @version 3.0.2
 * @since 1.0
 */
module org.opengis.geoapi {
    requires transitive java.measure;
    /*
     * The only java.desktop dependency is the java.awt.geom.Point2D class used in
     * org.opengis.referencing.operation.MathTransform2D.  Since that interface is
     * not directly referenced anywhere, it can easily be ignored by implementers.
     */
    requires transitive static java.desktop;

    exports org.opengis.annotation;
    exports org.opengis.util;
    exports org.opengis.metadata;
    exports org.opengis.metadata.acquisition;
    exports org.opengis.metadata.citation;
    exports org.opengis.metadata.constraint;
    exports org.opengis.metadata.content;
    exports org.opengis.metadata.distribution;
    exports org.opengis.metadata.extent;
    exports org.opengis.metadata.identification;
    exports org.opengis.metadata.lineage;
    exports org.opengis.metadata.maintenance;
    exports org.opengis.metadata.quality;
    exports org.opengis.metadata.spatial;
    exports org.opengis.parameter;
    exports org.opengis.referencing;
    exports org.opengis.referencing.datum;
    exports org.opengis.referencing.cs;
    exports org.opengis.referencing.crs;
    exports org.opengis.referencing.operation;
    exports org.opengis.geometry;
    exports org.opengis.geometry.coordinate;
    exports org.opengis.feature.type;

    exports org.opengis.temporal;
    exports org.opengis.geometry.primitive;
}
