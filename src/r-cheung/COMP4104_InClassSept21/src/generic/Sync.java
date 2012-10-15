package generic;

public interface Sync {

	public void acquire();
	public void release();
	public void attempt(int msecs);
}
