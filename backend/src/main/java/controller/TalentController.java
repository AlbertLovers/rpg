package controller;

import java.sql.SQLException;
import java.util.List;

import dao.AptitudeDao;
import domein.Aptitude;
import view.TalentView;

public class TalentController implements Controller {

	private TalentView talentView;
	private AptitudeDao aptitudeDao = new AptitudeDao();
	
	public TalentController() {
		talentView = new TalentView();
		try {
			List<Aptitude> aptitudes = aptitudeDao.getAptitudes();
			talentView.addAptitudes(aptitudes);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setScene() {
		talentView.setScene();
	}
	
}
