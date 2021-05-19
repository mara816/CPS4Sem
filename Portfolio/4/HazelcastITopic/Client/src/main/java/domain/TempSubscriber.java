package domain;

import com.hazelcast.topic.Message;
import com.hazelcast.topic.MessageListener;

public class TempSubscriber implements ISensor, MessageListener {

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
        String resp = (String) message.getMessageObject();
        String[] arr = resp.split("@");
        name = arr[0];
        value = Double.valueOf(arr[1]);
    }
}
