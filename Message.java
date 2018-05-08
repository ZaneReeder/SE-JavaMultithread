
public class Message {
	public String functionCall;
	public Bill billParam;
	public String stringParam;
	public int intParam;
	public double dblParam;
	
	public Message(String func, Bill billP, String stringP, int intP, double dblP) {
		functionCall = func;
		billParam = billP;
		stringParam = stringP;
		intParam = intP;
		dblParam = dblP;
	}
}
