/**************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2004 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.crs.crs;

//OpenGIS direct dependencies
import org.opengis.util.CodeList;


/**
 * The type of the classification of principal CRS types. This specifies the 
 * allowable types of {@link CoordinateReferencSystem}s.
 *
 * @UML codelist SC_CoordinateReferenceSystemType
 * @author ISO 19111
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 */
public final class CoordinateReferenceSystemType extends CodeList {
	/**
	 * Serial number for compatibility with different versions.
	 */
	//private static final long serialVersionUID = ;

	/**
	 * A coordinate reference system based on an ellipsoidal approximation of the geoid.
	 * Provides an accurate representation of the geometry of geographic features for a
	 * large portion of the earth's surface.
	 *
	 * @UML conditional geographic
	 */
	public static final CoordinateReferenceSystemType GEOGRAPHIC = new CoordinateReferenceSystemType("GEOGRAPHIC", 0);

	/**
	 * A coordinate reference system used for recording of heights or depths. Vertical
	 * CRSs make use of the direction of gravity to define the concept of height or depth,
	 * but the relationship with gravity may not be straightforward
	 *
	 * @UML conditional vertical
	 */
	public static final CoordinateReferenceSystemType VERTICAL = new CoordinateReferenceSystemType("VERTICAL", 1);

	/**
	 * A contextually local coordinate reference system. It can be divided into two broad
	 * categories:
	 * <ul>
	 *   <li>earth-fixed systems applied to engineering activities on or near the
	 *       surface of the earth;</li>
	 *   <li>CRSs on moving platforms such as road vehicles, vessels, aircraft, or spacecraft.</li>
	 * </ul>
	 *
	 * @UML conditional engineering
	 */
	public static final CoordinateReferenceSystemType ENGINEERING = new CoordinateReferenceSystemType("ENGINEERING", 2);

	/**
	 * An engineering coordinate reference system applied to locations in images.
	 *
	 * @UML conditional image
	 */
	public static final CoordinateReferenceSystemType IMAGE = new CoordinateReferenceSystemType("IMAGE", 3);

	/**
	 * A coordinate reference system used for the recording of time.
	 *
	 * @UML conditional temporal
	 */
	public static final CoordinateReferenceSystemType TEMPORAL = new CoordinateReferenceSystemType("TEMPORAL", 4);
	
	/**
	 * A coordinate reference system used for the recording of time.
	 *
	 * @UML conditional temporal
	 */
	public static final CoordinateReferenceSystemType GEOCENTRIC = new CoordinateReferenceSystemType("GEOCENTRIC", 5);
	
	/**
	 * A coordinate reference system used for the recording of time.
	 *
	 * @UML conditional temporal
	 */
	public static final CoordinateReferenceSystemType PROJECTED = new CoordinateReferenceSystemType("PROJECTED", 6);
	
	/**
	 * A coordinate reference system used for the recording of time.
	 *
	 * @UML conditional temporal
	 */
	public static final CoordinateReferenceSystemType COMPOUND = new CoordinateReferenceSystemType("COMPOUND", 7);
	
	/**
	 * A coordinate reference system used for the recording of time.
	 *
	 * @UML conditional temporal
	 */
	public static final CoordinateReferenceSystemType DERIVED = new CoordinateReferenceSystemType("DERIVED", 8);
	
	/**
	 * List of all enumeration of this type.
	 */
	private static final CoordinateReferenceSystemType[] VALUES = new CoordinateReferenceSystemType[] {
				GEOGRAPHIC, VERTICAL, ENGINEERING, IMAGE, TEMPORAL, GEOCENTRIC, PROJECTED, COMPOUND, DERIVED };

	/**
	 * Constructs an enum with the given name.
	 */
	private CoordinateReferenceSystemType(final String name, final int ordinal) {
		super(name, ordinal);
	}

	/**
	 * Returns the list of <code>CoordinateReferenceSystemType</code>s.
	 */
	public static CoordinateReferenceSystemType[] values() {
		return (CoordinateReferenceSystemType[]) VALUES.clone();
	}

	/**
	 * Returns the list of enumerations of the same kind than this enum.
	 */
	public /*{CoordinateReferenceSystemType}*/ CodeList[] family() {
		return values();
	}
}

