package qhtr.test;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.LoggerFactory;

import com.qhtr.utils.JPushUtils;

public class TestJpush {

	public static void main(String[] args) {
		
		Set<String> aliases = new HashSet<>();
		aliases.add(105+"");
		;
		JPushUtils.sendPush(JPushUtils.sendMessageToIOSByAlias("hello world ~", aliases), LoggerFactory.getLogger(TestJpush.class));
	}
}
