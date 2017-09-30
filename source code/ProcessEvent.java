import java.util.EventObject;

public class ProcessEvent extends EventObject {
	private String Psize;
	public ProcessEvent(Object source, String Psize) {
		super(source);
		this.Psize = Psize;
	}
	public String getPsize() {
		return Psize;
	}
	public void setPsize(String psize) {
		Psize = psize;
	}
	
}
