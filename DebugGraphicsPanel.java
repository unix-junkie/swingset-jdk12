/*
 * @(#)DebugGraphicsPanel.java	1.8 01/11/29
 *
 * Copyright 2002 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;


/**
 * Debug Graphics!
 *
 * @version 1.8 11/29/01
 * @author Jeff Dinkins
 * @author Peter Korn (accessibility support)
 */
public final class DebugGraphicsPanel extends JPanel 
{
    private static final long serialVersionUID = 232884007200482960L;

    JPanel components;

    JButton button = new JButton("Button");
    JRadioButton radio = new JRadioButton("RadioButton");
    JCheckBox check = new JCheckBox("Checkbox");
    JSlider slider = new JSlider(SwingConstants.HORIZONTAL, 0, 100, 50);
    JScrollBar scrollbar = new JScrollBar(Adjustable.HORIZONTAL, 50, 20, 0, 100);

    JSlider flashSlider = new JSlider(SwingConstants.HORIZONTAL, 1, 50, 10);
    JCheckBox buttonCheckbox = new JCheckBox("Button");
    JCheckBox radioCheckbox = new JCheckBox("RadioButton");
    JCheckBox checkboxCheckbox = new JCheckBox("Checkbox");
    JCheckBox sliderCheckbox = new JCheckBox("Slider");
    JCheckBox scrollbarCheckbox = new JCheckBox("ScrollBar");

    DebugGraphicsListener debugGraphicsListener = new DebugGraphicsListener();
    ChangeListener sliderListener;

