package com.company;

import org.jgroups.*;
import org.jgroups.stack.AddressGenerator;
import org.jgroups.util.UUID;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by sshekhar on 7/26/21.
 */
public class SimpleChat extends ReceiverAdapter {

    JChannel channel;
    String user_name=System.getProperty("user.name", "n/a");

    public String name;
    public String commonFile;
    public String clusterName;
    public long lsb;
    public long msb;

    public SimpleChat(String name, String commonFile, String clusterName, long lsb, long msb){
        this.name = name;
        this.commonFile = commonFile;
        this.clusterName = clusterName;
        this.lsb = lsb;
        this.msb = msb;
    }

    public void start() throws Exception {
        channel=new JChannel(commonFile); // use the default config, udp.xml
        channel.setName(name);
        channel.setReceiver(this);
        AddressGenerator addressGenerator = new AddressGenerator() {
            public Address generateAddress() {
                return new UUID(lsb,msb);
            }

        };
        channel.setAddressGenerator(addressGenerator);
        System.out.println("Address: " + addressGenerator.generateAddress().toString());
        channel.connect(clusterName);
        eventLoop();
        channel.close();
    }

    /*public static void main(String[] args) throws Exception {
        new SimpleChat().start();
    }*/

    private void eventLoop() {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                System.out.print("> ");
                System.out.flush();
                String line = in.readLine().toLowerCase();
                if (line.startsWith("quit") || line.startsWith("exit"))
                    break;
                line = "[" + user_name + "] " + line;
                Message msg = new Message(null, null, line);
                channel.send(msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void viewAccepted(View new_view) {
        System.out.println("** view: " + new_view);
    }

    public void receive(Message msg) {
        System.out.println(msg.getSrc() + ": " + msg.getObject());
    }

}

