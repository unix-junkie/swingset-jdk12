/*
 * @(#)ScrollPanePanel.java	1.9 01/11/29
 *
 * Copyright 2002 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

import javax.swing.*;
import java.awt.*;

/*
 * @version 1.9 11/29/01
 * @author Jeff Dinkins
 * @author Peter Korn (accessibility support)
 */
public final class ScrollPanePanel extends JPanel      {
    private static final long serialVersionUID = 5536516941152775107L;

    public ScrollPanePanel()    {
        setLayout(new BorderLayout());
	add(new TigerScrollPane(), BorderLayout.CENTER);
    }

}

final class TigerScrollPane extends JScrollPane {

    private static final long serialVersionUID = -4435614201334106105L;

    private static JLabel makeLabel(final String name, final String description) {
	final String filename = "images/" + name;
	final ImageIcon image = SwingSet.sharedInstance().loadImageIcon(filename, description);
	return new JLabel(image);
    }

    TigerScrollPane() {
	final JLabel horizontalRule = makeLabel("scrollpane/header.gif", "Horizontal ruler carved out of stone");
	horizontalRule.getAccessibleContext().setAccessibleName("Horizontal rule");
	final JLabel verticalRule = makeLabel("scrollpane/column.gif", "Vertical ruler carved out of stone");
	verticalRule.getAccessibleContext().setAccessibleName("Vertical rule");
	final JLabel tiger = makeLabel("BigTiger.gif","A rather fierce looking tiger");
	tiger.getAccessibleContext().setAccessibleName("scrolled image");
	tiger.getAccessibleContext().setAccessibleDescription("A rather fierce looking tiger");

	final JLabel cornerLL = makeLabel("scrollpane/corner.gif","Square chunk of stone (lower left)");
	cornerLL.getAccessibleContext().setAccessibleName("Lower left corner");
	cornerLL.getAccessibleContext().setAccessibleDescription("Square chunk of stone");
	final JLabel cornerLR = makeLabel("scrollpane/corner.gif","Square chunk of stone (lower right)");
	cornerLR.getAccessibleContext().setAccessibleName("Lower right corner");
	cornerLR.getAccessibleContext().setAccessibleDescription("Square chunk of stone");
	final JLabel cornerUL = makeLabel("scrollpane/corner.gif","Square chunk of stone (upper left)");
	cornerUL.getAccessibleContext().setAccessibleName("Upper left corner");
	cornerUL.getAccessibleContext().setAccessibleDescription("Square chunk of stone");
	final JLabel cornerUR = makeLabel("scrollpane/corner.gif","Square chunk of stone (upper right)");
	cornerUR.getAccessibleContext().setAccessibleName("Upper right corner");
	cornerUR.getAccessibleContext().setAccessibleDescription("Square chunk of stone");
	    
	setViewportView(tiger);
	setRowHeaderView(verticalRule);
	setColumnHeaderView(horizontalRule);

	setCorner(LOWER_LEFT_CORNER, cornerLL);
	setCorner(LOWER_RIGHT_CORNER, cornerLR);
	setCorner(UPPER_LEFT_CORNER, cornerUL);
	setCorner(UPPER_RIGHT_CORNER, cornerUR);
    }
    
    public Dimension getMinimumSize() {
	return new Dimension(25, 25);
    }
    
    public boolean isOpaque() {
        return true;
    }
}

