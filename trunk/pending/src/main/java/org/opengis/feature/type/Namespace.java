/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.feature.type;

// J2SE direct dependencies
import java.util.Set;

// Annotations
//import org.opengis.annotation.UML;
//import org.opengis.annotation.Extension;
//import static org.opengis.annotation.Obligation.*;
//import static org.opengis.annotation.Specification.*;


/**
 * A collection of 0 or more {@linkplain Name  names), with no duplicates.
 * <p>
 * If this was not an interface only project we could make use of the javax.naming package
 * </p>
 * 
 * @author Jody Garnett, Refractions Research, Inc.
 * @since GeoAPI 2.1
 */
//@UML(identifier="NameSpace", specification=ISO_19103)
public interface Namespace extends Set<Name> {
	
    /**
     * Indicates whether this namespace is a "top level" namespace.
     * 
     * @return <code>true</code> if getName() is not contained in another namespace
     */
    //@UML(identifier="global", obligation=MANDATORY, specification=ISO_19103)
    boolean isGlobal();

    /**
     * Represents the identifier of this namespace.
     */
    //@UML(identifier="name", obligation=MANDATORY, specification=ISO_19103)
    Name getName();
    
    /** Same as getName().getURI() */
    String getURI();
    
}