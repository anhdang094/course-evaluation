package thanhtuu.springmvc.Service;

import java.util.List;
import thanhtuu.springmvc.Domain.Subject;

public interface SubjectsServiceLocal {
	
    List<Subject> getAllSubjectByStudentId(long userId);
    
    List<Subject> getSubjectInList(List<Integer> subjectIdList);    
}
