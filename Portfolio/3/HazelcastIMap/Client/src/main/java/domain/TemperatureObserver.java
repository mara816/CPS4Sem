package domain;

import com.hazelcast.core.EntryEvent;
import com.hazelcast.map.listener.EntryUpdatedListener;

public class TemperatureObserver implements ISensor, EntryUpdatedListener<String, String> {

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
    public void entryUpdated(EntryEvent<String, String> entryEvent) {
        name = entryEvent.getKey();
        value = Double.valueOf(entryEvent.getValue());
    }
}
