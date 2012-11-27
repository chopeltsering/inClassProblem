package inClassProblem6part1;

import java.io.Serializable;

public class SerializableObject implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6796644969007778162L;
	public int index;
	public String name;
	public String cmd;
	
	public SerializableObject(int index, String name) {
		this.index = index;
		this.name = name;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		return name+"["+index+"]";
	}
}
