/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stmikwp.tokobuku.controller;

import com.stmikwp.tokobuku.daoimpl.KategoriDaoImpl;
import com.stmikwp.tokobuku.model.Kategori;
import com.stmikwp.tokobuku.view.KategoriView;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author andi
 */
public class KategoriController {
    
    KategoriView view;
    List<Kategori>listKategori;
    Kategori kategori;
    KategoriDaoImpl kategoriDaoImpl;
    
    
    public KategoriController(KategoriView view) {
        this.view = view;
        kategori = new Kategori();
        kategoriDaoImpl = new KategoriDaoImpl();
        listKategori = kategoriDaoImpl.getAll();
    }
    
    public void kondisiAwal(){
        view.getTxtIdKategori().setEnabled(false);
        view.getTxtNamaKategori().setEnabled(false);
        view.getBtnHapus().setEnabled(false);
        view.getBtnSimpan().setEnabled(false);
        view.getBtnUbah().setEnabled(false);
        view.getBtnTambah().setEnabled(true);
        view.getBtnBatal().setEnabled(false);
    }
    
    public void kondisiTambah(){
        view.getTxtIdKategori().setEnabled(true);
        view.getTxtNamaKategori().setEnabled(true);
        view.getBtnHapus().setEnabled(false);
        view.getBtnSimpan().setEnabled(true);
        view.getBtnUbah().setEnabled(false);
        view.getBtnTambah().setEnabled(false);
        view.getBtnBatal().setEnabled(true);
    }
    
    public void kondisiUbahDanHapus(){
        view.getTxtIdKategori().setEnabled(false);
        view.getTxtNamaKategori().setEnabled(true);
        view.getBtnHapus().setEnabled(true);
        view.getBtnSimpan().setEnabled(false);
        view.getBtnUbah().setEnabled(true);
        view.getBtnTambah().setEnabled(false);
        view.getBtnBatal().setEnabled(true);
    }
    
    public void resetField(){
        view.getTxtIdKategori().setText("");
        view.getTxtNamaKategori().setText("");
    }
    
    public void fillTable(){
        listKategori.clear();
        listKategori = kategoriDaoImpl.getAll();
        KategoriTableModel tableModel = new KategoriTableModel(listKategori);
        view.getTabelKategori().setModel(tableModel);
    }
    
    public void fillField(int row){
        view.getTxtIdKategori().setText(listKategori.get(row).getIdKategori());
        view.getTxtNamaKategori().setText(listKategori.get(row).getNamaKategori());
        view.getBtnSimpan().setEnabled(false);
        kondisiUbahDanHapus();
    }
    
    public void insert(){
        
        kategori = new Kategori();
        kategori.setIdKategori(view.getTxtIdKategori().getText());
        kategori.setNamaKategori(view.getTxtNamaKategori().getText());
        kategoriDaoImpl.insert(kategori);
        JOptionPane.showMessageDialog(view,"Data Berhasil Disimpan!");
    }
    
    public void update(){
        view.getTxtIdKategori().setEnabled(false);
        if(view.getTabelKategori().getSelectedRow() != -1){
            kategori = new Kategori();
            kategori.setIdKategori(view.getTxtIdKategori().getText());
            kategori.setNamaKategori(view.getTxtNamaKategori().getText());
            kategoriDaoImpl.update(kategori);
            JOptionPane.showMessageDialog(view,"Data Berhasil Diubah!");
        }else{
            JOptionPane.showMessageDialog(view,"Pilih Terlebih Dahulu Data Yang Akan Diubah!");
        }
        
    }
    
    public void delete(){
        if(view.getTabelKategori().getSelectedRow() != -1){
            kategori = new Kategori();
            kategoriDaoImpl.delete(view.getTxtIdKategori().getText());
            JOptionPane.showMessageDialog(view,"Data Berhasil Dihapus!");
        }else{
            JOptionPane.showMessageDialog(view,"Pilih Dahulu Data Yang Akan Dihapus!");
        }
        
    }
    
    public void fillTableCariNama(){
        listKategori.clear();
        listKategori = kategoriDaoImpl.getByNama(view.getTxtCariNama().getText());
        KategoriTableModel tableModel = new KategoriTableModel(listKategori);
        view.getTabelKategori().setModel(tableModel);
    }
    
    public void cariNama(){
        if(view.getTxtCariNama().getText().isEmpty()){
            fillTable();
        }else{
            fillTableCariNama();
        }
    }
    
    class KategoriTableModel extends AbstractTableModel{
        
        List<Kategori> lk;
        
        private final String[] HEADER = {"ID Kategori","Nama Kategori"};

        public KategoriTableModel(List<Kategori> lk) {
            this.lk = lk;
        }
        
        @Override
        public int getRowCount() {
            return lk.size();
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
                default:return null;
            }
        }
        
        
        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            switch(columnIndex){
                case 0 : return lk.get(rowIndex).getIdKategori();
                case 1 : return lk.get(rowIndex).getNamaKategori();
                default:return null;
            }
        }
    }
    
}
