package systemDesign.OOD;

public class ParkingLot {
    // requirements
    // large lot - 10k to 30k spots
    // 4 entrances and 4 exits
    // ticket and spot assigned at entrance
    // parking spot should be nearest to the entrance (from all entrance)
    // limit/capacity 30k
    // parking spots for: handicapped, compact, large, motorcycle
    // hourly rate
    // cash and credit card
    // monitoring system
    // design in such a way that it can applied to other parking lots with minimal changes

    /**
     * We design patterns for OO designs
     * - Creation
     * - Structural
     * - Behavioral
     *
     * There are two approaches we can take
     * - Top down (we first construct high level objects, and then identify smaller sub components, then design sub components and identify its sub components and so on
     * - Bottom up (we first construct smallest components, and use them to design bigger components and so on. Bottom up is aligned with object oriented design so use this
     */

    /*
    Objects and actors

    Parking system
    Entry/Exit terminal
        - Printers
        - payment processors
    Parking spot
    Ticket
    Database
    Monitoring system
     */

    // to define different parking spots, we could have a ParkingSpot class and define an enum for the ParkingType, but this method
    // makes us change other classes where ParkingType is used if we want to add/remove types, this violates open/close design principle.
    // this principle states that existing and well tested classes should not be modified when a new feature is to be built.
    // another option is to define ParkingSpot as abstract or interface and derive all types from it, if we need to add another type, we just extend or implement the ParkingSpot class
    abstract class ParkingSpot {
        int id;
        abstract void reserve();
    }
    class HandicappedParkingSpot extends ParkingLot {
    }

    class ParkingTicket {
        int id;
        int parkingSpotId;
        ParkingSpot type;
        int issueTime;
    }

    abstract class Terminal {
        int id;
    }
    class EntryTerminal extends Terminal {
        public ParkingTicket getTicket(ParkingSpot type) {
            return null;
        }
    }
    class ExitTerminal extends Terminal {
        public void acceptTicket(ParkingTicket ticket) {
        }
    }

    interface ParkingAssignmentStrategy {
        ParkingSpot getParkingSpot(Terminal terminal);
        void releaseParkingSpot(ParkingSpot parkingSpot);
    }

    // strategy design pattern
    // the strategy pattern lets us specify which algorithm the client should use
    class ParkingSpotNearEntranceStrategy implements ParkingAssignmentStrategy {
        // we can have N number of min heaps as entrances, which we can store them in a Map with key being entrance id
        // all Parking spots will be added to all heaps
        // in our case -
        // 4 min heaps that gives us ParkingSpot sorted on minimum distance from the entrance
        // 2 sets for available and reserved
        // complexity of kLogN
        @Override
        public ParkingSpot getParkingSpot(Terminal terminal) {
            return null;
        }

        @Override
        public void releaseParkingSpot(ParkingSpot parkingSpot) {

        }
    }

    // again strategy pattern
    interface PaymentProcess {
        void process(int amount);
    }
    class CreditCardPaymentProcessor implements PaymentProcess {
        @Override
        public void process(int amount) {
        }
    }
    class CashPaymentProcessor implements PaymentProcess {
        @Override
        public void process(int amount) {
        }
    }

    interface FeeCalculator {
        void calculate(int startTime);
    }
    class WeekdaysFeeCalculator implements FeeCalculator {
        @Override
        public void calculate(int startTime) {
        }
    }

    // we can use observer design pattern to log message for your monitoring system
    class Logger {
        void logMessage(String message) {}
    }

    // since we are using bottom up approach
    // we now design our overall system from sub components
    // singleton design pattern, since only one instance
    // we can use factory design pattern to instantiate all objects we need like entrances, payment processor, parking types
    // we can also instantiate configuration objects that have distances of spots from their entrances

}
