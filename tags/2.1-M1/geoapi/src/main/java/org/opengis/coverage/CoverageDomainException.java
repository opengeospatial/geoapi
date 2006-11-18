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
package org.opengis.coverage;

// OpenGIS direct dependencies
import org.opengis.coverage.Coverage;
import org.opengis.spatialschema.geometry.DirectPosition;


/**
 * Thrown when a {@link Coverage#evaluate(DirectPosition, java.util.Set) evaluate} method
 * is invoked for a location outside the domain of the coverage.
 *
 * @author Alexander Petkov
 */
public class CoverageDomainException extends CannotEvaluateException {
    /**
     * Serial number for interoperability with different versions.
     */
    private static final long serialVersionUID = -4989587403487024559L;

    /**
     * Represents a direct position which is outside 
     * the domain of the coverage.
     */
    private DirectPosition offendingLocation;

    /**
     * Represents the coverage for which an exception might be thrown. Useful when
     * {@link Coverage} is used on a multilevel, so {@code CoverageDomainException}
     * can provide informative details.
     */
    private Coverage coverage;

    /**
     * Creates an exception with no message.
     */
    public CoverageDomainException() {
        super();
    }

    /**
     * Creates an exception with the specified message.
     *
     * @param  message The detail message. The detail message is saved for 
     *         later retrieval by the {@link #getMessage()} method.
     */
    public CoverageDomainException(String message) {
        super(message);
    }

    /**
     * Returns the {@linkplain DirectPosition direct position}
     * which is outside the domain of the coverage. 
     */
    public DirectPosition getOffendingLocation() {
        return offendingLocation;
    }

    /**
     * Sets the {@linkplain DirectPosition direct position}
     * which is outside the domain of the coverage. 
     */
    public void setOffendingLocation(final DirectPosition offendingLocation) {
        this.offendingLocation = offendingLocation;
    }

    /**
     * Returns the coverage.
     */
    public Coverage getCoverage() {
        return coverage;
    }

    /**
     * Sets the coverage. The {@linkplain #getOffendingLocation offending location}
     * should be within the domain of this coverage, otherwise this exception is thrown.
     */
    public void setCoverage(final Coverage coverage) {
        this.coverage = coverage;
    }
}
