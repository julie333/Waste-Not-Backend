package com.project.service;

import com.project.domain.Location;

public interface LocationService {
	
	public void createLocation(Location location);
	
	public Location findById(Long locationId);

	public void deleteById(Long locationId);
			
}
