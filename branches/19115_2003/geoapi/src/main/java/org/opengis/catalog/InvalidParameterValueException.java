package org.opengis.catalog;

/**
 * InvalidParameterValueException class
 * 
 * <p>
 * Los parametros correspondientes al metadato no tiene valores acorde con el
 * dominio de valores definido en la norma ISO 19115
 * </p>
 * 
 * @author Mauricio Pazos, Axios Engineering
 * @version $Id: InvalidParameterValueException.java 848 2006-06-06 04:40:00Z
 *          groldan $
 */
public final class InvalidParameterValueException extends Exception {
    /**
     * 
     */
    private static final long serialVersionUID = -797435347886278091L;

    /**
     * Creates a new InvalidParameterValueException object.
     * 
     * @param msg
     *            tipo y rango de valores esperados según norma ISO 19115
     */
    public InvalidParameterValueException(String msg) {
        super(msg);
    }

    /**
     * Creates a new InvalidParameterValueException object.
     * 
     * @param msg
     *            tipo y rango de valores esperados según norma ISO 19115
     * @param e
     */
    public InvalidParameterValueException(String msg, Throwable e) {
        super(msg, e);
    }
}
