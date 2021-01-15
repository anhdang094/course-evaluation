package thanhtuu.springmvc.Dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import thanhtuu.springmvc.Domain.Users;

public interface UsersMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Users record);
    
    int insertSelective(Users record);
    
    Users selectByPrimaryKey(Long id);
    
    int updateByPrimaryKeySelective(Users record);
    
    int updateByPrimaryKey(Users record);
    
    Users getEmail(@Param("email")String email);
    
    List<Users> getUserOfList(@Param("list") List<Long> list);
}