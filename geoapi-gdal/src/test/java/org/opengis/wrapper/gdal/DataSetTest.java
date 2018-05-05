/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 *
 *    The GDAL wrappers are provided as code examples, in the hope to facilitate
 *    GeoAPI implementations backed by other libraries. Implementors can take this
 *    source code and use it for any purpose, commercial or non-commercial, copyrighted
 *    or open-source, with no legal obligation to acknowledge the borrowing/copying
 *    in any way.
 */
package org.opengis.wrapper.gdal;

import java.io.IOException;
import java.nio.file.Paths;
import org.junit.Ignore;
import org.junit.Test;


/**
 * Tests the {@link DataSet} class.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public final strictfp class DataSetTest {
    @Test
    @Ignore("Not yet ready")
    public void testOpen() throws IOException {
        try (DataSet ds = new DataSet(Paths.get("TODO"))) {
            System.out.println(ds.getMetadata());
        }
    }
}
