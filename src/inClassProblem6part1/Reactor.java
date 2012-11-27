package inClassProblem6part1;

//import java.io.BufferedReader;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;


public class Reactor {
	HashMap<String, EventHandler> storage; // cmd name/ class name as keys to eventhandler objects as values
	ArrayList<String> keys;             // class name corresponding with the handlers
	private static Reactor reactor;
	
	public Reactor(){ 
	}
	
	public static Reactor getInstance() {
		if(reactor ==null){
			reactor = new Reactor();
		}
		return reactor;
	}

	
	public void init(){
		storage = new HashMap<String, EventHandler>(); 
		keys = new ArrayList<String>();
	}
	
	public void registerHandler(String pathToFile) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(pathToFile));
		String [] tempKey;
		while(true){
			String line = reader.readLine();
			if(line!=null){
				tempKey = line.split("=");
				System.out.println(tempKey[0]);
				keys.add(tempKey[0]);
			}else{
				break;
			}
		}
		reader.close();
		for (String s : keys) {
			EventHandler handler= loadFromFile(pathToFile, s);
			storage.put(s, handler);
		}
		printObject();
	}
	
	public void printObject() throws IOException{ 
		for(String s : keys){
			System.out.println("Key : " + s + " = eventHandler : " + storage.get(s));
		}
	}
	
	public void removeHandler(String key, EventHandler eh){
		storage.remove(key);
	}
	
	public void dispatch(Event e) throws IOException, InterruptedException{
		EventHandler handler = storage.get(e.getEventType());
		handler.handleEvent(e);
	}

	
	public EventHandler loadFromFile(String pathToFile, String key){
		//System.out.println(pathToFile);
		Properties p = new Properties();
		EventHandler handler = null;
		try {
			
			p.load(new FileInputStream(pathToFile));
			//System.out.println("after loading"); //
			String className = p.getProperty(key);
			//System.out.println(key); //
			//System.out.println(className); //
			Class<?>cla = Class.forName(className);
			handler = (EventHandler)cla.newInstance();
			//System.out.println(object.getName());
 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return handler;
	}


}
