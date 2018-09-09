
package library_management;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class UserViewBook implements ActionListener 
{
    JFrame f;
    JLabel l1;
    JTextField t1;
    JButton b1,b2,b3;
    JRadioButton r1,r2;
    JTable tb;
    JScrollPane sp;
    String column[]={"Book Name","Genre","Author","Publisher","Shelf","Row"};
    public UserViewBook() 
    {
        f = new JFrame();
        l1= new JLabel("Books");
        r1 = new JRadioButton("Name");
        r2 = new JRadioButton("Author");

        ButtonGroup bg = new ButtonGroup();
        bg.add(r1);
        bg.add(r2);

        t1 = new JTextField("");
        b1=new JButton("Show All");
        b2=new JButton("Search");
        b3=new JButton("Close");

        
        Image icon = Toolkit.getDefaultToolkit().getImage("D:\\java netbeans\\Library management\\src\\library_management\\0007_CMO_IconLB_HSEQ-Library_RGB.png");    
        f.setIconImage(icon);
        f.setLayout(new BorderLayout());
        f.setContentPane(new JLabel(new ImageIcon("D:\\java netbeans\\Library management\\src\\library_management\\5.jpg")));
        f.setLayout(new FlowLayout());
        
        f.add(l1);
        f.add(t1);
        f.add(b1);
        f.add(b2);
        f.add(b3);
        f.add(r1);
        f.add(r2);
        sp = new JScrollPane(tb);

        
        t1.setBounds(500,150,150,30);
        l1.setBounds(420, 20, 250, 40);
        b1.setBounds(250, 150, 100, 30);
        b2.setBounds(700, 150, 100, 30);
        b3.setBounds(420, 650, 100, 40);
        r1.setBounds(200, 100, 100, 30);
        r2.setBounds(400, 100, 100, 30);

        l1.setFont(new Font("Calibri", Font.BOLD, 36));
        r1.setFont(new Font("Calibri", Font.ROMAN_BASELINE, 20));
        r2.setFont(new Font("Calibri", Font.ROMAN_BASELINE, 20));

               
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        r1.addActionListener(this);
        r2.addActionListener(this);
        
    }


    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        if(ae.getSource()==b1)
        {
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
  
        }

        else if(ae.getSource()==b3)
        {
            new UserSuccess();
            f.dispose();
        }
        
        else if(ae.getSource()==b2)
        {
           
            if(r1.isSelected())
            {
            
        String a = t1.getText();
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
            PreparedStatement ps=Con.prepareStatement("select * from books where BookName = ?");
            ps.setString(1, a);
            ResultSet rs= ps.executeQuery();

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
        }
        }
        else if(ae.getSource()==b2)
        {
          if(r2.isSelected())
          {
            
           String b = t1.getText();
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
            PreparedStatement ps=Con.prepareStatement("select * from books where Author = ?");
            ps.setString(1, b);
            ResultSet rs= ps.executeQuery();

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
            }
        }
        
    }
    
    public static void main(String[] args) 
    {
        new UserViewBook();
        UserViewBook hg = new UserViewBook();
        hg.table();
    }

    public void table() 
    {
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
        
        
        JPanel p =new JPanel(); 
        p.add(sp);
        sp.setPreferredSize(new Dimension(700,400));
        p.setBounds(150,210,700,407);
        f.add(p);
        f.setLayout(null);
        f.setVisible(true);
        f.setSize(1000,800);
        f.setLocation(400,150);
        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
    }
}
