package com.fprochazka.drill.api.drill;

import com.fprochazka.drill.model.drill.Drill;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class DrillResponseFactory
{

	public DrillResponse createDrillResponse(Drill drill)
	{
		return new DrillResponse(drill.getId(), drill.getName());
	}

	public Collection<DrillResponse> createDrillsResponse(Iterable<Drill> drills)
	{
		return StreamSupport
			.stream(drills.spliterator(), false)
			.map(this::createDrillResponse)
			.collect(Collectors.toList());
	}
}
