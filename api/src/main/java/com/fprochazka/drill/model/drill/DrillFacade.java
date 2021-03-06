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

	public Drill createDrill(String code, String name) throws DrillCodeNotUniqueException
	{
		Drill drill = new Drill(code, name);
		if (drillRepository.getDrillByCode(code) != null) {
			throw new DrillCodeNotUniqueException();
		}
		drillRepository.save(drill);

		return drill;
	}
}
