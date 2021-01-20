package elements.tiles;

import elements.Alliance;

public class Ship {

    final Alliance alliance;
    final ShipClass shipClass;

    public Ship(Alliance alliance, ShipClass shipClass){
        this.alliance = alliance;
        this.shipClass = shipClass;
    }

    public Alliance getAlliance() {
        return alliance;
    }

    public ShipClass getShipClass() {
        return shipClass;
    }

    @Override
    public String toString() {
        return shipClass.toString() + " " + alliance.toString();
    }

    public static Ship UNKNOWN = new Ship (null, null){
        @Override
        public String toString() {
            return "UNKNOWN";
        }
    };

}
