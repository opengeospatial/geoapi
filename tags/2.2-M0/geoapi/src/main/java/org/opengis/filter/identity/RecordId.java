/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.filter.identity;

import org.opengis.annotation.XmlElement;
import org.opengis.feature.Feature;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.util.Record;


/**
 *
 * RecordId refered to by CSW-2 specification.
 * <p>
 * Records are identified with a String, commonly referred to as an "id". We
 * are using SimpleFeature to represent a Record at the moment (since
 * our Record does not have a getID() method).
 * </p>
 *
 * @version <A HREF="http://www.opengis.org/docs/02-059.pdf">Implementation specification 1.0</A>
 * @author Jody Garnett, Refractions Research Inc.
 * @since GeoAPI 2.1
 */
@XmlElement("RecordId")
public interface RecordId extends Identifier<String,SimpleFeature> {

    /**
     * The identifier value, which is a string.
     */
    @XmlElement("id")
    String getID();

    /**
     * Evaluates the identifer value against the given record.
     *
     * @param record The recrod construct to be tested.
     *
     * @return <code>true</code> if a match, otherwise <code>false</code>
     */
    boolean matches( Object record );

}