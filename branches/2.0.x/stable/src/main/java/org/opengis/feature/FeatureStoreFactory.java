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
package org.opengis.feature;

// J2SE direct dependencies
import java.io.IOException;
import java.net.URI;
import java.util.Map;

// OpenGIS direct dependencies
import org.opengis.util.InternationalString;


/**
 * A provider of spatial information.
 * <p>
 * A provider, or service, implementing this API may range from a single
 * shapefile, to a complete Web Feature Server or OracleSpatial instance.
 * <p>
 * This API does need to consider the:
 * <ul>
 *   <li>Identity: currently captured as a {@link URI}, completly defines a server
 *       or provider. Usually via a URI or JDBC URI as required.</li>
 *   <li>Configuration: currently captured as a {@link Map}.</li>
 * </ul>
 *
 * @since GeoAPI 2.0
 */
public interface FeatureStoreFactory {
    /**
     * Ask for a {@code FeatureStore} connecting to the indicated provider or service.
     * The returned feature store may have been previously cached.
     * <p>
     * Additional hints or configuration information may be provided according
     * to the metadata indicated by {@link #getParametersInfo}. This information
     * often includes security information such as username and password.
     * 
     * @param provider Often a URI or JDBC URI locating the service to connect to.
     * @param params Map of hints or configuration information.
     * @return Feature store connected to the indicated provider or service.
     * @throws IOException if the {@code FeatureStore} cannot connect to its source.
     * @throws FeatureStoreException if there are any other problems creating the {@code FeatureStore}.
     */
    FeatureStore createFeatureStore(URI provider, Map<String,Object> params) throws IOException, FeatureStoreException;

    /**
     * Ask for a new feature store connecting to the indicated provider or service.
     * <p>
     * Additional hints or configuration information may be provided according
     * to the metadata indicated by {@link #getParametersInfo}. This information
     * often includes security information such as username and password.
     * 
     * @param provider Often a URI or JDBC URI locating the service to connect to.
     * @param params Map of hints or configuration information.
     * @return Feature store connected to the newly created provider or service.
     * @throws IOException if the {@code FeatureStore} cannot connect to its source.
     * @throws FeatureStoreException if there are any other problems creating the {@code FeatureStore}.
     */
    FeatureStore createNewFeatureStore(URI provider, Map<String,Object> params) throws IOException, FeatureStoreException;

    /**
     * Icon representing this category of {@link FeatureStore}s.
     *
     * @return URI to a icon (GIF or PNG) representing this factory.
     */
    URI getIcon();

    /**
     * Display name used to communicate this type of factory to end users.
     */
    InternationalString getDisplayName();
    
    /** 
     * Description of this type of factory.
     */
    InternationalString getDescription();

    /**
     * Gets parameters needed (beyond the URI) to instantiate a {@link FeatureStore}.
     * 
     * @todo Should be replaced with a {@code Param}[] based on ISO standards (ISO 19119).
     */
    Map<String,Class> getParametersInfo();

    /**
     * Indicates this factory communicate with the indicated provider or service.
     * <p>
     * This method should not fail, if a connection needs to be made
     * to parse a {@code GetCapabilities} file or negotiate WMS versions any
     * IO problems simply indicate the inabiity to process.
     * <p>
     * This method may be considered the same as:
     * <code>{@linkplain #canProcess(URI,Map) canProcess}(provider, hints)</code>
     * where hints was generated by using all the default values specified by the
     * {@link #getParametersInfo} method.
     *
     * @param provider Provider or Server of spatial information. 
     * @return {@code true} if this factory can communicate with the provider.
     */
    boolean canProcess(URI provider);

    /**
     * Indicates this factory communicate with the indicated provider or service.
     * <p>
     * This method differs from {@link #canProcess(URI)} in that additional configuration
     * information may be supplied. 
     *
     * @param provider Provider or Server of spatial information. 
     * @param params additional configuration information.
     * @return {@code true} if this factory can communicate with the provider.
     */
    boolean canProcess(URI provider, Map<String,Object> params);

    /**
     * Allows a factory to ensure all its preconditions are met,
     * such as the presense of required libraries.
     *
     * @return {@code true} if available
     */
    boolean isAvailable();
}
