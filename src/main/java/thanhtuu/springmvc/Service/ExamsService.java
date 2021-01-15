package thanhtuu.springmvc.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import thanhtuu.springmvc.Dao.ExamMapper;
import thanhtuu.springmvc.Dao.Exam_AnswerMapper;
import thanhtuu.springmvc.Dao.Exam_QuestionMapper;
import thanhtuu.springmvc.Dao.Exam_Question_BlockMapper;
import thanhtuu.springmvc.Dao.ExamsMapper;
import thanhtuu.springmvc.Dao.Exams_InfoMapper;
import thanhtuu.springmvc.Dao.Exams_TargetMapper;
import thanhtuu.springmvc.Dao.QuestionsMapper;
import thanhtuu.springmvc.Dao.Questions_TargetMapper;
import thanhtuu.springmvc.Dao.Student_ExamMapper;
import thanhtuu.springmvc.Domain.Exam;
import thanhtuu.springmvc.Domain.Exam_Answer;
import thanhtuu.springmvc.Domain.Exam_Question;
import thanhtuu.springmvc.Domain.Exam_Question_BlockKey;
import thanhtuu.springmvc.Domain.Exams;
import thanhtuu.springmvc.Domain.Exams_Info;
import thanhtuu.springmvc.Domain.Exams_Target;
import thanhtuu.springmvc.Domain.Questions;
import thanhtuu.springmvc.Domain.Questions_TargetKey;
import thanhtuu.springmvc.Domain.Student_Exam;
import thanhtuu.springmvc.Domain.Target;
import thanhtuu.springmvc.Temporary.CreateExams;
import thanhtuu.springmvc.Temporary.ExamsInfo;
import thanhtuu.springmvc.Temporary.TargetList;
import thanhtuu.springmvc.Temporary.Exam.ExamCount;
import thanhtuu.springmvc.Temporary.Exam.RootExamTemp;

public class ExamsService implements ExamsServiceLocal {

	@Override
	public long createExams(CreateExams createExams) {
		int IDExam = 0;
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		ExamMapper examMapper = sqlSession.getMapper(ExamMapper.class);
		ExamsMapper mapper = sqlSession.getMapper(ExamsMapper.class);
		Exams_TargetMapper examsTargetMapper = sqlSession.getMapper(Exams_TargetMapper.class);

		if (mapper.getIDExamCurrent() == null) {
			Exams exams2 = createExams.exams;
			exams2.setId((long) 1);
			IDExam = 1;
		} else {
			IDExam = mapper.getIDExamCurrent().getId().intValue() + 1;
		}

		// Create exams
		Exams exams = createExams.exams;
		Date now = new Date();
		exams.setModifiedat(now);
		exams.setCreatedat(now);
		exams.setStatus("request");
		exams.setExamtype("online");

		mapper.insertSelective(exams);

		for (int i = 0; i < createExams.targetList.size(); i++) {
			if (createExams.targetList.get(i).questionL1Count + createExams.targetList.get(i).questionL2Count
					+ createExams.targetList.get(i).questionL3Count + createExams.targetList.get(i).questionL4Count
					+ createExams.targetList.get(i).questionL5Count > 0) {

				Exams_Target examsTarget = new Exams_Target();
				System.out.println("=  examsTarget => Examsid   :" + (long) IDExam);
				examsTarget.setExamsid((long) IDExam);
				examsTarget.setTargetid(createExams.targetList.get(i).target.getId().intValue());
				examsTarget.setChapterid(createExams.targetList.get(i).target.getChapterid());
				examsTarget.setSubjectid(createExams.targetList.get(i).target.getSubjectid());

				examsTarget.setLevel1(createExams.targetList.get(i).questionL1Count);
				examsTarget.setLevel2(createExams.targetList.get(i).questionL2Count);
				examsTarget.setLevel3(createExams.targetList.get(i).questionL3Count);
				examsTarget.setLevel4(createExams.targetList.get(i).questionL4Count);
				examsTarget.setLevel5(createExams.targetList.get(i).questionL5Count);

				if (exams.getTimetype().equals("question")) {
					examsTarget.setLevel1time(createExams.targetList.get(i).questionL1Time);
					examsTarget.setLevel2time(createExams.targetList.get(i).questionL2Time);
					examsTarget.setLevel3time(createExams.targetList.get(i).questionL3Time);
					examsTarget.setLevel4time(createExams.targetList.get(i).questionL4Time);
					examsTarget.setLevel5time(createExams.targetList.get(i).questionL5Time);
				}
				examsTargetMapper.insertSelective(examsTarget);
			}
		}

		// Create exam base on exam count of exams
		for (int i = 0; i < exams.getExamcount(); i++) {
			Exam exam = new Exam();
			exam.setExamsid(IDExam);
			examMapper.insertSelective(exam);
			this.generateQuestionForExam(exam, createExams.targetList);
		}
		sqlSession.close();
		return IDExam;
	}

