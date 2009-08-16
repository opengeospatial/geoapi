/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2004-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.quality;

import org.opengis.annotation.UML;
import static org.opengis.annotation.Specification.*;


/**
 * Adherence to rules of the conceptual schema.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/as#01-111">ISO 19115</A>
 * @author  Martin Desruisseaux (IRD)
 * @since   GeoAPI 2.0
 */
@UML(identifier="DQ_ConceptualConsistency", specification=ISO_19115)
public interface ConceptualConsistency extends LogicalConsistency {
}