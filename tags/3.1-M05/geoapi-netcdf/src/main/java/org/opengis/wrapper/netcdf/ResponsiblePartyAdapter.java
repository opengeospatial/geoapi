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

import java.util.Collection;
import org.opengis.metadata.citation.Contact;
import org.opengis.metadata.citation.Individual;
import org.opengis.metadata.citation.Organisation;
import org.opengis.metadata.citation.Party;
import org.opengis.metadata.citation.Responsibility;
import org.opengis.metadata.citation.ResponsibleParty;
import org.opengis.metadata.citation.Role;
import org.opengis.metadata.extent.Extent;
import org.opengis.util.InternationalString;


/**
 * Adapter from ISO 19115:2014 {@link Responsibility} to legacy ISO 19115:2003 {@link ResponsibleParty}.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
final class ResponsiblePartyAdapter implements ResponsibleParty {
    /**
     * The responsibility to wrap.
     */
    final Responsibility responsibility;

    /**
     * Wraps the given responsibility.
     */
    ResponsiblePartyAdapter(final Responsibility responsibility) {
        this.responsibility = responsibility;
    }

    /**
     * Returns the name of the first party of kind {@link Organisation}.
     *
     * @return The first organisation name found, or {@code null} if none.
     */
    @Override
    @Deprecated
    public InternationalString getOrganisationName() {
        for (final Party party : getParties()) {
            if (party instanceof Organisation) {
                final InternationalString name = party.getName();
                if (name != null) {
                    return name;
                }
            }
        }
        return null;
    }

    /**
     * Returns the name of the first party of kind {@link Individual}.
     *
     * @return The first individual name found, or {@code null} if none.
     */
    @Override
    @Deprecated
    public String getIndividualName() {
        for (final Party party : getParties()) {
            if (party instanceof Individual) {
                final InternationalString name = party.getName();
                if (name != null) {
                    return name.toString();
                }
            }
        }
        return null;
    }

    /**
     * Returns the position of the individual returned by {@link #getIndividualName()}.
     *
     * @return Position of the first individual found, or {@code null} if none.
     */
    @Override
    @Deprecated
    public InternationalString getPositionName() {
        for (final Party party : getParties()) {
            if (party instanceof Individual) {
                if (party.getName() != null) { // For consistency with getIndividualName().
                    return ((Individual) party).getPositionName();
                }
            }
        }
        return null;
    }

    /**
     * Returns the first contact information found.
     *
     * @return The first contact information found, or {@code null} if none.
     */
    @Override
    @Deprecated
    public Contact getContactInfo() {
        for (final Party party : getParties()) {
            for (final Contact contact : party.getContactInfo()) {
                if (contact != null) { // Paranoiac check.
                    return contact;
                }
            }
        }
        return null;
    }

    /**
     * Delegates to {@link Responsibility#getRole()}.
     */
    @Override
    public Role getRole() {
        return responsibility.getRole();
    }

    /**
     * Delegates to {@link Responsibility#getExtents()}.
     */
    @Override
    public Collection<? extends Extent> getExtents() {
        return responsibility.getExtents();
    }

    /**
     * Delegates to {@link Responsibility#getParties()}.
     */
    @Override
    public Collection<? extends Party> getParties() {
        return responsibility.getParties();
    }
}
