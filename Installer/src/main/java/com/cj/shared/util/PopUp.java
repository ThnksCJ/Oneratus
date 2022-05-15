package com.cj.shared.util;

import javax.swing.*;

public class PopUp {

    public static void show(String header, String msg){
        showMessage(header, msg, JOptionPane.WARNING_MESSAGE);
    }

    private static void showMessage(final String header, final String message, final int type) {
        try {
            final boolean headless = Boolean.parseBoolean(System.getProperty("java.awt.headless"));
            System.setProperty("java.awt.headless", String.valueOf(false));

            Class<?> uiManagerClass = null;
            Object oldLookAndFeel = null;
            try {
                uiManagerClass = Class.forName("javax.swing.UIManager");
                oldLookAndFeel = uiManagerClass.getMethod("getLookAndFeel")
                        .invoke(null);
                final String systemLookAndFeelClassName = (String) uiManagerClass.getMethod("getSystemLookAndFeelClassName")
                        .invoke(null);
                uiManagerClass.getMethod("setLookAndFeel", String.class)
                        .invoke(null, systemLookAndFeelClassName);
            } catch (final Exception ignored) {}

            final Class<?> jframeClass = Class.forName("javax.swing.JFrame");
            final Object frame = jframeClass.newInstance();
            jframeClass.getMethod("setAlwaysOnTop", boolean.class)
                    .invoke(frame, true);
            jframeClass.getMethod("setFocusable", boolean.class)
                    .invoke(frame, true);

            final Class<?> componentClass = Class.forName("java.awt.Component");
            final Class<?> joptionPaneClass = Class.forName("javax.swing.JOptionPane");
            joptionPaneClass.getMethod("showMessageDialog", componentClass, Object.class, String.class, int.class)
                    .invoke(null, frame, message, header, type);

            jframeClass.getMethod("dispose")
                    .invoke(frame);

            try {
                if (uiManagerClass != null && oldLookAndFeel != null) {
                    final Class<?> lookAndFeelClass = Class.forName("javax.swing.LookAndFeel");
                    uiManagerClass.getMethod("setLookAndFeel", lookAndFeelClass)
                            .invoke(null, oldLookAndFeel);
                }
            } catch (final Exception ignored) {}

            System.setProperty("java.awt.headless", String.valueOf(headless));
        } catch (final Throwable t) {
            t.printStackTrace();
        }
    }
}
