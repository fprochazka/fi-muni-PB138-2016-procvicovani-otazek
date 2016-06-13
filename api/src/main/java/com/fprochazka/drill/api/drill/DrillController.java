package com.fprochazka.drill.api.drill;

import com.fprochazka.drill.model.api.BadRequestException;
import com.fprochazka.drill.model.api.ResourceNotFoundException;
import com.fprochazka.drill.model.drill.Drill;
import com.fprochazka.drill.model.drill.DrillCodeNotUniqueException;
import com.fprochazka.drill.model.drill.DrillFacade;
import com.fprochazka.drill.model.drill.DrillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class DrillController
{

	private DrillFacade drillFacade;
	private DrillRepository drillRepository;
	private DrillResponseFactory drillResponseFactory;

	@Autowired
	public DrillController(
		DrillFacade drillFacade,
		DrillRepository drillRepository,
		DrillResponseFactory drillResponseFactory
	)
	{
		this.drillFacade = drillFacade;
		this.drillRepository = drillRepository;
		this.drillResponseFactory = drillResponseFactory;
	}

	/**
	 * Function, that gives response object of all drills in the database.
	 *
	 * @return response for all found drills
	 */
	@RequestMapping(value = "/drill", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getAllDrills()
	{
		Iterable<Drill> drills = drillRepository.findAll();

		return new HashMap<String, Object>(){{
			put("drills", drillResponseFactory.createDrillsResponse(drills));
		}};
	}

	/**
	 * Function, that returns the drill in database with given ID.
	 * If the ID is not given or do not corresponds to any drill in database, function throws exception.
	 *
	 * @return drill - object of type Drill with given id, null if drill with the id does not exist
	 */
	@RequestMapping(value = "/drill/{drillId}", method = RequestMethod.GET)
	public @ResponseBody DrillResponse getDrillById(
		@PathVariable("drillId") UUID drillId
	) throws ResourceNotFoundException
	{
		Drill drill = drillRepository.getDrillById(drillId);
		if (drill == null) {
			throw new ResourceNotFoundException( "drill-not-found", "Drill with given ID not found." );
		}
		return drillResponseFactory.createDrillResponse(drill);
	}

	/**
	 * Create new drill based on the create drill request
	 * and save it to the database.
	 *
	 * @param createDrillRequest
	 * @return drill response of the new drill
	 */
	@Secured("ROLE_USER")
	@RequestMapping(value = "/drill", method = RequestMethod.POST)
	public @ResponseBody DrillResponse createDrill(@Valid @RequestBody CreateDrillRequest createDrillRequest ) throws BadRequestException
	{
		try {
			Drill drill = drillFacade.createDrill( createDrillRequest.getCode(), createDrillRequest.getName());
			return drillResponseFactory.createDrillResponse(drill);
		} catch (DrillCodeNotUniqueException e) {
			throw new BadRequestException( e, "drill-code-not-unique", "Drill with given code already exists.");
		}
	}
}
