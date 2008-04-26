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

import java.util.Collection;
import java.util.Map;  // For javadoc
import java.util.Set;
import java.util.List;

import org.opengis.go.display.canvas.Canvas; // For javadoc
import org.opengis.go.display.primitive.Graphic;
import org.opengis.metadata.Identifier;
import org.opengis.metadata.extent.GeographicBoundingBox;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.geometry.Envelope;
import org.opengis.util.InternationalString;
import org.opengis.annotation.XmlElement;
import org.opengis.go.display.canvas.FeatureCanvas;
import org.opengis.go.display.canvas.FeatureLayer;


/**
 * Organizes the basic GO-1 constructs that may be added to {@link FeatureCanvas} or {@link Canvas}.
 * A GO-1 application may be directed to "add" a {@code Layer}; it should then add the {@code Layer}'s
 * {@link FeatureLayer} and {@link Graphic}s to the respective canvases.
 * <p>
 * <h3>Mutability</h3>
 * In current version, layers are <cite>unmodifiable</cite>, i.e. they can't be modified through
 * the API provided in this interface, but they are not immutable because they may (or may not, at
 * implementor choice) reflect a change in the underlying database. This means that all methods
 * returning a {@linkplain java.util.Collection collection} must returns either an
 * {@linkplain java.util.Collections#unmodifiableList unmodifiable} one, or a copy.
 * Modifying returned collections should have no affect on this {@code Layer}.
 * <p>
 * Note that the mutability aspect of {@code Layer} may be revisited in future versions,
 * so users are encouraged to not rely strongly on current behavior.
 *
 * @version <A HREF="http://portal.opengeospatial.org/files/?artifact_id=5316">Implementation specification 1.3</A>
 * @author Jesse Crossley (SYS Technologies)
 * @since GeoAPI 2.0
 */
@XmlElement("Layer") // 7.2.4.5 Layers and styles
public interface Layer {
    /**
     * Provides a unique name for identifying this {@code Layer}. If, and only if, a layer has a
     * name, then it is a map layer that can be requested by using that name in the {@code LAYERS}
     * parameter of a {@code GetMap} request. A layer that contains a {@code Name} element is
     * referred to as a <cite>named layer</cite>. If the layer has a {@linkplain #getTitle title}
     * but no name, then that layer is only a category title for all the layers nested within. A
     * Map Server that advertises a layer containing a name element shall be able to accept that
     * name as the value of {@code LAYERS} argument in a {@code GetMap} request and return the
     * corresponding map. A client shall not attempt to request a layer that has a title but no
     * name.
     * <p>
     * The name is not inherited by {@linkplain #getLayers child layers}.
     *
     * @return the unique string identifier for this {@code Layer}.
     */
    @XmlElement("Name") // 7.2.4.6.3
    String getName();

    /**
     * Provides the human-readable string for presenting this {@code Layer}.
     * Equivalent to {@link org.opengis.metadata.citation.Citation#getTitle}
     * in {@linkplain org.opengis.annotation.Specification#ISO_19115 ISO 19115}.
     *
     * @return the human-readable title for this {@code Layer}.
     */
    @XmlElement("Title") // 7.2.4.6.2
    InternationalString getTitle();

    /**
     * Provides the narrative description of this {@code Layer}.
     * Equivalent to {@link org.opengis.metadata.identification.DataIdentification#getAbstract}
     * in {@linkplain org.opengis.annotation.Specification#ISO_19115 ISO 19115}.
     * The abstract is not inherited by {@linkplain #getLayers child layers}.
     *
     * @return the narrative description of this {@code Layer}.
     */
    @XmlElement("Abstract") // 7.2.4.6.4
    InternationalString getAbstract();

    /**
     * Provides keywords to aid in catalogue searches.
     * Equivalent to {@link org.opengis.metadata.identification.TopicCategory}
     * in {@linkplain org.opengis.annotation.Specification#ISO_19115 ISO 19115} if the {@code vocabulary}
     * attribute of {@code Keyword} element is "ISO19115:2003". Other vocabularies are permitted.
     * The keywords are not inherited by {@linkplain #getLayers child layers}.
     * <p>
     * The returned {@code List} (if modifiable) should not be live, and
     * modifying it should not affect this {@code Layer}'s set of keywords.
     *
     * @return this {@code Layer}'s keyword list.
     */
    @XmlElement("KeywordList") // 7.2.4.6.4
    List<InternationalString> getKeywordList();

