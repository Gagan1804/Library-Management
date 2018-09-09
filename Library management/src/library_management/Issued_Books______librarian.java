
package library_management;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class Issued_Books______librarian implements ActionListener
{
    JFrame f;
    JLabel l1;
    JTextField t1;
    JButton b1,b2;
    JRadioButton r1,r2,r3,r4;
    JTable tb;
    JScrollPane sp;
    JPanel p;
    String column[]={"Book Name","Username","Issue Date","Issue Month","Issue Year","Return Date","Return Month","Return Year"};
    
    public Issued_Books______librarian() 
    {
        f = new JFrame();
        l1= new JLabel("Issued Books");
        r1 = new JRadioButton("Name");
        r2 = new JRadioButton("Book ID");
        r3 = new JRadioButton("User ID");
        r4 = new JRadioButton("All");
        p = new JPanel();
        ButtonGroup bg = new ButtonGroup();
        bg.add(r1);
        bg.add(r2);
        bg.add(r3);
        bg.add(r4);
        t1 = new JTextField("");
        b1=new JButton("Show All");
        b2=new JButton("Close");
        b1.addActionListener(this);
        b2.addActionListener(this);
        
                
        Image icon = Toolkit.getDefaultToolkit().getImage("D:\\java netbeans\\Library management\\src\\library_management\\0007_CMO_IconLB_HSEQ-Library_RGB.png");    
        f.setIconImage(icon);
        f.setLayout(new BorderLayout());
        f.setContentPane(new JLabel(new ImageIcon("D:\\java netbeans\\Library management\\src\\library_management\\5.jpg")));
        f.setLayout(new FlowLayout());

        f.add(l1);
        f.add(t1);
        
        f.add(b1);
        f.add(b2);
        f.add(r1);
        f.add(r2);
        f.add(r3);
        f.add(r4);
        
        t1.setBounds(500,120,150,30);
        l1.setBounds(400, 20, 250, 40);
        b2.setBounds(400, 730, 100, 30);
        b1.setBounds(700, 120, 100, 30);
        r1.setBounds(200, 60, 100, 40);
        r2.setBounds(300, 60, 110, 30);
        r3.setBounds(410, 60, 110,30);
        r4.setBounds(600,60,110,30);
        l1.setFont(new Font("Calibri", Font.BOLD, 36));
        r1.setFont(new Font("Calibri", Font.ROMAN_BASELINE, 20));
        r2.setFont(new Font("Calibri", Font.ROMAN_BASELINE, 20));
        r3.setFont(new Font("Calibri", Font.ROMAN_BASELINE, 20));
        r4.setFont(new Font("Calibri", Font.ROMAN_BASELINE, 20));

        
    }
    public static void main(String[] args) 
    {
        new Issued_Books______librarian();
        Issued_Books______librarian bp = new Issued_Books______librarian();
        bp.displaytable();
    }

    
    public void actionPerformed(ActionEvent ae) 
    {
        if(ae.getSource()==b2)
        {
            new LibrarianSuccess();
            f.dispose();
        }
    }
    private void displaytable() 
    {
        DefaultTableModel dtm = new DefaultTableModel() ;
        dtm.setColumnIdentifiers(column);
        tb = new JTable();
        tb.setModel(dtm);

        tb.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tb.getColumnModel().getColumn(0).setMinWidth(150);
        tb.getColumnModel().getColumn(1).setMinWidth(146);
        tb.getColumnModel().getColumn(2).setMinWidth(100);
        tb.getColumnModel().getColumn(3).setMinWidth(100);
        tb.getColumnModel().getColumn(4).setMinWidth(100);
        tb.getColumnModel().getColumn(5).setMinWidth(100);
        tb.getColumnModel().getColumn(6).setMinWidth(100);
        tb.getColumnModel().getColumn(7).setMinWidth(100);
        
        tb.setFillsViewportHeight(true);
        sp = new JScrollPane(tb);
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        String bn ="";
        String un ="";
        String id ="";
        String im ="";
        String iy ="";
        String rd ="";
        String rm ="";
        String ry ="";
        
           try(Connection Con = Db.getConnection()) 
           {
            PreparedStatement ps=Con.prepareStatement("select * from issue_book");
            ResultSet rs= ps.executeQuery();
            System.out.println(rs);
            while(rs.next())
            {
                bn = rs.getString("Bookname");
                un = rs.getString("Username");
                id = rs.getString("IssueDate");
                im = rs.getString("IssueMonth");
                iy = rs.getString("IssueYear");
                rd = rs.getString("ReturnDate");
                rm = rs.getString("ReturnMonth");
                ry = rs.getString("ReturnYear");
                dtm.addRow(new Object[]{bn,un,id,im,iy,rd,rm,ry});

            }
            
           }
            catch(Exception gr)
            {
                System.out.println(gr);
            }
        
        JPanel p = new JPanel();
        p.add(sp);
        sp.setPreferredSize(new Dimension(900,500));
        p.setBounds(30,200,900,507);
        f.add(p);
        f.setLayout(null);
        f.setVisible(true);
        f.setSize(1000,830);
        f.setLocation(400,100);
        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
    }

    
}
