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

import java.io.File;
import java.util.Date;
import java.util.Locale;
import java.util.EnumSet;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Objects;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import ucar.nc2.NetcdfFile;
import ucar.nc2.Attribute;
import ucar.nc2.time.Calendar;
import ucar.nc2.time.CalendarDateFormatter;
import ucar.nc2.constants.ACDD;

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
import org.opengis.metadata.lineage.Lineage;
import org.opengis.referencing.ReferenceSystem;
import org.opengis.util.InternationalString;
import org.opengis.temporal.Duration;


/**
 * A {@link Metadata} implementation backed by a netCDF {@link NetcdfFile} object.
 * All getter methods fetch their values from the netCDF file, so change to the netCDF
 * file content will be immediately reflected in this class.
 *
 * <p>Unless otherwise noted in the javadoc, this implementation defines a one-to-one relationship
 * between the metadata attributes and netCDF attributes. This simple model allows us to implement
 * relevant interfaces in a single class. Note that this simplification may not be appropriate for
 * real world usages — the purpose of this class is mainly to provide a starting point for your
 * own implementations.</p>
 *
 * <p>Some interfaces implemented by this class or its component objects:</p>
 * <ul>
 *   <li>{@link Metadata} is the root interface.</li>
 *   <li>{@link Identifier} implements the value returned by {@link Citation#getIdentifiers()}.
 *          <ul><li>NetCDF attributes: {@code id}, {@code naming_authority}.
 *          </li></ul></li>
 *   <li>{@link DataIdentification} implements the value returned by {@link Metadata#getIdentificationInfo()}.
 *          <ul><li>NetCDF attributes: {@code summary}, {@code purpose}, {@code topic_category}, {@code cdm_data_type},
 *          {@code comment}, {@code acknowledgment}.
 *          </li></ul></li>
 *   <li>{@link CitationDate} implements the value returned by {@link Metadata#getDateInfo()}.
 *          <ul><li>NetCDF attributes: {@code metadata_creation}.
 *          </li></ul></li>
 *   <li>{@link Citation} implements the value returned by {@link Identification#getCitation()},
 *          which contain only the creator. This simple implementation ignores the publisher and contributors.
 *          <ul><li>NetCDF attributes: {@code title}.
 *          </li></ul></li>
 *   <li>{@link Responsibility} implements the value returned by {@link Citation#getCitedResponsibleParties()},
 *          which contain only the creator and the institution.
 *          <ul><li>NetCDF attributes: {@code creator_name}, {@code institution}.
 *          </li></ul></li>
 *   <li>{@link Individual} implements the value returned by {@link Responsibility#getParties()},
 *          which contain only the creator. This simple implementation ignores the publisher and contributors.
 *          <ul><li>NetCDF attributes: {@code creator_name}.
 *          </li></ul></li>
 *   <li>{@link Address} implements the value returned by {@link Contact#getAddress()}.
 *          <ul><li>NetCDF attributes: {@code creator_email}.
 *          </li></ul></li>
 *   <li>{@link GeographicBoundingBox} implements the value returned by {@link Extent#getGeographicElements()}.
 *          <ul><li>NetCDF attributes: {@code geospatial_lon_min}, {@code geospatial_lon_max},
 *          {@code geospatial_lat_min}, {@code geospatial_lat_max}.
 *          </li></ul></li>
 * </ul>
 *
 * <p><b>Javadoc convention:</b> all public non-deprecated methods in this class can be grouped in 3 categories,
 * identifiable by the first words in their Javadoc:</p>
 *
 * <ul>
 *   <li>Each method documented as “<cite><b>Returns</b> foo…</cite>” maps directly to a netCDF attribute
 *       or hard-coded non-empty value.</li>
 *   <li>Each method documented as “<cite><b>Encapsulates</b> foo…</cite>” returns an object (often {@code this})
 *       providing the getter methods for a group of attributes. This encapsulation is done for compliance with
 *       the ISO 19115 model.</li>
 *   <li>Each method documented as “<cite><b>Default to</b> foo…</cite>” is an unimplemented property.</li>
 * </ul>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 4.0
 * @since   3.1
 *
 * @see <a href="http://www.unidata.ucar.edu/software/thredds/current/netcdf-java/metadata/DataDiscoveryAttConvention.html">NetCDF Attribute Convention for Dataset Discovery</a>
 */
