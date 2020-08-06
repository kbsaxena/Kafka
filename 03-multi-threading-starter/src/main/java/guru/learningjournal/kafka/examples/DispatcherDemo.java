package guru.learningjournal.kafka.examples;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.stream.IntStream;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DispatcherDemo {
    private static final Logger log = LogManager.getLogger();
    
    public static void main(String[] args) {
        Properties props = new Properties();
        try {
            InputStream is = new FileInputStream(AppConfigs.kafkaConfigFileLocation);
            props.load(is);
            
            props.setProperty(ProducerConfig.CLIENT_ID_CONFIG, AppConfigs.applicationID);
            props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
            props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
        KafkaProducer<Integer, String> producer = new KafkaProducer<>(props);
        
        Thread[] dispatchers = new Thread[AppConfigs.eventFiles.length];
        
        log.info("Starting Dispatcher Threads...");
        
        IntStream.range(0, AppConfigs.eventFiles.length).forEach(a -> {
            dispatchers[a] = new Thread(new Dispatcher(AppConfigs.eventFiles[a], AppConfigs.topicName, producer));
            dispatchers[a].start();
        });
        
        try {
            for(Thread t : dispatchers) t.join();
        } catch (Exception e) {
            log.error("Main Thread Interuppted...");
        } finally {
            producer.close();
            log.info("Finished Dispatcher Demo...");
        }
        
    }
}
