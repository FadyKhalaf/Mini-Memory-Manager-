import java.util.EventObject;

/**
 * @author fady khalaf
 *
 */
public class HolesEvent extends EventObject {
	
	private String holes;
	private String memSize;
	private String AllocationMethod;
	
	
	public HolesEvent(Object source, String holesNum, String size, String allocationMethod) {
		super(source);
		this.holes = holesNum;
		this.memSize = size;
		this.AllocationMethod = allocationMethod;
	}


	public String getHoles() {
		return holes;
	}


	public void setHoles(String holes) {
		this.holes = holes;
	}


	public String getMemSize() {
		return memSize;
	}


	public void setMemSize(String memSize) {
		this.memSize = memSize;
	}


	public String getAllocationMethod() {
		return AllocationMethod;
	}


	public void setAllocationMethod(String allocationMethod) {
		this.AllocationMethod = allocationMethod;
	}
	
	
}
