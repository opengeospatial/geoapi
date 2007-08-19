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

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Correctness of non-quantitative attributes.
 *
 * @author <A HREF="http://www.opengeospatial.org/standards/as#01-111">ISO 19115</A>
 * @author Martin Desruisseaux (IRD)
 * @deprecated renamed to NonQuantitativeAttributeAccuracy
 * @since GeoAPI 2.0
 */
@UML(identifier="DQ_NonQuantitativeAttributeCorrectness", specification=ISO_19115)
public interface NonQuantitativeAttributeCorrectness extends NonQuantitativeAttributeAccuracy {
}
