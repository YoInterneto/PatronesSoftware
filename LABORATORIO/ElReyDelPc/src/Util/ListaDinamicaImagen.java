/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.awt.Component;
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
public class ListaDinamicaImagen extends DefaultListCellRenderer {
    Border noFocusBorder;
    TitledBorder focusBorder;
    DefaultListCellRenderer defaultRenderer;
    
    Map<String, ImageIcon> imageMap;
    
    public ListaDinamicaImagen(ArrayList<String> informacion, ArrayList<String> rutaImagen, String titulo){
        this.imageMap = crearImageMap(informacion, rutaImagen);
        this.noFocusBorder = new EmptyBorder(15, 1, 1, 1);
        this.focusBorder = new TitledBorder(LineBorder.createGrayLineBorder(), titulo);
        this.defaultRenderer = new DefaultListCellRenderer();
    }
    
    public Map<String, ImageIcon> crearImageMap(ArrayList<String> informacion, ArrayList<String> rutaImagen) {
        Map<String, ImageIcon> map = new HashMap<>();
        
        for (int i=0; i<informacion.size(); i++) {
            java.net.URL imgURL = getClass().getResource(rutaImagen.get(i));
            if(imgURL == null){
                map.put(informacion.get(i), new ImageIcon(
                    getClass().getResource("/images/fa.png"))); //PONER IMAGEN POR DEFECTO
            }
            else{
                map.put(informacion.get(i), new ImageIcon(
                    getClass().getResource(rutaImagen.get(i))));
            }
        }
        
        return map;
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel renderer = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        renderer.setIcon(imageMap.get((String)value));
        renderer.setBorder(cellHasFocus ? focusBorder : noFocusBorder);
        return renderer;
    }
}
