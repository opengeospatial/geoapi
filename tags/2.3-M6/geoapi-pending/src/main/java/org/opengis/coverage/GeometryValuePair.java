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
package org.opengis.coverage;

import java.util.Collection; // For javadoc
import org.opengis.util.Record;
import org.opengis.geometry.DirectPosition;  // For javadoc
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Describes an element of a set that defines the relationships of a
 * {@linkplain DiscreteCoverage discrete coverage}. Each member of this class consists of two parts:
 * a {@linkplain DomainObject domain object} from the domain of the coverage to which it belongs and
 * a record of feature attribute values from the range of the coverage to which it belongs.
 * <var>geometry</var>-<var>value</var> pairs may be generated in the execution of an
 * {@link Coverage#evaluate(DirectPosition,Collection) evaluate} operation, and need not be
 * persistent. {@code GeometryValuePair} is subclassed to restrict the pairing of a feature
 * attribute value record to a specific subtype of domain object.
 *
 * @version ISO 19123:2004
 * @author  Stephane Fellah
 * @author  Martin Desruisseaux (IRD)
 * @since   GeoAPI 2.1
 *
 * @see Coverage#evaluate(DirectPosition,Collection)
 * @see Coverage#select
 * @see Coverage#find(DirectPosition,int)
 * @see Coverage#list
 */
@UML(identifier="CV_GeometryValuePair", specification=ISO_19123)
public interface GeometryValuePair {
    /**
     * The domain object that is a member of this <var>geometry</var>-<var>value</var> pair.
     *
     * @return The geometry member of the pair.
     */
    @UML(identifier="geometry", obligation=MANDATORY, specification=ISO_19123)
    DomainObject<?> getGeometry();

    /**
     * Holds the record of feature attribute values associated with the domain object.
     *
     * @return The value member of the pair.
     */
    @UML(identifier="value", obligation=MANDATORY, specification=ISO_19123)
    Record getValue();
}
