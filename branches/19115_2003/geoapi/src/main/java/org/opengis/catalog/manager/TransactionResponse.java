package org.opengis.catalog.manager;

/**
 * A summary of the transaction results that identifies newly created entries when applicable.
 * Most contents of the result depend on the types of data defined by the specific protocol
 * binding and Application Profile. 
 *  
 * @author Mauricio Pazos - Axios Engineering
 * @author Gabriel Roldan - Axios Engineering
 */
public interface TransactionResponse {

	TransactionSummaryType getTransactionSummary();

	
}
