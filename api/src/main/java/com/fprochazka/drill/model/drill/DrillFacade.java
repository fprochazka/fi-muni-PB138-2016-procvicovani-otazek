package com.fprochazka.drill.model.drill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DrillFacade
{

	private DrillRepository drillRepository;

	@Autowired
	public DrillFacade(DrillRepository drillRepository)
	{
		this.drillRepository = drillRepository;
	}

	public Drill createDrill(String code, String name)
	{
		Drill drill = new Drill(code, name);
		drillRepository.save(drill);
		return drill;
	}
}
