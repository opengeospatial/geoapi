/**************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/

package org.opengis.go.display.style;

/**
 * The <code>Editability</code> interface encapsulates the editability attributes
 * that can be applied to any Graphic.
 *
 * @author Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 */
public interface Editability {
    
    /**
     * Default auto edit value.
     */
    public static final boolean DEFAULT_AUTO_EDIT = true;

    /**
     * Default drag selectable value.
     */
    public static final boolean DEFAULT_DRAG_SELECTABLE = true;

    /**
     * Default pickable value.
     */
    public static final boolean DEFAULT_PICKABLE = true;

    /**
     * Default selected value.
     */
    public static final boolean DEFAULT_SELECTED = false;
    
    /**
     * Auto edit attribute name.
     */
    public static final String AUTO_EDIT = "EDITABILITY_AUTO_EDIT";

    /**
     * Drag selectable attribute name.
     */
    public static final String DRAG_SELECTABLE = "EDITABILITY_DRAG_SELECTABLE";
    
    /**
     * Pickable attribute name.
     */
    public static final String PICKABLE = "EDITABILITY_PICKABLE";

    /**
     * Selected attribute name.
     */
    public static final String SELECTED = "EDITABILITY_SELECTED"; 
    
    /**
     * Returns the auto edit value.
     * @return the auto edit value.
     */
    public boolean getAutoEdit();
    
    /**
     * Returns whether the auto edit value has been set.
     * @return true if the auto edit value has been set, false otherwise.
     */    
    public boolean isAutoEditSet();
    
    /**
     * Sets the auto edit value.
     * @param autoEdit the auto edit value.
     */    
    public void setAutoEdit(boolean autoEdit);
    
    /**
     * Sets the fact that the auto edit value has been set.
     * @param flag true if the auto edit value has been set, false otherwise.
     */    
    public void setAutoEditSet(boolean flag);
   
    /**
     * Returns the drag selectable value.
     * @return the drag selectable value.
     */
    public boolean getDragSelectable();
    
    /**
     * Returns whether the drag selectable value has been set.
     * @return true if the drag selectable value has been set, false otherwise.
     */    
    public boolean isDragSelectableSet();
    
    /**
     * Sets the drag selectable value.
     * @param dragSelectable the drag selectable value.
     */    
    public void setDragSelectable(boolean dragSelectable);
    
    /**
     * Sets the fact that the drag selectable value has been set.
     * @param flag true if the drag selectable value has been set, false otherwise.
     */    
    public void setDragSelectableSet(boolean flag);
      
    /**
     * Returns the pickable value.
     * @return the pickable value.
     */
    public boolean getPickable();
    
    /**
     * Returns whether the pickable value has been set.
     * @return true if the pickable value has been set, false otherwise.
     */    
    public boolean isPickableSet();
    
    /**
     * Sets the pickable value.
     * @param pickable the pickable value.
     */    
    public void setPickable(boolean pickable);
    
    /**
     * Sets the fact that the pickable value has been set.
     * @param flag true if the pickable value has been set, false otherwise.
     */    
    public void setPickableSet(boolean flag);
   
    /**
     * Returns the selected value.
     * @return the selected value.
     */
    public boolean getSelected();
    
    /**
     * Returns whether the selected value has been set.
     * @return true if the selected value has been set, false otherwise.
     */    
    public boolean isSelectedSet();
    
    /**
     * Sets the selected value.
     * @param selected the selected value.
     */    
    public void setSelected(boolean selected);
    
    /**
     * Sets the fact that the selected value has been set.
     * @param flag true if the selected value has been set, false otherwise.
     */    
    public void setSelectedSet(boolean flag);
   
}
