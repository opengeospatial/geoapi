package org.opengis.filter;

import java.io.Serializable;

/**
 * Indicating "filter all", evaultates to <code>false</code>.
 * <p>
 * This is a placeholder filter intended to be used in data structuring
 * definition.
 * <ul>
 * <li>
 * <ul>
 * EXCLUDE or Filter ==> Filter
 * <ul>
 * EXCLUDE and Filter ==> EXCLUDE
 * <ul>
 * not EXCLUDE ==> INCLUDE
 * </ul>
 * The above does imply that the AND opperator can short circuit on encountering
 * ALL.
 * </p>
 *
 * @author Jody Garnett, Refractions Research, Inc.
 */
public final class ExcludeFilter implements Filter, Serializable {
    private static final long serialVersionUID = -716705962006999508L;

    /** non extensible */
    ExcludeFilter() {
    }

    public Object accept(FilterVisitor visitor, Object extraData) {
        return visitor.visit(this, extraData);
    }

    /**
     * Returns <code>false</code>, content is excluded.
     */
    public boolean evaluate(Object object) {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        return Filter.EXCLUDE == obj;
    }

    @Override
    public int hashCode() {
        return 1;
    }

    public String toString() {
        return "Filter.EXCLUDE";
    }
}
