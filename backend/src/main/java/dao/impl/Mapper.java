package dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import domein.Aptitude;
import domein.Karakter;

public interface Mapper<T> {
	T map(ResultSet rs) throws SQLException ;
}

class KarakterMapper implements Mapper<Karakter> {
	public Karakter map(ResultSet resultSet) throws SQLException {
		return new Karakter();
	}
}

class AptitudeMapper implements Mapper<Aptitude> {

	@Override
	public Aptitude map(ResultSet rs) throws SQLException {
		Aptitude aptitude = new Aptitude();
		aptitude.setId(rs.getInt(1));
		aptitude.setNaam(rs.getString(2));
		return aptitude;
	}
	
}