import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;
import java.util.Scanner;

public class Producer {

    //public KafkaProducer kafkaProducer;
    public Properties properties;

    public Producer() {
        properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

    }

    public void stream_data() throws IOException, ParseException {
        // Connect to NYTimes API
        URL url = new URL("https://api.nytimes.com/svc/topstories/v2/arts.json?api-key"
                + "=oEhArg4nDQtIA7aYd2LelrBgsILXa8Ge");

        HttpURLConnection conection = (HttpURLConnection) url.openConnection();

        conection.setRequestMethod("GET");

        conection.setRequestProperty("userId", "Riddhi");

        int responseCode = conection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {

            String inline = "";
            Scanner scanner = new Scanner(url.openStream());

            //Write all the JSON data into a string using a scanner
            while (scanner.hasNext()) {
                inline += scanner.nextLine();
            }

            //Close the scanner
            scanner.close();

            //Parsing the Json formatted String
            JSONParser parse = new JSONParser();
            JSONObject json_obj = (JSONObject) parse.parse(inline);

            JSONArray json_arr = (JSONArray) json_obj.get("results");

            //Get required elements from the json
            for (int i = 0; i < json_arr.size(); i++) {
                JSONObject json_data = (JSONObject) json_arr.get(i);
                System.out.println("\nElements under results array");
                String news_topic = (String) json_data.get("section");
                System.out.println("Section: " + news_topic);
                String title = (String) json_data.get("title");
                System.out.println("Title: " + title);
                String summary = (String) json_data.get("abstract");
                System.out.println("Abstract: " + summary);
                insert_into_topics(news_topic,title,summary);
            }
        } else {
            System.out.println("GET NOT WORKED");
        }
    }

    public void insert_into_topics(String news_topic, String key, String val){
        KafkaProducer kafkaProducer  = new KafkaProducer(properties);

        // Assign the value of Kafka Topic
        String topic;
        if (news_topic == "movies"){
            topic = "movies";
        }
        else if (news_topic == "arts"){
            topic = "arts";
        }
        else{
            topic = "general";
        }

        // Insert the key-value to appropriate Kafka Topic
        ProducerRecord producerRecord = new ProducerRecord("news",key,val);
        kafkaProducer.send(producerRecord);
        kafkaProducer.close();
    }



    }
