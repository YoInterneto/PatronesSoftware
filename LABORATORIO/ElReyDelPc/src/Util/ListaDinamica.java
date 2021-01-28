/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.awt.Component;
import java.awt.Cursor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Alberto
 */
public class ListaDinamica extends DefaultListCellRenderer {
    
    private Border noFocusBorder;
    private TitledBorder focusBorder;
    private DefaultListCellRenderer defaultRenderer;
    
    public ListaDinamica(String titulo){
        this.noFocusBorder = new EmptyBorder(15, 1, 1, 1);
        this.focusBorder = new TitledBorder(LineBorder.createGrayLineBorder(), titulo);
        this.defaultRenderer = new DefaultListCellRenderer();
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel renderer = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        renderer.setBorder(cellHasFocus ? focusBorder : noFocusBorder);
        
        return renderer;
    }
}
