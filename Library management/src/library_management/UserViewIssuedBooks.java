
package library_management;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class UserViewIssuedBooks implements ActionListener
{
    JLabel l;
    JButton b;
    JTable tb;
    JScrollPane sp;
    JFrame f;
    JPanel p;
    String column[]={"Book Name","Issued to username","Issue Date","Issue Month","Issue Year","Return Date","Return Month","Return Year"};
    public UserViewIssuedBooks()
    {
        f = new JFrame();
        l= new JLabel("Issued Books");
        b=new JButton("Back");
        
        l.setFont(new Font("Calibri", Font.BOLD, 36));
        l.setBounds(320,30,300,40);
        b.setBounds(100,730,100,40);
        b.addActionListener(this);
        
        Image icon = Toolkit.getDefaultToolkit().getImage("D:\\java netbeans\\Library management\\src\\library_management\\0007_CMO_IconLB_HSEQ-Library_RGB.png");    
        f.setIconImage(icon);
        f.setLayout(new BorderLayout());
        f.setContentPane(new JLabel(new ImageIcon("D:\\java netbeans\\Library management\\src\\library_management\\5.jpg")));
        f.setLayout(new FlowLayout());
        f.add(l);
        f.add(b);
        
    }


    @Override
    public void actionPerformed(ActionEvent a) 
    {
        new UserSuccess();
        f.dispose();
    }
    
    public static void main(String[] args) 
    {
        new UserViewIssuedBooks();
        UserViewIssuedBooks n = new UserViewIssuedBooks();
        n.showTable();
    }

    private void showTable() 
    {
        DefaultTableModel dtm = new DefaultTableModel() ;
        dtm.setColumnIdentifiers(column);
        tb = new JTable();
        tb.setModel(dtm);

        tb.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tb.getColumnModel().getColumn(0).setMinWidth(150);
        tb.getColumnModel().getColumn(1).setMinWidth(148);
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
        
        p = new JPanel();
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
    


