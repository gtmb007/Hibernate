package com.gautam.dao;

import java.util.List;
import java.util.Optional;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gautam.model.Location;

@Repository("locationDao")
public class LocationDAOImpl implements LocationDAO {
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Transactional
	@Override
	public Integer addLocation(Location loc) throws Exception {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(loc);
			return loc.getLocId();
		} catch(HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Transactional
	@Override
	public Integer updateLocation(Integer locId, Location loc) throws Exception {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(loc);
			return loc.getLocId();
		} catch(HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Transactional
	@Override
	public Integer deleteLocation(Integer locId) throws Exception {
		Location loc=sessionFactory.getCurrentSession().get(Location.class, locId);
		if(loc!=null) {
			sessionFactory.getCurrentSession().delete(loc);
			return locId;
		} else {
			return null;
		}
	}

	@Transactional(readOnly=true)
	@Override
	public Optional<Location> getLocation(Integer locId) throws Exception {
		Location loc=sessionFactory.getCurrentSession().get(Location.class, locId);
		if(loc==null) return Optional.empty();
		else return Optional.ofNullable(loc);
	}

	@Transactional(readOnly=true)
	@Override
	public Optional<List<Location>> getLocations() throws Exception {
		String queryString="select l from Location l";
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery(queryString);
		List<Location> locs=query.getResultList();
		if(locs.isEmpty()) return Optional.empty();
		return Optional.ofNullable(locs);
	}

}
