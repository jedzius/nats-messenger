package pl.pvpheaven.messenger.nats.callback;

public interface Callback<V> {
    void done(V v);
}
