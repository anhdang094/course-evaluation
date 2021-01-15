package thanhtuu.springmvc.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import thanhtuu.springmvc.Domain.Question;
import thanhtuu.springmvc.Domain.QuestionWithBLOBs;
import thanhtuu.springmvc.Domain.Username;
import thanhtuu.springmvc.Service.QuestionService;
import thanhtuu.springmvc.Service.QuestionServiceLocal;
import thanhtuu.springmvc.Service.UsernameServiceLocal;

@Controller
public class QuestionController {

	private static String format(String line) {

		while (line.startsWith(" ")) {
			line = line.substring(1, line.length());
		}
		while (line.endsWith(" ")) {
			line = line.substring(0, line.length() - 1);
		}
		return line;
	}

	@Autowired
	private QuestionServiceLocal questionservice;
    private UsernameServiceLocal usernameservice;
	@RequestMapping(value = "/getexam")
	public String listquestion(Model model) {
		List<Question> qList = questionservice.getQuestionList();
	//	List<QuestionWithBLOBs> qList2 = questionservice.getQuestionWithBLOBsList();
		model.addAttribute("question2", qList);
	//	model.addAttribute("question2", qList2);
		return "viewexam";
	}

	@RequestMapping(value = "/inputexam", method = RequestMethod.GET)
	public String inputquestion(Model model, Integer id) {
		Question question;
		if (id != null) {
			question = questionservice.getByQuestionId(id);
		} else {
			question = new Question();
		}
		model.addAttribute("question", question);
		/*
		 * List <Question> qList = questionservice.getQuestionList();
		 * model.addAttribute("question", qList);
		 */
		return "inputexam";
	}

	/*
	 * @RequestMapping(value="/inputexam", method=RequestMethod.POST) public
	 * String inputpostquestion(Model model, QuestionWithBLOBs questionW) {
	 * if(questionW.getQuestionid()==null){ questionservice.insert(questionW); }
	 * return "inputexam"; }
	 */

	@RequestMapping(value = "/formgetexam", method = RequestMethod.GET)
	public String formGquestion(Model model) {
		
		List<Question> getsubjectsCreateExam = questionservice.getSubjectsList();
		//Question
		model.addAttribute("listsubject",getsubjectsCreateExam );
		return "formgetexam";
	}

	@RequestMapping(value = "/formgetexam", method = RequestMethod.POST)
	public String formPquestion(Model model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//String name = request.getParameter("text1");
		String examid = request.getParameter("examid");
		String numberQuestion = request.getParameter("NumberQ");
		String subject = request.getParameter("subjects");
		String times = request.getParameter("times");
		String code = request.getParameter("code");
		String dateexam = request.getParameter("dateexam");
		List<Question> queryQuestion = questionservice.getquerySubjectQuestion(subject);
		//List<Question> queryQuestion = questionservice.getqueryExamQuestion(Integer.parseInt(examid),1);
		
		System.out.println("subjects :"+ subject);
		
		int sizeList = queryQuestion.size();
		
	
		
		List<Question> arrayQuestion = new ArrayList<Question>();
		
		System.out.println("listsize:"+queryQuestion.size());
		
		System.out.println("numbersize:"+Integer.parseInt(numberQuestion));
		
			if(queryQuestion.size()<Integer.parseInt(numberQuestion)){
				System.out.println("Số câu hỏi không đủ có xin hãy tạo thêm câu hỏi !");
				
			}
			else if(queryQuestion.size()> Integer.parseInt(numberQuestion)){
			  int number = queryQuestion.size()- Integer.parseInt(numberQuestion);
			  System.out.println("number:"+ number);
			 
			  for(int i=0; i<number; i++){
				  System.out.println("number i:"+ i);
				  /*
				   * Radom position delete
				   */
				  Random r = new Random();
				  int start = 0;
				  int end = queryQuestion.size();
				  int Result = r.nextInt(end-start) + start;
				  System.out.println("Result :"+ Result);
				  queryQuestion.remove(Result);
			  }
			  
			  model.addAttribute("question", queryQuestion);
			  
			}
			else {
				model.addAttribute("question", queryQuestion);
			}
			
			 
		model.addAttribute("subjects", subject);
		model.addAttribute("times", times);
		model.addAttribute("code", code);
		model.addAttribute("dateexam",dateexam);
		 
	//	model.addAttribute("question2", queryQuestion);
		return "hello";
	}

