/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stmikwp.tokobuku.main;

import com.jtattoo.plaf.JTattooUtilities;
import com.stmikwp.tokobuku.dao.KategoriDao;
import com.stmikwp.tokobuku.daoimpl.KategoriDaoImpl;
import com.stmikwp.tokobuku.model.Kategori;
import com.stmikwp.tokobuku.view.MenuUtamaView;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author andi
 */
public class Main {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new com.jtattoo.plaf.graphite.GraphiteLookAndFeel());
            SwingUtilities.updateComponentTreeUI(new MenuUtamaView());
        } catch (Exception e){
            e.printStackTrace();
        } 
        new MenuUtamaView().setVisible(true);
    }
    
}
