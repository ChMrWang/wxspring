package dbinstance;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import dbpackge1_0.DBService;

/**
 * Servlet implementation class DBInstance
 */
@WebServlet(
		description = "DataBase instance", 
		urlPatterns = { "/DBInstance" }, 
		//loadOnStartup = 1,  //使此servlet在tomcat启动时执行
		initParams = { 
				@WebInitParam(name = "driver", value = "com.mysql.jdbc.Driver"), 
				@WebInitParam(name = "url", value = "jdbc:mysql://127.0.0.1:3306/spring"), 
				@WebInitParam(name = "user", value = "root"), 
				@WebInitParam(name = "password", value = "root")				
		})
public class DBInstance extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DBInstance() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		String url = config.getInitParameter("url");
		url = url+"?useUnicode=true&characterEncoding=utf-8";
		String driver = config.getInitParameter("driver");
		String name = config.getInitParameter("user");
		String password = config.getInitParameter("password");
		/*Properties prop = new Properties();
		
		try{
			prop.load(new FileInputStream("jdbc.properties"));
		} catch(IOException e1){
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		url = prop.getProperty("url");
		driver = prop.getProperty("driver");
		name = prop.getProperty("username");
		password = prop.getProperty("password");*/
		DBService.instance(driver,url,name,password);
		System.out.println("DataBase connection instance success ...!");
	}

}
