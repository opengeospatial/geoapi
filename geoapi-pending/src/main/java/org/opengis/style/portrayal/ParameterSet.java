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
 * <p>A parameter set holds a list of actualparameter values. The actual values shall
 * correspond to the formal parameters associated with the portrayal operation.
 * ParameterSet has a label which is referred from the portrayal catalogue.
 * </p>
 *
 * <p>
 * parameterset objects are used to predefine specific portrayal operations.
 * The label could for example be "thick_red_line", where the parameter values are
 * colour = RED, thickness = 5, brush= SOLID.
 * </p>
 *
 * @version <A HREF="http://www.isotc211.org">ISO 19117 Portrayal</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.2
 */
@UML(identifier="PF_ParameterSet", specification=ISO_19117)
public interface ParameterSet extends Collection<AttributeValue>{
    /**
     * Label used for this parameter set.
     * This is a short human readable value.
     */
    @UML(identifier="label", obligation=OPTIONAL, specification=ISO_19117)
    InternationalString getLabel();

    /**
     * Returns a description of the parameter set.
     * It is a human readable value.
     */
    @UML(identifier="description", obligation=OPTIONAL, specification=ISO_19117)
    InternationalString getDescription();

}
