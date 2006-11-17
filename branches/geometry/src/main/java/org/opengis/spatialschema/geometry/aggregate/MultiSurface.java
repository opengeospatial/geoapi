// This class was created by Sanjay Jena and Prof. Jackson Roehrig in order to complete missing parts of the GeoAPI and submit the results to GeoAPI afterwards as proposal.

package org.opengis.spatialschema.geometry.aggregate;

import java.util.Set;

import org.opengis.spatialschema.geometry.DirectPosition;
import org.opengis.spatialschema.geometry.primitive.OrientableSurface;

/**
 * {@linkplain MultiSurface} is an aggregate class containing only instances of {@linkplain OrientableSurface}.
 * The association role <code>element</code> shall be the set of {@linkplain OrientableSurface}s contained in this {@linkplain MultiSurface}.
 * 
 * @author Sanjay Jena and Prof. Dr. Jackson Roehrig
 * @version <A HREF="http://www.opengis.org/docs/01-101.pdf">Abstract specification 5</A>
 */
@UML(identifier="GM_MultiSurface", specification=ISO_19107)
public interface MultiSurface extends MultiPrimitive {
	
    /**
     * Returns the {@linkplain Set} containing the OrientableSurfaces that compose this
     * MultiSurface.  The {@linkplain Set} may be modified if this geometry is
     * {@linkplain #isMutable mutable}.
     */
    @UML(identifier="element", obligation=MANDATORY, specification=ISO_19107)
    public Set<OrientableSurface> getElements();
    
    
    /**
     * Returns the accumulated area of all {@linkplain OrientableSurface}s contained in this MultiSurface.
     * The area of a 2-dimensional geometric object shall be a numeric measure of its surface area
     * (in a square unit of distance). Since area is an accumulation (integral) of the product of
     * two distances, its return value shall be in a unit of measure appropriate for measuring
     * distances squared, such as meters squared (m<sup>2</sup>).
     *
     * <blockquote><font size=2>
     * <strong>NOTE:</strong> Consistent with the definition of surface as a set of
     * {@linkplain DirectPosition direct positions}, holes in the surfaces will not contribute to
     * the total area. If the usual Green's Theorem (or more general Stokes' Theorem) integral is
     * used, the integral around the holes in the surface are subtracted from the integral
     * about the exterior of the surface patch.
     * </font></blockquote>
     *
     * @return The area.
     * @unitof Area
     */
    @UML(identifier="area", obligation=MANDATORY, specification=ISO_19107)
    public double getArea();



}
