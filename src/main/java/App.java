import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class App {
    public static void main(String[] args) throws IOException, ParseException {
        startMenu();
    }

    private static void startMenu() throws IOException, ParseException {

        Scanner sc = new Scanner(System.in);

        int input = sc.nextInt();

        while (input == 1 || input == 2) {
            System.out.println("Enter 1 or 2");
            System.out.println("1.Produce the data  \n 2.Client login");

            switch (input) {
                case 1: {
                    Producer producer = new Producer();
                    producer.stream_data();
                }

                case 2: {
                    System.out.println("Please enter the userId");
                    String userId = sc.next();
                    System.out.println("Enter category");
                    String topics = sc.next();
                    Consumer consumer = new Consumer(userId, topics);
                    consumer.consume_data();
                }


            }
        }
    }
}