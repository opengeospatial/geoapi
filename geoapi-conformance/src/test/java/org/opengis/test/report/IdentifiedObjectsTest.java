/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2011-2021 Open Geospatial Consortium, Inc.
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
package org.opengis.test.report;

import org.junit.*;
import static org.junit.Assert.*;
import static org.opengis.test.report.IdentifiedObjects.SEPARATOR;


/**
 * Tests {@link IdentifiedObjects}.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public class IdentifiedObjectsTest {
    /**
     * Tests {@link IdentifiedObjects#compare(String[], String[])}.
     */
    @Test
    public void testCompareArrays() {
        assertTrue(IdentifiedObjects.compare("EPSG:4326".split(SEPARATOR), "EPSG:004326".split(SEPARATOR)) == 0);
        assertTrue(IdentifiedObjects.compare("EPSG:4326".split(SEPARATOR), "EPSG:000432".split(SEPARATOR)) >  0);
        assertTrue(IdentifiedObjects.compare("EPSG"     .split(SEPARATOR), "EPSG:4326"  .split(SEPARATOR)) <  0);
    }
}
