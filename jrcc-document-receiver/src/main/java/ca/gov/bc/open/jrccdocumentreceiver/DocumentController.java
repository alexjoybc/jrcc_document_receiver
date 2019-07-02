package ca.gov.bc.open.jrccdocumentreceiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ca.gov.bc.open.jrccaccess.libs.DocumentStorageProperties;
import ca.gov.bc.open.jrccaccess.libs.StorageService;

@Service
public class DocumentController {

	
	private StorageService storageService;
	
	private Logger logger = LoggerFactory.getLogger(DocumentController.class);
	
	
	public DocumentController(StorageService storageService) {
		this.storageService = storageService;
	}
	
	
	public void post(String content) {
		
		logger.info("receiving new document");
		
		DocumentStorageProperties props = this.storageService.putString(content);
		
		logger.info("document successfully stored, key: " + props.getKey());

	}
	
}
