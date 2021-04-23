package com.changenode.plugin;

import com.changenode.Log;
import com.changenode.Plugin;

import javax.swing.*;

public class DarkMode implements Plugin {

    private boolean isDark;

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
        } else {
            //TODO set dark
            toggleDark.setText("Dark");
        }
        isDark = !isDark;
    }
}
