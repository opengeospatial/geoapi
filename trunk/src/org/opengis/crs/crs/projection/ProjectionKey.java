package org.opengis.crs.crs.projection;

import java.util.ArrayList;
import java.util.List;
import org.opengis.util.CodeList;
import org.opengis.util.SimpleEnumerationType;

/**
 * Enumerates well-known map projections.  The primary intended use is
 * to faciliate maintaining maps of Projection objects
 * (e.g., within a ProjectedCRS).  It is expected that implementors
 * will define a <code>getProjection(PrejectionKey key)</code>
 * within ProjectedCRS, Projection, or a factory class.
 * Implementators may facilitate such a method by following the
 * naming convention relative to the ProjectionKey indicated by the
 * interfaces for required projections.
 *
 * <p>
 * Implementation of all projections with defined keys is not required;
 * an implementation of a given projection is mandatory only if an interface
 * is defined for it in this package.
 * An implementation may also implement
 * other projections not listed here.
 */
public class ProjectionKey extends SimpleEnumerationType
{
    //*************************************************************************
    //  Static Fields
    //*************************************************************************
    /**
     * The list of enumeration available in this virtual machine.
     * <strong>Must be declared first!</strong>.
     */
    private static final List VALUES = new ArrayList(80);

    public static ProjectionKey ADAMS_EQUAL_AREA
            = new ProjectionKey("AdamsEqualArea", null);

    public static ProjectionKey AIRY_MINIMUM_ERROR_AZIMUTHAL
            = new ProjectionKey("AiryMinimumErrorAzimuthal", null);

    public static ProjectionKey AITOFF
            = new ProjectionKey("Aitoff", null);

    public static ProjectionKey AITOFF_EQUAL_AREA
            = new ProjectionKey("AitoffEqualArea", null);

    public static ProjectionKey AITOFF_WAGNER
            = new ProjectionKey("AitoffWagner", null);

    public static ProjectionKey ALBERS_EQUAL_AREA_CONIC
            = new ProjectionKey("AlbersEqualAreaConic", null);

    public static ProjectionKey AUGUST
            = new ProjectionKey("August", null);

    public static ProjectionKey BEHRMANN
            = new ProjectionKey("Bonne", null);

    public static ProjectionKey BIPOLAR_OBLIQUE_CONIC_CONFORMAL
            = new ProjectionKey("BipolarObliqueConicConformal", null);

    public static ProjectionKey BOGGS_EUMORPHIC
            = new ProjectionKey("BoggsEumorhphic", null);

    public static ProjectionKey BONNE
            = new ProjectionKey("Bonne", null);

    public static ProjectionKey BRIESEMEISTER
            = new ProjectionKey("Briesemeister", null);

    public static ProjectionKey CASSINI
            = new ProjectionKey("Cassini", null);

    public static ProjectionKey CRASTER_PARABOLIC
            = new ProjectionKey("CrasterParabolic", null);

    public static ProjectionKey CYLINDRICAL_EQUAL_AREA
            = new ProjectionKey("CylindricalEqualArea", null);

    public static ProjectionKey DOUBLE_STEREOGRAPHIC
            = new ProjectionKey("DoubleStereographic", null);

    public static ProjectionKey ECKERT_I
            = new ProjectionKey("EckertI", null);

    public static ProjectionKey ECKERT_II
            = new ProjectionKey("EckertII", null);

    public static ProjectionKey ECKERT_III
            = new ProjectionKey("EckertIII", null);

    public static ProjectionKey ECKERT_IV
            = new ProjectionKey("EckertIV", null);

    public static ProjectionKey ECKERT_V
            = new ProjectionKey("EckertV", null);

    public static ProjectionKey ECKERT_VI
            = new ProjectionKey("EckertVI", null);

    public static ProjectionKey EISENLOHR
            = new ProjectionKey("Eisenlohr", null);

    public static ProjectionKey EQUIDISTANT_AZIMUTHAL
            = new ProjectionKey("EquidistantAzimuthal", null);

    public static ProjectionKey EQUIDISTANT_CONIC
            = new ProjectionKey("EquidistantConic", null);

	public static ProjectionKey EQUIDISTANT_CYLINDRICAL
            = new ProjectionKey("EquidistantCylindrical",
            "Equidistant Cylindrical projection, AKA Geographic, Unprojected, or Equirectangular");

    public static ProjectionKey EQUIRECTANGULAR
            = new ProjectionKey("Equirectangular", "Equivalent to Equidistant Cylindrical");

    public static ProjectionKey GALL_STEREOGRAPHIC
            = new ProjectionKey("GallStereographic", null);

    public static ProjectionKey GAUSS_KRUGER
            = new ProjectionKey("GaussKruger", "Equivalent to Transverse Mercator");

    public static ProjectionKey GENERAL_PERSPECTIVE
            = new ProjectionKey("GeneralPerspective", null);

    public static ProjectionKey GINZBURG
            = new ProjectionKey("Ginzburg", null);

    public static ProjectionKey GLOBULAR
            = new ProjectionKey("Globular", null);

