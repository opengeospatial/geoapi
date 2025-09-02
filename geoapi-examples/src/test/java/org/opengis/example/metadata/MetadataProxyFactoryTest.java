/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 */
package org.opengis.example.metadata;

import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.Collection;
import java.util.Iterator;
import org.opengis.metadata.Metadata;
import org.opengis.metadata.citation.Party;
import org.opengis.metadata.citation.ResponsibleParty;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Tests {@link MetadataProxyFactory}.
 */
public class MetadataProxyFactoryTest {
    /**
     * Creates a new test case.
     */
    public MetadataProxyFactoryTest() {
    }

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
        assertNull(attributes.put("party", Set.of(party)));
        final ResponsibleParty responsibility = factory.create(ResponsibleParty.class, attributes);
        assertEquals("Aristotle", getSingleton(responsibility.getParties()).getName().toString());

        attributes = new HashMap<>();
        assertNull(attributes.put("contact", Set.of(responsibility)));
        final Metadata md = factory.create(Metadata.class, attributes);
        assertEquals("Aristotle", getSingleton(getSingleton(md.getContacts()).getParties()).getName().toString());

        assertTrue(md.getSpatialRepresentationInfo().isEmpty(),
                "Null value should have been replaced by empty collection.");

        assertEquals("MD_Metadata{contact=[CI_ResponsibleParty{party=[CI_Party{name=Aristotle}]}]}", md.toString());
    }

    /**
     * Verifies that the given collection contains exactly one element, then returns that element.
     *
     * @param  <T>  type of elements in the collection.
     * @param  collection  the collection to verify.
     * @return the singleton element in the given collection.
     */
    private static <T> T getSingleton(final Collection<? extends T> collection) {
        final Iterator<? extends T> it = collection.iterator();
        assertTrue(it.hasNext(), "hasNext");
        final T element = it.next();
        assertFalse(it.hasNext(), "hasNext");
        return element;
    }
}
