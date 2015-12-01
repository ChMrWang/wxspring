package dbpackge1_0;

import java.sql.ResultSet;
import java.util.HashSet;

public interface JdbcOrm
{
	public String toSql(); 
	public HashSet<JdbcOrm> toObject(ResultSet rt);
}