	public void generateQuestionForExam(Exam exam, List<TargetList> targetList) {
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		Exam_Question_BlockMapper examQuestionBlockMapper = sqlSession.getMapper(Exam_Question_BlockMapper.class);
		QuestionsMapper questionMapper = sqlSession.getMapper(QuestionsMapper.class);
		Questions_TargetMapper questionTargetMapper = sqlSession.getMapper(Questions_TargetMapper.class);
		ExamMapper examMapper = sqlSession.getMapper(ExamMapper.class);

		Exam_Question_BlockKey examQuestionBlock = new Exam_Question_BlockKey();

		examQuestionBlock.setExamsid(exam.getExamsid());
		/*
		 * Exam_Question
		 */
		// examQuestion.setExamsid(exam.getExamsid());

		/*
		 * Exam_Ansert
		 */

		/*
		 * Get ID EXAM
		 */
		int IdCurrentOfExam = examMapper.getIDExamCurrentByExam().getId().intValue();
		System.out.println("CHECK ID EXAM INSERT" + IdCurrentOfExam);
		examQuestionBlock.setExamid(examMapper.getIDExamCurrentByExam().getId());
		// examQuestion.setExamid(examMapper.getIDExamCurrentByExam().getId().intValue());//Exam_Question
		// Table

		Set<Integer> questionBlockIdTotalSet = new HashSet<Integer>();

		for (int i = 0; i < targetList.size(); i++) {
			Target target = targetList.get(i).target;
			List<Questions_TargetKey> questionTargetList = questionTargetMapper
					.getBySubjectIdChapterIdTargetId(target.getSubjectid(), target.getChapterid(), target.getId());
			Set<Integer> questionBlockIdSet;

			if (targetList.get(i).questionL1Count > 0) {
				List<Questions> questionL1List = questionMapper.getL1BySubjectIdChapterId(target.getSubjectid(),
						target.getChapterid());
				questionBlockIdSet = new HashSet<Integer>();
				for (int j = 0; j < questionL1List.size(); j++) {
					for (int z = 0; z < questionTargetList.size(); z++) {
						if (questionL1List.get(j).getId() == questionTargetList.get(z).getQuestionid()) {
							questionBlockIdSet.add(questionL1List.get(j).getQuestionblockid());
						}
					}
				}

				questionBlockIdSet.removeAll(questionBlockIdTotalSet);
				if (targetList.get(i).questionL1Count > questionBlockIdSet.size()) {
					System.out.println("Khong Du Cau Hoi");
				}

				if (questionBlockIdSet.size() >= targetList.get(i).questionL1Count) {
					List<Integer> questionBlockIdList = new ArrayList<Integer>();
					questionBlockIdList.addAll(questionBlockIdSet);
					Collections.shuffle(questionBlockIdList);
					for (int j = 0; j < targetList.get(i).questionL1Count; j++) {
						questionBlockIdTotalSet.add(questionBlockIdList.get(j));
						examQuestionBlock.setQuestionblockid(questionBlockIdList.get(j));
						examQuestionBlockMapper.insert(examQuestionBlock);
					}
				}
			}

			if (targetList.get(i).questionL2Count > 0) {
				List<Questions> questionL2List = questionMapper.getL2BySubjectIdChapterId(target.getSubjectid(),
						target.getChapterid());
				questionBlockIdSet = new HashSet<Integer>();
				for (int j = 0; j < questionL2List.size(); j++) {
					for (int z = 0; z < questionTargetList.size(); z++) {
						if (questionL2List.get(j).getId() == questionTargetList.get(z).getQuestionid()) {
							questionBlockIdSet.add(questionL2List.get(j).getQuestionblockid());
						}
					}
				}

				questionBlockIdSet.removeAll(questionBlockIdTotalSet);
				if (targetList.get(i).questionL2Count > questionBlockIdSet.size()) {
					System.out.println("Khong Du Cau Hoi");
				}

				if (questionBlockIdSet.size() >= targetList.get(i).questionL2Count) {
					List<Integer> questionBlockIdList = new ArrayList<Integer>();
					questionBlockIdList.addAll(questionBlockIdSet);
					Collections.shuffle(questionBlockIdList);
					for (int j = 0; j < targetList.get(i).questionL2Count; j++) {
						questionBlockIdTotalSet.add(questionBlockIdList.get(j));
						examQuestionBlock.setQuestionblockid(questionBlockIdList.get(j));
						examQuestionBlockMapper.insert(examQuestionBlock);
					}
				}
			}

			if (targetList.get(i).questionL3Count > 0) {
				List<Questions> questionL3List = questionMapper.getL3BySubjectIdChapterId(target.getSubjectid(),
						target.getChapterid());
				questionBlockIdSet = new HashSet<Integer>();
				for (int j = 0; j < questionL3List.size(); j++) {
					for (int z = 0; z < questionTargetList.size(); z++) {
						if (questionL3List.get(j).getId() == questionTargetList.get(z).getQuestionid()) {
							questionBlockIdSet.add(questionL3List.get(j).getQuestionblockid());
						}
					}
				}

				questionBlockIdSet.removeAll(questionBlockIdTotalSet);
				if (targetList.get(i).questionL3Count > questionBlockIdSet.size()) {
					System.out.println("Khong Du Cau Hoi");
				}

				if (questionBlockIdSet.size() >= targetList.get(i).questionL3Count) {
					List<Integer> questionBlockIdList = new ArrayList<Integer>();
					questionBlockIdList.addAll(questionBlockIdSet);
					Collections.shuffle(questionBlockIdList);
					for (int j = 0; j < targetList.get(i).questionL3Count; j++) {
						questionBlockIdTotalSet.add(questionBlockIdList.get(j));
						examQuestionBlock.setQuestionblockid(questionBlockIdList.get(j));
						examQuestionBlockMapper.insert(examQuestionBlock);
					}
				}
			}

			if (targetList.get(i).questionL4Count > 0) {
				List<Questions> questionL4List = questionMapper.getL4BySubjectIdChapterId(target.getSubjectid(),
						target.getChapterid());
				questionBlockIdSet = new HashSet<Integer>();
				for (int j = 0; j < questionL4List.size(); j++) {
					for (int z = 0; z < questionTargetList.size(); z++) {
						if (questionL4List.get(j).getId() == questionTargetList.get(z).getQuestionid()) {
							questionBlockIdSet.add(questionL4List.get(j).getQuestionblockid());
						}
					}
				}

				questionBlockIdSet.removeAll(questionBlockIdTotalSet);
				if (targetList.get(i).questionL4Count > questionBlockIdSet.size()) {
					System.out.println("Khong Du Cau Hoi");
				}

				if (questionBlockIdSet.size() >= targetList.get(i).questionL4Count) {
					List<Integer> questionBlockIdList = new ArrayList<Integer>();
					questionBlockIdList.addAll(questionBlockIdSet);
					Collections.shuffle(questionBlockIdList);
					for (int j = 0; j < targetList.get(i).questionL4Count; j++) {
						questionBlockIdTotalSet.add(questionBlockIdList.get(j));
						examQuestionBlock.setQuestionblockid(questionBlockIdList.get(j));
						examQuestionBlockMapper.insert(examQuestionBlock);
					}
				}
			}

			if (targetList.get(i).questionL5Count > 0) {
				List<Questions> questionL5List = questionMapper.getL5BySubjectIdChapterId(target.getSubjectid(),
						target.getChapterid());
				questionBlockIdSet = new HashSet<Integer>();
				for (int j = 0; j < questionL5List.size(); j++) {
					for (int z = 0; z < questionTargetList.size(); z++) {
						if (questionL5List.get(j).getId() == questionTargetList.get(z).getQuestionid()) {
							questionBlockIdSet.add(questionL5List.get(j).getQuestionblockid());
						}
					}
				}

				questionBlockIdSet.removeAll(questionBlockIdTotalSet);
				if (targetList.get(i).questionL5Count > questionBlockIdSet.size()) {
					System.out.println("Khong Du Cau Hoi");
				}

				if (questionBlockIdSet.size() >= targetList.get(i).questionL5Count) {
					List<Integer> questionBlockIdList = new ArrayList<Integer>();
					questionBlockIdList.addAll(questionBlockIdSet);
					Collections.shuffle(questionBlockIdList);
					for (int j = 0; j < targetList.get(i).questionL5Count; j++) {
						questionBlockIdTotalSet.add(questionBlockIdList.get(j));
						examQuestionBlock.setQuestionblockid(questionBlockIdList.get(j));
						examQuestionBlockMapper.insert(examQuestionBlock);
					}
				}
			}
		}
		sqlSession.close();
	}

