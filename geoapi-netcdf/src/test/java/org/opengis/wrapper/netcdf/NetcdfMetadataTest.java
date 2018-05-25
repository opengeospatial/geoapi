/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 *
 *    The netCDF wrappers are provided as code examples, in the hope to facilitate
 *    GeoAPI implementations backed by other libraries. Implementors can take this
 *    source code and use it for any purpose, commercial or non-commercial, copyrighted
 *    or open-source, with no legal obligation to acknowledge the borrowing/copying
 *    in any way.
 */
package org.opengis.wrapper.netcdf;

import java.net.URI;
import java.util.Date;
import java.io.IOException;
import ucar.nc2.NetcdfFile;

import org.opengis.metadata.Metadata;
import org.opengis.metadata.citation.Role;
import org.opengis.metadata.citation.DateType;
import org.opengis.metadata.citation.Responsibility;
import org.opengis.metadata.citation.OnLineFunction;
import org.opengis.metadata.extent.GeographicBoundingBox;
import org.opengis.metadata.spatial.SpatialRepresentationType;
import org.opengis.metadata.identification.TopicCategory;
import org.opengis.metadata.maintenance.ScopeCode;
import org.opengis.test.metadata.MetadataBaseValidator;
import org.opengis.test.Validators;
import org.opengis.test.dataset.TestData;
import org.opengis.test.dataset.ContentVerifier;

import org.junit.Test;


