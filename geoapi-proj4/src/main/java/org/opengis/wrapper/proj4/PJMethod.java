/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 *
 *    The Proj.4 wrappers are provided as code examples, in the hope to facilitate
 *    GeoAPI implementations backed by other libraries. Implementers can take this
 *    source code and use it for any purpose, commercial or non-commercial, copyrighted
 *    or open-source, with no legal obligation to acknowledge the borrowing/copying
 *    in any way.
 */
package org.opengis.wrapper.proj4;

import java.util.Collection;
import org.opengis.util.GenericName;
import org.opengis.parameter.ParameterDescriptorGroup;
import org.opengis.referencing.ReferenceIdentifier;
import org.opengis.referencing.operation.Formula;
import org.opengis.referencing.operation.OperationMethod;


/**
 * Information about a Proj.4 projection method.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
final class PJMethod extends PJObject implements OperationMethod {
    /**
     * Creates a new operation method.
     */
    PJMethod(final ReferenceIdentifier name, final Collection<GenericName> aliases) {
        super(name, aliases);
    }

    /**
     * Returns {@code null}, since we have no information about the formulas used.
     */
    @Override
    public Formula getFormula() {
        return null;
    }

    /**
     * Returns the number of source dimensions, which is 2.
     *
     * @deprecated This attribute has been removed from ISO 19111:2019.
     */
    @Override
    @Deprecated
    public Integer getSourceDimensions() {
        return 2;
    }

    /**
     * Returns the number of target dimensions, which is 2.
     *
     * @deprecated This attribute has been removed from ISO 19111:2019.
     */
    @Override
    @Deprecated
    public Integer getTargetDimensions() {
        return 2;
    }

    /**
     * Creates a parameter group. We can not provides the descriptors for parameter values,
     * since we don not know them.
     */
    @Override
    public ParameterDescriptorGroup getParameters() {
        return new PJParameterGroup(name, aliases);
    }
}
