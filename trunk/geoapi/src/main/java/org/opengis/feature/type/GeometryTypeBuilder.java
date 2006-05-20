package org.opengis.feature.type;

import org.opengis.referencing.crs.CoordinateReferenceSystem;

public interface GeometryTypeBuilder<B,T extends GeometryType<B>> 
    extends AttributeTypeBuilder<B,T> {

    /**
     * @return The crs of the geometry type.
     */
    CoordinateReferenceSystem getCRS();
    
    /**
     * Sets the crs for the geometry type.
     */
    void setCRS(CoordinateReferenceSystem crs);
}
