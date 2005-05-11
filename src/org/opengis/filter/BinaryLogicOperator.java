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

// J2SE direct dependencies
import java.util.List;

// Annotations
import org.opengis.annotation.UML;
import org.opengis.annotation.XmlSchema;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Abstract super-interface for logical operators that accept two or more
 * other logical values as inputs.  Currently, the only two subclasses are
 * {@link And} and {@link Or}.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/02-059.pdf">Implementation specification 1.0</A>
 * @since 1.1
 */
@XmlSchema("http://schemas.opengis.net/filter/1.0.0/filter.xsd")
@UML(identifier="BinaryLogicOpType", specification=OGC_02_059)
public interface BinaryLogicOperator extends Filter {
    /**
     * Returns a list containing all of the child filters of this object.  This
     * list will contain at least two elements, and each element will be an
     * instance of {@code Filter}.  Implementations of this interface are
     * encouraged to return either a copy of their internal list or an
     * immutable wrapper around their internal list.  This is because this
     * specification requires {@code Filter} objects to be immutable.
     */
    List<Filter> getChildren();
}
