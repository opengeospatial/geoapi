/*
 * $ Id $
 * $ Source $
 * Created on Apr 4, 2005
 */
package org.opengis.layer;



/**
 * The <code>AbstractSizedURL</code> class/interface...
 * 
 * @author SYS Technologies
 * @author crossley
 * @version $Revision $
 */
public interface AbstractSizedURL extends AbstractURL {

    int getWidth();
    
    int getHeight();

}
