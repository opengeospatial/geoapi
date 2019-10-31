/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opengis.bridge.python;

import java.util.ServiceLoader;
import org.opengis.referencing.crs.CRSAuthorityFactory;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.crs.GeographicCRS;
import org.opengis.referencing.crs.ProjectedCRS;
import org.opengis.util.FactoryException;

public interface PythonHandler {

    static CoordinateReferenceSystem findCoordinateReferenceSystem(String code) throws FactoryException {
        CRSAuthorityFactory factory = null;
        if (factory == null) {
            for (CRSAuthorityFactory f : ServiceLoader.load(CRSAuthorityFactory.class)) {
                // On pourrait filter ici pour choisir plus finement lequel on retient.
                factory = f;
                break;
            }
        }
        return factory.createCoordinateReferenceSystem(code);
    }

    static String getInterface(Object obj) {
        if (obj instanceof GeographicCRS) return "GeographicCRS";
        else if (obj instanceof ProjectedCRS) return "ProjectedCRS";
        else return null;
    }
}
