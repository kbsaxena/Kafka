package guru.learningjournal.kafka.examples;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Dispatcher implements Runnable {

    private static final Logger log = LogManager.getLogger();
    private String fileLocation;
    private String topicName;
    private KafkaProducer<Integer, String> producer;
    
    public Dispatcher(String fileLocation, String topicName, KafkaProducer<Integer, String> producer) {
        super();
        this.fileLocation = fileLocation;
        this.topicName = topicName;
        this.producer = producer;
    }
    
    @Override
    public void run() {
        log.info("Started Processing " + fileLocation);
        File file = new File(fileLocation);
        int counter = 0;
        
        try(Scanner in = new Scanner(file)){
            while(in.hasNextLine()) {
                String line = in.nextLine();
                producer.send(new ProducerRecord<Integer, String>(topicName,null, line));
                counter++;
            }
            log.info("Finished Sending "+ counter +" message from " + fileLocation);
        } catch(FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
