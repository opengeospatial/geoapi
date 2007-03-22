/*$************************************************************************************************
 **
 ** $Id: RepresentativeFraction.java $
 **
 ** $URL: https://geoapi.svn.sourceforge.net/svnroot/geoapi/trunk/geoapi/src/main/java/org/opengis/metadata/identification/RepresentativeFraction.java $
 **
 ** Copyright (C) 2004-2007 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.identification;

//Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.MANDATORY;
import static org.opengis.annotation.Specification.ISO_19115;

/**
 * Derived from ISO 19103 Scale where MD_Representative Fraction.denominator = 1 / Scale.
 * Measure And Scale.targetUnits = Scale.sourceUnits
 * <p>
 * Implementations are encouraged to extend Number.
 * </p>
 * 
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 * @author Ely Conn (Leica Geosystems Geospatial Imaging, LLC)
 * @since GeoAPI 2.1
 */
@UML(identifier="MD_RepresentativeFraction", specification=ISO_19115)
public interface RepresentativeFraction {    
    /**
     * Returns this value in a form usable for computation.
     * @return 1.0 / (double) getDenominator()
     */
    public double doubleValue();
    
    /**
     * The number below the line in a vulgar fraction
     */
    @UML(identifier="denominator", obligation=MANDATORY, specification=ISO_19115)
    int getDenominator(); // TODO: this may need to be a long? Source interface seems to indicate such
}
