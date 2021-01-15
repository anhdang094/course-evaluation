package thanhtuu.springmvc.Service;

import thanhtuu.springmvc.Domain.Chapter;

/**
 * Created by anh.dang on 3/22/2017.
 */
public interface ChapterServiceLocal {

    Chapter findChapterById(int chapterId, int subjectId);
}