	public void generateRootQuestionForExam(Exam exam, List<TargetList> targetList) {
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		Exam_Question_BlockMapper examQuestionBlockMapper = sqlSession.getMapper(Exam_Question_BlockMapper.class);
		Exam_QuestionMapper examQuestionMapper = sqlSession.getMapper(Exam_QuestionMapper.class);
		QuestionsMapper questionMapper = sqlSession.getMapper(QuestionsMapper.class);
		Questions_TargetMapper questionTargetMapper = sqlSession.getMapper(Questions_TargetMapper.class);
		ExamMapper examMapper = sqlSession.getMapper(ExamMapper.class);

		Exam_Question_BlockKey examQuestionBlock = new Exam_Question_BlockKey();
		// Exam_Question examQuestion = new Exam_Question();
		examQuestionBlock.setExamsid(exam.getExamsid());

		/*
		 * Get ID EXAM
		 */
		int IdCurrentOfExam = examMapper.getIDExamCurrentByExam().getId().intValue();

		examQuestionBlock.setExamid(examMapper.getIDExamCurrentByExam().getId());

		Set<Integer> questionBlockIdTotalSet = new HashSet<Integer>();

		for (int i = 0; i < targetList.size(); i++) {
			Target target = targetList.get(i).target;
			List<Questions_TargetKey> questionTargetList = questionTargetMapper
					.getBySubjectIdChapterIdTargetId(target.getSubjectid(), target.getChapterid(), target.getId());
			Set<Integer> questionBlockIdSet;

			if (targetList.get(i).questionL1Count > 0) {
				List<Questions> questionL1List = questionMapper.getL1BySubjectIdChapterId(target.getSubjectid(),
						target.getChapterid());
				questionBlockIdSet = new HashSet<Integer>();
				List<Integer> InsertQuestionId = new ArrayList<>();

				for (int j = 0; j < questionL1List.size(); j++) {
					for (int z = 0; z < questionTargetList.size(); z++) {
						if (questionL1List.get(j).getId() == questionTargetList.get(z).getQuestionid()) {
							questionBlockIdSet.add(questionL1List.get(j).getQuestionblockid());
							// InsertQuestionId.add(questionL1List.get(j).getId().intValue());
						}
					}
				}

				questionBlockIdSet.removeAll(questionBlockIdTotalSet);
				if (targetList.get(i).questionL1Count > questionBlockIdSet.size()) {
					System.out.println("Khong Du Cau Hoi");
				}

				if (questionBlockIdSet.size() >= targetList.get(i).questionL1Count) {
					List<Integer> questionBlockIdList = new ArrayList<Integer>();
					questionBlockIdList.addAll(questionBlockIdSet);
					Collections.shuffle(questionBlockIdList);
					for (int j = 0; j < targetList.get(i).questionL1Count; j++) {
						questionBlockIdTotalSet.add(questionBlockIdList.get(j));
						examQuestionBlock.setQuestionblockid(questionBlockIdList.get(j));
						examQuestionBlockMapper.insert(examQuestionBlock);

						System.out.println("questionBlock : " + questionBlockIdList.size());
						// for(int n =0 ; n < questionBlockIdList.size(); n++){
						List<Questions> IdQuestionInsert = questionMapper
								.getQuestionIDByBlockQuestion(questionBlockIdList.get(j).intValue());
						// examQuestion.setQuestionblockid(questionBlockIdList.get(j).intValue());
						System.out.println("ID Block " + questionBlockIdList.get(j).intValue() + "IdQuestionInsert "
								+ IdQuestionInsert.size());
						for (int k = 0; k < IdQuestionInsert.size(); k++) {
							System.out.println("LAN " + k);
							System.out.println(IdQuestionInsert.get(k).getId());
							// S//ystem.out..println(IdQuestionInsert.get(k).getId());

							// examQuestion.setQuestionid(IdQuestionInsert.get(k).getId().intValue());
							// System.out.println("Set Exam ID : "
							// +examQuestion.getExamid());
							// System.out.println("Set Exams ID : "
							// +examQuestion.getExamsid());
							// System.out.println("Set BlockQuestion ID : "
							// +examQuestion.getQuestionblockid());

							// examQuestionMapper.insert(examQuestion);
						}
						// }
					}
				}
			}
			if (targetList.get(i).questionL2Count > 0) {
				List<Questions> questionL2List = questionMapper.getL2BySubjectIdChapterId(target.getSubjectid(),
						target.getChapterid());
				questionBlockIdSet = new HashSet<Integer>();
				// List<Integer> InsertQuestionId = new ArrayList<>();
				System.out.println("questionL2List.size()" + questionL2List.size());

				for (int j = 0; j < questionL2List.size(); j++) {
					for (int z = 0; z < questionTargetList.size(); z++) {
						if (questionL2List.get(j).getId() == questionTargetList.get(z).getQuestionid()) {
							questionBlockIdSet.add(questionL2List.get(j).getQuestionblockid());
							// InsertQuestionId.add(j,
							// questionL2List.get(j).getId().intValue());
						}
					}
				}

				questionBlockIdSet.removeAll(questionBlockIdTotalSet);
				if (targetList.get(i).questionL2Count > questionBlockIdSet.size()) {
					System.out.println("Khong Du Cau Hoi");
				}

				if (questionBlockIdSet.size() >= targetList.get(i).questionL2Count) {
					List<Integer> questionBlockIdList = new ArrayList<Integer>();
					questionBlockIdList.addAll(questionBlockIdSet);
					Collections.shuffle(questionBlockIdList);
					for (int j = 0; j < targetList.get(i).questionL2Count; j++) {
						questionBlockIdTotalSet.add(questionBlockIdList.get(j));
						examQuestionBlock.setQuestionblockid(questionBlockIdList.get(j));
						examQuestionBlockMapper.insert(examQuestionBlock);

						// for(int n =0 ; n < questionBlockIdList.size(); n++){
						List<Questions> IdQuestionInsert = questionMapper
								.getQuestionIDByBlockQuestion(questionBlockIdList.get(j).intValue());

						System.out.println("ID Block " + questionBlockIdList.get(j).intValue() + "IdQuestionInsert "
								+ IdQuestionInsert.size());
						for (int k = 0; k < IdQuestionInsert.size(); k++) {
							// examQuestion.setQuestionid(IdQuestionInsert.get(k).getId().intValue());
							// examQuestion.setQuestionblockid(questionBlockIdList.get(j).intValue());
							// examQuestionMapper.insert(examQuestion);
						}
					}
				}
			}

			if (targetList.get(i).questionL3Count > 0) {
				List<Questions> questionL3List = questionMapper.getL3BySubjectIdChapterId(target.getSubjectid(),
						target.getChapterid());
				questionBlockIdSet = new HashSet<Integer>();
				// List<Integer> InsertQuestionId = new ArrayList<>();
				for (int j = 0; j < questionL3List.size(); j++) {
					for (int z = 0; z < questionTargetList.size(); z++) {
						if (questionL3List.get(j).getId() == questionTargetList.get(z).getQuestionid()) {
							questionBlockIdSet.add(questionL3List.get(j).getQuestionblockid());
							// InsertQuestionId.add(questionL3List.get(j).getId().intValue());
						}
					}
				}

				questionBlockIdSet.removeAll(questionBlockIdTotalSet);
				if (targetList.get(i).questionL3Count > questionBlockIdSet.size()) {
					System.out.println("Khong Du Cau Hoi");
				}

				if (questionBlockIdSet.size() >= targetList.get(i).questionL3Count) {
					List<Integer> questionBlockIdList = new ArrayList<Integer>();
					questionBlockIdList.addAll(questionBlockIdSet);
					Collections.shuffle(questionBlockIdList);
					for (int j = 0; j < targetList.get(i).questionL3Count; j++) {
						questionBlockIdTotalSet.add(questionBlockIdList.get(j));
						examQuestionBlock.setQuestionblockid(questionBlockIdList.get(j));
						examQuestionBlockMapper.insert(examQuestionBlock);

						// for(int n =0 ; n < questionBlockIdList.size(); n++){
						List<Questions> IdQuestionInsert = questionMapper
								.getQuestionIDByBlockQuestion(questionBlockIdList.get(j).intValue());

						System.out.println("ID Block " + questionBlockIdList.get(j).intValue() + "IdQuestionInsert "
								+ IdQuestionInsert.size());
						for (int k = 0; k < IdQuestionInsert.size(); k++) {
							// examQuestion.setQuestionid(IdQuestionInsert.get(k).getId().intValue());
							// examQuestion.setQuestionblockid(questionBlockIdList.get(j).intValue());
							// examQuestionMapper.insert(examQuestion);
						}
					}
				}
			}

			if (targetList.get(i).questionL4Count > 0) {
				List<Questions> questionL4List = questionMapper.getL4BySubjectIdChapterId(target.getSubjectid(),
						target.getChapterid());
				questionBlockIdSet = new HashSet<Integer>();
				// List<Integer> InsertQuestionId = new ArrayList<>();
				for (int j = 0; j < questionL4List.size(); j++) {
					for (int z = 0; z < questionTargetList.size(); z++) {
						if (questionL4List.get(j).getId() == questionTargetList.get(z).getQuestionid()) {
							questionBlockIdSet.add(questionL4List.get(j).getQuestionblockid());
						}
					}
				}

				questionBlockIdSet.removeAll(questionBlockIdTotalSet);
				if (targetList.get(i).questionL4Count > questionBlockIdSet.size()) {
					System.out.println("Khong Du Cau Hoi");
				}

				if (questionBlockIdSet.size() >= targetList.get(i).questionL4Count) {
					List<Integer> questionBlockIdList = new ArrayList<Integer>();
					questionBlockIdList.addAll(questionBlockIdSet);
					Collections.shuffle(questionBlockIdList);
					for (int j = 0; j < targetList.get(i).questionL4Count; j++) {
						questionBlockIdTotalSet.add(questionBlockIdList.get(j));
						examQuestionBlock.setQuestionblockid(questionBlockIdList.get(j));
						examQuestionBlockMapper.insert(examQuestionBlock);

						// for(int n =0 ; n < questionBlockIdList.size(); n++){
						List<Questions> IdQuestionInsert = questionMapper
								.getQuestionIDByBlockQuestion(questionBlockIdList.get(j).intValue());

						System.out.println("ID Block " + questionBlockIdList.get(j).intValue() + "IdQuestionInsert "
								+ IdQuestionInsert.size());
						for (int k = 0; k < IdQuestionInsert.size(); k++) {
							// examQuestion.setQuestionid(IdQuestionInsert.get(k).getId().intValue());
							// examQuestion.setQuestionblockid(questionBlockIdList.get(j).intValue());
							// examQuestionMapper.insert(examQuestion);
						}
					}
				}
			}

			if (targetList.get(i).questionL5Count > 0) {
				List<Questions> questionL5List = questionMapper.getL5BySubjectIdChapterId(target.getSubjectid(),
						target.getChapterid());
				questionBlockIdSet = new HashSet<Integer>();
				// List<Integer> InsertQuestionId = new ArrayList<>();
				for (int j = 0; j < questionL5List.size(); j++) {
					for (int z = 0; z < questionTargetList.size(); z++) {
						if (questionL5List.get(j).getId() == questionTargetList.get(z).getQuestionid()) {
							questionBlockIdSet.add(questionL5List.get(j).getQuestionblockid());
							// InsertQuestionId.add(questionL5List.get(j).getId().intValue());
						}
					}
				}

				questionBlockIdSet.removeAll(questionBlockIdTotalSet);
				if (targetList.get(i).questionL5Count > questionBlockIdSet.size()) {
					System.out.println("Khong Du Cau Hoi");
				}

				if (questionBlockIdSet.size() >= targetList.get(i).questionL5Count) {
					List<Integer> questionBlockIdList = new ArrayList<Integer>();
					questionBlockIdList.addAll(questionBlockIdSet);
					// Collections.shuffle(questionBlockIdList);
					for (int j = 0; j < targetList.get(i).questionL5Count; j++) {
						questionBlockIdTotalSet.add(questionBlockIdList.get(j));
						examQuestionBlock.setQuestionblockid(questionBlockIdList.get(j));
						examQuestionBlockMapper.insert(examQuestionBlock);

						// for(int n =0 ; n < questionBlockIdList.size(); n++){
						List<Questions> IdQuestionInsert = questionMapper
								.getQuestionIDByBlockQuestion(questionBlockIdList.get(j).intValue());

						System.out.println("ID Block " + questionBlockIdList.get(j).intValue() + "IdQuestionInsert "
								+ IdQuestionInsert.size());
						for (int k = 0; k < IdQuestionInsert.size(); k++) {
							// examQuestion.setQuestionid(IdQuestionInsert.get(k).getId().intValue());
							// examQuestion.setQuestionblockid(questionBlockIdList.get(j).intValue());
							// examQuestionMapper.insert(examQuestion);
						}
					}
				}
			}
		}

		sqlSession.close();
	}

