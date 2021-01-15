package thanhtuu.springmvc.Temporary;

import java.util.ArrayList;
import java.util.List;

import thanhtuu.springmvc.Domain.Question_Blocks;
import thanhtuu.springmvc.Domain.Question_BlocksWithBLOBs;

public class BlockQuestion {
	public Question_BlocksWithBLOBs blockQuestion;
	public List<SingleQuestion> questionList = new ArrayList<SingleQuestion>();
}
