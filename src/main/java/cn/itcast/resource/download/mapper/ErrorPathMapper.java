package cn.itcast.resource.download.mapper;

import java.util.Set;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ErrorPathMapper {
	@Select("SELECT error_path FROM springboot_book_online.`t_error_path`")
	Set<String> getErrorPaths();
	
	@Insert("INSERT INTO springboot_book_online.`t_error_path` (error_path) values (#{path})")
	Integer saveErrorPath(String path);
}