	@RequestMapping(value = "/question/{QuestionID}/delete", method = RequestMethod.GET)
	public String DeleteQuestion(@PathVariable long QuestionID, Model model) {
		questionservice.deleteQuestionId(QuestionID);
		return "redirect:/getexam";
	}

	@RequestMapping(value = "question/{QuestionID}/edit", method = RequestMethod.GET)
	public String EditGQuestion(@PathVariable long QuestionID, Model model) {
		Question question = questionservice.getByQuestionId(QuestionID);
		model.addAttribute("editQuestion", question);
		return "editquestion";
	}
	
	@RequestMapping(value = "question/{QuestionID}/edit", method = RequestMethod.POST)
	public String EditPQuestion(@PathVariable long QuestionID, QuestionWithBLOBs questionEdit, Model model) {
			questionEdit.setQuestionid(QuestionID);
	       questionservice.updateByPrimaryKeyWithBLOBs(questionEdit);
		return "redirect:/getexam";
	}
	
	/*
	 * @RequestMapping
	 * String getList
	 * 	 
	 */

	@RequestMapping(value = "/inputexam", method = RequestMethod.POST, params = "postquestion")
	public String inputpostquestion(Model model, QuestionWithBLOBs questionW) {
		if (questionW.getQuestionid() == null) {
			questionservice.insert(questionW);
		}
		return "inputexam";
	}

