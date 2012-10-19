package inClassProblem4_part1_byChopel;

import java.util.ArrayList;
import java.util.List;

public class Vault {
	
	List <Object> list;
	public Vault(){
		list = new ArrayList<Object>();	
	}
	
	public void addObject(Object item){
		list.add(item);
	}
	
	public Object getObject(int index){
		return list.get(index);
	}
}
