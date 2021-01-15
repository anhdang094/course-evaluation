package thanhtuu.springmvc.Temporary;

import java.util.List;
import thanhtuu.springmvc.Domain.Exam_Question_BlockKey;

public class UpdateExam {
	public List<Exam_Question_BlockKey> blockQuestionAddList;

	public List<Exam_Question_BlockKey> getBlockQuestionAddList() {
		return blockQuestionAddList;
	}

	public void setBlockQuestionAddList(List<Exam_Question_BlockKey> blockQuestionAddList) {
		this.blockQuestionAddList = blockQuestionAddList;
	}

	public List<Exam_Question_BlockKey> getBlockQuestionRemoveList() {
		return blockQuestionRemoveList;
	}

	public void setBlockQuestionRemoveList(List<Exam_Question_BlockKey> blockQuestionRemoveList) {
		this.blockQuestionRemoveList = blockQuestionRemoveList;
	}

	public List<Exam_Question_BlockKey> blockQuestionRemoveList;
}
