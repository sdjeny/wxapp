package org.sdjen.apps.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.sdjen.apps.dao.Dao;
import org.sdjen.apps.util.DaoParams;
import org.sdjen.apps.util.EntryData;
import org.sdjen.apps.util.HttpClientHelper;
import org.sdjen.apps.util.JsonFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;

@Controller
@RequestMapping("/wxapp/wzcpw")
public class WXApp_wzcpw {

	@Autowired
	Dao dao;

	@RequestMapping("/hello")
	@ResponseBody
	private String hello() throws Throwable {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		EntryData<String, Object> result = new EntryData<>();
		EntryData<String, int[]> month = new EntryData<String, int[]>().setInitValue(k -> new int[] { 31, 1 });
		result.put("first_day_of_week", calendar.get(Calendar.DAY_OF_WEEK) - 1);
		Date date_from = calendar.getTime(), date_to, date;
		calendar.add(Calendar.DATE, 30);
		date_to = calendar.getTime();
		result.put("date_from", dateFormat.format(date_from));
		result.put("date_to", dateFormat.format(date_to));
		result.put("month", month.getData());

		for (calendar.setTime(date_from); (date = calendar.getTime()).compareTo(date_to) <= 0; calendar
				.add(Calendar.DATE, 1)) {
			String key = (calendar.get(Calendar.MONTH) + 1) + "";
			int d = calendar.get(Calendar.DATE);
			int[] days = month.get(key);
			days[0] = Math.min(days[0], d);
			days[1] = Math.max(days[1], d);
		}
		return "hello wxapp " + dao.getObject(DaoParams.get().setJpql("select count(*) from User")) + ""
				+ JsonFactory.toJsonPretty(result.getData());
	}

	@RequestMapping("/subscribe_send/{appid}/{code}/{tempid}/{verify}")
	@ResponseBody
	private String subscribe_send(//
			@PathVariable("appid") String appid//
			, @PathVariable("code") String code//
			, @PathVariable("tempid") String tempid//
			, @PathVariable("verify") String verify//
	) {
		try {
			Map<String, Object> params = new LinkedHashMap<>();
			Map<String, Object> rep;
			params.put("appid", appid);
			params.put("secret", "914aecbc1cb96e500ca094376f48b844");
			params.put("grant_type", "client_credential");
			rep = JsonFactory.fromJson(
					HttpClientHelper.getInstance().doGet("https://api.weixin.qq.com/cgi-bin/token", params), Map.class);
			String access_token = (String) rep.get("access_token");
			params.put("js_code", code);
			params.put("grant_type", "authorization_code");
			rep = (Map<String, Object>) JsonFactory.fromJson(
					HttpClientHelper.getInstance().doGet("https://api.weixin.qq.com/sns/jscode2session", params),
					Object.class);
			String openid = (String) rep.get("openid");
			params.clear();
			Function<String, Map<String, String>> valueMap = s -> {
				Map<String, String> result = new HashMap<>();
				result.put("value", s);
				return result;
			};
			Map<String, Object> data = new LinkedHashMap<>();
			data.put("thing1", valueMap.apply("Title"));
			data.put("date3", valueMap.apply("2019年10月1日"));
			data.put("phone_number6", valueMap.apply("18888888888"));
			data.put("amount5", valueMap.apply("100"));
			data.put("character_string7", valueMap.apply("W443544333f3"));
//			params.put("access_token", access_token);
//			params.put("appid", appid);
			params.put("touser", openid);
			params.put("template_id", tempid);
			params.put("data", data);
			rep = (Map<String, Object>) JsonFactory.fromJson(HttpClientHelper.getInstance().doPost(
					"https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=" + access_token, params,
					true), Object.class);
			Map<String, Object> result = new HashMap<>();
			result.put("req", rep);
			result.put("err", "");
			return org.sdjen.apps.util.JsonFactory.toJson(result);
		} catch (Throwable e) {
			return String.format("{\"err\":\"%s\"}", e.getMessage());
		}
	}

	@RequestMapping("/ship_search/{from}/{to}/{date}/{verify}")
	@ResponseBody
	private String ship_search(//
			@PathVariable("from") int from//
			, @PathVariable("to") int to//
			, @PathVariable("date") String date//
			, @PathVariable("verify") String verify//
	) {
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
		// 16:北海；17:涠洲岛
		return 0 == key ? "16" : "17";
	}

}
