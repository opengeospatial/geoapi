/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2006-2021 Open Geospatial Consortium, Inc.
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
package org.opengis.temporal;

import org.opengis.annotation.UML;

import static org.opengis.annotation.Specification.*;

/**
 * The Julian day numbering system is a temporal coordinate system that has its origin at noon
 * on 1 January 4713 BC in the Julian proleptic calendar. The Julian day number is an integer
 * value; the Julian date is a decimal value that allows greater resolution.
 *
 * @author Stephane Fellah (Image Matters)
 * @since   2.3
 * @version 4.0
 */
@UML(identifier="JulianDate", specification=ISO_19108)
public interface JulianDate extends TemporalCoordinate {
}
