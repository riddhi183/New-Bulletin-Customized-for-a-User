import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class Producer {

    //public KafkaProducer kafkaProducer;
    public Properties properties;

    public Producer() {
        properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

    }

    public void insert_into_topics(String news_topic,String key, String val){
        KafkaProducer kafkaProducer  = new KafkaProducer(properties);

        System.out.println("HERE");
        String topic;
        if (news_topic == "movies"){
            topic = "movies";
        }else if (news_topic == "arts"){
            topic = "arts";
        }else{
            topic = "general";
        }
//        System.out.println(key);
//        System.out.println(val);

        ProducerRecord producerRecord = new ProducerRecord("news",key,val);
        kafkaProducer.send(producerRecord);
        kafkaProducer.close();
    }

    }
