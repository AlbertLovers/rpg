package dao;

import java.sql.SQLException;

import domein.Karakter;

public class KarakterDao {

	private GenericDao<Karakter> dao = new GenericDao<>();

	public Karakter fetchKatakter(Long id) {
		String query = "SELECT * FROM Karakter WHERE id = ?";
		
		try {
			return dao.querySingleObject(query, id, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}