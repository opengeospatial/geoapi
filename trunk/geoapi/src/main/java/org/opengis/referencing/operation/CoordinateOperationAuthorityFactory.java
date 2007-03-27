/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.referencing.operation;

// J2SE dependencies
import java.util.Set;

// OpenGIS direct dependencies and extensions
import org.opengis.metadata.Identifier;                       // For javadoc
import org.opengis.referencing.AuthorityFactory;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.NoSuchAuthorityCodeException;  // For javadoc
import org.opengis.referencing.crs.CoordinateReferenceSystem; // For javadoc

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Creates coordinate transformation objects from codes. The codes are maintained by an
 * external authority. A commonly used authority is <A HREF="http://www.epsg.org">EPSG</A>,
 * which is also used in the GeoTIFF standard.
 *
 * @version <A HREF="http://www.opengis.org/docs/01-009.pdf">Implementation specification 1.0</A>
 * @author Open Geospatial Consortium
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 1.0
 */
@UML(identifier="CT_CoordinateTransformationAuthorityFactory", specification=OGC_01009)
public interface CoordinateOperationAuthorityFactory extends AuthorityFactory {
    /**
     * Creates an operation from a single operation code. The "{@linkplain Identifier#getAuthority
     * authority}" and "{@linkplain Identifier#getCode code}" values of the created object will be
     * set to the authority of this object, and the code specified by the client, respectively. The
     * other metadata values may or may not be set.
     *
     * @param code Coded value for transformation.
     *
     * @throws NoSuchAuthorityCodeException if the specified {@code code} was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     */
    @UML(identifier="createFromTransformationCode", specification=OGC_01009)
    CoordinateOperation createCoordinateOperation(String code) throws FactoryException;

    /**
     * Creates operations from {@linkplain CoordinateReferenceSystem coordinate reference system}
     * codes. This method returns only the operations declared by the authority, with preferred
     * operations first. This method doesn't need to compute operations from {@code source} to
     * {@code target} CRS if no such operations were explicitly defined in the authority database.
     * Computation of arbitrary operations can be performed by
     * <code>{@linkplain CoordinateOperationFactory#createOperation(CoordinateReferenceSystem,
     * CoordinateReferenceSystem) CoordinateOperationFactory.createOperation}(sourceCRS, targetCRS)</code>
     * instead.
     *
     * @param  sourceCode   Coded value of source coordinate reference system.
     * @param  targetCode   Coded value of target coordinate reference system.
     *
     * @throws NoSuchAuthorityCodeException if a specified code was not found.
     * @throws FactoryException if the object creation failed for some other reason.
     */
    @UML(identifier="createFromCoordinateSystemCodes", specification=OGC_01009)
    Set<CoordinateOperation> createFromCoordinateReferenceSystemCodes(String sourceCode, String targetCode)
            throws FactoryException;
}
