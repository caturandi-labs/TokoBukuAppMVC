/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stmikwp.tokobuku.controller;


import com.stmikwp.tokobuku.daoimpl.PelangganDaoImpl;
import com.stmikwp.tokobuku.model.Pelanggan;
import com.stmikwp.tokobuku.view.PelangganView;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author andi
 */
public class PelangganController {
    PelangganView view;
    List<Pelanggan> listPelanggan;
    Pelanggan pelanggan;
    PelangganDaoImpl pelangganDaoImpl;

    public PelangganController(PelangganView view) {
        this.view = view;
        listPelanggan = new ArrayList<>();
        pelanggan = new Pelanggan();
        pelangganDaoImpl = new PelangganDaoImpl();
    }
    
    public void kondisiAwal(){
        view.getTxtAlamat().setEnabled(false);
        view.getTxtIdPelanggan().setEnabled(false);
        view.getTxtKodePos().setEnabled(false);
        view.getTxtKota().setEnabled(false);
        view.getTxtNamaPelanggan().setEnabled(false);
        view.getTxtTelepon().setEnabled(false);
        view.getBtnHapus().setEnabled(false);
        view.getBtnSimpan().setEnabled(false);
        view.getBtnUbah().setEnabled(false);
        view.getBtnTambah().setEnabled(true);
        view.getBtnBatal().setEnabled(false);
    }
    
    public void kondisiTambah(){
        view.getTxtAlamat().setEnabled(true);
        view.getTxtIdPelanggan().setEnabled(true);
        view.getTxtKodePos().setEnabled(true);
        view.getTxtKota().setEnabled(true);
        view.getTxtNamaPelanggan().setEnabled(true);
        view.getTxtTelepon().setEnabled(true);
        view.getBtnHapus().setEnabled(false);
        view.getBtnSimpan().setEnabled(true);
        view.getBtnUbah().setEnabled(false);
        view.getBtnTambah().setEnabled(false);
        view.getBtnBatal().setEnabled(true);
    }
    
    public void kondisiUbahDanHapus(){
        view.getTxtIdPelanggan().setEnabled(false);
        view.getTxtAlamat().setEnabled(true);
        view.getTxtKodePos().setEnabled(true);
        view.getTxtKota().setEnabled(true);
        view.getTxtNamaPelanggan().setEnabled(true);
        view.getTxtTelepon().setEnabled(true);
        view.getBtnHapus().setEnabled(true);
        view.getBtnSimpan().setEnabled(false);
        view.getBtnUbah().setEnabled(true);
        view.getBtnTambah().setEnabled(false);
        view.getBtnBatal().setEnabled(true);
    }
    
    public void resetField(){
        view.getTxtAlamat().setText("");
        view.getTxtIdPelanggan().setText("");
        view.getTxtKodePos().setText("");
        view.getTxtKota().setText("");
        view.getTxtNamaPelanggan().setText("");
        view.getTxtTelepon().setText("");
    }
    
    public boolean validasi(){
        if(view.getTxtIdPelanggan().getText().isEmpty()){
            JOptionPane.showMessageDialog(view,"Data ID Penulis masih kosong!");
            return false; 
        }else if(view.getTxtNamaPelanggan().getText().isEmpty()){
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
            pelanggan = new Pelanggan();
            pelanggan.setIdPelanggan(view.getTxtIdPelanggan().getText());
            pelanggan.setNama(view.getTxtNamaPelanggan().getText());
            pelanggan.setAlamatJalan(view.getTxtAlamat().getText());
            pelanggan.setKota(view.getTxtKota().getText());
            pelanggan.setKodePos(view.getTxtKodePos().getText());
            pelanggan.setTelepon(view.getTxtTelepon().getText());
            pelangganDaoImpl.insert(pelanggan);
            JOptionPane.showMessageDialog(view,"Data Berhasil Disimpan!");
        }
        
    }
    
    
    public void update(){
        if(validasi()){
            pelanggan = new Pelanggan();
            pelanggan.setIdPelanggan(view.getTxtIdPelanggan().getText());
            pelanggan.setNama(view.getTxtNamaPelanggan().getText());
            pelanggan.setAlamatJalan(view.getTxtAlamat().getText());
            pelanggan.setKota(view.getTxtKota().getText());
            pelanggan.setKodePos(view.getTxtKodePos().getText());
            pelanggan.setTelepon(view.getTxtTelepon().getText());
            pelangganDaoImpl.update(pelanggan);
            JOptionPane.showMessageDialog(view,"Data Berhasil Disimpan!");
        }
    }
    
    public void delete(){
        if(view.getTabelPelanggan().getSelectedRow() != -1){
            pelanggan = new Pelanggan();
            pelangganDaoImpl.delete(view.getTxtIdPelanggan().getText());
            JOptionPane.showMessageDialog(view,"Data Berhasil Dihapus!");
        }else{
            JOptionPane.showMessageDialog(view,"Pilih Dahulu Data Yang Akan Dihapus!");
        }
        
    }
    
    public void fillTable(){
        listPelanggan.clear();
        listPelanggan = pelangganDaoImpl.getAll();
        PelangganTableModel tableModel = new PelangganTableModel(listPelanggan);
        view.getTabelPelanggan().setModel(tableModel);
    }
    
    public void fillField(int row){
        view.getTxtIdPelanggan().setText(listPelanggan.get(row).getIdPelanggan());
        view.getTxtNamaPelanggan().setText(listPelanggan.get(row).getNama());
        view.getTxtAlamat().setText(listPelanggan.get(row).getAlamatJalan());
        view.getTxtKota().setText(listPelanggan.get(row).getKota());
        view.getTxtKodePos().setText(listPelanggan.get(row).getKodePos());
        view.getTxtTelepon().setText(listPelanggan.get(row).getTelepon());
        view.getBtnSimpan().setEnabled(false);
        kondisiUbahDanHapus();
    }
    
    public void fillTableCariNama(){
        listPelanggan.clear();
        listPelanggan = pelangganDaoImpl.getByNama(view.getTxtCariNama().getText());
        PelangganTableModel tableModel = new PelangganTableModel(listPelanggan);
        view.getTabelPelanggan().setModel(tableModel);
    }
    
    public void cariNama(){
        if(view.getTxtCariNama().getText().isEmpty()){
            fillTable();
        }else{
            fillTableCariNama();
        }
    }
    
    class PelangganTableModel extends AbstractTableModel{

        private List<Pelanggan> lp;
        private final String[]HEADER = {"ID Pelanggan","Nama Pelanggan","Alamat","Kota","Kodepos","Telepon"};

        public PelangganTableModel(List<Pelanggan> lp) {
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
                case 0 : return lp.get(rowIndex).getIdPelanggan();
                case 1 : return lp.get(rowIndex).getNama();
                case 2 : return lp.get(rowIndex).getAlamatJalan();
                case 3 : return lp.get(rowIndex).getKota();
                case 4 : return lp.get(rowIndex).getKodePos();
                case 5 : return lp.get(rowIndex).getTelepon();
                default:return null;
            }
        }

    }   
}
