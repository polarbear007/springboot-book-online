package cn.itcast.resource.download.mapper;

import java.util.Set;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface FinishPathMapper {
	
	@Select("SELECT finish_path FROM springboot_book_online.`t_finish_path`;")
	Set<String> getFinishPaths();
	
	@Insert("INSERT INTO springboot_book_online.`t_finish_path` (finish_path) VALUES (#{path})")
	Integer saveFinishPath(String path);
}
