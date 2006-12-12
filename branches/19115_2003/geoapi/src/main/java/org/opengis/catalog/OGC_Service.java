package org.opengis.catalog;

import org.opengis.webservice.capability.Capabilities;

/**
 * <b>OGC_Service interface</b>
 * <p>
 * DOCUMENT ME!
 * </p>
 * 
 * @author Mauricio Pazos, Axios Engineering
 * @version $Id$
 */
public interface OGC_Service {

    /**
     * Returns the capabilities of the service.
     */
    Capabilities getCapabilities() throws CatalogServiceFailureException;

}
