/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.coverage.grid;

import javax.imageio.IIOException;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Thrown when a {@linkplain GridCoverage grid coverage} can't be exported in the specified
 * format.
 *
 * <P>&nbsp;</P>
 * <TABLE WIDTH="80%" ALIGN="center" CELLPADDING="18" BORDER="4" BGCOLOR="#FFE0B0">
 *   <TR><TD>
 *     <P align="justify"><STRONG>WARNING: THIS CLASS WILL CHANGE.</STRONG> Current API is derived from OGC
 *     <A HREF="http://www.opengis.org/docs/01-004.pdf">Grid Coverages Implementation specification 1.0</A>.
 *     We plan to replace it by new interfaces derived from ISO 19123 (<CITE>Schema for coverage geometry
 *     and functions</CITE>). Current interfaces should be considered as legacy and are included in this
 *     distribution only because they were part of GeoAPI 1.0 release. We will try to preserve as much 
 *     compatibility as possible, but no migration plan has been determined yet.</P>
 *   </TD></TR>
 * </TABLE>
 *
 * @version <A HREF="http://www.opengis.org/docs/01-004.pdf">Grid Coverage specification 1.0</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.0
 * @deprecated In favor of migrating to ISO 19123 definition for Coverage.
 *
 * @see GridCoverageWriter#write
 */
@UML(identifier="GC_FileFormatNotCompatibleWithGridCoverage", specification=OGC_01004)
public class FileFormatNotCompatibleWithGridCoverageException extends IIOException {
    /**
     * Serial number for interoperability with different versions.
     */
    private static final long serialVersionUID = 9063391579226867676L;

    /**
     * Creates an exception with no message.
     */
    public FileFormatNotCompatibleWithGridCoverageException() {
        super(null);
    }

    /**
     * Creates an exception with the specified message.
     *
     * @param  message The detail message. The detail message is saved for 
     *         later retrieval by the {@link #getMessage()} method.
     */
    public FileFormatNotCompatibleWithGridCoverageException(String message) {
        super(message);
    }
}
