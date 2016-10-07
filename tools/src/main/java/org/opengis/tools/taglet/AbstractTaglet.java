/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2009-2016 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Permission to use, copy, and modify this software and its documentation, with
 *    or without modification, for any purpose and without fee or royalty is hereby
 *    granted, provided that you include the following on ALL copies of the software
 *    and documentation or portions thereof, including modifications, that you make:
 *
 *    1. The full text of this NOTICE in a location viewable to users of the
 *       redistributed or derivative work.
 *    2. Notice of any changes or modifications to the OGC files, including the
 *       date changes were made.
 *
 *    THIS SOFTWARE AND DOCUMENTATION IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE
 *    NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *    TO, WARRANTIES OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT
 *    THE USE OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
 *    PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
 *
 *    COPYRIGHT HOLDERS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL OR
 *    CONSEQUENTIAL DAMAGES ARISING OUT OF ANY USE OF THE SOFTWARE OR DOCUMENTATION.
 *
 *    The name and trademarks of copyright holders may NOT be used in advertising or
 *    publicity pertaining to the software without specific, written prior permission.
 *    Title to copyright in this software and any associated documentation will at all
 *    times remain with copyright holders.
 */
package org.opengis.tools.taglet;

import com.sun.javadoc.RootDoc;
import com.sun.javadoc.SourcePosition;
import com.sun.tools.doclets.Taglet;
import com.sun.tools.doclets.internal.toolkit.Configuration;
import com.sun.tools.doclets.formats.html.ConfigurationImpl;


/**
 * Base class for taglet implemented in this package.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
abstract class AbstractTaglet implements Taglet {
    /**
     * The doclet configuration, created when first needed for reporting warnings.
     *
     * <p>Note: the JDK7 branch uses {@code ConfigurationImpl.getInstance()} method.
     * But that method does not exist anymore in JDK8.</p>
     */
    private static Configuration configuration;

    /**
     * Returns the doclet configuration.
     */
    private static synchronized Configuration getConfiguration() {
        if (configuration == null) {
            try {
                // ConfigurationImpl.getInstance() on JDK5, JDK6 or JDK7.
                configuration = (Configuration) ConfigurationImpl.class.getMethod("getInstance", (Class[]) null).invoke((Object[]) null);
            } catch (ReflectiveOperationException e1) {
                // new ConfigurationImpl() on JDK8 (constructor is not public on previous versions).
                try {
                    configuration = ConfigurationImpl.class.newInstance();
                } catch (ReflectiveOperationException e2) {
                    throw new RuntimeException("Can't get the configuration:\n"
                            + "   1) " + e1 + '\n'
                            + "   2) " + e2 + '\n');
                }
            }
        }
        return configuration;
    }

    /**
     * For subclasses constructors.
     */
    AbstractTaglet() {
    }

    /**
     * Returns {@code true} by default.
     *
     * @return {@code true} by default.
     */
    @Override
    public boolean inOverview() {
        return true;
    }

    /**
     * Returns {@code true} since this taglet can be used in package documentation.
     *
     * @return Always {@code true}.
     */
    @Override
    public boolean inPackage() {
        return true;
    }

    /**
     * Returns {@code true} since this taglet can be used in type documentation
     * (classes or interfaces).
     *
     * @return Always {@code true}.
     */
    @Override
    public boolean inType() {
        return true;
    }

    /**
     * Returns {@code true} since this taglet can be used in constructor
     *
     * @return Always {@code true}.
     */
    @Override
    public boolean inConstructor() {
        return true;
    }

    /**
     * Returns {@code true} since this taglet can be used in method documentation.
     *
     * @return Always {@code true}.
     */
    @Override
    public boolean inMethod() {
        return true;
    }

    /**
     * Returns {@code true} since this taglet can be used in field documentation.
     *
     * @return Always {@code true}.
     */
    @Override
    public boolean inField() {
        return true;
    }

    /**
     * Prints a warning message.
     */
    @SuppressWarnings("UseOfSystemOutOrSystemErr")
    static void printWarning(final SourcePosition position, final String message) {
        final RootDoc root = getConfiguration().root;
        if (root != null) {
            root.printWarning(position, message);
        } else {
            System.err.println(message);
        }
    }
}
