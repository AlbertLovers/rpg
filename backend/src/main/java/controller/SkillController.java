package controller;

import java.sql.SQLException;
import java.util.List;

import dao.impl.AptitudeDao;
import domein.Aptitude;
import view.SkillView;

public class SkillController implements Controller {

	private SkillView skillView;

	private AptitudeDao aptitudeDao = new AptitudeDao();

	public SkillController() {
		skillView = new SkillView();
		try {
			List<Aptitude> aptitudes = aptitudeDao.getAptitudes();
			skillView.addAptitudes(aptitudes);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setScene() {
		skillView.setScene();
	}

}
