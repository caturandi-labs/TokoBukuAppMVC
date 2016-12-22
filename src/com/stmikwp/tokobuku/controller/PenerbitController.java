/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stmikwp.tokobuku.controller;

import com.stmikwp.tokobuku.daoimpl.PenerbitDaoImpl;
import com.stmikwp.tokobuku.model.Penerbit;
import com.stmikwp.tokobuku.view.PenerbitView;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author andi
 */
public class PenerbitController {
    PenerbitView view;
    List<Penerbit>listPenerbit;
    Penerbit penerbit;
    PenerbitDaoImpl penerbitDaoImpl;

    public PenerbitController(PenerbitView view) {
        this.view = view;
        listPenerbit = new ArrayList<>();
        penerbit = new Penerbit();
        penerbitDaoImpl = new PenerbitDaoImpl();
    }
    
    public void kondisiAwal(){
        view.getTxtAlamat().setEnabled(false);
        view.getTxtIdPenerbit().setEnabled(false);
        view.getTxtKodePos().setEnabled(false);
        view.getTxtKota().setEnabled(false);
        view.getTxtNamaPenerbit().setEnabled(false);
        view.getTxtTelepon().setEnabled(false);
        view.getBtnHapus().setEnabled(false);
        view.getBtnSimpan().setEnabled(false);
        view.getBtnUbah().setEnabled(false);
        view.getBtnTambah().setEnabled(true);
        view.getBtnBatal().setEnabled(false);
    }
    
    public void kondisiTambah(){
        view.getTxtAlamat().setEnabled(true);
        view.getTxtIdPenerbit().setEnabled(true);
        view.getTxtKodePos().setEnabled(true);
        view.getTxtKota().setEnabled(true);
        view.getTxtNamaPenerbit().setEnabled(true);
        view.getTxtTelepon().setEnabled(true);
        
        view.getBtnHapus().setEnabled(false);
        view.getBtnSimpan().setEnabled(true);
        view.getBtnUbah().setEnabled(false);
        view.getBtnTambah().setEnabled(false);
        view.getBtnBatal().setEnabled(true);
    }
    
    public void kondisiUbahDanHapus(){
        view.getTxtIdPenerbit().setEnabled(false);
        view.getTxtAlamat().setEnabled(true);
        view.getTxtKodePos().setEnabled(true);
        view.getTxtKota().setEnabled(true);
        view.getTxtNamaPenerbit().setEnabled(true);
        view.getTxtTelepon().setEnabled(true);
        view.getBtnHapus().setEnabled(true);
        view.getBtnSimpan().setEnabled(false);
        view.getBtnUbah().setEnabled(true);
        view.getBtnTambah().setEnabled(false);
        view.getBtnBatal().setEnabled(true);
    }
    
    public void resetField(){
        view.getTxtAlamat().setText("");
        view.getTxtIdPenerbit().setText("");
        view.getTxtKodePos().setText("");
        view.getTxtKota().setText("");
        view.getTxtNamaPenerbit().setText("");
        view.getTxtTelepon().setText("");
    }
    
    public boolean validasi(){
        if(view.getTxtIdPenerbit().getText().isEmpty()){
            JOptionPane.showMessageDialog(view,"Data ID Penulis masih kosong!");
            return false; 
        }else if(view.getTxtNamaPenerbit().getText().isEmpty()){
            JOptionPane.showMessageDialog(view, "Nama Penulis Masih kosong!");
            return false;
        }else if(view.getTxtAlamat().getText().isEmpty()){
            JOptionPane.showMessageDialog(view, "Alamat Masih kosong!");
            return false;
        }else if(view.getTxtKota().getText().isEmpty()){
            JOptionPane.showMessageDialog(view, "Kota Masih kosong!");
            return false;
        }else if(view.getTxtKodePos().getText().isEmpty()){
            JOptionPane.showMessageDialog(view, "Kodepos Masih kosong!");
            return false;
        }else if(view.getTxtTelepon().getText().isEmpty()){
            JOptionPane.showMessageDialog(view, "No telepon Masih kosong!");
            return false;
        }else{
            return true;
        }
        
    }
    
    
    public void insert(){
        
        if(validasi()){
            penerbit = new Penerbit();
            penerbit.setIdPenerbit(view.getTxtIdPenerbit().getText());
            penerbit.setNamaPenerbit(view.getTxtNamaPenerbit().getText());
            penerbit.setAlamatJalan(view.getTxtAlamat().getText());
            penerbit.setKota(view.getTxtKota().getText());
            penerbit.setKodePos(view.getTxtKodePos().getText());
            penerbit.setTelepon(view.getTxtTelepon().getText());
            penerbitDaoImpl.insert(penerbit);
            JOptionPane.showMessageDialog(view,"Data Berhasil Disimpan!");
        }
        
    }
    
