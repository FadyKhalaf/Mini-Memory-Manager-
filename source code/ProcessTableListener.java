import java.util.EventListener;

public interface ProcessTableListener extends EventListener {
	public void RowDeleted(int index);
}
