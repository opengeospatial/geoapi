/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.filter;

// Annotations
import org.opengis.annotation.XmlElement;


/**
 * {@linkplain #evaluate Evaluates} to {@code true} if all the combined expressions evaluate to {@code true}.
 * This interface exposes no additional methods beyond those of {@link BinaryLogicOperator}.
 * It only serves as a marker of what type of operator this is.
 *
 * @version <A HREF="http://www.opengis.org/docs/02-059.pdf">Implementation specification 1.0</A>
 * @author Chris Dillard (SYS Technologies)
 * @since GeoAPI 2.0
 */
@XmlElement("And")
public interface And extends BinaryLogicOperator {
}
