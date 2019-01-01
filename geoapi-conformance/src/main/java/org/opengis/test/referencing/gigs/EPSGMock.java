/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2011-2019 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Permission to use, copy, and modify this software and its documentation, with
 *    or without modification, for any purpose and without fee or royalty is hereby
 *    granted, provided that you include the following on ALL copies of the software
 *    and documentation or portions thereof, including modifications, that you make:
 *
 *    1. The full text of this NOTICE in a location viewable to users of the
 *       redistributed or derivative work.
 *    2. Notice of any changes or modifications to the OGC files, including the
 *       date changes were made.
 *
 *    THIS SOFTWARE AND DOCUMENTATION IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE
 *    NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *    TO, WARRANTIES OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT
 *    THE USE OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
 *    PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
 *
 *    COPYRIGHT HOLDERS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL OR
 *    CONSEQUENTIAL DAMAGES ARISING OUT OF ANY USE OF THE SOFTWARE OR DOCUMENTATION.
 *
 *    The name and trademarks of copyright holders may NOT be used in advertising or
 *    publicity pertaining to the software without specific, written prior permission.
 *    Title to copyright in this software and any associated documentation will at all
 *    times remain with copyright holders.
 */
package org.opengis.test.referencing.gigs;

import org.opengis.referencing.cs.CSFactory;
import org.opengis.referencing.datum.DatumFactory;
import org.opengis.test.Units;
import org.opengis.test.ValidatorContainer;
import org.opengis.test.referencing.PseudoEpsgFactory;


/**
 * Provides data for geodetic objects defined by the EPSG dataset but not present in the GIGS files.
 * This class is used when a test case needs some dependencies, and those dependencies are expected
 * to be defined in the EPSG dataset instead than in a previous test case.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
final class EPSGMock extends PseudoEpsgFactory {
    /**
     * Creates a new EPSG pseudo-factory which will use the given factories for creating coordinate system instances.
     */
    EPSGMock(final Units              units,
             final DatumFactory       datumFactory,
             final CSFactory          csFactory,
             final ValidatorContainer validators)
    {
        super(units, datumFactory, csFactory, null, null, null, validators);
    }

    /**
     * Returns the coordinate system factory specified at construction time.
     */
    CSFactory getCSFactory() {
        return csFactory;
    }
}
