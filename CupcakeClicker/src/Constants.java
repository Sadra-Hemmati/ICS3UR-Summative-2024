package CupcakeClicker.src;

import java.awt.Font;

/**
 * The Constants class contains various constants used throughout the application.
 */
public class Constants {
    /**
     * The Dimensions class contains constants related to the dimensions of various GUI components.
     */
    public static class Dimensions {
        public static final int DEFAULT_WIDTH = 1500;
        public static final int LEFT_PANEL_WIDTH = DEFAULT_WIDTH / 3;
        public static final int MIDDLE_PANEL_WIDTH = DEFAULT_WIDTH / 3;
        public static final int RIGHT_PANEL_WIDTH = DEFAULT_WIDTH / 3;

        public static final int DEFAULT_HEIGHT = 750;
        public static final int LEFT_PANEL_HEIGHT = DEFAULT_HEIGHT;
        public static final int MIDDLE_PANEL_HEIGHT = DEFAULT_HEIGHT;
        public static final int RIGHT_PANEL_HEIGHT = DEFAULT_HEIGHT;
    }

    /**
     * The Fonts class contains constants related to the fonts used in the application.
     */
    public static class Fonts {
        public static final Font AGENCY_FB_BOLD = new Font("Agency FB", Font.BOLD, 48);
        public static final Font AGENCY_FB = new Font("Agency FB", Font.PLAIN, 48);

        public static final Font LEFT_PANEL = AGENCY_FB_BOLD.deriveFont(48f);

        public static final Font GENERATOR_TITLE = AGENCY_FB_BOLD.deriveFont(30f);
        public static final Font GENERATOR_NAME = AGENCY_FB_BOLD.deriveFont(20f);
        public static final Font GENERATOR_TEXT = AGENCY_FB.deriveFont(18f);

        public static final Font UPGRADE_TITLE = AGENCY_FB_BOLD.deriveFont(30f);
        public static final Font UPGRADE_NAME = AGENCY_FB_BOLD.deriveFont(20f);
        public static final Font UPGRADE_TEXT = AGENCY_FB.deriveFont(18f);

        public static final Font PRESTIGE_TITLE = AGENCY_FB_BOLD.deriveFont(30f);
        public static final Font PRESTIGE_TEXT = AGENCY_FB_BOLD.deriveFont(20f);

        public static final Font BUTTON_FONT = AGENCY_FB.deriveFont(18f);
        public static final Font BUTTON_FONT_BOLD = BUTTON_FONT.deriveFont(Font.BOLD);
    }

    /**
     * The GeneratorID class contains constants representing the IDs of various generators.
     */
    public static class GeneratorID {
        public static final int AUTO_CLICKER = 0;
        public static final int OVEN = 1;
        public static final int KITCHEN = 2;
        public static final int PLANTATION = 3;
        public static final int FARM = 4;
        public static final int FACTORY = 5;
    }
}
