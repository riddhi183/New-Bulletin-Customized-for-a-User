import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Runner {
    public static void main(String[] args) throws IOException, ParseException {

        Consumer_topic();
//        URL url = new URL("https://api.nytimes.com/svc/topstories/v2/arts.json?api-key"
//                + "=oEhArg4nDQtIA7aYd2LelrBgsILXa8Ge");
//
//        HttpURLConnection conection = (HttpURLConnection) url.openConnection();
//
//        conection.setRequestMethod("GET");
//
//        conection.setRequestProperty("userId", "a1bcdef");
//        int responseCode = conection.getResponseCode();
//
//        if (responseCode == HttpURLConnection.HTTP_OK) {
//            Producer producer = new Producer();
//            String inline = "";
//            Scanner scanner = new Scanner(url.openStream());
//
//            //Write all the JSON data into a string using a scanner
//            while (scanner.hasNext()) {
//                inline += scanner.nextLine();
//            }
//
//            //Close the scanner
//            scanner.close();
//            JSONParser parse = new JSONParser();
//            JSONObject jobj = (JSONObject) parse.parse(inline);
//
//            JSONArray jsonarr_1 = (JSONArray) jobj.get("results");
//
//
//            for (int i = 0; i < jsonarr_1.size(); i++) {
//                JSONObject jsonobj_1 = (JSONObject) jsonarr_1.get(i);
//                System.out.println("\nElements under results array");
//                String news_topic = (String) jsonobj_1.get("section");
//                System.out.println("Section: " + news_topic);
//                String title = (String) jsonobj_1.get("title");
//                System.out.println("Title: " + title);
//                String val = (String) jsonobj_1.get("abstract");
//                System.out.println("Abstract: " + val);
//                producer.insert_into_topics(news_topic,title,val);
//            }
//        } else {
//            System.out.println("GET NOT WORKED");
//        }
    }

    public static void Consumer_topic(){
        Consumer consumer = new Consumer();
        System.out.println("Now");
    }
}
