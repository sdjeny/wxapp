package org.sdjen.apps.service;

import java.util.HashMap;
import java.util.Map;

import org.sdjen.apps.util.JsonFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HttpImpl implements HttpService {
	Logger logger = LoggerFactory.getLogger(HttpImpl.class);
//	@Autowired
//	private ObjectMapper objectMapper;
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public String get(String url) {
		return get(url, new HashMap<>());
	}

	@Override
	public String get(String url, Map<String, ?> variables) {
		String result = "";
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class, variables);
		if (responseEntity != null && responseEntity.getStatusCode().is2xxSuccessful()) {
			// responseEntity.getStatusCode() == HttpStatus.OK
//			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			result = responseEntity.getBody();
		}
		return result;
	}

	public void post(String url, Object variables, MediaType type) throws Throwable {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(type);
		HttpEntity<?> request = new HttpEntity<>(variables, headers);
		ResponseEntity<String> postForEntity = restTemplate.postForEntity(url, request, String.class);
		String body = postForEntity.getBody();
		System.out.println(body);
	}

	public void postJson(String url, Object variables) throws Throwable {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		HttpEntity<String> request = new HttpEntity<>(JsonFactory.toJson(variables), headers);//
		ResponseEntity<String> postForEntity = restTemplate.postForEntity(url, request, String.class);
		postForEntity.getStatusCode().is2xxSuccessful();
		String body = postForEntity.getBody();
		System.out.println(body);
	}

}
