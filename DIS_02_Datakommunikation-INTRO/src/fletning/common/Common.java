package fletning.common;

public class Common {
	private String commonString = "";
	private String history = "";

	public Common() {
	}

	public String getString() {
		return commonString;
	}

	public void setString(String string) {
		commonString += string;
	}

	public void reset() {
		history = commonString;
		commonString = "";
	}

	public String getHistory() {
		return history;
	}
}