    public static ProjectionKey GNOMONIC
            = new ProjectionKey("Gnomonic", null);

    public static ProjectionKey GOODE_HOMOLOSINE
            = new ProjectionKey("GoodeHomolosine", null);

    public static ProjectionKey GUYOU
            = new ProjectionKey("Guyou", "Guyou's Doubly Periodic Projection");

    public static ProjectionKey HAMMER
            = new ProjectionKey("Hammer", null);

    public static ProjectionKey HAMMER_AITOFF
            = new ProjectionKey("Hammer_Aitoff", null);

    public static ProjectionKey HOTINE_OBLIQUE_MERCATOR
            = new ProjectionKey("HotineObliqueMercator", "Ellipsoidal Oblique Mercator projection");

    public static ProjectionKey LAGRANGE
            = new ProjectionKey("Lagrange", null);

    public static ProjectionKey LAMBERT_AZIMUTHAL_EQUAL_AREA
            = new ProjectionKey("LambertAzimuthalEqualArea", null);

    public static ProjectionKey LAMBERT_CONFORMAL_CONIC
            = new ProjectionKey("LambertConformalConic", null);

    public static ProjectionKey LASKOWSKI_TRI_OPTIMAL
            = new ProjectionKey("LaskowskiTriOptimal", null);

    public static ProjectionKey LOXIMUTHAL
            = new ProjectionKey("Loximuthal", null);

    public static ProjectionKey MCBRYDE
            = new ProjectionKey("McBryde", null);

    public static ProjectionKey MERCATOR
            = new ProjectionKey("Mercator", null);

    public static ProjectionKey MILLER_CYLINDRICAL
            = new ProjectionKey("MillerCylindrical", null);

    public static ProjectionKey MODIFIED_STEREOGRAPHIC_CONFORMAL
            = new ProjectionKey("ModifiedStereographicConformal", null);

    public static ProjectionKey MOLLWEIDE
            = new ProjectionKey("Mollweide", null);

    public static ProjectionKey OBLIQUE_MERCATOR
            = new ProjectionKey("ObliqueMercator", "Spherical Oblique Mercator projection");

    public static ProjectionKey OBLIQUE_MOLLWEIDE
            = new ProjectionKey("ObliqueMollweide", null);

    public static ProjectionKey ORTHOGRAPHIC
            = new ProjectionKey("Orthographic", null);

    public static ProjectionKey PLATE_CARREE
            = new ProjectionKey("PlateCarree", null);

    public static ProjectionKey POLYCONIC
            = new ProjectionKey("Polyconic", null);

    public static ProjectionKey QUARTIC_AUTHALIC
            = new ProjectionKey("QuarticAuthalic", "Quartic Authalic or Adams Orthembadic Projection");

    public static ProjectionKey ROBINSON
            = new ProjectionKey("Robinson", null);

    public static ProjectionKey SINUSOIDAL
            = new ProjectionKey("Sinusoidal", null);

    public static ProjectionKey SPACE_OBLIQUE_MERCATOR
            = new ProjectionKey("SpaceObliqueMercator", null);

    public static ProjectionKey STEREOGRAPHIC
            = new ProjectionKey("Stereographic", null);

    public static ProjectionKey TIMES
            = new ProjectionKey("Times", null);

    public static ProjectionKey TRANSVERSE_CYLINDRICAL_EQUAL_AREA
            = new ProjectionKey("TransverseCylindricalEqualArea", null);

    public static ProjectionKey TRANSVERSE_MERCATOR
            = new ProjectionKey("TransverseMercator", null);

    public static ProjectionKey TWO_POINT_EQUIDISTANT
            = new ProjectionKey("TwoPointEquidistant", null);

    public static ProjectionKey VANDER_GRINTEN
            = new ProjectionKey("VanderGrinten", null);

    public static ProjectionKey VANDER_GRINTEN_IV
            = new ProjectionKey("VanderGrintenIV", null);

    public static ProjectionKey WINKEL_I
            = new ProjectionKey("WinkelI", null);

    public static ProjectionKey WINKEL_II
            = new ProjectionKey("WinkelII", null);

    public static ProjectionKey WINKEL_TRIPEL
            = new ProjectionKey("WinkelTripel", null);

    //*************************************************************************
    //  Constructor
    //*************************************************************************

    /**
     * Construct a new ProjectionKey with the give name and description.
     * This constructor should only be used to make the static
     * constants in this class or by a provider subclasses to create
     * implementation specific projections.
     *
     * @param name a String defining the name of the projection.
     * @param description a String describing the projection.
     */
    protected ProjectionKey(String name, String description) {
        super(VALUES, name, description);
    }

    //*************************************************************************
    //  Methods
    //*************************************************************************

    /**
     * Returns the list of <code>LineStyle</code>s.
     */
    public static ProjectionKey[] values() {
        synchronized (VALUES) {
            return (ProjectionKey[]) VALUES.toArray(new ProjectionKey[VALUES.size()]);
        }
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public CodeList[] family() {
        return values();
    }

}
