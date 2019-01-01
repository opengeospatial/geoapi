/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2018-2019 Open Geospatial Consortium, Inc.
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
package org.opengis.bridge.python;

import java.util.Iterator;
import java.io.File;
import java.net.URISyntaxException;
import java.util.List;
import org.opengis.metadata.Metadata;
import org.opengis.metadata.MetadataScope;
import org.opengis.metadata.maintenance.ScopeCode;
import org.opengis.metadata.spatial.GridSpatialRepresentation;
import org.opengis.metadata.spatial.DimensionNameType;
import org.opengis.metadata.spatial.Dimension;
import org.jpy.PyLib;
import org.jpy.PyModule;
import org.jpy.PyObject;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assume.*;
import static org.junit.Assert.*;


/**
 * Tests the binding using GDAL implementation as an arbitrary backend.
 * The tests require the {@code "jpy.config"} system property to be set
 * to the path of a {@code "jpyconfig.properties"} file, otherwise the
 * tests are skipped.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 4.0
 * @since   4.0
 */
public final strictfp class GDALTest {
    /**
     * Non-null if the Python interpreter is running.
     */
    private static File rootDirectory;

    /**
     * Starts the Python interpreter before any test is run.
     */
    @BeforeClass
    public static void startPython() {
        String config = System.getProperty("jpy.config");
        if (config != null && !config.trim().isEmpty()) {
            File root;
            try {
                root = new File(GDALTest.class.getResource("GDALTest.class").toURI());
            } catch (URISyntaxException e) {
                throw new RuntimeException("Test class is not a regular file.", e);
            }
            do {
                root = root.getParentFile();
                if (root == null) return;
            } while (!new File(root, "geoapi-java-python").isDirectory());
            if (!PyLib.isPythonRunning()) {
                PyLib.startPython(new File(root, "geoapi/src/main/python").getPath(),
                                  new File(root, "geoapi-gdal/src/main/python").getPath());
            }
            rootDirectory = root;
        }
    }

    /**
     * Stops the Python interpreter after all tests have been run, successfully or not.
     */
    @AfterClass
    public static void stopPython() {
        if (rootDirectory != null) {
            rootDirectory = null;
            PyLib.stopPython();
        }
    }

    /**
     * Loads metadata from the given file. This methods use JPY-specific methods for getting the root object.
     * Once we have this root, all remaining method calls use GeoAPI only.
     */
    private static Metadata load(final String datafile) {
        assumeNotNull("The \"jpy.config\" system property must be set to the path of a \"jpyconfig.properties\" file.", rootDirectory);
        final PyModule pm = PyModule.importModule("opengis.wrapper.gdal");
        final PyObject dataset = pm.call("DataSet", new File(rootDirectory, datafile).toString());
        final Environment env = new Environment();
        return env.toJava(dataset.callMethod("metadata"), Metadata.class);
    }

    /**
     * Test a case using geographic coordinate reference system.
     */
    @Test
    public void testGeographic() {
        final Metadata metadata = load("geoapi-conformance/src/main/resources/org/opengis/test/dataset/Cube2D_geographic_packed.nc");
        final MetadataScope scope = first(metadata.getMetadataScopes());
        assertEquals("metadataScope.resourceScope", ScopeCode.DATASET, scope.getResourceScope());

        final GridSpatialRepresentation representation = (GridSpatialRepresentation) first(metadata.getSpatialRepresentationInfo());
        final List<? extends Dimension> axes = representation.getAxisDimensionProperties();
        assertEquals("axisDimensionProperties[0].dimensionName", DimensionNameType.COLUMN, axes.get(0).getDimensionName());
        assertEquals("axisDimensionProperties[0].dimensionName", DimensionNameType.ROW,    axes.get(1).getDimensionName());
    }

    /**
     * Returns the first element if the given collection, or {@code null} if none.
     */
    private static <E> E first(final Iterable<E> collection) {
        if (collection != null) {
            Iterator<E> it = collection.iterator();
            if (it.hasNext()) return it.next();
        }
        return null;
    }
}
