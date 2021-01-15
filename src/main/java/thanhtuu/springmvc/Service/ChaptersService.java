package thanhtuu.springmvc.Service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import thanhtuu.springmvc.Dao.ChaptersMapper;
import thanhtuu.springmvc.Domain.Chapters;

public class ChaptersService implements ChaptersServiceLocal {

    @Override
    public Chapters getIDByName(String name, Long subjectId, Long teacherId) {
        SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
        SqlSession sqlSession = sqlMapper.openSession(true);
        ChaptersMapper mapper = sqlSession.getMapper(ChaptersMapper.class);
        Chapters chapters = mapper.getIdFromName(name, subjectId, teacherId);
        sqlSession.close();
        return chapters;
    }
}
