package org.opengis.service;

import static org.opengis.annotation.Specification.UNSPECIFIED;

import java.util.ArrayList;
import java.util.List;

import org.opengis.annotation.UML;
import org.opengis.util.CodeList;

/**
 * Indication of the optionality of presence of a {@link Parameter}
 * <p>
 * Difference with the spec here: we define this CodeList, spec states
 * optionality as a character string.
 * </p>
 * 
 * @author Mauricio Pazos - Axios Engineering
 * @author Gabriel Roldan - Axios Engineering
 */
@UML(identifier="Optionality", specification=UNSPECIFIED)
public class Optionality extends CodeList<Optionality> {

    /**
     * for version control
     */
    private static final long serialVersionUID = -3427175690956905357L;

    /**
     * List of all enumerations of this type. Must be declared before any enum
     * declaration.
     */
    private static final List<Optionality> VALUES = new ArrayList<Optionality>(3);

    public static final Optionality MANDATORY = new Optionality("Mandatory");

    public static final Optionality OPTIONAL = new Optionality("Optional");

    private Optionality(String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code Optionality}s.
     */
    public static Optionality[] values() {
        synchronized (VALUES) {
            return (Optionality[]) VALUES.toArray(new Optionality[VALUES.size()]);
        }
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     * 
     * @return
     */
    public CodeList<Optionality>[] family() {
        return values();
    }

}
