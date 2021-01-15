package thanhtuu.springmvc.Temporary.Exam;

import java.util.List;

import thanhtuu.springmvc.Constains.Answer;
import thanhtuu.springmvc.Domain.Answers;
import thanhtuu.springmvc.Domain.Exam_Answer;
import thanhtuu.springmvc.Domain.Exam_Question;
import thanhtuu.springmvc.Domain.Exam_Question_BlockKey;

public class UpdateRootExam {
	public List<Exam_Question_BlockKey> blockQuestionAddList;

	public List<Exam_Question_BlockKey> blockQuestionRemoveList;
	
	public List<Exam_Question> examQuestionRemoveList;
	
	public List<Exam_Question> examQuestionAddList;
	
	public List<Exam_Answer> examAnswerRemoveList;
	
	public List<Answers> examAnswerAddList;
	
	public int countExam;
    
	public int IdExams;
	
	public String codeExam;
	
	public String getCodeExam() {
		return codeExam;
	}

	public void setCodeExam(String codeExam) {
		this.codeExam = codeExam;
	}

	public int getIdExams() {
		return IdExams;
	}

	public void setIdExams(int idExams) {
		IdExams = idExams;
	}

	public int getCountExam() {
		return countExam;
	}

	public void setCountExam(int countExam) {
		this.countExam = countExam;
	}
}
