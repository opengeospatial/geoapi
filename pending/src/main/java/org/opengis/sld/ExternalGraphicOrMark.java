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
package org.opengis.sld;

// Annotations
import org.opengis.annotation.Extension;


/**
 * Common subclass for the two types of markers that can appear as children of a
 * {@link Graphic} object, namely {@link Mark} and {@link ExternalGraphic}.
 *
 * @version <A HREF="http://portal.opengeospatial.org/files/?artifact_id=1188">Implementation specification 1.0</A>
 * @author Chris Dillard (SYS Technologies)
 * @since GeoAPI 2.0
 */
public interface ExternalGraphicOrMark {
    /**
     * Accepts a visitor.  Implementations of all subinterfaces must have with a
     * method whose content is the following:
     * <pre>return visitor.{@linkplain StyleVisitor#visit visit}(this, extraData);</pre>
     */
    @Extension
    Object accept(StyleVisitor visitor, Object extraData);
}
