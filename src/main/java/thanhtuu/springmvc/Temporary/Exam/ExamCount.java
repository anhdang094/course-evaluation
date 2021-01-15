package thanhtuu.springmvc.Temporary.Exam;

public class ExamCount {
	public int id;
	public int examCount;
	public String codeExam;

	public String getCodeExam() {
		return codeExam;
	}

	public void setCodeExam(String codeExam) {
		this.codeExam = codeExam;
	}

	public int getExamCount() {
		return examCount;
	}

	public void setExamCount(int examCount) {
		this.examCount = examCount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
