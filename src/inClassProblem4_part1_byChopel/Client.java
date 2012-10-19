package inClassProblem4_part1_byChopel;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
	Reactor r;
	Acceptor ac;
	Socket sock;
	
	public Client(){
		ac = new Acceptor();
		r = new Reactor();
		try {
			sock = new Socket(InetAddress.getLocalHost(), 69); // at localhost<ip address of this computer> , at specified port of this computer
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Client cl = new Client();
		try {
			InputStream in = cl.sock.getInputStream();
			OutputStream out = cl.sock.getOutputStream();
			for(int i = 0; i <10; i++){
				try {
					System.out.println("senting: " + i);
					out.write(Marshaller.serializeObject(new TestObject(i, " ObjectNameIsInNumber: "+ i)));
					Thread.sleep(100);  // to make sure for loop dont run too fast and receiver on server just end up beint too slow
				}catch (InterruptedException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		

	}

}
