package dao;

import java.sql.SQLException;

import entities.Characteristics;
import entities.mapper.CharacteristicsMapper;

public class CharacteristicsDao {

	private GenericDao<Characteristics> dao = new GenericDao<>();

	public Characteristics getCharacteristic(Long id) throws SQLException {
		String query = "SELECT * FROM Characteristics WHERE id = ?";
		return dao.querySingleObject(query, id, new CharacteristicsMapper());
	}

	public Long insertCharacteristics(Characteristics item) {
		String query = "INSERT INTO Characteristics "
				+ "(weaponSkill, ballisticSkill, strength, toughness, agility, intelligene, perception, willpower, fellowship) "
				+ "VALUES (?,?,?,?,?,?,?,?,?)";

		Object[] params = { item.getWeaponSkill(), item.getBallisticSkill(), item.getStrength(), item.getToughness(), item.getAgility(),
				item.getIntelligence(), item.getPerception(), item.getWillpower(), item.getFellowship() };

		return dao.insert(query, params);
	}
}
