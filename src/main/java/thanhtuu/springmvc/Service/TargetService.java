package thanhtuu.springmvc.Service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import thanhtuu.springmvc.Dao.TargetMapper;
import thanhtuu.springmvc.Domain.Target;

import java.util.List;

/**
 * Created by Administrator on 9/3/2016.
 */
public class TargetService implements TargetServiceLocal {
    @Override
    public void insertTarget(Target target) {
        // TODO Auto-generated method stub
        SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
        SqlSession sqlSession = sqlMapper.openSession(true);
        TargetMapper mapper = sqlSession.getMapper(TargetMapper.class);
        mapper.insert(target);
        sqlSession.close();
    }

    @Override
    public Target getIDByName(String name, Long subjectId) {
        SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
        SqlSession sqlSession = sqlMapper.openSession(true);
        TargetMapper mapper = sqlSession.getMapper(TargetMapper.class);
        Target target = mapper.getIdFromName(name, subjectId);
        sqlSession.close();
        return target;
    }

    @Override
    public List<Target> getAllTarget(Long subjectId) {
        SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
        SqlSession sqlSession = sqlMapper.openSession(true);
        TargetMapper mapper = sqlSession.getMapper(TargetMapper.class);
        List<Target> target = mapper.getAllTarget(subjectId);
        sqlSession.close();
        return target;
    }
}
