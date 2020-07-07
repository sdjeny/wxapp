package org.sdjen.apps.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.sdjen.apps.dao.Dao;
import org.sdjen.apps.entity.InitDate;
import org.sdjen.apps.util.DaoParams;
import org.sdjen.apps.util.EntryData;
import org.sdjen.apps.util.HttpClientHelper;
import org.sdjen.apps.util.JsonFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class WXAppWzcpwImpl implements WXAppWzcpwService {
	@Autowired
	Dao dao;

	@Override
	public String getDateInitData() throws Throwable {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		Date date_from = calendar.getTime(), date_to, date, today = dateFormat.parse(dateFormat.format(date_from));
		int first_day_of_week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		String result = dao
				.getObject(DaoParams.get().setJpql("select json from InitDate where date=?1").setParams(today));
		if (null == result) {
			System.out.println("计算");
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

}
