package domain;

import com.hazelcast.topic.MessageListener;

/**
 * @author Anders
 */
public interface ISensor {
    public String getName();
    public Double getValue();
}
