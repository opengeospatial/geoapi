package org.opengis.feature;

import java.util.Collection;

import org.opengis.feature.type.FeatureType;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.spatialschema.geometry.BoundingBox;

/**
 * A Feature, of abitrary complexity, with at a minimum Geometry and CRS information.
 * <p>
 * We have allowed for additional "tempoary" user data to be associated
 * with Attributes in order to facilitiate procesing services. These services
 * traditionally end up hold "shadow" structures such as a HashMap referenced
 * by FeatureID.
 * <ul>
 * <li>putClientProperty(String key, Object value );
 * <li>getClientProperty(String key);
 * </ul>
 * </p>
 * @author Jody Garnett, Refractions Research
 */
public interface Feature<E extends Property,C extends Collection<E>, T extends FeatureType<E,C>> 
	extends ComplexAttribute<E,C,T> {
	
	/**
	 * Allows the association of process specific information.
	 * 
	 * @param key Object used to allow String and Enum keys
	 * @param value Associated with key
	 */
	public void putUserData( Object key, Object value );
	
	/**
	 * Retrieve associated process specific information.
	 * 
	 * @param key Object used to allow String and Enum keys
	 */	
	public Object getUserData( Object key );
	
	/**
	 * Feature ID, should be unique, inmutable identification for physical
	 * Feature being modeled.
	 * <p>
	 * Care must be taken to avoid generation based things that will change over
	 * time (mutable attribtues, or shapefile rows). It is hoped that the domain
	 * being modeled will have specific identity practices in palce that you can
	 * depend on, as example Airports make use of standards code such as <b>YVR</b>
	 * for the Victoria International Airport.
	 * </p>
	 * <p>
	 * As a consequence of this different Application Descriptors representing
	 * the same physical content should end up with the same idenification. This
	 * is a "dream", on the off chance is obtained you may wish to prepend the
	 * typeName to this ID when producing GML. This would only be a problem when
	 * creating one GML document based on two application schemas for Airport,
	 * both of which succeeded in using the actual physical identification
	 * strings for their ID.
	 * </p>
	 * 
	 * @return Feature ID generated in an opaque fashion, may not be null
	 */
	String getID();

	/**
	 * The CRS of this feature (if known).
	 * <p>
	 * This information is available as an optional attribute in a GML document,
	 * the idea is sound: if avalable the CRS information for a feature should
	 * be available here.
	 * </p>
	 * 
	 * @return CoordinateReferenceSystem if set or null.
	 */
	CoordinateReferenceSystem getCRS();
    
    /**
     * Sets the CRS of the feature. 
     * 
     * @param crs The coordinate reference system of the feature.
     */
    void setCRS(CoordinateReferenceSystem crs);

	/**
	 * The bounds of this Feature, if available.
	 * <p>
	 * This should be a "shadow" of information available as part of the
	 * attribute model, for instance of BoundedFeatureType should show bounds as a
	 * required attribute and you would be able to expect a non-null value from
	 * this method.
	 * </p>
	 * <p>
	 * It is assumed the returned Envelope has a CRS, if not you can assume the
	 * value of getCRS() applies?
	 * </p>
	 * 
	 * @return Bounds if available or null
	 */
	BoundingBox getBounds();

	/**
	 * Access the type of this Feature.
	 */
///	FeatureType getType();

	/**
	 * @return The default geometry attribute.
	 */
	GeometryAttribute getDefaultGeometry();

	/**
	 * Sets the default geometry attribute.
	 * 
	 * @param geom A geometry attribute.
	 
	 */
	void setDefaultGeometry(GeometryAttribute geometryAttribute);
}