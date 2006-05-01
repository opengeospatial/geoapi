/**************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.go;

// J2SE direct dependencies
import java.util.Set;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.LogRecord;
import javax.imageio.spi.ServiceRegistry;
import javax.imageio.spi.RegisterableService; // For javadoc

// OpenGIS dependencies
import org.opengis.go.display.DisplayFactory;


/**
 * Defines static methods used to access the application's default {@linkplain CommonFactory
 * common factory} implementation.
 *
 * <P>To declare a factory implementation, a services subdirectory is placed within the
 * <code>META-INF</code> directory that is present in every JAR file. This directory
 * contains a file for each factory interface that has one or more implementation classes
 * present in the JAR file. For example, if the JAR file contained a class named
 * <code>com.mycompany.CommonFactoryImpl</code> which implements the {@link CommonFactory}
 * interface, the JAR file would contain a file named:</P>
 *
 * <blockquote><pre>META-INF/services/org.opengis.go.CommonFactory</pre></blockquote>
 *
 * <P>containing the line:</P>
 *
 * <blockquote><pre>com.mycompany.CommonFactoryImpl</pre></blockquote>
 *
 * <P>If the factory classes implements {@link RegisterableService}, it will be notified upon
 * registration and deregistration. Note that the factory classes should be lightweight and quick
 * to load. Implementations of these interfaces should avoid complex dependencies on other classes
 * and on native code. The usual pattern for more complex services is to register a lightweight
 * proxy for the heavyweight service.</P>
 *
 * <TABLE WIDTH="80%" ALIGN="center" CELLPADDING="18" BORDER="4" BGCOLOR="#FFE0B0"><TR><TD>
 * This class is for demonstration purpose only and may be removed in future GeoAPI release.
 * As long as <code>Factory</code> implementations register themselves in the
 * <code>META-INF/services</code> directory, any implementation of a <code>FactoryManager</code>
 * can find them. It doesn't need to be this one.
 * </TD></TR></TABLE>
 *
 * @author Open GIS Consortium, Inc.
 * @version 1.0
 */
public final class FactoryManager {
    /**
     * The service registry for this manager.
     * Will be initialized only when first needed.
     */
    private static ServiceRegistry registry;

    /**
     * Do not allows any instantiation of this class.
     */
    private FactoryManager() {
    }

    /**
     * Returns the {@link ServiceRegistry} instance to use for all factories.
     * The instance will be created and plugins will be scanned the first time
     * this method is invoked.
     */
    private static ServiceRegistry getRegistry() {
        if (registry == null) {
            registry = new ServiceRegistry(Arrays.asList(new Class[] {
                    CommonFactory.class,
                    DisplayFactory.class,
                    // List here the INTERFACE classes of all other factories to manage.
                    }).iterator());
            scanForPlugins();
        }
        return registry;
    }

    /**
     * Returns the default implementation of {@link CommonFactory}. If no implementation is
     * registered, then this method throws an exception. If more than one implementation is
     * registered, an arbitrary one is selected.
     *
     * @return The default implementation of the {@link CommonFactory} interface.
     * @throws NoSuchElementException if no implementation was found for the
     *         {@link CommonFactory} interface.
     */
    public static synchronized CommonFactory getCommonFactory() throws NoSuchElementException {
        return (CommonFactory) getRegistry().getServiceProviders(CommonFactory.class, false).next();
    }

    /**
     * Returns a set of all available implementations for the {@link CommonFactory} interface.
     *
     * @return The set of all implementations available for the {@link CommonFactory} interface.
     */
    public static synchronized Set getCommonFactories() {
        return new LazySet(getRegistry().getServiceProviders(CommonFactory.class, false));
    }

    /**
     * Returns the default implementation of {@link DisplayFactory}. If no implementation is
     * registered, then this method throws an exception. If more than one implementation is
     * registered, an arbitrary one is selected.
     *
     * @return The default implementation of the {@link DisplayFactory} interface.
     * @throws NoSuchElementException if no implementation was found for the
     *         {@link DisplayFactory} interface.
     */
    public static synchronized DisplayFactory getDisplayFactory() throws NoSuchElementException {
        return (DisplayFactory) getRegistry().getServiceProviders(DisplayFactory.class, false).next();
    }

    /**
     * Returns a set of all available implementations for the {@link DisplayFactory} interface.
     *
     * @return The set of all implementations available for the {@link DisplayFactory} interface.
     */
    public static synchronized Set getDisplayFactories() {
        return new LazySet(getRegistry().getServiceProviders(DisplayFactory.class, false));
    }

    /**
     * Scans for factory plug-ins on the application class path. This method is needed because the
     * application class path can theoretically change, or additional plug-ins may become available.
     * Rather than re-scanning the classpath on every invocation of the API, the class path is
     * scanned automatically only on the first invocation. Clients can call this method to prompt
     * a re-scan. Thus this method need only be invoked by sophisticated applications which
     * dynamically make new plug-ins available at runtime.
     */
    public static synchronized void scanForPlugins() {
        if (registry == null) {
            getRegistry();
            return;
        }
        final Logger      logger = Logger.getLogger("org.opengis");
        final ClassLoader loader = Thread.currentThread().getContextClassLoader();
        for (final Iterator categories=registry.getCategories(); categories.hasNext();) {
            final Class category = (Class)categories.next();
            final Iterator  iter = ServiceRegistry.lookupProviders(category, loader);
            String classname = category.getName();
            classname = classname.substring(classname.lastIndexOf('.')+1);
            while (iter.hasNext()) {
                final Object factory   = iter.next();
                final String operation = registry.registerServiceProvider(factory, category) ? "Register " : "Replace  ";
                final LogRecord    log = new LogRecord(Level.CONFIG, operation + factory.getClass().getName() + " as " + classname);
                log.setSourceClassName("org.opengis.go.FactoryManager");
                log.setSourceMethodName("scanForPlugins");
                logger.log(log);
            }
        }
    }
}
