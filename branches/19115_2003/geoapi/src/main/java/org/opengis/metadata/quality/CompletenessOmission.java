/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source: /cvsroot/geoapi/src/org/opengis/metadata/quality/CompletenessOmission.java,v $
 **
 ** Copyright (C) 2004-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.quality;

// Annotations
import static org.opengis.annotation.Specification.ISO_19115;

import org.opengis.annotation.UML;


/**
 * Data absent from the dataset, as described by the scope.
 *
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.0
 */
@UML(identifier="DQ_CompletenessOmission", specification=ISO_19115)
public interface CompletenessOmission extends Completeness {
}