/**
 * Tests the {@link NetcdfMetadata} class.
 * The main properties tested by this class are:
 *
 * <ul>
 *   <li>The {@linkplain Metadata#getIdentificationInfo() identification} identifier, title, abstract and date.</li>
 *   <li>The {@linkplain Responsibility responsible party} name, role and email address.</li>
 *   <li>The {@linkplain GeographicBoundingBox geographic bounding box}.</li>
 * </ul>
 *
 * Every {@code testXXX()} method in this class proceeds in three steps:
 *
 * <ul>
 *   <li>First,   {@link MetadataBaseValidator#validate(Metadata) validates} the metadata.</li>
 *   <li>Next,    {@linkplain ContentVerifier#addMetadataToVerify(Metadata) fetch all property values} which are going to be tested.</li>
 *   <li>Finally, {@linkplain ContentVerifier#compareMetadata(String, Object, Object...) compares the expected and actual values}.</li>
 * </ul>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public strictfp class NetcdfMetadataTest extends IOTestCase {
    /**
     * The validator to use for validating the {@link Metadata} instance.
     * This validator is specified at construction time.
     */
    protected final MetadataBaseValidator validator;

    /**
     * The metadata object being tested. This field is set to the value returned
     * by {@link #wrap(NetcdfFile)} when a {@code testXXX()} method is executed.
     */
    protected Metadata metadata;

    /**
     * Creates a new test case using the default validator.
     * This constructor sets the {@link MetadataBaseValidator#requireMandatoryAttributes} field
     * to {@code false}, since netCDF metadata are sometime incomplete.
     */
    public NetcdfMetadataTest() {
        validator = new MetadataBaseValidator(Validators.DEFAULT);
        validator.requireMandatoryAttributes = false;
    }

    /**
     * Creates a new test case using the given validator. This constructor is provided for
     * subclasses wanting to use different validation methods. It is caller responsibility
     * to configure the given validator (for example whether to
     * {@link MetadataBaseValidator#requireMandatoryAttributes require mandatory attributes} or not).
     *
     * @param validator  the validator to use for validating the {@link Metadata} instance.
     */
    protected NetcdfMetadataTest(final MetadataBaseValidator validator) {
        this.validator = validator;
    }

    /**
     * Wraps the given netCDF file into a GeoAPI metadata object.
     * The default implementation creates a {@link NetcdfMetadata} instance.
     * Subclasses can override this method for creating their own instance.
     *
     * @param  file  the netCDF file to wrap.
     * @return a metadata implementation created from the attributes found in the given file.
     * @throws IOException if an error occurred while wrapping the given netCDF file.
     */
    protected Metadata wrap(final NetcdfFile file) throws IOException {
        return new NetcdfMetadata(file);
    }

    /*
     * Note: this test case shall not verify the hard-coded constants (metadataStandardName,
     * metadataStandardVersion, hierarchyLevel) since they are obviously implementation-dependent.
     */

    /**
     * Verifies metadata decoded from {@link TestData#NETCDF_2D_GEOGRAPHIC} file.
     *
     * @throws IOException if the test file can not be read.
     */
    @Test
    public void testGeographic2D() throws IOException {
        final ContentVerifier verifier = new ContentVerifier();
        try (NetcdfFile file = open(TestData.NETCDF_2D_GEOGRAPHIC)) {
            metadata = wrap(file);
            validator.validate(metadata);
            verifier.addMetadataToVerify(metadata);
            verifier.assertMetadataEquals(
                "metadataScope[0].resourceScope",                                          ScopeCode.DATASET,
                "identificationInfo[0].abstract",                                          "Global, two-dimensional model data",
                "identificationInfo[0].purpose",                                           "GeoAPI conformance tests",
                "identificationInfo[0].citation.title",                                    "Test data from Sea Surface Temperature Analysis Model",
                "identificationInfo[0].citation.citedResponsibleParty[0].role",            Role.ORIGINATOR,
                "identificationInfo[0].citation.citedResponsibleParty[0].party[0].name",   "NOAA/NWS/NCEP",
                "identificationInfo[0].citation.date[0].date",                             new Date(1127347200000L),
                "identificationInfo[0].citation.date[0].dateType",                         DateType.CREATION,
                "identificationInfo[0].citation.identifier[0].code",                       "NCEP/SST/Global_5x2p5deg/SST_Global_5x2p5deg_20050922_0000.nc",
                "identificationInfo[0].citation.identifier[0].codeSpace",                  "edu.ucar.unidata",
                "identificationInfo[0].citation.identifier[0].authority.title",            "edu.ucar.unidata",
                "identificationInfo[0].citation.onlineResource[0].name",                   "Cube2D geographic packed",
                "identificationInfo[0].citation.onlineResource[0].linkage",                URI.create("Cube2D_geographic_packed.nc"),
                "identificationInfo[0].citation.onlineResource[0].function",               OnLineFunction.FILE_ACCESS,
                "identificationInfo[0].extent[0].geographicElement[0].extentTypeCode",     Boolean.TRUE,
                "identificationInfo[0].extent[0].geographicElement[0].westBoundLongitude", -180.0,
                "identificationInfo[0].extent[0].geographicElement[0].eastBoundLongitude",  180.0,
                "identificationInfo[0].extent[0].geographicElement[0].southBoundLatitude",  -90.0,
                "identificationInfo[0].extent[0].geographicElement[0].northBoundLatitude",   90.0,
                "identificationInfo[0].spatialRepresentationType[0]",                      SpatialRepresentationType.GRID,
                "identificationInfo[0].supplementalInformation",                           "For testing purpose only.",
                "metadataStandard[0].title",                                               "ISO 19115-2 Geographic Information - Metadata Part 2 Extensions for imagery and gridded data",
                "metadataStandard[0].edition",                                             "ISO 19115-2:2009(E)");
        }
    }

    /**
     * Verifies metadata decoded from {@link TestData#NETCDF_4D_PROJECTED} file.
     *
     * @throws IOException if the test file can not be read.
     */
    @Test
    public void testCompound4D() throws IOException {
        final String individual = "identificationInfo[0].citation.citedResponsibleParty[0].party[0].individual[0].";     // Shortcut.
        final ContentVerifier verifier = new ContentVerifier();
        try (NetcdfFile file = open(TestData.NETCDF_4D_PROJECTED)) {
            metadata = wrap(file);
            validator.validate(metadata);
            verifier.addMetadataToVerify(metadata);
            verifier.assertMetadataEquals(
                "metadataScope[0].resourceScope",                                          ScopeCode.DATASET,
                "identificationInfo[0].abstract",                                          "Hourly, three-dimensional diagnosis of the icing environment.",
                "identificationInfo[0].purpose",                                           "GeoAPI conformance tests",
                "identificationInfo[0].citation.title",                                    "Test data from Current Icing Product (CIP)",
                "identificationInfo[0].citation.citedResponsibleParty[0].role",            Role.ORIGINATOR,
                "identificationInfo[0].citation.citedResponsibleParty[0].party[0].name",   "UCAR",
                individual + "name",                                                       "John Doe",
                individual + "contactInfo[0].address[0].electronicMailAddress[0]",         "john.doe@example.org",
                "identificationInfo[0].citation.onlineResource[0].name",                   "Cube4D projected float",
                "identificationInfo[0].citation.onlineResource[0].linkage",                URI.create("Cube4D_projected_float.nc"),
                "identificationInfo[0].citation.onlineResource[0].function",               OnLineFunction.FILE_ACCESS,
                "identificationInfo[0].extent[0].geographicElement[0].extentTypeCode",     Boolean.TRUE,
                "identificationInfo[0].extent[0].geographicElement[0].westBoundLongitude", -107.75f,
                "identificationInfo[0].extent[0].geographicElement[0].eastBoundLongitude", -56.66f,
                "identificationInfo[0].extent[0].geographicElement[0].southBoundLatitude",  15.94f,
                "identificationInfo[0].extent[0].geographicElement[0].northBoundLatitude",  58.37f,
                "identificationInfo[0].topicCategory[0]",                                   TopicCategory.CLIMATOLOGY_METEOROLOGY_ATMOSPHERE,
                "identificationInfo[0].supplementalInformation",                           "For testing purpose only.",
                "metadataStandard[0].title",                                               "ISO 19115-2 Geographic Information - Metadata Part 2 Extensions for imagery and gridded data",
                "metadataStandard[0].edition",                                             "ISO 19115-2:2009(E)");
        }
    }
}