    public DebugGraphicsPanel(final SwingSet swing) {
	sliderListener = new ChangeListener() {
	    public void stateChanged(final ChangeEvent e) {
		final JSlider s = (JSlider)e.getSource();
		DebugGraphics.setFlashTime(s.getValue());
	    }
	};
	flashSlider.addChangeListener(sliderListener);
	

	setBorder(SwingSet.emptyBorder5);
	setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

	// *************** buttons ****************
	components = SwingSet.createVerticalPanel(true);
	components.setBorder(SwingSet.emptyBorder10);

        button.setEnabled(false);
        radio.setEnabled(false);
        radio.setSelected(true);
        check.setEnabled(false);
        check.setSelected(true);
	
	// Add buttons to buttonPanel
	final JPanel buttonPanel = SwingSet.createHorizontalPanel(false);
	buttonPanel.setBorder(SwingSet.etchedBorder10);
	buttonPanel.setAlignmentX(LEFT_ALIGNMENT);
	buttonPanel.setAlignmentY(TOP_ALIGNMENT);

	JLabel l = (JLabel) components.add(new JLabel("Buttons"));
	l.setFont(swing.boldFont);
	buttonPanel.add(button);
	buttonPanel.add(Box.createRigidArea(SwingSet.hpad20));
	buttonPanel.add(radio);
	buttonPanel.add(Box.createRigidArea(SwingSet.hpad20));
	buttonPanel.add(check);
	components.add(buttonPanel);
	components.add(Box.createRigidArea(SwingSet.vpad20));

	// *************** slider ****************

        // slider.setEnabled(false);
	slider.setEnabled(false);
	slider.setPaintTicks(true);
	slider.setMinorTickSpacing(10);
	slider.setMajorTickSpacing(40);
	slider.getAccessibleContext().setAccessibleName("Sample slider");

	final JPanel sliderPanel = new JPanel() {
	    private static final long serialVersionUID = -5788757002076409424L;

	    public Dimension getMaximumSize() {
		return new Dimension(super.getMaximumSize().width, 60);
	    }
	};
	sliderPanel.setLayout(new BoxLayout(sliderPanel, BoxLayout.X_AXIS));
	sliderPanel.setBorder(SwingSet.etchedBorder10);
	sliderPanel.setAlignmentX(LEFT_ALIGNMENT);
	sliderPanel.setAlignmentY(TOP_ALIGNMENT);

	l = (JLabel) components.add(new JLabel("Slider"));
	l.setFont(swing.boldFont);
	sliderPanel.add(slider);
	components.add(sliderPanel);
	components.add(Box.createRigidArea(SwingSet.vpad20));

	// *************** scrollbar ****************

        scrollbar.setEnabled(false);
	scrollbar.getAccessibleContext().setAccessibleName("Sample scrollbarlider");

	final JPanel scrollbarPanel = new JPanel() {
	    private static final long serialVersionUID = -5166243690704239038L;

	    public Dimension getMaximumSize() {
		return new Dimension(super.getMaximumSize().width, 60);
	    }
	};
	scrollbarPanel.setLayout(new BoxLayout(scrollbarPanel, BoxLayout.Y_AXIS));
	scrollbarPanel.setBorder(SwingSet.etchedBorder10);
	scrollbarPanel.setAlignmentX(LEFT_ALIGNMENT);
	scrollbarPanel.setAlignmentY(TOP_ALIGNMENT);

	final JPanel box = SwingSet.createHorizontalPanel(false);
	box.add(Box.createRigidArea(SwingSet.hpad5));
	box.add(scrollbar);
	box.add(Box.createRigidArea(SwingSet.hpad5));

	l = (JLabel) components.add(new JLabel("ScrollBar"));
	l.setFont(swing.boldFont);
	scrollbarPanel.add(Box.createRigidArea(SwingSet.vpad5));
	scrollbarPanel.add(box);
	scrollbarPanel.add(Box.createRigidArea(SwingSet.vpad5));
	components.add(scrollbarPanel);
	components.add(Box.createRigidArea(SwingSet.vpad20));

	l = (JLabel) components.add(new JLabel("Note: the above components are intentionally disabled."));
	components.add(Box.createRigidArea(SwingSet.vpad5));
	l = (JLabel) components.add(new JLabel("Choose a component checkbox at right, then click on"));
	l = (JLabel) components.add(new JLabel("the \"Repaint\" button to see debug graphics at work."));


	// ***** fill out the rest of the panel
	components.add(Box.createGlue());

	// *************** Create the debug graphics controls ****************
	final JPanel controls = new JPanel() {
	    private static final long serialVersionUID = 4148890003104794455L;

	    public Dimension getMaximumSize() {
		return new Dimension(300, super.getMaximumSize().height);
	    }
	};
	controls.setLayout(new BoxLayout(controls, BoxLayout.Y_AXIS));

	final JPanel debugControls = SwingSet.createHorizontalPanel(true);
	debugControls.setAlignmentY(TOP_ALIGNMENT);
	debugControls.setAlignmentX(LEFT_ALIGNMENT);

	final JPanel leftColumn = SwingSet.createVerticalPanel(false);
	leftColumn.setAlignmentX(LEFT_ALIGNMENT);
	leftColumn.setAlignmentY(TOP_ALIGNMENT);

	debugControls.add(leftColumn);
	debugControls.add(Box.createRigidArea(SwingSet.hpad20));
	debugControls.add(Box.createRigidArea(SwingSet.hpad20));

	controls.add(debugControls);

	// Display Options
	l = new JLabel("Use Debug Graphics On:");
	leftColumn.add(l);
	l.setFont(swing.boldFont);

 	buttonCheckbox.addItemListener(debugGraphicsListener);
	buttonCheckbox.setToolTipText("Turns DebugGraphics on or off.");
 	leftColumn.add(buttonCheckbox);

 	radioCheckbox.addItemListener(debugGraphicsListener);
	radioCheckbox.setToolTipText("Turns DebugGraphics on or off.");
 	leftColumn.add(radioCheckbox);

 	checkboxCheckbox.addItemListener(debugGraphicsListener);
	checkboxCheckbox.setToolTipText("Turns DebugGraphics on or off.");
 	leftColumn.add(checkboxCheckbox);

 	sliderCheckbox.addItemListener(debugGraphicsListener);
	sliderCheckbox.setToolTipText("Turns DebugGraphics on or off.");
 	leftColumn.add(sliderCheckbox);

 	scrollbarCheckbox.addItemListener(debugGraphicsListener);
	scrollbarCheckbox.setToolTipText("Turns DebugGraphics on or off.");
 	leftColumn.add(scrollbarCheckbox);

	// debug flashTime
	leftColumn.add(Box.createRigidArea(SwingSet.vpad40));
	l = new JLabel("Debug Flash Interval:");
	l.setFont(swing.boldFont);
	leftColumn.add(l);
	leftColumn.add(flashSlider);
	flashSlider.setMaximumSize(new Dimension(150, 60));
	flashSlider.setPaintTicks(true);
	flashSlider.setMinorTickSpacing(5);
	flashSlider.setMajorTickSpacing(20);
	flashSlider.setToolTipText("Sets the sleep time between graphics operations, from 1 to 50 milliseconds");
	flashSlider.getAccessibleContext().setAccessibleName("Debug Flash Interval");

	// repaint button
	leftColumn.add(Box.createRigidArea(SwingSet.vpad40));
 	final JButton repaintButton = new JButton("Repaint");
	repaintButton.setToolTipText("Causes the selected components to be repainted using DebugGraphics.");
 	repaintButton.addActionListener(debugGraphicsListener);
 	leftColumn.add(repaintButton);
 	leftColumn.add(Box.createGlue());

	add(components);
	add(Box.createRigidArea(SwingSet.hpad10));
 	add(controls);
    }

