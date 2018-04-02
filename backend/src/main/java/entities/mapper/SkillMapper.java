package entities.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import dao.Cache;
import entities.Skill;

public class SkillMapper implements Mapper<Skill> {

	@Override
	public Skill map(ResultSet rs) throws SQLException {
		Skill skill = new Skill();

		skill.setId(rs.getLong("id"));
		skill.setAptitude1ById(rs.getLong("aptitude1"));
		skill.setAptitude2ById(rs.getLong("aptitude2"));
		skill.setNaam(rs.getString("naam"));
		skill.setOmschrijving(rs.getString("omschrijving"));

		return skill;
	}

	public static Skill map(Map<String, Object> rs) {
		Skill skill = new Skill();

		skill.setId(rs.get("id") == null ? 0 : Long.parseLong(String.valueOf(rs.get("id"))));
		skill.setAptitude1ById(Cache.getAptitude(String.valueOf(rs.get("aptitude1"))).getId());
		skill.setAptitude2ById(Cache.getAptitude(String.valueOf(rs.get("aptitude2"))).getId());
		skill.setNaam(String.valueOf(rs.get("naam")));
		skill.setOmschrijving(String.valueOf(rs.get("omschrijving")));

		return skill;
	}
}
