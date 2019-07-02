package ca.gov.bc.open.jrccdocumentreceiver;

import java.io.File;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {

	@Autowired
	private DocumentController documentController;
	
	
	@Value("classpath:data/sample.xml")
	Resource resourceFile;
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
	
		File resource = resourceFile.getFile();
		String contentString = new String(Files.readAllBytes(resource.toPath()));
		
		System.out.println(contentString);
		
		
	}

}
