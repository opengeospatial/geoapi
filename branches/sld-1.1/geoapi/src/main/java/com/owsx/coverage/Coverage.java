/* ====================================================================
 * The ImageMatters LLC Software License, Version 1.1
 *
 * Copyright (c) 1999-2004 ImageMatters LLC.  All rights reserved.
 *
 * ====================================================================
 * 
 * $Log$
 * Revision 1.1  2005/03/03 11:54:42  desruisseaux
 * Added Stephane's interfaces proposal for GridCoverage
 *
 * Revision 1.3  2005/01/21 19:42:48  stephanef
 * RangePosition related change
 *
 * Revision 1.2  2005/01/05 19:03:06  stephanef
 * *** empty log message ***
 *
 * Revision 1.1  2005/01/04 15:08:49  stephanef
 * Change osf to owsx project name
 * 
 */
package com.owsx.coverage;

import java.util.Date;
import java.util.Set;

import org.opengis.metadata.extent.Extent;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.spatialschema.geometry.DirectPosition;

/**
 * Interface representing an OpenGIS coverage. The essential property of
 * coverage is to be able to generate a value for any point within its domain.
 * How coverage is represented internally is not a concern.
 * 
 * For example consider the following different internal representations of
 * coverage: <br>
 * <OL>
 * <li>A coverage may be represented by a set of polygons which exhaustively
 * tile a plane (that is each point on the plane falls in precisely one
 * polygon). The value returned by the coverage for a point is the value of an
 * attribute of the polygon that contains the point.</li>
 * <li>A coverage may be represented by a grid of values. The value returned by
 * the coverage for a point is that of the grid value whose location is nearest
 * the point.</li>
 * <li>Coverage may be represented by a mathematical function. The value
 * returned by the coverage for a point is just the return value of the function
 * when supplied the coordinates of the point as arguments.</li>
 * <li>Coverage may be represented by combination of these. For example,
 * coverage may be represented by a combination of mathematical functions valid
 * over a set of polynomials.</LI>
 * </OL>
 * 
 * @author Stephane Fellah
 * @version $Revision: 658 $
 * @since Dec 27, 2004
 */
public interface Coverage {

    /**
     * Returns the description of the Coverage's type
     * 
     * @return CoverageType instance
     */
    public CoverageType getCoverageType();

    /**
     * Get the extent of the spatiotemporal domain of the Coverage The Extent is
     * defined in ISO 19115 specification. Extents may be specified in space,
     * time or timespace.
     * 
     * @return Array of Extent
     */
    public Extent[] getDomainExtent();
    
    public CoordinateReferenceSystem getCoordinateReferenceSystem();
    
    public Set<GeometryValuePair> list();
    
    public Set<GeometryValuePair> find(DirectPosition p,int limit);

    public Measure evaluate(DirectPosition p, Date timeInstant, RangePosition rangePosition);
    
    public byte[] evaluate(DirectPosition p, RangePosition rangePosition,byte[] dest);
    public short[] evaluate(DirectPosition p, RangePosition rangePosition,short[] dest);
    public int[] evaluate(DirectPosition p, RangePosition rangePosition,int[] dest);
    public float[] evaluate(DirectPosition p, RangePosition rangePosition,float[] dest);
    public double[] evaluate(DirectPosition p, RangePosition rangePosition,double[] dest);
    
    public byte[] evaluate(DirectPosition p, Date timeInstant,RangePosition rangePosition,byte[] dest);
    public short[] evaluate(DirectPosition p,  Date timeInstant,RangePosition rangePosition,short[] dest);
    public int[] evaluate(DirectPosition p,  Date timeInstant,RangePosition rangePosition,int[] dest);
    public float[] evaluate(DirectPosition p,  Date timeInstant,RangePosition rangePosition,float[] dest);
    public double[] evaluate(DirectPosition p,  Date timeInstant,RangePosition rangePosition,double[] dest);

    
}
