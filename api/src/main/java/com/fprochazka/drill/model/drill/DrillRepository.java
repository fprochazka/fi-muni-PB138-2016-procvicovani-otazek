package com.fprochazka.drill.model.drill;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DrillRepository extends CrudRepository<Drill, UUID>
{

	Drill getDrillById(UUID drillId);

	Drill getDrillByCode(String code);

}
