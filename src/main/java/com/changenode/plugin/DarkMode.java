package com.changenode.plugin;

import com.changenode.Log;
import com.changenode.Plugin;
import com.formdev.flatlaf.intellijthemes.FlatDraculaIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatLightFlatIJTheme;
import de.jangassen.MenuToolkit;
import de.jangassen.model.AppearanceMode;

import javax.swing.*;

public class DarkMode implements Plugin {

    private boolean isDark = true;

    private JFrame scene;
    private JButton toggleDark;

    @Override
    public void setup(JFrame stage, JTextArea textArea, JToolBar toolBar, Log log, JMenuBar menuBar) {
        scene = stage;
        toggleDark = new JButton();
        toggleDark.setText("Light");
        toggleDark.addActionListener(e -> toggleDark());
        toolBar.add(toggleDark);
    }

    private void toggleDark() {
        if (isDark) {
            //TODO Set light
            toggleDark.setText("Light");
            MenuToolkit.toolkit().setAppearanceMode(AppearanceMode.LIGHT);
            FlatLightFlatIJTheme.install();
        } else {
            //TODO set dark
            toggleDark.setText("Dark");
            MenuToolkit.toolkit().setAppearanceMode(AppearanceMode.DARK);
            FlatDraculaIJTheme.install();
        }
        isDark = !isDark;

        JFrame.setDefaultLookAndFeelDecorated(true);
        SwingUtilities.updateComponentTreeUI(scene);

    }
}
