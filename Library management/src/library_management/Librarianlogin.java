
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

public class Librarianlogin implements ActionListener
{
    JFrame f;
    JButton b1,b2;
    JTextField t1;
    JPasswordField  p;
    JLabel l1,l2;
    Librarianlogin()
    {
        f = new JFrame("Librarian login");
        b1 = new JButton("Login");
        b2 = new JButton("Back");
        t1 = new JTextField();
        t1.setBounds(305,100,250,30);
        l1 = new JLabel("Enter Username : ");
        l2 = new JLabel("Enter Password : ");
        p = new JPasswordField();
        
        l1.setBounds(100,100,200,30);
        l2.setBounds(100,150,200,30);
        b1.setBounds(350,200,150,40);
        b2.setBounds(50,500,150,40);
        
        l1.setFont(new Font("Calibri", Font.ROMAN_BASELINE, 20));
        l2.setFont(new Font("Calibri", Font.ROMAN_BASELINE, 20));
        b1.setFont(new Font("Calibri", Font.ROMAN_BASELINE, 20));
        b2.setFont(new Font("Calibri", Font.ROMAN_BASELINE, 20));
        p.setBounds(305,150,250,30);
        
        Image icon = Toolkit.getDefaultToolkit().getImage("D:\\java netbeans\\Library management\\src\\library_management\\0007_CMO_IconLB_HSEQ-Library_RGB.png");    
        f.setIconImage(icon);
        f.setLayout(new BorderLayout());
        f.setContentPane(new JLabel(new ImageIcon("D:\\java netbeans\\Library management\\src\\library_management\\5.jpg")));
        f.setLayout(new FlowLayout());
        
        f.add(p);
        f.add(l1);
        f.add(l2);
        f.add(t1);
        f.add(b1);
        f.add(b2);
        f.setLocation(600,150); // set x-coordinate, y-coordinate
        f.setSize(700,700);
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);  
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        
    }
    public void actionPerformed(ActionEvent ae) 
        {   
            if(ae.getSource()==b1)
            {
            String Uname = t1.getText();
            String Pass = p.getText();
 
            if(LibrarianDao.validate(Uname, Pass))  // matlab agar yeh condition true ho gayi to yaani agar database mein uss bande ke account ka username aur password agar iss window mein entered username aur password se match kar gayi to yeh if wali condition chalegi
            {
                new LibrarianSuccess();
                LibrarianSuccess at = new LibrarianSuccess();
                at.admin_details();
                f.dispose();
            }
            else  // warna fir yeh else wali condition chalegi
            {
                JOptionPane.showMessageDialog(f,"Sorry....But the entered username or password is incorrect","Kindly check it again",JOptionPane.ERROR_MESSAGE);
                t1.setText("");
                p.setText("");
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
        new Librarianlogin();
    }
}


