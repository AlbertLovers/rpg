package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
}