    public void update(){
        if(validasi()){
            penerbit = new Penerbit();
            penerbit.setIdPenerbit(view.getTxtIdPenerbit().getText());
            penerbit.setNamaPenerbit(view.getTxtNamaPenerbit().getText());
            penerbit.setAlamatJalan(view.getTxtAlamat().getText());
            penerbit.setKota(view.getTxtKota().getText());
            penerbit.setTelepon(view.getTxtTelepon().getText());
            penerbitDaoImpl.update(penerbit);
            JOptionPane.showMessageDialog(view,"Data Berhasil Diubah!");
        }
    }
    
    public void delete(){
        if(view.getTabelPenerbit().getSelectedRow() != -1){
            penerbit = new Penerbit();
            penerbitDaoImpl.delete(view.getTxtIdPenerbit().getText());
            JOptionPane.showMessageDialog(view,"Data Berhasil Dihapus!");
        }else{
            JOptionPane.showMessageDialog(view,"Pilih Dahulu Data Yang Akan Dihapus!");
        }
        
    }
    
    public void fillTable(){
        listPenerbit.clear();
        listPenerbit = penerbitDaoImpl.getAll();
        PenerbitTableModel tableModel = new PenerbitTableModel(listPenerbit);
        view.getTabelPenerbit().setModel(tableModel);
    }
    
    public void fillField(int row){
        view.getTxtIdPenerbit().setText(listPenerbit.get(row).getIdPenerbit());
        view.getTxtNamaPenerbit().setText(listPenerbit.get(row).getNamaPenerbit());
        view.getTxtAlamat().setText(listPenerbit.get(row).getAlamatJalan());
        view.getTxtKota().setText(listPenerbit.get(row).getKota());
        view.getTxtKodePos().setText(listPenerbit.get(row).getKodePos());
        view.getTxtTelepon().setText(listPenerbit.get(row).getTelepon());
        view.getBtnSimpan().setEnabled(false);
        kondisiUbahDanHapus();
    }
    
    public void fillTableCariNama(){
        listPenerbit.clear();
        listPenerbit = penerbitDaoImpl.getByNama(view.getTxtCariNama().getText());
        PenerbitTableModel tableModel = new PenerbitTableModel(listPenerbit);
        view.getTabelPenerbit().setModel(tableModel);
    }
    
    public void cariNama(){
        if(view.getTxtCariNama().getText().isEmpty()){
            fillTable();
        }else{
            fillTableCariNama();
        }
    }
    
    class PenerbitTableModel extends AbstractTableModel{
       
        private List<Penerbit> lp;
        private final String[]HEADER = {"ID Penulis","Nama Penulis","Alamat","Kota","Kodepos","Telepon"};

        public PenerbitTableModel(List<Penerbit> lp) {
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
                case 0 : return lp.get(rowIndex).getIdPenerbit();
                case 1 : return lp.get(rowIndex).getNamaPenerbit();
                case 2 : return lp.get(rowIndex).getAlamatJalan();
                case 3 : return lp.get(rowIndex).getKota();
                case 4 : return lp.get(rowIndex).getKodePos();
                case 5 : return lp.get(rowIndex).getTelepon();
                default:return null;
            }
        }
        
    }
    
}
