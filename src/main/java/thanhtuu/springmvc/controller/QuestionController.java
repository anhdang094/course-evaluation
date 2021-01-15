package thanhtuu.springmvc.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import thanhtuu.springmvc.Domain.Answers;
import thanhtuu.springmvc.Domain.Question_Blocks;
import thanhtuu.springmvc.Domain.Question_BlocksWithBLOBs;
import thanhtuu.springmvc.Domain.Questions;
import thanhtuu.springmvc.Domain.Users;
import thanhtuu.springmvc.Service.ExamServiceLocal;
import thanhtuu.springmvc.Service.QuestionServiceLocal;
import thanhtuu.springmvc.Service.UsersServiceLocal;
import thanhtuu.springmvc.Temporary.BlockQuestion;
import thanhtuu.springmvc.Temporary.LatexQuestion;
import thanhtuu.springmvc.Temporary.SingleQuestion;

@RestController
public class QuestionController {
	@Autowired
	private UsersServiceLocal usersService;
	
	@Autowired
	private QuestionServiceLocal questionService;
	
	@Autowired
	private ExamServiceLocal examService;
	
	@ResponseBody
    @ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/saveBlockQuestionList", method = RequestMethod.POST)
	public void saveBlockQuestionList(@RequestBody List<BlockQuestion> blockQuestionList, Principal principal) {
		String email = principal.getName();
		Users userlogin = usersService.getIdByEmail(email);
		if (userlogin.getRole().equals("teacher")) {
			questionService.saveBlockQuestionList(blockQuestionList, userlogin.getId().intValue());
		}
	}
	
	@ResponseBody
    @ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/api/getQuestionFromLatex", method = RequestMethod.POST)
	public List<BlockQuestion> getQuestionFromLatex(@RequestBody LatexQuestion latextQuestion, Principal principal) {
		String email = principal.getName();
		Users userlogin = usersService.getIdByEmail(email);
		if (userlogin.getRole().equals("teacher")) {
			List<Integer> questionBlockIdList = questionService.getQuestionFromLatex(latextQuestion, userlogin.getId().intValue());
			if(questionBlockIdList.size() > 0) {
				List<BlockQuestion> blockQuestionList = new ArrayList<BlockQuestion>();
				List<Question_BlocksWithBLOBs> questionBlockList = questionService.getQuestionBlockInList(questionBlockIdList);
				List<Questions> questionList = examService.getQuestionByBlockQuestionIdList(questionBlockIdList);
				List<Integer> questionIdList = new ArrayList<Integer>();
				for(int i = 0; i < questionList.size(); i++) {
					questionIdList.add(questionList.get(i).getChapterid().intValue());
				}
				List<Answers> answerList = examService.getAnswerByQuestionIdList(questionIdList);					
				for (int i = 0; i < questionBlockList.size(); i++) {
					System.out.println("question block " + i);
					BlockQuestion blockQuestion = new BlockQuestion();
					blockQuestion.questionList = new ArrayList<SingleQuestion>();
					blockQuestion.blockQuestion = questionBlockList.get(i);
					for(int j = 0; j < questionList.size(); j++) {
						Questions question = questionList.get(j);
						if(question.getQuestionblockid().equals(questionBlockList.get(i).getId().intValue())) {
							System.out.println("question " + i);
							SingleQuestion singleQuestion = new SingleQuestion();
							singleQuestion.question = question;
							singleQuestion.answerList = new ArrayList<Answers>();
							for(int z = 0; z < answerList.size(); z++) {
								if(answerList.get(z).getQuestionid().equals(question.getId().intValue())) {
									System.out.println("answer " + i);
									singleQuestion.answerList.add(answerList.get(z));
								}
							}
							blockQuestion.questionList.add(singleQuestion);
						}
					}
					blockQuestionList.add(blockQuestion);
				}	
				return blockQuestionList;
			}
		}
		return new ArrayList<BlockQuestion>();
	}
}
