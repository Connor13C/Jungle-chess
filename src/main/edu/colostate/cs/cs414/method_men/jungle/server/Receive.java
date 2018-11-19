package edu.colostate.cs.cs414.method_men.jungle.server;

import edu.colostate.cs.cs414.method_men.jungle.client.gui.ClientSend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.*;

public class Receive extends Thread{

    private BufferedReader in;
    private Socket socket;
    private TCPServer server;

    public Receive(Socket socket, TCPServer server) throws IOException{
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.socket = socket;
        this.server = server;
    }

    public void receive(){
        String msg = null;
        System.out.println("Receive thread started");
        while (true) {
            try {
                //Buffered Reader reads from socket
                while ((msg = in.readLine()) != null) {
                    System.out.println("Message received: " + msg);
                    //Parse input into string array
                    String[] message = parseReceive(msg);
                    respondToInput(message);

                }
            } catch (Exception e) {}

        }
    }

    public String[] parseReceive(String msg) {
        String [] items = msg.split(" ");
        return items;
    }

    public void respondToInput(String [] message){
        //if logging in, do some stuff to send to database to authenticate, etc.
        //TODO
        if(message[0].equals("login")){
            System.out.println("Logging in");
            //will need to check/call authentication methods here
            try{
                Send send = new Send(this.socket);
                send.sendLoginResponse(true);
                User user = new User(message[1], this.socket);
                server.addUser(user);
            }catch(Exception e){}
        }
    }




    public void run(){
        receive();
    }
}
