/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2004-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.citation;

// OpenGIS direct dependencies
import org.opengis.util.InternationalString;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Information about the series, or aggregate dataset, to which a dataset belongs.
 *
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 1.0
 */
@UML(identifier="CI_Series", specification=ISO_19115)
public interface Series {
    /**
     * Name of the series, or aggregate dataset, of which the dataset is a part.
     * Returns {@code null} if none.
     */
    @UML(identifier="name", obligation=OPTIONAL, specification=ISO_19115)
    InternationalString getName();

    /**
     * Information identifying the issue of the series.
     */
    @UML(identifier="issueIdentification", obligation=OPTIONAL, specification=ISO_19115)
    String getIssueIdentification();

    /**
     * Details on which pages of the publication the article was published.
     */
    @UML(identifier="page", obligation=OPTIONAL, specification=ISO_19115)
    String getPage();
}
