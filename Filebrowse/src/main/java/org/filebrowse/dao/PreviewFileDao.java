package org.filebrowse.dao;


import java.util.Date;
import java.util.List;

import org.filebrowse.entity.PreviewFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class PreviewFileDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private final static RowMapper<PreviewFile> rowMapper=new BeanPropertyRowMapper<PreviewFile>(PreviewFile.class);
	
	public int insertOne(PreviewFile file){
		String sql="insert into PreviewFile(file_name,create_time,location,type) values(?,?,?,?)";
		
		return jdbcTemplate.update(sql, file.getFileName(),file.getCreateTime(),file.getLocation(),file.getType());
	}
	
	public List<PreviewFile> getAllByOrder(){
		
		String sql="select * from PreviewFile order by create_time desc";
		
		return jdbcTemplate.query(sql, rowMapper);
	}
	
	public List<PreviewFile> getListByType(int type){
	    String sql = "select * from PreviewFile where type=? order by create_time desc";
	    return jdbcTemplate.query(sql, rowMapper, type);
	}
	
	public List<PreviewFile> getByNameAndDate(String name,Date date){
	    String sql = "select * from PreviewFile where file_name=? and create_time=?";
	    return jdbcTemplate.query(sql, rowMapper, name,date);
	}
	
	public List<PreviewFile> getByLocation(String location){
		String sql="select * from PreviewFile where location = ?";
		return jdbcTemplate.query(sql, rowMapper, location);
	}
	
	public int updateByLocation(String location,Date date){
		String sql="update PreviewFile set create_time = ? where location = ?";
		return jdbcTemplate.update(sql, date, location);
	}
	
	public List<PreviewFile> getByNameLike(String str){
	    String param="%"+str+"%";
	    System.out.println(param);
	    String sql="select * from PreviewFile where file_name like ? order by create_time desc";
	    return jdbcTemplate.query(sql, rowMapper, param);
	}

	
	
//	public List<PreviewFile> getListByName(String name){
//		String sql = "select * from PreviewFile where file_name like '%?%'";
//		return jdbcTemplate.query(sql, rowMapper, name);
//	}
//	
//	public List<PreviewFile> getListByTime(Date time){
//		String sql ="select * from PreviewFile where time like '%?%'";
//		return jdbcTemplate.query(sql, rowMapper, time);
//	}
//	
//	public List<PreviewFile> getListByLevel(Integer level){
//		String sql="select * from PreviewFile where level=?";
//		return jdbcTemplate.query(sql, rowMapper, level);
//	}
	
	
	
}
