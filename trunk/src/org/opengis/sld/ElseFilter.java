package org.opengis.sld;

/**
 * An instance of this interface serves as a filter that evaluates to true only
 * if no other filters in the containing Rule have yet evaluated to true.
 * <p>
 * This interface has no methods, so implementations are free to use a
 * singleton if it makes sense.
 */
public interface ElseFilter {
}
