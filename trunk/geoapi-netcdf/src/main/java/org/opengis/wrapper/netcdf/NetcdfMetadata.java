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

import java.util.Date;
import java.util.Locale;
import java.util.Collection;
import java.util.Collections;
import ucar.nc2.units.DateFormatter;
import ucar.nc2.NetcdfFile;
import ucar.nc2.Attribute;

import org.opengis.metadata.*;
import org.opengis.metadata.extent.*;
import org.opengis.metadata.spatial.*;
import org.opengis.metadata.citation.*;
import org.opengis.metadata.maintenance.*;
import org.opengis.metadata.distribution.*;
import org.opengis.metadata.identification.*;
import org.opengis.metadata.quality.DataQuality;
import org.opengis.metadata.constraint.Constraints;
import org.opengis.metadata.content.ContentInformation;
import org.opengis.metadata.acquisition.AcquisitionInformation;
import org.opengis.referencing.ReferenceSystem;
import org.opengis.util.InternationalString;


/**
 * A {@link Metadata} implementation backed by a NetCDF {@link NetcdfFile} object.
 * All getter methods fetch their values from the NetCDF file, so change to the NetCDF
 * file content will be immediately reflected in this class.
 * <p>
 * Unless otherwise noted in the javadoc, this implementation defines a one-to-one relationship
 * between the metadata attributes and NetCDF attributes. This simple model allows us to implement
 * all relevant interfaces in a single class. Note that this simplification may not be appropriate
 * for real world usages - the purpose of this class is mainly to provide a starting point for your
 * own implementations.
 * <p>
 * Some interfaces implemented by this class:
 *
 * <ul>
 *   <li>{@link Metadata} is the root interface.
 *          <ul><li>NetCDF attributes: {@code id}, {@code metadata_creation}.
 *          </li></ul><br></li>
 *
 *   <li>{@link DataIdentification} implements the value returned by {@link Metadata#getIdentificationInfo()}.
 *          <ul><li>NetCDF attributes: {@code summary}, {@code purpose}, {@code topic_category}, {@code cdm_data_type},
 *          {@code comment}, {@code acknowledgment}.
 *          </li></ul><br></li>
 *
 *   <li>{@link Identifier} implements the value returned by {@link Citation#getIdentifiers()}.
 *          <ul><li>NetCDF attributes: {@code id}, {@code naming_authority}.
 *          </li></ul><br></li>
 *
 *   <li>{@link Citation} implements the value returned by {@link Identification#getCitation()},
 *          which contain only the creator. This simple implementation ignores the publisher and contributors.
 *          <ul><li>NetCDF attributes: {@code title}.
 *          </li></ul><br></li>
 *
 *   <li>{@link CitationDate} implements the value returned by {@link Citation#getDates()}.
 *          <ul><li>NetCDF attributes: {@code date_created}.
 *          </li></ul><br></li>
 *
 *   <li>{@link ResponsibleParty} implements the value returned by {@link Citation#getCitedResponsibleParties()},
 *          which contain only the creator. This simple implementation ignores the publisher and contributors.
 *          <ul><li>NetCDF attributes: {@code creator_name}, {@code institution}.
 *          </li></ul><br></li>
 *
 *   <li>{@link Address} implements the value returned by {@link Contact#getAddress()}.
 *          <ul><li>NetCDF attributes: {@code creator_email}.
 *          </li></ul><br></li>
 *
 *   <li>{@link GeographicBoundingBox} implements the value returned by {@link Extent#getGeographicElements()}.
 *          <ul><li>NetCDF attributes: {@code geospatial_lon_min}, {@code geospatial_lon_max},
 *          {@code geospatial_lat_min}, {@code geospatial_lat_max}.
 *          </li></ul></li>
 * </ul>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 *
 * @see <a href="http://www.unidata.ucar.edu/software/netcdf-java/formats/DataDiscoveryAttConvention.html">NetCDF Attribute Convention for Dataset Discovery</a>
 */
