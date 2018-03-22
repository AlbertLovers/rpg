package domein.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import domein.Karakter;

public class KarakterMapper implements Mapper<Karakter> {
	public Karakter map(ResultSet resultSet) throws SQLException {
		return new Karakter();
	}
}