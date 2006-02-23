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

// OpenGIS direct dependencies
import org.opengis.referencing.operation.Conversion;


/**
 * A coordinate reference system that is defined by its coordinate
 * {@linkplain Conversion conversion} from another coordinate reference system
 * (not by a {@linkplain org.opengis.referencing.datum.Datum datum}).
 *
 * @UML abstract SC_GeneralDerivedCRS
 * @author ISO 19111
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/03-073r1.zip">Abstract specification 2.0</A>
 */
public interface GeneralDerivedCRS extends CoordinateReferenceSystem {
    /**
     * Returns the base coordinate reference system.
     *
     * @return The base coordinate reference system.
     * @UML association baseCRS
     */
    CoordinateReferenceSystem getBaseCRS();

    /**
     * Returns the conversion from the {@linkplain #getBaseCRS base CRS} to this CRS.
     *
     * @return The conversion to this CRS.
     * @UML association definedByConversion
     *
     * @rename <code>definedByConversion</code> may be a precise description of the association,
     *         but is confusing as a method name. OGC document 01-009 used <code>toBase()</code>
     *         method name.   By analogy with 01-009, this new interface specifies a method name
     *         which contains the <code>FromBase</code> words.
     */
    Conversion getConversionFromBase();
}
