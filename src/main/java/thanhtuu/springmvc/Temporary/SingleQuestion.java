package thanhtuu.springmvc.Temporary;

import java.util.ArrayList;
import java.util.List;

import thanhtuu.springmvc.Domain.Answers;
import thanhtuu.springmvc.Domain.Questions;
import thanhtuu.springmvc.Domain.Questions_TargetKey;

public class SingleQuestion {
	public Questions question;
	public List<Answers> answerList = new ArrayList<Answers>();
	public List<Questions_TargetKey> questionTargetList = new ArrayList<Questions_TargetKey>();
}
