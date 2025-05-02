import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
// клиент должен подключиться к нашему серверу, цикл while тут не нужен, достаточно try

    public static void main(String[] args) {
        /* Обратите внимание, что хостом подключения будет localhost - это зарезервированный адрес для подключения
         к текущему компьютеру (то есть это ваш компьютер - там, где запущена программа).
         Также можно использовать 127.0.0.1 - это ip адрес, соответствующий доменному имени localhost */

        try(Socket clientSocket = new Socket("localhost", Server.PORT_SERVER);
            // также нам нужны два потока на чтение и на запись
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
        ){
            out.println("Hello from Kirill!");
            String serverResponse = in.readLine(); // ответ сервера запишем в переменную serverResponse
            System.out.println(serverResponse);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
