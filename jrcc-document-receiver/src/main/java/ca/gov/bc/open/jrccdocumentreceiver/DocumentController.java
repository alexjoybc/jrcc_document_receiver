package ca.gov.bc.open.jrccdocumentreceiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ca.gov.bc.open.jrccaccess.libs.DocumentOutput;
import ca.gov.bc.open.jrccaccess.libs.DocumentStorageProperties;
import ca.gov.bc.open.jrccaccess.libs.StorageService;
import ca.gov.bc.open.jrccaccess.libs.TransactionInfo;

/**
 * The Document Controller is used to post new document to the receiver
 * @author alexjoybc
 *
 */
@Service
public class DocumentController {

	private DocumentOutput documentOutput;

	private Logger logger = LoggerFactory.getLogger(DocumentController.class);
	
	/**
	 * Default constructor
	 * @param storageService A Storage service
	 */
	public DocumentController(DocumentOutput documentOutput) {
		this.documentOutput = documentOutput;
	}

	/**
	 * Post a new document
	 * @param content The content of the document
	 */
	public void post(String content, TransactionInfo transactionInfo) {


		logger.info("new Transaction [{}].", transactionInfo);
		
		this.documentOutput.send(content, transactionInfo);
			

	}
}
