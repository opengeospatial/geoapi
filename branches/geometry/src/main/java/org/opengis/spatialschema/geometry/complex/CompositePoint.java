// This class was created by Sanjay Jena and Prof. Jackson Roehrig in order to complete missing parts of the GeoAPI and submit the results to GeoAPI afterwards as proposal.

package org.opengis.spatialschema.geometry.complex;

import org.opengis.annotation.UML;
import static org.opengis.annotation.Specification.*;

/**
 * A separate class for composite point, {@linkplain CompositePoint} is included for completeness.
 * It is a {@linkplain Complex} containing one and only one {@linkplain Point}.
 * 
 * @version <A HREF="http://www.opengis.org/docs/01-101.pdf">Abstract specification 5</A>
 * @author Sanjay Jena and Jackson Roehrig
 *
 */
@UML(identifier="GM_CompositeCurve", specification=ISO_19107)
public interface CompositePoint extends Composite {

}
