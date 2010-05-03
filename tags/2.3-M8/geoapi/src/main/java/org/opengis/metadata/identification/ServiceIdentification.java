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
package org.opengis.metadata.identification;

import org.opengis.annotation.UML;
import static org.opengis.annotation.Specification.*;


/**
 * Identification of capabilities which a service provider makes available to a service user
 * through a set of interfaces that define a behaviour - See ISO 19119 for further information.
 *
 * <BLOCKQUOTE><FONT SIZE=-1><B>NOTE:</B>
 * The prefix was {@code MD} in a previous ISO 19115 specification, but
 * has been renamed {@code SV} in corrigendum 2006.</FONT></BLOCKQUOTE>
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/as#01-111">ISO 19115</A>
 * @author  Martin Desruisseaux (IRD)
 * @since   GeoAPI 2.0
 */
@UML(identifier="SV_ServiceIdentification", specification=ISO_19115)
public interface ServiceIdentification extends Identification {
}
