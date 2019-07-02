package ca.gov.bc.open.jrccdocumentreceiver;

import java.io.File;
import java.nio.file.Files;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.stereotype.Component;

import brave.Span;
import brave.Tracer;
import brave.Tracer.SpanInScope;
import ca.gov.bc.open.jrccaccess.libs.DocumentStorageProperties;

@Component
public class AppRunner implements CommandLineRunner {

	@Autowired
	private DocumentController documentController;

	@Value("classpath:data/sample.xml")
	Resource resourceFile;

	@Autowired
	private Tracer tracer;

	private Logger logger = LoggerFactory.getLogger(AppRunner.class);

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

		File resource = resourceFile.getFile();
		String contentString = new String(Files.readAllBytes(resource.toPath()));

		Span newSpan = tracer.nextSpan().name("ReceiveDocument").start();

		try (SpanInScope ws = tracer.withSpanInScope(newSpan.start())) {

			DocumentStorageProperties props = this.documentController.post(contentString);
			
			logger.info("Document successfully stored, key: " + props.getKey());

		} catch (RedisConnectionFailureException e) {

			logger.error("redis serivce not available");
			throw e;

		} finally {

			newSpan.finish();

		}

	}

}
