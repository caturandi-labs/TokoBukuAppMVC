/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stmikwp.tokobuku.controller;

import com.stmikwp.tokobuku.daoimpl.BukuDaoImpl;
import com.stmikwp.tokobuku.daoimpl.KategoriDaoImpl;
import com.stmikwp.tokobuku.model.Buku;
import com.stmikwp.tokobuku.model.Kategori;
import com.stmikwp.tokobuku.model.Penerbit;
import com.stmikwp.tokobuku.model.Penulis;
import com.stmikwp.tokobuku.view.InputBukuView;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author andi
 */
public class BukuController {
    
    InputBukuView view;
    List<Kategori>listKategori;
    List<Buku>listBuku;
    List<Penulis>listPenulis;
    List<Penerbit>listPenerbit;
    Kategori kategori;
    Penulis penulis;
    Buku buku;
    Penerbit penerbit;
    BukuDaoImpl bukuDaoImpl;
    KategoriDaoImpl kategoriDaoImpl;

    public BukuController(InputBukuView view) {
        this.view = view;
        listKategori = new ArrayList<>();
        listBuku = new ArrayList<>();
        listPenerbit = new  ArrayList<>();
        listPenulis = new ArrayList<>();
        kategori = new Kategori();
        penerbit = new Penerbit();
        penulis = new Penulis();
        buku = new Buku();
        bukuDaoImpl = new BukuDaoImpl();
    }
    
    public void kondisiAwal(){
        view.getTxtNoIsbn().setEnabled(false);
        view.getTxtJudulBuku().setEnabled(false);
        view.getTxtTahunTerbit().setEnabled(false);
        view.getTxtHarga().setEnabled(false);
        view.getTxtStok().setEnabled(false);
        view.getBtnHapus().setEnabled(false);
        view.getBtnSimpan().setEnabled(false);
        view.getBtnUbah().setEnabled(false);
        view.getBtnTambah().setEnabled(true);
        view.getBtnBatal().setEnabled(false);
    }
    
    public void kondisiTambah(){
        view.getTxtNoIsbn().setEnabled(true);
        view.getTxtJudulBuku().setEnabled(true);
        view.getTxtTahunTerbit().setEnabled(true);
        view.getTxtHarga().setEnabled(true);
        view.getTxtStok().setEnabled(true);
        view.getBtnHapus().setEnabled(false);
        view.getBtnSimpan().setEnabled(true);
        view.getBtnUbah().setEnabled(false);
        view.getBtnTambah().setEnabled(false);
        view.getBtnBatal().setEnabled(true);
    }
    
    public void kondisiUbahDanHapus(){
        view.getTxtNoIsbn().setEnabled(false);
        view.getTxtJudulBuku().setEnabled(true);
        view.getTxtTahunTerbit().setEnabled(true);
        view.getTxtHarga().setEnabled(true);
        view.getTxtStok().setEnabled(true);
        view.getBtnHapus().setEnabled(true);
        view.getBtnSimpan().setEnabled(false);
        view.getBtnUbah().setEnabled(true);
        view.getBtnTambah().setEnabled(false);
        view.getBtnBatal().setEnabled(true);
    }
    
    public void resetField(){
        view.getTxtNoIsbn().setText("");
        view.getTxtJudulBuku().setText("");
        view.getTxtTahunTerbit().setText("");
        view.getTxtHarga().setText("");
        view.getTxtStok().setText("");
    }
    
    public void tampilkanKategori(){
        
        view.getComboKategori().removeAllItems();
        List<Kategori>listKat = bukuDaoImpl.getAllKategori();
        
        for (Kategori kategori1 : listKat) {
            view.getComboKategori().addItem(kategori1.getNamaKategori());
        }
        
    }
    
    public void tampilkanPenulis(){
        listPenulis = bukuDaoImpl.getAllPenulis();
        view.getComboPenulis().removeAllItems();
        
        for(Penulis penulis1 : listPenulis){
            view.getComboPenulis().addItem(penulis1.getNamaPenulis());
        }
    }
    
    public void tampilkanPenerbit(){
        listPenerbit = bukuDaoImpl.getAllPenerbit();
        view.getComboPenerbit().removeAllItems();
        for(Penerbit penerbit1 : listPenerbit){
            view.getComboPenerbit().addItem(penerbit1.getNamaPenerbit());
        }
    }
    
    public boolean validasi(){
        if(view.getTxtNoIsbn().getText().isEmpty()){
            JOptionPane.showMessageDialog(view,"No ISBN Penulis masih kosong!");
            view.getTxtNoIsbn().requestFocusInWindow();
            return false; 
        }else if(view.getTxtJudulBuku().getText().isEmpty()){
            JOptionPane.showMessageDialog(view, "Judul Buku Masih kosong!");
            view.getTxtJudulBuku().requestFocusInWindow();
            return false;
        }else if(view.getTxtTahunTerbit().getText().isEmpty()){
            JOptionPane.showMessageDialog(view, "Tahun Terbit Masih kosong!");
            view.getTxtTahunTerbit().requestFocusInWindow();
            return false;
        }else if(view.getTxtHarga().getText().isEmpty()){
            JOptionPane.showMessageDialog(view, "Harga Masih kosong!");
            view.getTxtHarga().requestFocusInWindow();
            return false;
        }else if(view.getTxtStok().getText().isEmpty()){
            JOptionPane.showMessageDialog(view, "Stok Masih kosong!");
            view.getTxtStok().requestFocusInWindow();
            return false;
        }else if(view.getComboKategori().getSelectedItem().toString().isEmpty()){
            JOptionPane.showMessageDialog(view, "Kategori belum dipilih !");
            view.getComboKategori().requestFocusInWindow();
            return false;
        }else if(view.getComboPenerbit().getSelectedItem().toString().isEmpty()){
            JOptionPane.showMessageDialog(view,"Penerbit Belum dipilih !");
            view.getComboPenerbit().requestFocusInWindow();
            return false;
        }else if(view.getComboPenulis().getSelectedItem().toString().isEmpty()){
            JOptionPane.showConfirmDialog(view,"Penulis Belum Dipilih !");
            view.getComboPenulis().requestFocusInWindow();
            return false;
        }else{
            return true;
        }
        
    }
    
