package thanhtuu.springmvc.Temporary.Exam;


import java.util.ArrayList;
import java.util.List;

import thanhtuu.springmvc.Domain.Exam;

public class ListExam {
	int examId;
	String CodeExam;
	int examsId;
	public int getExamId() {
		return examId;
	}
	public void setExamId(int examId) {
		this.examId = examId;
	}
	public String getCodeExam() {
		return CodeExam;
	}
	public void setCodeExam(String codeExam) {
		CodeExam = codeExam;
	}
	public int getExamsId() {
		return examsId;
	}
	public void setExamsId(int examsId) {
		this.examsId = examsId;
	}
	public List<RootBlocksQuestion> blockRootQuestion;
	
	

}
