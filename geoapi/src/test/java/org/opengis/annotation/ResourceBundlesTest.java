/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2011-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.annotation;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Tests {@link ResourceBundles}.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public final class ResourceBundlesTest {
    /**
     * Verifies the {@code *_CAPACITY} constant values.
     *
     * @throws IOException if an error occurred while loading the properties file.
     */
    @Test
    public void verifyCapacities() throws IOException {
        assertEquals(ResourceBundles.classIndex().size(), ResourceBundles.INDEX_CAPACITY);
    }

    /**
     * Tests {@link ResourceBundles#codeLists(Locale)}
     */
    @Test
    public void testCodeLists() {
        final String key = "CI_RoleCode.principalInvestigator";
        ResourceBundle rb;

        rb = ResourceBundles.codeLists(Locale.ENGLISH);
        assertEquals("Principal investigator", rb.getString(key));

        rb = ResourceBundles.codeLists(Locale.FRENCH);
        assertEquals("Maître d’œuvre principal", rb.getString(key));
    }

    /**
     * Tests {@link ResourceBundles#descriptions(Locale)}
     */
    @Test
    public void testDescriptions() {
        final String key = "CI_RoleCode.principalInvestigator";
        ResourceBundle rb;

        rb = ResourceBundles.descriptions(Locale.ENGLISH);
        assertEquals("Key party responsible for gathering information and conducting research.", rb.getString(key));

        rb = ResourceBundles.descriptions(Locale.FRENCH);
        assertEquals("Acteur qui a assuré la réalisation de la ressource, " +
                "éventuellement en faisant appel à des co-traitants ou des sous-traitants.", rb.getString(key));
    }
}
