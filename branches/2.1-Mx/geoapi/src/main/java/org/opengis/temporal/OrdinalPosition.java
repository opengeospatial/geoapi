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
package org.opengis.temporal;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A data type that shall be used for identifying temporal position within an ordinal
 * temporal reference system.
 *
 * @author Stephane Fellah (Image Matters)
 * @author Alexander Petkov
 */
@UML(identifier="TM_OrdinalPosition", specification=ISO_19108)
public interface OrdinalPosition extends TemporalPosition {
    /**
     * Provides a reference to the ordinal era in which the instant occurs.
     *
     * @todo The method name doesn't match the return type.
     */
    @UML(identifier="ordinalPosition", specification=ISO_19108)
    OrdinalEra getOrdinalPosition();
}