package dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import domein.Aptitude;
import domein.Characteristics;
import domein.Karakter;

public interface Mapper<T> {
	T map(ResultSet rs) throws SQLException;
}

class KarakterMapper implements Mapper<Karakter> {
	public Karakter map(ResultSet resultSet) throws SQLException {
		return new Karakter();
	}
}

class AptitudeMapper implements Mapper<Aptitude> {

	@Override
	public Aptitude map(ResultSet rs) throws SQLException {
		Aptitude aptitude = new Aptitude();

		aptitude.setId(rs.getInt(1));
		aptitude.setNaam(rs.getString(2));

		return aptitude;
	}
}

// Voor de sier, want nu hoef ik nooit meer een Characteristicsmapper te schrijven
// hoewel dit natuurlijk wel een stuk langzamer is, maar geen high throughput applicatie
// dus who cares
class CharacteristicsMapper implements Mapper<Characteristics> {
	private static Map<String, String> setters = new HashMap<>();

	@Override
	public Characteristics map(ResultSet rs) throws SQLException {
		Characteristics characteristics = new Characteristics();
		ResultSetMetaData meta = rs.getMetaData();

		for (int i = 1; i <= meta.getColumnCount(); i++) {
			try {
				String column = meta.getColumnLabel(i);
				String setter;

				if(setters.containsKey(column)) {
					setter = setters.get(column);
				} else {
					setter = "set" + Character.toUpperCase(column.charAt(0)) + column.substring(1, column.length());
				}

				Characteristics.class.getMethod(setter, int.class).invoke(characteristics, rs.getInt(i));
			} catch (IllegalArgumentException | IllegalAccessException | SecurityException | InvocationTargetException | NoSuchMethodException e) {
				e.printStackTrace();
			}
		}

		return characteristics;
	}
}