package qhtr.test;

import java.io.IOException;
import java.text.SimpleDateFormat;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

public class TestJson extends JsonSerializer<Object>{

	@Override
	public void serialize(Object value, JsonGenerator jgen, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM-dd HH:mm:ss");
		String format = sdf.format(value);
		jgen.writeString(format);
	}

}
