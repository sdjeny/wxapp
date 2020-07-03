package wxapp.util;

import java.io.IOException;
import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * [11806]sdjen20170606<br>
 */
public class JsonFactory {
	private static final ObjectMapper parser = new ObjectMapper();
	public final static String TIME_ZONE = "GMT+8";
	public final static String STANDARD_TIMESTAMP_TYPE = "yyyy/MM/dd HH:mm:ss";
	public final static String STANDARD_DATE_TYPE = "yyyy/MM/dd";
	public final static String DEFAULT_ENCODING = "UTF-8";
	static {
		parser.setSerializationInclusion(Include.NON_NULL);
		parser.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);
		parser.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		parser.configure(SerializationFeature.FAIL_ON_SELF_REFERENCES, false);
		parser.configure(SerializationFeature.FAIL_ON_UNWRAPPED_TYPE_IDENTIFIERS, false);
		parser.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		// parser.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
		// parser.setVisibility(PropertyAccessor.GETTER, Visibility.NONE);
		// parser.setVisibility(PropertyAccessor.SETTER, Visibility.NONE);
		parser.setDateFormat(new SimpleDateFormat(STANDARD_TIMESTAMP_TYPE) {
			private static final long serialVersionUID = 1L;
			@Override
			public StringBuffer format(Date arg0, StringBuffer arg1, FieldPosition arg2) {
				return this.localFormat(arg0, arg1, arg2);
			}
			public synchronized StringBuffer localFormat(Date arg0, StringBuffer arg1, FieldPosition arg2) {
				return super.format(arg0, arg1, arg2);
			}
		});
	}
	public synchronized static ObjectMapper getParser() {
		return parser;
	}
	public synchronized static String toJson(Object obj) throws JsonProcessingException {
		return getParser().writeValueAsString(obj);
	}
	public synchronized static String toJsonPretty(Object obj) throws JsonProcessingException {
		return getParser().writerWithDefaultPrettyPrinter().writeValueAsString(obj);
	}
	public synchronized static <T> T fromJson(String json, Class<T> clazz) throws JsonParseException,
			JsonMappingException, IOException {
		return getParser().readValue(json, clazz);
	}
}
