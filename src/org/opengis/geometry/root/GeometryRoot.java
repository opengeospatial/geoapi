
//  Class:  GeometryRoot
            
package org.opengis.geometry.root;
public interface GeometryRoot
		{
		void setCRS(String CRS);
		String getCRS();
		GeometryRoot  getMbRegion ( );
		org.opengis.geometry.coordinate.DirectPosition  getRepresentativePoint ( );
		Boundary  getBoundary ( );
		org.opengis.geometry.complex.Complex  getClosure ( );
		boolean  isSimple ( );
		boolean  isCycle ( );
		org.opengis.measure.Distance  getDistance (
 					GeometryRoot  geometry );
		int  getDimension (
 					org.opengis.geometry.coordinate.DirectPosition  point );
		int  getCoordinateDimension ( );
		org.opengis.geometry.complex.Complex[]  getMaximalComplexs ( );
		GeometryRoot  getTransform (
 					String  newCRS );
		org.opengis.geometry.coordinate.Envelope  getEnvelope ( );
		org.opengis.geometry.coordinate.DirectPosition  getCentroid ( );
		GeometryRoot  getConvexHull ( );
		GeometryRoot  getBuffer (
 					org.opengis.measure.Distance  radius );
		};

         