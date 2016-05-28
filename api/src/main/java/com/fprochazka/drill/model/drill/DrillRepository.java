package com.fprochazka.drill.model.drill;

import com.fprochazka.drill.model.drill.Drill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DrillRepository extends CrudRepository<Drill, UUID> {

	/*
	public List<Drill> getAllDrills()
	public Drill getDrillById(UUID drillId )
	public void createDrill(String subject)
	 */
}
