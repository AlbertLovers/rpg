package dao;

import domein.Karakter;

import java.sql.SQLException;
import java.util.List;

public interface KarakterDAO {
   public List<Karakter> getAllKarakters() throws SQLException;
   public Karakter getKarakter(int karakterId) throws SQLException;
   public void newKarakter(Karakter karakter);
   public void updateKarakter(Karakter karakter);
   public void deleteKarakter(Karakter karakter);
}
