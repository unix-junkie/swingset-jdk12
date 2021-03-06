/*
 * @(#)ButtonPanel.java	1.13 01/11/29
 *
 * Copyright 2002 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;


/**
 * Buttons!
 *
 * @version 1.10 08/26/98
 * @author Jeff Dinkins
 */
public final class ButtonPanel extends JPanel 
{
    private static final long serialVersionUID = -8345626217664033991L;

    private static final ImageIcon LEFT = SwingSet.sharedInstance().loadImageIcon("images/WebSpice/left.gif","fancy green arrow pointing left");
    private static final ImageIcon LEFT_DOWN = SwingSet.sharedInstance().loadImageIcon("images/WebSpice/leftDown.gif","fancy yellow arrow pointing left");
    private static final ImageIcon LEFT_ROLLOVER = SwingSet.sharedInstance().loadImageIcon("images/WebSpice/leftRollover.gif","fancy purple arrow pointing left");
    private static final ImageIcon RIGHT = SwingSet.sharedInstance().loadImageIcon("images/WebSpice/right.gif","fancy green arrow pointing right");
    private static final ImageIcon RIGHT_DOWN = SwingSet.sharedInstance().loadImageIcon("images/WebSpice/rightDown.gif","fancy yellow arrow pointing right");
    private static final ImageIcon RIGHT_ROLLOVER = SwingSet.sharedInstance().loadImageIcon("images/WebSpice/rightRollover.gif","fancy purple arrow pointing right");

