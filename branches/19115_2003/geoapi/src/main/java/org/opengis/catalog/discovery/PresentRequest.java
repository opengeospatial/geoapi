package org.opengis.catalog.discovery;

import java.util.List;
import java.util.Set;

import org.opengis.catalog.InvalidParameterValueException;
import org.opengis.filter.sort.SortBy;

/**
 * <b>PresentRequest interfase</b>
 * <p>
 * Used as argument for preset operation in Discovery service
 * </p>
 * 
 * @see Discovery.present(...)
 * 
 * @author Mauricio Pazos, Axios Engineering
 * @version $Id$
 */
public interface PresentRequest extends Cloneable {

    /**
     * Indicates at which cursor position, the lower possible one being
     * <code>1</code>, to start fetching metadata records.
     * 
     * @return
     */
    int getCursorPosition();

    /**
     * How many records to retrieve from the set of identifiers stated in
     * {@link #getTargetID()}, and in the order stated by
     * {@link #getSortSpec()}.
     */
    int getIteratorSize();

    Set getTargetID();

    /**
     * Indicates a prescribed set of metadata properties to retrieve.
     * <p>
     * The exact set of attributes to be returned is implementation dependent
     * and shall follow de general rules stated in {@link PresentationSetType}
     * </p>
     * 
     * @return an identifier for a prescribed subset of metadata properties to
     *         retrieve.
     */
    PresentationSetType getResponsePresentType();

    /**
     * Indicates the set of element names to retrieve. The presence of this
     * attribute is mutually exclusive with the precense of
     * {@link #getResponsePresentType()}.
     * 
     * @return which metadata properties to retrieve, or <code>null</code> if
     *         not set.
     * 
     * @see #getResponsePresentType()
     */
    Set<String> getResponseElementsList();

    List<SortBy> getSortSpec();

    Object clone();

    /**
     * 
     * @param retrievedData
     *            the set of metadata identifiers to retrieve. The order is
     *            denoted by {@link #getSortSpec()}.
     * @throws InvalidParameterValueException
     */
    void setTargetID(Set retrievedData) throws InvalidParameterValueException;
    // REFACTORY no puede ser inmutable ya que en cada iteraci�n este conjunto
    // cambia y
    // ser�a engorroso su manejo (crear un nuevo objeto en cada iteraci�n)

}
