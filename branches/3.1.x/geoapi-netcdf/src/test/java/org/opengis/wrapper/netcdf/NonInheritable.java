/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 *
 *    The NetCDF wrappers are provided as code examples, in the hope to facilitate
 *    GeoAPI implementations backed by other libraries. Implementors can take this
 *    source code and use it for any purpose, commercial or non-commercial, copyrighted
 *    or open-source, with no legal obligation to acknowledge the borrowing/copying
 *    in any way.
 */
package org.opengis.wrapper.netcdf;

import java.util.Map;
import org.opengis.metadata.citation.Role;
import org.opengis.metadata.citation.DateType;
import static org.junit.Assert.*;


/**
 * Tests that are specific to the GeoAPI implementation of NetCDF wrappers. We do not allow subclasses
 * to inherit those tests, because different implementation may processed in a different way.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
final strictfp class NonInheritable {
    /**
     * Do not allow instantiation of this class.
     */
    private NonInheritable() {
    }

    /**
     * Asserts that {@link NetcdfMetadataTest#compareProperties(Map, Map, double)} processed all
     * relevant entries. The tests performed by this method are specific to GeoAPI. If the user
     * overrides the {@link NetcdfMetadataTest} class, then we will not execute those tests
     * because the user may have modified the expected or the actual map, or may be testing
     * an other implementation which will read a different set of metadata.
     *
     * @param actual        The properties actually found in the metadata object being tested.
     * @param title         The citation title to remove (implementation specific), or {@code null} if none.
     * @param hasNoExtent   {@code true} if the NetCDF file contains no geographic extent.
     */
    static void assertProcessedAllRelevant(final Map<String,Object> actual, final String title,
            final boolean hasNoExtent)
    {
        // Following values were not tested because they are hard-coded,
        // consequently they are implementation-dependent.
        assertNotNull(actual.remove("metadataStandardName"));
        assertNotNull(actual.remove("metadataStandardVersion"));
        // Following values were not tested because the actual value should be Role.POINT_OF_CONTACT.
        // However the NetCDF wrapper provided in this demo module is too simple for this distinction.
        assertSame(Role.ORIGINATOR, actual.remove("contact.role"));
        assertSame(Role.ORIGINATOR, actual.remove("identificationInfo.pointOfContact.role"));
        // Following are hard-coded values in our simple implementation. We did not tested
        // them because a more sophisticated implementation would not have provided values
        // for those properties when they are not applicable.
        if (title != null) {
            assertEquals(title, actual.remove("identificationInfo.citation.title"));
        }
        if (hasNoExtent) {
            assertSame(Boolean.TRUE,    actual.remove("identificationInfo.extent.geographicElement.extentTypeCode"));
            assertSame(Role.ORIGINATOR, actual.remove("identificationInfo.citation.citedResponsibleParty.role")); // Opportunist
        }
        assertTrue(actual.toString(), actual.isEmpty());
    }
}
