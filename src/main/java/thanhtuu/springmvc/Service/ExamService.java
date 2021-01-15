package thanhtuu.springmvc.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import thanhtuu.springmvc.Constains.Answer;
import thanhtuu.springmvc.Dao.AnswersMapper;
import thanhtuu.springmvc.Dao.ExamMapper;
import thanhtuu.springmvc.Dao.Exam_AnswerMapper;
import thanhtuu.springmvc.Dao.Exam_QuestionMapper;
import thanhtuu.springmvc.Dao.Exam_Question_BlockMapper;
import thanhtuu.springmvc.Dao.ExamsMapper;
import thanhtuu.springmvc.Dao.Exams_InfoMapper;
import thanhtuu.springmvc.Dao.Question_BlocksMapper;
import thanhtuu.springmvc.Dao.QuestionsMapper;
import thanhtuu.springmvc.Dao.Questions_TargetMapper;
import thanhtuu.springmvc.Dao.Student_ExamMapper;
import thanhtuu.springmvc.Dao.TestMapper;
import thanhtuu.springmvc.Domain.Answers;
import thanhtuu.springmvc.Domain.Class_Member;
import thanhtuu.springmvc.Domain.Exam;
import thanhtuu.springmvc.Domain.ExamKey;
import thanhtuu.springmvc.Domain.Exam_Answer;
import thanhtuu.springmvc.Domain.Exam_Question;
import thanhtuu.springmvc.Domain.Exam_Question_BlockKey;
import thanhtuu.springmvc.Domain.Exams;
import thanhtuu.springmvc.Domain.Exams_Info;
import thanhtuu.springmvc.Domain.Question_Blocks;
import thanhtuu.springmvc.Domain.Question_BlocksWithBLOBs;
import thanhtuu.springmvc.Domain.Questions;
import thanhtuu.springmvc.Domain.Questions_TargetKey;
import thanhtuu.springmvc.Domain.Student_Exam;
import thanhtuu.springmvc.Domain.Test;
import thanhtuu.springmvc.Temporary.BlockQuestion;
import thanhtuu.springmvc.Temporary.SingleQuestion;
import thanhtuu.springmvc.Temporary.UpdateExam;
import thanhtuu.springmvc.Temporary.Exam.ListExam;
import thanhtuu.springmvc.Temporary.Exam.RootBlocksQuestion;
import thanhtuu.springmvc.Temporary.Exam.UpdateRootExam;

public class ExamService implements ExamServiceLocal {

	public Exams_Info getExamInfo(long ExamsId) {
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		Exams_InfoMapper examInfoMapper = sqlSession.getMapper(Exams_InfoMapper.class);
		Exams_Info examInfo = examInfoMapper.getExamInfo(ExamsId);
		sqlSession.close();
		return examInfo;
	}

	public Exam getExamFromId(long examId) {
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		ExamMapper rootExamMapper = sqlSession.getMapper(ExamMapper.class);
		Exam exam = rootExamMapper.getExamById(examId);
		sqlSession.close();
		return exam;
	}

