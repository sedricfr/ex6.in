
// Loading required libraries
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class loassign extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // JDBC driver name and database URL
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost:3307/movies";

        // Database credentials
        String USER = "root";
        String PASS = "";

        // Set response content type
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String title = "Database Result";

        String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";

        out.println(docType +
                "<html>\n" +
                "<head><title>" + title + "</title> " +
                "<style> th, td {" +
                " padding-top: 10px;" +
                "padding-bottom: 20px;" +
                "  padding-left: 30px;" +
                "padding-right: 40px;" +
                "} </style>" +
                "</head>\n" +
                "<body bgcolor = \"#f0f0f0\">\n" +
                "<h1 align = \"center\">" + title + "</h1>\n");
        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // Open a connection
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Execute SQL query
            Statement stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM shows;";
            ResultSet rs = stmt.executeQuery(sql);

            out.println(" <style> th, td { ");
            out.println("padding-top: 10px;");
            out.println(" padding-bottom: 20px;");
            out.println("padding-left: 30px;");
            out.println("padding-right: 40px;");
            out.println("} </style><center><div><table border = 1 >");
            out.println("<tr><td> Title </td>'");
            out.println("<td> Year </td>");
            out.println("<td> Country of Origin </td>");
            out.println("<td> Comment </td></tr>");

            // Extract data from result set
            while (rs.next()) {
                // Retrieve by column name
                String Title = rs.getString("Title");
                String year = rs.getString("year");
                String co = rs.getString("co");
                String comment = rs.getString("comment");

                // Display values
                out.println("<tr> <td>" + Title + " </td>");
                out.println("<td>" + year + "</td>");
                out.println("<td>" + co + "</td>");
                out.println("<td >" + comment + "</td>");
                out.println(
                        "<td > ");
                out.println("<form action='comment' method ='post'>");// onclick='window.open('comment','_blank')
                out.println("<input type='text' name='input'>");
                out.print("<input type='hidden' name='yr' value='" + year + "'>");
                out.print("<input type='submit' value='UPDATE'>");
                out.print("</form> ");
                out.println("</td></tr> ");
            }

            out.println("</table></div></center>");
            out.println("</body></html>");

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.print("Do not connect to DB - Error:" + e);
        }
    }
}