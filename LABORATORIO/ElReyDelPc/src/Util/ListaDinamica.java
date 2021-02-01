
package Util;

import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 * Renderiza un componente jList de forma dinámica.
 * Cada fila de la lista es un jLabel con un texto dado.
 * 
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
