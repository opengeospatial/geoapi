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

import java.io.IOException;
import ucar.nc2.NetcdfFile;

import org.opengis.metadata.*;
import org.opengis.metadata.extent.*;
import org.opengis.metadata.spatial.*;
import org.opengis.metadata.citation.*;
import org.opengis.metadata.identification.*;
import org.opengis.test.metadata.RootValidator;
import org.opengis.test.Validators;

import org.junit.Test;

import static org.opengis.test.Assert.*;


/**
 * Tests the {@link NetcdfMetadata} class.
 * <p>
 * External projects can override the {@link #wrap(NetcdfFile)}
 * method in order to test their own NetCDF wrapper, as in the example below:
 *
 * <blockquote><pre>public class MyTest extends NetcdfMetadataTest {
 *    &#64;Override
 *    protected Metadata wrap(NetcdfFile file) throws IOException {
 *        return new MyWrapper(file);
 *    }
 *}</pre></blockquote>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public strictfp class NetcdfMetadataTest extends IOTestCase {
    /**
     * Tolerance factor for floating point comparison.
     * We actually require an exact match.
     */
    private static final double EPS = 0;

    /**
     * The validator to use for validating the {@link Metadata} instance.
     * This validator is specified at construction time.
     */
    protected final RootValidator validator;

    /**
     * The metadata object being tested. This field is set to the value returned
     * by {@link #wrap(NetcdfFile)} when a {@code testXXX()} method is executed.
     */
    protected Metadata metadata;

    /**
     * Creates a new test case using the default validator.
     * This constructor sets the {@link RootValidator#requireMandatoryAttributes} field
     * to {@code false}, since NetCDF metadata are sometime incomplete.
     */
    public NetcdfMetadataTest() {
        this(new RootValidator(Validators.DEFAULT));
        validator.requireMandatoryAttributes = false;
    }

    /**
     * Creates a new test case using the given validator. This constructor is provided for
     * subclasses wanting to use different validation methods. It is caller responsibility
     * to configure the given validator (for example whatever to
     * {@linkplain RootValidator#requireMandatoryAttributes require mandatory attributes}).
     *
     * @param validator The validator to use for validating the {@link Metadata} instance.
     */
    protected NetcdfMetadataTest(final RootValidator validator) {
        this.validator = validator;
    }

    /**
     * Wraps the given NetCDF file into a GeoAPI Metadata object. The default implementation
     * creates a {@link NetcdfMetadata} instance. Subclasses can override this method for
     * creating their own instance.
     *
     * @param  file The NetCDF file to wrap.
     * @return A metadata implementation created from the attributes found in the given file.
     * @throws IOException If an error occurred while wrapping the given NetCDF file.
     */
    protected Metadata wrap(final NetcdfFile file) throws IOException {
        return new NetcdfMetadata(file);
    }

    /*
     * Note: this test case shall not verify the hard-coded constants (metadataStandardName,
     * metadataStandardVersion, hierarchyLevel) since they are obviously implementation-dependent.
     */

    /**
     * Tests the {@value org.opengis.wrapper.netcdf.IOTestCase#THREDDS} file (XML format).
     * The current implementation tests:
     * <p>
     * <ul>
     *   <li>The {@linkplain ResponsibleParty Responsible party} name, role and email address.</li>
     *   <li>The {@linkplain GeographicBoundingBox geographic bounding box}.</li>
     * </ul>
     *
     * @throws IOException If the test file can not be read.
     */
    @Test
    public void testTHREDDS() throws IOException {
        final NetcdfFile file = open(THREDDS);
        try {
            final Metadata metadata = wrap(file);
            this.metadata = metadata; // For subclasses usage.
            validator.validate(metadata);
            /*
             * Metadata / Data Identification / Citation / Responsibly party.
             */
            final DataIdentification identification = (DataIdentification) getSingleton(metadata.getIdentificationInfo());
            final ResponsibleParty party = getSingleton(identification.getCitation().getCitedResponsibleParties());
            assertEquals("identificationInfo.citation.citedResponsibleParty.individualName", "David Neufeld",
                    party.getIndividualName());
            assertEquals("identificationInfo.citation.citedResponsibleParty.contactInfo.address.electronicMailAddress", "xxxxx.xxxxxxx@noaa.gov",
                    getSingleton(party.getContactInfo().getAddress().getElectronicMailAddresses()));
            assertEquals("identificationInfo.citation.citedResponsibleParty.role", Role.ORIGINATOR,
                    party.getRole());
            /*
             * Metadata / Data Identification / Geographic Bounding Box.
             */
            final GeographicBoundingBox bbox = (GeographicBoundingBox) getSingleton(getSingleton(identification.getExtents()).getGeographicElements());
            assertEquals("West Bound Longitude", -80, bbox.getWestBoundLongitude(), EPS);
            assertEquals("East Bound Longitude", -64, bbox.getEastBoundLongitude(), EPS);
            assertEquals("South Bound Latitude",  40, bbox.getSouthBoundLatitude(), EPS);
            assertEquals("North Bound Latitude",  48, bbox.getNorthBoundLatitude(), EPS);
        } finally {
            file.close();
        }
    }

    /**
     * Tests the {@value org.opengis.wrapper.netcdf.IOTestCase#NCEP} file (binary format).
     * The current implementation tests:
     * <p>
     * <ul>
     *   <li>The {@linkplain Metadata#getIdentificationInfo() identification} identifier, title, abstract and date.</li>
     *   <li>The {@linkplain ResponsibleParty Responsible party} name, role and email address.</li>
     *   <li>The {@linkplain GeographicBoundingBox geographic bounding box}.</li>
     * </ul>
     *
     * @throws IOException If the test file can not be read.
     */
    @Test
    public void testNCEP() throws IOException {
        final NetcdfFile file = open(NCEP);
        try {
            final Metadata metadata = wrap(file);
            this.metadata = metadata; // For subclasses usage.
            validator.validate(metadata);
            /*
             * Metadata / Data Identification.
             */
            assertEquals("fileIdentifier", "edu.ucar.unidata:NCEP/SST/Global_5x2p5deg/SST_Global_5x2p5deg_20050922_0000.nc",
                    metadata.getFileIdentifier());
            final DataIdentification identification = (DataIdentification) getSingleton(metadata.getIdentificationInfo());
            assertEquals("identificationInfo.spatialRepresentationType", SpatialRepresentationType.GRID,
                    getSingleton(identification.getSpatialRepresentationTypes()));
            assertEquals("identificationInfo.abstract", "NCEP SST Global 5.0 x 2.5 degree model data",
                    valueOf(identification.getAbstract()));
            /*
             * Metadata / Data Identification / Citation.
             */
            final Citation citation = identification.getCitation();
            final Identifier identifier = getSingleton(citation.getIdentifiers());
            final CitationDate date = getSingleton(citation.getDates());
            assertEquals("identificationInfo.citation.title", "Sea Surface Temperature Analysis Model",
                    valueOf(citation.getTitle()));
            assertEquals("identificationInfo.citation.identifier.authority", "edu.ucar.unidata",
                    valueOf(identifier.getAuthority().getTitle()));
            assertEquals("identificationInfo.citation.identifier.code", "NCEP/SST/Global_5x2p5deg/SST_Global_5x2p5deg_20050922_0000.nc",
                    identifier.getCode());
            assertEquals("identificationInfo.citation.date.date(2005-09-22T00:00)", parseDate("2005-09-22T00:00"),
                    date.getDate());
            assertEquals("identificationInfo.citation.date.dateType", DateType.CREATION,
                    date.getDateType());
            /*
             * Metadata / Data Identification / Citation / Responsibly party.
             */
            final ResponsibleParty party = getSingleton(citation.getCitedResponsibleParties());
            assertEquals("identificationInfo.citation.citedResponsibleParty.individualName", "NOAA/NWS/NCEP",
                    party.getIndividualName());
            assertEquals("identificationInfo.citation.citedResponsibleParty.role", Role.ORIGINATOR,
                    party.getRole());
            /*
             * Metadata / Data Identification / Geographic Bounding Box.
             */
            final Extent extent = getSingleton(identification.getExtents());
            final GeographicBoundingBox bbox = (GeographicBoundingBox) getSingleton(extent.getGeographicElements());
            assertEquals("West Bound Longitude", -180, bbox.getWestBoundLongitude(), 0);
            assertEquals("East Bound Longitude", +180, bbox.getEastBoundLongitude(), 0);
            assertEquals("South Bound Latitude",  -90, bbox.getSouthBoundLatitude(), 0);
            assertEquals("North Bound Latitude",  +90, bbox.getNorthBoundLatitude(), 0);
            assertEquals("identificationInfo.extent.geographicElement.extentTypeCode", Boolean.TRUE,
                    bbox.getInclusion());
        } finally {
            file.close();
        }
    }

    /**
     * Tests the {@value org.opengis.wrapper.netcdf.IOTestCase#LANDSAT} file (binary format).
     * Actually there is close to nothing we can test in this file, so this test case is merely
     * verifying that no exception is thrown at reading time.
     *
     * @throws IOException If the test file can not be read.
     */
    @Test
    public void testLandsat() throws IOException {
        final NetcdfFile file = open(LANDSAT);
        try {
            final Metadata metadata = wrap(file);
            this.metadata = metadata; // For subclasses usage.
            assertNotNull(metadata);
        } finally {
            file.close();
        }
    }

    /**
     * Tests the {@value org.opengis.wrapper.netcdf.IOTestCase#CIP} file (binary format).
     *
     * @throws IOException If the test file can not be read.
     */
    @Test
    public void testCIP() throws IOException {
        final NetcdfFile file = open(CIP);
        try {
            final Metadata metadata = wrap(file);
            this.metadata = metadata; // For subclasses usage.
            assertNotNull(metadata);
            /*
             * Metadata / Data Identification.
             */
            final DataIdentification identification = (DataIdentification) getSingleton(metadata.getIdentificationInfo());
            assertEquals("identificationInfo.supplementalInformation", "Created by Mdv2NetCDF",
                    valueOf(identification.getSupplementalInformation()));
            final ResponsibleParty party = getSingleton(identification.getCitation().getCitedResponsibleParties());
            assertEquals("identificationInfo.citation.citedResponsibleParty.organisationName", "UCAR",
                    valueOf(party.getOrganisationName()));
            assertEquals("identificationInfo.citation.citedResponsibleParty.role", Role.ORIGINATOR,
                    party.getRole());
        } finally {
            file.close();
        }
    }
}