    /**
     * Provides the coordinate reference systems available to this {@code Layer},
     * which includes CRSs inherited from parent {@code Layer}s. In order to indicate
     * what layer CRSs are available, every named layer shall have at least one CRS.
     * The root layer shall include a sequence of zero or more CRS elements listing all
     * CRSs that are common to all subsidiary layers. A child layer may optionally add
     * to the list inherited from a parent layer.
     * <p>
     * If CRS and {@linkplain #getBoundingBoxes bounding boxes} are backed by a
     * <code>{@linkplain Map}&lt;{@linkplain CoordinateReferenceSystem},{@linkplain Envelope}&gt</code>
     * (from the J2SE collection framework), then the CRSs are the {@linkplain Map#keySet ket set}
     * of the above-cited map.
     * <p>
     * This {@code Set} (if modifiable) should not be live, and
     * modifying it should not affect this {@code Layer}'s set of CRSs.
     *
     * @return this {@code Layer}'s coordinate reference systems.
     */
    @XmlElement("CRS") // 7.2.4.6.7
    Set<CoordinateReferenceSystem> getCRSs();

    /**
     * Provides the bounding boxes that specify the coordinate ranges for
     * this {@code Layer}, as {@code Envelope}s, including bounding boxes
     * inherited from parent {@code Layer}s.
     * Equivalent to {@link org.opengis.metadata.extent.BoundingPolygon}
     * in {@linkplain org.opengis.annotation.Specification#ISO_19115 ISO 19115},
     * except that it is strictly a box here.
     * <p>
     * A layer may have multiple bounding box elements, but each one shall state a different CRS.
     * A bounding box inherited from the parent layer for a particular CRS is replaced by any
     * declaration for the same CRS in the child layer. A bounding box in the child for a new
     * CRS not already declared by the parent is added to the list of bounding boxes for the child
     * layer. A single layer element shall not contain more than one bounding box for the same CRS.
     * <p>
     * <strong>NOTE:</strong> There is no provision for describing disjoint bounding boxes.
     * For example, consider a dataset which covers two areas separated by some distance.
     * The server cannot provide two separate bounding boxes in the same layer using the same
     * CRS to separately describe those areas. To handle this type of situation, the server may
     * either define a single larger bounding box which encloses both areas, or may define two
     * separate layers that each have distinct name and bounding box values.
     * <p>
     * A layer shall not provide a bounding box for a CRS it does not support. Conversely, a
     * layer may support CRSs for which it does not provide a bounding box: a server that has
     * the ability to transform data to different CRSs may choose not to provide an explicit
     * bounding box for every possible CRS available for each layer. The server should provide
     * bounding box information for at least the native CRS of the layer (that is, the CRS
     * in which the layer is stored in the server's database).
     * <p>
     * If {@linkplain #getCRSs CRS} and bounding boxes are backed by a
     * <code>{@linkplain Map}&lt;{@linkplain CoordinateReferenceSystem},{@linkplain Envelope}&gt</code>
     * (from the J2SE collection framework), then the bounding boxes are the
     * {@linkplain Map#values values} of the above-cited map.
     * <p>
     * This {@code Collection} (if modifiable) should not be live, and modifying
     * it should have no affect on this {@code Layer}'s set of bounding boxes.
     *
     * @return this {@code Layer}'s bounding box envelopes.
     */
    @XmlElement("BoundingBox")  // 7.2.4.6.8
    Collection<Envelope> getBoundingBoxes();

