/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.cc;

// J2SE direct dependencies
import java.util.Locale;

// OpenGIS direct dependencies
import org.opengis.rs.Identifier;


/**
 * The definition of a group of related parameters used by an operation method.
 *  
 * @UML abstract CC_OperationParameterGroup
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 */
public interface OperationParameterGroup extends GeneralOperationParameter {
    /**
     * The name by which this operation parameter group is identified.
     *
     * @return The operation parameter group name.
     * @UML mandatory groupName
     */
    public String getName();

    /**
     * Set of alternative identifications of this parameter group. The
     * first identifier, if any, is normally the primary identification code,
     * and any others are aliases.
     *
     * @return The parameter group identifiers, or an empty array if there is none.
     * @UML optional methodID
     *
     * @rename  Omitted the "<code>group</code>" prefix.
     *          Replaced "<code>ID</code>" by "<code>Identifiers</code>" in order to
     *          1) use the return type class name and 2) use the plural form.
     */
    public Identifier[] getIdentifiers();

    /**
     * Returns the parameters in this group.
     *
     * @return The parameters.
     */
    public GeneralOperationParameter[] getParameters();

    /**
     * The maximum number of times that values for this parameter group or
     * parameter can be included. The default value is one.
     *
     * @return The maximum occurrences.
     * @UML optional maximumOccurs
     *
     * @see #getMinimumOccurs
     *
     * @revisit Why this method is not declared in the same interface than
     *          {@link #getMinimumOccurs}?
     */
    public int getMaximumOccurs();

    /**
     * Comments on the parameter group, including source information.
     *
     * @param  locale The desired locale for the remarks to be returned,
     *         or <code>null</code> for a non-localized string (or a default default locale).
     * @return The parameter group remarks, or <code>null</code> if not available.
     * @UML optional remarks
     */
    public String getRemarks(Locale locale);
}
