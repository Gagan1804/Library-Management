
package library_management;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;
public class user_login implements ActionListener
{
    JFrame f;
    JButton b1,b2;
    JTextField t1;
    JLabel l1,l2,l3;
    JPasswordField p;
    user_login()
    {
        f = new JFrame("User login");
        b1 = new JButton("Login");
        b2 = new JButton("Back");
        t1 = new JTextField();
        t1.setBounds(305,100,250,30);
        l1 = new JLabel("Enter Username : ");
        l2 = new JLabel("Enter Password : ");
        p = new JPasswordField();
        
        l3 = new JLabel();
        l3.setIcon(new ImageIcon(new ImageIcon("D:\\java netbeans\\Library management\\src\\library_management\\giphy (3).gif").getImage().getScaledInstance(450, 450, Image.SCALE_DEFAULT)));
        
        l1.setBounds(100,100,200,30);
        l2.setBounds(100,150,200,30);
        l3.setBounds(600,100,400,400);
        b1.setBounds(350,300,150,40);
        b2.setBounds(50,500,150,40);
        
        l1.setFont(new Font("Calibri", Font.ROMAN_BASELINE, 20));
        l2.setFont(new Font("Calibri", Font.ROMAN_BASELINE, 20));
        b1.setFont(new Font("Calibri", Font.ROMAN_BASELINE, 20));
        b2.setFont(new Font("Calibri", Font.ROMAN_BASELINE, 20));
        p.setBounds(305,150,250,30);
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        
        Image icon = Toolkit.getDefaultToolkit().getImage("D:\\java netbeans\\Library management\\src\\library_management\\0007_CMO_IconLB_HSEQ-Library_RGB.png");    
        f.setIconImage(icon);
        f.setLayout(new BorderLayout());
        f.setContentPane(new JLabel(new ImageIcon("D:\\java netbeans\\Library management\\src\\library_management\\5.jpg")));
        f.setLayout(new FlowLayout());
        
        f.add(p);
        f.add(l1);
        f.add(l2);
        f.add(l3);
        f.add(t1);
        f.add(b1);
        f.add(b2);
        f.setLocation(400,100);
        f.setSize(1040,700);
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
    }    
        public void actionPerformed(ActionEvent ae) 
        {   
            if(ae.getSource()==b1)
            {
            String usr = t1.getText();
            String pass = p.getText();
            try 
        {
            // load driver
            Class.forName("com.mysql.jdbc.Driver");  // yeh line as it is likhni hai
            // getConnection
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library1", "root", "123"); // yeh line bhi as it is likhni hai aur yeh root and 123 are 
            // create statement
            PreparedStatement ps = con.prepareStatement("select * from user_login_accounts where Username=? && Password=?");
            // execute statement
            
            ps.setString(1, usr);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery(); // executeQuery return type= ResultSet 
            System.out.println("user name "+usr);
            if(rs.next())  // matlab agar yeh condition true ho gayi to yaani agar database mein uss bande ke account ka username aur password agar iss window mein entered username aur password se match kar gayi to yeh if wali condition chalegi
            {
                f.dispose();
              //  new UserSuccess();
                UserSuccess.showData(new String[]{usr, pass});
                
            }
            else  // warna fir yeh else wali condition chalegi
            {
                new user_login_accounts_creation();
                f.setVisible(false);
                JOptionPane.showMessageDialog(b1,"This account details you entered do not exist...Please check your details or create an account first");
            }
        } 
        catch (Exception e) //try catch use nahin karenge to exception aa jayegi aur program run nahin hogi
        {
            System.out.println(e);
        }
        }
        else if(ae.getSource()==b2)
        {
            new Main_page();
            f.dispose();
        }
    }
    public static void main(String[] args) 
    {
        new user_login();

    }

}


