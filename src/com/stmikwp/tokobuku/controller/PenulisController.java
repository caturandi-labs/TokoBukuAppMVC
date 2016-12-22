/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stmikwp.tokobuku.controller;

import com.stmikwp.tokobuku.daoimpl.PenulisDaoImpl;
import com.stmikwp.tokobuku.model.Penulis;
import com.stmikwp.tokobuku.view.PenulisView;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author andi
 */
public class PenulisController {
    
    PenulisView view;
    List<Penulis>listPenulis;
    Penulis penulis;
    PenulisDaoImpl penulisDaoImpl;

    public PenulisController(PenulisView view) {
        this.view = view;
        listPenulis = new ArrayList<>();
        penulisDaoImpl = new PenulisDaoImpl();
    }
    
    public void kondisiAwal(){
        view.getTxtAlamat().setEnabled(false);
        view.getTxtIdPenulis().setEnabled(false);
        view.getTxtKodePos().setEnabled(false);
        view.getTxtKota().setEnabled(false);
        view.getTxtNamaPenulis().setEnabled(false);
        view.getTxtTelepon().setEnabled(false);
        view.getBtnHapus().setEnabled(false);
        view.getBtnSimpan().setEnabled(false);
        view.getBtnUbah().setEnabled(false);
        view.getBtnTambah().setEnabled(true);
        view.getBtnBatal().setEnabled(false);
    }
    
    public void kondisiTambah(){
        view.getTxtAlamat().setEnabled(true);
        view.getTxtIdPenulis().setEnabled(true);
        view.getTxtKodePos().setEnabled(true);
        view.getTxtKota().setEnabled(true);
        view.getTxtNamaPenulis().setEnabled(true);
        view.getTxtTelepon().setEnabled(true);
        
        view.getBtnHapus().setEnabled(false);
        view.getBtnSimpan().setEnabled(true);
        view.getBtnUbah().setEnabled(false);
        view.getBtnTambah().setEnabled(false);
        view.getBtnBatal().setEnabled(true);
    }
    
    public void kondisiUbahDanHapus(){
        view.getTxtIdPenulis().setEnabled(false);
        view.getTxtAlamat().setEnabled(true);
        view.getTxtKodePos().setEnabled(true);
        view.getTxtKota().setEnabled(true);
        view.getTxtNamaPenulis().setEnabled(true);
        view.getTxtTelepon().setEnabled(true);
        view.getBtnHapus().setEnabled(true);
        view.getBtnSimpan().setEnabled(false);
        view.getBtnUbah().setEnabled(true);
        view.getBtnTambah().setEnabled(false);
        view.getBtnBatal().setEnabled(true);
    }
    
    public void resetField(){
        view.getTxtAlamat().setText("");
        view.getTxtIdPenulis().setText("");
        view.getTxtKodePos().setText("");
        view.getTxtKota().setText("");
        view.getTxtNamaPenulis().setText("");
        view.getTxtTelepon().setText("");
    }
    
    public boolean validasi(){
        if(view.getTxtIdPenulis().getText().isEmpty()){
            JOptionPane.showMessageDialog(view,"Data ID Penulis masih kosong!");
            view.getTxtIdPenulis().requestFocusInWindow();
            return false; 
        }else if(view.getTxtNamaPenulis().getText().isEmpty()){
            JOptionPane.showMessageDialog(view, "Nama Penulis Masih kosong!");
            view.getTxtNamaPenulis().requestFocusInWindow();
            return false;
        }else if(view.getTxtAlamat().getText().isEmpty()){
            JOptionPane.showMessageDialog(view, "Alamat Masih kosong!");
            view.getTxtAlamat().requestFocusInWindow();
            return false;
        }else if(view.getTxtKota().getText().isEmpty()){
            JOptionPane.showMessageDialog(view, "Kota Masih kosong!");
            view.getTxtKota().requestFocusInWindow();
            return false;
        }else if(view.getTxtKodePos().getText().isEmpty()){
            JOptionPane.showMessageDialog(view, "Kodepos Masih kosong!");
            view.getTxtKodePos().requestFocusInWindow();
            return false;
        }else if(view.getTxtTelepon().getText().isEmpty()){
            JOptionPane.showMessageDialog(view, "No telepon Masih kosong!");
            view.getTxtTelepon().requestFocusInWindow();
            return false;
        }else{
            return true;
        }
        
    }
    
