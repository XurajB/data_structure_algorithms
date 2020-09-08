package designPatterns.adapterPattern;

public class GermanElectricalSocket {
    public void plugIn(GermanPlugConnector plugConnector) {
        plugConnector.giveElectricity();
    }
}
