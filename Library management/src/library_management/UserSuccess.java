package library_management;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;

public class UserSuccess implements ActionListener {

    public JFrame f;
    public JLabel l1, l2;
    JButton b1, b2, b3;
    public static JTextField t1, t2;
  public static String Name,Lastname,Email;
    public UserSuccess() {
        l1 = new JLabel("Name");

        l2 = new JLabel("Email");

        f = new JFrame();

        b1 = new JButton("View Books");
        b2 = new JButton("Issued Books");
        b3 = new JButton("LogOut");

        t1 = new JTextField();
        t2 = new JTextField();

         t1.setText(Name+" "+Lastname);
        t2.setText(Email);
        Font font = new Font("Calibri", Font.PLAIN, 20);
        Font grt = new Font("Calibri", Font.ROMAN_BASELINE, 30);

        t1.setFont(font);
        t1.setForeground(Color.BLACK);
        t1.setEditable(false);
        t2.setFont(font);
        t2.setForeground(Color.BLACK);
        t2.setEditable(false);

        l1.setFont(font);
        l1.setForeground(Color.BLUE);
        l2.setFont(font);
        l2.setForeground(Color.BLUE);

        b1.setFont(grt);
        b1.setForeground(Color.BLACK);
        b2.setFont(grt);
        b2.setForeground(Color.BLACK);
        b3.setFont(grt);
        b3.setForeground(Color.RED);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);

        l1.setBounds(50, 50, 100, 30);
        l2.setBounds(50, 120, 100, 30);

        t1.setBounds(200, 50, 400, 30);
        t2.setBounds(200, 120, 400, 30);

        b1.setBounds(200, 380, 200, 50);
        b2.setBounds(500, 380, 200, 50);
        b3.setBounds(750, 40, 200, 40);

        t1.setEditable(false);
        t2.setEditable(false);

        Image icon = Toolkit.getDefaultToolkit().getImage("D:\\java netbeans\\Library management\\src\\library_management\\0007_CMO_IconLB_HSEQ-Library_RGB.png");    
        f.setIconImage(icon);
        f.setLayout(new BorderLayout());
        f.setContentPane(new JLabel(new ImageIcon("D:\\java netbeans\\Library management\\src\\library_management\\5.jpg")));
        f.setLayout(new FlowLayout());
        f.add(l1);
        f.add(l2);
        f.add(t1);
        f.add(t2);
        f.add(b1);
        f.add(b2);
        f.add(b3);
        f.setSize(1000, 600);
        f.setLocation(400, 150);
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            new UserViewBook();  // **not working
            f.dispose();
        } else if (e.getSource() == b2) {
            new UserViewIssuedBooks(); // **not working
            f.dispose();
        } else if (e.getSource() == b3) {
            new Main_page();
            f.dispose();
        }
    }

    public static void main(String[] args) {
        new UserSuccess();    //   yeh bhi lagayenge to 2 windows khul jayengi same yehi wali... kyunki jo neeche 2 lines likhi hain usse bhi kaam ban jayega
        //    UserSuccess ul = new UserSuccess();
        // ul.showData(args);
    }

    public static void showData(String args[]) {

        String usr = args[0];
        String pass = args[1];
        String fn = "";
        String ln = "";
        String em = "";
        System.out.println("new " + usr);

        try (Connection Con = Db.getConnection()) {

            PreparedStatement ps = Con.prepareStatement("select * from user_login_accounts where Username = ? && Password = ?");

            ps.setString(1, usr);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
              boolean status = rs.next();
            Name=rs.getString("Firstname");
            Lastname = rs.getString("Lastname");
            Email = rs.getString("Email");
            System.out.println(Name+" "+Lastname+" "+Email);
            Con.close();

            if (fn != null) 
            {
                t1 = new JTextField();
                t2 = new JTextField();
                t1.setText(fn + " " + ln);
                t2.setText(em);
                new UserSuccess();
            }

        } catch (Exception j) {
            System.out.println(j);
        }

    }
}