public class NetcdfMetadata implements Metadata, DataIdentification, Identifier, Citation, CitationDate,
        ResponsibleParty, Contact, Address, Extent, GeographicBoundingBox
{
    /**
     * The hierarchy level returned by {@link #getHierarchyLevels()}.
     */
    private static final Collection<ScopeCode> HIERARCHY_LEVEL = Collections.singleton(ScopeCode.DATASET);

    /**
     * The NetCDF file given to the constructor.
     */
    protected final NetcdfFile file;

    /**
     * A singleton containing only {@code this}.
     */
    private final Collection<NetcdfMetadata> self;

    /**
     * The object to use for parsing dates, created when first needed.
     */
    private transient DateFormatter dateFormat;

    /**
     * Creates a new metadata object as a wrapper around the given NetCDF file.
     *
     * @param file The NetCDF file.
     */
    public NetcdfMetadata(final NetcdfFile file) {
        Objects.requireNonNull(file);
        this.file = file;
        self = Collections.singleton(this);
    }

    /**
     * Returns the value of the given attribute as a string. The attribute name is case-insensitive.
     * If non-null, the returned string is {@linkplain String#trim() trimmed} and never
     * {@linkplain String#isEmpty() empty}.
     *
     * @param  name The case-insensitive attribute name.
     * @return The non-empty attribute value, or {@code null} if none.
     */
    private String getString(final String name) {
        final Attribute attribute = file.findGlobalAttributeIgnoreCase(name);
        if (attribute != null && attribute.isString()) {
            String value = attribute.getStringValue();
            if (value != null && !(value = value.trim()).isEmpty()) {
                return value;
            }
        }
        return null;
    }

    /**
     * Returns the value of the given attribute as an upper case string. The default
     * implementation invokes {@link #getString(String)}, then change the case of the
     * result (if non null).
     *
     * @param  name The case-insensitive attribute name.
     * @return The non-empty attribute value in upper-case, or {@code null} if none.
     */
    private String getUpperCase(final String name) {
        final String value = getString(name);
        return (value != null) ? value.toUpperCase() : null;
    }

    /**
     * Returns the value of the given attribute as an international string. The default
     * implementation invokes {@link #getString(String)}, then wraps the result (if non
     * null) in an {@link InternationalString} implementation.
     *
     * @param  name The case-insensitive attribute name.
     * @return The non-empty attribute value, or {@code null} if none.
     */
    private InternationalString getInternationalString(final String name) {
        final String value = getString(name);
        return (value != null) ? new SimpleCitation(value) : null;
    }

    /**
     * Returns the value of the given attribute as a date. The default implementation invokes
     * {@link #getString(String)}, then parses the value.
     *
     * @param  name The case-insensitive attribute name.
     * @return The attribute value, or {@code null} if none or can not be parsed.
     */
    private Date getDate(final String name) {
        final String value = getString(name);
        if (value != null) {
            if (dateFormat == null) {
                dateFormat = new DateFormatter();
            }
            return dateFormat.getISODate(value);
        }
        return null;
    }

    /**
     * Returns the value of the given attribute as a floating point value.
     *
     * @param  name The case-insensitive attribute name.
     * @return The attribute value, or {@code NaN} if none.
     * @throws NumberFormatException If the number can not be parsed.
     */
    private double getDouble(final String name) throws NumberFormatException {
        final Attribute attribute = file.findGlobalAttributeIgnoreCase(name);
        if (attribute != null) {
            if (attribute.isString()) {
                final String value = attribute.getStringValue();
                if (value != null) {
                    return Double.parseDouble(value);
                }
            } else {
                final Number value = attribute.getNumericValue();
                if (value != null) {
                    return value.doubleValue();
                }
            }
        }
        return Double.NaN;
    }

    /**
     * Returns the given value in a collection if non-null, or an empty collection if the
     * given value is {@code null}.
     *
     * @param <T>   The type of the element to be wrapped in a collection.
     * @param value The value to wrap in a collection.
     * @return      A collection containing the given value, or an empty collection
     *              if the value was null.
     */
    private static <T> Collection<T> singleton(final T value) {
        return (value != null) ? Collections.singleton(value) : Collections.<T>emptySet();
    }

    /**
     * Returns the NetCDF "{@code title}" attribute value, or the
     * {@linkplain NetcdfFile#getTitle() file title}, or {@code null} if none.
     *
     * @see NetcdfFile#getTitle()
     */
    @Override
    public InternationalString getTitle() {
        String title = getString("title");
        if (title == null) {
            title = file.getTitle();
            if (title == null) {
                return null;
            }
        }
        return new SimpleCitation(title);
    }

    /**
     * Defaults to {@code null}.
     */
    @Override
    public InternationalString getCollectiveTitle() {
        return null;
    }

    /**
     * Defaults to an empty set.
     */
    @Override
    public Collection<? extends InternationalString> getAlternateTitles() {
        return Collections.emptySet();
    }

    /**
     * Defaults to {@code null}.
     */
    @Override
    public InternationalString getOtherCitationDetails() {
        return null;
    }

    /**
     * Returns the NetCDF "{@code summary}" attribute value, or {@code null} if none.
     */
    @Override
    public InternationalString getAbstract() {
        return getInternationalString("summary");
    }

    /**
     * Returns the NetCDF "{@code purpose}" attribute value, or {@code null} if none.
     */
    @Override
    public InternationalString getPurpose() {
        return getInternationalString("purpose");
    }

    /**
     * Returns the NetCDF "{@code comment}" attribute value, or {@code null} if none.
     */
    @Override
    public InternationalString getSupplementalInformation() {
        return getInternationalString("comment");
    }

    /**
     * Returns the NetCDF "{@code topic_category}" attribute value, or an empty set if none.
     */
    @Override
    public Collection<TopicCategory> getTopicCategories() {
        return singleton(TopicCategory.valueOf(getUpperCase("topic_category")));
    }

    /**
     * Defaults to an empty set.
     */
    @Override
    public Collection<? extends Keywords> getDescriptiveKeywords() {
        return Collections.emptySet();
    }

    /**
     * Returns the NetCDF "{@code cdm_data_type}" attribute value, or an empty set if none.
     */
    @Override
    public Collection<SpatialRepresentationType> getSpatialRepresentationTypes() {
        return singleton(SpatialRepresentationType.valueOf(getUpperCase("cdm_data_type")));
    }

    /**
     * Defaults to an empty set.
     */
    @Override
    public Collection<? extends BrowseGraphic> getGraphicOverviews() {
        return Collections.emptySet();
    }

    /**
     * Encapsulates the {@linkplain #getIdentifiers() identifiers},
     * {@linkplain #getCitation() creator} and other information.
     */
    @Override
    public Collection<? extends Identification> getIdentificationInfo() {
        return self;
    }

    /**
     * Encapsulates the {@linkplain #getCode() identifier code} and
     * {@linkplain #getAuthority() naming authority}.
     */
    @Override
    public Collection<? extends Identifier> getIdentifiers() {
        return self;
    }

    /**
     * Returns the NetCDF "{@code naming_authority}" attribute value, or {@code null} if none.
     *
     * @see #getFileIdentifier()
     */
    @Override
    public Citation getAuthority() {
        final String value = getString("naming_authority");
        return (value != null) ? new SimpleCitation(value) : null;
    }

    /**
     * Returns the NetCDF "{@code id}" attribute value, or the {@linkplain NetcdfFile#getId()
     * file identifier}, or {@code null} if none.
     *
     * @see NetcdfFile#getId()
     * @see #getFileIdentifier()
     */
    @Override
    public String getCode() {
        final String id = getString("id");
        return (id != null) ? id : file.getId();
    }

    /**
     * Returns the concatenation of {@linkplain #getAuthority() naming authority},
     * the {@code ':'} character and the {@linkplain #getCode() identifier code}.
     * One or both of the authority and the code can be null.
     *
     * @return The identifier code in the naming authority space, or {@code null} if null.
     *
     * @see #getAuthority()
     * @see #getCode()
     */
    @Override
    public String getFileIdentifier() {
        String code = getCode();
        final Citation authority = getAuthority();
        if (authority != null) {
            final String naming = String.valueOf(authority.getTitle());
            code = (code != null) ? naming + ':' + code : naming;
        }
        return code;
    }

    /**
     * Default to {@code null}.
     */
    @Override
    public String getParentIdentifier() {
        return null;
    }

    /**
     * Returns the NetCDF {@linkplain NetcdfFile#getLocation() file location},
     * or {@code null} if none.
     */
    @Override
    public String getDataSetUri() {
        return file.getLocation();
    }

    /**
     * Returns the NetCDF "{@code metadata_creation}" attribute value, or {@code null} if none.
     * This is the time when metadata have been created (not necessarily the time when data have
     * been collected).
     */
    @Override
    public Date getDateStamp() {
        return getDate("metadata_creation");
    }

    /**
     * Returns the NetCDF "{@code date_created}" attribute value, or {@code null} if none.
     */
    @Override
    public Date getDate() {
        return getDate("date_created");
    }

    /**
     * Encapsulates the {@linkplain #getDate() creation date}.
     */
    @Override
    public Collection<? extends CitationDate> getDates() {
        return self;
    }

    /**
     * Unconditionally returns {@link DateType#CREATION}, because the citation
     * encapsulated by this implementation is only for the creator. The contributors and
     * publishers are not supported by this simple implementation.
     */
    @Override
    public DateType getDateType() {
        return DateType.CREATION;
    }

    /**
     * Unconditionally returns {@link Role#ORIGINATOR}, because the citation
     * encapsulated by this implementation is only for the creator. The contributors and
     * publishers are not supported by this simple implementation.
     */
    @Override
    public Role getRole() {
        return Role.ORIGINATOR;
    }

    /**
     * Encapsulates the {@linkplain #getTitle() title}, {@linkplain #getCitedResponsibleParties()
     * creator} and {@linkplain #getDate() creation time}.
     *
     * @return {@code this}.
     */
    @Override
    public Citation getCitation() {
        return this;
    }

    /**
     * Encapsulates the {@linkplain #getIndividualName() creator} and
     * {@linkplain #getOrganisationName() institution}.
     */
    @Override
    public Collection<? extends ResponsibleParty> getCitedResponsibleParties() {
        return self;
    }

    /**
     * Returns the {@linkplain #getCitedResponsibleParties() responsible party} as
     * the default point of contact. The responsible party returned by this method
     * is associated to {@link Role#ORIGINATOR} instead than {@link Role#POINT_OF_CONTACT}
     * because this simple implementation can not be associated to more than one responsible
     * party. However library vendors are encouraged to provide more accurate implementations.
     */
    @Override
    public Collection<? extends ResponsibleParty> getPointOfContacts() {
        return getCitedResponsibleParties();
    }

    /**
     * Defaults to a synonymous for the {@linkplain #getPointOfContacts() point of contacts}
     * in this simple implementation. Note that in theory, those two methods are not strictly
     * synonymous since {@code getContacts()} shall return the contact for the <em>metadata</em>,
     * while {@code getPointOfContacts()} shall return the contact for the <em>data</em>. However
     * the attributes in NetCDF files usually don't make this distinction.
     */
    @Override
    public Collection<? extends ResponsibleParty> getContacts() {
        return getPointOfContacts();
    }

    /**
     * Defaults to {@code null}.
     */
    @Override
    public InternationalString getContactInstructions() {
        return null;
    }

    /**
     * Encapsulates the creator {@linkplain #getElectronicMailAddresses() email address}.
     *
     * @return {@code this}.
     */
    @Override
    public Contact getContactInfo() {
        return this;
    }

    /**
     * Returns the NetCDF "{@code creator_name}" attribute value, or {@code null} if none.
     */
    @Override
    public String getIndividualName() {
        return getString("creator_name");
    }

    /**
     * Returns the NetCDF "{@code institution}" attribute value, or {@code null} if none.
     */
    @Override
    public InternationalString getOrganisationName() {
        return getInternationalString("institution");
    }

    /**
     * Defaults to {@code null}.
     */
    @Override
    public InternationalString getPositionName() {
        return null;
    }

    /**
     * Defaults to {@code null}.
     */
    @Override
    public Telephone getPhone() {
        return null;
    }

    /**
     * Returns the NetCDF "{@code creator_email}" attribute value, or {@code null} if none.
     */
    @Override
    public Collection<String> getElectronicMailAddresses() {
        return singleton(getString("creator_email"));
    }

    /**
     * Encapsulates the creator {@linkplain #getElectronicMailAddresses() email address}.
     *
     * @return {@code this}.
     */
    @Override
    public Address getAddress() {
        return this;
    }

    /**
     * Defaults to an empty set.
     */
    @Override
    public Collection<String> getDeliveryPoints() {
        return Collections.emptySet();
    }

    /**
     * Defaults to {@code null}.
     */
    @Override
    public InternationalString getCity() {
        return null;
    }

    /**
     * Defaults to {@code null}.
     */
    @Override
    public InternationalString getAdministrativeArea() {
        return null;
    }

    /**
     * Defaults to {@code null}.
     */
    @Override
    public String getPostalCode() {
        return null;
    }

    /**
     * Defaults to {@code null}.
     */
    @Override
    public InternationalString getCountry() {
        return null;
    }

    /**
     * Defaults to {@code null}.
     */
    @Override
    public InternationalString getHoursOfService() {
        return null;
    }

    /**
     * Returns the NetCDF "{@code acknowledgment}" attribute value, or an empty set if none.
     */
    @Override
    public Collection<String> getCredits() {
        return singleton(getString("acknowledgment"));
    }

    /**
     * Defaults to {@code null}.
     */
    @Override
    public Locale getLanguage() {
        return null;
    }

    /**
     * Defaults to the {@linkplain #getLanguage() metadata language}.
     */
    @Override
    public Collection<Locale> getLanguages() {
        return singleton(getLanguage());
    }

    /**
     * Defaults to an empty set.
     */
    @Override
    public Collection<Locale> getLocales() {
        return Collections.emptySet();
    }

    /**
     * Defaults to {@code null}.
     */
    @Override
    public CharacterSet getCharacterSet() {
        return null;
    }

    /**
     * Defaults to the {@linkplain #getCharacterSet() character set}.
     */
    @Override
    public Collection<CharacterSet> getCharacterSets() {
        return singleton(getCharacterSet());
    }

    /**
     * Defaults to {@code null}.
     */
    @Override
    public OnlineResource getOnlineResource() {
        return null;
    }

    /**
     * Defaults to an empty set.
     */
    @Override
    public Collection<? extends Format> getResourceFormats() {
        return Collections.emptySet();
    }

    /**
     * Defaults to an empty set.
     */
    @Override
    public Collection<? extends Usage> getResourceSpecificUsages() {
        return Collections.emptySet();
    }

    /**
     * Defaults to an empty set.
     */
    @Override
    public Collection<? extends Constraints> getResourceConstraints() {
        return Collections.emptySet();
    }

    /**
     * Defaults to an empty set.
     */
    @Override
    public Collection<? extends MaintenanceInformation> getResourceMaintenances() {
        return Collections.emptySet();
    }

    /**
     * Defaults to an empty set.
     */
    @Override
    public Collection<Progress> getStatus() {
        return Collections.emptySet();
    }

    /**
     * Defaults to an empty set.
     */
    @Override
    public Collection<? extends DataQuality> getDataQualityInfo() {
        return Collections.emptySet();
    }

    /**
     * Defaults to an empty set.
     */
    @Override
    public Collection<? extends AcquisitionInformation> getAcquisitionInformation() {
        return Collections.emptySet();
    }

    /**
     * Defaults to {@code null}.
     */
    @Override
    public InternationalString getEnvironmentDescription() {
        return null;
    }

    /**
     * Defaults to an empty set.
     */
    @Override
    public Collection<? extends SpatialRepresentation> getSpatialRepresentationInfo() {
        return Collections.emptySet();
    }

    /**
     * Defaults to an empty set.
     */
    @Override
    public Collection<? extends ReferenceSystem> getReferenceSystemInfo() {
        return Collections.emptySet();
    }

    /**
     * Defaults to an empty set.
     */
    @Override
    public Collection<? extends Resolution> getSpatialResolutions() {
        return Collections.emptySet();
    }

    /**
     * Encapsulates the {@linkplain #getGeographicElements() geographic bounding box}.
     *
     * @return {@code this}.
     */
    @Override
    public Collection<? extends Extent> getExtents() {
        return self;
    }

    /**
     * Encapsulates the geographic bounding box.
     *
     * @return {@code this}.
     */
    @Override
    public Collection<? extends GeographicExtent> getGeographicElements() {
        return self;
    }

    /**
     * Defaults to an empty set.
     */
    @Override
    public Collection<? extends TemporalExtent> getTemporalElements() {
        return Collections.emptySet();
    }

    /**
     * Defaults to an empty set.
     */
    @Override
    public Collection<? extends VerticalExtent> getVerticalElements() {
        return Collections.emptySet();
    }

    /**
     * Returns the NetCDF "{@code geospatial_lon_min}" attribute value, or {@linkplain Double#NaN NaN} if none.
     */
    @Override
    public double getWestBoundLongitude() {
        return getDouble("geospatial_lon_min");
    }

    /**
     * Returns the NetCDF "{@code geospatial_lon_max}" attribute value, or {@linkplain Double#NaN NaN} if none.
     */
    @Override
    public double getEastBoundLongitude() {
        return getDouble("geospatial_lon_max");
    }

    /**
     * Returns the NetCDF "{@code geospatial_lat_min}" attribute value, or {@linkplain Double#NaN NaN} if none.
     */
    @Override
    public double getSouthBoundLatitude() {
        return getDouble("geospatial_lat_min");
    }

    /**
     * Returns the NetCDF "{@code geospatial_lat_max}" attribute value, or {@linkplain Double#NaN NaN} if none.
     */
    @Override
    public double getNorthBoundLatitude() {
        return getDouble("geospatial_lat_max");
    }

    /**
     * Returns {@link Boolean#TRUE} since the geographic bounding box is inclusive.
     */
    @Override
    public Boolean getInclusion() {
        return Boolean.TRUE;
    }

    /**
     * Defaults to {@code null}.
     */
    @Override
    public InternationalString getDescription() {
        return null;
    }

    /**
     * Defaults to an empty set.
     */
    @Override
    public Collection<PresentationForm> getPresentationForms() {
        return Collections.emptySet();
    }

    /**
     * Defaults to {@code null}.
     */
    @Override
    public InternationalString getEdition() {
        return null;
    }

    /**
     * Defaults to {@code null}.
     */
    @Override
    public Date getEditionDate() {
        return null;
    }

    /**
     * Defaults to {@code null}.
     */
    @Override
    public Series getSeries() {
        return null;
    }

    /**
     * Defaults to {@code null}.
     */
    @Override
    public String getISBN() {
        return null;
    }

    /**
     * Defaults to {@code null}.
     */
    @Override
    public String getISSN() {
        return null;
    }

    /**
     * Defaults to an empty set.
     */
    @Override
    public Collection<? extends ContentInformation> getContentInfo() {
        return Collections.emptySet();
    }

    /**
     * Defaults to {@code null}.
     */
    @Override
    public Distribution getDistributionInfo() {
        return null;
    }

    /**
     * Defaults to an empty set.
     */
    @Override
    public Collection<? extends PortrayalCatalogueReference> getPortrayalCatalogueInfo() {
        return Collections.emptySet();
    }

    /**
     * Defaults to an empty set.
     */
    @Override
    public Collection<? extends ApplicationSchemaInformation> getApplicationSchemaInfo() {
        return Collections.emptySet();
    }

    /**
     * Defaults to an empty set.
     */
    @Override
    public Collection<? extends AggregateInformation> getAggregationInfo() {
        return Collections.emptySet();
    }

    /**
     * Defaults to {@link ScopeCode.DATASET}.
     */
    @Override
    public Collection<ScopeCode> getHierarchyLevels() {
        return HIERARCHY_LEVEL;
    }

    /**
     * Defaults to an empty set.
     */
    @Override
    public Collection<String> getHierarchyLevelNames() {
        return Collections.emptySet();
    }

    /**
     * Defaults to {@code "ISO 19115-2 Geographic Information - Metadata Part 2 Extensions for imagery and gridded data"}.
     */
    @Override
    public String getMetadataStandardName() {
        return "ISO 19115-2 Geographic Information - Metadata Part 2 Extensions for imagery and gridded data";
    }

    /**
     * Defaults to {@code "ISO 19115-2:2009(E)"}.
     */
    @Override
    public String getMetadataStandardVersion() {
        return "ISO 19115-2:2009(E)";
    }

    /**
     * Defaults to {@code null}.
     */
    @Override
    public MaintenanceInformation getMetadataMaintenance() {
        return null;
    }

    /**
     * Defaults to an empty set.
     */
    @Override
    public Collection<? extends Constraints> getMetadataConstraints() {
        return Collections.emptySet();
    }

    /**
     * Defaults to an empty set.
     */
    @Override
    public Collection<? extends MetadataExtensionInformation> getMetadataExtensionInfo() {
        return Collections.emptySet();
    }
}
