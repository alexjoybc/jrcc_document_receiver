package ca.gov.bc.open.jrccdocumentreceiver;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
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

		String contentString = new String(getSourceXml());

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
