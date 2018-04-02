package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entities.Characteristics;
import entities.Karakter;
import entities.Skill;
import entities.Talent;
import utility.ConnectionManager;

public class KarakterDao {

	private GenericDao<Karakter> dao = new GenericDao<>();
	private ConnectionManager connectionManager = new ConnectionManager();

	public Karakter fetchKatakter(Long id) {
		String query = "SELECT * FROM Karakter WHERE id = ?";

		try {
			return dao.querySingleObject(query, id, null);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void insertCharacter(Karakter karakter) {
		String karakterQuery = "INSERT INTO Karakter (naam, omschrijving, persoonlijkheid) VALUES (?, ?, ?)";
		Connection con = null;
		try {
			con = connectionManager.getConnection();
			con.setAutoCommit(false);

			try(PreparedStatement insertKarakterStatement = con.prepareStatement(karakterQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
				insertKarakterStatement.setString(1, karakter.getNaam());
				insertKarakterStatement.setString(2, karakter.getOmschrijving());
				insertKarakterStatement.setString(2, karakter.getPersoonlijkheid());
				insertKarakterStatement.executeUpdate();

				ResultSet rs = insertKarakterStatement.getGeneratedKeys();
				rs.next();
				karakter.setId(rs.getLong(1));
			}

			String characteristicsQuery = "INSERT INTO Characteristics "
					+ "(weaponSkill, ballisticSkill, strength, toughness, agility, intelligence, perception, willpower, fellowship "
					+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
			try(PreparedStatement insertCharacteristicsStatement = con.prepareStatement(characteristicsQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
				Characteristics characteristics = karakter.getCharacteristics();
				insertCharacteristicsStatement.setInt(1, characteristics.getWeaponSkill());
				insertCharacteristicsStatement.setInt(2, characteristics.getBallisticSkill());
				insertCharacteristicsStatement.setInt(3, characteristics.getStrength());
				insertCharacteristicsStatement.setInt(4, characteristics.getToughness());
				insertCharacteristicsStatement.setInt(5, characteristics.getAgility());
				insertCharacteristicsStatement.setInt(6, characteristics.getIntelligence());
				insertCharacteristicsStatement.setInt(7, characteristics.getPerception());
				insertCharacteristicsStatement.setInt(8, characteristics.getWillpower());
				insertCharacteristicsStatement.setInt(9, characteristics.getFellowship());
				insertCharacteristicsStatement.executeUpdate();

				ResultSet rs = insertCharacteristicsStatement.getGeneratedKeys();
				rs.next();
				Long id = rs.getLong(1);

				try(PreparedStatement setCharacteristicsId = con.prepareStatement("UPDATE Karakter SET characteristics_id = ? WHERE id = ?")) {
					setCharacteristicsId.setLong(1, id);
					setCharacteristicsId.setLong(2, karakter.getId());
					setCharacteristicsId.executeUpdate();
				}
			}

			for(Skill skill : karakter.getSkills()) {
				String skillQuery = "INSERT INTO Karakter_Skill (karakter_id, skill_id, level) VALUES (?, ?, ?)";
				try(PreparedStatement addSkill = con.prepareStatement(skillQuery)) {
					addSkill.setLong(1, karakter.getId());
					addSkill.setLong(2, skill.getId());
					addSkill.setInt(3, skill.getLevel());
					addSkill.executeUpdate();
				}
			}

			for(Talent talent : karakter.getTalents()) {
				String skillQuery = "INSERT INTO Karakter_Talent (karakter_id, talent_id) VALUES (?, ?)";
				try(PreparedStatement addSkill = con.prepareStatement(skillQuery)) {
					addSkill.setLong(1, karakter.getId());
					addSkill.setLong(2, talent.getId());
					addSkill.executeUpdate();
				}
			}

			con.commit();

		} catch(SQLException e) {
			try {
				con.rollback();
				con.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}