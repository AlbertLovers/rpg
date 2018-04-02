package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.mapper.Mapper;
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
	public T querySingleObject(String query, Long id, Mapper<T> mapper) throws SQLException {
		try(Connection connection = manager.getConnection(); PreparedStatement statement = connection.prepareStatement(query)){
			statement.setLong(1, id);
			try(ResultSet rs = statement.executeQuery()) {
				if(rs.next()) return (T) mapper.map(rs);
			}
		}

		return null;
	}
}
