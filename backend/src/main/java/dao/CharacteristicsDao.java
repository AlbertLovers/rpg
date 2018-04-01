package dao;

import java.sql.SQLException;

import domein.Characteristics;
import domein.mapper.CharacteristicsMapper;

public class CharacteristicsDao {

	private GenericDao<Characteristics> dao = new GenericDao<>();

	public Characteristics getCharacteristic(Long id) throws SQLException {
		String query = "SELECT * FROM Characteristics WHERE id = ?";
		return dao.querySingleObject(query, id, new CharacteristicsMapper());
	}
	
	public int insertCharacteristics(Characteristics characteristics) {
		return dao.insertObject(characteristics);
	}
}