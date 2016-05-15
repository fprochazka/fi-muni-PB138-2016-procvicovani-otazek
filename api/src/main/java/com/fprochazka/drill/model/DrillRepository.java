package com.fprochazka.drill.model;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

/**
 * Created by Michaela Bamburová on 15.05.2016.
 */
public interface DrillRepository extends CrudRepository<Drill, UUID> {

	/*
	public List<Drill> getAllDrills()
	public Drill getDrillById(UUID drillId )
	public void createDrill(String subject)
	 */
}
