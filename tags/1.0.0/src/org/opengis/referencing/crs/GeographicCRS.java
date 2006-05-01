/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.referencing.crs;

// OpenGIS direct dependencies
import org.opengis.referencing.cs.EllipsoidalCS; 	 
import org.opengis.referencing.datum.GeodeticDatum;


/**
 * A coordinate reference system based on an ellipsoidal approximation of the geoid; this provides
 * an accurate representation of the geometry of geographic features for a large portion of the
 * earth's surface.
 *
 * <TABLE CELLPADDING='6' BORDER='1'>
 * <TR BGCOLOR="#EEEEFF"><TH NOWRAP>Used with CS type(s)</TH></TR>
 * <TR><TD>
 *   {@link org.opengis.referencing.cs.EllipsoidalCS Ellipsoidal}
 * </TD></TR></TABLE>
 *
 * @UML abstract SC_GeographicCRS
 * @author ISO 19111
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/03-073r1.zip">Abstract specification 2.0</A>
 *
 * @revisit OGC document 01-009 defines a <CODE>getWGS84ConversionInfo()</CODE> method.
 *          A <CODE>getWGS84Parameters()</CODE> method was also defined in the datum.
 *          I see no equivalent in this ISO 19111 specification.
 */
public interface GeographicCRS extends CoordinateReferenceSystem {
    /** 	 
     * Returns the coordinate system, which must be ellipsoidal. 	 
     * 	 
     * @return The coordinate system. 	 
     * @UML association usesCS 	 
     */ 	 
/// EllipsoidalCS getCoordinateSystem(); 	 

    /** 	 
     * Returns the datum, which must be geodetic. 	 
     * 	 
     * @return The datum. 	 
     * @UML association usesDatum 	 
     */ 	 
/// GeodeticDatum getDatum();
}
