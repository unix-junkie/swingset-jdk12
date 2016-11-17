/*
 * @(#)SliderPanel.java	1.17 01/11/29
 *
 * Copyright 2002 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;

import java.awt.BorderLayout;
import java.awt.GridLayout;

final class SliderListener implements ChangeListener {
	JLabel tf;
	SliderListener(final JLabel f) {
	    tf = f;
	}
	
	public void stateChanged(final ChangeEvent e) {
	    final JSlider s1 = (JSlider)e.getSource();
		tf.setText("Slider Value: " + s1.getValue());
	}
}

/*
 * @version 1.17 11/29/01
 * @author Dave Kloba
 * @author Peter Korn (accessibility support)
 */
public final class SliderPanel extends JPanel      {
    private static final long serialVersionUID = 8594783010501894343L;

    public SliderPanel()    {
        JSlider s;
	JPanel hp;
	JPanel vp;
	GridLayout g;
	JPanel tp;
	JLabel tf;
	ChangeListener listener;

	setLayout(new BorderLayout());

	tf = new JLabel("Slider Value: " );
	this.add(tf, BorderLayout.SOUTH);
	
	tp = new JPanel();
	g = new GridLayout(1, 2);
	g.setHgap(5);
	g.setVgap(5);
	tp.setLayout(g);
	this.add(tp, BorderLayout.CENTER);
		
	listener = new SliderListener(tf);

	hp = new JPanel();
	hp.setLayout(new BoxLayout(hp, BoxLayout.Y_AXIS));
	hp.setBorder(new TitledBorder( 
			SwingSet.lightLoweredBorder, 
			"Horizontal",
			TitledBorder.LEFT,
			TitledBorder.ABOVE_TOP));
	tp.add(hp);

	vp = new JPanel();
	vp.setLayout(new BoxLayout(vp, BoxLayout.X_AXIS));
	vp.setBorder(new TitledBorder( 
			SwingSet.lightLoweredBorder, 
			"Vertical",
			TitledBorder.LEFT,
			TitledBorder.ABOVE_TOP));
	tp.add(vp);

	// Horizontal Slider 1
	JPanel p = new JPanel();
	p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
	p.setBorder(new TitledBorder("Plain"));
	s = new JSlider(-10, 100, 20);
	s.getAccessibleContext().setAccessibleName("Plain");
	s.getAccessibleContext().setAccessibleDescription("A plain slider");
	s.addChangeListener(listener);

	p.add(Box.createRigidArea(SwingSet.vpad5));
	p.add(s);
	p.add(Box.createRigidArea(SwingSet.vpad5));
	hp.add(p);
	hp.add(Box.createRigidArea(SwingSet.vpad10));

	// Horizontal Slider 2
	p = new JPanel();
	p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
	p.setBorder(new TitledBorder("Major Ticks"));
	s = new JSlider(100, 1000, 400);
	s.setPaintTicks(true);
	s.setMajorTickSpacing(100);
	s.getAccessibleContext().setAccessibleName("Major Ticks");
	s.getAccessibleContext().setAccessibleDescription("A slider showing major tick marks");
	s.addChangeListener(listener);

	p.add(Box.createRigidArea(SwingSet.vpad5));
	p.add(s);
	p.add(Box.createRigidArea(SwingSet.vpad5));
	hp.add(p);
	hp.add(Box.createRigidArea(SwingSet.vpad10));

	// Horizontal Slider 3
	p = new JPanel();
	p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
	p.setBorder(new TitledBorder("Minor Ticks, Snap-to-ticks and Labels"));
	s = new JSlider(0, 11, 6);

	s.putClientProperty( "JSlider.isFilled", Boolean.TRUE );

	s.setPaintTicks(true);
	s.setMajorTickSpacing(5);
	s.setMinorTickSpacing(1);

	s.setPaintLabels( true );
	s.setSnapToTicks( true );

	s.getLabelTable().put( new Integer( 11 ), new JLabel( "11", SwingConstants.CENTER ) );
	s.setLabelTable( s.getLabelTable() );

	s.getAccessibleContext().setAccessibleName("Minor Ticks");
	s.getAccessibleContext().setAccessibleDescription("A slider showing major and minor tick marks, with slider action snapping to tick marks, with some ticks visibly labeled");

	s.addChangeListener(listener);

	p.add(Box.createRigidArea(SwingSet.vpad5));
	p.add(s);
	p.add(Box.createRigidArea(SwingSet.vpad5));
	hp.add(p);
	hp.add(Box.createRigidArea(SwingSet.vpad10));

	// Horizontal Slider 4
	p = new JPanel();
	p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
	p.setBorder(new TitledBorder("Disabled"));
	final BoundedRangeModel brm = new DefaultBoundedRangeModel(80, 0, 0, 100);
	  // s = new JSlider(JSlider.HORIZONTAL, 0, 100, 80);
	s = new JSlider(brm);
	s.setPaintTicks(true);
	s.setMajorTickSpacing(20);
	s.setMinorTickSpacing(5);
	s.setEnabled(false);
	s.getAccessibleContext().setAccessibleName("Disabled");
	s.getAccessibleContext().setAccessibleDescription("A slider showing major and minor tick marks that is not enabled (cannot be manipulated)");
	s.addChangeListener(listener);

	p.add(Box.createRigidArea(SwingSet.vpad5));
	p.add(s);
	p.add(Box.createRigidArea(SwingSet.vpad5));
	hp.add(p);
	
//////////////////////////////////////////////////////////////////////////////

	// Vertical Slider 1
	p = new JPanel();
	p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
	p.setBorder(new TitledBorder("Plain"));
	s = new JSlider(SwingConstants.VERTICAL, -10, 100, 20);
	s.getAccessibleContext().setAccessibleName("Plain");
	s.getAccessibleContext().setAccessibleDescription("A plain slider");
	s.addChangeListener(listener);
	p.add(Box.createRigidArea(SwingSet.hpad10));
	p.add(s);
	p.add(Box.createRigidArea(SwingSet.hpad10));
	vp.add(p);
	vp.add(Box.createRigidArea(SwingSet.hpad5));

	// Vertical Slider 2
	p = new JPanel();
	p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
	p.setBorder(new TitledBorder("Major Ticks"));
	s = new JSlider(SwingConstants.VERTICAL, 100, 1000, 400);

	s.putClientProperty( "JSlider.isFilled", Boolean.TRUE );

	s.setPaintTicks(true);
	s.setMajorTickSpacing(100);
	s.getAccessibleContext().setAccessibleName("Major Ticks");
	s.getAccessibleContext().setAccessibleDescription("A slider showing major tick marks");
	s.addChangeListener(listener);
	p.add(Box.createRigidArea(SwingSet.hpad25));
	p.add(s);
	p.add(Box.createRigidArea(SwingSet.hpad25));
	vp.add(p);
	vp.add(Box.createRigidArea(SwingSet.hpad5));

	// Vertical Slider 3
	p = new JPanel();
	p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
	p.setBorder(new TitledBorder("Minor Ticks"));
	s = new JSlider(SwingConstants.VERTICAL, 0, 100, 60);
	s.setPaintTicks(true);
	s.setMajorTickSpacing(20);
	s.setMinorTickSpacing(5);

	s.setPaintLabels( true );

	s.getAccessibleContext().setAccessibleName("Minor Ticks");
	s.getAccessibleContext().setAccessibleDescription("A slider showing major and minor tick marks, with slider action snapping to tick marks, with some ticks visibly labeled");

	s.addChangeListener(listener);
	p.add(Box.createRigidArea(SwingSet.hpad10));
	p.add(s);
	p.add(Box.createRigidArea(SwingSet.hpad10));
	vp.add(p);
	vp.add(Box.createRigidArea(SwingSet.hpad5));

	// Vertical Slider 4
	p = new JPanel();
	p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
	p.setBorder(new TitledBorder("Disabled"));
	s = new JSlider(SwingConstants.VERTICAL, 0, 100, 80);
	s.setPaintTicks(true);
	s.setMajorTickSpacing(20);
	s.setMinorTickSpacing(5);
	s.setEnabled(false);
	s.getAccessibleContext().setAccessibleName("Disabled");
	s.getAccessibleContext().setAccessibleDescription("A slider showing major and minor tick marks that is not enabled (cannot be manipulated)");
	s.addChangeListener(listener);
	p.add(Box.createRigidArea(SwingSet.hpad20));
	p.add(s);
	p.add(Box.createRigidArea(SwingSet.hpad20));
	vp.add(p);
    }
}


