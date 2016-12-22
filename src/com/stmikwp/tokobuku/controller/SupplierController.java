/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stmikwp.tokobuku.controller;

import com.stmikwp.tokobuku.daoimpl.SupplierDaoImpl;
import com.stmikwp.tokobuku.model.Supplier;
import com.stmikwp.tokobuku.view.SupplierView;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author andi
 */
public class SupplierController {
    
    SupplierView view;
    List<Supplier>listSupplier;
    Supplier supplier;
    SupplierDaoImpl supplierDaoImpl;

    public SupplierController(SupplierView view) {
        this.view = view;
        listSupplier = new ArrayList<>();
        supplier = new Supplier();
        supplierDaoImpl = new SupplierDaoImpl();
    }
    
    public void kondisiAwal(){
        view.getTxtAlamat().setEnabled(false);
        view.getTxtIdSupplier().setEnabled(false);
        view.getTxtKodePos().setEnabled(false);
        view.getTxtKota().setEnabled(false);
        view.getTxtNamaSupplier().setEnabled(false);
        view.getTxtTelepon().setEnabled(false);
        view.getBtnHapus().setEnabled(false);
        view.getBtnSimpan().setEnabled(false);
        view.getBtnUbah().setEnabled(false);
        view.getBtnTambah().setEnabled(true);
        view.getBtnBatal().setEnabled(false);
    }
    
    public void kondisiTambah(){
        view.getTxtAlamat().setEnabled(true);
        view.getTxtIdSupplier().setEnabled(true);
        view.getTxtKodePos().setEnabled(true);
        view.getTxtKota().setEnabled(true);
        view.getTxtNamaSupplier().setEnabled(true);
        view.getTxtTelepon().setEnabled(true);
        
        view.getBtnHapus().setEnabled(false);
        view.getBtnSimpan().setEnabled(true);
        view.getBtnUbah().setEnabled(false);
        view.getBtnTambah().setEnabled(false);
        view.getBtnBatal().setEnabled(true);
    }
    
    public void kondisiUbahDanHapus(){
        view.getTxtIdSupplier().setEnabled(false);
        view.getTxtAlamat().setEnabled(true);
        view.getTxtKodePos().setEnabled(true);
        view.getTxtKota().setEnabled(true);
        view.getTxtNamaSupplier().setEnabled(true);
        view.getTxtTelepon().setEnabled(true);
        view.getBtnHapus().setEnabled(true);
        view.getBtnSimpan().setEnabled(false);
        view.getBtnUbah().setEnabled(true);
        view.getBtnTambah().setEnabled(false);
        view.getBtnBatal().setEnabled(true);
    }
    
    public void resetField(){
        view.getTxtAlamat().setText("");
        view.getTxtIdSupplier().setText("");
        view.getTxtKodePos().setText("");
        view.getTxtKota().setText("");
        view.getTxtNamaSupplier().setText("");
        view.getTxtTelepon().setText("");
    }
    
    public boolean validasi(){
        if(view.getTxtIdSupplier().getText().isEmpty()){
            JOptionPane.showMessageDialog(view,"Data ID Penulis masih kosong!");
            return false; 
        }else if(view.getTxtNamaSupplier().getText().isEmpty()){
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
            supplier = new Supplier();
            supplier.setIdSupplier(view.getTxtIdSupplier().getText());
            supplier.setNamaSupplier(view.getTxtNamaSupplier().getText());
            supplier.setAlamatJalan(view.getTxtAlamat().getText());
            supplier.setKota(view.getTxtKota().getText());
            supplier.setKodePos(view.getTxtKodePos().getText());
            supplier.setTelepon(view.getTxtTelepon().getText());
            supplierDaoImpl.insert(supplier);
            JOptionPane.showMessageDialog(view,"Data Berhasil Disimpan!");
        }
        
    }
    
    public void update(){
        
        if(validasi()){
            supplier = new Supplier();
            supplier.setIdSupplier(view.getTxtIdSupplier().getText());
            supplier.setNamaSupplier(view.getTxtNamaSupplier().getText());
            supplier.setAlamatJalan(view.getTxtAlamat().getText());
            supplier.setKota(view.getTxtKota().getText());
            supplier.setKodePos(view.getTxtKodePos().getText());
            supplier.setTelepon(view.getTxtTelepon().getText());
            supplierDaoImpl.update(supplier);
            JOptionPane.showMessageDialog(view,"Data Berhasil Disimpan!");
        }
    }
    
    public void delete(){
        if(view.getTabelSupplier().getSelectedRow() != -1){
            supplier = new Supplier();
            supplierDaoImpl.delete(view.getTxtIdSupplier().getText());
            JOptionPane.showMessageDialog(view,"Data Berhasil Dihapus!");
        }else{
            JOptionPane.showMessageDialog(view,"Pilih Dahulu Data Yang Akan Dihapus!");
        }
        
    }
    
    public void fillTable(){
        listSupplier.clear();
        listSupplier = supplierDaoImpl.getAll();
        SupplierTableModel tableModel = new SupplierTableModel(listSupplier);
        view.getTabelSupplier().setModel(tableModel);
    }
    
    public void fillField(int row){
        view.getTxtIdSupplier().setText(listSupplier.get(row).getIdSupplier());
        view.getTxtNamaSupplier().setText(listSupplier.get(row).getNamaSupplier());
        view.getTxtAlamat().setText(listSupplier.get(row).getAlamatJalan());
        view.getTxtKota().setText(listSupplier.get(row).getKota());
        view.getTxtKodePos().setText(listSupplier.get(row).getKodePos());
        view.getTxtTelepon().setText(listSupplier.get(row).getTelepon());
        view.getBtnSimpan().setEnabled(false);
        kondisiUbahDanHapus();
    }
    
    public void fillTableCariNama(){
        listSupplier.clear();
        listSupplier = supplierDaoImpl.getByNama(view.getTxtCariNama().getText());
        SupplierTableModel tableModel = new SupplierTableModel(listSupplier);
        view.getTabelSupplier().setModel(tableModel);
    }
    
    public void cariNama(){
        if(view.getTxtCariNama().getText().isEmpty()){
            fillTable();
        }else{
            fillTableCariNama();
        }
    }
    
    
    class SupplierTableModel extends AbstractTableModel{
       
        private List<Supplier> lp;
        private final String[]HEADER = {"ID Supplier","Nama Supplier","Alamat","Kota","Kodepos","Telepon"};
        
        public SupplierTableModel(List<Supplier> lp) {
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
                case 0 : return lp.get(rowIndex).getIdSupplier();
                case 1 : return lp.get(rowIndex).getNamaSupplier();
                case 2 : return lp.get(rowIndex).getAlamatJalan();
                case 3 : return lp.get(rowIndex).getKota();
                case 4 : return lp.get(rowIndex).getKodePos();
                case 5 : return lp.get(rowIndex).getTelepon();
                default:return null;
            }
        }
        
    }
    
}
