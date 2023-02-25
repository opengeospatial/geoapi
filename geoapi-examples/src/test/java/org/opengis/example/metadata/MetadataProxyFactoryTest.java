/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 */
package org.opengis.example.metadata;

import java.util.Map;
import java.util.HashMap;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import org.junit.Test;

import org.opengis.metadata.Metadata;
import org.opengis.metadata.citation.Party;
import org.opengis.metadata.citation.Responsibility;

import static org.junit.Assert.*;


/**
 * Tests {@link MetadataProxyFactory}.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public class MetadataProxyFactoryTest {
    /**
     * Tests the creation of a {@link Metadata} containing a {@link Responsibility}.
     */
    @Test
    public void testResponsibleParty() {
        final MetadataProxyFactory factory = new MetadataProxyFactory();
        Map<String,Object> attributes = new HashMap<>();
        assertNull(attributes.put("name", new SimpleCitation("Aristotle")));

        final Party party = factory.create(Party.class, attributes);
        assertEquals("Aristotle", party.getName().toString());

        attributes = new HashMap<>();
        assertNull(attributes.put("party", Collections.singleton(party)));
        final Responsibility responsibility = factory.create(Responsibility.class, attributes);
        assertEquals("Aristotle", getSingleton(responsibility.getParties()).getName().toString());

        attributes = new HashMap<>();
        assertNull(attributes.put("contact", Collections.singleton(responsibility)));
        final Metadata md = factory.create(Metadata.class, attributes);
        assertEquals("Aristotle", getSingleton(getSingleton(md.getContacts()).getParties()).getName().toString());

        assertTrue("Null value should have been replaced by empty collection.",
                md.getSpatialRepresentationInfo().isEmpty());

        assertEquals("MD_Metadata{contact=[CI_Responsibility{party=[CI_Party{name=Aristotle}]}]}", md.toString());
    }

    /**
     * Verifies that the given collection contains exactly one element, then returns that element.
     */
    private static <T> T getSingleton(final Collection<? extends T> collection) {
        final Iterator<? extends T> it = collection.iterator();
        assertTrue("hasNext", it.hasNext());
        final T element = it.next();
        assertFalse("hasNext", it.hasNext());
        return element;
    }
}
