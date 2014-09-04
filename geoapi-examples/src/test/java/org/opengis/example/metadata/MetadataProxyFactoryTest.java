/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 */
package org.opengis.example.metadata;

import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
import org.junit.Test;

import org.opengis.metadata.Metadata;
import org.opengis.metadata.citation.ResponsibleParty;

import static org.junit.Assert.*;


/**
 * Tests {@link MetadataProxyFactory}.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public strictfp class MetadataProxyFactoryTest {
    /**
     * Tests the creation of a {@link Metadata} containing a {@link ResponsibleParty}.
     */
    @Test
    public void testResponsibleParty() {
        final MetadataProxyFactory factory = new MetadataProxyFactory();
        Map<String,Object> attributes = new HashMap<String,Object>();
        assertNull(attributes.put("individualName", "Aristotle"));

        final ResponsibleParty party = factory.create(ResponsibleParty.class, attributes);
        assertEquals("Aristotle", party.getIndividualName());

        attributes = new HashMap<String,Object>();
        assertNull(attributes.put("contact", Collections.singleton(party)));

        final Metadata md = factory.create(Metadata.class, attributes);
        assertEquals("Aristotle", md.getContacts().iterator().next().getIndividualName());
        assertTrue("Null value should have been replaced by empty collection.",
                md.getSpatialRepresentationInfo().isEmpty());

        assertEquals("MD_Metadata{contact=[CI_ResponsibleParty{individualName=Aristotle}]}", md.toString());
    }
}