	@Override
	public List<Integer> getStudentExamsCountBySubjectId(long subjectId, List<Integer> examsIdList) {
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);

		Student_ExamMapper studentExamMapper = sqlSession.getMapper(Student_ExamMapper.class);
		List<Integer> studentCount = new ArrayList<Integer>();
		for (int i = 0; i < examsIdList.size(); i++) {
			studentCount
					.add(studentExamMapper.getStudentExamsCountBySubjectId(subjectId, examsIdList.get(i)).intValue());
		}

		sqlSession.close();
		return studentCount;

	}

	@Override
	public List<Integer> getStudentExamsCountByClassId(long classId, List<Integer> examsIdList) {
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);

		Student_ExamMapper studentExamMapper = sqlSession.getMapper(Student_ExamMapper.class);
		List<Integer> studentCount = new ArrayList<Integer>();
		for (int i = 0; i < examsIdList.size(); i++) {
			studentCount.add(studentExamMapper.getStudentExamsCountByClassId(classId, examsIdList.get(i)).intValue());
		}

		sqlSession.close();
		return studentCount;
	}

	@Override
	public long getExamsCountOfStudentByClassId(long classId, long studentId, long examsId) {
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);

		Student_ExamMapper studentExamMapper = sqlSession.getMapper(Student_ExamMapper.class);
		System.out.println(classId);
		System.out.println(studentId);
		System.out.println(examsId);
		Long result = studentExamMapper.getExamsCountOfStudentByClassId(classId, studentId, examsId);
		sqlSession.close();
		return result;
	}

	@Override
	public long getExamsCountOfStudentBySubjectId(long subjectId, long studentId, long examsId) {
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		Student_ExamMapper studentExamMapper = sqlSession.getMapper(Student_ExamMapper.class);
		Long result = studentExamMapper.getExamsCountOfStudentBySubjectId(subjectId, studentId, examsId);
		sqlSession.close();
		return result;
	}

	@Override
	public int approve(long examsId, Long teacherId) {
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		ExamsMapper examsMapper = sqlSession.getMapper(ExamsMapper.class);
		Exams exams = new Exams();
		exams.setId(examsId);
		exams.setStatus("approved");
		exams.setApproverid(teacherId.intValue());
		int result = examsMapper.updateByPrimaryKeySelective(exams);
		sqlSession.close();
		return result;
	}

	@Override
	public List<Student_Exam> getResultExam(Long classId, Long studentId, Long examsId) {
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		Student_ExamMapper studentExamMapper = sqlSession.getMapper(Student_ExamMapper.class);
		List<Student_Exam> result = studentExamMapper.getResultExam(classId, studentId, examsId);
		sqlSession.close();
		return result;
	}

	@Override
	public ExamsInfo getExams(long examsId) {
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		ExamsMapper examsMapper = sqlSession.getMapper(ExamsMapper.class);
		Exams_TargetMapper examsTargetMapper = sqlSession.getMapper(Exams_TargetMapper.class);
		Exams exams = examsMapper.selectByID(examsId);
		List<Exams_Target> examsTargetList = examsTargetMapper.getByExamsId(examsId);
		ExamsInfo examsInfo = new ExamsInfo();
		examsInfo.exams = exams;
		examsInfo.examsTargetList = examsTargetList;
		sqlSession.close();
		return examsInfo;

	}

	@Override
	public long createRootExams(CreateExams Rootexams) {
		// TODO Auto-generated method stub
		int IDExam = 0;
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		ExamMapper examMapper = sqlSession.getMapper(ExamMapper.class);
		ExamsMapper mapper = sqlSession.getMapper(ExamsMapper.class);
		Exams_TargetMapper examsTargetMapper = sqlSession.getMapper(Exams_TargetMapper.class);
		Exams_InfoMapper examsInfoMapper = sqlSession.getMapper(Exams_InfoMapper.class);
		if (mapper.getIDExamCurrent() == null) {
			Exams exams2 = Rootexams.exams;
			exams2.setId((long) 1);
			IDExam = 1;
		} else {
			IDExam = mapper.getIDExamCurrent().getId().intValue() + 1;
		}
		// Create Root exams
		Exams Rexams = Rootexams.exams;
		Date now = new Date();
		Rexams.setModifiedat(now);
		Rexams.setCreatedat(now);
		Rexams.setStatus("request");
		Rexams.setExamtype("offline");
		mapper.insertSelective(Rexams);

		System.out.println("TargetList SIZE : " + Rootexams.targetList.size());
		// System.out.println("Target Frist : " +
		// Rootexams.targetList.get(0).questionL1Count);

		for (int i = 0; i < Rootexams.targetList.size(); i++) {
			if (Rootexams.targetList.get(i).questionL1Count + Rootexams.targetList.get(i).questionL2Count
					+ Rootexams.targetList.get(i).questionL3Count + Rootexams.targetList.get(i).questionL4Count
					+ Rootexams.targetList.get(i).questionL5Count > 0) {

				Exams_Target examsTarget = new Exams_Target();
				// System.out.println("= examsTarget => Examsid :" + (long)
				// IDExam);
				examsTarget.setExamsid((long) IDExam);
				examsTarget.setTargetid(Rootexams.targetList.get(i).target.getId().intValue());
				examsTarget.setChapterid(Rootexams.targetList.get(i).target.getChapterid());
				examsTarget.setSubjectid(Rootexams.targetList.get(i).target.getSubjectid());

				examsTarget.setLevel1(Rootexams.targetList.get(i).questionL1Count);
				examsTarget.setLevel2(Rootexams.targetList.get(i).questionL2Count);
				examsTarget.setLevel3(Rootexams.targetList.get(i).questionL3Count);
				examsTarget.setLevel4(Rootexams.targetList.get(i).questionL4Count);
				examsTarget.setLevel5(Rootexams.targetList.get(i).questionL5Count);

				examsTargetMapper.insertSelective(examsTarget);
			}
		}
		for (int i = 0; i < 1; i++) {
			Exam rootexam = new Exam();
			rootexam.setExamsid(IDExam);
			examMapper.insertSelective(rootexam);
			this.generateRootQuestionForExam(rootexam, Rootexams.targetList);
		}

		// Insert Exam Table Infor

		Exams_Info examsInfo = new Exams_Info();

		examsInfo.setCreatedat(now);
		examsInfo.setModifiedat(now);
		examsInfo.setExamsid(IDExam);
		examsInfo.setNoteexam(Rootexams.getNoteExams());
		examsInfo.setTimes(Rootexams.getTimes());

		// System.out.println("Check PASS INSERT EXAMS INFO");
		examsInfoMapper.insert(examsInfo);

		sqlSession.close();
		return IDExam;
	}

	@Override
	public Exams fetchRootExamByExamId(long rootExams) {
		// TODO Auto-generated method stub
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		ExamsMapper mapper = sqlSession.getMapper(ExamsMapper.class);
		Exams rootExam = mapper.getRootExamId(rootExams);

		sqlSession.close();
		return rootExam;
	}

	@Override
	public int insertExamInfoByExam(RootExamTemp exams_info) {
		// TODO Auto-generated method stub
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		Exams_InfoMapper examsInfoMapper = sqlSession.getMapper(Exams_InfoMapper.class);
		ExamsMapper examsMapper = sqlSession.getMapper(ExamsMapper.class);
		int examsID = 1;
		if (examsMapper.getIDExamCurrent() == null) {
			examsID = 1;
		} else {

			examsID = examsMapper.getIDExamCurrent().getId().intValue() + 1;
		}
		Date now = new Date();
		Exams_Info exams = new Exams_Info();

		exams.setCreatedat(now);
		exams.setModifiedat(now);
		exams.setExamsid(examsID);
		exams.setNoteexam(exams_info.getNoteExam());
		exams.setTimes(String.valueOf(exams_info.getTimes()));
		System.out.println("Check PASS INSERT EXAMS INFO");
		int examsInfo = examsInfoMapper.insert(exams);
		sqlSession.close();
		return examsInfo;
	}

	@Override
	public int UpdateRootExamCount(ExamCount exams) {
		// TODO Auto-generated method stub
		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		ExamsMapper examsMapper = sqlSession.getMapper(ExamsMapper.class);
		ExamMapper examMapper = sqlSession.getMapper(ExamMapper.class);

		// Exams tempExams = examsMapper.getRootExamId(exams.getId());
		Exams Aexams = new Exams();
		Aexams.setId((long) exams.getId());
		Aexams.setExamcount(exams.getExamCount());
		examsMapper.updateByPrimaryKeySelective(Aexams);

		Exam exam = new Exam();
		exam = examMapper.getIdExamByExams(exams.id);

		if (exams.getExamCount() > 1) {
			this.CreateRootExamCount(exams.id, exams.getExamCount(), exams.getCodeExam());
		}
		sqlSession.close();
		return 0;
	}

	public void CreateRootExamCount(int ExamsId, int ExamCount,String codeExam) {
		// TODO Auto-generated method stub

		SqlSessionFactory sqlMapper = MyBatisService.getSessionFactory();
		SqlSession sqlSession = sqlMapper.openSession(true);
		ExamMapper examMapper = sqlSession.getMapper(ExamMapper.class);
		ExamsMapper mapper = sqlSession.getMapper(ExamsMapper.class);
		// Exams_TargetMapper examsTargetMapper =
		// sqlSession.getMapper(Exams_TargetMapper.class);
		Exam_Question_BlockMapper examQuestionBlockMapper = sqlSession.getMapper(Exam_Question_BlockMapper.class);
		Exam_QuestionMapper examQuestionMapper = sqlSession.getMapper(Exam_QuestionMapper.class);
		Exam_AnswerMapper examAnswerMapper = sqlSession.getMapper(Exam_AnswerMapper.class);
		// Get List Block Question
		List<Exam_Question_BlockKey> examQuestionBlock = examQuestionBlockMapper
				.getIdBlockQuestionOfRootExamId(ExamsId);

		// Get List Exam_Question
		//Collections.shuffle(examQuestionBlock);
		// Create Root exams
		for (int i = 0; i < ExamCount - 1; i++) {
			// Collections.shuffle(examQuestionBlock);
			// Insert Exam
			Exam exam = new Exam();
			exam.setExamsid(ExamsId);
			exam.setExamcode(randomCodeExam(codeExam));
			examMapper.insertSelective(exam);
			Exam IDinsert = examMapper.getIDExamCurrentByExam();
			// System.out.println("getExamId : " +IDinsert.getId());
			// System.out.println("examQuestionBlock.size "
			// +examQuestionBlock.size() );
			// Insert Exam_Block_Question
			Collections.shuffle(examQuestionBlock);
			for (int n = 0; n < examQuestionBlock.size(); n++) {

				Exam_Question_BlockKey examBlockQuestion = new Exam_Question_BlockKey();
				examBlockQuestion.setExamid(IDinsert.getId());
				examBlockQuestion.setExamsid(ExamsId);
				examBlockQuestion.setQuestionblockid(examQuestionBlock.get(n).getQuestionblockid());

				examQuestionBlockMapper.insertSelective(examBlockQuestion);

				// Insert Exam_Question
				List<Exam_Question> examQuestion = examQuestionMapper.getAllQuestionIDByExamAndIDExamsAndIdBlock(
						examQuestionBlock.get(n).getExamid(), examQuestionBlock.get(n).getExamsid(),
						examQuestionBlock.get(n).getQuestionblockid());
				Collections.shuffle(examQuestion);
				for (int k = 0; k < examQuestion.size(); k++) {
					// Insert Exam_Question
					Exam_Question exam_Question = new Exam_Question();
					exam_Question.setExamid(IDinsert.getId().intValue());
					exam_Question.setExamsid(examQuestion.get(k).getExamsid());
					exam_Question.setQuestionblockid(examQuestion.get(k).getQuestionblockid());
					exam_Question.setQuestionid(examQuestion.get(k).getQuestionid());
					examQuestionMapper.insertSelective(exam_Question);

					// Insert Answer
					List<Exam_Answer> examAnswer = examAnswerMapper.getIdAnswerByExam(
							examQuestion.get(k).getQuestionid(), examQuestion.get(k).getQuestionblockid(),
							examQuestion.get(k).getExamid());
					Collections.shuffle(examAnswer);
					for (int l = 0; l < examAnswer.size(); l++) {
						Exam_Answer examAnswer123 = new Exam_Answer();
						// Insert Exam_Answer
						examAnswer123.setAnswerid(examAnswer.get(l).getAnswerid());
						examAnswer123.setExamid(IDinsert.getId().intValue());
						examAnswer123.setQuestionblockid(examAnswer.get(l).getQuestionblockid());
						examAnswer123.setQuestionid(examAnswer.get(l).getQuestionid());
						examAnswerMapper.insertSelective(examAnswer123);

					}

				}

			}
		}
		sqlSession.close();
	}

	public String randomCodeExam(String codeExam) {
		char[] chars = codeExam.toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < chars.length; i++) {
			char c = chars[random.nextInt(chars.length)];
			sb.append(c);
		}
		String output = sb.toString();
		return output;
	}

}