    public void resetAll() {
	scrollbarCheckbox.setSelected(false);
	buttonCheckbox.setSelected(false);
	radioCheckbox.setSelected(false);
	sliderCheckbox.setSelected(false);
	checkboxCheckbox.setSelected(false);
    }

	
    class DebugGraphicsListener implements ItemListener, ActionListener {
	    boolean repaintButton = false;
	    boolean repaintRadio = false;
	    boolean repaintCheck = false;
	    boolean repaintSlider = false;
	    boolean repaintScrollBar = false;

	    public void actionPerformed(final ActionEvent e) {
		if(e.getSource() instanceof JButton) {
		    if(repaintButton) {
			button.repaint();
		    }
		    if(repaintRadio) {
			radio.repaint();
		    }
		    if(repaintCheck) {
			check.repaint();
		    }
		    if(repaintSlider) {
			slider.repaint();
		    }
		    if(repaintScrollBar) {
			scrollbar.repaint();
		    }
		}
	    }

	    public void itemStateChanged(final ItemEvent e) {
		final AbstractButton b = (AbstractButton) e.getSource();
		final String label = b.getText();
		if(label.equals("Button")) {
		    if(b.isSelected()) {
			button.setDebugGraphicsOptions(DebugGraphics.FLASH_OPTION);
			repaintButton = true;
		    } else {
			button.setDebugGraphicsOptions(DebugGraphics.NONE_OPTION);
			repaintButton = false;
		    }
		} else if(label.equals("RadioButton")) {
		    if(b.isSelected()) {
			radio.setDebugGraphicsOptions(DebugGraphics.FLASH_OPTION);
			repaintRadio = true;
		    } else {
			radio.setDebugGraphicsOptions(DebugGraphics.NONE_OPTION);
			repaintRadio = false;
		    }
		} else if(label.equals("Checkbox")) {
		    if(b.isSelected()) {
			check.setDebugGraphicsOptions(DebugGraphics.FLASH_OPTION);
			repaintCheck = true;
		    } else {
			check.setDebugGraphicsOptions(DebugGraphics.NONE_OPTION);
			repaintCheck = false;
		    }
		} else if(label.equals("Slider")) {
		    if(b.isSelected()) {
			slider.setDebugGraphicsOptions(DebugGraphics.FLASH_OPTION);
			repaintSlider = true;
		    } else {
			slider.setDebugGraphicsOptions(DebugGraphics.NONE_OPTION);
			repaintSlider = false;
		    }
		} else if(label.equals("ScrollBar")) {
		    if(b.isSelected()) {
			scrollbar.setDebugGraphicsOptions(DebugGraphics.FLASH_OPTION);
			repaintScrollBar = true;
		    } else {
			scrollbar.setDebugGraphicsOptions(DebugGraphics.NONE_OPTION);
			repaintScrollBar = false;
		    }
		} else {
		}
	}
    }

}
