package estimate.service;

import java.util.List;

import estimate.model.Spreconn;

public interface SpreconnEjbI {

	Spreconn findById(long disconnectDuration);

	List<Spreconn> getAll();

}