    /**
     * Provides the geographic bounding box that specify the longitude and
     * latitude ranges for this {@code Layer}.
     * Every named layer shall have exactly one geographic bounding box that is either stated
     * explicitly or inherited from a parent layer. Geographic bounding box states, via the
     * {@linkplain GeographicBoundingBox#getWestBoundLongitude west bound longitude},
     * {@linkplain GeographicBoundingBox#getEastBoundLongitude east bound longitude},
     * {@linkplain GeographicBoundingBox#getSouthBoundLatitude south bound latitude}, and
     * {@linkplain GeographicBoundingBox#getNorthBoundLatitude north bound latitude},
     * the minimum bounding rectangle in decimal degrees of the area covered by the layer.
     * Geographic bounding box shall be supplied regardless of what CRS the map server may support,
     * but it may be approximate if the data are not natively in geographic coordinates. The purpose
     * of beographic bounding box is to facilitate geographic searches without requiring coordinate
     * transformations by the search engine.
     * <p>
     * This method is conceptually similar to a {@linkplain #getBoundingBoxes bounding box} in
     * which the CRS is implicitly CRS:84. However, Geographic bounding box shall not be used
     * as a substitute for {@code <BoundingBox CRS="CRS:84">}. If the server wishes to provide
     * bounding box information in the CRS:84 CRS, then a separate bounding box element explicitly
     * naming CRS:84 shall be included in the service metadata.
     */
    @XmlElement("GeographicBoundingBox")  // 7.2.4.6.6
    GeographicBoundingBox getGeographicBoundingBox();

    /**
     * Provides the attribution for this {@code Layer}, which identifies the source of
     * the geographic information used in this layer. This is partially equivalent to
     * {@link org.opengis.metadata.citation.ResponsibleParty} in
     * {@linkplain org.opengis.annotation.Specification#ISO_19115 ISO 19115}.
     * The attribution is inherited by child layers. Any redefinition by
     * a child replaces the inherited value.
     *
     * @return this {@code Layer}'s attribution.
     */
    @XmlElement("Attribution") // 7.2.4.6.12
    Attribution getAttribution();

    /**
     * Provides the authority URLs named in this {@code Layer}'s {@linkplain Identifier identifiers}.
     * Equivalent to {@link org.opengis.metadata.citation.Contact#getOnLineResource}
     * in {@linkplain org.opengis.annotation.Specification#ISO_19115 ISO 19115}.
     * <p>
     * The returned {@code List} (if modifiable) should not be live, and
     * modifying it should have no affect on this {@code Layer}'s set of authority URLs.
     *
     * @return this {@code Layer}'s authority URLs.
     */
    @XmlElement("AuthorityURL") // 7.2.4.6.13
    List<AuthorityURL> getAuthorityURLs();

    /**
     * Provides the identifiers containing ID numbers or labels defined by a particular authority.
     * Equivalent to {@link org.opengis.metadata.Identifier#getCode}
     * in {@linkplain org.opengis.annotation.Specification#ISO_19115 ISO 19115}.
     * <p>
     * The returned {@code List} (if modifiable) should not be live, and
     * modifying it should have no affect on this {@code Layer}'s set of identifiers.
     *
     * @return this {@code Layer}'s identifiers.
     */
    @XmlElement("Identifier") // 7.2.4.6.13
    List<Identifier> getIdentifiers();

    /**
     * Provides the Metadata URLs that offer detailed, standardized metadata about
     * the data for this {@code Layer}.
     * <p>
     * The returned {@code List} (if modifiable) should not be live, and modifying
     * it should have no affect on this {@code Layer}'s set of metadata URLs.
     *
     * @return this {@code Layer}'s metadata URLs.
     */
    @XmlElement("MetadataURL") // 7.2.4.6.11
    List<MetadataURL> getMetadataURLs();

    /**
     * Provides the data URLs that offer links to the underlying data represented by this {@code Layer}.
     * Data URLs are not inherited by child layers.
     * <p>
     * The returned {@code List} (if modifiable) should not be live, and modifying
     * it should have no affect on this {@code Layer}'s set of data URLs.
     *
     * @return this {@code Layer}'s data URLs.
     */
    @XmlElement("DataURL") // 7.2.4.6.15
    List<DataURL> getDataURLs();