    public ButtonPanel(final SwingSet swing) {
	setBorder(SwingSet.emptyBorder5);
	setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

	// *************** buttons ****************
	// text buttons
	final JPanel textButtons = SwingSet.createHorizontalPanel(false);
	textButtons.setAlignmentX(LEFT_ALIGNMENT);
	Border buttonBorder = new TitledBorder(null, "Text Buttons", 
					       TitledBorder.LEFT, TitledBorder.TOP,
					       swing.boldFont);

	final Border emptyBorder = new EmptyBorder(5,5,5,5);
	Border compoundBorder = new CompoundBorder( buttonBorder, emptyBorder);

	textButtons.setBorder(compoundBorder);

	JButton button;
	button = new JButton("One");
	button.setToolTipText("This is a Button with Text");
        button.setMnemonic('o');
	swing.buttons.addElement(button);
	textButtons.add(button);
	textButtons.add(Box.createRigidArea(SwingSet.hpad10));
	
	button = new JButton("Two");
	button.setToolTipText("This is a Button with Text");
        button.setMnemonic('t');
	swing.buttons.addElement(button);
	textButtons.add(button);
	textButtons.add(Box.createRigidArea(SwingSet.hpad10));

	button = new JButton("Three");
	button.setBackground(new Color(204, 204, 255));
	button.setToolTipText("This is a Button with the background color set to be lavender");
        button.setMnemonic('h');
	swing.buttons.addElement(button);
	textButtons.add(button);


	// image buttons
	final JPanel imageButtons = SwingSet.createHorizontalPanel(false);
	imageButtons.setAlignmentX(LEFT_ALIGNMENT);

	buttonBorder = new TitledBorder(null, "Image Buttons", 
					       TitledBorder.LEFT, TitledBorder.TOP,
					       swing.boldFont);
	compoundBorder = new CompoundBorder(buttonBorder, emptyBorder);
	imageButtons.setBorder(compoundBorder);

	// 1 image
	button = new JButton(swing.upButton);
	button.setToolTipText("This is a Button with a Icon");
	button.getAccessibleContext().setAccessibleName("Right");
	// button.setDebugGraphicsOptions(DebugGraphics.FLASH_OPTION);
	swing.buttons.addElement(button);
	imageButtons.add(button);
	imageButtons.add(Box.createRigidArea(SwingSet.hpad10));
	
	// 2 images
	button = new JButton(swing.upButton);
	button.setToolTipText("This is a Button with a Icon and a PressedIcon");
	button.getAccessibleContext().setAccessibleName("Right");
	swing.buttons.addElement(button);
	button.setPressedIcon(swing.downButton);
	imageButtons.add(button);
	imageButtons.add(Box.createRigidArea(SwingSet.hpad10));

	// 3 images
	button = new JButton(swing.upButton);
	button.setToolTipText("This is a Button with a Icon, PressedIcon, and DisabledIcon");
	button.getAccessibleContext().setAccessibleName("Right");
	button.setPressedIcon(swing.downButton);
	button.setDisabledIcon(swing.disabledButton);
	swing.buttons.addElement(button);
	imageButtons.add(button);

	// text&image buttons
	final JPanel tiButtons = SwingSet.createHorizontalPanel(false);
	tiButtons.setAlignmentX(LEFT_ALIGNMENT);
	buttonBorder = new TitledBorder(null, "Rollover Image Buttons", 
					       TitledBorder.LEFT, TitledBorder.TOP,
					       swing.boldFont);
	compoundBorder = new CompoundBorder(buttonBorder, emptyBorder);
	tiButtons.setBorder(compoundBorder);

	button = new JButton("Left", LEFT);
	button.setPressedIcon(LEFT_DOWN);
	button.setRolloverIcon(LEFT_ROLLOVER);
	button.setRolloverEnabled(true);
	button.setToolTipText("This is a Button with a RolloverIcon");
	swing.buttons.addElement(button);
	tiButtons.add(button);
	tiButtons.add(Box.createRigidArea(SwingSet.hpad10));

	button = new JButton("Right", RIGHT);
	button.setPressedIcon(RIGHT_DOWN);
	button.setRolloverIcon(RIGHT_ROLLOVER);
	button.setRolloverEnabled(true);
	button.setToolTipText("This is a Button with a Rollover Icon");
	swing.buttons.addElement(button);
	tiButtons.add(button);
	tiButtons.add(Box.createHorizontalBox());

	// Add button panels to buttonPanel
	final JPanel buttonPanel = SwingSet.createVerticalPanel(true);
	buttonPanel.setAlignmentX(LEFT_ALIGNMENT);
	buttonPanel.setAlignmentY(TOP_ALIGNMENT);


	buttonPanel.add(textButtons);

	buttonPanel.add(Box.createVerticalStrut(10));


	buttonPanel.add(imageButtons);

	buttonPanel.add(Box.createVerticalStrut(10));

	buttonPanel.add(tiButtons);
	buttonPanel.add(tiButtons);
	buttonPanel.add(Box.createGlue());


	// *************** Create the button controls ****************
	final JPanel controls = new JPanel() {
	    private static final long serialVersionUID = -2348847337953317210L;

	    public Dimension getMaximumSize() {
		return new Dimension(300, super.getMaximumSize().height);
	    }
	};
	controls.setLayout(new BoxLayout(controls, BoxLayout.Y_AXIS));
	controls.setAlignmentY(TOP_ALIGNMENT);
	controls.setAlignmentX(LEFT_ALIGNMENT);

	final JPanel buttonControls = SwingSet.createHorizontalPanel(true);
	buttonControls.setAlignmentY(TOP_ALIGNMENT);
	buttonControls.setAlignmentX(LEFT_ALIGNMENT);

	final JPanel leftColumn = SwingSet.createVerticalPanel(false);
	leftColumn.setAlignmentX(LEFT_ALIGNMENT);
	leftColumn.setAlignmentY(TOP_ALIGNMENT);

	final JPanel rightColumn = new LayoutControlPanel(swing, swing.buttons);

	buttonControls.add(leftColumn);
	buttonControls.add(Box.createRigidArea(SwingSet.hpad20));
	buttonControls.add(rightColumn);
	buttonControls.add(Box.createRigidArea(SwingSet.hpad20));

	controls.add(buttonControls);

       
	// Display Options
	JLabel l = new JLabel("Display Options:");
	leftColumn.add(l);
	l.setFont(swing.boldFont);

 	final JCheckBox bordered = new JCheckBox("Paint Border");
	bordered.setToolTipText("Click here to turn border painting on or off.");
        bordered.setMnemonic('b');
 	bordered.setSelected(true);
 	bordered.addItemListener(swing.buttonDisplayListener);
 	leftColumn.add(bordered);
 
 	final JCheckBox focused = new JCheckBox("Paint Focus");
	focused.setToolTipText("Click here to turn focus painting on or off.");
        focused.setMnemonic('f');
 	focused.setSelected(true);
 	focused.addItemListener(swing.buttonDisplayListener);
 	leftColumn.add(focused);

	final JCheckBox enabled = new JCheckBox("Enabled");
	enabled.setToolTipText("Click here to enable or disable the buttons.");
	enabled.setSelected(true);
        enabled.setMnemonic('e');
	enabled.addItemListener(swing.buttonDisplayListener);
	leftColumn.add(enabled);

	final JCheckBox filled = new JCheckBox("Content Filled");
	filled.setToolTipText("Click here to control the filling of the content area.");
	filled.setSelected(true);
        filled.setMnemonic('i');
	filled.addItemListener(swing.buttonDisplayListener);
	leftColumn.add(filled);

	leftColumn.add(Box.createRigidArea(SwingSet.vpad20));
	
	l = new JLabel("Pad Amount:");
	leftColumn.add(l);
	l.setFont(swing.boldFont);
	
	final ButtonGroup group = new ButtonGroup();
	final JRadioButton defaultPad = new JRadioButton("Default");
	defaultPad.setToolTipText("Uses the default padding between the border and label.");
        defaultPad.setMnemonic('d');
	group.add(defaultPad);
	defaultPad.setSelected(true);
 	defaultPad.addItemListener(swing.buttonPadListener);
	leftColumn.add(defaultPad);

	final JRadioButton zeroPad = new JRadioButton("0");
	zeroPad.setToolTipText("Uses no padding between the border and label.");
        zeroPad.setMnemonic('0');
	group.add(zeroPad);
 	zeroPad.addItemListener(swing.buttonPadListener);
	leftColumn.add(zeroPad);

	final JRadioButton tenPad = new JRadioButton("10");
        tenPad.setMnemonic('1');
	tenPad.setToolTipText("Uses a 10 pixel pad between the border and label.");
	group.add(tenPad);
 	tenPad.addItemListener(swing.buttonPadListener);
	leftColumn.add(tenPad);

	leftColumn.add(Box.createRigidArea(SwingSet.vpad20));

	add(buttonPanel);
	add(Box.createRigidArea(SwingSet.hpad10));
 	add(controls);
    }

    
}
