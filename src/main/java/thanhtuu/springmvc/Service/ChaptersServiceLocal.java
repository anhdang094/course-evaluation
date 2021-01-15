package thanhtuu.springmvc.Service;

import thanhtuu.springmvc.Domain.Chapters;

public interface ChaptersServiceLocal {

    Chapters getIDByName(String name, Long subjectId, Long teacherId);

}
