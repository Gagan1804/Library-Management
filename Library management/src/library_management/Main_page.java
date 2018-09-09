
package library_management;



import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class Main_page implements ActionListener
{
    JPanel p;
    JFrame f;
    JLabel l1,l2;
    JButton b3,b4,b1,b2;
    JTextField t1;
    Main_page()
    {
        f= new JFrame("Welcome to The Grande Library");
        p = new JPanel();
        p.setBounds(0,0,1000,150);
        p.setBackground(Color.orange);
        l1= new JLabel("Library Management System");
        b3= new JButton("Librarian");
        b4= new JButton("Member");
        b1 = new JButton("Create new account");
        b2 = new JButton("Close");
        t1 = new JTextField("    Login as");
        Font font = new Font("Verdana", Font.BOLD, 12);
        t1.setFont(font);
        t1.setForeground(Color.BLUE);
        t1.setEditable(false);
        
        l1.setFont(new Font("Calibri", Font.PLAIN, 72));
        t1.setFont(new Font("Calibri", Font.BOLD, 32));
        b3.setFont(new Font("Calibri", Font.ROMAN_BASELINE, 28));
        b4.setFont(new Font("Calibri", Font.ROMAN_BASELINE, 28));
        b1.setFont(new Font("Calibri", Font.ROMAN_BASELINE, 28));
        b2.setFont(new Font("Calibri", Font.ROMAN_BASELINE, 28));
        
        l1.setBounds(20,20,600,60);
        t1.setBounds(410,240,170,40);
        b3.setBounds(200,330,200,40);
        b4.setBounds(600,330,200,40);
        b1.setBounds(360,550,280,40);
        b2.setBounds(410,700,180,40);
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        
        Image icon = Toolkit.getDefaultToolkit().getImage("D:\\java netbeans\\Library management\\src\\library_management\\0007_CMO_IconLB_HSEQ-Library_RGB.png");    
        f.setIconImage(icon);
        f.setLayout(new BorderLayout());
        f.setContentPane(new JLabel(new ImageIcon("D:\\java netbeans\\Library management\\src\\library_management\\lib.jpg")));
        f.setLayout(new FlowLayout());
        
        f.setSize(1000,800);
        f.setLayout(null);
        f.setVisible(true);
        f.setLocation(400,50);
        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
        f.add(p);
        p.add(l1);
        f.add(t1);
        f.add(b3);
        f.add(b4);
        f.add(b1);
        f.add(b2);

    }    
    public void actionPerformed(ActionEvent e)
        {
            if(e.getSource()==b1)
            {
                new user_login_accounts_creation();
                f.setVisible(false);
            }
            else if(e.getSource()==b2)
            {
                f.setVisible(false);
            }
            else if(e.getSource()==b3)
            {
                new Librarianlogin();
                f.setVisible(false);
            }
            else if(e.getSource()==b4)
            {
                new user_login();
                f.setVisible(false);
            }
        }
        
    public static void main(String[] args) 
    {
        new Main_page();
    }

}


    


