/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.referencing;

// OpenGIS dependencies
import org.opengis.metadata.citation.Citation;


/**
 * Base interface for all factories. Factories can be grouped in two categories:
 * <BR>
 * <UL>
 *   <LI>{@linkplain AuthorityFactory Authority factories} creates objects from
 *       a compact string defined by an authority.</LI>
 *   <LI>{@linkplain ObjectFactory Object factories} allows applications
 *       to make objects that cannot be created by an authority factory.
 *       This factory is very flexible, whereas the authority factory is
 *       easier to use.</LI>
 * </UL>
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 */
public interface Factory {
    /**
     * Returns the vendor responsible for creating this factory implementation. Many implementations
     * may be available for the same factory interface. Implementations are usually managed by a
     * {@linkplain javax.imageio.spi.ServiceRegistry service registry}.
     *
     * @return The vendor for this factory implementation.
     */
    Citation getVendor();
}
