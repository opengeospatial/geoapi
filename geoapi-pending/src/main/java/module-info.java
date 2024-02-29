/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2023 Open Geospatial Consortium, Inc.
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
 * Java interfaces derived from specifications of <cite>International Organization for Standardization</cite> (ISO)
 * and <cite><a href="http://www.opengeospatial.org">Open Geospatial Consortium</a></cite> (OGC), pending review.
 * Some of those interfaces may migrate to the normative GeoAPI module when they will be considered ready.
 *
 * <p>Every interfaces defined in the <a href="http://www.geoapi.org/snapshot/">normative module</a> are reproduced here.
 * Consequently, importing the {@code geoapi-pending} module in a project is sufficient for using both the normative and
 * the experimental interfaces.</p>
 *
 * @version 4.0
 * @since 2.2
 */
module org.opengis.geoapi.pending {
    /*
     * Bellow is a copy-and-paste of geoapi module information,
     * with some additional exports at the bottom.
     */
    requires transitive java.measure;
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
    exports org.opengis.filter;
    exports org.opengis.filter.capability;

    exports org.opengis.temporal;
    exports org.opengis.geometry.primitive;

    // Additional exports
    exports org.opengis.referencing.gazetteer;
    exports org.opengis.geometry.aggregate;
    exports org.opengis.geometry.complex;
    exports org.opengis.coverage;
    exports org.opengis.coverage.grid;
    exports org.opengis.observation;
    exports org.opengis.observation.coverage;
    exports org.opengis.observation.sampling;
    exports org.opengis.style;
    exports org.opengis.style.portrayal;
}