	@RequestMapping(value = "/inputexam", method = RequestMethod.POST, params = "postlatex")
	public String inputpostlatex(Model model, QuestionWithBLOBs questionW, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (questionW.getQuestionid() == null) {
			String latex_temp = questionW.getlatex();
			BufferedReader bufReader = new BufferedReader(new StringReader(latex_temp));
			String line = null;
			Boolean bool_question = false;
			String question_input = "";
			int number_choice = 0;
			Boolean bool_choiceA = false;
			Boolean bool_choiceB = false;
			Boolean bool_choiceC = false;
			Boolean bool_choiceD = false;
			Boolean bool_choiceE = false;

			String choiceA = "";
			String choiceB = "";
			String choiceC = "";
			String choiceD = "";
			String choiceE = "";

			Boolean bool_version = false;
			while ((line = bufReader.readLine()) != null) {
				// ------------------------------------------------------------------------------------

				// --------------------------------------------------------------------------------
				/*
				 * questionW.setExamid(1); questionW.setLevelq("1");
				 * questionW.setSubjects("TRR");
				 * questionW.setChaptersubjects(1); if (line.startsWith("1")){
				 * 
				 * questionW.setQuestionname(line); } if (line.startsWith("A")){
				 * questionW.setAnswera(line); } if (line.startsWith("B")){
				 * questionW.setAnswerb(line); } if (line.startsWith("C")){
				 * questionW.setAnswerc(line); } if (line.startsWith("D")){
				 * questionW.setAnswerd(line); } if (line.startsWith("E")){
				 * questionW.setAnswere(line);
				 * questionservice.insert(questionW); }
				 */
				// ------------------------------------------------------------------------------------
				if (line.contains("%")) {
					line = line.substring(0, line.indexOf("%"));
				}

				line = line.replace("\\\\", "");

				if (line.contains("\\begin{version}")) {
					if (line.contains("\\end{version}")) {
						line = line.substring(0, line.indexOf("\\begin{version}"))
								+ line.substring(line.indexOf("\\end{version}") + 13, line.length());
					} else
						bool_version = true;
				}

				if (line.contains("\\end{version}")) {
					line = line.substring(line.indexOf("\\end{version}") + 13, line.length());
					bool_version = false;
				}
				if (bool_version) {
					line = "";
				}

				line = line.replace("\\begin{tabular}", "");
				line = line.replace("\\end{tabular}", "");

				if (line.contains("\\textit{")) {
					String line_2 = line.substring(line.indexOf("\\textit{"), line.length());
					line_2 = line_2.replaceFirst("}", "");
					line_2 = line_2.replace("\\textit{", "");
					line = line.substring(0, line.indexOf("\\textit{")) + line_2;

				}

				if (line.contains("\\begin{question}")) {
					bool_question = true;
					line = line.replace("\\begin{question}", "");
				}
				if ((line.contains("{\\chon")) || (line.contains("\\end{question}"))) {
					bool_question = false;
				}
				if (bool_question) {

					if (line.startsWith("\\")) {
						if (line.contains(" ")) {
							line = line.substring(line.indexOf(" "), line.length());
						} else {
							line = "";
						}
					}

					question_input = question_input + line;

				}
				if (line.contains("{\\chon")) {
					number_choice++;
				}

				if (number_choice == 1) {
					bool_choiceA = true;
					line = line.replace("\\begin{question}", "");
				}
				if (number_choice == 2) {
					bool_choiceB = true;
					bool_choiceA = false;
				}
				if (number_choice == 3) {
					bool_choiceC = true;
					bool_choiceB = false;
				}
				if (number_choice == 4) {
					bool_choiceD = true;
					bool_choiceC = false;
				}
				if (number_choice == 5) {
					bool_choiceE = true;
					bool_choiceD = false;
				}
				if (line.contains("\\end{question}")) {
					bool_choiceA = false;
					bool_choiceB = false;
					bool_choiceC = false;
					bool_choiceD = false;
					bool_choiceE = false;
					bool_question = false;

				}

				if (bool_choiceA) {
					line = line.replace("{\\chond{", "");
					line = line.replace("{\\chon{", "");
					choiceA = choiceA + line;

				}
				if (bool_choiceB) {
					line = line.replace("{\\chond{", "");
					line = line.replace("{\\chon{", "");
					choiceB = choiceB + line;

				}
				if (bool_choiceC) {
					line = line.replace("{\\chond{", "");
					line = line.replace("{\\chon{", "");
					choiceC = choiceC + line;

				}
				if (bool_choiceD) {
					line = line.replace("{\\chond{", "");
					line = line.replace("{\\chon{", "");
					choiceD = choiceD + line;

				}
				if (bool_choiceE) {
					line = line.replace("{\\chond{", "");
					line = line.replace("{\\chon{", "");
					choiceE = choiceE + line;

				}

				if (line.contains("\\end{question}")) {
					
					String examid = request.getParameter("examid");
					questionW.setExamid(Integer.parseInt(examid));
					
					String levelq = request.getParameter("levelq");
					questionW.setLevelq(levelq);
					
					String subjects = request.getParameter("subjects");
					questionW.setSubjects(subjects);
					
					String chaptersubjects = request.getParameter("chaptersubjects");
					questionW.setChaptersubjects(Integer.parseInt(chaptersubjects));

					question_input = format(question_input);
					choiceA = format(choiceA);
					choiceB = format(choiceB);
					choiceC = format(choiceC);
					choiceD = format(choiceD);
					choiceE = format(choiceE);

					questionW.setQuestionname(question_input);

					if (choiceA.length() >= 2) {
						choiceA = choiceA.substring(0, choiceA.length() - 2);
					}
					if (choiceA.contains("\\aliincludetabular{")) {
						choiceA = choiceA.substring(0, choiceA.length() - 1);
						choiceA = choiceA.replace("\\aliincludetabular{", "");
					}

					questionW.setAnswera(choiceA);

					if (choiceB.length() >= 2) {
						choiceB = choiceB.substring(0, choiceB.length() - 2);
					}
					if (choiceB.contains("\\aliincludetabular{")) {
						choiceB = choiceB.substring(0, choiceB.length() - 1);
						choiceB = choiceB.replace("\\aliincludetabular{", "");
					}

					questionW.setAnswerb(choiceB);

					if (choiceC.length() >= 2) {
						choiceC = choiceC.substring(0, choiceC.length() - 2);
					}
					if (choiceC.contains("\\aliincludetabular{")) {
						choiceC = choiceC.substring(0, choiceC.length() - 1);
						choiceC = choiceC.replace("\\aliincludetabular{", "");
					}

					questionW.setAnswerc(choiceC);

					if (choiceD.length() >= 2) {
						choiceD = choiceD.substring(0, choiceD.length() - 2);
					}
					if (choiceD.contains("\\aliincludetabular{")) {
						choiceD = choiceD.substring(0, choiceD.length() - 1);
						choiceD = choiceD.replace("\\aliincludetabular{", "");
					}

					questionW.setAnswerd(choiceD);

					if (choiceE.length() >= 2) {
						choiceE = choiceE.substring(0, choiceE.length() - 2);
					}
					if (choiceE.contains("\\aliincludetabular{")) {
						choiceE = choiceE.substring(0, choiceE.length() - 1);
						choiceE = choiceE.replace("\\aliincludetabular{", "");
					}

					questionW.setAnswere(choiceE);

					questionservice.insert(questionW);

					question_input = "";
					number_choice = 0;
					choiceA = "";
					choiceB = "";
					choiceC = "";
					choiceD = "";
					choiceE = "";
				}

				// ----------------------------------------------------------------------------------------

			}

		}
		return "inputexam";
	}
	
	
	//----------------------------------------------------------------
	@RequestMapping(value = "/inputexam", method = RequestMethod.POST, params = "postword")
	public String inputpostword(Model model, QuestionWithBLOBs questionW, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (questionW.getQuestionid() == null) {
			String word_temp = questionW.getword();
			String stringexam_temp = questionW.getstringexam();
			String stringsolutionA_temp = questionW.getstringsolutionA();
			String stringsolutionB_temp = questionW.getstringsolutionB();
			String stringsolutionC_temp = questionW.getstringsolutionC();
			String stringsolutionD_temp = questionW.getstringsolutionD();
			String stringsolutionE_temp = questionW.getstringsolutionE();
			
			
			BufferedReader bufReader = new BufferedReader(new StringReader(word_temp));
			
			
			String line = null;
			Boolean bool_question = false;
			String question_input = "";
			Boolean bool_choiceA = false;
			Boolean bool_choiceB = false;
			Boolean bool_choiceC = false;
			Boolean bool_choiceD = false;
			Boolean bool_choiceE = false;

			String choiceA = "";
			String choiceB = "";
			String choiceC = "";
			String choiceD = "";
			String choiceE = "";
			while ((line = bufReader.readLine()) != null) {

				if (line.contains(stringexam_temp)) {
					bool_question = true;
					line = line.replace(stringexam_temp, "");
				}
				if (line.contains(stringsolutionA_temp)) {
					bool_question = false;
				}
				if (bool_question) {
					question_input = question_input + line;
				}
				

				if (line.contains(stringsolutionA_temp)) {
					bool_choiceA = true;
				}
				if (line.contains(stringsolutionB_temp)) {
					bool_choiceB = true;
					bool_choiceA = false;
				}
				if (line.contains(stringsolutionC_temp)) {
					bool_choiceC = true;
					bool_choiceB = false;
				}
				if (line.contains(stringsolutionD_temp)) {
					bool_choiceD = true;
					bool_choiceC = false;
				}
				if (line.contains(stringsolutionE_temp)) {
					bool_choiceE = true;
					bool_choiceD = false;
				}
				if (line.contains(stringexam_temp)) {
					bool_choiceA = false;
					bool_choiceB = false;
					bool_choiceC = false;
					bool_choiceD = false;
					bool_choiceE = false;
					bool_question = false;

				}

				if (bool_choiceA) {
					line = line.replace(stringsolutionA_temp, "");
					choiceA = choiceA + line;

				}
				if (bool_choiceB) {
					line = line.replace(stringsolutionB_temp, "");
					
					choiceB = choiceB + line;

				}
				if (bool_choiceC) {
					line = line.replace(stringsolutionC_temp, "");
					
					choiceC = choiceC + line;

				}
				if (bool_choiceD) {
					line = line.replace(stringsolutionD_temp, "");
					
					choiceD = choiceD + line;

				}
				if (bool_choiceE) {
					line = line.replace(stringsolutionE_temp, "");
					
					choiceE = choiceE + line;

				}

				
					
//					String examid = request.getParameter("examid");
//					questionW.setExamid(Integer.parseInt(examid));
//					
//					String levelq = request.getParameter("levelq");
//					questionW.setLevelq(levelq);
//					
//					String subjects = request.getParameter("subjects");
//					questionW.setSubjects(subjects);
//					
//					String chaptersubjects = request.getParameter("chaptersubjects");
//					questionW.setChaptersubjects(Integer.parseInt(chaptersubjects));
				if (line.contains(stringexam_temp)) {
					question_input = format(question_input);
					choiceA = format(choiceA);
					choiceB = format(choiceB);
					choiceC = format(choiceC);
					choiceD = format(choiceD);
					choiceE = format(choiceE);
					
					questionW.setExamid(1);
					questionW.setLevelq("1");
					questionW.setSubjects("TRR");
					questionW.setChaptersubjects(1);

					questionW.setQuestionname(question_input);

					questionW.setAnswera(choiceA);

					questionW.setAnswerb(choiceB);
					
					questionW.setAnswerc(choiceC);

					questionW.setAnswerd(choiceD);
					
					questionW.setAnswere(choiceE);

					questionservice.insert(questionW);

					question_input = "";
				
					choiceA = "";
					choiceB = "";
					choiceC = "";
					choiceD = "";
					choiceE = "";
				

				// ----------------------------------------------------------------------------------------

			}
			}

		}
		return "inputexam";
	}
	
	//---------------------------------------------------------------
	
	

}
