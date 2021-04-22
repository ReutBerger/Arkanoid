package ass6;

import java.awt.Color;
import java.lang.reflect.Field;
/**
 * A ColorsParser class.
 * @author Reut Berger
 * @version 1.8.0_201
 * @since 17/6/19
 */
public class ColorsParser {
    /**
     * The method return the real color.
     * @param s - parse color definition
     * @return the specified color
     */
    public static java.awt.Color colorFromString(String s) {
        Color color = null;
        s = s.substring(6, s.length() - 1);
        if (s.startsWith("RGB")) {
            s = s.substring(4, s.length() - 1);
            String[] split = s.split(",");
            int color1 = Integer.parseInt(split[0]);
            int color2 = Integer.parseInt(split[1]);
            int color3 = Integer.parseInt(split[2]);
            color = new Color(color1, color2, color3);
        } else {
            Field field = null;
            try {
                field = Class.forName("java.awt.Color").getField(s);
            } catch (NoSuchFieldException | SecurityException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                color = (Color) field.get(null);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
//          if (s.equals("blue")) {
//                return Color.blue;
//            } else if (s.equals("black")) {
//                return Color.black;
//            } else if (s.equals("cyan")) {
//                return Color.cyan;
//            } else if (s.equals("gray")) {
//                return Color.gray;
//            } else if (s.equals("lightGray")) {
//                return Color.lightGray;
//            } else if (s.equals("green")) {
//                return Color.green;
//            } else if (s.equals("orange")) {
//                return Color.orange;
//            } else if (s.equals("pink")) {
//                return Color.pink;
//            } else if (s.equals("red")) {
//                return Color.red;
//            } else if (s.equals("white")) {
//                return Color.white;
//            } else if (s.equals("yellow")) {
//                return Color.yellow;
//            }
        }
        return color;
    }
}
