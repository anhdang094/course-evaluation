package thanhtuu.springmvc.Temporary;

import java.util.List;

import thanhtuu.springmvc.Domain.Exams;

public class CreateExams {
	public String noteExams;
	public String times;
	public String getTimes() {
		return times;
	}
	public void setTimes(String times) {
		this.times = times;
	}
	public Exams exams;
	public String getNoteExams() {
		return noteExams;
	}
	public void setNoteExams(String noteExams) {
		this.noteExams = noteExams;
	}
	public List<TargetList> targetList; 
}
