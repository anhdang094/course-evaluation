package thanhtuu.springmvc.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import thanhtuu.springmvc.Dao.AnswersMapper;
import thanhtuu.springmvc.Dao.ExamMapper;
import thanhtuu.springmvc.Dao.Exam_Question_BlockMapper;
import thanhtuu.springmvc.Dao.ExamsMapper;
import thanhtuu.springmvc.Dao.Question_BlocksMapper;
import thanhtuu.springmvc.Dao.QuestionsMapper;
import thanhtuu.springmvc.Dao.Questions_TargetMapper;
import thanhtuu.springmvc.Domain.Answers;
import thanhtuu.springmvc.Domain.ExamKey;
import thanhtuu.springmvc.Domain.Exam_Question_BlockKey;
import thanhtuu.springmvc.Domain.Exams;
import thanhtuu.springmvc.Domain.Question_Blocks;
import thanhtuu.springmvc.Domain.Question_BlocksWithBLOBs;
import thanhtuu.springmvc.Domain.Questions;
import thanhtuu.springmvc.Domain.Questions_TargetKey;
import thanhtuu.springmvc.Temporary.BlockQuestion;
import thanhtuu.springmvc.Temporary.LatexQuestion;
import thanhtuu.springmvc.Temporary.SingleQuestion;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class QuestionService implements QuestionServiceLocal {
	
  @Override
  public List<Question_BlocksWithBLOBs> getQuestionBlockInList(List<Integer> list) {
	  SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
      SqlSession sqlSession = sqlMapper.openSession(true);
      Question_BlocksMapper questionBlockMapper = sqlSession.getMapper(Question_BlocksMapper.class);
      List<Question_BlocksWithBLOBs> questionBlockList = questionBlockMapper.getQuestionBlockInList(list);
      sqlSession.close();
      return questionBlockList;
  }
  
  @Override
  public List<Integer> getQuestionFromLatex(LatexQuestion latextQuestion, int teacherId) {
	  BufferedReader bufReader = new BufferedReader(new StringReader(latextQuestion.latextContent));
	  String line = null;
	  Boolean isQuestion = false;
	  Boolean isBlock = false;
	  Boolean isAnswer = false;
	  Boolean haveBlock = false;
	  String blockQuestionContent = "";
	  String questionContent = "";
	  String answerContent = "";
	  int questionLevel = 1;
	  int questionChapterId = 1;
	  
	  List<BlockQuestion> blockQuestionList = new ArrayList<BlockQuestion>();
	  BlockQuestion blockQuestion = null;
	  SingleQuestion singleQuestion = null;
	  
	  try {
		while ((line = bufReader.readLine()) != null) {
			  line = line.trim();	  
  
			  if (line.contains("%") && line.indexOf("%") == 0) continue;  
			  if (line.contains("\\usepackage")) continue;

			  if (line.contains("%")) {
				  line = line.substring(0, line.indexOf("%"));
			  }

			  line = line.replace("\\\\", "");
			  
			  if (line.contains("\\begin{block}")) {
				  isBlock = true;
				  isQuestion = false;
				  isAnswer = false;
				  haveBlock = true;
				  blockQuestion = new BlockQuestion();
				  blockQuestion.questionList = new ArrayList<SingleQuestion>();
				  System.out.println(blockQuestionContent);
				  blockQuestionContent = "";
				  continue;
			  }
			  
			  if (line.contains("\\end{block}")) {
				  Question_BlocksWithBLOBs questionBlock = new Question_BlocksWithBLOBs();
				 // questionBlock.setContent(blockQuestionContent);
				  blockQuestion.blockQuestion = questionBlock;
				  blockQuestionList.add(blockQuestion);
				  blockQuestion = null;
				  haveBlock = false;
				  continue;
			  }
			  
			  if (line.contains("\\begin{question}")) {
				  isBlock = false;
				  isQuestion = true;
				  isAnswer = false;
				  if (!haveBlock) {
					  Question_BlocksWithBLOBs questionBlock = new Question_BlocksWithBLOBs();
					 // questionBlock.setContent(blockQuestionContent);
					  blockQuestion = new BlockQuestion();
					  blockQuestion.blockQuestion = questionBlock;
					  blockQuestion.questionList = new ArrayList<SingleQuestion>();
				  }
				  singleQuestion = new SingleQuestion();
				  singleQuestion.answerList = new ArrayList<Answers>();
				  System.out.println(questionContent);
				  questionContent = "";
				  continue;
			  }
			  
			  if (line.contains("\\end{question}")) {
				  Questions question = new Questions();
				  question.setContent(questionContent);
				  question.setSubjectid(latextQuestion.subjectId);
				  question.setLevel(questionLevel);
				  question.setChapterid(questionChapterId);
				  singleQuestion.question = question;
				  blockQuestion.questionList.add(singleQuestion);
				  if (!haveBlock) {
					  blockQuestionList.add(blockQuestion);
				  }
				  singleQuestion = null;
				  continue;
			  }
			  
			  if (line.contains("\\chon")) {
				  isBlock = false;
				  isQuestion = false;
				  isAnswer = true;
				  
				  if (!answerContent.equals("")) {
					  Answers answer = new Answers();
					  answer.setContent(answerContent);
					  answerContent = answerContent.substring(7, answerContent.lastIndexOf("}}"));
					  singleQuestion.answerList.add(answer);		  
				  }
				  System.out.println(answerContent);
				  answerContent = "";
			  }
			  
			  if (isAnswer) {
				  answerContent = answerContent.concat(line);
				  continue;
			  }
			  
			  if (isBlock) {
				  blockQuestionContent = blockQuestionContent.concat(line);
				  continue;
			  }
			  
			  if (isQuestion) {
				  questionContent = questionContent.concat(line);
				  continue;
			  }
		  }
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  //sqlSession.close();
	return this.saveBlockQuestionList(blockQuestionList, teacherId);
  }

  @Override
  public List<Integer> saveBlockQuestionList(List<BlockQuestion> blockQuestionList, int teacherId) {
	  List<Integer> blockQuestionIdList = new ArrayList<Integer>();
	  for(int i = 0; i < blockQuestionList.size(); i++) {
		  System.out.println("Block Question" + i);
		  blockQuestionList.get(i).blockQuestion.setTeacherid(teacherId);
		  if (!blockQuestionList.get(i).questionList.isEmpty()) {
			  blockQuestionIdList.add(this.saveBlockQuestion(blockQuestionList.get(i)));  
		  }
	  }
	  return blockQuestionIdList;
  }
  
  public int saveBlockQuestion(BlockQuestion blockQuestion) {
	  SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
      SqlSession sqlSession = sqlMapper.openSession(true);
      
      Question_BlocksMapper questionBlockMapper = sqlSession.getMapper(Question_BlocksMapper.class);
      QuestionsMapper questionMapper = sqlSession.getMapper(QuestionsMapper.class);
      AnswersMapper answerMapper = sqlSession.getMapper(AnswersMapper.class);
      Questions_TargetMapper questionTargetMapper = sqlSession.getMapper(Questions_TargetMapper.class);

      List<Integer> chapterIdList = new ArrayList<Integer>();
      blockQuestion.blockQuestion.setQuestioncount(blockQuestion.questionList.size());
      
     
      Question_BlocksWithBLOBs getId = questionBlockMapper.getMaxIdBlock();
      questionBlockMapper.insertSelective(blockQuestion.blockQuestion);
      //System.out.println("ID MAX = " + getId.getId());
      System.out.println("Block Question");
      //System.out.println(blockQuestion.questionList.size());
      
      
      for (int i = 0; i < blockQuestion.questionList.size(); i++) {
    	  SingleQuestion singleQuestion = blockQuestion.questionList.get(i);
    	  	  
    	  System.out.println("AAAAAAAAAAAAAAAAAAA");
    	  
    	  System.out.println(getId.getId() +1);
    	 
    	  singleQuestion.question.setQuestionblockid(getId.getId().intValue() + 1);
    	  questionMapper.insertSelective(singleQuestion.question);
    	  chapterIdList.add(singleQuestion.question.getChapterid());
    	  System.out.println("Single Question");
    	  if(!singleQuestion.answerList.isEmpty()) {
    		  for (int j = 0; j < singleQuestion.answerList.size(); j++) {
        		  Answers answer = singleQuestion.answerList.get(j);
        		  answer.setQuestionid(singleQuestion.question.getId());
        		  answer.setQuestionblockid(getId.getId() + 1);
        		 
        		  if (answer.getIssolution() == null) {
        			  answer.setIssolution(false);
        		  }
        		  answerMapper.insertSelective(answer);
        		  System.out.println("Answer" + j);
        	  }  
    	  }
    	  if(!singleQuestion.questionTargetList.isEmpty()) {
    		  for (int j = 0; j < singleQuestion.questionTargetList.size(); j++) {
        		  Questions_TargetKey questionTarget = singleQuestion.questionTargetList.get(j);
        		  questionTarget.setQuestionid(singleQuestion.question.getId());
        		  questionTarget.setQuestionblockid(getId.getId() + 1);
        		  questionTargetMapper.insert(questionTarget);
        		  System.out.println("Target" + j);
        	  }  
    	  }
      }
      
      sqlSession.close();
      return (getId.getId().intValue() +1);
  }
  
  @Override
  public List<Integer> getQuestionL1CountByChapterIdList(List<Integer> chapterIdList) {
	  SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
      SqlSession sqlSession = sqlMapper.openSession(true);
	  QuestionsMapper questionMapper = sqlSession.getMapper(QuestionsMapper.class);
	    
	  List<Integer> questionCountList = new ArrayList<Integer>();
	  for(int i = 0; i < chapterIdList.size(); i++) {
		  questionCountList.add(questionMapper.getQuestionL1CountByChapterId(chapterIdList.get(i)).intValue());
	  }
	    
	  sqlSession.close();
	  return questionCountList;
  }
  
  @Override
  public List<Integer> getQuestionL2CountByChapterIdList(List<Integer> chapterIdList) {
	  SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
      SqlSession sqlSession = sqlMapper.openSession(true);
	  QuestionsMapper questionMapper = sqlSession.getMapper(QuestionsMapper.class);
	    
	  List<Integer> questionCountList = new ArrayList<Integer>();
	  for(int i = 0; i < chapterIdList.size(); i++) {
		  questionCountList.add(questionMapper.getQuestionL2CountByChapterId(chapterIdList.get(i)).intValue());
	  }
	    
	  sqlSession.close();
	  return questionCountList;
  }
  @Override
  public List<Integer> getQuestionL3CountByChapterIdList(List<Integer> chapterIdList) {
	  SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
      SqlSession sqlSession = sqlMapper.openSession(true);
	  QuestionsMapper questionMapper = sqlSession.getMapper(QuestionsMapper.class);
	    
	  List<Integer> questionCountList = new ArrayList<Integer>();
	  for(int i = 0; i < chapterIdList.size(); i++) {
		  questionCountList.add(questionMapper.getQuestionL3CountByChapterId(chapterIdList.get(i)).intValue());
	  }
	    
	  sqlSession.close();
	  return questionCountList;
  }
  @Override
  public List<Integer> getQuestionL4CountByChapterIdList(List<Integer> chapterIdList) {
	  SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
      SqlSession sqlSession = sqlMapper.openSession(true);
	  QuestionsMapper questionMapper = sqlSession.getMapper(QuestionsMapper.class);
	    
	  List<Integer> questionCountList = new ArrayList<Integer>();
	  for(int i = 0; i < chapterIdList.size(); i++) {
		  questionCountList.add(questionMapper.getQuestionL4CountByChapterId(chapterIdList.get(i)).intValue());
	  }
	    
	  sqlSession.close();
	  return questionCountList;
  }
  @Override
  public List<Integer> getQuestionL5CountByChapterIdList(List<Integer> chapterIdList) {
	  SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
      SqlSession sqlSession = sqlMapper.openSession(true);
	  QuestionsMapper questionMapper = sqlSession.getMapper(QuestionsMapper.class);
	    
	  List<Integer> questionCountList = new ArrayList<Integer>();
	  for(int i = 0; i < chapterIdList.size(); i++) {
		  questionCountList.add(questionMapper.getQuestionL5CountByChapterId(chapterIdList.get(i)).intValue());
	  }
	    
	  sqlSession.close();
	  return questionCountList;
  }

@Override
public List<BlockQuestion> getBLockQuestionOfRootExamId(long rootExamId) {
	// TODO Auto-generated method stub
	SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
	SqlSession sqlSession = sqlMapper.openSession(true);
	ExamMapper examMapper = sqlSession.getMapper(ExamMapper.class);
	ExamsMapper examsMapper = sqlSession.getMapper(ExamsMapper.class);
	ExamService examService = null;
	List<BlockQuestion> ListBlockQuestion = new ArrayList<BlockQuestion>();
	
	
	// Get Question Block
			@SuppressWarnings("null")
			List<Question_BlocksWithBLOBs> questionBlockList = examService.getBlockQuestionByExamId(rootExamId);
			List<Integer> questionBlockIdList = new ArrayList<Integer>();
			for(int i = 0; i < questionBlockList.size(); i++) {
				questionBlockIdList.add(questionBlockList.get(i).getId().intValue());
			}
			System.out.println("Question Root Block" + questionBlockList.size());
			
			// Get Question
			List<Questions> questionList = examService.getQuestionByBlockQuestionIdList(questionBlockIdList);
			List<Integer> questionIdList = new ArrayList<Integer>();
			for(int i = 0; i < questionList.size(); i++) {
				questionIdList.add(questionList.get(i).getId().intValue());
			}
			System.out.println("Question Root" + questionList.size());
			
			
			// Get Answer
			List<Answers> answerList = examService.getAnswerByQuestionIdList(questionIdList);
			
			// Get Target
			List<Questions_TargetKey> questionTargetList = examService.getQuestionTargetByQuestionIdList(questionIdList);
			System.out.println("Question Target Root" + questionTargetList.size());
			
			for (int i = 0; i < questionBlockList.size(); i++) {
				BlockQuestion blockQuestion = new BlockQuestion();
				blockQuestion.blockQuestion = questionBlockList.get(i);
				blockQuestion.questionList = new ArrayList<SingleQuestion>();
				
				for (int j = 0; j < questionList.size(); j++) {
					if (questionList.get(j).getQuestionblockid() == questionBlockList.get(i).getId().intValue()) {
						SingleQuestion singleQuestion = new SingleQuestion();
						singleQuestion.question = questionList.get(j);
						singleQuestion.answerList = new ArrayList<Answers>();
						List<Answers> al = new ArrayList<Answers>();
						
						for (int z = 0; z < answerList.size(); z++) {
							if(answerList.get(z).getQuestionid() == questionList.get(j).getId()) {
								al.add(answerList.get(z));
								
							}
						}
						
					/*	Collections.shuffle(al, new Random());
						for (int z = 0; z < exams.getAnswercount(); z++) {
							for (int t = 0; t < al.size(); t++) {
								if (z == 0) {
									if (al.get(t).getIssolution()) {
										singleQuestion.answerList.add(al.get(t));
										al.remove(t);
										break;
									}
								} else {
									if (!al.get(t).getIssolution()) {
										singleQuestion.answerList.add(al.get(t));
										al.remove(t);
										break;
									}
								}
							}
						}*/
						//Collections.shuffle(singleQuestion.answerList, new Random());
						
						
						for (int z = 0; z < questionTargetList.size(); z++) {
							if(questionTargetList.get(z).getQuestionid() == questionList.get(j).getId()) {
								singleQuestion.questionTargetList.add(questionTargetList.get(z));
							}
						}
						blockQuestion.questionList.add(singleQuestion);
					}
				}
				ListBlockQuestion.add(blockQuestion);
			}
			sqlSession.close();
    return ListBlockQuestion;
}

@Override
public List<Questions> getAllQuestionOfBlockQuestion(List<Exam_Question_BlockKey> blockQuestionId) {
	// TODO Auto-generated method stub
	SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
	SqlSession sqlSession = sqlMapper.openSession(true);
	QuestionsMapper questionsMapper = sqlSession.getMapper(QuestionsMapper.class);
	List<Questions> question = questionsMapper.getQuestionOfBlockQuestionByRootExam(blockQuestionId);
	sqlSession.close();
	return question;
}
}
