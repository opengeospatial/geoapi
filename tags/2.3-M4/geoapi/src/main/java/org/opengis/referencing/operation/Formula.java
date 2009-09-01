/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2009 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.referencing.operation;

import org.opengis.metadata.citation.Citation;
import org.opengis.util.InternationalString;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Specification of the coordinate operation method formula.
 *
 * @version <A HREF="http://portal.opengeospatial.org/files/?artifact_id=6716">Abstract specification 2.0</A>
 * @author  Martin Desruisseaux (Geomatys)
 * @since   GeoAPI 2.3
 */
@UML(identifier="CC_Formula", specification=ISO_19111)
public interface Formula {
    /**
     * Formula(s) or procedure used by the operation method.
     *
     * @return The formula used by the operation method, or {@code null} if none.
     *
     * @condition Only one of {@code getFormula()} and {@code getCitation()} shall be supplied.
     */
    @UML(identifier="formula", obligation=CONDITIONAL, specification=ISO_19111)
    InternationalString getFormula();

    /**
     * Reference to a publication giving the formula(s) or procedure used by the
     * coordinate operation method.
     *
     * @return Reference to a publication giving the formula, or {@code null} if none.
     *
     * @condition Only one of {@code getFormula()} and {@code getCitation()} shall be supplied.
     */
    @UML(identifier="formulaCitation", obligation=CONDITIONAL, specification=ISO_19111)
    Citation getCitation();
}
