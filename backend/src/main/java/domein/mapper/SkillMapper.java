package domein.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import domein.Skill;

public class SkillMapper implements Mapper<Skill> {

	@Override
	public Skill map(ResultSet rs) throws SQLException {
		Skill skill = new Skill();
		
		skill.setId(rs.getInt("id"));
		skill.setAptitude1Id(rs.getInt("aptitude1_id"));
		skill.setAptitude2Id(rs.getInt("aptitude2_id"));
		skill.setNaam(rs.getString("naam"));
		skill.setOmschrijving(rs.getString("omschrijving"));
		
		return null;
	}

}
