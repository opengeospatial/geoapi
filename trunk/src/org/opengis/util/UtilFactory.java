/*
 * $ Id $
 * $ Source $
 * Created on Jan 27, 2005
 */
package org.opengis.util;

import java.util.Locale;


/**
 * The <code>UtilFactory</code> class/interface...
 * 
 * @author SYS Technologies
 * @author crossley
 * @version $Revision $
 */
public interface UtilFactory {
    
    /**
     * DOCUMENT ME.
     * @param localizedStrings
     * @param locales
     * @return
     */
    InternationalString createInternationalString(String[] localizedStrings, Locale[] locales);
    
    /**
     * DOCUMENT ME.
     * @param scope
     * @param name
     * @param localizedName
     * @return
     */
    LocalName createLocalName(GenericName scope, String name, InternationalString localizedName);
    
    /**
     * DOCUMENT ME.
     * @param scope
     * @param name
     * @param localizedName
     * @return
     */
    ScopedName createScopedName(GenericName scope, String name, InternationalString localizedName);
    
}
