/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2018-2023 Open Geospatial Consortium, Inc.
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
 * An example of a very simple GeoAPI implementation.
 * This implementation is not complete, but can be used as a starting point for implementers.
 * Since every files in this module are placed into the Public Domain,
 * anyone is free to do whatever they wish with those files.
 *
 * <h2>Metadata</h2>
 * The {@link org.opengis.metadata} package and sub-packages contain a large amount of interfaces,
 * which may give the impression that implementing metadata is very tedious.
 * However the {@link java.lang.reflect.Proxy} class can provide dynamically generated implementations.
 * The metadata example in this module shows how to implement every metadata interfaces with only two small classes.
 *
 * <h2>Referencing</h2>
 * The ISO 19111 model contains lot of details, some of them being ignored by existing libraries.
 * For example some libraries make no distinction between:
 *
 * <ul>
 *   <li>Ellipsoid and Geodetic Datum</li>
 *   <li>Coordinate System and Coordinate Reference System</li>
 *   <li>Coordinate Operation and Math Transform</li>
 * </ul>
 *
 * This module follows the path of simpler libraries by merging different ISO concepts in the same class.
 * While this is not a recommended approach for strict ISO 19111 compliance, the intent is to demonstrate
 * that implementers can hide some of the ISO 19111 complexity.
 *
 * @version 4.0
 */
module org.opengis.geoapi.example {
    requires transitive vecmath;
    requires transitive java.measure;
    requires transitive org.opengis.geoapi.pending;

    requires java.naming;
    requires tech.uom.seshat;

    exports org.opengis.example.geometry;
    exports org.opengis.example.metadata;
    exports org.opengis.example.parameter;
    exports org.opengis.example.referencing;

    provides org.opengis.util.NameFactory with org.opengis.example.util.SimpleNameFactory;
}
