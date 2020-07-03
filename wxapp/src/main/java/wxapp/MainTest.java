package wxapp;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;

@Controller
@EnableAutoConfiguration
@RequestMapping("/wxapp")
public class MainTest {

	public static void main(String[] args) {
		SpringApplication.run(MainTest.class, args);
	}

	@RequestMapping("/getinfo/{appid}/{code}/{time}/{key}")
	@ResponseBody
	private String getInfo(//
			@PathVariable("appid") String appid//
			, @PathVariable("code") String code//
			, @PathVariable("time") long time//
			, @PathVariable("key") long key//
	) {
		Map<String, Object> result = new HashMap<>();
		Map<String, Object> data = new HashMap<>();
		data.put("appid", appid);
		data.put("code", code);
		data.put("time", time);
		data.put("key", key);
		result.put("data", data);
		result.put("err", "");
		try {
			return wxapp.util.JsonFactory.toJson(result);
		} catch (JsonProcessingException e) {
			return String.format("{\"err\":\"%s\"}", e.getMessage());
		}
	}

}
