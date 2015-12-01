package dbpackge1_0;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBService
{
	private static DBManage dbm;  //数据库连接池，封装底层操作，接受SQL语句即可
	private DBService(String driver,String url,String name,String password)
	{
		dbm = new DBManage(driver,url,name,password);
	}
	
	public static void instance(String driver,String url,String name,String password)
	{	//初始化连接池
		dbm = new DBManage(driver,url,name,password);
	}
	
	
	public static int insertReflect(Object obj)
	{	//将一个对象插入到对应表中，应用反射机制
		Class<?> classtype = obj.getClass();
		Field [] fieldlist = classtype.getDeclaredFields();
		StringBuffer sql = new StringBuffer("INSERT INTO "); //sql语句前半段，用于在循环中添加字段名
		sql.append(classtype.getSimpleName());
		sql.append(" (");
		StringBuffer value = new StringBuffer("VALUE (");  //sql语句后半段，用于在循环中添加字段值
		try {
			for(Field temp:fieldlist)
			{
				temp.setAccessible(true);
				if(temp.get(obj)==null)     //若字段值为空，则放弃添加，此判断不加 则会吧“null”当作字段值插入到表中
					continue;
				sql.append(temp.getName());
				sql.append(",");
				value.append("'");
				value.append(temp.get(obj));
				value.append("'");
				value.append(",");
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sql.deleteCharAt(sql.length()-1);     //删除多出的一个","
		value.deleteCharAt(value.length()-1); //同上
		sql.append(") ");
		value.append(") ");
		sql.append(value.toString());
		//System.out.println(sql.toString());
		return dbm.executeUpdate(sql.toString());
	}
	
	public static int insertReflectForIncKey(Object obj)
	{	//将一个对象插入到对应表中，应用反射机制,获取自增主键
		Class<?> classtype = obj.getClass();
		Field [] fieldlist = classtype.getDeclaredFields();
		StringBuffer sql = new StringBuffer("INSERT INTO "); //sql语句前半段，用于在循环中添加字段名
		sql.append(classtype.getSimpleName());
		sql.append(" (");
		StringBuffer value = new StringBuffer("VALUE (");  //sql语句后半段，用于在循环中添加字段值
		try {
			for(Field temp:fieldlist)
			{
				temp.setAccessible(true);
				if(temp.get(obj)==null)     //若字段值为空，则放弃添加，此判断不加 则会吧“null”当作字段值插入到表中
					continue;
				sql.append(temp.getName());
				sql.append(",");
				value.append("'");
				value.append(temp.get(obj));
				value.append("'");
				value.append(",");
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sql.deleteCharAt(sql.length()-1);     //删除多出的一个","
		value.deleteCharAt(value.length()-1); //同上
		sql.append(") ");
		value.append(") ");
		sql.append(value.toString());
		//System.out.println(sql.toString());
		return dbm.executeUpdate(sql.toString(), Statement.RETURN_GENERATED_KEYS);
	}
	
	public static List<Object> selectReflect(Object obj)
	{   //查询和一个类相关（通过类名）表格的所有数据，返回一个List<Object>
		List<Object> li= new ArrayList<Object>();
		Class<?> classtype = obj.getClass();
		StringBuffer sql = new StringBuffer("SELECT * FROM ");  //用于构造查询的SQL语句
		sql.append(classtype.getSimpleName());
		ResultSet rt = dbm.executeQuery(sql.toString());
		try {
			ResultSetMetaData rtmd= rt.getMetaData();   //获取表头，此信息用于获取相应的字段名
			while(rt.next())
			{
				Object data = classtype.newInstance();
				int i = rtmd.getColumnCount();
				for( ;i>0;i--)
				{
					byte[] letter = rtmd.getColumnName(i).getBytes();
					for(int j=0;j<letter.length;j++)
					{
						if(letter[j]<'a')
							letter[j]+=32;
					}     //将字段全部转化为小写字母
					Field field = classtype.getDeclaredField(new String(letter));
					field.setAccessible(true);
					if(rt.getObject(i)!=null)
						field.set(data, rt.getObject(i));//注意判空的问题
				}
				li.add(data);
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;
	}
	
	public static int modify(Object obj)
	{	//修记录信息，反射机制
		Class<?> classtype = obj.getClass();
		StringBuffer sql =new StringBuffer("UPDATE "); //记录where前的信息
		StringBuffer whe = new StringBuffer(" WHERE 1 "); //记录where后的信息
		String tname = classtype.getSimpleName();
		ArrayList<String> pk = dbm.getPriKey(tname); //获取表的主键名
		for(String ppk:pk)
		{
			System.out.println(ppk);
		}
		sql.append(tname);
		sql.append(" SET ");
		Field [] fieldlist = classtype.getDeclaredFields();
		for(Field temp:fieldlist)
		{
			temp.setAccessible(true);
			Object tobj = null;
			try {
				tobj = temp.get(obj);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			if(tobj==null) //属性值为空，则不用改变
				continue;
			
			String fname = temp.getName();
			if(pk.contains(fname.toLowerCase()))
			{			//属性名与主键名相等，则作为where后的查找条件
				whe.append(" AND ");
				whe.append(fname);
				whe.append("=");
				whe.append("'"+tobj+"'");
				continue;
			}
			
			sql.append(fname);
			sql.append("=");
			sql.append("'"+tobj+"'");
			sql.append(",");
		}
		sql.deleteCharAt(sql.length()-1);
		sql.append(whe);
		System.out.println(sql);
		int a =dbm.executeUpdate(sql.toString());
		return a;
	}
	
	public static boolean Exist(Object obj)
	{
		Class<?> classtype = obj.getClass();
		StringBuffer sql = new StringBuffer("SELECT * FROM ");
		String tname = classtype.getSimpleName();
		ArrayList<String> pk = dbm.getPriKey(tname);
		sql.append(tname);
		sql.append(" WHERE 1");
		for(String temp:pk)
		{
			sql.append(" AND ");
			sql.append(temp);
			sql.append(" = ");
			try {
				byte[] letter = temp.getBytes();
				for(int j=0;j<letter.length;j++)
				{
					if(letter[j]<'a')
						letter[j]+=32;
				}     //将字段全部转化为小写字母
				Field fie = classtype.getDeclaredField(new String(letter));
				fie.setAccessible(true);
				sql.append("'");
				sql.append(fie.get(obj));
				sql.append("'");
			} catch (NoSuchFieldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//System.out.println(sql.toString());
		ResultSet rt = dbm.executeQuery(sql.toString());
		try {
			return rt.first();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public static int delete (Object obj){
		Class<?> classtype= obj.getClass();
		StringBuffer sql = new StringBuffer("DELETE FROM ");
		String tname = classtype.getSimpleName();
		sql.append(tname);
		ArrayList<String> pk = dbm.getPriKey(tname);
		sql.append(" WHERE 1");
		for(String temp:pk)
		{
			sql.append(" AND ");
			sql.append(temp);
			sql.append(" = ");
			try {
				Field fie = classtype.getDeclaredField(temp.toLowerCase());
				fie.setAccessible(true);
				sql.append("'");
				sql.append(fie.get(obj));
				sql.append("'");
			} catch (NoSuchFieldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(sql);
		return dbm.executeUpdate(sql.toString());
	}

	
}
