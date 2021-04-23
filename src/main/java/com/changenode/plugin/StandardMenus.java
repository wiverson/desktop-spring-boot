package com.changenode.plugin;

import com.changenode.Log;
import com.changenode.Plugin;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

import static java.lang.System.getProperty;

public class StandardMenus implements Plugin {

    private JFrame stage;
    private JMenuBar menuBar;
    private Log output;

    public static boolean isMac() {
        return getProperty("os.name").contains("Mac");
    }

    public static JMenuItem Configure(String name, ActionListener action) {
        return Configure(name, action, 0);
    }

    public static JMenuItem Configure(String name, ActionListener action, int keyCode) {
        JMenuItem item = new JMenuItem(name);
        item.addActionListener(action);
        if (keyCode != 0)
            item.setMnemonic(keyCode);
        return item;
    }

    private void openFileDialog() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Open File");
        int result = fileChooser.showOpenDialog(stage);

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            output.log(file.getAbsolutePath());
            return;
        } else {
            output.log("Open File cancelled.");
        }
    }

    public void standardMenus() {

        JMenu file = new JMenu("File");
        JMenuItem newFile = Configure("New", x -> output.log("File -> New"), KeyEvent.VK_N);
        JMenuItem open = Configure("Open...", x -> openFileDialog(), KeyEvent.VK_O);

        file.add(newFile);
        file.add(open);

        if (!isMac()) {
            JMenuItem quit = Configure("Quit", x -> System.exit(0), KeyEvent.VK_Q);
            file.add(quit);
        }

        JMenu edit = new JMenu("Edit");
        JMenuItem undo = Configure("Undo", x -> output.log("Undo"), KeyEvent.VK_Z);
        edit.add(undo);
        JMenuItem redo = Configure("Redo", x -> output.log("Redo"), KeyEvent.VK_R);
        edit.add(redo);
        edit.addSeparator();
        JMenuItem cut = Configure("Cut", x -> output.log("Cut"), KeyEvent.VK_CUT);
        edit.add(cut);
        JMenuItem copy = Configure("Copy", x -> output.log("Copy"), KeyEvent.VK_COPY);
        edit.add(copy);
        JMenuItem paste = Configure("Paste", x -> output.log("Paste"), KeyEvent.VK_PASTE);
        edit.add(paste);

        menuBar.add(file);
        menuBar.add(edit);
    }

    @Override
    public void setup(JFrame stage, JTextArea textArea, JToolBar toolBar, Log log, JMenuBar menuBar) {
        this.menuBar = menuBar;
        this.output = log;
        this.stage = stage;

        standardMenus();
    }
}
