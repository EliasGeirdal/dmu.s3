package fletning.common;

public class Common {
	private String commonString = "";

	public Common() {
	}

	public String getString() {
		return commonString;
	}

	public void setString(String string) {
		commonString = string;
	}

	public boolean isEmpty() {
		return commonString == "";
	}
}
