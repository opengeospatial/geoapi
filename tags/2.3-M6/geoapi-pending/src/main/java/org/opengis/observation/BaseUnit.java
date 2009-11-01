/*
 * Sicade - Systèmes intégrés de connaissances pour l'aide à la décision en environnement
 * (C) 2005, Institut de Recherche pour le Développement
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation; either
 *    version 2.1 of the License, or (at your option) any later version.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 */

package org.opengis.observation;

/**
 * @version <A HREF="http://www.opengeospatial.org/standards/om">Implementation specification 1.0</A>
 * @author Open Geospatial Consortium
 * @author Guilhem Legal (Geomatys)
 * @since GeoAPI 2.3
 *
 */
public interface BaseUnit {
    
    /**
     * The alphanumeric identifier of the unit.
     */
    String getId();
    
    /**
     * The full name of the uom.
     */
    String getName();
    
    /**
     * The quantity type.
     */
    String getQuantityType();
    
    /**
     * The system defining this unit f measure.
     */
    String getUnitsSystem();
    
}
