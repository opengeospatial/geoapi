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
package org.opengis.metadata.content;

import java.util.Collection;

import org.opengis.annotation.UML;
import org.opengis.util.InternationalString;
import org.opengis.util.Record;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Description of specific range elements.
 *
 * @author Cédric Briançon (Geomatys)
 *
 * @since GeoAPI 2.3
 */
@UML(identifier="MI_RangeElementDescription", specification=ISO_19115_2)
public interface RangeElementDescription {
    /**
     * Designation associated with a set of range elements.
     *
     * @return Designation associated with a set of range elements.
     */
    @UML(identifier="name", obligation=MANDATORY, specification=ISO_19115_2)
    InternationalString getName();

    /**
     * Description of a set of specific range elements.
     *
     * @return Description of a set of specific range elements.
     */
    @UML(identifier="definition", obligation=MANDATORY, specification=ISO_19115_2)
    InternationalString getDefinition();

    /**
     * Specific range elements, i.e. range elements associated with a name and their definition.
     *
     * @return Specific range elements.
     */
    @UML(identifier="rangeElement", obligation=MANDATORY, specification=ISO_19115_2)
    Collection<? extends Record> getRangeElements();
}
