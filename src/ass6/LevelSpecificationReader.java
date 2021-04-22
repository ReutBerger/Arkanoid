package ass6;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

/**
 * A LevelSpecificationReader class.
 * @author Reut Berger
 * @version 1.8.0_201
 * @since 16/6/19
 */
public class LevelSpecificationReader {
    private BlocksFromSymbolsFactory factory;
    /**
     * The method read from file the level definitions.
     * @param reader the file to read from
     * @return the BlocksFromSymbolsFactory
     */
    public List<LevelInformation> fromReader(java.io.Reader reader) {
        List<LevelInformation> listOfLevels = new ArrayList<LevelInformation>();
        List<List<String>> listLevels = new ArrayList<List<String>>();
        listLevels = splitToLevels(reader);
        for (int i = 0; i < listLevels.size(); i++) {
            listOfLevels.add(createNewLevel(listLevels.get(i)));
        }
        return listOfLevels;
    }
    /**
     * The method split the file to levels.
     * @param reader - the file
     * @return list of levels
     */
    public List<List<String>> splitToLevels(java.io.Reader reader) {
        List<List<String>> list = new ArrayList<List<String>>();
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;
        List<String> string = new ArrayList<String>();
        //StringBuilder s = new StringBuilder();
        try {
            while ((line = bufferedReader.readLine()) != null) {
                if (line.equals("START_LEVEL")) {
                   continue;
                }
                if (line.equals("END_LEVEL")) {
                    list.add(string);
                    string = new ArrayList<String>();
                    continue;
                }
               // s.append(line).append("\n");
                string.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
    /**
     * The method create new level.
     * @param list - the level details
     * @return  a new level
     */
    public LevelInformation createNewLevel(List<String> list) {
        Level level = new Level();
        boolean isReadingBlocks = false;
        int numOfRows = 0;
        for (String s : list) {
            if (s.equals("START_BLOCKS")) {
                isReadingBlocks = true;
                continue;
            } else if (s.equals("END_BLOCKS")) {
                isReadingBlocks = false;
            }
            if (isReadingBlocks) {
                readingSymbols(level, s, numOfRows);
                numOfRows++;
            } else if (!isReadingBlocks) {
                String[] parts = s.trim().split(":");
                if (parts.length >= 2) {
                    String key = parts[0];
                    String value = parts[1];
                    checkOptions(key, value, level);
                }
            }
        }
        if (level.levelName() == null || level.numberOfBalls() == 0 || level.getBackground() == null
                || level.numberOfBlocksToRemove() == 0 || level.paddleSpeed() == 0 || level.paddleWidth() == 0
                || level.paddleSpeed() == 0 || level.blocksStartX() == 0 || level.blocksStartY() == 0
                || level.rowHeight() == 0) {
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return level;
    }
    /**
     * The method check which option to load to level.
     * @param key - the key
     * @param val - the value
     * @param level - the level
     */
    public void checkOptions(String key, String val, Level level) {
        if (key.equals("level_name")) {
            level.setLevelName(val);
            return;
        }
        if (key.equals("ball_velocities")) {
            int numOfBallsCounter = 0;
            String[] velocities = val.split(" ");
            for (String v : velocities) {
                String[] velocity = v.split(",");
                // Error in the file!
                if (!(velocity.length == 2)) {
                    try {
                        throw new Exception();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                String angle = velocity[0];
                String speed = velocity[1];
                level.addVelocity(Velocity.fromAngleAndSpeed(Double.parseDouble(angle), Double.parseDouble(speed)));
                numOfBallsCounter++;
            }
           level.setNumOfBalls(numOfBallsCounter);
           return;
        }
        if (key.equals("background")) {
            Background b = null;
            if (val.startsWith("color")) {
                b = new Background(ColorsParser.colorFromString(val));
            } else {
                val = val.substring(6, val.length() - 1);
                InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(val);
                Image img = null;
                try {
                    img = ImageIO.read(is);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                b = new Background(img);
            }
            level.setBackground(b);
            return;
        }
        if (key.equals("paddle_speed")) {
            level.setPaddleSpeed(Integer.parseInt(val));
            return;
        }
        if (key.equals("paddle_width")) {
            level.setPaddleWidth(Integer.parseInt(val));
            return;
        }
        if (key.equals("block_definitions")) {
            level.setBlockDefinitions(val);
//            BlocksDefinitionReader bDR = new BlocksDefinitionReader();
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(val);
            this.factory = BlocksDefinitionReader.fromReader(new InputStreamReader(is));
            return;
        }
        if (key.equals("blocks_start_x")) {
            level.setblocksStartX(Integer.parseInt(val));
            return;
        }
        if (key.equals("blocks_start_y")) {
            level.setblocksStartY(Integer.parseInt(val));
            return;
        }
        if (key.equals("row_height")) {
            level.setRowHeight(Integer.parseInt(val));
            return;
        }
        if (key.equals("num_blocks")) {
            level.setNumOfBlocks(Integer.parseInt(val));
            return;
        }
    }
    /**
     * The method analyze the symbols.
     * @param level - the kevel
     * @param s - the symbol
     * @param numOfRows - the num of the row
     */
    public void readingSymbols(Level level, String s, int numOfRows) {
        String[] symbols = s.split("(?!^)");
        int width = 0;
        for (String symbol: symbols) {
            if (this.factory.isSpaceSymbol(symbol)) {
                width += this.factory.getSpaceWidth(symbol);
            }
            if (this.factory.isBlockSymbol(symbol)) {
                level.addBlock(this.factory.getBlock(symbol, level.blocksStartX()
                               + width, level.blocksStartY() + level.rowHeight() * numOfRows));
                width += level.blocks().get(0).getCollisionRectangle().getWidth();
            }
        }
    }
}
