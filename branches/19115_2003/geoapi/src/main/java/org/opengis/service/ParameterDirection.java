package org.opengis.service;

import static org.opengis.annotation.Obligation.CONDITIONAL;
import static org.opengis.annotation.Specification.ISO_19119;

import java.util.ArrayList;
import java.util.List;

import org.opengis.annotation.UML;
import org.opengis.util.CodeList;

/**
 * Enumeration that indicates if a parameter is an input to the service
 * operation, an output or both.
 * 
 * @author Mauricio Pazos - Axios Engineering
 * @author Gabriel Roldan - Axios Engineering
 
 * @since GeoAPI 2.1
 */
@UML(identifier = "SV_ParameterDirection", specification = ISO_19119)
public class ParameterDirection extends CodeList<ParameterDirection> {

    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = 8990332966064976454L;

    /**
     * List of all enumerations of this type. Must be declared before any enum
     * declaration.
     */
    private static final List<ParameterDirection> VALUES = new ArrayList<ParameterDirection>(3);

    /**
     * Indicates a service operation parameter direction is IN
     */
    @UML(identifier = "in", obligation = CONDITIONAL, specification = ISO_19119)
    public static final ParameterDirection IN = new ParameterDirection("IN");

    /**
     * Indicates a service operation parameter direction's is OUT
     */
    @UML(identifier = "out", obligation = CONDITIONAL, specification = ISO_19119)
    public static final ParameterDirection OUT = new ParameterDirection("OUT");

    /**
     * Indicates a service operation parameter direction is both IN and OUT
     */
    @UML(identifier = "in/out", obligation = CONDITIONAL, specification = ISO_19119)
    public static final ParameterDirection IN_OUT = new ParameterDirection("IN/OUT");

    /**
     * Constructs an enum with the given name. The new enum is automatically
     * added to the list returned by {@link #values}.
     * 
     * @param name
     *            The enum name. This name must not be in use by an other enum
     *            of this type.
     */
    private ParameterDirection(String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code ParamteterDirection}s.
     */
    public static ParameterDirection[] values() {
        synchronized (VALUES) {
            return (ParameterDirection[]) VALUES.toArray(new ParameterDirection[VALUES.size()]);
        }
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public /*{ParameterDirection}*/CodeList[] family() {
        return values();
    }

}
