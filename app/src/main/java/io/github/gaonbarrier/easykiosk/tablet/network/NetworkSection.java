package io.github.gaonbarrier.easykiosk.tablet.network;

public class NetworkSection {
    private Sender sender;
    private Receiver receiver;

    public NetworkSection(){
        sender = new Sender();
        receiver = new Receiver();
        receiver.runServer();
    }
}
