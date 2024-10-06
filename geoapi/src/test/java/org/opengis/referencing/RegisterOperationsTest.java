/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2024 Open Geospatial Consortium, Inc.
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
package org.opengis.referencing;

import java.util.Set;
import org.opengis.util.FactoryException;
import org.opengis.util.UnimplementedServiceException;
import org.opengis.referencing.crs.GeographicCRS;
import org.opengis.referencing.crs.CRSAuthorityFactory;
import org.opengis.referencing.cs.EllipsoidalCS;
import org.opengis.referencing.cs.CSAuthorityFactory;
import org.opengis.referencing.datum.GeodeticDatum;
import org.opengis.referencing.operation.Transformation;
import org.opengis.referencing.operation.CoordinateOperationAuthorityFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Tests {@link RegisterOperations}.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public final class RegisterOperationsTest {
    /**
     * The instance to test.
     */
    private final RegisterOperations mock;

    /**
     * Creates a new test case.
     */
    public RegisterOperationsTest() {
        mock = new RegisterOperationsMock();
    }

    /**
     * Tests {@link RegisterOperations#getVendor()} and {@link RegisterOperations#getAuthority()}.
     *
     * @throws FactoryException should never happen.
     */
    @Test
    public void testGetVendorAndAuthority() throws FactoryException  {
        assertEquals(AuthorityFactoryMock.VENDOR, mock.getVendor().getTitle().toString());
        assertNull(mock.getAuthority());
    }

    /**
     * Tests {@link RegisterOperations#getFactory(Class)}.
     */
    @Test
    public void testGetFactory() {
        assertInstanceOf(AuthorityFactoryMock.class, mock.getFactory(CRSAuthorityFactory.class).orElseThrow());
        assertInstanceOf(AuthorityFactoryMock.class, mock.getFactory(CoordinateOperationAuthorityFactory.class).orElseThrow());
        assertTrue(mock.getFactory(CSAuthorityFactory.class).isEmpty());
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> mock.getFactory(AuthorityFactory.class));
        assertEquals("Illegal factory type: org.opengis.referencing.AuthorityFactory", exception.getMessage());
    }

    /**
     * Tests {@link RegisterOperations#getAuthorityCodes(Class)}.
     *
     * @throws FactoryException should never happen.
     */
    @Test
    public void testGetAuthorityCodes() throws FactoryException {
        assertEquals(Set.of("dummy CRS"),       mock.getAuthorityCodes(GeographicCRS.class));
        assertEquals(Set.of("dummy operation"), mock.getAuthorityCodes(Transformation.class));
        assertEquals(Set.of(), mock.getAuthorityCodes(GeodeticDatum.class));
        assertEquals(Set.of(), mock.getAuthorityCodes(EllipsoidalCS.class));
    }

    /**
     * Tests {@link RegisterOperations#getDescriptionText(Class, String)} with an ambiguous type.
     * The implementation should throw an exception.
     *
     * @throws FactoryException should never happen.
     */
    @Test
    public void testAmbiguousType() throws FactoryException {
        assertTrue(mock.getDescriptionText(GeographicCRS.class, AuthorityFactoryMock.OBJECT_CODE).isEmpty());
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> mock.getDescriptionText(IdentifiedObjectMock.class, AuthorityFactoryMock.OBJECT_CODE));
        assertEquals("Ambiguous object type: org.opengis.referencing.IdentifiedObjectMock", exception.getMessage());
    }

    /**
     * Tests {@link RegisterOperations#findCoordinateReferenceSystem(String)}.
     *
     * @throws FactoryException should never happen.
     */
    @Test
    public void testFind() throws FactoryException {
        assertEquals("Foo", mock.findCoordinateReferenceSystem("Foo").getIdentifiers().iterator().next().getCode());
        final var exception = assertThrows(UnimplementedServiceException.class, () -> mock.findCoordinateOperation("Foo"));
        assertEquals("The “Vendor mock” implementation does not support the creation of CoordinateOperation instances.", exception.getMessage());
    }
}
