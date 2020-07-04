package wxapp;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;

import wxapp.util.HttpClientHelper;
import wxapp.util.JsonFactory;

@Controller
@EnableAutoConfiguration
@RequestMapping("/wxapp")
public class MainTest {

	public static void main(String[] args) {
		SpringApplication.run(MainTest.class, args);
	}

//	@Bean
//	public ServletWebServerFactory servletContainer() {
//		TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
//		tomcat.addAdditionalTomcatConnectors(createStandardConnector());
//		return tomcat;
//	}
//
//	private Connector createStandardConnector() {
//		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
//		connector.setPort(8122);
//		return connector;
//	}

	@RequestMapping("/subscribe_send/{appid}/{code}/{tempid}/{verify}")
	@ResponseBody
	private String subscribe_send(//
			@PathVariable("appid") String appid//
			, @PathVariable("code") String code//
			, @PathVariable("tempid") String tempid//
			, @PathVariable("verify") long verify//
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
			return wxapp.util.JsonFactory.toJson(result);
		} catch (Throwable e) {
			return String.format("{\"err\":\"%s\"}", e.getMessage());
		}
	}

}
