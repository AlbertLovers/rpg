package domein.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import domein.Aptitude;

public class AptitudeMapper implements Mapper<Aptitude> {

	@Override
	public Aptitude map(ResultSet rs) throws SQLException {
		Aptitude aptitude = new Aptitude();

		aptitude.setId(rs.getLong(1));
		aptitude.setNaam(rs.getString(2));

		return aptitude;
	}
}