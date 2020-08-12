package com.gautam.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gautam.dao.LocationDAO;
import com.gautam.model.Location;

@Service("locationService")
public class LocationServiceImpl implements LocationService {

	@Autowired
	LocationDAO locationDao;

	@Override
	public Integer addLocation(Location loc) throws Exception {
		Integer locId=locationDao.addLocation(loc);
		if(locId==null) throw new Exception("Location Not Added!");
		else return locId;
	}
	
	@Override
	public Integer updateLocation(Integer locId, Location loc) throws Exception {
		Integer locid=locationDao.updateLocation(locId, loc);
		if(locid==null) throw new Exception("Location Not Updated!");
		else return locid;
	}

	@Override
	public Integer deleteLocation(Integer locId) throws Exception {
		Integer locid=locationDao.deleteLocation(locId);
		if(locid==null) throw new Exception("Location Not Deleted!");
		else return locid;
	}

	@Override
	public Optional<Location> getLocation(Integer locId) throws Exception {
		return locationDao.getLocation(locId);
		
	}

	@Override
	public Optional<List<Location>> getLocations() throws Exception {
		return locationDao.getLocations();
	}

}
