package thanhtuu.springmvc.ExportPDF;

import thanhtuu.springmvc.Temporary.Exam.ListExam;

public class ExportExam {
	private String Faulty;
	private String examName;
	private String Code;
	private String teacherName;
	private String subjectName;
	private String time;
	private String noteExam;
	

	public String getNoteExam() {
		return noteExam;
	}

	public void setNoteExam(String noteExam) {
		this.noteExam = noteExam;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getFaulty() {
		return Faulty;
	}

	public void setFaulty(String faulty) {
		Faulty = faulty;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public ListExam listExam;

}
