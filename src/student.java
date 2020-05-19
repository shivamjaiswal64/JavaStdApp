
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author shivam jaiswal
 */
public class student {
    public void insertUpdateDeleteStudent(char operation,Integer id,String fname,String lname,
            String sex, String bdate,String phone, String address)
    {
        Connection con = MyConnection.getConnection();
        PreparedStatement ps;
        // i for insert
        if(operation == 'i'){
            
            try { 
                ps = con.prepareStatement("INSERT INTO student(first_name, last_name, sex, birthdate, phone, address) VALUES (?,?,?,?,?,?)" );
                ps.setString(1,fname );
                ps.setString(2,lname );
                ps.setString(3,sex );
                ps.setString(4,bdate );
                ps.setString(5, phone);
                ps.setString(6, address);
                
                if(ps.executeUpdate()> 0){
                    JOptionPane.showMessageDialog(null,"New Student Added");
                }
                
                
                
                
            } catch (SQLException ex) {
                Logger.getLogger(student.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
    }
    
    public void fillStudentJtable(JTable table, String valueToSearch){
        Connection con = MyConnection.getConnection();
        PreparedStatement ps;        
        try {
            ps = con.prepareStatement("SELECT * FROM `student` WHERE CONCAT (`first_name`,`last_name`,`phone`,`address`) LIKE ?");
            ps.setString(1,"%"+valueToSearch+"%");
            
            ResultSet rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel)table.getModel();
            
            Object[] row;
            while(rs.next()){
                row = new Object[7];
                row[0] = rs.getInt(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getString(4);
                row[4] = rs.getString(5);
                row[5] = rs.getString(6);
                row[6] = rs.getString(7);
                
                model.addRow(row);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(student.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