	@Override
	public List<RootBlocksQuestion> getRootBlockQuestion(long examId) {

		// TODO Auto-generated method stub
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		ExamMapper rootExamMapper = sqlSession.getMapper(ExamMapper.class);
		ExamsMapper rootExamsMapper = sqlSession.getMapper(ExamsMapper.class);
		Exam_Question_BlockMapper getRootBlockExamQBMapper = sqlSession.getMapper(Exam_Question_BlockMapper.class);
		Question_BlocksMapper RQBlockMapper = sqlSession.getMapper(Question_BlocksMapper.class);

		List<RootBlocksQuestion> rootBlockQuestion = new ArrayList<RootBlocksQuestion>();

		// Get Exams Info
		Exams exams = rootExamsMapper.selectByPrimaryKey(examId);

		// Get Root Block Question
		List<Exam_Question_BlockKey> rootExamQuestion_BlockKey = getRootBlockExamQBMapper
				.getIdBlockQuestionOfRootExamId(examId);

		List<Question_BlocksWithBLOBs> rootQuestionBlock = new ArrayList<>();

		List<Integer> ListIdRootBlockQuestion = new ArrayList<>();
		for (int i = 0; i < rootExamQuestion_BlockKey.size(); i++) {
			long index = rootExamQuestion_BlockKey.get(i).getQuestionblockid();

			rootQuestionBlock.add(i, RQBlockMapper.selectRootQuestionBlockId(index));

			ListIdRootBlockQuestion.add(i, rootExamQuestion_BlockKey.get(i).getQuestionblockid());

		}

		// Get question
		List<Questions> ListRootQuestion = this.getQuestionByBlockQuestionIdList(ListIdRootBlockQuestion);
		List<Integer> ListIdRootQuestion = new ArrayList<>();

		for (int i = 0; i < ListRootQuestion.size(); i++) {

			ListIdRootQuestion.add(i, ListRootQuestion.get(i).getId().intValue());
		}

		// Get Answer
		List<Answers> ListRootAnswer = this.getAnswerByQuestionIdList(ListIdRootQuestion);
		List<Integer> ListIdAnswerOfQuestion = new ArrayList<>();
		for (int i = 0; i < ListRootAnswer.size(); i++) {
			// System.out.println("Answer OF Question : " +
			// ListRootAnswer.get(i).getContent());
			ListIdAnswerOfQuestion.add(i, ListRootAnswer.get(i).getId().intValue());
		}
		// Get ALL -> RootBlocksQuestion

		for (int i = 0; i < rootExamQuestion_BlockKey.size(); i++) {
			if (rootQuestionBlock.size() > 0) {
				RootBlocksQuestion rootBlockQUestion = new RootBlocksQuestion();
				rootBlockQUestion.rootQuestionBlock = rootQuestionBlock.get(i);

				rootBlockQUestion.questionList = new ArrayList<SingleQuestion>();
				for (int j = 0; j < ListRootQuestion.size(); j++) {

					if (ListRootQuestion.get(j).getQuestionblockid() == rootExamQuestion_BlockKey.get(i)
							.getQuestionblockid()) {

						SingleQuestion singleQuestion = new SingleQuestion();

						singleQuestion.question = ListRootQuestion.get(j);
						singleQuestion.answerList = new ArrayList<>();
						List<Answers> rootAnswer = new ArrayList<>();
						for (int z = 0; z < ListRootAnswer.size(); z++) {

							if (ListRootAnswer.get(z).getQuestionid() == ListRootQuestion.get(j).getId()) {
								rootAnswer.add(ListRootAnswer.get(z));

							}
						}

						// Remove Answer
						if (rootAnswer.size() > exams.getAnswercount()) {
							int RemoveAnswer = rootAnswer.size() - exams.getAnswercount();

							for (int k = 0; k < RemoveAnswer; k++) {
								Random rand = new Random();
								int randomNum = rand.nextInt((rootAnswer.size() - 1) + 1);
								rootAnswer.remove(randomNum);
							}
						}

						for (int z1 = 0; z1 < rootAnswer.size(); z1++) {

							singleQuestion.answerList.add(rootAnswer.get(z1));

						}
						rootBlockQUestion.questionList.add(singleQuestion);
					}

				}
				rootBlockQuestion.add(i, rootBlockQUestion);
			}
		}

		// System.out.println("====================PASS===================================");

		for (int n = 0; n < rootBlockQuestion.size(); n++) {
			// System.out.println("ID " +
			// rootBlockQuestion.get(n).rootQuestionBlock.getId());
			// System.out.println("Content Block Question " +
			// rootBlockQuestion.get(n).rootQuestionBlock.getContent());
			// System.out.println("Question " +
			// rootBlockQuestion.get(n).rootQuestionBlock.getContent());
			List<SingleQuestion> testSingleQuestion = new ArrayList<SingleQuestion>();
			testSingleQuestion = rootBlockQuestion.get(n).questionList;
			for (int n1 = 0; n1 < testSingleQuestion.size(); n1++) {
				// System.out.println("Question " +
				// testSingleQuestion.get(n1).question.getContent());
				List<Answers> rootAnswer = new ArrayList<Answers>();
				rootAnswer = rootBlockQuestion.get(n).questionList.get(n1).answerList;
				for (int n2 = 0; n2 < rootAnswer.size(); n2++) {
					// System.out.println("Answer1 : " +
					// rootBlockQuestion.get(n).questionList.get(n1).answerList.get(n2).getContent());
				}
			}
		}

		// System.out.println("rootExamMapper.getIdExamByExams(examId) : " +
		// examId);

		// Insert into Exam_Question and Answer
		Exam IdExam = rootExamMapper.getIdExamByExams(examId);
		int IdExamCurrent = IdExam.getId().intValue();
		// System.out.println("=====================================================================");
		// System.out.println("IDExamCurrent : " + IdExamCurrent);
		// System.out.println("=====================================================================");
		for (int k = 0; k < rootBlockQuestion.size(); k++) {

			int IdBlockQuestion = rootBlockQuestion.get(k).rootQuestionBlock.getId().intValue();

			// int IdExams = exams.getId().intValue();
			Exam_QuestionMapper exam_QuestionMapper = sqlSession.getMapper(Exam_QuestionMapper.class);
			Exam_AnswerMapper examAnswerMapper = sqlSession.getMapper(Exam_AnswerMapper.class);
			// examQuestion.setExamid();
			for (int N = 0; N < rootBlockQuestion.get(k).questionList.size(); N++) {
				int IdQuestion = rootBlockQuestion.get(k).questionList.get(N).question.getId().intValue();
				Exam_Question examQuestion = new Exam_Question();
				examQuestion.setExamid(IdExamCurrent);
				examQuestion.setExamsid((int) examId);
				examQuestion.setQuestionblockid(rootBlockQuestion.get(k).rootQuestionBlock.getId().intValue());
				examQuestion.setQuestionid(IdQuestion);
				exam_QuestionMapper.insertSelective(examQuestion);// Insert
																	// QUestion

				for (int M = 0; M < rootBlockQuestion.get(k).questionList.get(N).answerList.size(); M++) {
					Exam_Answer examAnswer = new Exam_Answer();
					// System.out.println("=================111111111111111111111=======================");
					// System.out.println(IdQuestion);
					// System.out.println("===============1111111111111111111111111=====================");
					int IdAnswer = rootBlockQuestion.get(k).questionList.get(N).answerList.get(M).getId().intValue();
					examAnswer.setAnswerid(IdAnswer);
					examAnswer.setQuestionid(IdQuestion);
					examAnswer.setQuestionblockid(IdBlockQuestion);
					examAnswer.setExamid(IdExamCurrent);
					if (rootBlockQuestion.get(k).questionList.get(N).answerList.get(M).getIssolution() == true) {
						examAnswer.setChecksolution(true);
					}
					examAnswerMapper.insertSelective(examAnswer);
				}
			}
		}
		sqlSession.close();
		return rootBlockQuestion;
	}

	@Override
	public List<Exam> getExamByExamsId(int examsId) {
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		ExamMapper mapper = sqlSession.getMapper(ExamMapper.class);
		Exam exam = new Exam();
		exam.setExamsid(examsId);
		List<Exam> examList = mapper.getExamByExamsId(exam);
		sqlSession.close();
		return examList;
	}

