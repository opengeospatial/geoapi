/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2018-2021 Open Geospatial Consortium, Inc.
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
 *   <li><a href="http://docs.opengeospatial.org/is/18-010r7/18-010r7.html"><cite>Well-Known Text 2</cite> (<abbr>WKT</abbr> 2) specification</a></li>
 *   <li><a href="org/opengis/referencing/doc-files/WKT.html"><cite>Well-Known Text 1</cite> (<abbr>WKT</abbr> 1) specification</a></li>
 *   <li><a href="future-work.html">Future work</a></li>
 * </ul>
 *
 * <h2>Requirements</h2>
 * <ul>
 *   <li><a href="http://docs.oracle.com/javase/7/docs/index.html">Java&nbsp;7</a> or later.</li>
 *   <li><a href="https://jcp.org/en/jsr/detail?id=385"><abbr>JSR</abbr>-385</a> units of measurement API.</li>
 * </ul>
 *
 * @version 4.0
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
    exports org.opengis.feature;

    exports org.opengis.temporal;
    exports org.opengis.geometry.primitive;
}
