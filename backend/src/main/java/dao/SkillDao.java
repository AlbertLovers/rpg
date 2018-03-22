package dao;

import java.sql.SQLException;
import java.util.List;

import domein.Skill;
import domein.mapper.SkillMapper;

public class SkillDao extends GenericDao<Skill> {

	private GenericDao<Skill> dao = new GenericDao<>();

	public List<Skill> getSkills() throws SQLException {
		String query = "SELECT * FROM Skill";
		return dao.queryForList(query, new SkillMapper());
	}

}
