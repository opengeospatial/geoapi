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

// Annotations
import static org.opengis.annotation.Specification.ISO_19115;

import org.opengis.annotation.UML;


/**
 * Identification of capabilities which a service provider makes available to a service user
 * through a set of interfaces that define a behaviour - See ISO 19119 for further information.
 *
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.0
 */
@UML(identifier="SV_ServiceIdentification", specification=ISO_19115)
public interface ServiceIdentification extends Identification {
}
