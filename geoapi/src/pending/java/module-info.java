/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2017-2024 Open Geospatial Consortium, Inc.
 *    http://www.geoapi.org
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
 *
 * <h2>Links</h2>
 * <ul>
 *   <li><a href="https://www.ogc.org/">Open Geospatial Consortium</a></li>
 *   <li><a href="https://www.geoapi.org/">GeoAPI home page</a></li>
 *   <li><a href="https://www.ogc.org/standard/geoapi/"><abbr>OGC</abbr> GeoAPI specification</a></li>
 *   <li><a href="http://docs.opengeospatial.org/is/18-010r7/18-010r7.html">Well-Known Text 2 (<abbr>WKT</abbr> 2) specification</a></li>
 * </ul>
 *
 * <h2>Requirements</h2>
 * <ul>
 *   <li><a href="https://docs.oracle.com/en/java/javase/11/">Java 11</a> or later.</li>
 *   <li><a href="https://jcp.org/en/jsr/detail?id=385"><abbr>JSR</abbr>-385</a> units of measurement API.</li>
 * </ul>
 *
 * @version 3.1
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
    exports org.opengis.feature.type;
    exports org.opengis.filter;
    exports org.opengis.filter.capability;

    exports org.opengis.temporal;
    exports org.opengis.geometry.primitive;
}
