package ca.gov.bc.open.jrccdocumentreceiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import ca.gov.bc.open.jrccaccess.libs.DocumentStorageProperties;
import ca.gov.bc.open.jrccaccess.libs.StorageService;

/**
 * The Document Controller is used to post new document to the receiver
 * @author alexjoybc
 *
 */
@Service
public class DocumentController {

	private StorageService storageService;

	private Logger logger = LoggerFactory.getLogger(DocumentController.class);
	

	public DocumentController(StorageService storageService) {
		this.storageService = storageService;
	}

	/**
	 * Post a new document
	 * @param content
	 */
	public DocumentStorageProperties post(String content) {

			logger.info("receiving new document");

			return this.storageService.putString(content);

	}
}
