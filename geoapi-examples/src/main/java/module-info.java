/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2023-2024 Open Geospatial Consortium, Inc.
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
 * Examples of GeoAPI implementations.
 * This module is provided only for illustrative purposes with simple implementations.
 * <strong>This module is not for use in production.</strong>
 * For real applications, use a library implementing GeoAPI or providing GeoAPI wrappers.
 *
 * <h2>Implementation details</h2>
 * In this example module, the implementation classes are not exported.
 * The intent is to demonstrate how geospatial services can be used through factories and standard interfaces.
 * The implementations in this module are very incomplete, but can be used as starting points for implementers.
 * Since every files in this module are placed into the Public Domain,
 * anyone is free to do whatever they wish with those files.
 * The following sections are for developers wanting to look at the implementation classes,
 * for example in order to develop their own library.
 *
 * <h3>Metadata</h3>
 * The {@link org.opengis.metadata} package and sub-packages contain a large number of interfaces,
 * which may give the impression that implementing metadata is very tedious.
 * However the {@link java.lang.reflect.Proxy} class can provide dynamically generated implementations.
 * The metadata example in this module shows how to implement every metadata interfaces with only two small classes.
 *
 * <h3>Referencing</h3>
 * The ISO 19111 model contains lot of details, some of them being ignored by existing libraries.
 * For example some libraries make no distinction between:
 *
 * <ul>
 *   <li>Ellipsoid and Geodetic Reference Frame</li>
 *   <li>Coordinate System and Coordinate Reference System</li>
 *   <li>Coordinate Operation and Math Transform</li>
 * </ul>
 *
 * This module follows the path of simpler libraries by merging different ISO concepts in the same class.
 * While this is not a recommended approach for strict ISO 19111 compliance, the intent is to demonstrate
 * that implementers are not forced to care about all ISO 19111 details.
 */
module org.opengis.geoapi.example {
    requires transitive java.measure;
    requires transitive java.desktop;       // For Java2D objects.
    requires transitive org.opengis.geoapi.pending;

    requires vecmath;
    requires java.naming;
    requires tech.uom.seshat;

    exports org.opengis.example;

    provides org.opengis.util.NameFactory
        with org.opengis.example.util.SimpleNameFactory;

    provides org.opengis.referencing.crs.CRSAuthorityFactory
        with org.opengis.example.referencing.SimpleAuthorityFactory;

    provides org.opengis.referencing.operation.MathTransformFactory
        with org.opengis.example.referencing.SimpleTransformFactory;
}
