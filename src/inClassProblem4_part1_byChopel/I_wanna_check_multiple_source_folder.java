package inClassProblem4_part1_byChopel;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
//import java.io.OutputStream;
//import java.net.*;
import java.io.*;
import java.util.Properties;


// this class is for testing snippets of code. 
@SuppressWarnings("unused")
public class I_wanna_check_multiple_source_folder {

	/**
	 * 
	 */
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		//to check out InetAddress methods.
		/*if(args.length<1){
			System.out.println("usage: java dpl.sockets.NsLookup <hostName>");
			System.exit(1);
		}
		InetAddress ia;
		try{
			ia = InetAddress.getByName(args[0]);
			System.out.println(InetAddress.getByName(args[0]));
			System.out.println("Name: " + args[0]);
			System.out.println("Address: " + ia.getHostAddress());
			System.out.println("hostName: " + ia.getHostName());
		}catch(UnknownHostException e){
			System.exit(2);
		}*/
		
		/*
			DataOutputStream out;
			try {
				out = new DataOutputStream(new FileOutputStream("outputToFile.nkl"));
				out.writeChars("yumdook\n");
				out.writeChars("yumdook2");
				out.writeChars("yumdook3");
				out.writeChars("\t");
				out.writeChars("yumdook4");
				out.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
		
		
		Properties p = new Properties();
		try {
			p.load(new FileInputStream("loadClassName.txt"));
			String className = p.getProperty("objectA");
			System.out.println(className);
			Class cla = Class.forName(className);
			TestObject object = (TestObject)cla.newInstance();
			System.out.println(object.getName());
			
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
		
	/*	try {
			DataInputStream in = new DataInputStream(new FileInputStream("outputToFile.txt"));
			System.out.println(DataInputStream.readUTF(in));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		
	}
}
			
			
		

	


