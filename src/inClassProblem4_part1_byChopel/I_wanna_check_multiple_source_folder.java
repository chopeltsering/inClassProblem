package inClassProblem4_part1_byChopel;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class I_wanna_check_multiple_source_folder {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if(args.length<1){
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
		}

	}

}
