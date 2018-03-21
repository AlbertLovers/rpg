package dao.impl;

import java.sql.SQLException;
import java.util.List;

import domein.Aptitude;

public class AptitudeDao extends GenericDao<Aptitude> {

	public List<Aptitude> getAptitudes() throws SQLException {
		return queryForList("SELECT id, naam FROM Aptitudes", new AptitudeMapper());
	}
}
