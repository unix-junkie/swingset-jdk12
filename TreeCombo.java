/*
 * @(#)TreeCombo.java	1.8 01/11/29
 *
 * Copyright 2002 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import java.util.Vector;
import java.awt.*;
import javax.swing.tree.*;

public final class TreeCombo extends JComboBox {
    private static final long serialVersionUID = -6971947508222867819L;

    static final int OFFSET = 16;

    public TreeCombo(final TreeModel aTreeModel) {
        setModel(new TreeToListModel(aTreeModel));
        setRenderer(new ListEntryRenderer());
    }

    final class TreeToListModel extends AbstractListModel implements ComboBoxModel,TreeModelListener {
	private static final long serialVersionUID = -4999586587124338968L;

        TreeModel source;
        boolean invalid = true;
        Object currentValue;
        Vector cache = new Vector();

        public TreeToListModel(final TreeModel aTreeModel) {
            source = aTreeModel;
            aTreeModel.addTreeModelListener(this);
            setRenderer(new ListEntryRenderer());
        }

        public void setSelectedItem(final Object anObject) {
            currentValue = anObject;
            fireContentsChanged(this, -1, -1);
        }

        public Object getSelectedItem() {
            return currentValue;
        }

        public int getSize() {
            validate();
            return cache.size();
        }

        public Object getElementAt(final int index) {
            return cache.elementAt(index);
        }

        public void treeNodesChanged(final TreeModelEvent e) {
            invalid = true;
        }

        public void treeNodesInserted(final TreeModelEvent e) {
            invalid = true;
        }

        public void treeNodesRemoved(final TreeModelEvent e) {
            invalid = true;
        }

        public void treeStructureChanged(final TreeModelEvent e) {
            invalid = true;
        }

        void validate() {
            if(invalid) {
                cache = new Vector();
                cacheTree(source.getRoot(),0);
                if(cache.size() > 0) {
		    currentValue = cache.elementAt(0);
		}
                invalid = false;             
                fireContentsChanged(this, 0, 0);
            }
        }

        void cacheTree(final Object anObject,int level) {
            if(source.isLeaf(anObject)) {
		addListEntry(anObject,level,false);
	    } else {
                final int c = source.getChildCount(anObject);
                int i;
                Object child;

                addListEntry(anObject,level,true);
                level++;

                for(i=0;i<c;i++) {
                    child = source.getChild(anObject,i);
                    cacheTree(child,level);
                }

                level--;
            }
        }

        void addListEntry(final Object anObject,final int level,final boolean isNode) {
            cache.addElement(new ListEntry(anObject,level,isNode));
        }
    }

    class ListEntry {
        Object object;
        int    level;
        boolean isNode;

        public ListEntry(final Object anObject,final int aLevel,final boolean isNode) {
            object = anObject;
            level = aLevel;
            this.isNode = isNode;
        }

        public Object object() {
            return object;
        }

        public int level() {
            return level;
        }

        public boolean isNode() {
            return isNode;
        }
    }

    static Border emptyBorder = new EmptyBorder(0,0,0,0);

    class ListEntryRenderer extends JLabel implements ListCellRenderer  {
	private static final long serialVersionUID = -3455618828471133284L;

        ImageIcon leafIcon = SwingSet.sharedInstance().loadImageIcon("images/document.gif","Document");
        ImageIcon nodeIcon = SwingSet.sharedInstance().loadImageIcon("images/folder.gif","Folder");

        public ListEntryRenderer() {
            setOpaque(true);
        }

        public Component getListCellRendererComponent(
            final JList listbox, 
	    final Object value, 
	    final int index,
	    final boolean isSelected,
	    final boolean cellHasFocus)
	{
            final ListEntry listEntry = (ListEntry)value;
            if(listEntry != null) {
                Border border;
                setText(listEntry.object().toString());
		setIcon( listEntry.isNode() ? nodeIcon : leafIcon );
                if(index != -1) {
		    border = new EmptyBorder(0, OFFSET * listEntry.level(), 0, 0);
		} else {
		    border = emptyBorder;
		}

                setOpaque(!UIManager.getLookAndFeel().getName().equals("CDE/Motif") || index != -1);
                
		setBorder(border); 
                if (isSelected) {
                    setBackground(UIManager.getColor("ComboBox.selectionBackground"));
                    setForeground(UIManager.getColor("ComboBox.selectionForeground"));
                } else {
                    setBackground(UIManager.getColor("ComboBox.background"));
                    setForeground(UIManager.getColor("ComboBox.foreground"));
                }
            } else {
                setText("");
            }
	    return this;
	}
    }
}







