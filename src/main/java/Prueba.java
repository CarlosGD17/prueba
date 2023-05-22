
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



public class Prueba extends HttpServlet {
	private DataSource ds;
	private static final long serialVersionUID = 1L;
       

    public Prueba() {
        super();
 
    }


	public void init(ServletConfig config) throws ServletException {
		 super.init(config);
	       
	        InitialContext cxt;
	       
	        try {
	            cxt = new InitialContext();
	            if(cxt!=null) {
	                this.ds = (DataSource) cxt.lookup("java:/comp/env/jdbc/ds");
	            }
	            if(this.ds == null) {
	                throw new ServletException("DataSource no encontrado");
	            }
	        }catch(NamingException e) {
	            throw new ServletException("Sin contexto inicial");
	        }
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
            Connection con = this.ds.getConnection();
            response.getWriter().append("Tenemos DS: ").append(this.ds.toString()).append(" "+con.toString());
        } catch (SQLException e) {
            throw new ServletException(e);
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}