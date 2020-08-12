package com.gautam.util;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class CustomPhysicalNamingStrategy extends PhysicalNamingStrategyStandardImpl {

	
	@Override
	public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment jdbcEnvironment) {
		// TODO Auto-generated method stub
		Identifier identifier = Identifier.toIdentifier(name.getText()+"_tbl");
		return identifier;
	}

	
	@Override
	public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment jdbcEnvironment) {
		// TODO Auto-generated method stub
		return convertToSnakeCase(name);
	}
	
	
	private Identifier convertToSnakeCase(final Identifier identifier) {
		final String regex = "([a-z])([A-Z])";
		final String replacement="$1_$2";
		
		final String newName = identifier.getText().replaceAll(regex, replacement).toLowerCase();
		System.out.println(newName);
		return Identifier.toIdentifier(newName);
	}

}

