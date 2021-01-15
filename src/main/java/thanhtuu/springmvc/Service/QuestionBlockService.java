package thanhtuu.springmvc.Service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import thanhtuu.springmvc.Dao.Question_BlocksMapper;
import thanhtuu.springmvc.Domain.Question_Blocks;
import thanhtuu.springmvc.Domain.Question_BlocksWithBLOBs;

/**
 * Created by Administrator on 9/1/2016.
 */
public class QuestionBlockService implements QuestionBlockServiceLocal {

    @Override
    public void insertBlock(Question_BlocksWithBLOBs questionBlock) {
        // TODO Auto-generated method stub
        SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
        SqlSession sqlSession = sqlMapper.openSession(true);
        Question_BlocksMapper mapper = sqlSession.getMapper(Question_BlocksMapper.class);
        mapper.insert(questionBlock);
        sqlSession.close();
    }


    @Override
    public Long checkMaxIdBlock() {
        // TODO Auto-generated method stub
        SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
        SqlSession sqlSession = sqlMapper.openSession(true);
        Question_BlocksMapper mapper = sqlSession.getMapper(Question_BlocksMapper.class);
        Long value = mapper.checkMaxIdQuestionBlock();
        sqlSession.close();
        return value;
    }
}
