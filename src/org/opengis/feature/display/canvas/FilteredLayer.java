package org.opengis.feature.display.canvas;

import org.opengis.filter.Filter;

/**
 * Layer containing all the Features of a given type that pass some Filter.
 */
public interface FilteredLayer extends Layer {
    /**
     * Returns the filter that this layer was created with.  Although Filter
     * objects are mutable, the filter should not be modified once the layer
     * has been created.
     */
    public Filter getFilter();
}
