/*
 * @(#)ComponentOrientationChanger.java	1.5 01/11/29
 *
 * Copyright 2002 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*
 * The ComponentOrientationChanger is a menu that lets the user select the
 * desired ComponentOrientation setting for all the Components in SwingSet.
 * When a selection is made, the ComponentOrientationChanger will walk the 
 * entire Component tree and changing each Components orientation setting to
 * the selected value.
 */
final class ComponentOrientationChanger extends JMenu implements ItemListener {

    private static final long serialVersionUID = 6012088503691186810L;

    static ComponentOrientationChanger create() {
        
        return new ComponentOrientationChanger();
        


    }

    ComponentOrientationChanger() {
        super("Component Orientation");

        getAccessibleContext().setAccessibleDescription(
           "Sub-menu containing options for changing the orientation of the Swing Components.");

        final ButtonGroup orientationGroup = new ButtonGroup();

        ltrRb = (JRadioButtonMenuItem)add(new JRadioButtonMenuItem("Left To Right"));
        ltrRb.getAccessibleContext().setAccessibleDescription("Orient Components for left to right languages.");
        ltrRb.setSelected(true);
        ltrRb.addItemListener(this);
        orientationGroup.add(ltrRb);
        
        rtlRb = (JRadioButtonMenuItem)add(new JRadioButtonMenuItem("Right To Left"));
        rtlRb.getAccessibleContext().setAccessibleDescription("Orient Components for left to right languages.");
        rtlRb.addItemListener(this);
        orientationGroup.add(rtlRb);
    }

    JRadioButtonMenuItem ltrRb, rtlRb;

    public void itemStateChanged(final ItemEvent e) {
        
        final JRadioButtonMenuItem rb = (JRadioButtonMenuItem) e.getSource();
        if (rb.isSelected()) {
            final String selected = rb.getText();
            ComponentOrientation orientation;
            if (selected.equals("Left To Right")) {
                orientation = ComponentOrientation.LEFT_TO_RIGHT;
            } else  {
                orientation = ComponentOrientation.RIGHT_TO_LEFT;
            } 
            final Container swingRoot = SwingSet.sharedInstance().getRootComponent();
            applyOrientation( swingRoot, orientation );
            fireActionPerformed(new ActionEvent(this,0,"OrientationChanged"));
            swingRoot.validate();
            swingRoot.repaint();
        }
        
    }

    
    private void applyOrientation(final Component c, final ComponentOrientation o) {
        c.setComponentOrientation(o);

        if( c instanceof JMenu ) {
            final JMenu menu = (JMenu)c;
            final int ncomponents = menu.getMenuComponentCount();
            for (int i = 0 ; i < ncomponents ; ++i) {
                applyOrientation( menu.getMenuComponent(i), o );
            }
        }
        else if( c instanceof Container ) {
            final Container container = (Container)c;
            final int ncomponents = container.getComponentCount();
            for (int i = 0 ; i < ncomponents ; ++i) {
                applyOrientation( container.getComponent(i), o );
            }
        }
    }
    
}

