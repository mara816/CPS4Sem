package domain;

import com.hazelcast.topic.Message;
import com.hazelcast.topic.MessageListener;

public class CO2Observer implements ISensor, MessageListener {

    private String name;
    private Double value;

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Double getValue() {
        return this.value;
    }

    @Override
    public void onMessage(Message message) {
        name = "CO2: ";
        value = (Double) message.getMessageObject();
    }
}

