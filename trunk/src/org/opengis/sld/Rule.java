/*
 * Created on Aug 3, 2004
 */
package org.opengis.sld;

import java.util.List;

public interface Rule {
    public String getName();
    public void setName(String name);

    public String getTitle();
    public void setTitle(String name);

    public String getAbstract();
    public void setAbstract(String abs);

    public Graphic getLegendGraphic();
    public void setLegendGraphic(Graphic g);

    /**
     * This method returns a pointer to the "live" list of Filter and ElseFilter
     * objects contained by this Rule.  This list can be modified by the caller,
     * so there is no need for a setFilterOrElseFilter method.  Only objects of
     * type Filter and ElseFilter can be present in the list.  Attempts to add
     * other objects may throw IllegalArgumentException or ClassCastException.
     */
    public List getFilterOrElseFilter();

    public double getMinScaleDenominator();
    public void setMinScaleDenominator(double d);

    public double getMaxScaleDenominator();
    public void setMaxScaleDenominator(double d);

    /**
     * This method returns a pointer to the "live" list of Symbol objects
     * contained by this Rule.  This list can be modified by the caller, so
     * there is no need for a setSymbols method.  Only objects of type Symbol
     * are allowed in this list.  Attempts to add other objects may throw
     * IllegalArgumentException or ClassCastException.
     */
    public List getSymbols();
}
