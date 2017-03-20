package com.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.domain.Location;
import com.project.repository.LocationRepository;

@Transactional(readOnly = true)
@Service
public class DefaultLocationService implements LocationService {

	private final LocationRepository locationRepository;

	@Autowired
	public DefaultLocationService(LocationRepository locationRepository) {
		super();
		this.locationRepository = locationRepository;
	}

	@Override
	public void createLocation(Location location) {

		this.locationRepository.save(location);

	}

	@Override
	public Location findById(Long locationId) {

		return this.locationRepository.findOne(locationId);
	}

	@Override
	public void deleteById(Long locationId) {

		this.locationRepository.delete(locationId);
	}

}
