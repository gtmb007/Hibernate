package com.gautam.dao;

import java.util.List;
import java.util.Optional;

import com.gautam.model.Location;

public interface LocationDAO {
	public Integer addLocation(Location loc) throws Exception;
	public Integer updateLocation(Integer locId, Location loc) throws Exception;
	public Integer deleteLocation(Integer locId) throws Exception;
	public Optional<Location> getLocation(Integer locId) throws Exception;
	public Optional<List<Location>> getLocations() throws Exception;
}
