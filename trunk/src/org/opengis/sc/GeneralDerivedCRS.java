/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.sc;


/**
 * A coordinate reference system that is defined by its coordinate conversion from another
 * coordinate reference system (not by a datum).
 *
 * @UML abstract SC_GeneralDerivedCRS
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 */
public interface GeneralDerivedCRS extends CoordinateReferenceSystem {
    /**
     * Returns the base coordinate reference system.
     *
     * @return The base coordinate reference system.
     * @UML association baseCRS
     */
    public CoordinateReferenceSystem getBaseCRS();

    /**
     *
     * @UML association definedByConversion
     *
     * @revisit Uncomment
     */
//    public org.opengis.crs.coordops.Conversion getDefinedByConversion();
}
