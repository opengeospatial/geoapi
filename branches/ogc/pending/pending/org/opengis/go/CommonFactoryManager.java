/**************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/

package org.opengis.go;

import com.dautelle.JADE;

import java.util.HashMap;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.opengis.crs.coordops.CoordinateTransformation;
import org.opengis.crs.coordrefsys.CoordinateReferenceSystem;
import org.opengis.crs.coordrefsys.CoordinateReferenceSystemFactory;
import org.opengis.crs.datum.Datum;
import org.opengis.crs.datum.DatumFactory;
import org.opengis.go.geometry.Bounds;
import org.opengis.go.geometry.BoundsFactory;
import org.opengis.spatialschema.SpatialschemaFactory;

/**
 * <code>CommonFactoryManager</code> defines static variables and methods used
 * to access the application's default <code>CommonFactory</code>
 * implementation.
 * <p>
 * The default <code>CommonFactory</code> implementation may be
 * overridden in one of two ways: by setting the system property
 * <code>org.ogc.go1.CommonFactoryClass</code> to the fully qualified name
 * of a class that implements <code>CommonFactory</code> (using the "-D"
 * option when starting the virtual machine or
 * <code>System.setProperty()</code>) or by adding the line
 * "<code>org.ogc.go1.CommonFactoryClass=<i>className</i></code>" to the
 * file <code>org/ogc/go1/common/Common.properties</code>, where
 * <code><i>className</i></code> is the fully qualified name of a class that
 * implements <code>CommonFactory</code>.
 *
 * @author Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 */
public class CommonFactoryManager {
    
    //*************************************************************************
    //  Static Fields
    //*************************************************************************
    
    /**
     * The mapping of CommonFactory class names to CommonFactory instances.
     * Used to store CommonFactoryImpls other than (but also including) the
     * default impl.
     */
    private static HashMap factoryMap;
    
    /**
     * The default CommonFactoryImpl and its subfactories.
     */
    private static CommonFactory commonFactory;
    private static CoordinateReferenceSystemFactory coordinateReferenceSystemFactory;
    private static SpatialschemaFactory spatialschemaFactory;
    private static DatumFactory datumFactory;
    private static BoundsFactory boundsFactory;
    
