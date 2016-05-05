package job.service;

import java.util.List;

import job.model.Spestjad;
import job.model.SpestjadPK;

public interface SpestjadEjbI {
	List<Spestjad> getAll (String deptId);
	Spestjad findById(SpestjadPK id);

}
