package org.opengis.coverage;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;

// Annotations
import org.opengis.annotation.UML;

import org.opengis.coverage.Coverage;

//OpenGIS direct dependencies
import org.opengis.spatialschema.geometry.DirectPosition;

/**
 * <p></p>
 * This exception is thrown when Coverage.evaluate()
 * needs to indicate that the location (or the DirectPosition datatype) specified is
 * outside the domain of the coverage.
 * @author Alexander Petkov
 */
public class CoverageDomainException extends Exception {
    /**
     * <p>Represents a direct position which is outside 
     * the domain of the coverage.</p>
     */
    private DirectPosition offendingLocation;

    /**
     * <p>Represents the coverage for 
     * which an exception might be thrown. Useful when Coverage is used on a multilevel, 
     * so CoverageDomainException can provide informative details.</p>
     */
    private Coverage coverage;

    /**
     * Returns the {@link DirectPosition} which is outside 
     * the domain of the coverage. 
     * @return offendingLocation
     */
    public DirectPosition getOffendingLocation() {
        return offendingLocation;
    }

    /**
     * A mutator for setting the {@link DirectPosition offendingLocation} 
     * @param _offendingLocation the {@link DirectPosition} 
     * which is outside the domain of the coverage. 
     */
    public void setOffendingLocation(DirectPosition _offendingLocation) {
        offendingLocation = _offendingLocation;
    }

    /**
     * An accessor for {@link #coverage}.
     * @return coverage
     */
    public Coverage getCoverage() {
        return coverage;
    }

    /**
     * A mutator for setting the {@link Coverage coverage}. 
     * {@link #offendingLocation} should be within the
     * domain of {@link #coverage}, otherwise this exception is thrown.  
     * @param _coverage
     */
    public void setCoverage(Coverage _coverage) {
        coverage = _coverage;
    }
}
