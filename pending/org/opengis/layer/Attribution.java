/*
 * $ Id $
 * $ Source $
 * Created on Apr 4, 2005
 */
package org.opengis.layer;

import org.opengis.metadata.citation.OnLineResource;
import org.opengis.util.InternationalString;


/**
 * The <code>Attribution</code> class/interface...
 * 
 * @author SYS Technologies
 * @author crossley
 * @version $Revision $
 */
public interface Attribution {

    InternationalString getTitle();
    
    OnLineResource getOnlineResource();
    
    LogoURL getLogoURL();
}