    /**
     * Provides the feature URLs that point to a list of features represented in this {@code Layer}.
     * Feature URLs are not inherited by child layers.
     * <p>
     * The returned {@code List} (if modifiable) should not be live, and modifying
     * it should have no affect on this {@code Layer}'s feature list URLs.
     *
     * @return this {@code Layer}'s feature list URLs.
     */
    @XmlElement("FeatureListURL") // 7.2.4.6.14
    List<FeatureListURL> getFeatureListURLs();

    /**
     * Provides the styles that may be requested for this {@code Layer}.
     * Zero or more styles may be advertised for a layer or collection of layers, each of
     * which shall have {@linkplain Style#getName name} and {@linkplain Style#getTitle title}.
     * The style's name is used in the Map request {@code STYLES} parameter. The title is a
     * human-readable string. If only a single style is available, that style is known as the
     * "default" style and need not be advertised by the server.
     * <p>
     * Style declarations are inherited by {@linkplain #getLayers child layers}.
     * A child shall not redefine a style with the same {@linkplain Style#getName name}
     * as one inherited from a parent. A child may define a new style with a new name
     * that is not available for the parent layer.
     * <p>
     * The returned {@code List} (if modifiable) should not be live, and
     * modifying it should have no affect on this {@code Layer}'s styles.
     *
     * @return this {@code Layer}'s styles.
     */
    @XmlElement("Style") // 7.2.4.6.5
    List<Style> getStyles();

    /**
     * Provides the lower bound for the range of scales for which it is appropriate
     * to generate a map for this {@code Layer}. See "Scale denominators" section in
     * {@linkplain org.opengis.layer package description}. A value of 0 indicates this
     * {@code Layer} has no minimum scale denominator. Negative values are not allowed.
     *
     * @return the minimum scale denominator, inclusive.
     */
    @XmlElement("MinScaleDenominator") // 7.2.4.6.9
    double getMinScaleDenominator();

    /**
     * Provides the upper bound for the range of scales for which it is appropriate
     * to generate a map for this {@code Layer}.  See "Scale denominators" section in
     * {@linkplain org.opengis.layer package description}. A value of {@link Double#POSITIVE_INFINITY}
     * indicates this {@code Layer} has no maximum scale denominator.
     *
     * @return the maximum scale denominator, exclusive.
     */
    @XmlElement("MaxScaleDenominator") // 7.2.4.6.9
    double getMaxScaleDenominator();

    /**
     * Gets the child layers of this {@code Layer}.  Typically, a {@code Layer} will have either
     * child {@code Layer}s or some combination of {@link FeatureLayer}s and/or {@link Graphic}s.
     * <p>
     * The returned {@code List} (if modifiable) should not be a live list.
     * Modifying the list should have no affect on this {@code Layer}'s child layers.
     *
     * @return the child layers.
     */
    List<Layer> getLayers();

    /**
     * Indicates whether the {@code GetFeatureInfo} operation is supported on this {@code Layer}.
     * A server may support {@code GetFeatureInfo} on some of its layers but not on all. A server
     * shall issue a service exception ("LayerNotQueryable") if {@code GetFeatureInfo} is requested
     * on a layer that is not queryable.
     *
     * @return {@code true} if this {@code Layer} supports {@code GetFeatureInfo}.
     */
    @XmlElement("queryable") // 7.2.4.7.2
    boolean isQueryable();

    /**
     * Indicates how many times this {@code Layer} has been cascaded.
     * A layer is said to have been "cascaded" if it was obtained from an originating server and then
     * included in the service metadata of a different server. The second server may simply offer an
     * additional access point for the layer, or may add value by offering additional output formats
     * or reprojection to other coordinate reference systems.
     * <p>
     * If a WMS cascades the content of another WMS then it shall increment by 1 the value of the cascaded
     * attribute for the affected layers. If that attribute is missing from the originating server's service
     * metadata, then the cascading WMS shall insert the attribute and set it to 1.
     *
     * @return how many times this {@code Layer} has been cascaded.
     */
    @XmlElement("cascaded") // 7.2.4.7.3
    int getCascaded();

