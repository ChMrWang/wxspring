package dbpackge1_0;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;

public class DBPool {
	private  String [] conf = null; //存放数据库连接配置数据，用于在连接不足时建立新的连接
	private  Vector<Connection> connPool;  //此处用Vector是为了使线程安全
	public DBPool(String driver,String url,String name,String password)
	{
		conf = new String [] {driver,url,name,password};
		if(connPool==null)
		{	
			connPool = new Vector<Connection>();
			try{
				Connection conn ;
				Class.forName(driver);
				for(int i=0;i<5;i++)
				{
					conn = DriverManager.getConnection(url,name,password);
					connPool.add(conn);
				}
			} catch(ClassNotFoundException e){
				e.printStackTrace();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
	
	
	public Connection getConnection()
	{
		if(connPool.isEmpty())
		{	
			connPool = new Vector<Connection>();
			try{
				Connection conn ;
				for(int i=0;i<5;i++)
				{
					conn = DriverManager.getConnection(conf[1],conf[2],conf[3]);
					connPool.add(conn);
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		Connection temp = connPool.lastElement();
		connPool.remove(connPool.size()-1); //取出一个connection，注意此处线程不安全
		return temp;
	}
	
	public void backConnection(Connection conn)
	{
		connPool.addElement(conn);
	}
}