    public void insert(){
        
        if(validasi()){
            
            buku = new Buku();
            buku.setIsbn(view.getTxtNoIsbn().getText());
            buku.setJudul(view.getTxtJudulBuku().getText());
            buku.setTahunTerbit(view.getTxtTahunTerbit().getText());
            int harga = Integer.parseInt(view.getTxtHarga().getText());
            buku.setHarga(harga);
            int stok = Integer.parseInt(view.getTxtStok().getText());
            buku.setStok(stok);
            
            String kat = view.getComboKategori().getSelectedItem().toString();
            String penu = view.getComboPenulis().getSelectedItem().toString();
            String pener = view.getComboPenulis().getSelectedItem().toString();
            
            kategori = new Kategori();
            kategori.setIdKategori(kat);
            buku.setKategori(kategori);
            
            penulis = new Penulis();
            penulis.setIdPenulis(penu);
            buku.setPenulis(penulis);
            
            penerbit = new Penerbit();
            penerbit.setIdPenerbit(pener);
            buku.setPenerbit(penerbit);
            
            bukuDaoImpl.insert(buku);
            
            JOptionPane.showMessageDialog(view,"Data Berhasil Disimpan!");
        }
        
    }
    
    public void update(){
        if(validasi()){
            buku = new Buku();
            buku.setIsbn(view.getTxtNoIsbn().getText());
            buku.setJudul(view.getTxtJudulBuku().getText());
            buku.setTahunTerbit(view.getTxtTahunTerbit().getText());
            int harga = Integer.parseInt(view.getTxtHarga().getText());
            buku.setHarga(harga);
            int stok = Integer.parseInt(view.getTxtStok().getText());
            buku.setStok(stok);
            
            String kat = view.getComboKategori().getSelectedItem().toString();
            String penu = view.getComboPenulis().getSelectedItem().toString();
            String pener = view.getComboPenulis().getSelectedItem().toString();
            
            kategori = new Kategori();
            kategori.setIdKategori(kat);
            buku.setKategori(kategori);
            
            penulis = new Penulis();
            penulis.setIdPenulis(penu);
            buku.setPenulis(penulis);
            
            penerbit = new Penerbit();
            penerbit.setIdPenerbit(pener);
            buku.setPenerbit(penerbit);
            
            bukuDaoImpl.update(buku);
            
            JOptionPane.showMessageDialog(view,"Data Berhasil Disimpan!");
        }
    }
    
    public void delete(){
        if(validasi()){
            if(view.getTabelBuku().getSelectedRow() != -1){
                buku = new Buku();
                bukuDaoImpl.delete(view.getTxtNoIsbn().getText());
                JOptionPane.showMessageDialog(view,"Data Berhasil Dihapus!");
            }else{
                JOptionPane.showMessageDialog(view,"Pilih Dahulu Data Yang Akan Dihapus!");
            }
        }
    }
    
    public void fillTable(){
        listBuku.clear();
        listBuku = bukuDaoImpl.getAll();
        BukuTableModel tableModel = new BukuTableModel(listBuku);
        view.getTabelBuku().setModel(tableModel);
    }
    
    public void fillField(int row){
        
        buku = listBuku.get(row);
        
        view.getTxtNoIsbn().setText(buku.getIsbn());
        view.getTxtJudulBuku().setText(buku.getJudul());
        view.getTxtTahunTerbit().setText(buku.getTahunTerbit());
        view.getTxtHarga().setText(String.valueOf(buku.getHarga()));
        view.getTxtStok().setText(String.valueOf(buku.getStok()));
        view.getComboKategori().setSelectedItem(buku.getKategori().getIdKategori());
        view.getComboPenerbit().setSelectedItem(buku.getPenerbit().getIdPenerbit());
        view.getComboPenulis().setSelectedItem(buku.getPenulis().getIdPenulis());
        view.getBtnSimpan().setEnabled(false);
        kondisiUbahDanHapus();
    }
    
    class BukuTableModel extends AbstractTableModel{
       
        private List<Buku> lp;
        private final String[]HEADER = {"ISBN","Judul","Tahun Terbit","Harga","Stok","Kategori","Nama Penulis","Nama Penerbit"};
        
        public BukuTableModel(List<Buku> lp) {
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
                case 6 : return HEADER[6];
                case 7 : return HEADER[7];
                default:return null;
            }
        }
        
        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Buku buku = lp.get(rowIndex);
            switch(columnIndex){
                case 0 : return buku.getIsbn();
                case 1 : return buku.getJudul();
                case 2 : return buku.getTahunTerbit();
                case 3 : return buku.getHarga();
                case 4 : return buku.getStok();
                case 5 : return buku.getKategori().getNamaKategori();
                case 6 : return buku.getPenulis().getNamaPenulis();
                case 7 : return buku.getPenerbit().getNamaPenerbit();
                default:return null;
            }
        }
        
    }
    
}
