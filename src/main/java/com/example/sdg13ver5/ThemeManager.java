package com.example.sdg13ver5;

import atlantafx.base.theme.PrimerDark;
import atlantafx.base.theme.PrimerLight;
import atlantafx.base.theme.Theme;
import javafx.application.Application;

import java.util.prefs.Preferences;

public final class ThemeManager {

    private static final String PREF_KEY = "sdg13.theme";
    private static final String LIGHT = "light";
    private static final String DARK = "dark";
    private static final Preferences PREFS = Preferences.userNodeForPackage(ThemeManager.class);

    private ThemeManager() {}

    public static boolean isDark() {
        return DARK.equals(PREFS.get(PREF_KEY, LIGHT));
    }

    public static void apply() {
        Theme theme = isDark() ? new PrimerDark() : new PrimerLight();
        Application.setUserAgentStylesheet(theme.getUserAgentStylesheet());
    }

    public static void toggle() {
        PREFS.put(PREF_KEY, isDark() ? LIGHT : DARK);
        apply();
    }
}
