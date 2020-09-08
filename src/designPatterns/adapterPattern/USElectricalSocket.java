package designPatterns.adapterPattern;

public class USElectricalSocket {
    public void plugIn(USPlugConnector plugConnector) {
        plugConnector.provideElectricity();
    }
}
