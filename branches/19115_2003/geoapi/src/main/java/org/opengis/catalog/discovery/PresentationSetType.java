package org.opengis.catalog.discovery;

import java.util.ArrayList;
import java.util.List;

import org.opengis.util.CodeList;

/**
 * <b>PresentationSetType class</b>
 * 
 * <p>
 * DOCUMENT ME!
 * </p>
 * 
 * @author Mauricio Pazos, Axios Engineering
 * @version $Id$
 */
public class PresentationSetType extends CodeList<PresentationSetType> {
    /** required for interoperability */
    private static final long serialVersionUID = 5274116455095989173L;

    private static final List VALUES = new ArrayList(4);

    public static final PresentationSetType BRIEF = new PresentationSetType("brief");

    public static final PresentationSetType SUMMARY = new PresentationSetType("summary");

    public static final PresentationSetType FULL = new PresentationSetType("full");

    public static final PresentationSetType BROWSE = new PresentationSetType("browse");

    /**
     * Creates a new PresentationSetType object.
     * 
     * @param value
     *            DOCUMENT ME!
     */
    protected PresentationSetType(String value) {
        super(value, VALUES);
    }

    /**
     * DOCUMENT ME!
     * 
     * @return DOCUMENT ME!
     */
    public CodeList[] family() {
        return (PresentationSetType[]) VALUES.toArray(new PresentationSetType[VALUES.size()]);
    }

    /**
     * Returns the list of {@code EvaluationMethodType}s.
     */
    public static PresentationSetType[] values() {
        synchronized (VALUES) {
            return (PresentationSetType[]) VALUES.toArray(new PresentationSetType[VALUES.size()]);
        }
    }

    /**
     * DOCUMENT ME!
     * 
     * @param obj
     *            DOCUMENT ME!
     * 
     * @return DOCUMENT ME!
     */
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof PresentationSetType)) {
            return false;
        }

        final PresentationSetType rt = (PresentationSetType) obj;

        return this.name().equals(rt.name());
    }

}
