
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
import java.sql.ResultSetMetaData;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class Viewbooks implements ActionListener 
{
    JFrame f;
    JLabel l1;
    JTextField t1;
    JButton b2,b3;
    JRadioButton r1,r2,r3;
    static JTable tb;
    JScrollPane sp;
    String column[]={"Book name","Genre","Author","Publisher","Shelf","Row"};
    Viewbooks() 
    {
        f = new JFrame();
        l1= new JLabel("Books");
        r1 = new JRadioButton("Name");
        r2 = new JRadioButton("Author");
        r3 = new JRadioButton("All");

        ButtonGroup bg = new ButtonGroup();
        bg.add(r1);
        bg.add(r2);
        bg.add(r3);

        t1 = new JTextField("");
        b2=new JButton("Search");
        b3=new JButton("Close");

               

        t1.setBounds(500,150,150,30);
        l1.setBounds(420, 20, 250, 40);

        b2.setBounds(700, 150, 100, 30);
        b3.setBounds(420, 720, 100, 40);
        r1.setBounds(200, 100, 100, 40);
        r2.setBounds(300, 100, 110, 30);
        r3.setBounds(500, 100, 110,30);

        l1.setFont(new Font("Calibri", Font.BOLD, 36));
        r1.setFont(new Font("Calibri", Font.ROMAN_BASELINE, 20));
        r2.setFont(new Font("Calibri", Font.ROMAN_BASELINE, 20));
        r3.setFont(new Font("Calibri", Font.ROMAN_BASELINE, 20));

        b2.addActionListener(this);
        b3.addActionListener(this);
        
   
        Image icon = Toolkit.getDefaultToolkit().getImage("D:\\java netbeans\\Library management\\src\\library_management\\0007_CMO_IconLB_HSEQ-Library_RGB.png");    
        f.setIconImage(icon);
        f.setLayout(new BorderLayout());
        f.setContentPane(new JLabel(new ImageIcon("D:\\java netbeans\\Library management\\src\\library_management\\5.jpg")));
        f.setLayout(new FlowLayout());
        
        f.add(l1);
        f.add(t1);
        
        f.add(b2);
        f.add(b3);
        f.add(r1);
        f.add(r2);        
        f.add(r3);
                       
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        if(ae.getSource()==b2)
        {
            
        }
        else if(ae.getSource()==b3)
        {
            new LibrarianSuccess();
            f.dispose();
        }
    }
    
    public static void main(String[] args) 
    {
      new Viewbooks();
      Viewbooks ob= new Viewbooks();
      ob.showTableData();
    }
 public  void showTableData(){
        DefaultTableModel dtm = new DefaultTableModel() ;
        dtm.setColumnIdentifiers(column);
        tb = new JTable();
        tb.setModel(dtm);

        tb.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tb.getColumnModel().getColumn(0).setMinWidth(150);
        tb.getColumnModel().getColumn(1).setMinWidth(100);
        tb.getColumnModel().getColumn(2).setMinWidth(146);
        tb.getColumnModel().getColumn(3).setMinWidth(100);
        tb.getColumnModel().getColumn(4).setMinWidth(100);
        tb.getColumnModel().getColumn(5).setMinWidth(100);
        
        tb.setFillsViewportHeight(true);
        sp = new JScrollPane(tb);
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        String bn ="";
        String ge ="";
        String au ="";
        String pu ="";
        String sh ="";
        String ro ="";
        
           try(Connection Con = Db.getConnection()) 
           {
            PreparedStatement ps=Con.prepareStatement("select * from books");
            ResultSet rs= ps.executeQuery();
            System.out.println(rs);
            while(rs.next())
            {
                bn = rs.getString("BookName");
                ge = rs.getString("Genre");
                au = rs.getString("Author");
                pu = rs.getString("Publisher");
                sh = rs.getString("Shelf");
                ro = rs.getString("Row");
                dtm.addRow(new Object[]{bn,ge,au,pu,sh,ro});

            }
            
           }
            catch(Exception gr)
            {
                System.out.println(gr);
            }
        
        JPanel p = new JPanel();
        p.add(sp);
        sp.setPreferredSize(new Dimension(700,500));
        p.setBounds(100,200,700,507);
        f.add(p);
        f.setLayout(null);
        f.setVisible(true);
        f.setSize(1000,830);
        f.setLocation(400,100);
        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
    }
}

