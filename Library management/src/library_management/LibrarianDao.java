package library_management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LibrarianDao {

    public static boolean validate(String name, String password) {
        boolean status = false;
        try {
            Connection con = Db.getConnection();
            PreparedStatement ps = con.prepareStatement("Select * from librarian where Username = ? and Password = ?");
            ps.setString(1, name);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            status = rs.next();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return status;
    }
}
