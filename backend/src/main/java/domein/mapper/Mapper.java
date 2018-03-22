package domein.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Mapper<T> {
	T map(ResultSet rs) throws SQLException;
}