package estimate.service;

import java.util.List;

import estimate.model.Spcrconv;
import estimate.model.SpcrconvPK;

public interface SpcrconvEjbI {

	Spcrconv findById(SpcrconvPK id);

	List<Spcrconv> getAll();

}
