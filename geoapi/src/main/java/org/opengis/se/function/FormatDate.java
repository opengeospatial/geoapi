/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2008 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.se.function;

import org.opengis.annotation.XmlElement;
import org.opengis.filter.expression.Expression;

/**
 * This function is used for several date types. The argument of the function can consist of
 * one of the following ISO 8601 XML schema types:
 * dateTime
 * time
 * date
 * gYearMonth
 * gMonthDay
 * gDay
 * gMonth
 * gYear
 * or
 * gml:TimeInstant
 * 
 * Standard output is as defined in ISO 8601: YYYY-MM-DDThh:mm:ss
 * 
 * @version <A HREF="http://www.opengeospatial.org/standards/symbol">Symbology Encoding Implementation Specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.2
 */
@XmlElement("FormatDate")
public interface FormatDate extends Function<String>{
        
    /**
     * Get value to format.
     * 
     * @return Expression
     */
    @XmlElement("DateValue")
    Expression getDateValue();
    
    /**
     * See {@link #getDateValue} for details.
     * 
     * @param exp
     */
    @XmlElement("DateValue")
    void setDateValue(Expression exp);
    
    /**
     * Set the pattern format.
     * The Pattern is build-up of characters as follows :
     * 
     * <table>
     *  <tr><td>YYYY</td><td>Four digit year</td></tr>
     *  <tr><td>YY</td><td> Two digit year (without century and millennium)</td></tr>
     *  <tr><td>MM</td><td>Two digit month</td></tr>
     *  <tr><td>M</td><td>Month, leading zero omitted</td></tr>
     *  <tr><td>MMM</td><td>Month displayed by three letter acronym (“FEB”),
     *    ISO 639 two-letter language codes as defined by ISO 639 can be
     *    appended to create language-dependent variants (MMMde would
     *    yield “DEZ” instead of “DEC”)</td></tr>
     *  <tr><td>MMMMM</td><td>for display of full month (“February”).
     *    The two-letter language code can be appended (MMMMMde would
     *    result in ‘Februar’).</td></tr>
     *  <tr><td>DD</td><td>Two digit day</td></tr>
     *  <tr><td>D</td><td>Day, leading zero omitted</td></tr>
     *  <tr><td>hh</td><td>hour, h is used to omit a leading zero</td></tr>
     *  <tr><td>mm</td><td>minute, m is used to omit a leading zero</td></tr>
     *  <tr><td>ss</td><td>second, s is used to omit a leading zero</td></tr>
     *  <tr><td>.</td><td>point, will appear literally in the result</td></tr>
     *  <tr><td>/</td><td>slash, literally</td></tr>
     *  <tr><td>:</td><td>colon. literally</td></tr>
     *  <tr><td>-</td><td>minus, literally</td></tr>
     *  <tr><td>\</td><td>backslash is employed to quote any character, which is to appear
     *    literally in the result.</td></tr>
     *  <tr><td>a</td><td>am/pm marker</td></tr>
     *  <tr><td>z</td><td>z: time zone (if present e.g. Pacific Standard Time; PST; GMT-08:00)</td></tr>
     * </table>
     * 
     * @return String
     */
    @XmlElement("Pattern")
    String getPattern();
    
    /**
     * Set the date pattern.
     * See {@link #getPattern} for details.
     * 
     * @param pattern 
     */
    @XmlElement("Pattern")
    void setPattern(String pattern);
    
}