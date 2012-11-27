package inClassProblem6part1;

public class Event {

	String eventType;
	String objectName;
	String text;
	Object object;
	
	
	public Event(String eventType, String objectName ,String text, Object o){
		this.eventType = eventType;
		this.objectName = objectName;
		this.text = text;
		object = o;
	}
	
	public String getText() {
		return text;
	}
	
	public String getObjectName(){
		return objectName;
	}

	public Object getObject() {
		return object;
	}
	
	public Object getEventType(){
		return eventType;
	}

}