    public void insert(){
        
        if(validasi()){
            penulis = new Penulis();
            penulis.setIdPenulis(view.getTxtIdPenulis().getText());
            penulis.setNamaPenulis(view.getTxtNamaPenulis().getText());
            penulis.setAlamatJalan(view.getTxtAlamat().getText());
            penulis.setKota(view.getTxtKota().getText());
            penulis.setKodePos(view.getTxtKodePos().getText());
            penulis.setTelepon(view.getTxtTelepon().getText());
            penulisDaoImpl.insert(penulis);
            JOptionPane.showMessageDialog(view,"Data Berhasil Disimpan!");
        }
        
    }
    
    public void update(){
        
        if(validasi()){
            penulis = new Penulis();
            penulis.setIdPenulis(view.getTxtIdPenulis().getText());
            penulis.setNamaPenulis(view.getTxtNamaPenulis().getText());
            penulis.setAlamatJalan(view.getTxtAlamat().getText());
            penulis.setKota(view.getTxtKota().getText());
            penulis.setTelepon(view.getTxtTelepon().getText());
            penulisDaoImpl.update(penulis);
            JOptionPane.showMessageDialog(view,"Data Berhasil Diubah!");
        }
    }
    
    public void delete(){
        if(view.getTabelPenulis().getSelectedRow() != -1){
            penulis = new Penulis();
            penulisDaoImpl.delete(view.getTxtIdPenulis().getText());
            JOptionPane.showMessageDialog(view,"Data Berhasil Dihapus!");
        }else{
            JOptionPane.showMessageDialog(view,"Pilih Dahulu Data Yang Akan Dihapus!");
        }
        
    }
    
    public void fillTable(){
        listPenulis.clear();
        listPenulis = penulisDaoImpl.getAll();
        PenulisTableModel tableModel = new PenulisTableModel(listPenulis);
        view.getTabelPenulis().setModel(tableModel);
    }
    
    public void fillField(int row){
        view.getTxtIdPenulis().setText(listPenulis.get(row).getIdPenulis());
        view.getTxtNamaPenulis().setText(listPenulis.get(row).getNamaPenulis());
        view.getTxtAlamat().setText(listPenulis.get(row).getAlamatJalan());
        view.getTxtKota().setText(listPenulis.get(row).getKota());
        view.getTxtKodePos().setText(listPenulis.get(row).getKodePos());
        view.getTxtTelepon().setText(listPenulis.get(row).getTelepon());
        view.getBtnSimpan().setEnabled(false);
        kondisiUbahDanHapus();
    }
    
    public void fillTableCariNama(){
        listPenulis.clear();
        listPenulis = penulisDaoImpl.getByNama(view.getTxtCariNama().getText());
        PenulisTableModel tableModel = new PenulisTableModel(listPenulis);
        view.getTabelPenulis().setModel(tableModel);
    }
    
    public void cariNama(){
        if(view.getTxtCariNama().getText().isEmpty()){
            fillTable();
        }else{
            fillTableCariNama();
        }
    }
    
    class PenulisTableModel extends AbstractTableModel{
       
        private List<Penulis> lp;
        private final String[]HEADER = {"ID Penulis","Nama Penulis","Alamat","Kota","Kodepos","Telepon"};
        
        public PenulisTableModel(List<Penulis> lp) {
            this.lp = lp;
        }
        
        @Override
        public int getRowCount() {
            return lp.size();
        }

        @Override
        public int getColumnCount() {
            return HEADER.length;
        }
        
        @Override
        public String getColumnName(int column) {
            switch(column){
                case 0 : return HEADER[0];
                case 1 : return HEADER[1];
                case 2 : return HEADER[2];
                case 3 : return HEADER[3];
                case 4 : return HEADER[4];
                case 5 : return HEADER[5];
                default:return null;
            }
        }
        
        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            switch(columnIndex){
                case 0 : return lp.get(rowIndex).getIdPenulis();
                case 1 : return lp.get(rowIndex).getNamaPenulis();
                case 2 : return lp.get(rowIndex).getAlamatJalan();
                case 3 : return lp.get(rowIndex).getKota();
                case 4 : return lp.get(rowIndex).getKodePos();
                case 5 : return lp.get(rowIndex).getTelepon();
                default:return null;
            }
        }
        
    }
    
}
