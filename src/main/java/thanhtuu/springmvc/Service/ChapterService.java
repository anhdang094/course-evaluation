package thanhtuu.springmvc.Service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import thanhtuu.springmvc.Dao.ChapterMapper;
import thanhtuu.springmvc.Domain.Chapter;
import thanhtuu.springmvc.Domain.ChapterKey;

import java.util.List;

/**
 * Created by anh.dang on 3/22/2017.
 */
public class ChapterService implements ChapterServiceLocal {
    @Override
    public Chapter findChapterById(int chapterId, int subjectId) {
        SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
        SqlSession sqlSession = sqlMapper.openSession(true);

        ChapterMapper mapper = sqlSession.getMapper(ChapterMapper.class);
        ChapterKey chapterKey = new ChapterKey();
        chapterKey.setId(Long.valueOf(chapterId));
        chapterKey.setSubjectid(subjectId);
        Chapter chapter = mapper.selectByPrimaryKey(chapterKey);
        sqlSession.close();
        return chapter;
    }
}
