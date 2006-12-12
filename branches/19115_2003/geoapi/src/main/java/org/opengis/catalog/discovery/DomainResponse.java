package org.opengis.catalog.discovery;

import javax.media.jai.util.Range;

/**
 * Response data for getDomain service.
 * <p>
 * Descriptions of domains of one or more requested metadata properties or
 * request parameters.
 * 
 * @see Discovery
 *      </p>
 * @author Mauricio Pazos, Axios Engineering
 * @version $Id$
 */
public interface DomainResponse {

    /**
     * Returns the name of a metadata property or request parameter (Mandatory).
     * 
     * @return String
     */
    String getParameterName();

    /**
     * Returns unordered list of domain values (Optional)
     * 
     * @return Set Unordered set of domain values
     */
    Object[] getListOfValues();

    /**
     * Return the reference to an authoritative list of domain values for the
     * specified parameter (Optional)
     * 
     * @return
     */
    Object getConceptualSchema();

    /**
     * Returns the minimum and maximum values of domain (Optional).
     * 
     * @return Range
     */
    Range getRangeOfValues();
}
