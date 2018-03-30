package ExceptionHandler;

@SuppressWarnings("serial")
public class InValidLoginParameterException extends Exception{

	public InValidLoginParameterException(String msg) {
		super(msg);
	}
}
