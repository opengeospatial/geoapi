package org.opengis.filter.sort;

import java.util.ArrayList;
import java.util.List;

import org.opengis.util.CodeList;


/**
 * Captures the {@link SortBy} order, {@code ASC} or {@code DESC}.
 * 
 * @see <a href="http://schemas.opengis.net/filter/1.1.0/sort.xsd">
 * @author Jody Garnett, Refractions Research.
 * @since GeoTools 2.2, Filter 1.1
 */
public final class SortOrder extends CodeList<SortOrder> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = 7840334200112859571L;

    /**
     * The list of enumeration available in this virtual machine.
     * <strong>Must be declared first!</strong>.
     */
    private static final List<SortOrder> VALUES = new ArrayList<SortOrder>(2);

    /**
     * Represents acending order.
     * <p>
     * Note this has the string representation of ASC to agree
     * with the Filter 1.1 specification.
     * </p>
     */
    public static final SortOrder ASCENDING  = new SortOrder("ASCENDING", "ASC");

    /**
     * Represents descending order.
     * <p>
     * Note this has the string representation of DESC to agree
     * with the Filter 1.1 specification.
     * </p> 
     */	
    public static final SortOrder DESCENDING = new SortOrder("DESCENDING", "DESC");

    /**
     * The SQL keyword for this sorting order.
     */
    private final String sqlKeyword;

    /**
     * Constructs an enum with the given name. The new enum is
     * automatically added to the list returned by {@link #values}.
     *
     * @param name The enum name. This name must not be in use by an other enum of this type.
     * @param sqlKeyword The SQL keyword for this sorting order.
     *
     * @todo Should invoke {code super(name)}, not {@code super(sqlKeyword}).
     */
    private SortOrder(final String name, final String sqlKeyword) {
//        super(name, VALUES);
        super(sqlKeyword, VALUES);
        this.sqlKeyword = sqlKeyword;
    }

    /**
     * Returns the SQL keyword for this sorting order.
     * This is either {@code "ASC"} or {@code "DESC"}.
     */
    public String sqlKeyword() {
        return sqlKeyword;
    }

    /**
     * Returns the list of {@code SortOrder}s.
     */
    public static SortOrder[] values() {
        synchronized (VALUES) {
            return (SortOrder[]) VALUES.toArray(new SortOrder[VALUES.size()]);
        }
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public /*{SortOrder}*/ CodeList[] family() {
        return values();
    }
}
