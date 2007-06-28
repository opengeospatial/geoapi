/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2004-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.distribution;

import java.util.Collection;
import org.opengis.util.InternationalString;
import org.opengis.annotation.UML;
import org.opengis.annotation.Profile;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;
import static org.opengis.annotation.ComplianceLevel.*;
import static org.opengis.annotation.Specification.*;


/**
 * Description of the computer language construct that specifies the representation
 * of data objects in a record, file, message, storage device or transmission channel.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/as#01-111">ISO 19115</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.0
 */
@Profile (level=CORE)
@UML(identifier="MD_Format", specification=ISO_19115)
public interface Format {
    /**
     * Name of the data transfer format(s).
     */
    @Profile (level=CORE)
    @UML(identifier="name", obligation=MANDATORY, specification=ISO_19115)
    InternationalString getName();

    /**
     * Version of the format (date, number, etc.).
     */
    @Profile (level=CORE)
    @UML(identifier="version", obligation=MANDATORY, specification=ISO_19115)
    InternationalString getVersion();

    /**
     * Amendment number of the format version.
     */
    @UML(identifier="amendmentNumber", obligation=OPTIONAL, specification=ISO_19115)
    InternationalString getAmendmentNumber();

    /**
     * Name of a subset, profile, or product specification of the format.
     */
    @UML(identifier="specification", obligation=OPTIONAL, specification=ISO_19115)
    InternationalString getSpecification();

    /**
     * Recommendations of algorithms or processes that can be applied to read or
     * expand resources to which compression techniques have been applied.
     */
    @UML(identifier="fileDecompressionTechnique", obligation=OPTIONAL, specification=ISO_19115)
    InternationalString getFileDecompressionTechnique();

    /**
     * Provides information about the distributor’s format.
     */
    @UML(identifier="formatDistributor", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends Distributor> getFormatDistributors();
}
