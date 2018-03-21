package dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import domein.Characteristics;

public class CharacteristicsDAOImp {

	private GenericDao<Characteristics> dao = new GenericDao<>();
	
	public Characteristics getCharacteristic(int id) throws SQLException {
		String query = "SELECT * FROM Characteristics WHERE id = ?";
		return dao.querySingleObject(query, id, new Mapper<Characteristics>() {

			@Override
			public Characteristics map(ResultSet rs) throws SQLException {
				Characteristics characteristics = new Characteristics();
				ResultSetMetaData meta = rs.getMetaData();
				
				for(int i = 1; i <= meta.getColumnCount(); i++) {
					try {
						Method[] methods = Characteristics.class.getDeclaredMethods();
						for(Method m : methods) {
							if(m.getName().startsWith("set") && m.getName().toLowerCase().contains(meta.getColumnLabel(i))) {
								m.invoke(characteristics, rs.getInt(i));
								break;
							}
						}
					} catch (IllegalArgumentException | IllegalAccessException | SecurityException | InvocationTargetException e) {
						e.printStackTrace();
					}
				}

				return characteristics;
			}			
		});
	}
}