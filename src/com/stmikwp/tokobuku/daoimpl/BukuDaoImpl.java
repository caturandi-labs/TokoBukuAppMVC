/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stmikwp.tokobuku.daoimpl;

import com.stmikwp.tokobuku.dao.BukuDao;
import com.stmikwp.tokobuku.database.DatabaseUtil;
import com.stmikwp.tokobuku.model.Buku;
import com.stmikwp.tokobuku.model.Kategori;
import com.stmikwp.tokobuku.model.Penerbit;
import com.stmikwp.tokobuku.model.Penulis;
import com.stmikwp.tokobuku.model.Supplier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author andi
 */
public class BukuDaoImpl implements BukuDao{
    
    private Connection connection;
    private  PreparedStatement statement = null;
    private  ResultSet result = null;
    private  Buku buku = null;
    private  Kategori kategori = null;
    private  Penulis penulis = null;
    private  Penerbit penerbit = null;
    private List<Buku>listBuku = null;
    private List<Penerbit>listPenerbit = null;
    private List<Penulis> listPenulis = null;
    private List<Kategori> listKategori = null;
    
    
    public BukuDaoImpl() {
        this.connection = DatabaseUtil.getConnection();
        listBuku = new ArrayList<>();
        listKategori = new ArrayList<>();
        listPenerbit = new ArrayList<>();
        listPenulis = new ArrayList<>();
    }
    
    // Query Database
    private final String insert = "INSERT INTO buku VALUES(?,?,?,?,?,?,?,?)";
    
    private final String update = "UPDATE buku SET judul=?, tahun_terbit=?,harga=?,stok=?,"
                                    +"id_kategori=?, id_penulis=?,id_penerbit=? WHERE isbn=?";
    
    private final String delete = "DELETE FROM buku WHERE isbn=?";
    
    private final String getAll = "SELECT b.isbn,b.judul, b.tahun_terbit,b.harga,b.stok,k.nama_kategori"
                                    +",p.nama_penerbit,pp.nama_penulis FROM buku AS b , kategori as k, penerbit as p"
                                    +", penulis as pp "
                                    +" WHERE b.id_kategori = k.id_kategori "
                                    +"AND b.id_penerbit = p.id_penerbit AND b.id_penulis = pp.id_penulis";
    
    private final String getByNama = "SELECT * FROM buku WHERE judul LIKE ?";
    
    
    @Override
    public void insert(Buku buku) {
        try {
            
            statement = connection.prepareStatement(insert);
            statement.setString(1,buku.getIsbn());
            statement.setString(2,buku.getJudul());
            statement.setString(3,buku.getTahunTerbit());
            statement.setInt(4,buku.getHarga());
            statement.setInt(5,buku.getStok());
            statement.setString(6,buku.getKategori().getIdKategori());
            statement.setString(7, buku.getPenulis().getIdPenulis());
            statement.setString(8, buku.getPenerbit().getIdPenerbit());
            statement.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                if(statement!=null){
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(Buku buku) {
        try {
            
            statement = connection.prepareStatement(insert);
            
            statement.setString(1,buku.getJudul());
            statement.setString(2,buku.getTahunTerbit());
            statement.setInt(3,buku.getHarga());
            statement.setInt(4,buku.getStok());
            statement.setString(5,buku.getKategori().getIdKategori());
            statement.setString(6, buku.getPenulis().getIdPenulis());
            statement.setString(7, buku.getPenerbit().getIdPenerbit());
            statement.setString(1,buku.getIsbn());
            statement.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                if(statement!=null){
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(String isbn) {
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(delete);
            statement.setString(1, isbn);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                e.printStackTrace();
            }
            e.printStackTrace();
        }finally{
            
            try{
                if(statement!=null){
                    statement.close();
                }
                connection.setAutoCommit(true);
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Buku> getAll() {
        try {
            statement = connection.prepareStatement(getAll);
            result = statement.executeQuery();
            
            while(result.next()){
                
                if(buku == null){
                    buku = new Buku();
                }
                
                buku.setIsbn(result.getString("isbn"));
                buku.setJudul(result.getString("judul"));
                buku.setTahunTerbit(result.getString("tahun_terbit"));
                buku.setHarga(result.getInt("harga"));
                buku.setStok(result.getInt("stok"));
                
                if(kategori == null){
                    kategori = new Kategori();
                }
                
                kategori.setIdKategori(result.getString("id_kategori"));
                kategori.setNamaKategori(result.getString("nama_kategori"));
                buku.setKategori(kategori);
                
                
                if(penulis == null){
                    penulis = new Penulis();
                }
                
                penulis.setIdPenulis(result.getString("id_penulis"));
                penulis.setNamaPenulis(result.getString("nama_penulis"));
                buku.setPenulis(penulis);
                
                
                if(penerbit == null){
                    penerbit = new Penerbit();
                }
                
                penerbit.setIdPenerbit(result.getString("id_penerbit"));
                penerbit.setNamaPenerbit(result.getString("nama_penerbit"));
                buku.setPenerbit(penerbit);
                
                listBuku.add(buku);
                
            }
        } catch (SQLException e) {
            
            e.printStackTrace();
            
        }finally{
            try {
                if(statement!=null){
                    statement.close();
                }
                if(result!=null){
                    result.close();
                }
            } catch (SQLException e) {
            }
        }
        return listBuku;
    }

    @Override
    public List<Buku> getByNama(String judulBuku) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Kategori> getAllKategori() {
        try {
            statement = connection.prepareStatement("SELECT * FROM kategori");
            result = statement.executeQuery();
            
            while(result.next()){
                kategori = new Kategori();
                kategori.setIdKategori(result.getString("id_kategori"));
                kategori.setNamaKategori(result.getString("nama_kategori"));
                listKategori.add(kategori);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                if(statement!=null){
                    statement.close();
                }
                if(result!=null){
                    result.close();
                }
            } catch (SQLException e) {
            }
        }
        return listKategori;
    }

    @Override
    public List<Penulis> getAllPenulis() {
        try {
            statement = connection.prepareStatement("SELECT * FROM penulis");
            result = statement.executeQuery();
            
            while(result.next()){
                penulis = new Penulis();
                penulis.setIdPenulis(result.getString("id_penulis"));
                penulis.setNamaPenulis(result.getString("nama_penulis"));
                penulis.setAlamatJalan(result.getString("alamat_jalan"));
                penulis.setKota(result.getString("kota"));
                penulis.setKodePos(result.getString("kodepos"));
                penulis.setTelepon(result.getString("telepon"));
                listPenulis.add(penulis);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                if(statement!=null){
                    statement.close();
                }
                if(result!=null){
                    result.close();
                }
            } catch (SQLException e) {
            }
        }
        return listPenulis;
    }

    @Override
    public List<Penerbit> getAllPenerbit() {
        try {
            statement = connection.prepareStatement("SELECT * FROM penerbit");
            result = statement.executeQuery();
            
            while(result.next()){
                penerbit = new Penerbit();
                penerbit.setIdPenerbit(result.getString("id_penerbit"));
                penerbit.setNamaPenerbit(result.getString("nama_penerbit"));
                penerbit.setAlamatJalan(result.getString("alamat_jalan"));
                penerbit.setKota(result.getString("kota"));
                penerbit.setKodePos(result.getString("kodepos"));
                penerbit.setTelepon(result.getString("telepon"));
                listPenerbit.add(penerbit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                if(statement!=null){
                    statement.close();
                }
                if(result!=null){
                    result.close();
                }
            } catch (SQLException e) {
            }
        }
        return listPenerbit;
    }

    @Override
    public List<Supplier> getAllSupplier() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