    /**
     * Indicates whether this {@code Layer}'s renderable data should be considered opaque, and
     * therefore requested at the bottom of a stack of maps. If {@code false}, then maps made
     * from that layer will generally have significant no-data areas that a client may display
     * as transparent. Vector features such as points and lines are considered not to be opaque
     * in this context (even though at some scales and symbol sizes a collection of features might
     * fill the map area). A {@code true} value for opaque indicates that the layer represents an
     * area-filling coverage. For example, a map that represents topography and bathymetry as regions
     * of differing colours will have no transparent areas. The opaque declaration should be taken as
     * a hint to the client to place such a layer at the bottom of a stack of maps.
     * <p>
     * This attribute describes only the layer's data content, not the picture format of the map response.
     * Whether or not a layer is listed as opaque, a server shall still comply the {@code GetMap}
     * {@code TRANSPARENT} parameter: that is, the server shall send an image with a transparent
     * background if and only if the client requests {@code TRANSPARENT=TRUE} and a picture
     * {@code FORMAT} that supports transparency.
     *
     * @return {@code true} if this {@code Layer}'s data is mostly opaque.
     */
    @XmlElement("opaque") // 7.2.4.7.4
    boolean isOpaque();

    /**
     * Indicates that this {@code Layer} is not able to produce a map of a geographic
     * area other than this {@linkplain #getBoundingBoxes layer's declared bounding box}.
     * For example, a WMS that houses a collection of digitized images of historical maps,
     * or pre-computed browse images of satellite data, may not be able to subset or
     * resize those images. However, it can still respond to {@code GetMap} requests
     * for complete maps in the original size.
     *
     * @return {@code true} if this {@code Layer} is not able to display subsets.
     */
    @XmlElement("noSubsets") // 7.2.4.7.5
    boolean isNoSubsets();

    /**
     * Indicates that this {@code Layer} is not able to produce a map
     * with a width different from the fixed width indicated. A value
     * of 0 indicates this {@code Layer} has no fixed width.
     *
     * @return the fixed width of this {@code Layer}, or 0 if the width is not fixed.
     */
    @XmlElement("fixedWidth") // 7.2.4.7.5
    int getFixedWidth();

    /**
     * Indicates that this {@code Layer} is not able to produce a map
     * with a height different from the fixed height indicated. A value
     * of 0 indicates this {@code Layer} has no fixed height.
     *
     * @return the fixed height of this {@code Layer}, or 0 if the height is not fixed.
     */
    @XmlElement("fixedHeight") // 7.2.4.7.5
    int getFixedHeight();

    //*************************************************************************
    //  'work' methods?
    //*************************************************************************

    // parent access

    /**
     * Gets the parent {@code LayerSource} that produced this {@code Layer}.
     * This should assist in serialization.
     *
     * @return the parent {@code LayerSource} that produced this {@code Layer}
     */
    //LayerSource getLayerSource();

    // 'renderable' access

    /**
     * Gets the feature layers from this {@code Layer} that are suitable for adding
     * to a {@link FeatureCanvas} in order to visually represent this {@code Layer}.
     * <p>
     * The returned {@code List} (if modifiable) should not be a live list of this {@code Layer}'s
     * {@code FeatureLayer}s, and modifying the list should not affect this {@code Layer}'s set of
     * {@code FeatureLayer}s.
     *
     * @return the feature layers to add to a {@code FeatureCanvas}.
     */
    List<FeatureLayer> getFeatureLayers();

    /**
     * Gets the graphics from this {@code Layer} that are suitable for adding
     * to a {@link Canvas} in order to visually represent this {@code Layer}.
     * <p>
     * The returned {@code List} (if modifiable) should not be a live list of this {@code Layer}'s
     * {@code Graphic}s, and modifying the list should not affect this {@code Layer}'s set of
     * {@code Graphic}s.
     *
     * @return the {@code Graphic}s to add to a {@code Canvas}.
     */
    List<Graphic> getGraphics();

    /**
     * Whether this {@code Layer}'s renderable components should be initially rendered.
     * @return true if the {@code Layer} should be shown initially
     */
    //boolean isInitiallyVisible();

    // use sparingly to indicate a Layer is always on?
    // ie, for a background vectormap?
    // boolean isAlwaysVisible();
}
