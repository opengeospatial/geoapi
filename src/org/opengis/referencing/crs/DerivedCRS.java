/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.referencing.crs;

// Annotations
///import org.opengis.annotation.UML;
///import static org.opengis.annotation.Obligation.*;


/**
 * A coordinate reference system that is defined by its coordinate conversion from another
 * coordinate reference system but is not a projected coordinate reference system. This
 * category includes coordinate reference systems derived from a projected coordinate
 * reference system.
 *
 * @author ISO 19111
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/03-073r1.zip">Abstract specification 2.0</A>
 */
///@UML (identifier="SC_DerivedCRS")
public interface DerivedCRS extends GeneralDerivedCRS {
}
