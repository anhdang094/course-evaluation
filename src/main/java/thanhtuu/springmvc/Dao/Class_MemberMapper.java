package thanhtuu.springmvc.Dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import thanhtuu.springmvc.Domain.Class_Member;
import thanhtuu.springmvc.Domain.Class_MemberKey;

public interface Class_MemberMapper {
    int deleteByPrimaryKey(Class_MemberKey key);
    
    int insert(Class_Member record);
    
    int insertSelective(Class_Member record);
    
    Class_Member selectByPrimaryKey(Class_MemberKey key);
    
    int updateByPrimaryKeySelective(Class_Member record);
    
    int updateByPrimaryKey(Class_Member record);
    
    List<Class_Member> getStudentOfClass(@Param("classId") int classId);
    
    List<Class_Member> getClassOfStudent(@Param("studentId") long studentId);
    
    Class_Member getClassMemberList(@Param("studentId") long studentId, @Param("list") List<Integer>list);
    
    Long getStudentCountByClassId(@Param("classId") long classId);
    
    int removeBySubjectId(Class_Member record);
    
    int removeByClassId(Class_Member record);
}