package ca.gov.bc.open.jrccdocumentreceiver;


import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import ca.gov.bc.open.jrccaccess.libs.DocumentStorageProperties;
import ca.gov.bc.open.jrccaccess.libs.StorageService;

public class DocumentControllerTester {

	
	private static final String MD5 = "F2BFA7FC155C4F42CB91404198DDA01F";

	private static final String KEY = "key";

	@Mock
	private StorageService storageService;
	
	private DocumentController sut;
	
	@Before
	public void init() {
		
		Mockito.when(storageService.putString(Mockito.anyString())).thenReturn(new DocumentStorageProperties(KEY, MD5));
		sut = new DocumentController(storageService);
	}
	
	
	@Test
	public void with_valid_content_should_be_handled() {
		
		
		DocumentStorageProperties props = sut.post("awesome content");
		
		assertEquals(KEY, props.getKey());
		assertEquals(MD5, props.getMD5());
		
		
	}
	
	
}
