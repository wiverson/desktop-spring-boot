package com.changenode.plugin;

import com.changenode.Log;
import com.changenode.Plugin;

import javax.swing.*;
import java.awt.*;
import java.awt.desktop.*;
import java.io.File;

public class DesktopHandlers implements Plugin, SystemEventListener, AboutHandler, OpenFilesHandler, OpenURIHandler, PreferencesHandler {
    private Log log;

    @Override
    public void setup(JFrame stage, JTextArea textArea, JToolBar toolBar, Log log, JMenuBar menuBar) {
        this.log = log;

        Desktop.getDesktop().addAppEventListener(this);
        Desktop.getDesktop().setAboutHandler(this);
        Desktop.getDesktop().setOpenFileHandler(this);
        Desktop.getDesktop().setOpenURIHandler(this);
        Desktop.getDesktop().setPreferencesHandler(this);
    }

    @Override
    public void handleAbout(AboutEvent e) {
        log.log("AboutEvent received.");
    }

    @Override
    public void openFiles(OpenFilesEvent e) {
        log.log("Open Files search term: " + e.getSearchTerm());
        for (File f : e.getFiles()) {
            log.log("File open: " + f.getAbsolutePath());
        }
    }

    @Override
    public void openURI(OpenURIEvent e) {
        log.log("OpenURIEvent:" + e.getURI().toString());
    }

    @Override
    public void handlePreferences(PreferencesEvent e) {
        log.log("PreferencesEvent received.");
    }
}
