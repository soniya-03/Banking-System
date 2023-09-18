import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/balance")
public class balance extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
   
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/yourdb?useSSL=false", "root", "soniya22322");
            String sql = "SELECT balance FROM accounts WHERE username = ? AND password = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            rs = stmt.executeQuery();
            out.println("<style>");
    out.println("body {");
    out.println("    background-color: lightblue;");
    out.println("    margin: 0;");
    out.println("    padding: 0;");
    out.println("    font-family: Arial, sans-serif;");
    out.println("}");
    out.println(".navbar {");
    out.println("    background-color: rgba(0, 0, 0, 0.5);");
    out.println("    overflow: hidden;");
    out.println("    text-align: center;");
    out.println("    border-bottom: 2px solid #333;"); // Add a border to the navigation bar
    out.println("}");
    out.println(".navbar a {");
    out.println("    display: inline-block;");
    out.println("    font-size: 16px;");
    out.println("    color: white;");
    out.println("    text-align: center;");
    out.println("    padding: 14px 16px;");
    out.println("    text-decoration: none;");
    out.println("}");
    out.println(".navbar a:hover {");
    out.println("    background-color: #ddd;");
    out.println("    color: black;");
    out.println("}");
    out.println(".social-media {\n" +
"            position: fixed;\n" +
"            bottom: 20px;\n" +
"            right: 20px;\n" +
"        }\n" +
"\n" +
"        .social-media a {\n" +
"            display: inline-block;\n" +
"            margin-right: 10px;\n" +
"            font-size: 20px;\n" +
"            color: Blue;\n" +
"            text-decoration: none;\n" +
"        }");
    out.println(".success-message {"); // Add styles for the success message
out.println("    font-size: 40px;");
out.println("    font-weight: bold;");
out.println("    text-align: center;");
out.println("    margin-top: 50px;"); // Adjust margin-top to center the message
out.println("}");
    out.println("</style>");
        
            out.println(" <div class=\"navbar\">\n" +
"        <a href=\"index.html\">Home</a>\n" +
"        <a href=\"newaccount.html\">New Account</a>\n" +
"        <a href=\"balance.html\">Balance</a>\n" +
"        <a href=\"deposit.html\">Deposit</a>\n" +
"        <a href=\"withdraw.html\">Withdraw</a>\n" +"        <a href=\"transfer.html\">Transfer</a>\n" +
"        <a href=\"aboutus.html\">About us</a>\n" +
"        <a href=\"contactus.html\">Contact us</a>\n" +
"    </div>");
            out.println(" <div class=\"social-media\">\n" +
"        <a href=\"https://facebook.com/rmbank\" target=\"_blank\">Facebook</a>\n" +
"        <a href=\"https://twitter.com/rmbank\" target=\"_blank\">Twitter</a>\n" +
"        <a href=\"https://instagram.com/rmbank\" target=\"_blank\">Instagram</a>\n" +
"        <!-- Add more social media links as needed -->\n" +
"        \n" +
"    </div>");

            if (rs.next()) {
                double balance = rs.getDouble("balance");
                out.println("<div class=\"success-message\">Your balance is: Rs. "+balance+"</div>");
                
            } else {
                out.println("<div class=\"success-message\">Invalid username or password </div>");
                
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.println("Error: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
