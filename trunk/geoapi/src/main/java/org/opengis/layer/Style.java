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
package org.opengis.layer;

// J2SE direct dependencies
import java.util.List;

// OpenGIS direct dependencies
import org.opengis.sld.FeatureStyle;
import org.opengis.util.InternationalString;
import org.opengis.go.display.primitive.Graphic;
import org.opengis.go.display.style.GraphicStyle;

// Annotations
import org.opengis.annotation.Extension;
import org.opengis.annotation.XmlElement;


/**
 * Encapsulates the information necessary to style the renderable data for a given {@link Layer}.
 *
 * @version <A HREF="http://portal.opengeospatial.org/files/?artifact_id=5316">Implementation specification 1.3</A>
 * @author Jesse Crossley (SYS Technologies)
 * @since GeoAPI 2.0
 */
@XmlElement("Style")
public interface Style {
    /**
     * Provides a unique name for identifying this {@code Style}.
     *
     * @return this {@code Style}'s name.
     */
    @XmlElement("Name")
    String getName();

    /**
     * Provides the human-readable string for presenting this {@code Style}.
     *
     * @return this {@code Style}'s title.
     */
    @XmlElement("Title")
    InternationalString getTitle();

    /**
     * Provides the narrative description of this {@code Style}.
     *
     * @return this {@code Style}'s narrative description.
     */
    @XmlElement("Abstract")
    InternationalString getAbstract();

    /**
     * Provides the linkage to an image of a map legend appropriate to this {@code Style}.
     *
     * @return the linkage to this {@code Style}'s map legend image.
     */
    @XmlElement("LegendURL")
    List<LegendURL> getLegendURLs();

    /**
     * Provides the linkage to symbology information for this {@code Style}.
     *
     * @return the linkage to this {@code Style}'s symbology information.
     */
    @XmlElement("StyleSheetURL")
    StyleSheetURL getStyleSheetURL();

    /**
     * Provides the linkage to more information about the data or symbology
     * underlying this {@code Style}.
     *
     * @return the linkage to more info about this {@code Style}'s data or symbology.
     */
    @XmlElement("StyleURL")
    StyleURL getStyleURL();

    /**
     * Gets the {@link FeatureStyle}s used to style the {@link FeatureLayer}s used
     * by a {@link Layer} using this {@code Style}.  The returned {@code List}
     * (if modifiable) should NOT be live, and modifying it should not affect this
     * {@code Style}'s set of {@code FeatureStyle}s.
     *
     * @return this {@code Style}'s {@code FeatureStyle}s.
     */
    @Extension
    List<FeatureStyle> getFeatureStyles();

    /**
     * Gets the {@link GraphicStyle}s used to style the {@link Graphic}s used by a
     * {@link Layer} using this {@code Style}.  The returned {@code List} (if modifiable)
     * should NOT be live, and modifying it should not affect this {@code Style}'s set of
     * {@code GraphicStyle}s.
     *
     * @return this {@code Style}'s {@code GraphicStyle}s.
     */
    @Extension
    List<GraphicStyle> getGraphicStyles();
}
