/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2008-2019 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Permission to use, copy, and modify this software and its documentation, with
 *    or without modification, for any purpose and without fee or royalty is hereby
 *    granted, provided that you include the following on ALL copies of the software
 *    and documentation or portions thereof, including modifications, that you make:
 *
 *    1. The full text of this NOTICE in a location viewable to users of the
 *       redistributed or derivative work.
 *    2. Notice of any changes or modifications to the OGC files, including the
 *       date changes were made.
 *
 *    THIS SOFTWARE AND DOCUMENTATION IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE
 *    NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *    TO, WARRANTIES OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT
 *    THE USE OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
 *    PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
 *
 *    COPYRIGHT HOLDERS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL OR
 *    CONSEQUENTIAL DAMAGES ARISING OUT OF ANY USE OF THE SOFTWARE OR DOCUMENTATION.
 *
 *    The name and trademarks of copyright holders may NOT be used in advertising or
 *    publicity pertaining to the software without specific, written prior permission.
 *    Title to copyright in this software and any associated documentation will at all
 *    times remain with copyright holders.
 */
package org.opengis.style.portrayal;

import java.util.Collection;
import org.opengis.util.InternationalString;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;

/**
 * A portrayal operation holds the details for a particular portrayal operation.
 * It declares a set of formal parameters that are need when invoking the
 * underlying rendering functions.
 *
 * <p>They should be one instance of portrayal specification class for each operation
 * defined by the portrayal service.</p>
 *
 * @version <A HREF="http://www.isotc211.org">ISO 19117 Portrayal</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.2
 */
@UML(identifier="PF_PortrayalOperation", specification=ISO_19117)
public interface PortrayalOperation {
    /**
     * Returns the name of the operation.
     */
    @UML(identifier="name", obligation=MANDATORY, specification=ISO_19117)
    String getName();

    /**
     * Returns a description of the operation.
     * It is a human readable value.
     */
    @UML(identifier="description", obligation=OPTIONAL, specification=ISO_19117)
    InternationalString getDescription();

    /**
     * Returns the list of External functions.
     */
    @UML(identifier="externalFunction", obligation=OPTIONAL, specification=ISO_19117)
    Collection<ExternalFunction> getExternalFunctions();

    /**
     * Returns a list of attributDefinition used by this operation.
     */
    @UML(identifier="formalParameter{ordered}", obligation=MANDATORY, specification=ISO_19117)
    Collection<AttributeDefinition> getFormalParameters();

    /**
     * Parameterset to use.
     *
     * Those parameter are given when we invoke a
     * portrayal operation, depending on the rendering device, this may result
     * on a return value or not.
     *
     * <p><b>Caution</b> This method may change!</p>
     */
    void portray(ParameterSet parameters);
}
