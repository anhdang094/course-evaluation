package thanhtuu.springmvc.Dao;

import thanhtuu.springmvc.Domain.Admin;

public interface AdminMapper {
    int deleteByPrimaryKey(Long id);
    
    int insert(Admin record);
    
    int insertSelective(Admin record);
}