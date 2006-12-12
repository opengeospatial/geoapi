/*
 * Copyright (c) 2006 - Gipuzkoako Foru Aldundia
 * http://b5m.gipuzkoa.net.  All rights reserved.
 */
package org.opengis.catalog.discovery;

import java.util.ArrayList;
import java.util.List;

import org.opengis.util.CodeList;

/**
 * ResourceType CodeList
 * 
 * <p>
 * A catalogue may contain references to several different resource types. This
 * parameter provides for the selection of one of those types for processing.
 * </p>
 * 
 * @author Mauricio Pazos, Axios Engineering
 * @version $Id$
 */
public final class ResourceType extends CodeList<ResourceType>{

    /**
     * required for interoperability
     */
    private static final long serialVersionUID = 5274116455095989173L;

    private static final List VALUES = new ArrayList(4);

    public static final ResourceType NULL = new ResourceType("undefined");

    /**
     * The lowest level packaging of Features that have been catalogued.
     */
    public static final ResourceType DATA_SET = new ResourceType("DataSet");

    /**
     * A grouping of data sets that have commonality (ISO 19115: data set
     * series).
     */
    public static final ResourceType DATA_SET_COLLECTION = new ResourceType("DataSetCollection");

    /**
     * A set of interfaces that provide access to or operations on data (e.g.
     * catalogue service).
     */
    public static final ResourceType SERVICE = new ResourceType("Service");

    public CodeList[] family() {
        return (ResourceType[]) VALUES.toArray(new ResourceType[VALUES.size()]);
    }

    protected ResourceType(String value) {
        super(value, VALUES);
    }

    /**
     * Equals if they have the same code value
     * 
     * @param obj
     * 
     * @return boolean
     */
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ResourceType)) {
            return false;
        }

        final ResourceType rt = (ResourceType) obj;

        return this.name().equals(rt.name());
    }

    /**
     * Returns the list of {@code EvaluationMethodType}s.
     */
    public static ResourceType[] values() {
        synchronized (VALUES) {
            return (ResourceType[]) VALUES.toArray(new ResourceType[VALUES.size()]);
        }
    }
}