    // Initialize our static fields...
    static {
    	// Initialize the JADE unit database.
    	JADE.initialize();
    	
        try {
            
            // Get the system property.
            String cfClassName =
                System.getProperty("org.ogc.go1.CommonFactoryClass");
            
            // If the system property was not set, try to read from the file
            // "org/ogc/go1/common/Common.properties".
            if (cfClassName == null) {
                try {
                    ResourceBundle commonResources =
                        ResourceBundle.getBundle("org.ogc.go1.Common");
                    if (commonResources != null) {
                        cfClassName = commonResources.
                            getString("org.ogc.go1.CommonFactoryClass");
                    }
                } catch (MissingResourceException mie) {
                    // If the properties file was not present, then we'll
                    // throw an exception below.
                }
            }
            
            // If we were unable to get the name of a valid CommonFactory,
            // then throw an exception.
            if (cfClassName == null) {
                throw new FactoryManagerInitializationException();
            }
            // Otherwise, try to instantiate a new instance.
            Class cfClass = Class.forName(cfClassName);
            commonFactory = (org.opengis.go.CommonFactory)
                                cfClass.newInstance();
            
            // set up the factoryMap and put the default factory in it
            // factoryMap's size starts out at 1 because in most cases
            // we won't have more than CommonFactory
            factoryMap = new HashMap(1);
            factoryMap.put(cfClassName, commonFactory);
            
            // Retrieve some information used in the convenience methods.
            spatialschemaFactory = commonFactory.getSpatialschemaFactory();
            coordinateReferenceSystemFactory = commonFactory.getCoordinateReferenceSystemFactory();
            datumFactory = commonFactory.getDatumFactory();
            boundsFactory = commonFactory.getBoundsFactory();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Returns the <code>CommonFactory</code> instance with the given
     * className.  If that particular type of <code>CommonFactory</code>
     * has not been created, then a new instance will be instantiated.
     * @param className the class name of the requested 
     *        <code>CommonFactory</code>.
     */
    public static CommonFactory getCommonFactory(String className) 
            throws ClassNotFoundException, IllegalAccessException, 
                   InstantiationException {
        if (factoryMap.containsKey(className)) {
            return (CommonFactory)factoryMap.get(className);   
        }
        CommonFactory factory = 
            (CommonFactory)Class.forName(className).newInstance();
        factoryMap.put(className, factory);
        return factory;
    }
    
    /**
     *
     */
    //public static void releaseCommonFactory(CommonFactory factory) {
    //}

    /**
    * Convenience method that returns the <code>SpatialschemaFactory</code>
    * instance from the default <code>CommonFactory</code>.
    */
   public static SpatialschemaFactory getSpatialschemaFactory() {
       return spatialschemaFactory;
   }

    /**
     * Convenience method that returns the <code>DatumFactory</code> instance
     * from the default <code>CommonFactory</code>.
     */
    public static CoordinateReferenceSystemFactory getCoordinateReferenceSystemFactory() {
        return coordinateReferenceSystemFactory;
    }

    /**
     * Convenience method that returns the <code>DatumFactory</code> instance
     * from the default <code>CommonFactory</code>.
     */
    public static DatumFactory getDatumFactory() {
        return datumFactory;
    }

    /**
     * Convenience method that returns the <code>BoundsFactory</code>
     * instance from the default <code>CommonFactory</code>.
     */
    public static BoundsFactory getBoundsFactory() {
            return boundsFactory;
    }

    /**
     * Returns an object that represents the capabilities of the default
     * <code>Canvas</code> and its factories.
     */
    public static CommonCapabilities getCommonCapabilities() {
        return commonFactory.getCapabilities();
    }
    
    /**
     * A shortcut method for creating a new spatialschema <code>Object</code> 
     * that has a <code>CoordinateReferenceSystsm</code>.
     * @param coordInterface The <code>Class</code> of a spatialschema <code>Object</code>.
     * @param crs the <code>CoordinateReferenceSystem</code> to be used.
     * @return Returns the newly created spatialschema <code>Object</code>.
     */
    public static Object createObjectWithCRS(Class coordInterface, CoordinateReferenceSystem crs) {
        return spatialschemaFactory.createObjectWithCRS(coordInterface, crs);
    }

    /**
     * A shortcut method for creating a new spatialschema <code>Object</code>.
     * @param coordInterface The <code>Class</code> of a spatialschema <code>Object</code>.
     * @return Returns the newly created spatialschema <code>Object</code>.
     */
    public static Object createObject(Class coordinateInterface) {
        return spatialschemaFactory.createObject(coordinateInterface);   
    }
    
    /**
     * A shortcut method for retrieving a <code>CoordinateTransformation</code> object 
     * (from the default <code>CoordinateReferenceSystemFactory</code>).
     * @return a  coordinate transformation object.
     */
    public static CoordinateTransformation getCoordinateTransformation() {
        return getCoordinateReferenceSystemFactory().getCoordinateTransformation();
    }
    
    //**  DatumFactory shortcuts  **

    /**
     * A shortcut method that gets a Datum by name.
     * @param datumName The name of the datum to get.
     * @return Returns the Datum or null if no datum of the given name is
     *   known.
     */
    public static Datum getDatum(String datumName) {
        return datumFactory.getDatum(datumName);
    }

    /**
     * A shortcut method that creates a new <code>Bounds</code> object from
     * the default <code>BoundsFactory</code>.
     * @param boundsInterface The <code>Class</code> of a bounds
     *   interface (such as <code>XYBoundingRectangle.class</code>).
     * @return Returns a newly created instance of the given interface.
     */
    public static Bounds createBounds(Class boundsInterface) {
        return boundsFactory.createBounds(boundsInterface);
    }

}