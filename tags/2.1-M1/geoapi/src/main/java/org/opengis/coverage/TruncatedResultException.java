/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.coverage;

// J2SE dependencies
import java.util.List;


/**
 * Thrown as a warning to users that the last element of result list of a find operation is
 * the same distance as other objects not selected.
 *
 * @author Alexander Petkov
 *
 * @todo Can we provide this warning in an other way than throwing an exception?
 */
public class TruncatedResultException extends Exception {
    /**
     * Serial number for interoperability with different versions.
     */
    private static final long serialVersionUID = 3491256594470735222L;

    /**
     * Represents result list of a find operation.
     */
    private List<? extends GeometryValuePair> resultList;

    /**
     * Creates an exception with no message.
     */
    public TruncatedResultException() {
        super();
    }

    /**
     * Creates an exception with the specified message.
     *
     * @param  message The detail message. The detail message is saved for 
     *         later retrieval by the {@link #getMessage()} method.
     */
    public TruncatedResultException(String message) {
        super(message);
    }

    /**
     * Returns the result list of a find operation.
     */
    public List<? extends GeometryValuePair> getResultList() {
        return resultList;
    }

    /**
     * Sets the result list.
     */
    public void setResultList(final List<? extends GeometryValuePair> resultList) {
        this.resultList = resultList;
    }
}
