/*
 * @(#)SwingSetApplet.java	1.12 01/11/29
 *
 * Copyright 2002 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.metal.MetalLookAndFeel;

import java.awt.*;

import java.applet.*;
import SwingSet;

public final class SwingSetApplet extends JApplet {
    private static final long serialVersionUID = -224546757455147774L;

    JPanel panel;
    
    public void init() {

        final String vers = System.getProperty("java.version");
        final Applet thisApplet = this;

        if (vers.compareTo("1.1.2") < 0) {
            System.out.println("!!!WARNING: Swing must be run with a " +
                               "1.1.2 or higher version VM!!!");
        }

	// Force SwingSet to come up in the Cross Platform L&F
	try {
	    final String lookAndFeelClassName = UIManager.getCrossPlatformLookAndFeelClassName();
	    UIManager.setLookAndFeel(lookAndFeelClassName);
	    if (lookAndFeelClassName.equals(MetalLookAndFeel.class.getName())) {
                /*
                 * Correct Look&Feel defaults for Java 1.4+.
                 */
                final ColorUIResource labelForeground = new ColorUIResource(102, 102, 153);
                UIManager.put("Label.foreground", labelForeground);
                UIManager.put("TitledBorder.titleColor", labelForeground);
	    }
	    // If you want the System L&F instead, comment out the above line and
	    // uncomment the following:
	    // UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	} catch (final Exception exc) {
	    System.err.println("Error loading L&F: " + exc);
	}

        panel = new JPanel();
        getContentPane().add(panel,BorderLayout.CENTER);
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        final JPanel progressPanel = SwingSet.createVerticalPanel(false);
        panel.add(Box.createGlue());
        panel.add(progressPanel);
        panel.add(Box.createGlue());

        progressPanel.add(Box.createGlue());

        final Dimension d = new Dimension(400, 20);
        SwingSet.progressLabel = new JLabel("Loading, please wait...");
        SwingSet.progressLabel.setMaximumSize(d);
        progressPanel.add(SwingSet.progressLabel);
        progressPanel.add(Box.createRigidArea(new Dimension(1,20)));

        SwingSet.progressBar = new JProgressBar();
        SwingSet.progressBar.setMaximumSize(d);
        SwingSet.progressBar.setMinimum(0);
        SwingSet.progressBar.setMaximum(SwingSet.totalPanels);
        SwingSet.progressBar.setValue(0);
        progressPanel.add(SwingSet.progressBar);
        progressPanel.add(Box.createGlue());
        progressPanel.add(Box.createGlue());

        // show the panel
        final Rectangle ab = getContentPane().getBounds();
        panel.setPreferredSize(new Dimension(ab.width,ab.height));
        getContentPane().add(panel,BorderLayout.CENTER);
        validate();
        setVisible(true);

        final SwingSet sw = new SwingSet(thisApplet);
        getContentPane().remove(panel);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(sw, BorderLayout.CENTER);
        validate();
        repaint();
        sw.requestDefaultFocus();
    }
}

