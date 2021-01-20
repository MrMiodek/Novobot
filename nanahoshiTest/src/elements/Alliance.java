package elements;

import boardgame.elements.GameActor;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Alliance  implements GameActor {

    PIRATES, NAVY;

    private static final Random r = new Random();

    public Alliance getOpponent(){
        return this.equals(PIRATES) ? NAVY : PIRATES;
    }


    private static final List<Alliance> VALUES =
            Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static Alliance getRandom()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }

}
