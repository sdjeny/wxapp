package org.sdjen.apps.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

import org.sdjen.apps.service.WXAppWzcpwService;
import org.sdjen.apps.util.HttpClientHelper;
import org.sdjen.apps.util.JsonFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/wxapp/wzcpw")
public class WXApp_wzcpw {

	@Autowired
	WXAppWzcpwService service;

	@RequestMapping("/hello")
	@ResponseBody
	private String hello() throws Throwable {
		return service.getDateInitData();
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
		return service.ship_search(from, to, date);
	}

}
