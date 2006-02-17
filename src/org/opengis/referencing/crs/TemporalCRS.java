/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.referencing.crs;

// OpenGIS direct dependencies
import org.opengis.referencing.cs.TimeCS;
import org.opengis.referencing.datum.TemporalDatum;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A 1D coordinate reference system used for the recording of time.
 *
 * <TABLE CELLPADDING='6' BORDER='1'>
 * <TR BGCOLOR="#EEEEFF"><TH NOWRAP>Used with CS type(s)</TH></TR>
 * <TR><TD>
 *   {@link org.opengis.referencing.cs.TimeCS Time}
 * </TD></TR></TABLE>
 *
 * @version <A HREF="http://portal.opengeospatial.org/files/?artifact_id=6716">Abstract specification 2.0</A>
 * @author ISO/DIS 19111
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 1.0
 */
@UML(identifier="SC_TemporalCRS", specification=ISO_19111)
public interface TemporalCRS extends SingleCRS {
    /** 	 
     * Returns the coordinate system, which must be temporal. 	 
     */ 	 
/// @UML(identifier="usesCS", obligation=MANDATORY, specification=ISO_19111)
/// TimeCS getCoordinateSystem(); 	 

    /** 	 
     * Returns the datum, which must be temporal. 	 
     */ 	 
/// @UML(identifier="usesDatum", obligation=MANDATORY, specification=ISO_19111)
/// TemporalDatum getDatum();
}
