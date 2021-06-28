package config;

import gameplay.TaluvaState;
import org.junit.Assert;
import org.junit.Test;

public class TaluvaConfigTest {

    @Test
    public void getInitialGameState() {
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        TaluvaConfig config = new TaluvaConfig();
        TaluvaState state = config.getInitialGameState();
        Assert.assertEquals(state.getTiles().getSize(), 48);
    }

}