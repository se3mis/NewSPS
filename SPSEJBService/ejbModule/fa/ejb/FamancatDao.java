package fa.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.PersistenceException;

import fa.model.Famancat;

/**
 * Session Bean implementation class FamancatDao
 */
@Stateless
public class FamancatDao implements FamancatDaoRemote, FamancatDaoLocal {

    /**
     * Default constructor. 
     */
    public FamancatDao() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void createFamancat(Famancat famancat, String webAppName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateFamancat(Famancat famancat, String webAppName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeFamancat(Famancat famancat, String webAppName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Famancat> getAll(String webAppName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeAll(String webAppName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Famancat findById(String catCode, String webAppName)
			throws PersistenceException {
		// TODO Auto-generated method stub
		return null;
	}

}
