package designPatterns.adapterPattern;

public class Test {
    public static void main(String[] args) {
        // german connector
        GermanPlugConnector germanPlugConnector = new GermanPlugConnector() {
            @Override
            public void giveElectricity() {
                System.out.println("gave electricity to US connector");
            }
        };
        // us plug connector
        USPlugConnector usPlugConnector = new GermanToUSPlugConnectorAdapter(germanPlugConnector);

        USElectricalSocket socket = new USElectricalSocket();
        socket.plugIn(usPlugConnector);
    }
}
