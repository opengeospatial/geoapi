package org.opengis.example.unsafe;

import java.util.Collection;

/**
 * An example of a BAD mutable interface.
 * <p>
 * What makes this interface bad?
 * <ul>
 * <li>Not Thread-Safe: while we have setters we are trusting the application
 * to handle any and all synchronization issues. While this would be okay if we
 * were simply implementing an application, it is not okay from an
 * interoptability standpoint - an application working with a library would have
 * no assurances on how instances are used.
 * <li>No Notification: there is no way to recieve notificaitons when contents
 *   are changed
 * </ul>
 * 
 * @author Jody
 */
public interface Citation {
	String getISBN();
	/**
	 * @return Direct access to CitationDates
	 */
	Collection<CitationDate> getDates();
	
	void setISBN(String isbn);
	void setDates(Collection<CitationDate> dates);
}