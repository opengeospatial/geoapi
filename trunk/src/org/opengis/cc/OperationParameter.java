/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.cc;

// J2SE direct dependencies
import java.util.Locale;

// OpenGIS direct dependencies
import org.opengis.rs.Identifier;


/**
 * The definition of a parameter used by an operation method. Most parameter values are
 * numeric, but other types of parameter values are possible.
 *  
 * @UML abstract CC_OperationParameter
 * @author ISO 19111
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 */
public interface OperationParameter extends GeneralOperationParameter {
    /**
     * The name by which this operation parameter is identified.
     *
     * @return The operation parameter name.
     * @UML mandatory parameterName
     *
     * @rename Omitted the "<code>parameter</code>" prefix.
     */
    public String getName();

    /**
     * Set of alternative identifications of this operation parameter. The
     * first identifier, if any, is normally the primary identification code,
     * and any others are aliases.
     *
     * @return The operation parameter identifiers, or an empty array if there is none.
     * @UML optional parameterID
     *
     * @rename  Omitted the "<code>parameter</code>" prefix.
     *          Replaced "<code>ID</code>" by "<code>Identifiers</code>" in order to
     *          1) use the return type class name and 2) use the plural form.
     */
    public Identifier[] getIdentifiers();

    /**
     * Comments on the operation parameter, including source information.
     *
     * @param  locale The desired locale for the remarks to be returned,
     *         or <code>null</code> for a non-localized string (or a default default locale).
     * @return The operation parameter remarks, or <code>null</code> if not available.
     * @UML optional remarks
     */
    public String getRemarks(Locale locale);
}
