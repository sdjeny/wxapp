package org.sdjen.apps.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.sdjen.apps.dao.Dao;
import org.sdjen.apps.entity.InitDate;
import org.sdjen.apps.util.DaoParams;
import org.sdjen.apps.util.EntryData;
import org.sdjen.apps.util.HttpClientHelper;
import org.sdjen.apps.util.JsonFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@Transactional
public class WXAppWzcpwImpl implements WXAppWzcpwService {
	Logger logger = LoggerFactory.getLogger(WXAppWzcpwImpl.class);
	@Autowired
	Dao dao;
//	@Autowired
//	private ObjectMapper objectMapper;
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public String getDateInitData() throws Throwable {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		Date date_from = calendar.getTime(), date_to, date, today = dateFormat.parse(dateFormat.format(date_from));
		int first_day_of_week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		String result = dao
				.getObject(DaoParams.get().setJpql("select json from InitDate where date=?1").setParams(today));
		if (null == result) {
			logger.debug("计算");
			EntryData<String, Object> data = new EntryData<>();
			EntryData<Integer, int[]> month = new EntryData<Integer, int[]>().setInitValue(k -> new int[] { 31, 1 });
			calendar.add(Calendar.DATE, 30);
			date_to = calendar.getTime();
			data.put("first_day_of_week", first_day_of_week);
			data.put("date_from", dateFormat.format(date_from));
			data.put("date_to", dateFormat.format(date_to));
			data.put("month", month.getData());
			for (calendar.setTime(date_from); (date = calendar.getTime()).compareTo(date_to) <= 0; calendar
					.add(Calendar.DATE, 1)) {
				int d = calendar.get(Calendar.DATE);
				int[] days = month.get(calendar.get(Calendar.MONTH) + 1);
				days[0] = Math.min(days[0], d);
				days[1] = Math.max(days[1], d);
			} // dao.getObject(DaoParams.get().setJpql("select count(*) from User"))
			result = JsonFactory.toJsonPretty(data.getData());
			InitDate initDate = new InitDate();
			initDate.date = today;
			initDate.json = result;
			dao.merge(initDate);
		} else {
			logger.debug("直接取");
		}
		return result;
	}

	@Override
	public String ship_search(int from, int to, String date) {
		Map<String, Object> params = new LinkedHashMap<>();
		params.put("prevDate", date);// 2020-07-05
		params.put("departPort", getPort(from));
		params.put("arrivalPort", getPort(to));
		String html = HttpClientHelper.getInstance().doGet("https://www.laiu8.cn/ship/index", params);
		String fs = "shipLines: JSON.parse('", es = "code: Number";
		fs = ", lineList: JSON.parse('";
		fs = "lineList: JSON.parse('";
		String result = html.substring(html.indexOf(fs) + fs.length(), html.indexOf(es));
		result = result.trim();
		result = result.substring(0, result.length() - 3);
		return result;
	}

	private String getPort(int key) {
		return 0 == key ? "16" : "17";// 16:北海；17:涠洲岛
	}

	public String get(String url) {
		return get(url, new HashMap<>());
	}

	public String get(String url, Map<String, ?> variables) {
		String result = "";
		try {
			logger.info("I'm going to send request: " + url);
			ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class, variables);
			if (responseEntity != null && responseEntity.getStatusCode().is2xxSuccessful()) {
				// responseEntity.getStatusCode() == HttpStatus.OK
//				objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				result = responseEntity.getBody();
			}
			return result;
		} catch (Exception e) {
			logger.error("获取第三方天气API接口getWeather error {} ", e);
		}
		return result;
	}

	private void post(String url, Object variables, MediaType type) throws Throwable {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(type);
		HttpEntity<?> request = new HttpEntity<>(variables, headers);
		ResponseEntity<String> postForEntity = restTemplate.postForEntity(url, request, String.class);
		String body = postForEntity.getBody();
		System.out.println(body);
	}

	private void postJson(String url, Object variables) throws Throwable {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		HttpEntity<String> request = new HttpEntity<>(JsonFactory.toJson(variables), headers);//
		ResponseEntity<String> postForEntity = restTemplate.postForEntity(url, request, String.class);
		postForEntity.getStatusCode().is2xxSuccessful();
		String body = postForEntity.getBody();
		System.out.println(body);
	}

}