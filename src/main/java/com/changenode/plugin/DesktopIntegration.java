package com.changenode.plugin;

import com.changenode.Log;
import com.changenode.Plugin;

import javax.swing.*;
import java.awt.*;
import java.awt.Taskbar.Feature;
import java.awt.image.BufferedImage;

import static com.changenode.plugin.StandardMenus.Configure;
import static java.awt.Taskbar.getTaskbar;
import static java.awt.Taskbar.isTaskbarSupported;

public class DesktopIntegration implements Plugin {

    int currentIconProgress = 1;

    Image defaultIcon;
    Image redCircleIcon;

    public JMenu extraDesktopIntegration(Log log) {
        if (!isTaskbarSupported())
            return null;

        log.log("");
        log.log("Desktop integration flags for this platform include:");

        for (Feature feature : Feature.values()) {
            log.log(" " + feature.name() + " " + getTaskbar().isSupported(feature));
        }

        if (getTaskbar().isSupported(Feature.ICON_IMAGE)) {
            defaultIcon = getTaskbar().getIconImage();

            BufferedImage bufferedImage = new BufferedImage(256, 256, BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics2D = bufferedImage.createGraphics();
            graphics2D.setColor(Color.red);
            graphics2D.fillOval(0, 0, 256, 256);
            graphics2D.dispose();

            redCircleIcon = bufferedImage;

        }
        JMenuItem useCustomIcon = Configure("Use Custom App Icon", x -> getTaskbar().setIconImage(redCircleIcon));
        JMenuItem useDefaultAppIcon = Configure("Use Default App Icon", x -> getTaskbar().setIconImage(defaultIcon));
        useCustomIcon.setEnabled(getTaskbar().isSupported(Feature.ICON_IMAGE));
        useDefaultAppIcon.setEnabled(getTaskbar().isSupported(Feature.ICON_IMAGE));

        JMenu desktopIntegration = new JMenu("Desktop");

        JMenuItem setIconBadge = Configure("Set Badge", x -> getTaskbar().setIconBadge("1"));
        JMenuItem removeIconBadge = Configure("Remove Badge", x -> getTaskbar().setIconBadge("1"));

        setIconBadge.setEnabled(getTaskbar().isSupported(Feature.ICON_BADGE_TEXT));
        removeIconBadge.setEnabled(getTaskbar().isSupported(Feature.ICON_BADGE_TEXT));

        JMenuItem addProgress = Configure("Add Icon Progress", x -> getTaskbar().setProgressValue(currentIconProgress++));
        JMenuItem clearProgress = Configure("Clear Icon Progress", x -> {
            currentIconProgress = -1;
            getTaskbar().setProgressValue(currentIconProgress++);
        });
        addProgress.setEnabled(getTaskbar().isSupported(Feature.PROGRESS_VALUE));
        clearProgress.setEnabled(getTaskbar().isSupported(Feature.PROGRESS_VALUE));

        JMenuItem requestUserAttention = Configure("Request User Attention (5s)", x -> requestUserAttention());

        requestUserAttention.setEnabled(getTaskbar().isSupported(Feature.USER_ATTENTION));

        desktopIntegration.add(setIconBadge);
        desktopIntegration.add(removeIconBadge);
        desktopIntegration.add(addProgress);
        desktopIntegration.add(clearProgress);
        desktopIntegration.add(useCustomIcon);
        desktopIntegration.add(useDefaultAppIcon);
        desktopIntegration.add(requestUserAttention);

        return desktopIntegration;
    }

    private void requestUserAttention() {

        SwingWorker task = new SwingWorker<Void, Void>() {

            @Override
            protected Void doInBackground() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                getTaskbar().requestUserAttention(true, true);
                return null;
            }

        };

        new Thread(task).start();
    }

    @Override
    public void setup(JFrame stage, JTextArea textArea, JToolBar toolBar, Log log, JMenuBar menuBar) {
        menuBar.add(extraDesktopIntegration(log));
    }
}
