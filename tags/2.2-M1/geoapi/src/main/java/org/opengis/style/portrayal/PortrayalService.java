/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2008 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.style.portrayal;

import org.opengis.annotation.UML;

import static org.opengis.annotation.Specification.*;

/**
 * A portrayal service is used to portray a feature instance or instances.
 * The portrayal service applies operations using the parameters defined
 * in the portrayal specification.
 *
 * Portrayal shall not be limited to visual rendering, but may include
 * audio,tactile and other media.
 *
 * @version <A HREF="http://www.isotc211.org">ISO 19117 Portrayal</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.2
 */
@UML(identifier="PF_PortrayalService", specification=ISO_19117)
public interface PortrayalService {

}
