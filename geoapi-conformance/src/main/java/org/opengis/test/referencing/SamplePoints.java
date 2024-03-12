/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2011-2024 Open Geospatial Consortium, Inc.
 *    http://www.geoapi.org
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.opengis.test.referencing;

import java.util.Arrays;
import java.awt.geom.Rectangle2D;
import org.opengis.referencing.operation.CoordinateOperation;

import static org.junit.jupiter.api.Assertions.*;
import static org.opengis.test.referencing.PseudoEpsgFactory.R_US_FEET;
import static org.opengis.test.referencing.PseudoEpsgFactory.LINKS;


/**
 * Sample points given in the EPSG guidance document or other authoritative sources. The sample
 * points are used for testing a {@linkplain CoordinateOperation coordinate operation}, which is
 * typically (but not necessarily) a map projection. The coordinate operation being tested is
 * identified by the {@linkplain #operation} field.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
final class SamplePoints {
    /**
     * The EPSG code for the Coordinate Reference System using the sample points.
     * Whether it is the source or the target CRS is test-dependent. This field
     * is used only for information purpose in the {@link #toString()} method.
     */
    private final int usedByCRS;

    /**
     * The EPSG code of the {@linkplain CoordinateOperation coordinate operation} being tested.
     */
    final int operation;

    /**
     * The points to test, in the source (typically geographic) CRS.
     */
    final double[] sourcePoints;

    /**
     * The expected results of the conversion or transformation of {@link #sourcePoints}.
     */
    final double[] targetPoints;

    /**
     * The area of validity in which to test random points, in units of the base (source) CRS.
     */
    final Rectangle2D areaOfValidity;

    /**
     * Creates a new instance for the given sample points.
     *
     * @param usedByCRS       the EPSG code for the Coordinate Reference System using the sample points.
     * @param operation       the EPSG code of the {@link CoordinateOperation} being tested.
     * @param sourcePoints    the points to test, in the source (typically geographic) CRS.
     * @param targetPoints    the expected results of the conversion or transformation of {@code sourcePoints}.
     * @param areaOfValidity  the area of validity in which to test random points, in units of the base (source) CRS.
     */
    private SamplePoints(final int usedByCRS, final int operation,
            double[] sourcePoints, double[] targetPoints, final Rectangle2D areaOfValidity)
    {
        this.usedByCRS      = usedByCRS;
        this.operation      = operation;
        this.sourcePoints   = sourcePoints;
        this.targetPoints   = targetPoints;
        this.areaOfValidity = areaOfValidity;
        assertFalse(areaOfValidity.isEmpty());
    }

    /**
     * Creates an object containing sample values for an operation method using the given CRS.
     * The CRS codes accepted by this method are documented in the second column of the table
     * in {@link PseudoEpsgFactory#createParameters(int)} javadoc.
     *
     * <ul>
     *   <li>For map projection tests, this method returns the sample points for a conversion
     *       from the base CRS to the given projected CRS.</li>
     *   <li>For datum shift tests, this method returns the sample points for a transformation
     *       from WGS84 to the given CRS.</li>
     * </ul>
     *
     * @param  crs  a code from the second column of {@link PseudoEpsgFactory#createParameters(int)}.
     * @return sample points (never {@code null}).
     */
    static SamplePoints forCRS(final int crs) {
        final double λ0;   // Longitude of natural origin
        final double φ0;   // Latitude of natural origin
        final double fe;   // False easting
        final double fn;   // False northing
        final double λ, φ, e, n;
        final double λmin, λmax, φmin, φmax;
        final int operation;
        double[] sourcePoints = null;
        double[] targetPoints = null;
        switch (crs) {
            case 3002: {                                                    // "Makassar / NEIEZ"
                operation = 19905;
                fe =   3900000.00;  λ0 = 110;
                fn =    900000.00;  φ0 =   0;
                e  =   5009726.58;  λ  = 120;
                n  =    569150.82;  φ  =  -3;
                λmin = 117.6; φmin = -7.9;
                λmax = 121.0; φmax =  2.0;
                break;
            }
            case 3388: {                                                    // "Pulkovo 1942 / Caspian Sea Mercator"
                operation = 19884;
                fe =         0.00;  λ0 = 51;
                fn =         0.00;  φ0 =  0;
                e  =    165704.29;  λ  = 53;
                n  =   5171848.07;  φ  = 53;
                λmin = 46.68; φmin = 36.58;
                λmax = 54.76; φmax = 47.11;
                break;
            }
            case 3857: {                                                    // "WGS 84 / Pseudo-Mercator"
                operation = 3856;
                fe =         0.00;  λ0 = 0;
                fn =         0.00;  φ0 = 0;
                e  = -11169055.58;  λ  = -(100 +       20.0  /60);          // 100°20'00.000"W
                n  =   2800000.00;  φ  =  ( 24 + (22 + 54.433/60)/60);      //  24°22'54.433"N
                λmin = -180.0; φmin = -85.0;
                λmax =  180.0; φmax =  85.0;
                break;
            }
            case 310642901: {                                               // "IGNF:MILLER"
                operation = 310642901;
                fe =         0.00;  λ0 =  0;
                fn =         0.00;  φ0 =  0;
                e  =    275951.78;  λ  =  2.478917;
                n  =   5910061.78;  φ  = 48.805639;
                λmin = -180.0; φmin = -90.0;
                λmax =  180.0; φmax =  90.0;
                break;
            }
            case 29873: {                                                   // "Timbalai 1948 / RSO Borneo (m)"
                operation = 19958;
                fe = 590476.87;  λ0 = 115;
                fn = 442857.65;  φ0 =   4;
                e  = 679245.73;  λ  = 115 + (48 + 19.8196/60)/60;           // 115°48'19.8196"E
                n  = 596562.78;  φ  =   5 + (23 + 14.1129/60)/60;           //   5°23'14.1129"N
                λmin = 109.55; φmin = 0.85;
                λmax = 119.26; φmax = 7.35;
                break;
            }
            case 27700: {                                                   // "OSGB 1936 / British National Grid"
                operation = 19916;
                fe =  400000.00;  λ0 =  -2;
                fn = -100000.00;  φ0 =  49;
                e  =  577274.98;  λ  =    30.0/60;                          // 00°30'00.00"E
                n  =   69740.49;  φ  = 50+30.0/60;                          // 50°30'00.00"N
                λmin = -7.56; φmin = 49.96;
                λmax =  1.78; φmax = 60.84;
                break;
            }
            case 2053: {                                                    // "Hartebeesthoek94 / Lo29"
                operation = 17529;
                fe =  0;  λ0 = 29;
                fn =  0;  φ0 =  0;
                e  =    71984.48;  λ  =  28 + (16 + 57.479/60)/60;          // 28°16'57.479"E
                n  =  2847342.74;  φ  = -25 - (43 + 55.302/60)/60;          // 25°43'55.302"S
                λmin = 27.99; φmin = -33.03;
                λmax = 30.00; φmax = -22.13;
                break;
            }
            case 2314: {                                                    // "Trinidad 1903 / Trinidad Grid"
                operation = 19975;
                fe =   430000.00*LINKS;  λ0 = -(61 + 20.0/60);              // 61°20'00"W
                fn =   325000.00*LINKS;  φ0 = 10 + (26 + 30.0/60)/60;       // 10°26'30"N
                e  =    66644.94*LINKS;  λ  = -62;
                n  =    82536.22*LINKS;  φ  =  10;
                λmin = -62.08; φmin =  9.83;
                λmax = -60.00; φmax = 11.50;
                break;
            }
            case 3139: {                                                    // "Vanua Levu 1915 / Vanua Levu Grid"
                operation = 19878;
                fe =   1251331.8*LINKS;  λ0 =  179 + 20./60;                // 179°20'00"E
                fn =   1662888.5*LINKS;  φ0 = -(16 + 15./60);               // 16°15'00"S
                e  =  1601528.90*LINKS;  λ  =  179 + (59 + 39.6115/60)/60;  // 179°59′39.6115″E
                n  =  1336966.01*LINKS;  φ  = -(16 + (50 + 29.2435/60)/60); //  16°50′29.2435″S
                λmin = 178.42; φmin = -17.07;
                λmax = 180.23; φmax = -16.1;
                break;
            }
            case 24200: {                                                   // "JAD69 / Jamaica National Grid"
                operation = 19910;
                fe =    250000.00;  λ0 = -77.0;
                fn =    150000.00;  φ0 =  18.0;
                e  =    255966.58;  λ  = -(76 + (56 + 37.26/60)/60);        // 76°56'37.26"W
                n  =    142493.51;  φ  =  (17 + (55 + 55.80/60)/60);        // 17°55'55.80"N
                λmin = -78.4; φmin = 17.65;
                λmax = -76.1; φmax = 18.6;
                break;
            }
            case 32040: {                                                   // "NAD27 / Texas South Central"
                operation = 14204;
                fe = 2000000.00/R_US_FEET;  λ0 = -99.0;
                fn =       0.00/R_US_FEET;  φ0 =  27 + 50.0/60;
                e  = 2963503.91/R_US_FEET;  λ  = -96.0;                     // 96°00'00.00"W
                n  =  254759.80/R_US_FEET;  φ  =  28 + 30.0/60;             // 28°30'00.00"N
                λmin = -105.0; φmin = 27.82;
                λmax = -93.41; φmax = 30.66;
                break;
            }
            case 6201: {                                                    // "NAD27 / Michigan Central"
                operation = 6198;
                fe = 2000000.00/R_US_FEET;  λ0 = -84 - 20.0/60;
                fn =       0.00/R_US_FEET;  φ0 =  43 + 19.0/60;
                e  = 2308335.75/R_US_FEET;  λ  = -83 - 10.0/60;             // 83°10'00.00"W
                n  =  160210.48/R_US_FEET;  φ  =  43 + 45.0/60;             // 43°45'00.00"N
                λmin = -87.06; φmin = 43.80;
                λmax = -82.27; φmax = 45.92;
                break;
            }
            case 31300: {                                                   // "Belge 1972 / Belge Lambert 72"
                operation = 19902;
                fe =    150000.01;  λ0 =  4 + (21 + 24.983/60)/60;
                fn =   5400088.44;  φ0 = 90;
                e  =    251763.20;  λ  =  5 + (48 + 26.533/60)/60;          //  5°48'26.533"E
                n  =    153034.13;  φ  = 50 + (40 + 46.461/60)/60;          // 50°40'46.461"N
                λmin = 2.54; φmin = 49.51;
                λmax =  6.4; φmax = 51.5;
                break;
            }
            case 3035: {                                                    // "ETRS89 / LAEA Europe"
                operation = 19986;
                fe =   4321000.00;  λ0 = 10;
                fn =   3210000.00;  φ0 = 52;
                e  =   3962799.45;  λ  =  5;
                n  =   2999718.85;  φ  = 50;
                λmin = -31.53; φmin = 27.75;
                λmax =  45.00; φmax = 71.15;
                break;
            }
            case 32661:                                                     // "WGS 84 / UPS North (N,E)"
            case 5041: {                                                    // "WGS 84 / UPS North (E,N)"
                operation = 16061;
                fe =  2000000.00;  λ0 =  0;
                fn =  2000000.00;  φ0 = 90;
                e  =  3320416.75;  λ  = 44;
                n  =   632668.43;  φ  = 73;
                λmin = -180.0; φmin = 60.0;
                λmax =  180.0; φmax = 90.0;
                break;
            }
            case 3032: {                                                    // "WGS 84 / Australian Antarctic Polar Stereographic"
                operation = 19993;
                fe =  6000000.00;  λ0 =  70;
                fn =  6000000.00;  φ0 = -90;
                e  =  7255380.79;  λ  = 120;
                n  =  7053389.56;  φ  = -75;
                λmin =  45.0; φmin = -90.0;
                λmax = 160.0; φmax = -60.0;
                break;
            }
            case 2985: {                                                    // "Petrels 1972 / Terre Adelie Polar Stereographic"
                operation = 19983;
                fe =    300000.0;  λ0 = 140;
                fn =    200000.0;  φ0 = -67;
                e  =   303169.52;  λ  = 140 + ( 4 + 17.040/60)/60;          // 140°04'17.040"E
                n  =   244055.72;  φ  = -66 - (36 + 18.820/60)/60;          // 66°36'18.820"S
                λmin = 139.44; φmin = -66.78;
                λmax = 141.50; φmax = -66.10;
                break;
            }
            case 28992: {                                                   // "Amersfoort / RD New"
                operation = 19914;
                fe =  155000.000;  λ0 =  5 + (23 + 15.500/60)/60;           //  5°23'15.500"E
                fn =  463000.000;  φ0 = 52 + ( 9 + 22.178/60)/60;           // 52°09'22.178"N
                e  =  196105.283;  λ =  6;
                n  =  557057.739;  φ = 53;
                λmin =  3.2; φmin = 50.75;
                λmax = 7.24; φmax = 53.75;
                break;
            }
            case 9818: {                                                    // "Polyconic" (not an official EPSG code)
                operation = 9818;
                fe =       0;  λ0 =  0;
                fn =       0;  φ0 =  0;
                e  =       0;  λ  =  0;
                n  = 5540628;  φ  = 50;
                sourcePoints = new double[] {
                    0, 50,   1, 49,   2, 48,   3, 47,
                    0, 30,   1, 29,   2, 28,   3, 27
                };
                targetPoints = new double[] {
                    0, 5540628,   73172, 5429890,   149239, 5320144,   228119, 5211397,
                    0, 3319933,   97440, 3209506,   196719, 3099882,   297742, 2991002
                };
                λmin = 0; φmin =  23;                                       // Domain of table 19 of Snyder.
                λmax = 3; φmax =  50;
                break;
            }
            case 9840: {                                                    // "Orthographic" (not an official EPSG code)
                operation = 9840;
                fe =         0.00;   λ0 =  5;
                fn =         0.00;   φ0 = 55;
                e  =   -189011.711;  λ  =  2 + ( 7 + 46.38/60)/60;          //  2°07'46.38"E
                n  =   -128640.567;  φ  = 53 + (48 + 33.82/60)/60;          // 53°48'33.82"N
                λmin = λ0 - 50; φmin = φ0 - 60;                             // Arc length ≈ 73°. Must be ≤ 90°.
                λmax = λ0 + 50; φmax =      90;
                sourcePoints = new double[] {λ, φ};
                targetPoints = new double[] {e, n};
                break;
            }
            case 3295: {                                                    // "Guam 1963 / Yap Islands"
                operation = 15399;
                fe =  40000.00;    λ0 = 138 + (10 +  7.48/60)/60;           // 138°10'07.48"E
                fn =  60000.00;    φ0 =   9 + (32 + 48.15/60)/60;           //   9°32'48.15"N
                e  =  42665.90;    λ  = 138 + (11 + 34.908/60)/60;          // 138°11'34.908"E
                n  =  65509.82;    φ  =   9 + (35 + 47.493/60)/60;          //   9°35'47.493"N
                λmin = 137.99; φmin = 9.39;
                λmax = 138.27; φmax = 9.69;
                break;
            }
            case 2065: {                                                    // "CRS S-JTSK (Ferro) / Krovak"
                operation = 19952;
                fe =         0.00;   λ0 = 24 + 50.0/60;                     // 24°50'00"E
                fn =         0.00;   φ0 = 59 + (45 + 27.3548/60)/60;        // 59°45'27.355"N
                e  =   -568990.997;  λ  = 16 + (50 + 59.1790/60)/60;        // 16°50'59.179"E
                n  =  -1050538.643;  φ  = 50 + (12 + 32.4416/60)/60;        // 50°12'32.442"N
                λmin = 12.09; φmin = 47.74;
                λmax = 22.56; φmax = 51.05;
                // I found documentation on the web saying that 24°50'E 59°45'27"N is the
                // cartographic pole, but I did not found a documentation having the .355
                // digits. Exclude the cartographic pole for now...
                sourcePoints = new double[] {λ, φ};
                targetPoints = new double[] {e, n};
                break;
            }
            // ―――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――
            // END OF PROJECTION CODES - now testing datum shifts or other operations.
            // ―――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――
            case 4230: {                            // "ED50" (a GeographicCRS for testing Abridged Molodensky)
                operation = 9605;
                λ0 = φ0 = λ = φ = Double.NaN;
                fe = fn = e = n = Double.NaN;
                sourcePoints = new double[] {
                     2 + ( 7 + 46.38/60)/60,        // Longitude
                    53 + (48 + 33.82/60)/60,        // Latitude
                    73.0                            // Height (m)
                };
                targetPoints = new double[] {
                     2 + ( 7 + 51.477/60)/60,       // Longitude
                    53 + (48 + 36.563/60)/60,       // Latitude
                    28.091                          // Height (m)
                };
                λmin = -180; φmin = -80;
                λmax = +180; φmax = +80;
                break;
            }
            case 5819: {                            // "EPSG topocentric example A"
                operation = 15594;
                λ0 = φ0 = λ = φ = Double.NaN;
                fe = fn = e = n = Double.NaN;
                sourcePoints = new double[] {
                     2 + ( 7 + 46.38/60)/60,        // Longitude
                    53 + (48 + 33.82/60)/60,        // Latitude
                    73.0                            // Height (m)
                };
                targetPoints = new double[] {-189013.869, -128642.040, -4220.171};
                λmin = 1; φmin = 50;
                λmax = 4; φmax = 60;
                break;
            }
            case 5820: {                            // "EPSG topocentric example B"
                operation = 15595;
                λ0 = φ0 = λ = φ = Double.NaN;
                fe = fn = e = n = Double.NaN;
                sourcePoints = new double[] {3771793.968,  140253.342, 5124304.349};
                targetPoints = new double[] {-189013.869, -128642.040,   -4220.171};
                λmin = 3700000; φmin = 120000;      // Actually X,Y (not latitude/longitude).
                λmax = 3900000; φmax = 160000;
                break;
            }
            default: throw new IllegalArgumentException("No sample points for EPSG:" + crs);
        }
        if (sourcePoints == null) sourcePoints = new double[] {λ, φ, λ0, φ0};
        if (targetPoints == null) targetPoints = new double[] {e, n, fe, fn};
        return new SamplePoints(crs, operation, sourcePoints, targetPoints,
                new Rectangle2D.Double(λmin, φmin, λmax - λmin, φmax - φmin));
    }

    /**
     * Subtracts the given amount to every longitudes in the source coordinates. This method shall
     * be invoked, if needed, before the {@link #swap(double[])} and {@link #flip(double[])} methods.
     *
     * @param primeMeridian  the amount to subtracts to longitude.
     */
    final void rotateLongitude(final double primeMeridian) {
        for (int i=0; i<sourcePoints.length; i+=2) {
            sourcePoints[i] -= primeMeridian;
        }
    }

    /**
     * Swap the (λ,φ) or (x,y) coordinate values in the given array.
     * The coordinate points are assumed two-dimensional.
     *
     * @param  coordinates  the array where to swap axes.
     */
    static void swap(final double[] coordinates) {
        for (int i=0; i<coordinates.length; i++) {
            final double t = coordinates[i];
            coordinates[i] = coordinates[++i];
            coordinates[i] = t;
        }
    }

    /**
     * Reverses the sign of all coordinate values in the given array.
     *
     * @param  coordinates  the array where to flip axes.
     */
    static void flip(final double[] coordinates) {
        for (int i=0; i<coordinates.length; i++) {
            coordinates[i] = -coordinates[i];
        }
    }

    /**
     * Returns a string representation of the sample points, for debugging purpose.
     */
    @Override
    public String toString() {
        return "SamplePoints[CRS=" + usedByCRS + ": "+ Arrays.toString(sourcePoints) + " ⇒ " +
                Arrays.toString(targetPoints) + ']';
    }
}
