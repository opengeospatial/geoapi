/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2018-2021 Open Geospatial Consortium, Inc.
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
 * The interfaces were created from UML diagrams found in the <abbr>ISO</abbr> and <abbr>OGC</abbr> specifications.
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
