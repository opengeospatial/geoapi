/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.filter;

// Annotations
import org.opengis.annotation.UML;
import org.opengis.annotation.XmlSchema;
import static org.opengis.annotation.Specification.*;


/**
 * {@linkplain #evaluate Evaluates} to {@code true} if all the combined expressions evaluate to {@code true}.
 * This interface exposes no additional methods beyond those of {@link BinaryLogicOperator}.
 * It only serves as a marker of what type of operator this is.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/02-059.pdf">Implementation specification 1.0</A>
 * @since 1.1
 */
@XmlSchema("http://schemas.opengis.net/filter/1.0.0/filter.xsd")
@UML(identifier="And", specification=OGC_02_059)
public interface And extends BinaryLogicOperator {
}
