/**************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.go.display;

import org.opengis.go.display.style.ArrowStyle;
import org.opengis.go.display.style.FillPattern;
import org.opengis.go.display.style.FillStyle;
import org.opengis.go.display.style.GraphicStyle;
import org.opengis.go.display.style.LineCap;
import org.opengis.go.display.style.LineJoin;
import org.opengis.go.display.style.LinePattern;
import org.opengis.go.display.style.LineStyle;
import org.opengis.go.display.style.Mark;
import org.opengis.go.display.style.SymbologyInfo;
import org.opengis.go.display.style.XAnchor;
import org.opengis.go.display.style.YAnchor;

/**
 * Objects implementing the interface <code>DisplayCapabilities</code> provide
 * runtime information about the capabilities of a given implementation.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version $Revision$, $Date$
 */
public interface DisplayCapabilities {
    
    /**
     * Returns an array of <code>Class</code> objects for the
     * non-editable primitive interfaces that are supported by a given
     * implementation.
     */
    public Class[] getSupportedPrimitives();
    
    /**
     * Returns true if a given implementation supports the given
     * <code>EventManager</code> type.
     */
    public boolean isEventManagerSupported(Class eventManagerClass);
    
    /**
     * Returns true if a given implementation supports drawing
     * primitives in "backlit" mode, using the "isBacklighting" attribute
     * of the <code>GraphicStyle</code> interface.
     */
    public boolean isBacklightingSupported();
    
    /**
     * Returns true if a given implementation will honor the
     * "lineWidth" attribute of the <code>GraphicStyle</code> interface.
     */
    public boolean isLineWidthSupported();
    
    /**
     * Returns true if a given implementation will honor the
     * "lineGap" attribute of the <code>GraphicStyle</code> interface.
     */
    public boolean isLineGapSupported();
    
    /**
     * Returns an array of <code>ArrowStyle</code> objects that
     * indicates the arrow styles that are supported by a given
     * implementation.
     */
    public ArrowStyle[] getSupportedArrowStyles();
    
    /**
     * Returns an array of <code>LineStyle</code> objects that
     * indicates the line styles that are supported by a given
     * implementation.
     */
    public LineStyle[] getSupportedLineStyles();
    
    /**
     * Returns an array of <code>LineJoin</code> objects that indicates the
     * line join styles that are supported by a given implementation.
     */
    public LineJoin[] getSupportedLineJoins();
    
    /**
     * Returns an array of <code>LineCap</code> objects that indicates the
     * line cap styles that are supported by a given implementation.
     */
    public LineCap[] getSupportedLineCaps();
    
    /**
     * Returns true if a given implementation will honor the
     * "dashPattern" attribute of the <code>GraphicStyle</code> interface.
     */
    public boolean isDashPatternSupported();
    
    /**
     * Returns true if a given implementation supports the "blink"
     * attribute of the <code>GraphicStyle</code> interface.
     */
    public boolean isBlinkSupported();
    
    /**
     * Returns an array of <code>FillStyle</code> objects that indicates the
     * fill styles supported by a given implementation.
     */
    public FillStyle[] getSupportedFillStyles();
    
    /**
     * Returns an array of <code>FillPattern</code> objects that
     * indicates the fill patterns that are supported by a given
     * implementation.
     */
    public FillPattern[] getSupportedFillPatterns();
    
    /**
     * Returns an array of <code>Mark</code> objects that
     * indicates the marks that are supported by a given
     * implementation.
     */
    public Mark[] getSupportedMarks();
    
    /**
     * Returns an array of <code>LinePattern</code> objects that
     * indicates the line patterns that are supported by a given
     * implementation.
     */
    public LinePattern[] getSupportedLinePatterns();
    
    /**
     * Returns an array of <code>XAnchor</code> objects that
     * indicates the X anchors that are supported by a given
     * implementation.
     */
    public XAnchor[] getSupportedXAnchors();
    
    /**
     * Returns an array of <code>YAnchor</code> objects that
     * indicates the Y anchors that are supported by a given
     * implementation.
     */
    public YAnchor[] getSupportedYAnchors();
    
    /**
     * Returns true if a given implementation support drawing
     * primitives filled with a gradient as specified by in the
     * <code>GraphicStyle</code> interface.
     */
    public boolean isGradientSupported();
    
    /**
     * Returns an <code>GraphicStyle</code> object, configured to the
     * defaults for the <code>DisplayFactory</code> for this
     * <code>Capabilities</code> instance.
     */
    public GraphicStyle getDefaultGraphicStyle();
    
    /**
     * Returns an array of <code>SymbologyInfo</code> objects
     * that this implementations supports.
     */
    public SymbologyInfo[] getSupportedSymbologies();
     
}

