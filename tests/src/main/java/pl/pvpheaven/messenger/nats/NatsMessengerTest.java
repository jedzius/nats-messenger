package pl.pvpheaven.messenger.nats;

import io.nats.client.Options;
import pl.pvpheaven.messenger.nats.callback.Callback;
import pl.pvpheaven.messenger.nats.codec.NatsStringCodec;
import pl.pvpheaven.messenger.nats.connection.NatsConnection;

import java.time.Duration;
import java.util.UUID;

public final class NatsMessengerTest {

    private static final Options OPTIONS = new Options.Builder()
            .server("nats://demo.nats.io:4222")
            .reconnectWait(Duration.ofSeconds(1))
            .build();

    private final NatsClient natsClient = NatsMessenger.create(OPTIONS);
    private final NatsConnection<String> natsConnection = this.natsClient.createConnection(new NatsStringCodec());

    public static void main(String[] args) {
        final NatsMessengerTest natsMessengerTest = new NatsMessengerTest();

        natsMessengerTest.pubSubTest();
    }

    private void pubSubTest() {
        final String uniqueTopic = UUID.randomUUID().toString();

        this.natsConnection.publish(uniqueTopic, "testowa wiadomosc", o -> System.out.println(o));

        this.natsConnection.flush(Duration.ZERO);
    }

}