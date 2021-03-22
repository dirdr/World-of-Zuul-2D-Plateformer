import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Map {


    private double aX; //x Position
    private double aY; //y Position

    private int aXMin; //x Minimum Position
    private int aXMax; //x Maximum Position
    private int aYMin; //Y Minimum position
    private int aYMax;//y max position

    private int[][] aMap;
    private int aOneTileSize;
    private int aNumberOfRows;
    private int aNumberOfColumns;
    private int aWidth;
    private int aHeight;


    private SpriteSheet aTileSpriteSheet;
    private int aNumberOfTilesInTheTileSet;
    private ArrayList<BufferedImage> aTiles;


    private int aRawStartDraw;
    private int aColumnsStartDraw;
    private int aNumberOfRawsToDraw;
    private int aNumberOfColumnsToDraw;

    public Map(final int pTileSize) {
        this.aOneTileSize = pTileSize;
        // Max Number of Raws we can draw is the GamePanel Height Const divided by the size of one single tile
        this.aNumberOfRawsToDraw = GamePanel.aHEIGHT / this.aOneTileSize;
        // Max Number of Columns we can draw is The GamePanel Width Const divided by the size of one single tile
        this.aNumberOfColumnsToDraw = GamePanel.aWIDTH / this.aOneTileSize;
    }

    /**
     * Method to load all the different tile from the TileSet Image
     */
    public void load() {
        BufferedImageManager vLoader = new BufferedImageManager();
        try {
            this.aTileSpriteSheet = new SpriteSheet(vLoader.load("TileSet/spr_VillageTileSetNoBkg_strip.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.aNumberOfTilesInTheTileSet = 48;
        int vTemp = 0;
        //we go through all the tile's sprite and load them into the BufferedImage ArrayList
        for (int i = 0; i < this.aNumberOfTilesInTheTileSet; i++) {
            aTiles.add(aTileSpriteSheet.spritePicker(16 + vTemp, 16, 32, 32));
            vTemp += 64;
        }

    }


}
