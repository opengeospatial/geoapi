package org.opengis.feature.type;


/**
 * Represents a scoped type name.
 * <p>
 * This interface is method compatiable with QName, to facility those
 * transitioning form XML based models. As GenericName is finalized this
 * interface will be asked to fall into line with GenericnName.
 * </p>
 * <p>
 * If you need to store the information for "getPrefix" please make use
 * of per attribute client properties.
 * </p>
 * @author Jody Garnett, Refractions Research
 */
public interface TypeName extends Name {

}