package com.changenode.plugin;

import com.changenode.BaseApplication;
import com.changenode.Log;
import com.changenode.Plugin;

import javax.swing.*;

import static java.awt.Desktop.getDesktop;
import static java.lang.System.out;
import static java.util.Calendar.getInstance;

public class LogFile implements Plugin {
    @Override
    public void setup(JFrame stage, JTextArea textArea, JToolBar toolBar, Log log, JMenuBar menuBar) {

        JMenu menu = new JMenu("Debug");
        JMenuItem findDebugLog = new JMenuItem("Find Debug Log");
        findDebugLog.addActionListener(e -> showDebugLog());

        JMenuItem writeHelloWorldToLog = new JMenuItem("Write Hello World to Log");
        writeHelloWorldToLog.addActionListener(e -> out.println("Hello World! " + getInstance().getTime()));

        menu.add(findDebugLog);
        menu.add(writeHelloWorldToLog);
        menuBar.add(menu);
    }

    private void showDebugLog() {
        getDesktop().browseFileDirectory(BaseApplication.outputFile);
    }
}
