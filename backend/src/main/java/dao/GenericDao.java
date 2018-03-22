package dao;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import annotation.Transient;
import domein.mapper.Mapper;
import utility.ConnectionManager;

public class GenericDao<T> {

	private static final ConnectionManager manager = new ConnectionManager();

	public List<T> queryForList(String query, Mapper<T> mapper) throws SQLException {
		List<T> result = new ArrayList<>();
		try(ResultSet rs = manager.getConnection().prepareStatement(query).executeQuery()) {
			while(rs.next()) result.add((T) mapper.map(rs));
		}
		return result;
	}

	public List<T> queryForList(String query, Object[] params, Mapper<T> mapper) throws SQLException {
		List<T> result = new ArrayList<>();
		try (PreparedStatement statement = manager.getConnection().prepareStatement(query)) {
			for(int i = 0; i < params.length; i++) statement.setObject(i + 1, params[i]);
			try(ResultSet rs = statement.executeQuery()) {
				while(rs.next()) result.add((T) mapper.map(rs));
			}
		}

		return result;
	}

	public T querySingleObject(String query, int id, Mapper<T> mapper) throws SQLException {
		try(Connection connection = manager.getConnection(); PreparedStatement statement = connection.prepareStatement(query)){
			statement.setInt(1, id);
			try(ResultSet rs = statement.executeQuery()) {
				if(rs.next()) return (T) mapper.map(rs);
			}
		}

		return null;
	}

	public int insertObject(T item) {
		Field[] fields = item.getClass().getDeclaredFields();
		String query = buildQuery(item, fields);
		try (PreparedStatement statement = manager.getConnection().prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
			populateStatement(statement, item, fields);
			statement.executeUpdate();

			try(ResultSet rs = statement.getGeneratedKeys()) {
				rs.next();
				return rs.getInt(1);
			}

		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | SQLException e) {
			e.printStackTrace();
		}

		return -1;
	}

	private void populateStatement(PreparedStatement statement, T item, Field[] fields) throws SQLException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		int length = fields.length;

		for(int i = 0; i < length; i++) {
			Field field = fields[i];
			
			if(field.getAnnotationsByType(Transient.class) != null) continue;
			
			String naam = field.getName();
			Method getter = item.getClass().getDeclaredMethod("get" + Character.toUpperCase(naam.charAt(0)) + naam.substring(1));

			Object value = getter.invoke(item);
			if("id".equals(naam) && "0".equals(value.toString())) {
				statement.setObject(i + 1, null);
			} else {
				statement.setObject(i + 1, value);
			}
		}
	}

	private String buildQuery(T item, Field[] fields) {
		StringBuilder vraagtekens = new StringBuilder();
		StringBuilder query = new StringBuilder("INSERT INTO ").append(item.getClass().getSimpleName()).append("(");
		for(int i = 0; i < fields.length; i++ ) {
			query.append(fields[i].getName()).append(",");
			vraagtekens.append("?,");
		}
		vraagtekens.setLength(vraagtekens.length() - 1);

		query.setLength(query.length() - 1);
		query.append(") VALUES (").append(vraagtekens.toString()).append(");");

		return query.toString();
	}
}
