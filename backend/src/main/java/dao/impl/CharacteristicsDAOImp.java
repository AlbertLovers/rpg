package dao.impl;

import java.sql.SQLException;

import domein.Characteristics;

public class CharacteristicsDAOImp {

	private GenericDao<Characteristics> dao = new GenericDao<>();

	public Characteristics getCharacteristic(int id) throws SQLException {
		String query = "SELECT * FROM Characteristics WHERE id = ?";
		return dao.querySingleObject(query, id, new CharacteristicsMapper());
	}
}