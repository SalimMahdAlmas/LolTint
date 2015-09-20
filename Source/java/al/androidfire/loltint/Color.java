package al.androidfire.loltint;

/*
 * Copyright (C) 2015 AndroidFire
 * https://github.com/AndroidFire/ColorHelper/blob/master/LICENSE
 */

/**
 * Alternative class of Color with some extra features added
 */
public class Color extends android.graphics.Color {

    public static int COLOR_RED = parseColor("F44336");
    public static int COLOR_PINK = parseColor("E91E63");
    public static int COLOR_PURPLE = parseColor("9C27B0");
    public static int COLOR_INDIGO = parseColor("3F51B5");
    public static int COLOR_BLUE = parseColor("2196F3");
    public static  int COLOR_TEAL = parseColor("009688");
    public static int COLOR_GREEN = parseColor("4CAF50");
    public static int COLOR_YELLOW = parseColor("FFEB3B");
    public static int COLOR_BROWN = parseColor("FFEB3B");
    public static int COLOR_GREY = parseColor("9E9E9E");
    public static int COLOR_BLUE_GREY = parseColor("607D8B");
    public static int COLOR_DEEP_ORANGE = parseColor("#FF5722");
    public static int COLOR_LIGHT_GREEN = parseColor("8BC34A");
    public  static int COLOR_LIGHT_BLUE = parseColor("03A9F4");
    public  static int COLOR_DEEP_PURPLE = parseColor("673AB7");



    public static String convertToARGB(int color) {
        String alpha = Integer.toHexString(android.graphics.Color.alpha(color));
        String red = Integer.toHexString(android.graphics.Color.red(color));
        String green = Integer.toHexString(android.graphics.Color.green(color));
        String blue = Integer.toHexString(android.graphics.Color.blue(color));

        if (alpha.length() == 1) {
            alpha = "0" + alpha;
        }

        if (red.length() == 1) {
            red = "0" + red;
        }

        if (green.length() == 1) {
            green = "0" + green;
        }

        if (blue.length() == 1) {
            blue = "0" + blue;
        }

        return "#" + alpha + red + green + blue;
    }

    public static String toHex(int color) {

        String ALGORITHM = "0123456789ABCDEF";
        color = Math.max(0, Math.min(color, 255));

        return String.valueOf(ALGORITHM.charAt((color - color % 16) / 16)
                + ALGORITHM.charAt(color % 16));

    }

    public static int toDarker(int colorZ) {
        float[] hsv = new float[3];
        int color = colorZ;
        android.graphics.Color.colorToHSV(color, hsv);
        hsv[2] *= 0.8f;
        color = android.graphics.Color.HSVToColor(hsv);
        return color;

    }
    public static int lighter(int color, float factor) {
        int red = (int) ((Color.red(color) * (1 - factor) / 255 + factor) * 255);
        int green = (int) ((Color.green(color) * (1 - factor) / 255 + factor) * 255);
        int blue = (int) ((Color.blue(color) * (1 - factor) / 255 + factor) * 255);
        return Color.argb(Color.alpha(color), red, green, blue);
    }
    public static int darker (int color, float factor) {
        int a = Color.alpha( color );
        int r = Color.red( color );
        int g = Color.green( color );
        int b = Color.blue( color );

        return Color.argb( a,
                Math.max( (int)(r * factor), 0 ),
                Math.max( (int)(g * factor), 0 ),
                Math.max( (int)(b * factor), 0 ) );
    }






    public static int alpha(int color) {
        return color >>> 24;
    }


    public static int red(int color) {
        return (color >> 16) & 0xFF;
    }

    public static int green(int color) {
        return (color >> 8) & 0xFF;
    }

    public static int blue(int color) {
        return color & 0xFF;
    }


    public static int rgb(int red, int green, int blue) {
        return (0xFF << 24) | (red << 16) | (green << 8) | blue;
    }


    public static int argb(int alpha, int red, int green, int blue) {
        return (alpha << 24) | (red << 16) | (green << 8) | blue;
    }

    public static float saturation(int color) {
        int r = (color >> 16) & 0xFF;
        int g = (color >> 8) & 0xFF;
        int b = color & 0xFF;


        int V = Math.max(b, Math.max(r, g));
        int temp = Math.min(b, Math.min(r, g));

        float S;

        if (V == temp) {
            S = 0;
        } else {
            S = (V - temp) / (float) V;
        }

        return S;
    }

    public static int parseColor(String a) {
        String Color = null;
        if (!a.startsWith("#")) {
            Color = "#"+a;
        }
        else {
            Color = a;
        }
        return android.graphics.Color.parseColor(Color);

    }

    public static float brightness(int color) {
        int r = (color >> 16) & 0xFF;
        int g = (color >> 8) & 0xFF;
        int b = color & 0xFF;

        int V = Math.max(b, Math.max(r, g));

        return (V / 255.f);
    }




}