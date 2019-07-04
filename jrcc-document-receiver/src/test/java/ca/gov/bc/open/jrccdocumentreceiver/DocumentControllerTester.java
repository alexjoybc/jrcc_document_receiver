package ca.gov.bc.open.jrccdocumentreceiver;


import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import ca.gov.bc.open.jrccaccess.libs.DocumentOutput;
import ca.gov.bc.open.jrccaccess.libs.DocumentStorageProperties;
import ca.gov.bc.open.jrccaccess.libs.StorageService;
import ca.gov.bc.open.jrccaccess.libs.TransactionInfo;

public class DocumentControllerTester {

	
	private static final String MD5 = "F2BFA7FC155C4F42CB91404198DDA01F";

	private static final String KEY = "key";

	@Mock
	private DocumentOutput documentOutput;
	
	private DocumentController sut;
	
	@Before
	public void init() {
		
		MockitoAnnotations.initMocks(this);
		Mockito.doNothing().when(this.documentOutput).send(Mockito.anyString(), Mockito.any());
		sut = new DocumentController(documentOutput);
	}
	
	
	@Test
	public void with_valid_content_should_be_handled() {
		sut.post("awesome content", new TransactionInfo("test.txt", "jrcc-receiver", LocalDateTime.now()));

	}
	
	
}
