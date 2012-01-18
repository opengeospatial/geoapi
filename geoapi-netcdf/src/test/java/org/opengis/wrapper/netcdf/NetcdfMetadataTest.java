/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 *
 *    The NetCDF wrappers are provided as code examples, in the hope to facilitate
 *    GeoAPI implementations backed by other libraries. Implementors can take this
 *    source code and use it for any purpose, commercial or non-commercial, copyrighted
 *    or open-source, with no legal obligation to acknowledge the borrowing/copying
 *    in any way.
 */
package org.opengis.wrapper.netcdf;

import java.util.Iterator;
import java.io.File;
import java.io.InputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import ucar.nc2.NetcdfFile;
import ucar.nc2.ncml.NcMLReader;

import org.opengis.metadata.*;
import org.opengis.metadata.extent.*;
import org.opengis.metadata.citation.*;
import org.opengis.metadata.identification.*;

import org.junit.Test;

import static org.opengis.test.Assert.*;


/**
 * Tests the {@link NetcdfMetadataTest} class.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public strictfp class NetcdfMetadataTest {
    /**
     * The THREDDS XML test file.
     */
    private static final String THREDDS_FILE = "thredds.ncml";

    /**
     * The binary NetCDF test file.
     */
    private static final String BINARY_FILE = "2005092200_sst_21-24.en.nc";

    /**
     * Tolerance factor for floating point comparison.
     * We actually require an exact match.
     */
    private static final double EPS = 0;

    /**
     * Returns the single element from the given collection. If the given collection is null
     * or does not contains exactly one element, then an {@link AssertionError} is thrown.
     *
     * @param  <E> The type of collection elements.
     * @param  collection The collection from which to get the singleton.
     * @return The singleton element from the collection.
     */
    public static <E> E getSingleton(final Iterable<? extends E> collection) {
        assertNotNull("Null collection.", collection);
        final Iterator<? extends E> it = collection.iterator();
        assertTrue("The collection is empty.", it.hasNext());
        final E element = it.next();
        assertFalse("The collection has more than one element.", it.hasNext());
        return element;
    }

    /**
     * Tests a file that contains THREDDS metadata.
     *
     * @throws IOException If the test file can not be read.
     */
    @Test
    public void testTHREDDS() throws IOException {
        final InputStream in = NetcdfMetadataTest.class.getResourceAsStream(THREDDS_FILE);
        assertNotNull("Can't find the " + THREDDS_FILE + " test file.", in);
        final NetcdfFile file = NcMLReader.readNcML(in, null);
        final Metadata metadata = new NetcdfMetadata(file);

        assertEquals("David Neufeld", getSingleton(metadata.getContacts()).getIndividualName());

        final DataIdentification identification = (DataIdentification) getSingleton(metadata.getIdentificationInfo());
        final GeographicBoundingBox bbox = (GeographicBoundingBox) getSingleton(getSingleton(identification.getExtents()).getGeographicElements());
        assertEquals("West Bound Longitude", -80, bbox.getWestBoundLongitude(), EPS);
        assertEquals("East Bound Longitude", -64, bbox.getEastBoundLongitude(), EPS);
        assertEquals("South Bound Latitude",  40, bbox.getSouthBoundLatitude(), EPS);
        assertEquals("North Bound Latitude",  48, bbox.getNorthBoundLatitude(), EPS);

        file.close();
        in.close();
    }

    /**
     * Tests a NetCDF binary file.
     *
     * @throws IOException If the test file can not be read.
     * @throws URISyntaxException Should never happen.
     */
    @Test
    public void testNC() throws IOException, URISyntaxException {
        final URL testFile = NetcdfMetadataTest.class.getResource(BINARY_FILE);
        assertNotNull("Can't find the " + BINARY_FILE + " test file.", testFile);
        final NetcdfFile file = NetcdfFile.open(new File(testFile.toURI()).getPath());
        final Metadata metadata = new NetcdfMetadata(file);

        assertEquals("edu.ucar.unidata:NCEP/SST/Global_5x2p5deg/SST_Global_5x2p5deg_20050922_0000.nc", metadata.getFileIdentifier());
        assertEquals("NOAA/NWS/NCEP", getSingleton(metadata.getContacts()).getIndividualName());
        assertEquals(Role.ORIGINATOR, getSingleton(metadata.getContacts()).getRole());

        final DataIdentification identification = (DataIdentification) getSingleton(metadata.getIdentificationInfo());
        assertEquals("NCEP SST Global 5.0 x 2.5 degree model data", identification.getAbstract().toString());

        final Citation citation = identification.getCitation();
        final Identifier identifier = getSingleton(citation.getIdentifiers());
        assertEquals("Sea Surface Temperature Analysis Model", citation.getTitle().toString());
        assertEquals("edu.ucar.unidata", identifier.getAuthority().getTitle().toString());
        assertEquals("NCEP/SST/Global_5x2p5deg/SST_Global_5x2p5deg_20050922_0000.nc", identifier.getCode());

        final Extent extent = getSingleton(identification.getExtents());
        final GeographicBoundingBox bbox = (GeographicBoundingBox) getSingleton(extent.getGeographicElements());
        assertEquals("West Bound Longitude", -180, bbox.getWestBoundLongitude(), 0);
        assertEquals("East Bound Longitude", +180, bbox.getEastBoundLongitude(), 0);
        assertEquals("South Bound Latitude",  -90, bbox.getSouthBoundLatitude(), 0);
        assertEquals("North Bound Latitude",  +90, bbox.getNorthBoundLatitude(), 0);
    }
}
