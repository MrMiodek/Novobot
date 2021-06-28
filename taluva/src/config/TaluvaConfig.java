package config;

import boardgame.elements.GameConfig;
import elements.tiles.TileSet;
import gameplay.TaluvaState;

public class TaluvaConfig implements GameConfig<TaluvaState> {

    @Override
    public TaluvaState getInitialGameState() {
        TileSet tiles = LoadTileFile.getInitialTileSet();
        return new TaluvaState(tiles);
    }

}
