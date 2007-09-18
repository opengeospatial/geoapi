package org.opengis.feature.type;

/**
 * Describes an instance of a geometry attribute.
 * <p>
 * This interface adds no additional methods, the point of it is convenience 
 * to type narrow {@link #getType()} to {@link GeometryType}.
 * </p>
 * @author Justin Deoliveira, The Open Planning Project
 *
 */
public interface GeometryDescriptor extends AttributeDescriptor {

    /**
     * Override of {@link AttributeDescriptor#getType()} which type narrows
     * to {@link GeometryType}.
     */
    GeometryType getType();
}
