package thanhtuu.springmvc.Dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import thanhtuu.springmvc.Domain.Question_Blocks;
import thanhtuu.springmvc.Domain.Question_BlocksWithBLOBs;

public interface Question_BlocksMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table question_blocks
     *
     * @mbggenerated Sat Mar 04 16:46:20 ICT 2017
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table question_blocks
     *
     * @mbggenerated Sat Mar 04 16:46:20 ICT 2017
     */
    int insert(Question_BlocksWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table question_blocks
     *
     * @mbggenerated Sat Mar 04 16:46:20 ICT 2017
     */
    int insertSelective(Question_BlocksWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table question_blocks
     *
     * @mbggenerated Sat Mar 04 16:46:20 ICT 2017
     */
    Question_BlocksWithBLOBs selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table question_blocks
     *
     * @mbggenerated Sat Mar 04 16:46:20 ICT 2017
     */
    int updateByPrimaryKeySelective(Question_BlocksWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table question_blocks
     *
     * @mbggenerated Sat Mar 04 16:46:20 ICT 2017
     */
    int updateByPrimaryKeyWithBLOBs(Question_BlocksWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table question_blocks
     *
     * @mbggenerated Sat Mar 04 16:46:20 ICT 2017
     */
    int updateByPrimaryKey(Question_Blocks record);
    
     List<Question_BlocksWithBLOBs> getQuestionBlockInList(@Param("list")List<Integer> list);
    
    List<Question_BlocksWithBLOBs> getBlockQuestionByKeyWord(@Param("list")List<Integer> list);
    
    List<Question_BlocksWithBLOBs> getSingleQuestion();
    
    Question_BlocksWithBLOBs selectRootQuestionBlockId(@Param("id") long id);
    
    Question_BlocksWithBLOBs getBlockQuestionByID(@Param("id") long id);
    
    Question_BlocksWithBLOBs getMaxIdBlock();
}