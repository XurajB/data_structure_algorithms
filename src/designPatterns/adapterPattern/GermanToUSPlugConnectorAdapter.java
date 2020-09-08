package designPatterns.adapterPattern;

/**
 * Adapter pattern converts the interface of a class into another interface the clients expect.
 * It lets classes work together that couldn’t otherwise because of incompatible interfaces.
 */
public class GermanToUSPlugConnectorAdapter implements USPlugConnector {

    private GermanPlugConnector plug;

    public GermanToUSPlugConnectorAdapter(GermanPlugConnector connector) {
        this.plug = connector;
    }

    @Override
    public void provideElectricity() {
        plug.giveElectricity();
    }
}
