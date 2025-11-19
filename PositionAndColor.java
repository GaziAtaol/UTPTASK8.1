import java.awt.*;

public
    class PositionAndColor {

    public static int encode(int x, int y, Color color){
        int result = x & 0xFFF;
        result = result | ((y & 0xFFF) << 12);
        result = result | ((colorToByte(color) & 0xFF) << 24);
        return result;
    }

    public static int[] decode(int value) {
        int x = value & 0xFFF;
        int y = (value >> 12) & 0xFFF;
        int colorByte = (value >> 24) & 0xFF;
        return new int[]{x, y, colorByte};
    }

    public static int colorToByte(Color color){
        int r = color.getRed() >> 5;
        int g = color.getGreen() >> 5;
        int b = color.getBlue() >> 6;
        return (r << 5) | (g << 2) | b;
    }

    public static Color byteToColor(int value) {
        int r = (value >> 5) & 0x7;
        int g = (value >> 2) & 0x7;
        int b = value & 0x3;
        
        // Expand 3-bit values (r, g) to 8 bits by bit replication
        r = (r << 5) | (r << 2) | (r >> 1);
        g = (g << 5) | (g << 2) | (g >> 1);
        
        // Expand 2-bit value (b) to 8 bits by bit replication
        b = (b << 6) | (b << 4) | (b << 2) | b;
        
        return new Color(r, g, b);
    }
}
