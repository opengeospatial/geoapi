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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CRSAuthorityFactory;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

/**
 * <code>CommonFactoryManager</code> defines static variables and methods used
 * to access the application's default <code>CommonFactory</code>
 * implementation.
 * <p>
 * The default <code>CommonFactory</code> implementation may be overridden in
 * one of two ways: by setting the system property
 * <code>org.ogc.go1.CommonFactoryClass</code> to the fully qualified name of
 * a class that implements <code>CommonFactory</code> (using the "-D" option
 * when starting the virtual machine or <code>System.setProperty()</code>) or
 * by adding the line "
 * <code>org.ogc.go1.CommonFactoryClass=<i>className</i></code>" to the
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
     * The mapping of CommonFactory class names to CommonFactory instances. Used
     * to store CommonFactoryImpls other than (but also including) the default
     * impl.
     */
    private static HashMap commonFactoryMap;
    
    private static ArrayList boundsFactoryList;
    
    private static ArrayList crsFactoryList;
    
    private static ArrayList crsAuthorityFactoryList;
    
    private static ArrayList datumFactoryList;
    
    private static ArrayList datumAuthorityFactoryList;
    
    
    // Initialize our static fields...
    static {
        try {
            
            // Get the system property.
            String cfClassName = System.getProperty("org.opengis.go.CommonFactoryClass");
            
            // If the system property was not set, try to read from the file
            // "org/opengis/go/Common.properties".
            if (cfClassName == null) {
                try {
                    ResourceBundle commonResources = ResourceBundle.getBundle("org.opengis.go.Common");
                    if (commonResources != null) {
                        cfClassName = commonResources.getString("org.opengis.go.CommonFactoryClass");
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
            CommonFactory commonFactory = (org.opengis.go.CommonFactory) cfClass.newInstance();
            
            // set up the commonFactoryMap and put the default factory in it
            // commonFactoryMap's size starts out at 1 because in most cases
            // we won't have more than one CommonFactory
            commonFactoryMap = new HashMap(1);
            commonFactoryMap.put(cfClassName, commonFactory);
            
            // add subfactories to the factory lists
            boundsFactoryList = new ArrayList(1);
            boundsFactoryList.add(commonFactory.getBoundsFactory());
            crsFactoryList = new ArrayList(1);
            crsFactoryList.add(commonFactory.getCRSFactory());
            crsAuthorityFactoryList = new ArrayList(1);
            crsAuthorityFactoryList.add(commonFactory.getCRSAuthorityFactory());
            datumAuthorityFactoryList = new ArrayList(1);
            datumAuthorityFactoryList.add(commonFactory.getDatumAuthorityFactory());
            datumFactoryList = new ArrayList(1);
            datumFactoryList.add(commonFactory.getDatumFactory());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Returns the <code>CommonFactory</code> instance with the given
     * className. If that particular type of <code>CommonFactory</code> has
     * not been created, then a new instance will be instantiated.
     * 
     * @param className the class name of the requested 
     *        <code>CommonFactory</code>.
     */
    public static CommonFactory getCommonFactory(String className) throws ClassNotFoundException,
            IllegalAccessException, InstantiationException {
        
        if (commonFactoryMap.containsKey(className)) {
            return (CommonFactory)commonFactoryMap.get(className);   
        }
        CommonFactory commonFactory = (CommonFactory) Class.forName(className).newInstance();
        commonFactoryMap.put(className, commonFactory);

        // add subfactories to the factory lists
        boundsFactoryList.add(commonFactory.getBoundsFactory());
        crsFactoryList.add(commonFactory.getCRSFactory());
        crsAuthorityFactoryList.add(commonFactory.getCRSAuthorityFactory());
        datumAuthorityFactoryList.add(commonFactory.getDatumAuthorityFactory());
        datumFactoryList.add(commonFactory.getDatumFactory());
        
        return commonFactory;
    }
    
    
    /**
     * Returns an arbitrary CoordinateReferenceSystem from the given code.  
     * The CommonFactoryManager will ask each of its known CRSAuthorityFactories
     * in turn to create the CRS, returning as soon as it is successfully created
     * and returning null if no factory can resolve the code.
     * @param code the CRSAuthority code to resolve
     * @return the newly create CoordinateReferenceSystem, or null if no 
     * CRSAuthorityFactory could resolve the code
     */
    public static CoordinateReferenceSystem createCoordinateReferenceSystem(String code) {
        CoordinateReferenceSystem crs = null;
        
        int size = crsAuthorityFactoryList.size();
        for (int i = 0; i < size && crs == null; i++) {
            try {
                crs = ((CRSAuthorityFactory)crsAuthorityFactoryList.get(i)).createCoordinateReferenceSystem(code);
            } catch(FactoryException fe) {
                // silence
            }
        }       
        return crs;
    }
    
    // PENDING(jdc): would we like to see other create*** methods from other factories
    // get the same treatment as CRS?  does it make sense?
}

