package com.changenode;

import com.changenode.plugin.*;
import com.formdev.flatlaf.intellijthemes.FlatDraculaIJTheme;

import javax.swing.*;
import java.awt.*;
import java.io.*;

import static javax.swing.filechooser.FileSystemView.getFileSystemView;

public class BaseApplication {

    /**
     * This is the very simple "registry" for the various demonstration features of this application.
     */
    private static final Plugin[] plugins = new Plugin[]{new StandardMenus(), new HelloWorld(), new FileDrop(),
            new DesktopIntegration(), new LogFile(), new DarkMode()};
    public static File outputFile;
    private static Log log;


    public static void main(String[] args) {
        /*
         * This little bit of code causes this application to route the debugging output for this application to a
         * log file in your "default" directory.
         * */
        try {
            outputFile = File.createTempFile("debug", ".log", getFileSystemView().getDefaultDirectory());
            PrintStream output = new PrintStream(new BufferedOutputStream(new FileOutputStream(outputFile)), true);
            System.setOut(output);
            System.setErr(output);
        } catch (IOException e) {
            e.printStackTrace();
        }

        FlatDraculaIJTheme.install();

        JFrame.setDefaultLookAndFeelDecorated(true);

        openGUI();
    }

    private static void openGUI() {
        JFrame jFrame = new JFrame("Hello World Swing Example");
        jFrame.setLayout(new FlowLayout());
        jFrame.setSize(500, 360);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jFrame.setBackground(Color.BLACK);

        JToolBar jToolBar = new JToolBar();

        JTextArea jTextArea = new JTextArea();

        JMenuBar jMenuBar = new JMenuBar();

        jFrame.add(jToolBar);
        jFrame.add(jTextArea);
        jFrame.add(jMenuBar);

        jFrame.setVisible(true);

        for (Plugin plugin : plugins) {
            plugin.setup(jFrame, jTextArea, jToolBar, log, jMenuBar);
        }

    }

}
