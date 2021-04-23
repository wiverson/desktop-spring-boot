package com.changenode.plugin;

import com.changenode.Log;
import com.changenode.Plugin;

import javax.swing.*;

public class HelloWorld implements Plugin {

    JButton button;

    @Override
    public void setup(JFrame stage, JTextArea textArea, JToolBar toolBar, Log log, JMenuBar menuBar) {
        button = new JButton();
        button.setText("Hello World");
        button.addActionListener(event -> log.log("Hello World! " + java.util.Calendar.getInstance().getTime()));
        toolBar.add(button);
    }
}
