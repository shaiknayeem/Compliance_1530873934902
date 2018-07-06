package com.compliance.db.compliance_db.service.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

import com.compliance.db.compliance_db.entity.Compliance;
import com.compliance.db.compliance_db.service.ComplianceService;

//IMPORT RELATIONS

@Service
public class ComplianceBaseService {

	private static NamedParameterJdbcTemplate jdbcTemplate;
	
		@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	


    //CRUD METHODS
    
    //CRUD - CREATE
    	
	public Compliance insert(Compliance obj) {
		Long id = jdbcTemplate.queryForObject("select max(_id) from `compliance`", new MapSqlParameterSource(), Long.class);
		obj.set_id(id == null ? 1 : id + 1);
		String sql = "INSERT INTO `compliance` (`_id`, `approvedDate`,`aprroverName`,`compliance_id`,`createdBy`,`createdDate`,`isactive`,`lawType`,`penalty`,`publishedBy`,`publishedOn`,`regulator`,`section`,`status`,`version`) VALUES (:id,:approvedDate,:aprroverName,:compliance_id,:createdBy,:createdDate,:isactive,:lawType,:penalty,:publishedBy,:publishedOn,:regulator,:section,:status,:version)";
			SqlParameterSource parameters = new MapSqlParameterSource()
		    .addValue("id", obj.get_id())
			.addValue("approvedDate", obj.getApprovedDate())
			.addValue("aprroverName", obj.getAprroverName())
			.addValue("compliance_id", obj.getCompliance_id())
			.addValue("createdBy", obj.getCreatedBy())
			.addValue("createdDate", obj.getCreatedDate())
			.addValue("isactive", obj.getIsactive())
			.addValue("lawType", obj.getLawType())
			.addValue("penalty", obj.getPenalty())
			.addValue("publishedBy", obj.getPublishedBy())
			.addValue("publishedOn", obj.getPublishedOn())
			.addValue("regulator", obj.getRegulator())
			.addValue("section", obj.getSection())
			.addValue("status", obj.getStatus())
			.addValue("version", obj.getVersion());
		
		jdbcTemplate.update(sql, parameters);
		return obj;
	}
	
    	
    //CRUD - REMOVE
    
	public void delete(Long id) {
		String sql = "DELETE FROM `Compliance` WHERE `_id`=:id";
		SqlParameterSource parameters = new MapSqlParameterSource()
			.addValue("id", id);
		
		jdbcTemplate.update(sql, parameters);
	}

    	
    //CRUD - GET ONE
    	
	public Compliance get(Long id) {
	    
		String sql = "select * from `Compliance` where `_id` = :id";
		
	    SqlParameterSource parameters = new MapSqlParameterSource()
			.addValue("id", id);
	    
	    return (Compliance) jdbcTemplate.queryForObject(sql, parameters, new BeanPropertyRowMapper(Compliance.class));
	}


    	
        	
    //CRUD - GET LIST
    	
	public List<Compliance> getList() {
	    
		String sql = "select * from `Compliance`";
		
	    SqlParameterSource parameters = new MapSqlParameterSource();
	    return jdbcTemplate.query(sql, parameters, new BeanPropertyRowMapper(Compliance.class));
	    
	    
	}

    	
    //CRUD - EDIT
    	
	public Compliance update(Compliance obj, Long id) {

		String sql = "UPDATE `Compliance` SET `approvedDate` = :approvedDate,`aprroverName` = :aprroverName,`compliance_id` = :compliance_id,`createdBy` = :createdBy,`createdDate` = :createdDate,`isactive` = :isactive,`lawType` = :lawType,`penalty` = :penalty,`publishedBy` = :publishedBy,`publishedOn` = :publishedOn,`regulator` = :regulator,`section` = :section,`status` = :status,`version` = :version  WHERE `_id`=:id";
		SqlParameterSource parameters = new MapSqlParameterSource()
			.addValue("id", id)
			.addValue("approvedDate", obj.getApprovedDate())
			.addValue("aprroverName", obj.getAprroverName())
			.addValue("compliance_id", obj.getCompliance_id())
			.addValue("createdBy", obj.getCreatedBy())
			.addValue("createdDate", obj.getCreatedDate())
			.addValue("isactive", obj.getIsactive())
			.addValue("lawType", obj.getLawType())
			.addValue("penalty", obj.getPenalty())
			.addValue("publishedBy", obj.getPublishedBy())
			.addValue("publishedOn", obj.getPublishedOn())
			.addValue("regulator", obj.getRegulator())
			.addValue("section", obj.getSection())
			.addValue("status", obj.getStatus())
			.addValue("version", obj.getVersion());
		jdbcTemplate.update(sql, parameters);
	    return obj;
	}
	
    	
    
    


    
    /*
     * CUSTOM SERVICES
     * 
     *	These services will be overwritten and implemented in ComplianceService.java
     */
    

}
