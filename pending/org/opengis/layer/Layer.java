/*
 * $ Id $
 * $ Source $
 * Created on Jan 12, 2005
 */
package org.opengis.layer;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.opengis.feature.display.canvas.FeatureLayer;
import org.opengis.go.display.primitive.Graphic;
import org.opengis.layer.source.LayerSourceFactory.Param;
import org.opengis.metadata.Identifier;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.spatialschema.geometry.Envelope;
import org.opengis.util.InternationalString;


/**
 * The <code>Layer</code> interface organizes the basic GO-1 constructs that
 * may be added to <code>FeatureCanvas</code> or <code>Canvas</code>.  A GO-1
 * application may be directed to "add" a <code>Layer</code>; it should then
 * add the <code>Layer</code>'s <code>FeatureLayer</code> and 
 * <code>Graphic</code>s to the respective canvases.  
 * 
 * It is currently assumed that the <code>Layer</code> interface will prove
 * analogous to the WMS concept of Layer, and will soon be an implementation
 * of said 
 * 
 * See ISO/DIS 7.2.4.5 Layers and styles
 * 
 * @author SYS Technologies
 * @author crossley
 * @version $Revision $
 */
public interface Layer {
    
    public static final Param NAME_PARAM = 
        new Param( "Name", String.class, "Unique name for identifying a Layer", false);
    
    public static final Param TITLE_PARAM = 
        new Param("Title", InternationalString.class, "Human-readable title for presenting a Layer", true);
    
    public static final Param ABSTRACT_PARAM = 
        new Param("Abstract", InternationalString.class, "", false);
    
    public static final Param KEYWORD_LIST_PARAM = 
        new Param("KeywordList", InternationalString[].class, "", false);
    
    public static final Param CRSS_PARAM = 
        new Param ("CRSs", CoordinateReferenceSystem[].class, "", false);
    
    public static final Param BOUNDING_BOXES_PARAM =
        new Param("BoundingBoxes", Envelope[].class, "", false);
    
    public static final Param ATTRIBUTION_PARAM =
        new Param("Attribution", Attribution.class, "", false);
    
    public static final Param AUTHORITY_URLS_PARAM =
        new Param("AuthorityURLs", AuthorityURL[].class, "", false);
    
    public static final Param IDENTIFIERS_PARAM =
        new Param("Identifiers", Identifier[].class, "", false);
    
    public static final Param METADATA_URLS_PARAM =
        new Param("MetadataURLs", MetadataURL[].class, "", false);
    
    public static final Param DATA_URLS_PARAM =
        new Param("DataURLs", DataURL[].class, "", false);
    
    public static final Param FEATURE_LIST_URLS_PARAM =
        new Param("FeatureListURLs", FeatureListURL[].class, "", false);
    
    public static final Param STYLES_PARAM =
        new Param("Styles", Style[].class, "", false);
    
    public static final Param MIN_SCALE_DENOMINATOR_PARAM =
        new Param("MinScaleDenominator", Double.class, "", false);
    
    public static final Param MAX_SCALE_DENOMINATOR_PARAM =
        new Param("MaxScaleDenominator", Double.class, "", false);
    
    public static final Param LAYERS_PARAM =
        new Param("Layers", Layer[].class, "", false);
    
    // GO-1 representation objects
    
    public static final Param FEATURE_LAYER_PARAM =
        new Param("FeatureLayer", FeatureLayer.class, "", false);
    
    public static final Param GRAPHICS_PARAM =
        new Param("Graphics", Graphic[].class, "", false);
        
    // attributes
    
    public static final Param QUERYABLE_PARAM =
        new Param("queryable", Boolean.class, "", false);
    
    public static final Param CASCADED_PARAM =
        new Param("cascaded", Integer.class, "", false);
    
    public static final Param OPAQUE_PARAM =
        new Param("opaque", Boolean.class, "", false);
    
    public static final Param NO_SUBSETS_PARAM =
        new Param("noSubsets", Boolean.class, "", false);
    
    public static final Param FIXED_WIDTH_PARAM =
        new Param("fixedWidth", Integer.class, "", false);
    
    public static final Param FIXED_HEIGHT_PARAM =
        new Param("fixedHeight", Integer.class, "", false);
    
    // WMS properties?
    
    /**
     * Provides a unique name for identifying this <code>Layer</code>.
     * See ISO/DIS 19128 7.2.4.6.3 Name.  
     * @return the unique String identifier for this <code>Layer</code>
     */
    String getName();
    
    /**
     * Provides the human-readable String for presenting this <code>Layer</code>.
     * See ISO/DIS 19128 7.2.4.6.2 Title
     * @return the human-readable Title for this <code>Layer</code> 
     */
    InternationalString getTitle();
    
    /** 
     * Provides the narrative description of this <code>Layer</code>.
     * See ISO/DIS 19128 7.2.4.6.4 Abstract and KeywordList
     * @return the narrative description of this <code>Layer</code>
     */
    InternationalString getAbstract();
    
    /**
     * DOCUMENT ME.
     * @return
     */
    InternationalString[] getKeywordList();
    
    /**
     * DOCUMENT ME.
     * @return
     */
    CoordinateReferenceSystem[] getCRSs();
    
    Envelope[] getBoundingBoxes();
    
    Attribution getAttribution();
    
    AuthorityURL[] getAuthorityURLs();
        
    Identifier[] getIdentifiers();
    
    MetadataURL[] getMetadataURLs();
    
    DataURL[] getDataURLs();
    
    FeatureListURL[] getFeatureListURLs();
    
    Style[] getStyles();
    
    double getMinScaleDenominator();
    
