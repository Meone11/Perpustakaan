
package pendataanmahasiswa;

import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author meone
 */
public class Mahasiswa {
    
    public void InsertUpdateDelete(char Operation, String nama, String prodi, String email, String pass, String gender, String date, String npm, String nomor,String namaLama){
        PreparedStatement ps;
        String query = "INSERT INTO `DataMahasiswa`(`Nama`, `Prodi`, `Email`, `Password`, `Jenis Kelamin`, `Tanggal Lahir`, `NPM`, `No Telepon`) VALUES (?,?,?,?,?,?,?,?)";
        if (Operation == 'i'){
            try {
                ps = (PreparedStatement) MyConncetion.getConnection().prepareStatement(query);
                ps.setString(1, nama);
                ps.setString(2, prodi);
                ps.setString(3, email);
                ps.setString(4, pass);
                ps.setString(5, gender);
                ps.setString(6, date);
                ps.setString(7, npm);
                ps.setString(8, nomor);
                
                if (ps.executeUpdate() > 0){
                    JOptionPane.showMessageDialog(null,"Anda berhasil Mendaftar, NPM Anda Adalah " + npm);
                }
            } catch (java.sql.SQLException ex) {
                Logger.getLogger(Mahasiswa.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }else if (Operation == 'u'){ // u For Update or Edit Data
            try {
                ps = (PreparedStatement) MyConncetion.getConnection().prepareStatement("UPDATE `DataMahasiswa` SET `Nama`= ?,`Prodi`= ?,`Email`= ?,`Password`= ?,`Jenis Kelamin`= ?,`Tanggal Lahir`= ?,`NPM`= ?,`No Telepon`= ? WHERE `Nama` = ?");
                ps.setString(1, nama);
                ps.setString(2, prodi);
                ps.setString(3, email);
                ps.setString(4, pass);
                ps.setString(5, gender);
                ps.setString(6, date);
                ps.setString(7, npm);
                ps.setString(8, nomor);
                ps.setString(9, namaLama);
                
                if (ps.executeUpdate() > 0){
                    JOptionPane.showMessageDialog(null,"Data Berhasil Di Update");
                }
            } catch (java.sql.SQLException ex) {
                Logger.getLogger(Mahasiswa.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }else if (Operation == 'd'){ // d For Delete Data
            try {
                ps = (PreparedStatement) MyConncetion.getConnection().prepareStatement("DELETE FROM `DataMahasiswa` WHERE `Nama` = ?");
                ps.setString(1, nama);
                
                if (ps.executeUpdate() > 0){
                    JOptionPane.showMessageDialog(null,"Data Berhasil Di Hapus");
                }
            } catch (java.sql.SQLException ex) {
                Logger.getLogger(Mahasiswa.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
    public void FillToTable(JTable table, String cari){
        Connection c = MyConncetion.getConnection();
        PreparedStatement ps;
        
        try {
            ps = (PreparedStatement) c.prepareStatement("SELECT * FROM `DataMahasiswa` WHERE CONCAT(`Nama`, `Prodi`, `Jenis Kelamin`, `Tanggal Lahir`, `NPM`, `No Telepon`) LIKE ?");
            ps.setString(1,"%"+cari+"%");
            
            ResultSet rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            
            Object[] row;
            
            while (rs.next()){
                row = new Object[6];
                row[0] = rs.getString(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(5);
                row[3] = rs.getString(6);
                row[4] = rs.getString(8);
                row[5] = rs.getString(7);
                
                model.addRow(row);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Mahasiswa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void Seacrh(JTable table, String cari){
        Connection c = MyConncetion.getConnection();
        PreparedStatement ps;
        
        try {
            ps = (PreparedStatement) c.prepareStatement("SELECT * FROM `DataMahasiswa` WHERE Nama = ?");
            ps.setString(1,cari);
            
            ResultSet rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            
            Object[] row;
            
            while (rs.next()){
                row = new Object[6];
                row[0] = rs.getString(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(5);
                row[3] = rs.getString(6);
                row[4] = rs.getString(8);
                row[5] = rs.getString(7);
                
                model.addRow(row);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Mahasiswa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
