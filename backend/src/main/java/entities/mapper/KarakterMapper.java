package entities.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import entities.Karakter;

public class KarakterMapper implements Mapper<Karakter> {
	public Karakter map(ResultSet resultSet) throws SQLException {
		return new Karakter();
	}
}