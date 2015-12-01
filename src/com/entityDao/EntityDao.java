package com.entityDao;

import java.util.List;

public interface EntityDao {
	public Object insert(final Object model);
	public void delete(final Object model);
	public void update(final Object model);
	public List<?> select (final String hql);
}
