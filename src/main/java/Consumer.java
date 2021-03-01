import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class Consumer {
    public Properties properties;
    private String userId;
    private String topic_subscribed;

    public Consumer(String id,String topic) {
        properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("group.id", "test-group");
        userId = id;
        topic_subscribed = topic;
    }

    public void consume_data(){
        KafkaConsumer kafkaConsumer = new KafkaConsumer(properties);
        List topics = new ArrayList();
        topics.add(topic_subscribed);
        kafkaConsumer.subscribe(topics);


        try{
            while (true){
                // to fetch the data from topic
                ConsumerRecords <String, String> records = kafkaConsumer.poll(1000L);

                //To print the news articles fetch
                for (ConsumerRecord <String, String> record: records){
                    System.out.println(String.format("Topic - %s, Partition - %d, Value: %s", record.topic(), record.partition(), record.value()));
                }
                kafkaConsumer.close();
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            kafkaConsumer.close();
        }

    }

}
