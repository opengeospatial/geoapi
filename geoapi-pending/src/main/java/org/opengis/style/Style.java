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
package org.opengis.style;

import java.util.List;
import org.opengis.annotation.UML;

import org.opengis.annotation.XmlElement;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;

/**
 * <p>A UserStyle is at the same semantic level as a NamedStyle used in the context of a
 * WMS. In a sense, a named style can be thought of as a reference to a hidden UserStyle
 * that is stored inside of a map server.</p>
 *
 *
 * <p>A portrayal catalog consists of a set of feature portrayal objects. Many may
 * exist for each feature type that may occur in the dataset. each feature object
 * has assigned a set of portrayal rules.</p>
 *
 * This class is a merged between ISO 19117 Portrayal and OGC SLD 1.1.0
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/sld">Implementation specification 1.1.0</A>
 *
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.2
 */
@UML(identifier="PF_PortrayalCatalog", specification=ISO_19117)
@XmlElement("UserStyle")
public interface Style {

    /**
     * Style name (machine readable, don't show to users)
     *
     * @return identification name of this style
     */
    @XmlElement("UserStyle")
    String getName();

    /**
     * Returns the description of this style.
     *
     * @return description with usual informations used for user interfaces.
     */
    @XmlElement("Description")
    Description getDescription();

    /**
     * The IsDefault element identifies whether a style is the default style of a layer, for use in
     * SLD ‘library mode’ when rendering or for storing inside of a map server. IsDefault uses
     * “1” or “true” for true and “0” or “false” for false. The default value is “0”.
     */
    @XmlElement("IsDefault")
    boolean isDefault();

    /**
     * Returns a collection of feature type style.
     */
    @UML(identifier="featurePortrayal", obligation=MANDATORY, specification=ISO_19117)
    @XmlElement("FeatureTypeStyle")
    List<? extends FeatureTypeStyle> featureTypeStyles();

    /**
     * Returns the default specification used if no rule return true.
     * This specification should not use any external functions.
     * This specification should use at least one spatial attribute.
     */
    @UML(identifier="defaultPortrayalSpec", obligation=MANDATORY, specification=ISO_19117)
    Symbolizer getDefaultSpecification();

    /**
     * calls the visit method of a StyleVisitor
     *
     * @param visitor the style visitor
     */
    Object accept(StyleVisitor visitor, Object extraData);
}