public class NetcdfMetadata implements Metadata, DataIdentification, Identifier, Citation, CitationDate,
        OnlineResource, Address, Extent, GeographicBoundingBox     // Do not implement Party because can be Individual or Organisation.
{
    /**
     * The netCDF file given to the constructor.
     */
    protected final NetcdfFile file;

    /**
     * Creates a new metadata object as a wrapper around the given netCDF file.
     *
     * @param file  the netCDF file.
     */
    public NetcdfMetadata(final NetcdfFile file) {
        Objects.requireNonNull(file);
        this.file = file;
    }

    /**
     * Returns {@code this} wrapped in a singleton.
     */
    private Collection<NetcdfMetadata> self() {
        return Collections.singleton(this);
    }

    /**
     * Returns {@code this} wrapped in a singleton if the given flag is {@code true}, or an empty set otherwise.
     */
    private Collection<NetcdfMetadata> self(final boolean flag) {
        return flag ? Collections.singleton(this) : Collections.<NetcdfMetadata>emptySet();
    }

    /**
     * Returns {@code true} if the netCDF file contains an attribute of the given name.
     */
    private boolean hasAttribute(final String name) {
        return file.findGlobalAttributeIgnoreCase(name) != null;
    }

    /**
     * Returns the value of the given attribute as a string. The attribute name is case-insensitive.
     * If non-null, the returned string is {@linkplain String#trim() trimmed} and never
     * {@linkplain String#isEmpty() empty}.
     *
     * @param  name  the case-insensitive attribute name.
     * @return the non-empty attribute value, or {@code null} if none.
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
     * Returns the value of the given attribute as an upper case string.
     * This method invokes {@link #getString(String)}, then change the case of the result (if non null).
     *
     * @param  name  the case-insensitive attribute name.
     * @return the non-empty attribute value in upper-case, or {@code null} if none.
     */
    private String getUpperCase(final String name) {
        final String value = getString(name);
        return (value != null) ? value.toUpperCase() : null;
    }

    /**
     * Returns the value of the given attribute as an international string.
     * This method invokes {@link #getString(String)}, then wraps the result
     * (if non null) in an {@link InternationalString} implementation.
     *
     * @param  name  the case-insensitive attribute name.
     * @return the non-empty attribute value, or {@code null} if none.
     */
    private InternationalString getInternationalString(final String name) {
        final String value = getString(name);
        return (value != null) ? new SimpleCitation(value) : null;
    }

    /**
     * Returns the value of the given attribute as a floating point value.
     *
     * @param  name  the case-insensitive attribute name.
     * @return the attribute value, or {@code NaN} if none.
     * @throws NumberFormatException if the number can not be parsed.
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
     * Returns the value of the given attribute as a date.
     * This method invokes {@link #getString(String)}, then parses the value.
     *
     * @param  name  the case-insensitive attribute name.
     * @return the attribute value, or {@code null} if none or can not be parsed.
     */
    private Date getDate(final String name) {
        final String value = getString(name);
        if (value != null) {
            return parseDate(value);
        }
        return null;
    }

    /**
     * Parses the given ISO date, assuming proleptic Gregorian calendar and UTC time zone.
     *
     * @param  value  the date in ISO format.
     * @return the parsed date.
     * @throws IllegalArgumentException if the given date can not be parsed.
     */
    private static Date parseDate(final String value) throws IllegalArgumentException {
        return new Date(CalendarDateFormatter.isoStringToCalendarDate(Calendar.proleptic_gregorian, value).getMillis());
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
     * Returns the first item in the given collection, or {@code null} if none.
     */
    private static <T> T first(final Collection<? extends T> values) {
        final Iterator<? extends T> it = values.iterator();
        return it.hasNext() ? it.next() : null;
    }




    // ┌─────────────────────────────────────────────────────────────────────────────────────────┐
    // │    Methods that return directly an attribute value                                      │
    // └─────────────────────────────────────────────────────────────────────────────────────────┘

    /**
     * Encapsulates the {@linkplain #getCodeSpace() code space} in a citation.
     *
     * @return the authority for the dataset described by this metadata, or {@code null}.
     */
    @Override
    public Citation getAuthority() {
        final String naming = getCodeSpace();
        return (naming != null) ? new SimpleCitation(naming) : null;
    }

    /**
     * Returns the netCDF {@value ACDD#naming_authority} attribute value, or {@code null} if none.
     *
     * @see #getMetadataIdentifier()
     */
    @Override
    public String getCodeSpace() {
        return getString(ACDD.naming_authority);
    }

    /**
     * Returns the netCDF {@value ACDD#id} attribute value,
     * or the {@linkplain NetcdfFile#getId() file identifier},
     * or {@code null} if none.
     *
     * @see NetcdfFile#getId()
     * @see #getMetadataIdentifier()
     */
    @Override
    public String getCode() {
        final String id = getString(ACDD.id);
        return (id != null) ? id : file.getId();
    }

    /**
     * Returns the name of the resource, as the filename without path and suffix.
     *
     * @return the name of the resource, or {@code null} if none.
     */
    @Override
    public InternationalString getName() {
        String name = file.getLocation();
        if (name == null) {
            return null;
        }
        int end = name.lastIndexOf('.');
        if (end < 0) end = name.length();
        final int start = name.lastIndexOf('/', end);
        if (start >= 0) {
            name = name.substring(start + 1, end);
        } else {
            name = new File(name.substring(0, end)).getName();
        }
        return new SimpleCitation(name.replace('_', ' '));
    }

    /**
     * Returns the netCDF {@value ACDD#title} attribute value, or the
     * {@linkplain NetcdfFile#getTitle() file title}, or {@code null} if none.
     *
     * @return the title, or {@code null} if none.
     *
     * @see NetcdfFile#getTitle()
     */
    @Override
    public InternationalString getTitle() {
        String title = getString(ACDD.title);
        if (title == null) {
            title = file.getTitle();
            if (title == null) {
                return null;
            }
        }
        return new SimpleCitation(title);
    }

    /**
     * Returns the netCDF {@value ACDD#summary} attribute value, or {@code null} if none.
     */
    @Override
    public InternationalString getAbstract() {
        return getInternationalString(ACDD.summary);
    }

    /**
     * Returns the netCDF {@code "purpose"} attribute value, or {@code null} if none.
     */
    @Override
    public InternationalString getPurpose() {
        return getInternationalString("purpose");
    }

    /**
     * Returns the netCDF {@code "topic_category"} attribute value, or an empty set if none.
     */
    @Override
    public Collection<TopicCategory> getTopicCategories() {
        final String value = getUpperCase("topic_category");
        if (value == null) return Collections.emptySet();
        final EnumSet<TopicCategory> categories = EnumSet.noneOf(TopicCategory.class);
        for (final String element : value.split(",")) {
            categories.add(TopicCategory.valueOf(element.replace(' ', '_').trim()));
        }
        return categories;
    }

    /**
     * Returns the netCDF {@value ACDD#cdm_data_type} attribute value, or an empty set if none.
     */
    @Override
    public Collection<SpatialRepresentationType> getSpatialRepresentationTypes() {
        return singleton(SpatialRepresentationType.valueOf(getUpperCase(ACDD.cdm_data_type)));
    }

    /**
     * Returns the netCDF {@value ACDD#creator_email} attribute value, or {@code null} if none.
     */
    @Override
    public Collection<String> getElectronicMailAddresses() {
        return singleton(getString(ACDD.creator_email));
    }

    /**
     * Returns the netCDF {@code "acknowledgment"} attribute value, or an empty set if none.
     */
    @Override
    public Collection<InternationalString> getCredits() {
        InternationalString value = getInternationalString("acknowledgment");
        if (value == null) {
            value = getInternationalString(ACDD.acknowledgement);
        }
        return singleton(value);
    }

    /**
     * Returns {@code true} if this metadata has a date.
     */
    private boolean hasDate(final boolean data) {
        return hasAttribute(data ? ACDD.date_created : "metadata_creation");
    }

    /**
     * Encapsulates the netCDF {@code "metadata_creation"} attribute value, or {@code null} if none.
     * This is the time when metadata have been created (not necessarily the time when data have been collected).
     */
    @Override
    public Collection<? extends CitationDate> getDateInfo() {
        if (hasDate(false)) {
            return Collections.singleton(new CitationDate() {
                @Override public Date getDate() {
                    return NetcdfMetadata.this.getDate("metadata_creation");
                }
                @Override public DateType getDateType() {
                    return DateType.CREATION;
                }
            });
        } else {
            return Collections.emptySet();
        }
    }

    /**
     * Encapsulates the netCDF {@value ACDD#date_created} attribute value.
     */
    @Override
    public Collection<? extends CitationDate> getDates() {
        return self(hasDate(true));
    }

    /**
     * Returns the netCDF {@value ACDD#date_created} attribute value, or {@code null} if none.
     * This is the creation date of the actual dataset, not necessarily the same that the
     * metadata creation time.
     *
     * @return the creation date, or {@code null} if none.
     */
    @Override
    public Date getDate() {
        return getDate(ACDD.date_created);
    }

    /**
     * Returns {@link DateType#CREATION}.
     * Note that the citation encapsulated by this implementation is only for the creator.
     * The contributors and publishers are not supported by this simple implementation.
     */
    @Override
    public DateType getDateType() {
        return DateType.CREATION;
    }

    /**
     * Returns the netCDF {@value ACDD#LON_MIN} attribute value, or {@linkplain Double#NaN NaN} if none.
     */
    @Override
    public double getWestBoundLongitude() {
        return getDouble(ACDD.LON_MIN);
    }

    /**
     * Returns the netCDF {@value ACDD#LON_MAX} attribute value, or {@linkplain Double#NaN NaN} if none.
     */
    @Override
    public double getEastBoundLongitude() {
        return getDouble(ACDD.LON_MAX);
    }

    /**
     * Returns the netCDF {@code ACDD#LAT_MIN} attribute value, or {@linkplain Double#NaN NaN} if none.
     */
    @Override
    public double getSouthBoundLatitude() {
        return getDouble(ACDD.LAT_MIN);
    }

    /**
     * Returns the netCDF {@value ACDD#LAT_MAX} attribute value, or {@linkplain Double#NaN NaN} if none.
     */
    @Override
    public double getNorthBoundLatitude() {
        return getDouble(ACDD.LAT_MAX);
    }

    /**
     * Returns {@link Boolean#TRUE} since the geographic bounding box is inclusive.
     */
    @Override
    public Boolean getInclusion() {
        return Boolean.TRUE;
    }

    /**
     * Encapsulates the netCDF {@linkplain NetcdfFile#getLocation() file location}.
     */
    @Override
    public Collection<? extends OnlineResource> getOnlineResources() {
        return self(file.getLocation() != null);
    }

    /**
     * Returns the netCDF {@linkplain NetcdfFile#getLocation() file location}, or {@code null} if none.
     *
     * @return the file path, or {@code null} if none.
     */
    @Override
    public URI getLinkage() {
        final String location = file.getLocation();
        if (location != null) try {
            return new URI(location);
        } catch (URISyntaxException e) {
            throw new IllegalStateException(e);
        }
        return null;
    }

    /**
     * Returns the {@code getLinkage()} purpose, which is to access the file.
     *
     * @return {@link OnLineFunction#FILE_ACCESS}.
     */
    @Override
    public OnLineFunction getFunction() {
        return OnLineFunction.FILE_ACCESS;
    }

    /**
     * Returns the netCDF {@value ACDD#comment} attribute value, or {@code null} if none.
     */
    @Override
    public InternationalString getSupplementalInformation() {
        return getInternationalString(ACDD.comment);
    }

    /**
     * Defaults to {@code "ISO 19115-2 Geographic Information - Metadata Part 2 Extensions for imagery and gridded data"}
     * with {@code "ISO 19115-2:2009(E)"} edition.
     */
    @Override
    public Collection<? extends Citation> getMetadataStandards() {
        return Collections.singleton(SimpleCitation.ISO_19115);
    }

    /**
     * Encapsulates {@link ScopeCode#DATASET}.
     */
    @Override
    public Collection<? extends MetadataScope> getMetadataScopes() {
        return Collections.singleton(new MetadataScope() {
            @Override public ScopeCode getResourceScope() {return ScopeCode.DATASET;}
            @Override public InternationalString getName() {return null;}
        });
    }




    // ┌─────────────────────────────────────────────────────────────────────────────────────────┐
    // │    Indirection levels to the above attributes                                           │
    // └─────────────────────────────────────────────────────────────────────────────────────────┘

    /**
     * Returns {@code null} by default.
     * Metadata identifier should not be confused with data identifier.
     *
     * @return the authority and the code for this metadata.
     */
    @Override
    public Identifier getMetadataIdentifier() {
        return null;
    }

    /**
     * Encapsulates the {@linkplain #getAuthority() naming authority} together with the
     * {@linkplain #getCode() identifier code} for this data.
     *
     * @return the authority and the code for this data.
     *
     * @see #getAuthority()
     * @see #getCode()
     */
    @Override
    public Collection<? extends Identifier> getIdentifiers() {
        return self();
    }

    /**
     * Encapsulates the data identifiers,
     * {@linkplain #getCitation() creator} and other information.
     */
    @Override
    public Collection<? extends Identification> getIdentificationInfo() {
        return self();
    }

    /**
     * Encapsulates the {@linkplain #getTitle() title}, {@linkplain #getPointOfContacts() creator}
     * and {@linkplain #getDatasetDate() data creation time}.
     *
     * @return {@code this}.
     */
    @Override
    public Citation getCitation() {
        return this;
    }

    /**
     * Encapsulates the role, name, contact and position information for individuals or organisations.
     */
    @Override
    public Collection<? extends Responsibility> getCitedResponsibleParties() {
        return Collections.singleton(new Creator());
    }

    /**
     * Encapsulates the {@linkplain #getName() creator name} and
     * {@linkplain #getElectronicMailAddresses() email address}.
     * The responsible party represented by this class is associated to {@link Role#ORIGINATOR} instead than
     * {@link Role#POINT_OF_CONTACT} because this simple implementation can not be associated to more than
     * one responsible party. However library vendors are encouraged to provide more accurate implementations.
     *
     * <p>Defined in a separated class because of methods clash:</p>
     * <ul>
     *   <li>{@link Party#getName()} not the same name than {@link OnlineResource#getName()}.</li>
     *   <li>{@link Contact#getOnlineResources()} not the same link than {@link Citation#getOnlineResources()}.</li>
     *   <li>{@link Responsibility#getExtents()} not the same than {@link DataIdentification#getExtents()}.</li>
     * </ul>
     */
    private final class Creator implements Responsibility, Individual, Contact {
        @Override public Collection<? extends Extent>         getExtents()                 {return Collections.emptySet();}
        @Override public Collection<? extends OnlineResource> getOnlineResources()         {return Collections.emptySet();}
        @Override public Collection<? extends Address>        getAddresses()               {return self(hasAttribute(ACDD.creator_email));}
        @Override public InternationalString                  getPositionName()            {return null;}
        @Override public InternationalString                  getContactType()             {return null;}
        @Override public InternationalString                  getContactInstructions()     {return null;}
        @Override public Collection<Telephone>                getPhones()                  {return Collections.emptySet();}
        @Override public Collection<InternationalString>      getHoursOfService()          {return Collections.emptySet();}
        @Override @Deprecated public Address                  getAddress()                 {return NetcdfMetadata.this;}
        @Override @Deprecated public Telephone                getPhone()                   {return null;}
        @Override @Deprecated public OnlineResource           getOnlineResource()          {return null;}

        /**
         * Returns a collection containing {@code this} if the given attribute is presents, or an empty set otherwise.
         */
        private Collection<Creator> ifAttributePresents(final String name) {
            return hasAttribute(name) ? Collections.<Creator>singleton(this) : Collections.<Creator>emptySet();
        }

        /**
         * Returns {@link Role#ORIGINATOR}, because the citation encapsulated by this implementation is only
         * for the creator. The contributors and publishers are not supported by this simple implementation.
         */
        @Override
        public Role getRole() {
            return Role.ORIGINATOR;
        }

        /**
         * Encapsulates the {@linkplain #getName() creator} name and email address.
         */
        @Override
        public Collection<? extends Party> getParties() {
            final Party party;
            if (hasAttribute("institution")) {
                party = new Organisation() {
                    @Override public InternationalString                 getName()        {return getInternationalString("institution");}
                    @Override public Collection<? extends BrowseGraphic> getLogo()        {return Collections.emptySet();}
                    @Override public Collection<? extends Individual>    getIndividual()  {return ifAttributePresents(ACDD.creator_name);}
                    @Override public Collection<? extends Contact>       getContactInfo() {
                        if (hasAttribute(ACDD.creator_name)) {
                            return Collections.emptySet();          // Email address to be provided with the individual instead.
                        } else {
                            return Creator.this.getContactInfo();
                        }
                    }
                };
            } else {
                party = this;                           // Individual only.
            }
            return Collections.singleton(party);
        }

        /**
         * Returns the netCDF {@value ACDD#creator_name} attribute value, or {@code null} if none.
         */
        @Override
        public InternationalString getName() {
            return getInternationalString(ACDD.creator_name);
        }

        /**
         * Encapsulates the creator {@linkplain #getElectronicMailAddresses() email address}.
         */
        @Override
        public Collection<? extends Contact> getContactInfo() {
            return ifAttributePresents(ACDD.creator_email);
        }
    }

    /**
     * Defaults to an empty set.
     * For the dataset creator, see {@link #getCitation()} instead.
     */
    @Override
    public Collection<? extends Responsibility> getPointOfContacts() {
        return Collections.emptySet();
    }

    /**
     * Defaults to a synonymous for the {@linkplain #getPointOfContacts() point of contacts}
     * in this simple implementation. Note that in theory, those two methods are not strictly
     * synonymous since {@code getContacts()} shall return the contact for the <em>metadata</em>,
     * while {@code getPointOfContacts()} shall return the contact for the <em>data</em>.
     * However the attributes in netCDF files usually don't make this distinction.
     */
    @Override
    public Collection<? extends Responsibility> getContacts() {
        return getPointOfContacts();
    }

    /**
     * Encapsulates the {@linkplain #getGeographicElements() geographic bounding box}.
     */
    @Override
    public Collection<? extends Extent> getExtents() {
        return self(hasAttribute(ACDD.LON_MIN) || hasAttribute(ACDD.LON_MAX)
                 || hasAttribute(ACDD.LAT_MIN) || hasAttribute(ACDD.LAT_MAX));
    }

    /**
     * Encapsulates the geographic bounding box.
     */
    @Override
    public Collection<? extends GeographicExtent> getGeographicElements() {
        return self();
    }




    // ┌─────────────────────────────────────────────────────────────────────────────────────────┐
    // │    Non-implemented methods                                                              │
    // └─────────────────────────────────────────────────────────────────────────────────────────┘

    /** Defaults to {@code null}. */ @Override public String                                   getVersion()                       {return null;}
    /** Defaults to {@code null}. */ @Override public Citation                                 getParentMetadata()                {return null;}
    /** Defaults to {@code null}. */ @Override public InternationalString                      getDescription()                   {return null;}
    /** Defaults to {@code null}. */ @Override public InternationalString                      getEnvironmentDescription()        {return null;}
    /** Defaults to {@code null}. */ @Override public Identifier                               getProcessingLevel()               {return null;}
    /** Defaults to an empty set. */ @Override public Collection<Keywords>                     getDescriptiveKeywords()           {return Collections.emptySet();}
    /** Defaults to an empty set. */ @Override public Collection<BrowseGraphic>                getGraphicOverviews()              {return Collections.emptySet();}
    /** Defaults to an empty set. */ @Override public Collection<ContentInformation>           getContentInfo()                   {return Collections.emptySet();}
    /** Defaults to an empty set. */ @Override public Collection<SpatialRepresentation>        getSpatialRepresentationInfo()     {return Collections.emptySet();}
    /** Defaults to an empty set. */ @Override public Collection<ReferenceSystem>              getReferenceSystemInfo()           {return Collections.emptySet();}
    /** Defaults to an empty set. */ @Override public Collection<TemporalExtent>               getTemporalElements()              {return Collections.emptySet();}
    /** Defaults to an empty set. */ @Override public Collection<VerticalExtent>               getVerticalElements()              {return Collections.emptySet();}
    /** Defaults to an empty set. */ @Override public Collection<Resolution>                   getSpatialResolutions()            {return Collections.emptySet();}
    /** Defaults to an empty set. */ @Override public Collection<Duration>                     getTemporalResolutions()           {return Collections.emptySet();}
    /** Defaults to an empty set. */ @Override public Collection<Locale>                       getLanguages()                     {return Collections.emptySet();}
    /** Defaults to an empty set. */ @Override public Collection<Charset>                      getCharacterSets()                 {return Collections.emptySet();}
    /** Defaults to an empty set. */ @Override public Collection<Format>                       getResourceFormats()               {return Collections.emptySet();}
    /** Defaults to an empty set. */ @Override public Collection<Usage>                        getResourceSpecificUsages()        {return Collections.emptySet();}
    /** Defaults to an empty set. */ @Override public Collection<Constraints>                  getResourceConstraints()           {return Collections.emptySet();}
    /** Defaults to an empty set. */ @Override public Collection<Constraints>                  getMetadataConstraints()           {return Collections.emptySet();}
    /** Defaults to an empty set. */ @Override public Collection<MaintenanceInformation>       getResourceMaintenances()          {return Collections.emptySet();}
    /** Defaults to {@code null}. */ @Override public MaintenanceInformation                   getMetadataMaintenance()           {return null;}
    /** Defaults to an empty set. */ @Override public Collection<Lineage>                      getResourceLineages()              {return Collections.emptySet();}
    /** Defaults to an empty set. */ @Override public Collection<AcquisitionInformation>       getAcquisitionInformation()        {return Collections.emptySet();}
    /** Defaults to an empty set. */ @Override public Collection<DataQuality>                  getDataQualityInfo()               {return Collections.emptySet();}
    /** Defaults to an empty set. */ @Override public Collection<Progress>                     getStatus()                        {return Collections.emptySet();}
    /** Defaults to an empty set. */ @Override public Collection<OnlineResource>               getMetadataLinkages()              {return Collections.emptySet();}
    /** Defaults to an empty set. */ @Override public Collection<Citation>                     getMetadataProfiles()              {return Collections.emptySet();}
    /** Defaults to an empty set. */ @Override public Collection<ApplicationSchemaInformation> getApplicationSchemaInfo()         {return Collections.emptySet();}
    /** Defaults to an empty set. */ @Override public Collection<AssociatedResource>           getAssociatedResources()           {return Collections.emptySet();}
    /** Defaults to an empty set. */ @Override public Collection<MetadataExtensionInformation> getMetadataExtensionInfo()         {return Collections.emptySet();}
    /** Defaults to an empty set. */ @Override public Collection<Citation>                     getAdditionalDocumentations()      {return Collections.emptySet();}
    /** Defaults to an empty set. */ @Override public Collection<Citation>                     getAlternativeMetadataReferences() {return Collections.emptySet();}
    /** Defaults to an empty set. */ @Override public Collection<PortrayalCatalogueReference>  getPortrayalCatalogueInfo()        {return Collections.emptySet();}
    /** Defaults to an empty set. */ @Override public Collection<Distribution>                 getDistributionInfo()              {return Collections.emptySet();}
    /** Defaults to an empty set. */ @Override public Collection<InternationalString>          getDeliveryPoints()                {return Collections.emptySet();}
    /** Defaults to {@code null}. */ @Override public InternationalString                      getCity()                          {return null;}
    /** Defaults to {@code null}. */ @Override public InternationalString                      getAdministrativeArea()            {return null;}
    /** Defaults to {@code null}. */ @Override public String                                   getPostalCode()                    {return null;}
    /** Defaults to {@code null}. */ @Override public InternationalString                      getCountry()                       {return null;}
    /** Defaults to an empty set. */ @Override public Collection<InternationalString>          getAlternateTitles()               {return Collections.emptySet();}
    /** Defaults to an empty set. */ @Override public Collection<PresentationForm>             getPresentationForms()             {return Collections.emptySet();}
    /** Defaults to an empty set. */ @Override public Collection<BrowseGraphic>                getGraphics()                      {return Collections.emptySet();}
    /** Defaults to an empty set. */ @Override public Collection<InternationalString>          getOtherCitationDetails()          {return Collections.emptySet();}
    /** Defaults to {@code null}. */ @Override @Deprecated public InternationalString          getCollectiveTitle()               {return null;}
    /** Defaults to {@code null}. */ @Override public Series                                   getSeries()                        {return null;}
    /** Defaults to {@code null}. */ @Override public InternationalString                      getEdition()                       {return null;}
    /** Defaults to {@code null}. */ @Override public Date                                     getEditionDate()                   {return null;}
    /** Defaults to {@code null}. */ @Override public String                                   getISBN()                          {return null;}
    /** Defaults to {@code null}. */ @Override public String                                   getISSN()                          {return null;}
    /** Defaults to {@code null}. */ @Override public String                                   getProtocol()                      {return null;}
    /** Defaults to {@code null}. */ @Override public String                                   getApplicationProfile()            {return null;}
    /** Defaults to {@code null}. */ @Override public String                                   getProtocolRequest()               {return null;}




    // ┌─────────────────────────────────────────────────────────────────────────────────────────┐
    // │    Deprecated methods                                                                   │
    // └─────────────────────────────────────────────────────────────────────────────────────────┘

    /**
     * @deprecated Replaced by {@link #getMetadataIdentifier()}.
     */
    @Override
    @Deprecated
    public String getFileIdentifier() {
        return toString();
    }

    /**
     * @deprecated Replaced by {@link #getParentMetadata()}.
     */
    @Override
    @Deprecated
    public String getParentIdentifier() {
        return null;
    }

    /**
     * @deprecated Replaced by {@link #getLinkage()}.
     */
    @Override
    @Deprecated
    public String getDataSetUri() {
        final URI uri = getLinkage();
        return (uri != null) ? uri.toString() : null;
    }

    /**
     * @deprecated Replaced by {@link #getDate()}.
     */
    @Override
    @Deprecated
    public Date getDateStamp() {
        return getDate();
    }

    /**
     * @deprecated Replaced by {@link #getLanguages()}.
     */
    @Override
    @Deprecated
    public Locale getLanguage() {
        return first(getLanguages());
    }

    /**
     * Defaults to an empty set.
     */
    @Override
    @Deprecated
    public Collection<Locale> getLocales() {
        return Collections.emptySet();
    }

    /**
     * @deprecated Replaced by {@link #getCharacterSets()}.
     */
    @Override
    @Deprecated
    public CharacterSet getCharacterSet() {
        return null;
    }

    /**
     * @deprecated As of ISO 19115:2014, replaced by {@link #getAssociatedResources()}.
     */
    @Override
    @Deprecated
    public Collection<? extends AggregateInformation> getAggregationInfo() {
        return Collections.emptySet();
    }

    /**
     * Defaults to {@link ScopeCode#DATASET}.
     */
    @Override
    @Deprecated
    public Collection<ScopeCode> getHierarchyLevels() {
        return Collections.singleton(ScopeCode.DATASET);
    }

    /**
     * Defaults to an empty set.
     */
    @Override
    @Deprecated
    public Collection<String> getHierarchyLevelNames() {
        return Collections.emptySet();
    }

    /**
     * @deprecated As of ISO 19115:2014, Replaced by {@link #getMetadataStandards()}.
     */
    @Override
    @Deprecated
    public String getMetadataStandardName() {
        return first(getMetadataStandards()).getTitle().toString();
    }

    /**
     * @deprecated As of ISO 19115:2014, Replaced by {@link #getMetadataStandards()}.
     */
    @Override
    @Deprecated
    public String getMetadataStandardVersion() {
        return first(getMetadataStandards()).getEdition().toString();
    }

    /**
     * Returns the concatenation of {@linkplain #getAuthority() naming authority},
     * the {@code ':'} character and the {@linkplain #getCode() identifier code}.
     * One or both of the authority and the code can be null.
     *
     * @return the identifier code in the naming authority space, or {@code null} if null.
     */
    @Override
    public String toString() {
        final String code = getCode();
        final String codeSpace = getCodeSpace();
        if (codeSpace == null) {
            return code;
        }
        return (code != null) ? codeSpace + ':' + code : codeSpace;
    }
}
