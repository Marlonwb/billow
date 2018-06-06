package person.marlon.springbootdemo.common.cache;

import java.io.Serializable;

public class Foo implements Serializable {


	private static final long serialVersionUID = 2494641943555756033L;

	private String key;
	private String value;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
