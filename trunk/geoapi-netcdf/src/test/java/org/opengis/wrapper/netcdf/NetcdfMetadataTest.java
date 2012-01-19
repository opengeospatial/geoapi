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
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.ParseException;

import ucar.nc2.NetcdfFile;
import ucar.nc2.ncml.NcMLReader;
import ucar.unidata.util.DateUtil;

import org.opengis.metadata.*;
import org.opengis.metadata.extent.*;
import org.opengis.metadata.spatial.*;
import org.opengis.metadata.citation.*;
import org.opengis.metadata.identification.*;

import org.junit.Test;

import static org.opengis.test.Assert.*;
import static org.opengis.test.Validators.*;


/**
 * Tests the {@link NetcdfMetadataTest} class.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public strictfp class NetcdfMetadataTest {
    /**
     * The NcML XML test file.
     */
    static final String NCML_FILE = "thredds.ncml";

    /**
     * The binary NetCDF test file.
     */
    static final String BINARY_FILE = "2005092200_sst_21-24.en.nc";

    /**
     * Tolerance factor for floating point comparison.
     * We actually require an exact match.
     */
    private static final double EPS = 0;

    /**
     * The tests in this class need to be lenient about mandatory attributes
     * in the metadata object.
     */
    static {
        org.opengis.test.Validators.DEFAULT.metadata.requireMandatoryAttributes = false;
    }

    /**
     * Opens the given NetCDF file.
     *
     * @param  file One of the {@link #NCML_FILE} or {@link #BINARY_FILE} constant.
     * @return The NetCDF file.
     * @throws IOException If an error occurred while opening the file.
     */
    static NetcdfFile open(final String file) throws IOException {
        if (file.endsWith(".ncml")) {
            final InputStream in = NetcdfMetadataTest.class.getResourceAsStream(file);
            if (in == null) {
                throw new FileNotFoundException(file);
            }
            try {
                return NcMLReader.readNcML(in, null);
            } finally {
                in.close();
            }
        }
        if (file.endsWith(".nc")) {
            final URL url = NetcdfMetadataTest.class.getResource(file);
            if (url == null) {
                throw new FileNotFoundException(file);
            }
            try {
                return NetcdfFile.open(new File(url.toURI()).getPath());
            } catch (URISyntaxException e) {
                throw (IOException) new MalformedURLException(e.getLocalizedMessage()).initCause(e);
            }
        }
        throw new IllegalArgumentException(file);
    }

    /**
     * Returns the single element from the given collection. If the given collection is null
     * or does not contains exactly one element, then an {@link AssertionError} is thrown.
     *
     * @param  <E> The type of collection elements.
     * @param  collection The collection from which to get the singleton.
     * @return The singleton element from the collection.
     */
    static <E> E getSingleton(final Iterable<? extends E> collection) {
        assertNotNull("Null collection.", collection);
        final Iterator<? extends E> it = collection.iterator();
        assertTrue("The collection is empty.", it.hasNext());
        final E element = it.next();
        assertFalse("The collection has more than one element.", it.hasNext());
        return element;
    }

    /**
     * Tests a NcML file (XML format).
     *
     * @throws IOException If the test file can not be read.
     */
    @Test
    public void testNcML() throws IOException {
        final NetcdfFile file = open(NCML_FILE);
        final Metadata metadata = new NetcdfMetadata(file);
        validate(metadata);
        /*
         * Responsibly party.
         */
        final ResponsibleParty party = getSingleton(metadata.getContacts());
        assertEquals("David Neufeld", party.getIndividualName());
        assertEquals("xxxxx.xxxxxxx@noaa.gov", getSingleton(party.getContactInfo().getAddress().getElectronicMailAddresses()));
        /*
         * Metadata / Data Identification / Geographic Bounding Box.
         */
        final DataIdentification identification = (DataIdentification) getSingleton(metadata.getIdentificationInfo());
        final GeographicBoundingBox bbox = (GeographicBoundingBox) getSingleton(getSingleton(identification.getExtents()).getGeographicElements());
        assertEquals("West Bound Longitude", -80, bbox.getWestBoundLongitude(), EPS);
        assertEquals("East Bound Longitude", -64, bbox.getEastBoundLongitude(), EPS);
        assertEquals("South Bound Latitude",  40, bbox.getSouthBoundLatitude(), EPS);
        assertEquals("North Bound Latitude",  48, bbox.getNorthBoundLatitude(), EPS);

        file.close();
    }

    /**
     * Tests a NetCDF file (binary format).
     *
     * @throws IOException If the test file can not be read.
     * @throws ParseException Should never happen.
     */
    @Test
    public void testNC() throws IOException, ParseException {
        final NetcdfFile file = open(BINARY_FILE);
        final Metadata metadata = new NetcdfMetadata(file);
        validate(metadata);
        /*
         * Metadata / Data Identification.
         */
        assertEquals("edu.ucar.unidata:NCEP/SST/Global_5x2p5deg/SST_Global_5x2p5deg_20050922_0000.nc", metadata.getFileIdentifier());
        final DataIdentification identification = (DataIdentification) getSingleton(metadata.getIdentificationInfo());
        assertSame(SpatialRepresentationType.GRID, getSingleton(identification.getSpatialRepresentationTypes()));
        assertEquals("NCEP SST Global 5.0 x 2.5 degree model data", identification.getAbstract().toString());
        /*
         * Metadata / Responsibly party.
         */
        final ResponsibleParty party = getSingleton(metadata.getContacts());
        assertEquals("NOAA/NWS/NCEP", party.getIndividualName());
        assertEquals(Role.ORIGINATOR, party.getRole());
        /*
         * Metadata / Data Identification / Citation.
         */
        final Citation citation = identification.getCitation();
        final Identifier identifier = getSingleton(citation.getIdentifiers());
        final CitationDate date = getSingleton(citation.getDates());
        assertEquals("Sea Surface Temperature Analysis Model", citation.getTitle().toString());
        assertEquals("edu.ucar.unidata", identifier.getAuthority().getTitle().toString());
        assertEquals("NCEP/SST/Global_5x2p5deg/SST_Global_5x2p5deg_20050922_0000.nc", identifier.getCode());
        assertEquals("Expected 2005-09-22T00:00", DateUtil.parse("2005-09-22T00:00"), date.getDate());
        assertSame(DateType.CREATION, date.getDateType());
        /*
         * Metadata / Data Identification / Geographic Bounding Box.
         */
        final Extent extent = getSingleton(identification.getExtents());
        final GeographicBoundingBox bbox = (GeographicBoundingBox) getSingleton(extent.getGeographicElements());
        assertEquals("West Bound Longitude", -180, bbox.getWestBoundLongitude(), 0);
        assertEquals("East Bound Longitude", +180, bbox.getEastBoundLongitude(), 0);
        assertEquals("South Bound Latitude",  -90, bbox.getSouthBoundLatitude(), 0);
        assertEquals("North Bound Latitude",  +90, bbox.getNorthBoundLatitude(), 0);

        file.close();
    }
}
