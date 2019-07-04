package ca.gov.bc.open.jrccdocumentreceiver;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
import ca.gov.bc.open.jrccaccess.libs.TransactionInfo;
import ca.gov.bc.open.jrccaccess.libs.services.ServiceUnavailableException;

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

		Span newSpan = tracer.nextSpan().name("ReceiveDocument").start();

		TransactionInfo transactionInfo = new TransactionInfo(resourceFile.getFilename(), "jrcc-receiver", LocalDateTime.now());
		
		try (SpanInScope ws = tracer.withSpanInScope(newSpan.start())) {
			
			documentController.post(new String(getSourceXml()), transactionInfo);

			logger.info("Document successfully sent.");

		} catch (ServiceUnavailableException e) {

			logger.error(e.getMessage());
			throw e;

		} finally {
			newSpan.finish();
		}

	}

	public String getSourceXml() {

		StringBuilder stringBuilder = new StringBuilder();

		InputStream inputStream;
		try {
			inputStream = resourceFile.getInputStream();

			String line = null;

			try (BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
				while ((line = bufferedReader.readLine()) != null) {
					stringBuilder.append(line);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return stringBuilder.toString();
	}

}
