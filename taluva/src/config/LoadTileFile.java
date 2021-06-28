package config;

import elements.tiles.Terrain;
import elements.tiles.Tile;
import elements.tiles.TileSet;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class LoadTileFile {

    public static TileSet getInitialTileSet(){
        try {
            File tilesFile = new File("taluva/etc/original_tiles.txt");
            Scanner reader = new Scanner(tilesFile);
            ArrayList<Tile> tiles = new ArrayList<>();
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] tokens = line.split("\\s+");
                Terrain left = Terrain.valueOf(tokens[0]);
                Terrain right = Terrain.valueOf(tokens[1]);
                int amount = Integer.parseInt(tokens[2]);
                for(int i=amount; i> 0; i--){
                    tiles.add(new Tile(left, right));
                }
            }
            reader.close();
            return new TileSet(tiles);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        System.exit(0);
        return null;
    }

}
