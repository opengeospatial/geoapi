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

import java.util.HashMap;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

import org.opengis.go.FactoryManagerInitializationException;

/**
 * <code>DisplayFactoryManager</code> defines static variables and methods used
 * to access the application's <code>DisplayFactory</code>
 * implementation(s).
 * <P>
 * The default <code>DisplayFactory</code> implementation may be
 * overridden in one of two ways: by setting the system property
 * <code>org.ogc.go1.DisplayFactoryClass</code> to the fully qualified name
 * of a class that implements <code>DisplayFactory</code> (using the "-D"
 * option when starting the virtual machine or
 * <code>System.setProperty()</code>) or by adding the line
 * "<code>org.ogc.go1.DisplayFactoryClass=<i>className</i></code>" to the
 * file <code>org/ogc/go1/display/Display.properties</code>, where
 * <code><i>className</i></code> is the fully qualified name of a class that
 * implements <code>DisplayFactory</code>.
 *
 * @author Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 */
public class DisplayFactoryManager {
    
    //*************************************************************************
    //  Static Fields
    //*************************************************************************
    
    private static String DISPLAY_FACTORY_NAME = "DISPLAY_FACTORY_NAME";
    
    /**
     * The mapping of DisplayFactory class names to DisplayFactory instances.
     * Used to store DisplayFactoryImpls other than (but also including) the
     * default impl.
     */
    private static HashMap factoryMap;

    /**
     * The default DisplayFactoryImpl and its subfactories.
     */
    private static DisplayFactory displayFactory;
    
    // Initialize our static fields...
    static {
        try {
            
            // Get the system property.
            String dfClassName =
                System.getProperty("org.ogc.go1.DisplayFactoryClass");
            
            // If the system property was not set, try to read from the file
            // "org/ogc/go1/display/Display.properties".
            if (dfClassName == null) {
                try {
                    ResourceBundle displayResources =
                        ResourceBundle.getBundle("org.ogc.go1.Display");
                    if (displayResources != null) {
                        dfClassName = displayResources.
                            getString("org.ogc.go1.DisplayFactoryClass");
                    }
                } catch (MissingResourceException mie) {
                    // If the properties file was not present, then we'll
                    // throw an exception below.
                }
            }
            
            // If we were unable to get the name of a valid DisplayFactory,
            // then throw an exception.
            if (dfClassName == null) {
                throw new FactoryManagerInitializationException();
            }
            // Otherwise, try to instantiate a new instance.
            Class dfClass = Class.forName(dfClassName);
            displayFactory = (org.opengis.go.display.DisplayFactory)
                                dfClass.newInstance();
            
            // set up the factoryMap and put the default factory in it
            // factoryMap's size starts out at 1 because in most cases
            // we won't have more than DisplayFactory
            factoryMap = new HashMap(1);
            factoryMap.put(dfClassName, displayFactory);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Returns the <code>DisplayFactory</code> instance with the given
     * className.  If that particular type of <code>DisplayFactory</code>
     * has not been created, then a new instance will be instantiated.
     * @param className the class name of the requested 
     *        <code>DisplayFactory</code>.
     */
    public static DisplayFactory getDisplayFactory(Properties displayFactoryProps) 
            throws ClassNotFoundException, IllegalAccessException, 
                   InstantiationException {
        String className = displayFactoryProps.getProperty(DISPLAY_FACTORY_NAME);
        if (factoryMap.containsKey(className)) {
            return (DisplayFactory)factoryMap.get(className);   
        }
        DisplayFactory factory = 
            (DisplayFactory)Class.forName(className).newInstance();
        factoryMap.put(className, factory);
        return factory;
    }
    
}
