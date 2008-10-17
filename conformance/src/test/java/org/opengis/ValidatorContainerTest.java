/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2004-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis;

import java.util.HashSet;
import java.util.Set;
import org.junit.*;
import static org.junit.Assert.*;


/**
 * Tests {@link ValidatorContainer}.
 *
 * @author Martin Desruisseaux (Geomatys)
 * @since GeoAPI 2.2
 */
public class ValidatorContainerTest {
    /**
     * Ensures that {@link ValidatorContainer#all} contains no duplicated value.
     * Also ensures that the declared list size is the expected one.
     */
    @Test
    public void testAll() {
        final Set<Validator> previous = new HashSet<Validator>();
        final ValidatorContainer container = new ValidatorContainer();
        for (final Validator validator : container.all) {
            assertTrue("Found a duplicated value.", previous.add(validator));
        }
        assertFalse("Found a null value.", previous.remove(null));
        assertEquals("Declared size is wrong.", previous.size(), container.all.size());
    }
}
