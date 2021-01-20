package elements.tiles;

public enum ShipClass {

    SMALL(1){
        @Override
        public int[] getMoveOptions() {
            return new int []{0};
        }
    }, MEDIUM(2){
        @Override
        public int[] getMoveOptions() {
            return new int []{-1,1};
        }
    }, LARGE(3){
        @Override
        public int[] getMoveOptions() {
            return new int []{-1,0,1};
        }
    };

    private final int value;

    ShipClass(int value){
        this.value = value;
    }

    public abstract int[] getMoveOptions();

    public int getValue(){
        return value;
    }

}
