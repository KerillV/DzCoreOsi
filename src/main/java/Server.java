import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    // порт, на котором будет запущен наш сервер, укажем в константе
    public static final Integer PORT_SERVER = 8088;

    public static void main(String[] args) {
    // создаем сокет, в нашем случае серверный, с использованием try()
        try(ServerSocket serverSocket = new ServerSocket(PORT_SERVER)) {/* порт можете выбрать любой в доступном
            диапазоне 0-65536. Но чтобы не нарваться на уже занятый - рекомендуем использовать около 8080 */

            System.out.println("Сервер стартовал!"); // добавили sout просто для понимания старта

            // определим время работы сервера, в нашем случае указываем постоянно при помощи оператора while
            while (true) {
                // создаем сокет клиентский через accept(), т.е. accept() - это одобрение подключения к нашему серверу
                try (Socket clientSocket = serverSocket.accept();
                // для удобства работы сохраняем поток в переменную, кот-ю потом будем использовать, чтобы писать информацию
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                // аналогично создаем поток для чтения информации
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
                ){
                    // Читаем строку и выводим её на экран вместе с номером порта клиента, с которого пришло соединение
                    System.out.println("New connection accepted");
                    final String name = in.readLine();
                    // в ответ клиенту отправим его порт, с которого было подключение
                    out.println(String.format("Hi %s, your port is %d", name, clientSocket.getPort()));
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}


