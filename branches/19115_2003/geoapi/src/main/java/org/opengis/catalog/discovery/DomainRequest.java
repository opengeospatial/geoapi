package org.opengis.catalog.discovery;

/**
 * DomainRequest interfase
 * <p>
 * Request data for getDomain service.
 * 
 * @see Discovery
 *      </p>
 * @author Mauricio Pazos, Axios Engineering
 * @version $Id$
 */
public interface DomainRequest {

    /**
     * Returns the name of a metadata property or request parameter
     * 
     * @return String
     */
    String getParameterName();

}
