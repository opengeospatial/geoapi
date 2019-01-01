/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2006-2019 Open Geospatial Consortium, Inc.
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
package org.opengis.filter.identity;

import org.opengis.annotation.XmlElement;


/**
 * ObjectId refered to by Filter 1.1 specification (as an example).
 * <p>
 * Although ObjectId is referred to as an example we are making explicit use of it
 * here in order to show identification being defined with a long (as with several
 * popular object relational mappers).
 * </p>
 *
 * @version <A HREF="http://portal.opengeospatial.org/files/?artifact_id=5929">Implementation specification 1.1</A>
 * @author Jody Garnett, Refractions Research Inc.
 * @since GeoAPI 2.1
 */
@XmlElement("RecordId")
public interface ObjectId extends Identifier {

    /**
     * The identifier value, which is a Long.
     */
    @XmlElement("id")
    Long getID();

    /**
     * Evaluates the identifier value against the given Object.
     *
     * @param obj Object to be tested.
     *
     * @return <code>true</code> if a match, otherwise <code>false</code>
     */
    boolean matches( Object obj );

}
