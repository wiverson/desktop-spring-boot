package com.changenode.plugin;

import com.changenode.Log;
import com.changenode.Plugin;

import javax.swing.*;
import java.io.File;

public class FileDrop implements Plugin {

    public void setupFileDropTarget(JTextArea textArea, Log log) {

        //TODO

//        textArea.set
//
//        textArea.setOnDragOver(event -> {
//            if (event.getGestureSource() != textArea && event.getDragboard().hasFiles()) {
//                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
//            }
//            event.consume();
//
//        });
//
//        textArea.setOnDragEntered(event -> textArea.setBackground(
//                new Background(new BackgroundFill(Color.CORNFLOWERBLUE, CornerRadii.EMPTY, Insets.EMPTY))));
//
//        textArea.setOnDragExited(event -> textArea.setBackground(
//                new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY))));
//
//        textArea.setOnDragDropped(event -> {
//            Dragboard db = event.getDragboard();
//            boolean success = false;
//            if (db.hasFiles()) {
//
//                for (File file : db.getFiles()) {
//                    log.log(file.getAbsolutePath());
//                }
//
//                success = true;
//            }
//            /* let the source know whether the information was successfully transferred and used */
//            event.setDropCompleted(success);
//
//            event.consume();
//        });
    }



    @Override
    public void setup(JFrame stage, JTextArea textArea, JToolBar toolBar, Log log, JMenuBar menuBar) {
        log.log("Try dragging one or more files and/or directories here from another application.");
        setupFileDropTarget(textArea, log);
    }
}
