package domein.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import dao.Cache;
import domein.Skill;

public class SkillMapper implements Mapper<Skill> {

	@Override
	public Skill map(ResultSet rs) throws SQLException {
		Skill skill = new Skill();

		skill.setId(rs.getInt("id"));
		skill.setAptitude1Id(rs.getInt("aptitude1"));
		skill.setAptitude2Id(rs.getInt("aptitude2"));
		skill.setNaam(rs.getString("naam"));
		skill.setOmschrijving(rs.getString("omschrijving"));

		return skill;
	}

	public static Skill map(Map<String, Object> rs) {
		Skill skill = new Skill();

		skill.setId(rs.get("id") == null ? 0 : Integer.parseInt(String.valueOf(rs.get("id"))));
		skill.setAptitude1Id(Cache.getAptitude(String.valueOf(rs.get("aptitude1"))).getId());
		skill.setAptitude2Id(Cache.getAptitude(String.valueOf(rs.get("aptitude2"))).getId());
		skill.setNaam(String.valueOf(rs.get("naam")));
		skill.setOmschrijving(String.valueOf(rs.get("omschrijving")));

		return skill;
	}
}
