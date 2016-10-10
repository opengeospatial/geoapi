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

import java.util.Map;
import java.util.List;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.io.IOException;
import ucar.nc2.NetcdfFile;

import org.opengis.metadata.*;
import org.opengis.metadata.extent.*;
import org.opengis.metadata.spatial.*;
import org.opengis.metadata.content.*;
import org.opengis.metadata.quality.*;
import org.opengis.metadata.lineage.*;
import org.opengis.metadata.citation.*;
import org.opengis.metadata.constraint.*;
import org.opengis.metadata.identification.*;
import org.opengis.util.GenericName;
import org.opengis.test.metadata.RootValidator;
import org.opengis.test.Validators;

import org.junit.Test;

import static org.opengis.test.Assert.*;


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
 * <b><u>Inheriting tests in external projects</u></b><br>
 * In order to test their own implementation, external projects can override the
 * {@link #wrap(NetcdfFile)} method as in the example below:
 *
 * <blockquote><pre>public class MyTest extends NetcdfMetadataTest {
 *    &#64;Override
 *    protected Metadata wrap(NetcdfFile file) throws IOException {
 *        return new MyWrapper(file);
 *    }
 *}</pre></blockquote>
 *
 * In addition, implementors can alter the expected and actual attribute values before they
 * are compared, and alter the way the comparison is performed. More specifically, every
 * {@code testXXX()} method in this class proceeds in two steps:
 *
 * <ul>
 *   <li>First, {@linkplain #fetchMetadataProperties(String) fill a map of all property values}
 *       which are going to be tested.</li>
 *   <li>Then, {@linkplain #compareProperties(String,double) compare the expected and actual values},
 *       considering all properties as optional (i.e. no exception is thrown if the implementation
 *       didn't mapped a NetCDF attribute to a metadata property).</li>
 * </ul>
 *
 * Subclasses can override the {@link #fetchMetadataProperties(String)} and
 * {@link #compareProperties(String,double)} methods in order to modify the
 * default behavior, or for performing additional checks.
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
    protected final RootValidator validator;

    /**
     * The metadata object being tested. This field is set to the value returned
     * by {@link #wrap(NetcdfFile)} when a {@code testXXX()} method is executed.
     */
    protected Metadata metadata;

    /**
     * The properties that are expected for the test being run.
     * The content of this map is filled by the {@code testXXX()} method.
     */
    protected final Map<String,Object> expectedProperties;

    /**
     * The actual properties found in the {@linkplain #metadata}.
     * The content of this map is filled by the {@link #fetchMetadataProperties(String)} method.
     */
    protected final Map<String,Object> actualProperties;

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
     * to configure the given validator (for example whether to
     * {@linkplain RootValidator#requireMandatoryAttributes require mandatory attributes} or not).
     *
     * @param validator The validator to use for validating the {@link Metadata} instance.
     */
    protected NetcdfMetadataTest(final RootValidator validator) {
        this.validator = validator;
        expectedProperties = new LinkedHashMap<>(32);
        actualProperties   = new LinkedHashMap<>(32);
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

    /**
     * Returns the single element from the given collection, or {@code null} if none.
     * The default implementation makes the following choice:
     *
     * <ul>
     *   <li>If the given collection is null or empty, returns {@code null}.</li>
     *   <li>If the given collection contains exactly one element, returns that element.</li>
     *   <li>Otherwise, throws {@link AssertionError}.</li>
     * </ul>
     *
     * Subclasses can override this method if they want to select the element in a different way.
     *
     * @param  <E> The type of collection elements.
     * @param  collection The collection from which to get the singleton.
     * @return The singleton element from the collection, or {@code null} if none.
     */
    protected <E> E getSingleton(final Iterable<? extends E> collection) {
        if (collection != null) {
            final Iterator<? extends E> it = collection.iterator();
            if (it.hasNext()) {
                final E element = it.next();
                assertFalse("The collection has more than one element.", it.hasNext());
                return element;
            }
        }
        return null;
    }

    /**
     * Puts the given (key,value) pair in the map of actual properties, provided that the value is non-null.
     */
    private void put(final String key, Object value) {
        if (value != null) {
            if (value instanceof CharSequence || value instanceof GenericName) {
                value = value.toString();
            } else if (value instanceof Double) {
                if (Double.isNaN((Double) value)) {
                    return;
                }
            }
            final Object previous = actualProperties.put(key, value);
            if (previous != null) {
                assertEquals(key, previous, value);
            }
        }
    }

    /**
     * Puts the responsible party information in the {@link #actualProperties} map,
     * using the given prefix for the keys.
     */
    private void putParty(final String keyPrefix, final Responsibility responsibility) {
        if (responsibility != null) {
            put(keyPrefix + ".role", responsibility.getRole());
            for (final Party party : responsibility.getParties()) {
                if (party instanceof Individual) {
                    put(keyPrefix + ".individual.name",   party.getName());
                }
                if (party instanceof Organisation) {
                    put(keyPrefix + ".organisation.name", party.getName());
                }
                final Contact contact = getSingleton(party.getContactInfo());
                if (contact != null) {
                    final Address address = getSingleton(contact.getAddresses());
                    if (address != null) {
                        put(keyPrefix + ".party.contactInfo.address.electronicMailAddress", getSingleton(address.getElectronicMailAddresses()));
                    }
                }
            }
        }
    }

    /**
     * Fills the {@link #actualProperties} map with the property values found in the current {@link #metadata}.
     * This method fetches only the metadata properties which are mapped to NetCDF attributes,
     * and intentionally ignores all other metadata.
     *
     * @param filename The NetCDF file being tested, typically as one of the constants defined
     *         in the {@link IOTestCase} class.
     */
    protected void fetchMetadataProperties(final String filename) {
        put("metadataStandardName",    metadata.getMetadataStandardName());
        put("metadataStandardVersion", metadata.getMetadataStandardVersion());
        final Identifier metadataIdentifier = metadata.getMetadataIdentifier();
        if (metadataIdentifier != null) {
            put("metadataIdentifier.code",      metadataIdentifier.getCode());
            put("metadataIdentifier.codeSpace", metadataIdentifier.getCodeSpace());
        }
        /*
         * Metadata / Contact.
         */
        putParty("contact", getSingleton(metadata.getContacts()));
        final Identification identification = getSingleton(metadata.getIdentificationInfo());
        if (identification != null) {
            put("identificationInfo.abstract", identification.getAbstract());
            /*
             * Metadata / Data Identification / Point of Contact.
             */
            putParty("identificationInfo.pointOfContact", getSingleton(identification.getPointOfContacts()));
            /*
             * Metadata / Data Identification / Citation.
             */
            final Citation citation = identification.getCitation();
            if (citation != null) {
                put("identificationInfo.citation.title", citation.getTitle());
                final Identifier identifier = getSingleton(citation.getIdentifiers());
                if (identifier != null) {
                    put("identificationInfo.citation.identifier.code", identifier.getCode());
                    final Citation authority = identifier.getAuthority();
                    if (authority != null) {
                        put("identificationInfo.citation.identifier.authority", authority.getTitle());
                    }
                }
                final CitationDate date = getSingleton(citation.getDates());
                if (date != null) {
                    put("identificationInfo.citation.date.date", date.getDate());
                    put("identificationInfo.citation.date.dateType", date.getDateType());
                }
                /*
                 * Metadata / Data Identification / Citation / Responsibly party.
                 */
                putParty("identificationInfo.citation.citedResponsibleParty", getSingleton(citation.getCitedResponsibleParties()));
            }
            /*
             * Metadata / Data Identification / Descriptive Keywords.
             */
            final Keywords keywords = getSingleton(identification.getDescriptiveKeywords());
            if (keywords != null) {
                put("identificationInfo.descriptiveKeywords.type", keywords.getType());
                put("identificationInfo.descriptiveKeywords.thesaurusName", keywords.getThesaurusName().getTitle());
                put("identificationInfo.descriptiveKeywords.keyword", getSingleton(keywords.getKeywords()));
            }
            /*
             * Metadata / Data Identification / Resource Constraints / Use Limitation.
             */
            final Constraints constraints = getSingleton(identification.getResourceConstraints());
            if (constraints != null) {
                put("identificationInfo.resourceConstraints.useLimitation", getSingleton(constraints.getUseLimitations()));
            }
            /*
             * Metadata / Data Identification / Extents.
             */
            if (identification instanceof DataIdentification) {
                final DataIdentification data = (DataIdentification) identification;
                put("identificationInfo.supplementalInformation", data.getSupplementalInformation());
                put("identificationInfo.spatialRepresentationType", getSingleton(data.getSpatialRepresentationTypes()));
                final Extent extent = getSingleton(data.getExtents());
                if (extent != null) {
                    final GeographicExtent geoExtent = getSingleton(extent.getGeographicElements());
                    if (geoExtent != null) {
                        /*
                         * Metadata / Data Identification / Extents / Geographic Bounding Box.
                         */
                        put("identificationInfo.extent.geographicElement.extentTypeCode", geoExtent.getInclusion());
                        if (geoExtent instanceof GeographicBoundingBox) {
                            final GeographicBoundingBox bbox = (GeographicBoundingBox) geoExtent;
                            put("identificationInfo.extent.geographicElement.westBoundLongitude", bbox.getWestBoundLongitude());
                            put("identificationInfo.extent.geographicElement.eastBoundLongitude", bbox.getEastBoundLongitude());
                            put("identificationInfo.extent.geographicElement.southBoundLatitude", bbox.getSouthBoundLatitude());
                            put("identificationInfo.extent.geographicElement.northBoundLatitude", bbox.getNorthBoundLatitude());
                        }
                        /*
                         * Metadata / Data Identification / Extents / Vertical Extent.
                         */
                        final VerticalExtent vext = getSingleton(extent.getVerticalElements());
                        if (vext != null) {
                            put("identificationInfo.extent.verticalElement.minimumValue", vext.getMinimumValue());
                            put("identificationInfo.extent.verticalElement.maximumValue", vext.getMaximumValue());
                        }
                    }
                }
            }
        }
        /*
         * Metadata / Quality / Lineage.
         */
        final DataQuality quality = getSingleton(metadata.getDataQualityInfo());
        if (quality != null) {
            final Lineage lineage = quality.getLineage();
            if (lineage != null) {
                put("dataQualityInfo.lineage.statement", lineage.getStatement());
            }
        }
        /*
         * Metadata / Grid Spatial Representation.
         */
        final SpatialRepresentation spatial = getSingleton(metadata.getSpatialRepresentationInfo());
        if (spatial instanceof GridSpatialRepresentation) {
            final GridSpatialRepresentation gridSpatial = (GridSpatialRepresentation) spatial;
            put("spatialRepresentationInfo.numberOfDimensions", gridSpatial.getNumberOfDimensions());
            final List<? extends Dimension> axes = gridSpatial.getAxisDimensionProperties();
            if (axes != null) { // Should never be null, but let be safe in case an implementation choose to do so.
                int dimension = 0;
                for (final Dimension axis : axes) {
                    if (axis != null) {
                        put("spatialRepresentationInfo.axisDimensionProperties[" + dimension + "].dimensionName", axis.getDimensionName());
                        put("spatialRepresentationInfo.axisDimensionProperties[" + dimension + "].dimensionSize", axis.getDimensionSize());
                        put("spatialRepresentationInfo.axisDimensionProperties[" + dimension + "].resolution",    axis.getResolution());
                    }
                    dimension++;
                }
            }
        }
        /*
         * Metadata / Content information / Range dimensions.
         */
        final ContentInformation content = getSingleton(metadata.getContentInfo());
        if (content instanceof CoverageDescription) {
            final RangeDimension band = getSingleton(((CoverageDescription) content).getDimensions());
            if (band != null) {
                put("contentInfo.dimension.description",        band.getDescription());
                put("contentInfo.dimension.sequenceIdentifier", band.getSequenceIdentifier());
            }
        }
    }

    /**
     * Compares the properties found in the {@linkplain #metadata} with the expected properties.
     * The default implementation removes all properties found from the properties maps.
     * Consequently, after this method call the maps contain the following entries:
     *
     * <ul>
     *   <li>The {@link #expectedProperties} map contains all properties not found in the NetCDF file.</li>
     *   <li>The {@link #actualProperties} map contains all properties from the NetCDF file which have not
     *       been verified.</li>
     * </ul>
     *
     * Subclasses can override this method if they want to modify the values before the comparison
     * is performed, or for ensuring that their mandatory values have been removed from the maps
     * (i.e. have been compared) after the comparison.
     *
     *
     * @param filename The NetCDF file being tested, typically as one of the constants defined
     *                  in the {@link IOTestCase} class.
     * @param eps Tolerance factor for comparing floating-point numbers.
     */
    protected void compareProperties(final String filename, final double eps) {
        for (final Iterator<Map.Entry<String,Object>> it=actualProperties.entrySet().iterator(); it.hasNext();) {
            final Map.Entry<String,Object> entry = it.next();
            final String key = entry.getKey();
            final Object expectedValue = expectedProperties.remove(key);
            if (expectedValue != null) {
                final Object actualValue = entry.getValue();
                assertEquals(key, expectedValue.getClass(), actualValue.getClass());
                if (expectedValue instanceof Double) {
                    assertEquals(key, (Double) expectedValue, (Double) actualValue, eps);
                } else {
                    assertEquals(key, expectedValue, actualValue);
                }
                it.remove();
            }
        }
    }

    /*
     * Note: this test case shall not verify the hard-coded constants (metadataStandardName,
     * metadataStandardVersion, hierarchyLevel) since they are obviously implementation-dependent.
     */

    /**
     * Tests the {@value org.opengis.wrapper.netcdf.IOTestCase#THREDDS} file (XML format).
     * The current implementation tests:
     *
     * <ul>
     *   <li>The {@linkplain Metadata#getMetadataIdentifier() metadata identifier}.</li>
     *   <li>The {@linkplain Responsibility responsible party} name, role and email address.</li>
     *   <li>The {@linkplain GeographicBoundingBox geographic bounding box}.</li>
     *   <li>The {@linkplain Dimension axis dimensions} names, sizes and resolution.</li>
     *   <li>The {@linkplain Lineage lineage} statement.</li>
     * </ul>
     *
     * Note that implementations don't need to support all those metadata.
     * See the class javadoc for more information.
     *
     * @throws IOException If the test file can not be read.
     */
    @Test
    public void testTHREDDS() throws IOException {
        final Map<String,Object> expected = expectedProperties;
        assertNull(expected.put("metadataIdentifier.code",                                           "crm_v1"));
        assertNull(expected.put("identificationInfo.citation.citedResponsibleParty.role",            Role.ORIGINATOR));
        assertNull(expected.put("identificationInfo.citation.citedResponsibleParty.individual.name", "David Neufeld"));
        assertNull(expected.put("identificationInfo.pointOfContact.individual.name",                 "David Neufeld"));
        assertNull(expected.put("contact.individual.name",                                           "David Neufeld"));
        assertNull(expected.put("contact.party.contactInfo.address.electronicMailAddress",                                           "xxxxx.xxxxxxx@noaa.gov"));
        assertNull(expected.put("identificationInfo.citation.citedResponsibleParty.party.contactInfo.address.electronicMailAddress", "xxxxx.xxxxxxx@noaa.gov"));
        assertNull(expected.put("identificationInfo.pointOfContact.party.contactInfo.address.electronicMailAddress",                 "xxxxx.xxxxxxx@noaa.gov"));
        assertNull(expected.put("identificationInfo.extent.geographicElement.extentTypeCode",         Boolean.TRUE));
        assertNull(expected.put("identificationInfo.extent.geographicElement.westBoundLongitude",    -80.0));
        assertNull(expected.put("identificationInfo.extent.geographicElement.eastBoundLongitude",    -64.0));
        assertNull(expected.put("identificationInfo.extent.geographicElement.southBoundLatitude",     40.0));
        assertNull(expected.put("identificationInfo.extent.geographicElement.northBoundLatitude",     48.0));
        assertNull(expected.put("spatialRepresentationInfo.numberOfDimensions",                       2));
        assertNull(expected.put("spatialRepresentationInfo.axisDimensionProperties[0].dimensionName", DimensionNameType.COLUMN));
        assertNull(expected.put("spatialRepresentationInfo.axisDimensionProperties[1].dimensionName", DimensionNameType.ROW));
        assertNull(expected.put("spatialRepresentationInfo.axisDimensionProperties[0].dimensionSize", 19201));
        assertNull(expected.put("spatialRepresentationInfo.axisDimensionProperties[1].dimensionSize",  9601));
        assertNull(expected.put("spatialRepresentationInfo.axisDimensionProperties[0].resolution", 8.332899328159992E-4));
        assertNull(expected.put("spatialRepresentationInfo.axisDimensionProperties[1].resolution", 8.332465368190813E-4));
        assertNull(expected.put("dataQualityInfo.lineage.statement", "xyz2grd -R-80/-64/40/48 -I3c -Gcrm_v1.grd"));
        try (NetcdfFile file = open(THREDDS)) {
            metadata = wrap(file);
            validator.validate(metadata);
            fetchMetadataProperties(THREDDS);
            compareProperties(THREDDS, 1E-12);
            if (getClass() == NetcdfMetadataTest.class) {
                NonInheritable.assertProcessedAllRelevant(actualProperties, "crm_v1.grd", false);
            }
        }
    }

    /**
     * Tests the {@value org.opengis.wrapper.netcdf.IOTestCase#NCEP} file (binary format).
     * The current implementation tests:
     *
     * <ul>
     *   <li>The {@linkplain Metadata#getIdentificationInfo() identification} identifier, title, abstract and date.</li>
     *   <li>The {@linkplain Citation citation} title, date and abstract.</li>
     *   <li>The {@linkplain Keywords keywords} type and thesaurus.</li>
     *   <li>The {@linkplain Responsibility responsible party} name and role.</li>
     *   <li>The {@linkplain GeographicBoundingBox geographic bounding box}.</li>
     *   <li>The {@linkplain VerticalExtent vertical extent}.</li>
     *   <li>The {@linkplain Constraints constraints} use limitation.</li>
     *   <li>The {@linkplain Lineage lineage} statement.</li>
     *   <li>The {@linkplain RangeDimension range dimensions}.</li>
     * </ul>
     *
     * Note that implementations don't need to support all those metadata.
     * See the class javadoc for more information.
     *
     * @throws IOException If the test file can not be read.
     */
    @Test
    public void testNCEP() throws IOException {
        final Map<String,Object> expected = expectedProperties;
        assertNull(expected.put("metadataIdentifier.code",                                          "NCEP/SST/Global_5x2p5deg/SST_Global_5x2p5deg_20050922_0000.nc"));
        assertNull(expected.put("metadataIdentifier.codeSpace",                                     "edu.ucar.unidata"));
        assertNull(expected.put("identificationInfo.citation.identifier.code",                      "NCEP/SST/Global_5x2p5deg/SST_Global_5x2p5deg_20050922_0000.nc"));
        assertNull(expected.put("identificationInfo.citation.identifier.authority",                 "edu.ucar.unidata"));
        assertNull(expected.put("identificationInfo.citation.citedResponsibleParty.role",            Role.ORIGINATOR));
        assertNull(expected.put("identificationInfo.citation.citedResponsibleParty.individual.name", "NOAA/NWS/NCEP"));
        assertNull(expected.put("identificationInfo.pointOfContact.individual.name",                 "NOAA/NWS/NCEP"));
        assertNull(expected.put("contact.individual.name",                                           "NOAA/NWS/NCEP"));
        assertNull(expected.put("identificationInfo.citation.date.date",                            NetcdfMetadata.parseDate("2005-09-22T00:00")));
        assertNull(expected.put("identificationInfo.citation.date.dateType",                        DateType.CREATION));
        assertNull(expected.put("identificationInfo.citation.title",                                "Sea Surface Temperature Analysis Model"));
        assertNull(expected.put("identificationInfo.abstract",                                      "NCEP SST Global 5.0 x 2.5 degree model data"));
        assertNull(expected.put("identificationInfo.descriptiveKeywords.type",                      KeywordType.THEME));
        assertNull(expected.put("identificationInfo.descriptiveKeywords.thesaurusName",             "GCMD Science Keywords"));
        assertNull(expected.put("identificationInfo.descriptiveKeywords.keyword",                   "EARTH SCIENCE > Oceans > Ocean Temperature > Sea Surface Temperature"));
        assertNull(expected.put("identificationInfo.resourceConstraints.useLimitation",             "Freely available"));
        assertNull(expected.put("identificationInfo.extent.geographicElement.extentTypeCode",       Boolean.TRUE));
        assertNull(expected.put("identificationInfo.extent.geographicElement.westBoundLongitude",   -180.0));
        assertNull(expected.put("identificationInfo.extent.geographicElement.eastBoundLongitude",   +180.0));
        assertNull(expected.put("identificationInfo.extent.geographicElement.southBoundLatitude",    -90.0));
        assertNull(expected.put("identificationInfo.extent.geographicElement.northBoundLatitude",    +90.0));
        assertNull(expected.put("identificationInfo.extent.verticalElement.minimumValue",              0.0));
        assertNull(expected.put("identificationInfo.extent.verticalElement.maximumValue",              0.0));
        assertNull(expected.put("identificationInfo.spatialRepresentationType",                       SpatialRepresentationType.GRID));
        assertNull(expected.put("spatialRepresentationInfo.numberOfDimensions",                        3));
        assertNull(expected.put("spatialRepresentationInfo.axisDimensionProperties[0].dimensionSize", 73));
        assertNull(expected.put("spatialRepresentationInfo.axisDimensionProperties[1].dimensionSize", 73));
        assertNull(expected.put("spatialRepresentationInfo.axisDimensionProperties[2].dimensionSize",  1));
        assertNull(expected.put("spatialRepresentationInfo.axisDimensionProperties[0].dimensionName", DimensionNameType.COLUMN));
        assertNull(expected.put("spatialRepresentationInfo.axisDimensionProperties[1].dimensionName", DimensionNameType.ROW));
        assertNull(expected.put("spatialRepresentationInfo.axisDimensionProperties[2].dimensionName", DimensionNameType.TIME));
        assertNull(expected.put("contentInfo.dimension.sequenceIdentifier",                           "SST"));
        assertNull(expected.put("contentInfo.dimension.description",                                  "Sea temperature"));
        assertNull(expected.put("dataQualityInfo.lineage.statement",
                "2003-04-07 12:12:50 - created by gribtocdl              " +
                "2005-09-26T21:50:00 - edavis - add attributes for dataset discovery"));
        try (NetcdfFile file = open(NCEP)) {
            metadata = wrap(file);
            validator.validate(metadata);
            fetchMetadataProperties(NCEP);
            compareProperties(NCEP, 0.0);
            if (getClass() == NetcdfMetadataTest.class) {
                NonInheritable.assertProcessedAllRelevant(actualProperties, null, false);
            }
        }
    }

    /**
     * Tests the {@value org.opengis.wrapper.netcdf.IOTestCase#LANDSAT} file (binary format).
     *
     * @throws IOException If the test file can not be read.
     */
    @Test
    public void testLandsat() throws IOException {
        final Map<String,Object> expected = expectedProperties;
        assertNull(expected.put("contentInfo.dimension.sequenceIdentifier", "Band1"));
        assertNull(expected.put("contentInfo.dimension.description",        "GDAL Band Number 1"));
        try (NetcdfFile file = open(LANDSAT)) {
            metadata = wrap(file);
            // Do not validate, because the metadata is known to be invalid
            // since the test file is not providing suffisient information.
            fetchMetadataProperties(LANDSAT);
            compareProperties(LANDSAT, 0.0);
            if (getClass() == NetcdfMetadataTest.class) {
                NonInheritable.assertProcessedAllRelevant(actualProperties, null, true);
            }
        }
    }

    /**
     * Tests the {@value org.opengis.wrapper.netcdf.IOTestCase#CIP} file (binary format).
     *
     * @throws IOException If the test file can not be read.
     */
    @Test
    public void testCIP() throws IOException {
        final Map<String,Object> expected = expectedProperties;
        assertNull(expected.put("identificationInfo.citation.citedResponsibleParty.role",              Role.ORIGINATOR));
        assertNull(expected.put("identificationInfo.citation.citedResponsibleParty.organisation.name", "UCAR"));
        assertNull(expected.put("identificationInfo.pointOfContact.organisation.name",                 "UCAR"));
        assertNull(expected.put("contact.organisation.name",                                           "UCAR"));
        assertNull(expected.put("identificationInfo.supplementalInformation",                          "Created by Mdv2NetCDF"));
        assertNull(expected.put("identificationInfo.extent.geographicElement.extentTypeCode",          Boolean.TRUE));
        assertNull(expected.put("identificationInfo.extent.geographicElement.westBoundLongitude",     -140.290));
        assertNull(expected.put("identificationInfo.extent.geographicElement.eastBoundLongitude",      -56.658));
        assertNull(expected.put("identificationInfo.extent.geographicElement.southBoundLatitude",       15.944));
        assertNull(expected.put("identificationInfo.extent.geographicElement.northBoundLatitude",       58.364));
        assertNull(expected.put("identificationInfo.extent.verticalElement.minimumValue",            -15.0));
        assertNull(expected.put("identificationInfo.extent.verticalElement.maximumValue",             35.0));
        assertNull(expected.put("spatialRepresentationInfo.numberOfDimensions",                       4));
        assertNull(expected.put("spatialRepresentationInfo.axisDimensionProperties[0].dimensionName", DimensionNameType.COLUMN));
        assertNull(expected.put("spatialRepresentationInfo.axisDimensionProperties[1].dimensionName", DimensionNameType.ROW));
        assertNull(expected.put("spatialRepresentationInfo.axisDimensionProperties[2].dimensionName", DimensionNameType.VERTICAL));
        assertNull(expected.put("spatialRepresentationInfo.axisDimensionProperties[3].dimensionName", DimensionNameType.TIME));
        assertNull(expected.put("spatialRepresentationInfo.axisDimensionProperties[0].dimensionSize", 61));
        assertNull(expected.put("spatialRepresentationInfo.axisDimensionProperties[1].dimensionSize", 45));
        assertNull(expected.put("spatialRepresentationInfo.axisDimensionProperties[2].dimensionSize",  6));
        assertNull(expected.put("spatialRepresentationInfo.axisDimensionProperties[3].dimensionSize",  1));
        assertNull(expected.put("contentInfo.dimension.sequenceIdentifier",                           "CIP"));
        assertNull(expected.put("contentInfo.dimension.description",                                  "Current Icing Product"));
        assertNull(expected.put("dataQualityInfo.lineage.statement", "U.S. National Weather Service - NCEP (WMC)"));
        try (NetcdfFile file = open(CIP)) {
            metadata = wrap(file);
            validator.validate(metadata);
            fetchMetadataProperties(CIP);
            compareProperties(CIP, 0.001);
            if (getClass() == NetcdfMetadataTest.class) {
                NonInheritable.assertProcessedAllRelevant(actualProperties, null, false);
            }
        }
    }
}
