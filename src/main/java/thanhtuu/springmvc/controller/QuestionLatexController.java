package thanhtuu.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import thanhtuu.springmvc.Domain.*;
import thanhtuu.springmvc.Service.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.security.Principal;

/**
 * Created by Administrator on 8/27/2016.
 */
@Controller
public class QuestionLatexController {

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
    private UsersService usersService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuestionBlockService questionBlockService;

    @Autowired
    private ChaptersService chaptersService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private TargetService targetService;

    @RequestMapping(value = "/inputexam", method = RequestMethod.POST, params = "postlatex", produces = "text/plain; charset=UTF-8")
    public String inputpostquestion(Model model,
                                    Questions_ChaptersKey questions_chaptersKey,
                                    Questions_TargetKey questions_targetKey,
                                    Question_BlocksWithBLOBs question_blocksWithBLOBs,
                                    Questions questions,
                                    Answers answers,
                                    Users users,
                                    Principal principal,
                                    HttpServletRequest request,
                                    HttpServletResponse response) throws ServletException, IOException {


        String SubjectName = request.getParameter("subjectsname");
        String GetLatex = request.getParameter("latex");


        if((SubjectName != null) && (GetLatex != null)) {
            BufferedReader bufReader = new BufferedReader(new StringReader(GetLatex));

            String line = null;
            Boolean bool_question = false;
            String question_input = "";

            Boolean bool_answer = false;


            String answer = "";


            Boolean have_block = false;
            String name_block = "";
            Boolean bool_name_block = false;


            String change_last_begin = "";

            Boolean bool_version = false;

            int number_answer = 0;

            Long MaxIdOfBlock = null;

            Long MaxIdQuestion = null;


            while ((line = bufReader.readLine()) != null) {


                if (line.contains("%")) {
                    line = line.substring(0, line.indexOf("%"));
                }

                line = line.replace("\\\\", "");
                //------------------------------
                if (line.contains("\\begin")) {
                    String block_begin = line.substring(line.indexOf("\\begin") + 6);
                    while (block_begin.startsWith(" ")) {
                        block_begin = block_begin.substring(1, block_begin.length());
                    }
                    if (block_begin.startsWith("{block}")) {
                        have_block = true;
                        bool_name_block = true;
                        block_begin = block_begin.substring(7);
                        line = block_begin;
                    }
                    else {
                        have_block = false;
                    }
                }

                else if (bool_name_block) {
                    name_block = name_block + line;
                }

                if ((line.contains("\\end")) && (have_block)) {
                    String change_end = line.substring(line.indexOf("\\end") + 4);
                    while (change_end.startsWith(" ")) {
                        change_end = change_end.substring(1, change_end.length());
                    }
                    if (change_end.startsWith("{block}")) {
                        have_block = false;
                    }
                }

                if ((line.contains("\\begin")) && (have_block)) {

                    String change_begin = line.substring(line.indexOf("\\begin") + 6);
                    while (change_begin.startsWith(" ")) {
                        change_begin = change_begin.substring(1, change_begin.length());
                    }

                    if ((change_begin.startsWith("{question}"))) {
                        bool_name_block = false;

                        if (name_block.contains("{block}")) {

                            name_block = name_block.replace(name_block.substring(0, name_block.indexOf("{block}") + 7), "");
                        }
                        if (name_block.contains("{question}")) {

                            name_block = name_block.replace(name_block.substring(name_block.lastIndexOf("\\begin")), "");
                        }

                        //Insert block

                        String email = principal.getName();
                        Users userlogin = usersService.getIdByEmail(email);
                        question_blocksWithBLOBs.setTeacherid(userlogin.getId());
                        question_blocksWithBLOBs.setContent(name_block);
                        questionBlockService.insertBlock(question_blocksWithBLOBs);
                        MaxIdOfBlock = questionBlockService.checkMaxIdBlock();

                        name_block = "";

                    }
                }


                //----------------------------------------


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

                if (line.contains("\\begin")) {
                    String change_begin = line.substring(line.indexOf("\\begin") + 6);
                    while (change_begin.startsWith(" ")) {
                        change_begin = change_begin.substring(1, change_begin.length());
                    }
                    if (change_begin.startsWith("{question}")) {
                        change_last_begin = line.substring(line.indexOf("\\begin"), line.indexOf("{question}") + 10);
                        bool_question = true;
                        line = line.replace(change_last_begin, "");
                    }
                }


                if (line.contains("{\\chon")) {
                    bool_question = false;
                }
                if (line.contains("\\end")) {
                    String change_end = line.substring(line.indexOf("\\end") + 4);
                    while (change_end.startsWith(" ")) {
                        change_end = change_end.substring(1, change_end.length());
                    }
                    if (change_end.startsWith("{question}")) {
                        bool_question = false;
                    }

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
                    bool_answer = true;
                    number_answer ++;

                }

                if (line.contains("\\end")) {
                    String change_end = line.substring(line.indexOf("\\end") + 4);
                    while (change_end.startsWith(" ")) {
                        change_end = change_end.substring(1, change_end.length());
                    }
                    if (change_end.startsWith("{question}")) {
                        bool_answer = false;
                        bool_question = false;
                        number_answer = 0;
                    }
                }


                if ((bool_answer)&&(number_answer != 0)) {
                    line = line.replace("{\\chond{", "");
                    line = line.replace("{\\chon{", "");
                    answer = answer + line;

                }



                if (line.contains("\\end")) {

                    String change_end = line.substring(line.indexOf("\\end") + 4);
                    while (change_end.startsWith(" ")) {
                        change_end = change_end.substring(1, change_end.length());
                    }

                    if (change_end.startsWith("{question}")) {


                        question_input = format(question_input);
                        answer = format(answer);

                        if (have_block) {
                            //Insertquestion

                            questions.setLevel(0);
                            questions.setContent(question_input);
                            questions.setQuestionblockid(MaxIdOfBlock);
                            questionService.insertQuestion(questions);
                            MaxIdQuestion = questionService.checkMaxIdQuestion();
                        }
                        else {
                            //Insert block
                            String email = principal.getName();
                            Users userlogin = usersService.getIdByEmail(email);
                            question_blocksWithBLOBs.setTeacherid(userlogin.getId());
                            questionBlockService.insertBlock(question_blocksWithBLOBs);
                            MaxIdOfBlock = questionBlockService.checkMaxIdBlock();

                            //Insert question
                            questions.setLevel(0);
                            questions.setContent(question_input);
                            questions.setQuestionblockid(MaxIdOfBlock);
                            questionService.insertQuestion(questions);
                            MaxIdQuestion = questionService.checkMaxIdQuestion();
                        }

                        //InsertAnswer

                        //----------------------------------

                        if (answer.length() >= 2) {
                            answer = answer.substring(0, answer.length() - 2);
                        }
                        if (answer.contains("\\aliincludetabular{")) {
                            answer = answer.substring(0, answer.length() - 1);
                            answer = answer.replace("\\aliincludetabular{", "");
                        }

                        answers.setContent(answer);
                        answers.setQuestionid(MaxIdQuestion);
                        answers.setQuestionblockid(MaxIdOfBlock);
                        answers.setIssolution(false);
                        questionService.insertAnswer(answers);


                        question_input = "";
                        number_answer = 0;
                        answer = "";
                    }
                }
            }
        }

        else {
            System.out.println("ERORR MISS");
        }






        return "inputexam";
    }


}
