package com.changenode;

import javax.swing.*;

/**
 * This is a very basic, leaky example of a plugin interface
 */
public interface Plugin {

    void setup(JFrame stage, JTextArea textArea, JToolBar toolBar, Log log, JMenuBar menuBar);

}
