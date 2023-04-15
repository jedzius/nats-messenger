package pl.pvpheaven.messenger.nats.connection;

import pl.pvpheaven.messenger.nats.callback.Callback;
import pl.pvpheaven.messenger.nats.handler.NatsHandler;

import java.time.Duration;

/**
 * @param <V> Value type.
 */
public interface NatsConnection<V> {

    /**
     * @param channel Channel that the message will be published on.
     * @param value Message that will be published on channel.
     */
    void publish(String channel, V value);

    /**
     * @param channel Channel that the message will be published on.
     * @param value Message that will be published on channel.
     * @param callback Packet callback that will executed on finish.
     */
    void publish(String channel, V value, Callback callback);


    /**
     * @param channel Channel that we will be listening for messages on.
     * @param natsHandler Message handler that will do actions on message received.
     */
    void subscribe(String channel, NatsHandler<V> natsHandler);

    /**
     * @param channel Channel that we will be listening for messages on.
     * @param natsHandler Message handler that will do actions on message received.
     * @param type Message class (NOTE: for more demanding users, use #subscribe(String, NatsHandler<V>) if you don't know what you are doing.).
     */
    void subscribe(String channel, NatsHandler<V> natsHandler, Class<? extends V> type);

    /**
     * @param timeout Guarantees our server has processed the message.
     */
    void flush(Duration timeout);

}