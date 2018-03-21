package dao.impl;

import java.sql.SQLException;
import java.util.List;

import dao.KarakterDAO;
import domein.Karakter;

public class KarakterDAOImp implements KarakterDAO {

	private GenericDao<Karakter> karakterDao = new GenericDao<>();

	private static final String KLANT_QUERY = "SELECT k.id, voornaam, achternaam, tussenvoegsel, adres_id, geboortedatum "
			+ "FROM klant k "
			+ "INNER JOIN persoon p ON p.id = k.persoon_id "
			+ "WHERE k.id = ?";

	private static final String ALLE_KLANTEN_QUERY = "SELECT k.id, voornaam, achternaam, tussenvoegsel, adres_id, geboortedatum "
			+ "FROM klant k "
			+ "INNER JOIN persoon p on p.id = k.persoon_id";

	@Override
	public List<Karakter> getAllKarakters() throws SQLException {
		return karakterDao.queryForList(ALLE_KLANTEN_QUERY, new KarakterMapper());
	}

	@Override
	public Karakter getKarakter(int karakterId) throws SQLException {
		return karakterDao.querySingleObject(KLANT_QUERY, karakterId, new KarakterMapper());
	}

	@Override
	public void newKarakter(Karakter karakter) {
		// implementeren
	}

	@Override
	public void updateKarakter(Karakter karakter) {
		// implementeren
	}

	@Override
	public void deleteKarakter(Karakter karakter) {
		// implementeren
	}
}