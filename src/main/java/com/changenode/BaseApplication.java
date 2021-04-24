package com.changenode;

import com.changenode.plugin.*;
import com.formdev.flatlaf.intellijthemes.FlatDraculaIJTheme;
import de.jangassen.MenuToolkit;
import de.jangassen.model.AppearanceMode;

import javax.swing.*;
import java.io.*;

import static com.changenode.plugin.StandardMenus.isMac;
import static javax.swing.filechooser.FileSystemView.getFileSystemView;

public class BaseApplication implements Log {

    public static File outputFile;
    /**
     * This is the very simple "registry" for the various demonstration features of this application.
     */
    private final Plugin[] plugins = new Plugin[]{new StandardMenus(), new HelloWorld(), new FileDrop(),
            new DesktopIntegration(), new LogFile(), new DarkMode(), new DesktopHandlers()};

    BaseForm baseForm;

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

        if (isMac()) {
            System.setProperty("apple.laf.useScreenMenuBar", "true");
        }

        MenuToolkit.toolkit().setAppearanceMode(AppearanceMode.AUTO);

        FlatDraculaIJTheme.install();

        JFrame.setDefaultLookAndFeelDecorated(true);

        BaseApplication baseApplication = new BaseApplication();
        baseApplication.openGUI();
    }

    private void openGUI() {

        JFrame jFrame = new JFrame("Base Application");

        baseForm = new BaseForm();
        jFrame.add(baseForm.panel);

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jFrame.setSize(1024, 768);


        JMenuBar jMenuBar = new JMenuBar();
        jFrame.setJMenuBar(jMenuBar);
        jFrame.setVisible(true);

        for (Plugin plugin : plugins) {
            log("Setting up... " + plugin.getClass().getName());
            plugin.setup(jFrame, baseForm.textArea, baseForm.toolBar, this, jMenuBar);
        }

    }

    @Override
    public void log(String s) {
        System.out.println(s);
        baseForm.textArea.append(System.lineSeparator());
        baseForm.textArea.append(s);
    }
}
