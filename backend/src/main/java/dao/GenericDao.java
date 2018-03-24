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

import annotation.Id;
import annotation.Transient;
import domein.mapper.Mapper;
import utility.ConnectionManager;

public class GenericDao<T> {

	private static final ConnectionManager manager = new ConnectionManager();

	/**
	 * 
	 * @param query
	 * @param mapper die Mapper<T> implementeert
	 * @return Lijst van objecten of een lege lijst
	 * @throws SQLException
	 */
	public List<T> queryForList(String query, Mapper<T> mapper) throws SQLException {
		List<T> result = new ArrayList<>();
		try(ResultSet rs = manager.getConnection().prepareStatement(query).executeQuery()) {
			while(rs.next()) result.add((T) mapper.map(rs));
		}
		return result;
	}

	/**
	 * 
	 * @param query
	 * @param params
	 * @param mapper die Mapper<T> implementeert
	 * @return Lijst van objecten of een lege lijst
	 * @throws SQLException
	 */
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

	/**
	 * 
	 * @param query
	 * @param id
	 * @param mapper die Mapper<T> implementeert
	 * @return Gezochte object of null
	 * @throws SQLException
	 */
	public T querySingleObject(String query, int id, Mapper<T> mapper) throws SQLException {
		try(Connection connection = manager.getConnection(); PreparedStatement statement = connection.prepareStatement(query)){
			statement.setInt(1, id);
			try(ResultSet rs = statement.executeQuery()) {
				if(rs.next()) return (T) mapper.map(rs);
			}
		}

		return null;
	}

	/**
	 * 
	 * @param item
	 * @return id van gegenereerde item
	 */
	public int insertObject(T item) {
		Field[] fields = item.getClass().getDeclaredFields();
		String query = buildInsertQuery(item, fields);
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

	public int updateObject(T item) {
		buildUpdateQuery(item, item.getClass().getDeclaredFields());
		return -1;
	}

	private void populateStatement(PreparedStatement statement, T item, Field[] fields) throws SQLException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		int index = 0;

		for(Field field : fields) {
			if(field.getAnnotationsByType(Transient.class).length != 0 || field.getAnnotationsByType(Id.class).length != 0) continue;
			index++;

			String naam = field.getName();
			Method getter = item.getClass().getDeclaredMethod("get" + Character.toUpperCase(naam.charAt(0)) + naam.substring(1));

			statement.setObject(index, getter.invoke(item));
		}
	}

	private String buildInsertQuery(T item, Field[] fields) {
		StringBuilder vraagtekens = new StringBuilder();
		StringBuilder query = new StringBuilder("INSERT INTO ").append(item.getClass().getSimpleName()).append("(");

		for(int i = 0; i < fields.length; i++ ) {
			Field field = fields[i];
			if(field.getAnnotation(Transient.class) == null && field.getAnnotation(Id.class) == null) {
				query.append(field.getName()).append(",");
				vraagtekens.append("?,");
			}
		}

		vraagtekens.setLength(vraagtekens.length() - 1);

		query.setLength(query.length() - 1);
		query.append(") VALUES (").append(vraagtekens.toString()).append(");");

		return query.toString();
	}

	private String buildUpdateQuery(T item, Field[] fields) {
		StringBuilder query = new StringBuilder("UPDATE ").append(item.getClass().getSimpleName()).append(" SET (");
		for(int i = 0; i < fields.length; i++ ) {
			query.append(fields[i].getName()).append(",");
			query.append("=?,");
		}

		query.setLength(query.length() - 1);
		query.append(");");

		return query.toString();
	}
}
