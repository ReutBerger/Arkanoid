package ass6;

import java.awt.Color;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.TreeMap;

import javax.imageio.ImageIO;

/**
 * A BlocksDefinitionReader class.
 * @author Reut Berger
 * @version 1.8.0_201
 * @since 17/6/19
 */
public class BlocksDefinitionReader {
    /**
     * The method read from file the blocks definitions.
     * @param reader the file to read from
     * @return the BlocksFromSymbolsFactory
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) {
        Map<String, Integer> spacerWidths = new TreeMap<>();
        Map<String, BlockCreator> blockCreators = new TreeMap<>();
        Map<String, String> defaultB = new TreeMap<>();
        BlocksFromSymbolsFactory factory = null;
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line, symbol = null;
        String[] string = null;
        Map<Integer, Color> colors = new TreeMap<Integer, Color>();
        Map<Integer, Image> images = new TreeMap<Integer, Image>();
        BlockBuilder b = null;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                if ((!line.equals("")) && (!line.startsWith("#"))) {
                    if (line.startsWith("default")) {
                        line = line.substring(8, line.length());
                        string = line.split(" ");
                        for (String s : string) {
                            String[] parts = s.split(":");
                            defaultB.put(parts[0], parts[1]);
                        }
                        continue;
                    } else if (line.startsWith("bdef")) {
                        b = new BlockBuilder();
                        if (defaultB.containsKey("height")) {
                            b.setHeight(Integer.parseInt(defaultB.get("height")));
                        }
                        if (defaultB.containsKey("width")) {
                            b.setWidth(Integer.parseInt(defaultB.get("width")));
                        }
                        if (defaultB.containsKey("stroke")) {
                            b.setStrokeColor(ColorsParser.colorFromString(defaultB.get("stroke")));
                        }
                        if (defaultB.containsKey("hit_points")) {
                            b.setHitPoints(Integer.parseInt(defaultB.get("hit_points")));
                        }
                        if (defaultB.containsKey("fill") || defaultB.containsKey("/fill-[0-9]/")) {
                            if (defaultB.containsKey("fill")) {
                                defaultB.replace("fill", defaultB.get("fill"), "fill-1");
                            }
                            for (int i = 1; i <= b.getHitPoints(); i++) {
                                if (defaultB.containsKey("fill-" + i)) {
                                    String fillString = defaultB.get("fill-" + i);
                                    if (fillString.startsWith("image")) {
                                        fillString = fillString.substring(6, fillString.length() - 1);
                                        InputStream is = ClassLoader.getSystemClassLoader().
                                                         getResourceAsStream(fillString);
                                        Image img = null;
                                        try { // trying reading the image
                                            img = ImageIO.read(is);
                                           //img = ImageIO.read(new File(fillString));
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        } // put the image in the map
                                        if (img != null) {
                                            images.put(i, img);
                                        }
                                    } else if (fillString.startsWith("color")) {
                                        Color color = ColorsParser.colorFromString(fillString);
                                        colors.put(i, color);
                                    }
                                }
                            }
                        }
                        line = line.substring(5, line.length());
                        string = line.split(" ");
                        for (String s : string) {
                            String[] parts = s.split(":");
                            if (parts[0].equals("symbol")) {
                                symbol = parts[1];
                            }
                            if (parts[0].equals("height")) {
                                b.setHeight(Integer.parseInt(parts[1]));
                            }
                            if (parts[0].equals("width")) {
                                b.setWidth(Integer.parseInt(parts[1]));
                            }
                            if (parts[0].equals("stroke")) {
                                b.setStrokeColor(ColorsParser.colorFromString(parts[1]));
                            }
                            if (parts[0].equals("hit_points")) {
                                b.setHitPoints(Integer.parseInt(parts[1]));
                            }
                            if (parts[0].startsWith("fill")) {
                                if (parts[0].equals("fill")) {
                                    parts[0] = "fill-1";
                                }
                                for (int i = 1; i <= b.getHitPoints(); i++) {
                                    if (parts[0].equals("fill-" + i)) {
                                        String fillString = parts[1];
                                        if (fillString.startsWith("image")) {
                                            fillString = fillString.substring(6, fillString.length() - 1);
                                            Image img = null;
                                            try { // trying reading the image
                                                InputStream is = ClassLoader.getSystemClassLoader().
                                                                 getResourceAsStream(fillString);
                                                img = ImageIO.read(is);
                                                //img = ImageIO.read(new File(fillString));
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            } // put the image in the map
                                            if (img != null) {
                                                images.put(i, img);
                                            }
                                        } else if (fillString.startsWith("color")) {
                                            Color color = ColorsParser.colorFromString(fillString);
                                            colors.put(i, color);
                                        }
                                    }
                                }
                            }
                        }
                        Map<Integer, Color> colors1 = new TreeMap<Integer, Color>();
                       colors1.putAll(colors);
//                       Map<Integer, Image> images1 = new TreeMap<Integer, Image>();
//                       images1.putAll(images);
                        b.setFillColors(colors1);
                        b.setFillImages(images);
                    } else if (line.startsWith("sdef")) {
                        line = line.substring(5, line.length());
                        string = line.split(" ");
                        String[] s =  string[0].split(":");
                        String key = s[1];
                        s =  string[1].split(":");
                        int val = Integer.parseInt(s[1]);
                        spacerWidths.put(key, val);
                    }
                    blockCreators.put(symbol, b);
                    }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        factory = new BlocksFromSymbolsFactory(spacerWidths, blockCreators);
        return factory;
        }
}
