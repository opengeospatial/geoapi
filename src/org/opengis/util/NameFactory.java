/*
 * $ Id $
 * $ Source $
 * Created on Jan 27, 2005
 */
package org.opengis.util;

import java.util.Locale;
import java.util.Map;


/**
 * The <code>NameFactory</code> class/interface...
 * 
 * @author Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 */
public interface NameFactory {
    
    /**
     * DOCUMENT ME.
     * @param localizedStrings
     * @param locales
     * @return
     */
    InternationalString createInternationalString(Map<Locale, String> params);
    
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