	@Override
	public List<BlockQuestion> getBlockQuestionByExamId1(long examId) {
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		ExamMapper examMapper = sqlSession.getMapper(ExamMapper.class);
		ExamsMapper examsMapper = sqlSession.getMapper(ExamsMapper.class);
		List<BlockQuestion> blockQuestionList = new ArrayList<BlockQuestion>();

		// Get Exams Info
		ExamKey exam = examMapper.getExamById(examId);
		Exams exams = examsMapper.selectByPrimaryKey(exam.getExamsid().longValue());

		// Get Question Block
		List<Question_BlocksWithBLOBs> questionBlockList = this.getBlockQuestionByExamId(examId);
		List<Integer> questionBlockIdList = new ArrayList<Integer>();
		for (int i = 0; i < questionBlockList.size(); i++) {
			questionBlockIdList.add(questionBlockList.get(i).getId().intValue());
		}
		// System.out.println("Question Block" + questionBlockList.size());

		// Get Question
		List<Questions> questionList = getQuestionByBlockQuestionIdList(questionBlockIdList);
		List<Integer> questionIdList = new ArrayList<Integer>();
		for (int i = 0; i < questionList.size(); i++) {
			questionIdList.add(questionList.get(i).getId().intValue());
		}
		// System.out.println("Question" + questionList.size());

		// Get Answer
		List<Answers> answerList = getAnswerByQuestionIdList(questionIdList);
		// System.out.println("Answer" + answerList.size());

		// Get Target
		List<Questions_TargetKey> questionTargetList = getQuestionTargetByQuestionIdList(questionIdList);
		// System.out.println("Question Target" + questionTargetList.size());

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
						if (answerList.get(z).getQuestionid() == questionList.get(j).getId()) {
							al.add(answerList.get(z));
						}
					}

