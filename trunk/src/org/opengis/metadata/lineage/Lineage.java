/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.lineage;

// J2SE direct dependencies
import java.util.Locale;


/**
 * Information about the events or source data used in constructing the data specified by
 * the scope or lack of knowledge about lineage.
 *
 * Only one of {@linkplain #getStatement statement}, {@linkplain #getProcessStep process step}
 * and {@link #getSource source} should be provided.
 *
 * @UML datatype LI_Lineage
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
public interface Lineage {
    /**
     * General explanation of the data producer’s knowledge about the lineage of a dataset.
     * Should be provided only if {@linkplain Scope#getLevel scole level} is
     * {@link org.opengis.metadata.ScopeCode#DATASET dataset} or
     * {@link org.opengis.metadata.ScopeCode#SERIES series}.
     *
     * @param  locale The desired locale for the description to be returned, or <code>null</code>
     *         for a description in some default locale (may or may not be the
     *         {@linkplain Locale#getDefault() system default}).
     * @return The description in the given locale.
     *         If no description is available in the given locale, then some default locale is used.
     * @UML conditional statement
     */
    String getStatement(Locale locale);

    /**
     * Information about an event in the creation process for the data specified by the scope.
     *
     * @UML conditional processStep
     */
    ProcessStep[] getProcessSteps();

    /**
     * Information about the source data used in creating the data specified by the scope.
     *
     * @UML conditional source
     */
    Source[] getSources();
}
