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

import org.opengis.util.InternationalString;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * AttributeDefinition is used to define the formal parameters of external functions and the underlying
 * rendering operations of the portrayal service.
 *
 * @version <A HREF="http://www.isotc211.org">ISO 19117 Portrayal</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.2
 */
@UML(identifier="PF_AttributeDefinition", specification=ISO_19117)
public interface AttributeDefinition {
    /**
     * Returns the name of the attribute definition.
     * It shall be a legal and unique name for this function.
     */
    @UML(identifier="name", obligation=MANDATORY, specification=ISO_19117)
    String getName();

    /**
     * Returns a description of the usage of this attribute.
     * It is a human readable value.
     */
    @UML(identifier="description", obligation=OPTIONAL, specification=ISO_19117)
    InternationalString getDescription();

    /**
     * Returns the class type for this attribute.
     * It shall be a basic legal type.
     */
    @UML(identifier="type", obligation=MANDATORY, specification=ISO_19117)
    Class getReturnType();

    /**
     * Returns a Default Value for this attribut.
     * This value is optional.
     */
    @UML(identifier="defaultValue", obligation=OPTIONAL, specification=ISO_19117)
    Object getDefault();
}
