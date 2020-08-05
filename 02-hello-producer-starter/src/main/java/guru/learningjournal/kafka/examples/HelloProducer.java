package guru.learningjournal.kafka.examples;

import java.util.Properties;
import java.util.stream.IntStream;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HelloProducer {
    private static final Logger log = LogManager.getLogger();
    
    public static void main(String[] args) {
        log.info("Producer Started");
        
        Properties props = new Properties();
        props.setProperty(ProducerConfig.CLIENT_ID_CONFIG, AppConfigs.applicationID);
        props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.bootstrapServers);
        props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
        props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        
        KafkaProducer<Integer, String> producer = new KafkaProducer<>(props);
        
        log.info("Started sending message...");
        IntStream.rangeClosed(0, AppConfigs.numEvents).forEach(a-> {
            producer.send(new ProducerRecord<Integer, String>(AppConfigs.topicName, a, "Message-"+a));
        });
        
        log.info("Finished sending message...");
        producer.close();
        
    }
}