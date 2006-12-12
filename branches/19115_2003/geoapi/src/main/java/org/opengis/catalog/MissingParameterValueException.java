package org.opengis.catalog;

/**
 * MissingParameterValueException class
 * 
 * <p>
 * Esta excepción se produce si se ha omitido alguno de los valores obligatorios
 * (Mandatory) segun norma ISO 19115
 * </p>
 * 
 * @author Mauricio Pazos, Axios Engineering
 * @version $Id: MissingParameterValueException.java 848 2006-06-06 04:40:00Z
 *          groldan $
 */
public final class MissingParameterValueException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = -1032431481539832748L;

    /**
     * Creates a new MissingParameterValueException object.
     * 
     * @param msg
     *            DOCUMENT ME!
     */
    public MissingParameterValueException(String msg) {
        super(msg);

        // TODO Auto-generated constructor stub
    }

    /**
     * Creates a new MissingParameterValueException object.
     * 
     * @param msg
     *            DOCUMENT ME!
     * @param e
     *            DOCUMENT ME!
     */
    public MissingParameterValueException(String msg, Throwable e) {
        super(msg, e);

        // TODO Auto-generated constructor stub
    }
}
