package test;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class TestClient {

	public static void main(String[] args) throws Exception {
		String sendeBesked;
		String modtageBesked;
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		Socket clientSocket = new Socket("10.150.90.7", 6789);
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		System.out.println("Indtast besked");
		sendeBesked = inFromUser.readLine();
		outToServer.writeBytes(sendeBesked + '\n');
		modtageBesked = inFromServer.readLine();
		System.out.println("Besked fra modpart: " + modtageBesked);
		clientSocket.close();
	}
}