					Collections.shuffle(al, new Random());
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
					}
					Collections.shuffle(singleQuestion.answerList, new Random());

					for (int z = 0; z < questionTargetList.size(); z++) {
						if (questionTargetList.get(z).getQuestionid() == questionList.get(j).getId()) {
							singleQuestion.questionTargetList.add(questionTargetList.get(z));
						}
					}
					blockQuestion.questionList.add(singleQuestion);
				}
			}
			blockQuestionList.add(blockQuestion);
		}
		// System.out.println("Block Question" + blockQuestionList.size());
		sqlSession.close();
		return blockQuestionList;
	}

	public List<Questions_TargetKey> getQuestionTargetByQuestionIdList(List<Integer> questionIdList) {
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);

		// Get Question
		Questions_TargetMapper questionTargetMapper = sqlSession.getMapper(Questions_TargetMapper.class);
		List<Questions_TargetKey> questionTargetList = new ArrayList<Questions_TargetKey>();
		if (questionIdList.size() > 0) {
			questionTargetList = questionTargetMapper.getByQuestionIdList(questionIdList);
		}
		sqlSession.close();
		return questionTargetList;
	}

	@Override
	public List<Question_BlocksWithBLOBs> getBlockQuestionByExamId(long examId) {
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);

		// Get Question Block Id
		// System.out.println("Get Question Block Id");
		Exam_Question_BlockMapper examQuestionBlockMapper = sqlSession.getMapper(Exam_Question_BlockMapper.class);
		Exam_Question_BlockKey examQuestionBlock = new Exam_Question_BlockKey();
		examQuestionBlock.setExamid(examId);
		List<Exam_Question_BlockKey> examQuestionBlockList = examQuestionBlockMapper
				.getBlockQuestionByExamId(examQuestionBlock);

		Question_BlocksMapper questionBlockMapper = sqlSession.getMapper(Question_BlocksMapper.class);
		List<Integer> questionBlockIdList = new ArrayList<Integer>();
		for (int i = 0; i < examQuestionBlockList.size(); i++) {
			// System.out.println(i);
			questionBlockIdList.add(examQuestionBlockList.get(i).getQuestionblockid());
		}

		// Get Question Block
		// System.out.println("Get Question Block");
		List<Question_BlocksWithBLOBs> questionBlockList;
		if (questionBlockIdList.size() > 0) {
			questionBlockList = questionBlockMapper.getQuestionBlockInList(questionBlockIdList);
		} else {
			questionBlockList = new ArrayList<Question_BlocksWithBLOBs>();
		}
		sqlSession.close();
		return questionBlockList;
	}

	@Override
	public List<Questions> getQuestionByBlockQuestionIdList(List<Integer> blockQuestionIdList) {
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);

		// Get Question
		// System.out.println("Get Question");
		QuestionsMapper questionMapper = sqlSession.getMapper(QuestionsMapper.class);
		List<Questions> questionList = new ArrayList<Questions>();
		if (blockQuestionIdList.size() > 0) {
			questionList = questionMapper.getQuestionByBlockQuestionIdList(blockQuestionIdList);
		}
		sqlSession.close();
		return questionList;
	}

	@Override
	public List<Answers> getAnswerByQuestionIdList(List<Integer> questionIdList) {
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);

		// Get Question
		// System.out.println("Get Answer");
		AnswersMapper answerMapper = sqlSession.getMapper(AnswersMapper.class);
		List<Answers> answerList = new ArrayList<Answers>();
		if (questionIdList.size() > 0) {
			answerList = answerMapper.getAnswerByQuestionIdList(questionIdList);
		}
		sqlSession.close();
		return answerList;
	}

	@Override
	public Student_Exam getBlockQuestionByExamsId(long studentId, long examsId, Class_Member classMember) {
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		Date now = new Date();
		ExamMapper examMapper = sqlSession.getMapper(ExamMapper.class);
		ExamKey exam = examMapper.getExamRandomByExamsId(examsId);

		Student_Exam studentExam = new Student_Exam();
		if (!classMember.equals(null)) {
			Student_ExamMapper studentExamMapper = sqlSession.getMapper(Student_ExamMapper.class);
			studentExam.setClassid(classMember.getClassid());
			studentExam.setStudentid(classMember.getStudentid().intValue());
			studentExam.setSubjectid(classMember.getSubjectid());
			studentExam.setExamid(exam.getId().intValue());
			studentExam.setExamsid(exam.getExamsid());
			studentExam.setModifiedat(now);
			studentExam.setCreatedat(now);
			studentExamMapper.insertSelective(studentExam);
		}
		sqlSession.close();
		return studentExam;
	}

	@Override
	public long submitTest(List<Test> testList) {
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);

		Date now = new Date();
		TestMapper testMapper = sqlSession.getMapper(TestMapper.class);

		Long result = this.checkExam(testList);
		for (int i = 0; i < testList.size(); i++) {
			testList.get(i).setModifiedat(now);
			testList.get(i).setCreatedat(now);
			testMapper.insertSelective(testList.get(i));
		}

		if (testList.size() > 0) {
			Student_ExamMapper studentExamMapper = sqlSession.getMapper(Student_ExamMapper.class);
			Student_Exam studentExam = new Student_Exam();
			studentExam.setId(testList.get(0).getId());
			studentExam.setScore(result.intValue());
			studentExam.setModifiedat(new Date());
			studentExamMapper.updateByPrimaryKeySelective(studentExam);
		}

		sqlSession.close();
		return result;
	}

	@Override
	public long checkExam(List<Test> testList) {
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		AnswersMapper answerMapper = sqlSession.getMapper(AnswersMapper.class);

		List<Integer> answerIdList = new ArrayList<Integer>();
		for (int i = 0; i < testList.size(); i++) {
			if (!(testList.get(i).getAnswerid() == null)) {
				answerIdList.add(testList.get(i).getAnswerid());
			}
		}

		long result = 0;
		if (answerIdList.size() > 0) {
			result = answerMapper.getSoulutionAnswerCountByIdInList(answerIdList);
		}
		sqlSession.close();
		return result;
	}

	@Override
	public Exams fetchExamsByExamsId(long examsId) {
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		ExamsMapper examsMapper = sqlSession.getMapper(ExamsMapper.class);
		Exams exams = examsMapper.selectByPrimaryKey(examsId);
		sqlSession.close();
		return exams;
	}

	@Override
	public List<BlockQuestion> getBlockQuestionByKeyWord(long subjectId, long chapterId, String keyWord) {
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		Question_BlocksMapper questionBlockMapper = sqlSession.getMapper(Question_BlocksMapper.class);
		QuestionsMapper questionMapper = sqlSession.getMapper(QuestionsMapper.class);

		// Get Question
		List<Questions> questionList = questionMapper.getBySubjectIdChapterIdLevel(subjectId, chapterId, 0);
		// System.out.println("Single" + questionList.size());
		Set<Integer> questionBlockIdSet = new HashSet<Integer>();
		for (int i = 0; i < questionList.size(); i++) {
			questionBlockIdSet.add(questionList.get(i).getQuestionblockid());
		}

		// Unique Question Block
		List<Integer> blockQuestionIdList = new ArrayList<Integer>();
		blockQuestionIdList.addAll(questionBlockIdSet);

		// Get Block Question
		List<Question_BlocksWithBLOBs> questionBlockList = new ArrayList<Question_BlocksWithBLOBs>();
		if (blockQuestionIdList.size() > 0) {
			questionBlockList = questionBlockMapper.getBlockQuestionByKeyWord(blockQuestionIdList);
			// System.out.println("Block" + questionBlockList.size());
		}

		List<BlockQuestion> blockQuestionList = new ArrayList<BlockQuestion>();
		for (int i = 0; i < questionBlockList.size(); i++) {
			if (keyWord.equals("") || (questionBlockList.get(i).getKeyword() != null
					&& questionBlockList.get(i).getKeyword().equals(keyWord))) {
				BlockQuestion blockQuestion = new BlockQuestion();
				blockQuestion.blockQuestion = questionBlockList.get(i);
				blockQuestion.questionList = new ArrayList<SingleQuestion>();
				for (int j = 0; j < questionList.size(); j++) {
					if (questionList.get(j).getQuestionblockid() == questionBlockList.get(i).getId().intValue()) {
						SingleQuestion singleQuestion = new SingleQuestion();
						singleQuestion.question = questionList.get(j);
						blockQuestion.questionList.add(singleQuestion);
					}
				}
				blockQuestionList.add(blockQuestion);
				// System.out.println("Block" +
				// blockQuestion.questionList.size());
			}
		}
		sqlSession.close();
		return blockQuestionList;
	}

	@Override
	public int updateExam(UpdateExam updateExam) {
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		Exam_Question_BlockMapper examQuestionBlockMapper = sqlSession.getMapper(Exam_Question_BlockMapper.class);
		int result = 1;

		List<Exam_Question_BlockKey> examQuestionBlock = updateExam.blockQuestionRemoveList;
		for (int i = 0; i < examQuestionBlock.size(); i++) {
			// System.out.println(examQuestionBlock.get(i).getExamsid());
			// System.out.println(examQuestionBlock.get(i).getExamid());
			// System.out.println(examQuestionBlock.get(i).getQuestionblockid());
			result = result
					& examQuestionBlockMapper.deleteByPrigetBlockQuestionByExamIdmaryKey(examQuestionBlock.get(i));
		}

		examQuestionBlock = updateExam.blockQuestionAddList;
		for (int i = 0; i < examQuestionBlock.size(); i++) {
			// System.out.println(examQuestionBlock.get(i).getExamsid());
			// System.out.println(examQuestionBlock.get(i).getExamid());
			// System.out.println(examQuestionBlock.get(i).getQuestionblockid());
			result = result & examQuestionBlockMapper.insert(examQuestionBlock.get(i));
		}

		sqlSession.close();
		return result;
	}

	@Override
	public List<RootBlocksQuestion> getRootBlockQuestionBySearchText(long subjectId, long chapterId) {
		// TODO Auto-generated method stub
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		Question_BlocksMapper questionBlockMapper = sqlSession.getMapper(Question_BlocksMapper.class);

		QuestionsMapper questionMapper = sqlSession.getMapper(QuestionsMapper.class);

		// Get Question
		List<Questions> questionList = questionMapper.getBySubjectIdChapterIdLevel(subjectId, chapterId, 0);
		// System.out.println("Single" + questionList.size());
		List<Integer> questionBlockIdSet = new ArrayList<Integer>();
		for (int i = 0; i < questionList.size(); i++) {
			questionBlockIdSet.add(questionList.get(i).getQuestionblockid());
		}

		// Get Answer
		List<Answers> ListAnswer = this.getAnswerByQuestionIdList(questionBlockIdSet);

		// Unique Question Block
		List<Integer> blockQuestionIdList = new ArrayList<Integer>();
		blockQuestionIdList.addAll(questionBlockIdSet);

		// Get Block Question
		List<Question_BlocksWithBLOBs> questionBlockList = new ArrayList<Question_BlocksWithBLOBs>();
		if (blockQuestionIdList.size() > 0) {
			questionBlockList = questionBlockMapper.getBlockQuestionByKeyWord(blockQuestionIdList);

		}

		/*
		 * Check Block Question
		 */

		List<RootBlocksQuestion> blockQuestionList = new ArrayList<RootBlocksQuestion>();
		for (int n = 0; n < questionBlockList.size(); n++) {

			// System.out.println("======================================================");
			RootBlocksQuestion blockQuestion = new RootBlocksQuestion();
			blockQuestion.rootQuestionBlock = questionBlockList.get(n);
			blockQuestion.questionList = new ArrayList<SingleQuestion>();
			for (int j = 0; j < questionList.size(); j++) {
				if (questionList.get(j).getQuestionblockid() == questionBlockList.get(n).getId().intValue()) {
					SingleQuestion singleQuestion = new SingleQuestion();
					singleQuestion.question = questionList.get(j);
					singleQuestion.answerList = new ArrayList<Answers>();
					for (int k = 0; k < ListAnswer.size(); k++) {
						if (questionList.get(j).getQuestionblockid() == ListAnswer.get(k).getQuestionblockid()
								.intValue()) {
							singleQuestion.answerList.add(ListAnswer.get(k));
							// System.out.println("ANSWER : " +
							// ListAnswer.get(k));
						}
					}
					blockQuestion.questionList.add(singleQuestion);
				}
			}
			blockQuestionList.add(blockQuestion);
		}
		// System.out.println(" blockQuestion FINAL : " +
		// blockQuestionList.size());
		sqlSession.close();
		return blockQuestionList;
	}

	@Override
	public int updateRootExam(UpdateRootExam updateRootExam) {
		// TODO Auto-generated method stub
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		Exam_Question_BlockMapper examQuestionBlockMapper = sqlSession.getMapper(Exam_Question_BlockMapper.class);
		ExamMapper examMapper = sqlSession.getMapper(ExamMapper.class);
		ExamsMapper examsMapper = sqlSession.getMapper(ExamsMapper.class);
		Exam_QuestionMapper examQuestionMapper = sqlSession.getMapper(Exam_QuestionMapper.class);
		Exam_AnswerMapper examAnswerMapper = sqlSession.getMapper(Exam_AnswerMapper.class);
		AnswersMapper answersMapper = sqlSession.getMapper(AnswersMapper.class);
		QuestionsMapper questionsMapper = sqlSession.getMapper(QuestionsMapper.class);
		int result = 1;
		Exam_Question_BlockKey checkIdBlock = new Exam_Question_BlockKey();

		/*
		 * UPDATED COUNTEXAM
		 */

		Exams exams = examsMapper.selectByPrimaryKey((long) updateRootExam.IdExams);
		if (exams.getExamcount() > 0)
			return result;
		exams.setExamcount(updateRootExam.countExam);
		examsMapper.updateByPrimaryKey(exams);

		Exam findExamId = examMapper.getIdExamByExams(updateRootExam.getIdExams());
		findExamId.setExamcode(updateRootExam.getCodeExam());
		examMapper.updateByPrimaryKeySelective(findExamId);
		List<Exam_Question_BlockKey> examQuestionBlock = updateRootExam.blockQuestionAddList;
		for (int j = 0; j < examQuestionBlock.size(); j++) {
		
			long ExamId = examMapper
					.getIdExamByExams(updateRootExam.blockQuestionAddList.get(j).getExamsid().intValue()).getId();

			checkIdBlock.setExamid(ExamId);
			checkIdBlock.setExamsid(updateRootExam.blockQuestionAddList.get(j).getExamsid());
			checkIdBlock.setQuestionblockid(updateRootExam.blockQuestionAddList.get(j).getQuestionblockid());

			result = result & examQuestionBlockMapper.insert(checkIdBlock);
			/*
			 * ADD into table exam_QUestion
			 */
		}

		/*
		 * Add Question
		 */

		List<Exam_Question> examQuestionadd = updateRootExam.examQuestionAddList;
		List<Integer> listQuestionId = new ArrayList<Integer>();
		for (int y = 0; y < examQuestionadd.size(); y++) {
			listQuestionId.add(examQuestionadd.get(y).getQuestionid());
		}
		// System.out.println("listQuestionId" +listQuestionId.size());
		if (listQuestionId.size() == 0) {
			List<Questions> questionadd = new ArrayList<Questions>();

			for (int e = 0; e < listQuestionId.size(); e++) {
				Questions newQuestion = new Questions();
				newQuestion = questionsMapper.getQuestionID(listQuestionId.get(e).longValue());
				questionadd.add(newQuestion);
			}

			for (int u = 0; u < questionadd.size(); u++) {
				// Add block Question
				// Check Block question exist
				Exam_Question_BlockKey checkExamQuestionBlockExist = examQuestionBlockMapper.findExamBlockQuestionExist(
						findExamId.getId(), updateRootExam.getIdExams(), questionadd.get(u).getQuestionblockid());
				if (checkExamQuestionBlockExist == null) {
					Exam_Question_BlockKey examQuestionBlockAdd = new Exam_Question_BlockKey();
					examQuestionBlockAdd.setExamid(findExamId.getId());
					examQuestionBlockAdd.setExamsid(updateRootExam.getIdExams());
					// System.out.println("CHECK QUESTION BLOCK ID " +
					// questionadd.get(u).getQuestionblockid());
					examQuestionBlockAdd.setQuestionblockid(questionadd.get(u).getQuestionblockid());
					examQuestionBlockMapper.insert(examQuestionBlockAdd);
				}
				// Add Exam_Question

				Exam_Question examQuestionAdd = new Exam_Question();
				examQuestionAdd.setExamid(findExamId.getId().intValue());
				examQuestionAdd.setExamsid(updateRootExam.getIdExams());
				examQuestionAdd.setQuestionblockid(questionadd.get(u).getQuestionblockid());
				examQuestionAdd.setQuestionid(questionadd.get(u).getId().intValue());

				examQuestionMapper.insert(examQuestionAdd);

				// Add exam_answer

				List<Answers> answersAdd = answersMapper.answerListByQuestionId(questionadd.get(u).getId().intValue());
				for (int t = 0; t < answersAdd.size(); t++) {
					Exam_Answer examAnswer = new Exam_Answer();
					examAnswer.setAnswerid(answersAdd.get(t).getId().intValue());
					examAnswer.setChecksolution(answersAdd.get(t).getIssolution());
					examAnswer.setQuestionblockid(answersAdd.get(t).getQuestionblockid().intValue());
					examAnswer.setQuestionid(answersAdd.get(t).getQuestionid().intValue());
					examAnswer.setExamid(findExamId.getId().intValue());

					// Insert
					examAnswerMapper.insert(examAnswer);

				}
			}
		}

		/*
		 * Add Answer With Question Exits
		 */

		List<Answers> answerAdd = updateRootExam.examAnswerAddList;
		if (answerAdd.size() > 0) {
			for (int p = 0; p < answerAdd.size(); p++) {
				Exam_Question CheckexamQuestion = examQuestionMapper.findQuestionIDByExamQuestionTable(
						answerAdd.get(p).getQuestionblockid(), updateRootExam.getIdExams(), findExamId.getId(),
						answerAdd.get(p).getQuestionid());

				if (CheckexamQuestion != null) {
					// Add Answer to Answer table
					Exam_Answer insertExamAnswer = new Exam_Answer();
					answersMapper.insert(answerAdd.get(p));
					Answers IDAnswer = answersMapper.getMaxIdAnswer();

					// System.out.println("ID ANSwer MAX" + IDAnswer);
					// Insert to Exam_Answer
					insertExamAnswer.setAnswerid(IDAnswer.getId().intValue());
					insertExamAnswer.setChecksolution(answerAdd.get(p).getIssolution());
					insertExamAnswer.setExamid(CheckexamQuestion.getExamid());
					insertExamAnswer.setQuestionblockid(answerAdd.get(p).getQuestionblockid().intValue());
					insertExamAnswer.setQuestionid(answerAdd.get(p).getQuestionid().intValue());

					// Done
					examAnswerMapper.insert(insertExamAnswer);

				}

			}
		}
		examQuestionBlock = null;
		/*
		 * DELETE LIST REMOVE
		 */
		examQuestionBlock = updateRootExam.blockQuestionRemoveList;
		for (int i = 0; i < examQuestionBlock.size(); i++) {

			// System.out.println(examQuestionBlock.get(i).getExamsid());
			// System.out.println(examQuestionBlock.get(i).getExamid());
			// System.out.println(examQuestionBlock.get(i).getQuestionblockid());
			checkIdBlock = examQuestionBlockMapper.getIdExamOfRootExamsId(examQuestionBlock.get(i).getExamsid(),
					examQuestionBlock.get(i).getQuestionblockid());
			// System.out.println(checkIdBlock.getExamid());
			result = result & examQuestionBlockMapper.deleteByPrigetBlockQuestionByExamIdmaryKey(checkIdBlock);

			/*
			 * Delete into table exam_Question
			 */
			// System.out.println("Question Block iD" +
			// examQuestionBlock.get(i).getQuestionblockid());
			// System.out.println("Question Exams iD" +
			// examQuestionBlock.get(i).getExamsid());
			// System.out.println("Question Exam iD" +
			// examQuestionBlock.get(i).getExamid());

			ArrayList<Exam_Question> examQuestion = examQuestionMapper.getAllQuestionIDByExamQuestionTable(
					examQuestionBlock.get(i).getQuestionblockid(), examQuestionBlock.get(i).getExamsid(),
					checkIdBlock.getExamid());
			// System.out.println("Exam_Question : " + examQuestion.size());
			for (int g = 0; g < examQuestion.size(); g++) {
				examQuestionMapper.deleteByPrimaryKey(examQuestion.get(g).getId());

				/*
				 * Delete Answer
				 */
				ArrayList<Exam_Answer> examAnswer = examAnswerMapper.getIdAnswerByExam(
						examQuestion.get(g).getQuestionid(), examQuestion.get(g).getQuestionblockid(),
						checkIdBlock.getExamid());
				// System.out.println("SIZE ANSWER : " + examAnswer.size());
				for (int l = 0; l < examAnswer.size(); l++) {
					examAnswerMapper.deleteByPrimaryKey(examAnswer.get(l).getId());
				}
			}
		}
		examQuestionBlock = null;
		/*
		 * Delete Exam_Question
		 */
		List<Exam_Question> examQuestionRemove = updateRootExam.examQuestionRemoveList;

		// System.out.println("Exam_Question Remove");

		if (examQuestionRemove.size() > 0) {
			for (int f = 0; f < examQuestionRemove.size(); f++) {
				// System.out.println("Exam_Question Remove");
				//// System.out.println(examQuestionRemove.get(f).getQuestionblockid());
				// System.out.println(updateRootExam.getIdExams());
				/// System.out.println(findExamId.getId());
				// System.out.println(examQuestionRemove.get(f).getQuestionid());
				// System.out.println("===============================");

				Exam_Question findExamQuestion = examQuestionMapper.findQuestionIDByExamQuestionTable(
						examQuestionRemove.get(f).getQuestionblockid(), updateRootExam.getIdExams(), findExamId.getId(),
						examQuestionRemove.get(f).getQuestionid());
				if (findExamQuestion != null) {
					// System.out.println("YES FOUND");
					// System.out.println("ID QUESTION EXAM deleted" +
					// findExamQuestion.getId());
					examQuestionMapper.deleteByPrimaryKey(findExamQuestion.getId());

					/*
					 * Delete Answer
					 */
					ArrayList<Exam_Answer> examAnswer = examAnswerMapper.getIdAnswerByExam(
							findExamQuestion.getQuestionid(), findExamQuestion.getQuestionblockid(),
							findExamQuestion.getExamid());
					// System.out.println("SIZE ANSWER REMOVE : " +
					// examAnswer.size());
					for (int l = 0; l < examAnswer.size(); l++) {
						examAnswerMapper.deleteByPrimaryKey(examAnswer.get(l).getId());
					}
				}
			}
		}

		/*
		 * Delete Answer
		 */
		List<Exam_Answer> examAnswerRemove = updateRootExam.examAnswerRemoveList;
		// List<Exam_Answer> examAnswer =
		// examAnswerMapper.getIdAnswerByExam(questionId, questionBlockId,
		// examId);
		if (examAnswerRemove.size() > 0) {
			// System.out.println("SIZE ANSWER : " + examAnswerRemove.size());
			for (int h = 0; h < examAnswerRemove.size(); h++) {
				// System.out.println("get(h).getQuestionid() " +
				// examAnswerRemove.get(h).getQuestionid());
				// System.out.println("get(h).getQuestionblockid()" +
				// examAnswerRemove.get(h).getQuestionblockid());
				// System.out.println("get(h).getAnswerid() " +
				// examAnswerRemove.get(h).getAnswerid());
				// System.out.println(findExamId.getId());

				Exam_Answer findAnswerExam = examAnswerMapper.findAnswerExamByExam(
						examAnswerRemove.get(h).getQuestionid(), examAnswerRemove.get(h).getQuestionblockid(),
						examAnswerRemove.get(h).getAnswerid(), findExamId.getId());

				if (findAnswerExam != null) {
					// System.out.println("Answer Remove");
					examAnswerMapper.deleteByPrimaryKey(findAnswerExam.getId());
				}
			}
		}

		/*
		 * UPDATED COUNTEXAM
		 */

		sqlSession.close();
		return result;
	}

	@Override
	public List<ListExam> getAllExam(long examsid) {
		// TODO Auto-generated method stub
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		ExamMapper exammapper = sqlSession.getMapper(ExamMapper.class);
		Exam_QuestionMapper examQuestionMapper = sqlSession.getMapper(Exam_QuestionMapper.class);
		Exam_AnswerMapper exam_AnswerMapper = sqlSession.getMapper(Exam_AnswerMapper.class);
		AnswersMapper answerMapper = sqlSession.getMapper(AnswersMapper.class);
		QuestionsMapper questionMapper = sqlSession.getMapper(QuestionsMapper.class);
		Question_BlocksMapper questionBlockMapper = sqlSession.getMapper(Question_BlocksMapper.class);
		List<Exam> exam = exammapper.getAllExamByExamsId(examsid);
		List<ListExam> listExam = new ArrayList<>();
		for (int i = 0; i < exam.size(); i++) {
			ListExam listE = new ListExam();
			List<RootBlocksQuestion> rootBlocksQuestion = new ArrayList<RootBlocksQuestion>();
			listE.setExamId(exam.get(i).getId().intValue());
			listE.setExamsId(exam.get(i).getExamsid());
			listE.setCodeExam(exam.get(i).getExamcode());
			List<Exam_Question> examQuestion = examQuestionMapper.getAllQuestionByIdExamandIdExams(exam.get(i).getId(),
					examsid);
			for (int j = 0; j < examQuestion.size(); j++) {
				List<Exam_Question> examQuestion2 = examQuestionMapper.getAllQuestionIDByExamAndIDExamsAndIdBlock(
						exam.get(i).getId().intValue(), examsid, examQuestion.get(j).getQuestionblockid());
				RootBlocksQuestion singleBlock = new RootBlocksQuestion();
				singleBlock.rootQuestionBlock = questionBlockMapper
						.getBlockQuestionByID(examQuestion.get(j).getQuestionblockid());
				List<Integer> getIdQuestion = new ArrayList<Integer>();
				Question_BlocksWithBLOBs questionBlock = new Question_BlocksWithBLOBs();
				List<SingleQuestion> singleListQuestion = new ArrayList<SingleQuestion>();
				
				for (int k = 0; k < examQuestion2.size(); k++) {
					SingleQuestion singleQuestion = new SingleQuestion();
					getIdQuestion.add(examQuestion2.get(k).getQuestionid());
					Questions questions = questionMapper.getQuestionID(examQuestion2.get(k).getQuestionid());
					singleQuestion.question = questions;
					// Add Answer
					List<Integer> IdAnswer = new ArrayList<>();
					List<Exam_Answer> examAnswer = exam_AnswerMapper.getIdAnswerByExam(
							examQuestion2.get(k).getQuestionid(), examQuestion2.get(k).getQuestionblockid(),
							exam.get(i).getId().intValue());
					System.out.println("=======ANSWER=========");
					for (int n = 0; n < examAnswer.size(); n++) {
						System.out.println("test answer" + examAnswer.get(n).getId());
						// Add ALL ANSWER -> List
						Answers answers = new Answers();
						answers = answerMapper.getAnswerByIDAnswer(examAnswer.get(n).getAnswerid());
						singleQuestion.answerList.add(answers);
						IdAnswer.add(examAnswer.get(n).getAnswerid());
					}
					singleListQuestion.add(singleQuestion);
				}
				singleBlock.questionList = singleListQuestion;
				rootBlocksQuestion.add(singleBlock);
				listE.blockRootQuestion = rootBlocksQuestion;
			}
			listExam.add(listE);
		}
		sqlSession.close();
		return listExam;
	}

	@Override
	public ListExam getRootExamPDF(long examsid, long examid) {
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		ExamMapper exammapper = sqlSession.getMapper(ExamMapper.class);
		Exam_QuestionMapper examQuestionMapper = sqlSession.getMapper(Exam_QuestionMapper.class);
		Exam_AnswerMapper exam_AnswerMapper = sqlSession.getMapper(Exam_AnswerMapper.class);
		AnswersMapper answerMapper = sqlSession.getMapper(AnswersMapper.class);
		QuestionsMapper questionMapper = sqlSession.getMapper(QuestionsMapper.class);
		Question_BlocksMapper questionBlockMapper = sqlSession.getMapper(Question_BlocksMapper.class);
		// Get List Exam with IdExams

		// List<Exam> exam = exammapper.getAllExamByExamsId(examsid);
		// Check Exam #0
		List<ListExam> listExam = new ArrayList<>();

		// System.out.println("SIZE : " + exam.size());
		// for (int i = 0; i < exam.size(); i++) {
		ListExam listE = new ListExam();
		List<RootBlocksQuestion> rootBlocksQuestion = new ArrayList<RootBlocksQuestion>();

		// System.out.println("exam iD " + exam.get(i).getId());
		/*
		 * List Exam set exammapper.getExamById(examid);
		 */
		Exam examPDF = exammapper.getExamById(examid);
		listE.setExamId((int) examid);
		listE.setExamsId((int) examsid);
		listE.setCodeExam(examPDF.getExamcode());
		List<Exam_Question> examQuestion = examQuestionMapper.getAllQuestionByIdExamandIdExams(examid, examsid);

		// System.out.println("EXAM _ QUESTION BLOCK: " +
		// examQuestion.size());

		//Collections.shuffle(examQuestion);
		for (int j = 0; j < examQuestion.size(); j++) {

			// Get All ID Question of Block
			List<Exam_Question> examQuestion2 = examQuestionMapper.getAllQuestionIDByExamAndIDExamsAndIdBlock(examid,
					examsid, examQuestion.get(j).getQuestionblockid());

			RootBlocksQuestion singleBlock = new RootBlocksQuestion();
			singleBlock.rootQuestionBlock = questionBlockMapper
					.getBlockQuestionByID(examQuestion.get(j).getQuestionblockid());

			List<Integer> getIdQuestion = new ArrayList<Integer>();
			Question_BlocksWithBLOBs questionBlock = new Question_BlocksWithBLOBs();

			List<SingleQuestion> singleListQuestion = new ArrayList<SingleQuestion>();
			// System.out.println("EXAM _ QUESTION 2: " +
			// examQuestion2.size());
			for (int k = 0; k < examQuestion2.size(); k++) {
				/*
				 * System.out.println("EXAM _ QUESTION 2:ID BlockQuestion " +
				 * examQuestion2.get(k).getQuestionblockid());
				 * System.out.println("EXAM _ QUESTION 2: ID Question " +
				 * examQuestion2.get(k).getQuestionid());
				 */
				SingleQuestion singleQuestion = new SingleQuestion();
				// singleQuestion.answerList = new ArrayList<Answers>() ;

				getIdQuestion.add(examQuestion2.get(k).getQuestionid());
				// -------------------------------
				Questions questions = questionMapper.getQuestionID(examQuestion2.get(k).getQuestionid());
				singleQuestion.question = questions;

				// Add Answer
				List<Integer> IdAnswer = new ArrayList<>();

				List<Exam_Answer> examAnswer = exam_AnswerMapper.getIdAnswerByExam(examQuestion2.get(k).getQuestionid(),
						examQuestion2.get(k).getQuestionblockid(), examid);

				System.out.println("=======ANSWER=========");
				for (int n = 0; n < examAnswer.size(); n++) {
					System.out.println("test answer" + examAnswer.get(n).getId());
					// Add ALL ANSWER -> List
					Answers answers = new Answers();
					answers = answerMapper.getAnswerByIDAnswer(examAnswer.get(n).getAnswerid());
					singleQuestion.answerList.add(answers);
					IdAnswer.add(examAnswer.get(n).getAnswerid());

				}

				singleListQuestion.add(singleQuestion);
				//

			}
			singleBlock.questionList = singleListQuestion;
			rootBlocksQuestion.add(singleBlock);
			listE.blockRootQuestion = rootBlocksQuestion;
			// listExam.add(listE);
		}
		sqlSession.close();
		return listE;
	}

	
}