    double getMaxScaleDenominator();

    /**
     * Gets the child <code>Layer</code>s of this <code>Layer</code>.  Typically,
     * a <code>Layer</code> will have either child <code>Layer</code>s or 
     * @return the child <code>Layer</code>s or an empty array
     */
    Layer[] getLayers();
    
    boolean isQueryable();
    
    int getCascaded();
    
    boolean isOpaque();
    
    boolean isNoSubsets();
    
    int getFixedWidth();
    
    int getFixedHeight();
    
    //*************************************************************************
    //  'work' methods?
    //*************************************************************************
    
    // parent access
    
    /**
     * Gets the parent <code>LayerSource</code> that produced this <code>Layer</code>.
     * This should assist in serialization.
     * @return the parent <code>LayerSource</code> that produced this <code>Layer</code>
     */
    //LayerSource getLayerSource();

    // 'renderable' access
    
    /**
     * Gets the <code>FeatureLayer</code> from this <code>Layer</code> that is suitable
     * for adding to a <code>FeatureCanvas</code> in order to visually represent this
     * <code>Layer</code>.
     * @return the <code>FeatureLayer</code> to add to a <code>FeatureCanvas</code> or
     *         null
     */
    FeatureLayer getFeatureLayer();
    
    /**
     * Gets the <code>Graphic</code>s from this <code>Layer</code> that are suitable
     * for adding to a <code>Canvas</code> in order to visually represent this 
     * <code>Layer</code>.
     * @return the <code>Graphic</code>s to add to a <code>Canvas</code> or an empty array.
     */
    Graphic[] getGraphics();
    
    
    
    /**
     * Whether this <code>Layer</code>'s renderable components should be initially rendered.
     * @return true if the <code>Layer</code> should be shown initially
     */
    //boolean isInitiallyVisible();
    
    // use sparingly to indicate a Layer is always on?
    // ie, for a background vectormap?
    // boolean isAlwaysVisible();
    
    /**
     * Simple service metadata - should be replaced by ISO19119 interfaces
     * as they are made available.
     * <p>
     * Differences from geotools - no param is required. Sensible
     * defaults should always be available.
     * </p>
     * @author Jody Garnett
     */
    class Param {
        /** True if Param is required. */
        final public boolean required;

        /** Key used in Parameter map. */
        final public String key;

        /** Type of information required. */
        final public Class type;

        /** Short description (less then 40 characters). */
        final public String description;

        /** Default value, please provide one users will be so much happier */
        final public Object defaultValue;

        public Param(final String key) {
            this(key, String.class, null);
        }

        public Param(final String key, final Class type) {
            this(key, type, null);
        }

        public Param(final String key, final Class type, final String description) {
            this(key, type, description, true);
        }

        public Param(
                final String key, 
                final Class type, 
                final String description, 
                final boolean required) {
            this(key, type, description, required, null);
        }

        public Param(
                final String key, 
                final Class type, 
                final String description, 
                final boolean required, 
                final Object defaultValue) {
            this.key = key;
            this.type = type;
            this.description = description;
            this.required = required;
            this.defaultValue = defaultValue;
        }

        /**
         * Utility method for implementors of canProcess.
         * <p>
         * Willing to check all known constraints and parse Strings to requred value
         * if needed.
         * </p>
         * 
         * @param map Map of parameter values
         * @return Value of the correct type from map
         * @throws IOException if parameter was of the wrong type, or a required parameter is not present
         */
        public Object lookUp(final Map map) throws IOException {
            if (!map.containsKey(key)) {
                if (required) {
                    throw new IOException("Parameter " + key + " is required:" + description);
                } else {
                    return defaultValue;
                }
            }
            Object value = map.get(key);
            if (value == null) {
                return null;
            }
            if (value instanceof String && (type != String.class)) {
                value = handle((String) value);
            }
            if (value == null) {
                return null;
            }
            if (!type.isInstance(value)) {
                throw new IOException(type.getName() + " required for parameter " + key + ": not "
                        + value.getClass().getName());
            }
            return value;
        }

        public String text(final Object value) {
            return value.toString();
        }

        public Object handle(final String text) throws IOException {
            if (text == null) {
                return null;
            }
            if (type == String.class) {
                return text;
            }
            if (text.length() == 0) {
                return null;
            }
            try {
                return parse(text);
            } catch (IOException ioException) {
                throw ioException;
            } catch (Throwable throwable) {
                throw new LayerException("Problem creating " + type.getName() + " from '"
                        + text + "'", throwable);
            }
        }

        public Object parse(final String text) throws Throwable {
            Constructor constructor;

            try {
                constructor = type.getConstructor(new Class[] {
                    String.class
                });
            } catch (SecurityException e) {
                //  type( String ) constructor is not public
                throw new IOException("Could not create " + type.getName() + " from text");
            } catch (NoSuchMethodException e) {
                // No type( String ) constructor
                throw new IOException("Could not create " + type.getName() + " from text");
            }

            try {
                return constructor.newInstance(new Object[] {
                    text,
                });
            } catch (IllegalArgumentException illegalArgumentException) {
                throw new LayerException("Could not create " + type.getName() + ": from '"
                        + text + "'", illegalArgumentException);
            } catch (InstantiationException instantiaionException) {
                throw new LayerException("Could not create " + type.getName() + ": from '"
                        + text + "'", instantiaionException);
            } catch (IllegalAccessException illegalAccessException) {
                throw new LayerException("Could not create " + type.getName() + ": from '"
                        + text + "'", illegalAccessException);
            } catch (InvocationTargetException targetException) {
                throw targetException.getCause();
            }
        }
    }
}
