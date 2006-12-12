package org.opengis.catalog.manager;

/**
 * @author <a href="mailto:poth@lat-lon.de">Andreas Poth </a>
 * @author <a href="mailto:tfr@users.sourceforge.net">Torsten Friebe </a>
 * 
 * @author Last update: Mauricio Pazos - Axios Engineering
 */
public interface Manager {

	HarvestResult harvestRecords(HarvestRecords request)throws TransactionFailedException;

    TransactionResponse transaction(TransactionRequest request)throws TransactionFailedException;

}
