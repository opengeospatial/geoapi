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
 * Encapsulates the editability attributes that can be applied to any
 * {@link org.opengis.go.display.primitive.Graphic}.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version $Revision$, $Date$
 */
public interface Editability {

    //*************************************************************************
    //  Static Fields
    //*************************************************************************
      
    //**  Editability property names  **
    
    public static final String EDITABILITY_AUTO_EDIT = "EDITABILITY_AUTO_EDIT";    
    public static final String EDITABILITY_DRAG_SELECTABLE = "EDITABILITY_DRAG_SELECTABLE";    
    public static final String EDITABILITY_PICKABLE = "EDITABILITY_PICKABLE";    
    public static final String EDITABILITY_SELECTED = "EDITABILITY_SELECTED";  
    
    //**  Default Editability property values  **
    
    /**  Default auto edit value.  */
    public static final boolean DEFAULT_EDITABILITY_AUTO_EDIT = true;

    /**  Default drag selectable value.  */
    public static final boolean DEFAULT_EDITABILITY_DRAG_SELECTABLE = true;

    /** Default pickable value.  */
    public static final boolean DEFAULT_EDITABILITY_PICKABLE = true;

    /**  Default selected value.  */
    public static final boolean DEFAULT_EDITABILITY_SELECTED = false;
    
    //*************************************************************************
    //  Methods
    //*************************************************************************
    
    /**
     * Returns the auto edit value.
     * @return the auto edit value.
     */
    public boolean getEditabilityAutoEdit();
    
    /**
     * Returns whether the auto edit value has been set.
     * @return true if the auto edit value has been set, false otherwise.
     */    
    public boolean isEditabilityAutoEditSet();
    
    /**
     * Sets the auto edit value.
     * @param autoEdit the auto edit value.
     */    
    public void setEditabilityAutoEdit(boolean autoEdit);
    
    /**
     * Sets the fact that the auto edit value has been set.
     * @param flag true if the auto edit value has been set, false otherwise.
     */    
    public void setEditabilityAutoEditSet(boolean flag);
   
    /**
     * Returns the drag selectable value.
     * @return the drag selectable value.
     */
    public boolean getEditabilityDragSelectable();
    
    /**
     * Returns whether the drag selectable value has been set.
     * @return true if the drag selectable value has been set, false otherwise.
     */    
    public boolean isEditabilityDragSelectableSet();
    
    /**
     * Sets the drag selectable value.
     * @param dragSelectable the drag selectable value.
     */    
    public void setEditabilityDragSelectable(boolean dragSelectable);
    
    /**
     * Sets the fact that the drag selectable value has been set.
     * @param flag true if the drag selectable value has been set, false otherwise.
     */    
    public void setEditabilityDragSelectableSet(boolean flag);
      
    /**
     * Returns the pickable value.
     * @return the pickable value.
     */
    public boolean getEditabilityPickable();
    
    /**
     * Returns whether the pickable value has been set.
     * @return true if the pickable value has been set, false otherwise.
     */    
    public boolean isEditabilityPickableSet();
    
    /**
     * Sets the pickable value.
     * @param pickable the pickable value.
     */    
    public void setEditabilityPickable(boolean pickable);
    
    /**
     * Sets the fact that the pickable value has been set.
     * @param flag true if the pickable value has been set, false otherwise.
     */    
    public void setEditabilityPickableSet(boolean flag);
   
    /**
     * Returns the selected value.
     * @return the selected value.
     */
    public boolean getEditabilitySelected();
    
    /**
     * Returns whether the selected value has been set.
     * @return true if the selected value has been set, false otherwise.
     */    
    public boolean isEditabilitySelectedSet();
    
    /**
     * Sets the selected value.
     * @param selected the selected value.
     */    
    public void setEditabilitySelected(boolean selected);
    
    /**
     * Sets the fact that the selected value has been set.
     * @param flag true if the selected value has been set, false otherwise.
     */    
    public void setEditabilitySelectedSet(boolean flag);
   
}

