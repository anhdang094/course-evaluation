package thanhtuu.springmvc.Temporary.Exam;

import java.util.ArrayList;
import java.util.List;

import thanhtuu.springmvc.Domain.Question_Blocks;
import thanhtuu.springmvc.Domain.Question_BlocksWithBLOBs;
import thanhtuu.springmvc.Temporary.SingleQuestion;

public class RootBlocksQuestion {
    public Question_BlocksWithBLOBs rootQuestionBlock;
    public List<SingleQuestion> questionList = new ArrayList<SingleQuestion>();
}
