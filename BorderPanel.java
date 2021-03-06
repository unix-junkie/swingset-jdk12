/*
 * @(#)BorderPanel.java	1.8 01/11/29
 *
 * Copyright 2002 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

import javax.swing.*;

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Font;

import javax.swing.border.*;

/*
 * @version 1.8 11/29/01
 * @author Dave Kloba
 */
public final class BorderPanel extends JPanel      {
    private static final long serialVersionUID = -6293894212491834255L;

    public BorderPanel()    {
        setLayout(new BorderLayout());
        final JPanel tp = new JPanel();
	final GridLayout g = new GridLayout(0, 2);
	g.setHgap(3);
	g.setVgap(1);
        tp.setLayout(g);


        tp.add(new BorderLabel("LineBorder", 
                               new LineBorder(Color.darkGray, 2)));
        tp.add(new BorderLabel("BevelBorder RAISED",
                               BorderFactory.createRaisedBevelBorder()));
        tp.add(new BorderLabel("BevelBorder LOWERED", 
                               BorderFactory.createLoweredBevelBorder()));
        tp.add(new BorderLabel("EtchedBorder",
                               BorderFactory.createEtchedBorder()));
        tp.add(new BorderLabel("TitledBorder 1",
                               new TitledBorder(LineBorder.createBlackLineBorder(),
                               "Using LineBorder")));
        tp.add(new BorderLabel("TitledBorder 2",
                               new TitledBorder(BorderFactory.createRaisedBevelBorder(),
                               "Using BevelBorder")));
        tp.add(new BorderLabel("TitledBorder 3",
                                new TitledBorder(
                                    new TitledBorder(LineBorder.createBlackLineBorder(),
                                        "Using a TitledBorder"),
                                    "as the Border",
                                TitledBorder.RIGHT ,
                                TitledBorder.BOTTOM )));

        tp.add(new BorderLabel("TitledBorder 4",
                         new TitledBorder(new LineBorder(Color.black, 1), 
                                "Using Courier 16 bold",
                                 TitledBorder.LEFT,
                                 TitledBorder.TOP,
                                 new Font("Courier", Font.BOLD, 16))));

        tp.add(new BorderLabel("TitledBorder 5",
                               new TitledBorder(new EmptyBorder(1, 1, 1, 1),
                               "Using EmptyBorder",
                               TitledBorder.LEFT , TitledBorder.TOP )));

        tp.add(new BorderLabel("Matte Border",
                               new MatteBorder(18,18,18,18, 
                                   SwingSet.sharedInstance().loadImageIcon("images/swirl.gif","Swirl"))));


        add(tp, BorderLayout.CENTER);


    }
}

final class BorderLabel extends JLabel {
    private static final long serialVersionUID = 9136088785354592021L;

    BorderLabel(final String text, final Border b) {
        super(text);
        setBorder(b);
        setHorizontalAlignment(SwingConstants.CENTER);
    }
}
