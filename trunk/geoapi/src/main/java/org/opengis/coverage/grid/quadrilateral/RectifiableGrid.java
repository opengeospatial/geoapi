
package org.opengis.coverage.grid.quadrilateral;

//OpenGIS dependencies:
import org.opengis.referencing.operation.Conversion;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.Operation;
import org.opengis.spatialschema.geometry.DirectPosition;

// Annotations
import org.opengis.annotation.UML;
import org.opengis.annotation.Extension;


import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Represents a general coordinate conversion algorithm to be applied to the grid.  
 * In the special case where the coordinate conversion is affine, see RectifiedGrid (section 1.6.8.)  
 * This class defines the required convertCoordinates and inverseConvertCoordinates methods required by the RectifiableGrid interface 
 * and provides access to the MathTransform object associated with the algorithm.  
 * Children of this class need only supply the Conversion object (stored in the inherited “operation” attribute) to produce a functional coordinate conversion object.
 *  
 * @author Alexander Petkov
 */
@Extension
public interface RectifiableGrid  extends GridPositioning{
    /**
     * This association shall link the {@linkplain RectifiableGrid} class with the coordinate conversion object 
     * which defines the coordinate operation to be performed.  
     * This conversion object shall be identical to the inherited “operation” attribute.
     */
    @Extension
	Conversion getConversion();

    /**
     * This attribute shall contain only the {@linkplain Conversion} subtype of the {@linkplain Operation} interface, 
     * signifying that RectifiableGrid and children represent only coordinate conversions as defined by ISO 19111.  
     * This attribute shall be identical to the conversion attribute.
     */
    @Extension
    Operation getOperation();
    
    /**
     * This association shall link the {@linkplain RectifiableGrid} class with the coordinate conversion object 
     * which defines the inverse coordinate operation to be performed.  
     * This conversion object shall be identical to the inherited “inverseOperation” attribute.
     */
    @Extension
    Conversion getInverseConversion();
    
    /**
     * This inherited attribute shall contain only the {@linkplain Conversion} subtype of the {@linkplain Operation} interface, 
     * signifying that {@linkplain RectifiableGrid} and children represent only coordinate conversions as defined by ISO 19111.   
     * This attribute shall be identical to the conversion attribute.
     */
    @Extension
	Conversion getInverseOperation();
    
    /**
     * Converts grid coordinates through an affine transform to a direct position.  
     * This is an adapter method for the {@linkplain MathTransform#transform()} method.  
     * The {@linkplain MathTransform} object used in the conversion is associated with the “conversion” and “operation” attributes.
     */
    @Extension
	DirectPosition convertCoordinates();
    
    /**
     * Converts through an affine transform a direct position to the grid coordinates of the nearest grid point.  
     * This is an adapter method for the {@linkplain MathTransform#transform()} method.  
     * The {@linkplain MathTransform} object used in the conversion is associated with the “inverseConversion” and “inverseOperation” attributes.
     */
    @Extension
    GridCoordinates inverseConvertCoordinates();
    
    /**
     * This optional attribute is specified on the {@linkplain GridGeometry} from the legacy OGC 01-004 specification.  
     * It is retained here because it allows the user access to a conversion object which yields non-integer results.  
     * This property is derived from the {@linkplain MathTransform} object associated with the operation and conversion attributes, and is merely a convenience method.
     */
    @Extension
    MathTransform getGridToCoordinateSystem();
    
}
