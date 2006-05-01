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
package org.opengis.filter.expression;

// OpenGIS direct dependencies
import org.opengis.annotation.Extension;
import org.opengis.annotation.XmlElement;
import org.opengis.feature.Feature;


/**
 * Interface for all the OGC Filter elements that compute values.
 * <p>
 * The most common use is with potentially using {@linkplain Feature feature} and
 * metadata.  The ability to access "attributes" based on the provided content is
 * defined based on XPath queries currently.
 * 
 * @version <A HREF="http://www.opengis.org/docs/02-059.pdf">Implementation specification 1.0</A>
 * @author Chris Dillard (SYS Technologies)
 * @since GeoAPI 2.0
 */
@XmlElement("expression")
public interface Expression {
  
    /**
     * Evaluates the given expression based on the content of the given object.
     */
    @Extension
    Object evaluate(Object object);
    
    /**
     * Accepts a visitor. Subclasses must implement with a method whose content* is the following:
     * <pre>return visitor.{@linkplain ExpressionVisitor#visit visit}(this, extraData);</pre>
     */
    @Extension
    Object accept(ExpressionVisitor visitor, Object extraData);
}
