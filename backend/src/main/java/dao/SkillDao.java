package dao;

import java.sql.SQLException;
import java.util.List;

import entities.Skill;
import entities.mapper.SkillMapper;

public class SkillDao extends GenericDao<Skill> {

	private GenericDao<Skill> dao = new GenericDao<>();

	public List<Skill> getSkills() throws SQLException {
		String query = "SELECT * FROM Skill";
		return dao.queryForList(query, new SkillMapper());
	}

	public void insertObject(Skill skill) {
		String query = "INSERT INTO Skill (naam, omschrijving, aptitude1Id, aptitude2Id) VALUES "
				+ "(?, ?, ?, ?)";
		
		Object[] params = {
				skill.getNaam(),
				skill.getOmschrijving(),
				skill.getAptitude1().getId(),
				skill.getAptitude2().getId()
		};
		
		dao.insert(query, params);
	}

	public void updateObject(Skill skill) {
		String query = "UPDATE Skill SET naam = ?, omschrijving = ?, aptitude1Id = ?, aptitude2Id = ? WHERE id = ?";		
		Object[] params = {
				skill.getNaam(),
				skill.getOmschrijving(),
				skill.getAptitude1().getId(),
				skill.getAptitude2().getId(),
				skill.getId()
		};
		
		dao.update(query, params);
	}

}
