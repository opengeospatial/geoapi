/*
 * $ Id $
 * $ Source $
 * Created on Apr 4, 2005
 */
package org.opengis.layer;

import org.opengis.metadata.citation.OnLineResource;


/**
 * The <code>AuthorityURL</code> class/interface...
 * 
 * @author SYS Technologies
 * @author crossley
 * @version $Revision $
 */
public interface AuthorityURL {

    String getName();
    
    OnLineResource getOnlineResource();
}
