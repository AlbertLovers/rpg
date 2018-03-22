package dao;

import java.sql.SQLException;
import java.util.List;

import domein.Aptitude;
import domein.mapper.AptitudeMapper;

public class AptitudeDao {

	 GenericDao<Aptitude> dao = new GenericDao<>();
	
	public List<Aptitude> getAptitudes() throws SQLException {
		return dao.queryForList("SELECT id, naam FROM Aptitudes", new AptitudeMapper());
	}
}
