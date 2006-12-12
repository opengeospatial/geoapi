package org.opengis.catalog.discovery;

import org.opengis.filter.Filter;

/**
 * <b>QueryExpression interfase</b>
 * <p>
 * DOCUMENT ME!
 * </p>
 * 
 * @author Axios Engineering S.L.
 * @version $Id$
 */
public interface QueryExpression extends Cloneable {
    /**
     * DOCUMENT ME!
     * 
     * @param obj
     *            DOCUMENT ME!
     * 
     * @return DOCUMENT ME!
     */
    public boolean equals(Object obj);

    /**
     * DOCUMENT ME!
     * 
     * @return DOCUMENT ME!
     */
    public Filter getPredicate();

    /**
     * DOCUMENT ME!
     * 
     * @return DOCUMENT ME!
     */
    public Object clone();
}
