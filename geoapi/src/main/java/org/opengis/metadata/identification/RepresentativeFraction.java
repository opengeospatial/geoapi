/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2004-2007 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.identification;

import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.MANDATORY;
import static org.opengis.annotation.Specification.ISO_19115;


/**
 * Derived from ISO 19103 Scale where {@linkplain #getDenominator denominator} = 1 / Scale.
 * {@code Measure} and {@code Scale.targetUnits} = {@code Scale.sourceUnits}.
 * <p>
 * Implementations are encouraged to extend {@link Number}.
 * </p>
 * 
 * @version <A HREF="http://www.opengeospatial.org/standards/as#01-111">ISO 19115</A>
 * @author Ely Conn (Leica Geosystems Geospatial Imaging, LLC)
 * @since GeoAPI 2.1
 */
@UML(identifier="MD_RepresentativeFraction", specification=ISO_19115)
public interface RepresentativeFraction {    
    /**
     * Returns this value in a form usable for computation.
     *
     * @return <code>1.0 / (double) {@linkplain #getDenominator()}</code>
     */
    double doubleValue();
    
    /**
     * The number below the line in a vulgar fraction.
     *
     * @todo Return type may need to be a {@code long}? Source interface seems to indicate such.
     */
    @UML(identifier="denominator", obligation=MANDATORY, specification=ISO_19115)
    int getDenominator();
}
