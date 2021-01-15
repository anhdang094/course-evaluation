package thanhtuu.springmvc.controller;

import com.lowagie.text.Chapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import thanhtuu.springmvc.Domain.*;
import thanhtuu.springmvc.Service.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.security.Principal;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 8/27/2016.
 */
@Controller
public class QuestionInputController {

    @Autowired
    private UsersServiceLocal usersService;

    @Autowired
    private QuestionServiceLocal questionService;

    @Autowired
    private QuestionBlockServiceLocal questionBlockService;

    @Autowired
    private ChaptersServiceLocal chaptersService;

    @Autowired
    private SubjectServiceLocal subjectService;

    @Autowired
    private TargetServiceLocal targetService;


    @RequestMapping(value = "/inputexam", method = RequestMethod.POST, params = "postquestion")
    public String inputpostquestion(Model model,
                                    Questions_ChaptersKey questions_chaptersKey,
                                    Questions_TargetKey questions_targetKey,
                                    Question_BlocksWithBLOBs question_blocksWithBLOBs,
                                    Questions questions,
                                    Answers answers,
                                    Target target,
                                    Users users,
                                    Principal principal,
                                    HttpServletRequest request,
                                    HttpServletResponse response) throws ServletException, IOException {


        String Subjectname = request.getParameter("subjects");
        String choice = request.getParameter("radio1");  //option1 or option2
        String keyWord = request.getParameter("keyWord");

        if ((Subjectname != "Chọn môn học") && (choice.equals("option1"))) {

            String Questioncontent = request.getParameter("questioncontent");
            String Chaptername = request.getParameter("chaptername");
            String GetTarget = request.getParameter("targetname");
            String LevelQ = request.getParameter("level");
            String GetAnswer = request.getParameter("answers");

            if(Questioncontent != null && Chaptername != "Chọn chương" && GetAnswer != null && GetTarget != null){


                //Insert question_block
                String email = principal.getName();
                Users userlogin = usersService.getIdByEmail(email);
                question_blocksWithBLOBs.setTeacherid(userlogin.getId());
                question_blocksWithBLOBs.setKeyword(keyWord);
                questionBlockService.insertBlock(question_blocksWithBLOBs);
                Long MaxIdOfBlock = questionBlockService.checkMaxIdBlock();

                //Insert question

                questions.setLevel(Integer.parseInt(LevelQ));
                questions.setContent(Questioncontent);
                questions.setQuestionblockid(MaxIdOfBlock);
                Subjects subjects = subjectService.getIDByName(Subjectname);
                Chapters chapters = chaptersService.getIDByName(Chaptername, subjects.getId(), userlogin.getId());
                questions.setChapterid(chapters.getId());
                questions.setSubjectid(subjects.getId());


                questionService.insertQuestion(questions);
                Long MaxIdQuestion = questionService.checkMaxIdQuestion();

                //Insert answer
                if (!GetAnswer.isEmpty()) {

                    BufferedReader bufReader = new BufferedReader(new StringReader(GetAnswer));
                    String answer = "";
                    String line = null;
                    int numberOfEnter = 0;

                    while ((line = bufReader.readLine()) != null) {

                        if (line.isEmpty()){
                            numberOfEnter ++;
                        }

                        if ( (numberOfEnter == 1) ) {
                            while (answer.endsWith("\n")){
                                answer = answer.substring(0,answer.length()-1);
                            }
                            numberOfEnter = 0;
                            answers.setContent(answer);
                            answers.setQuestionid(MaxIdQuestion);
                            answers.setQuestionblockid(MaxIdOfBlock);
                            answers.setIssolution(false);
                            questionService.insertAnswer(answers);
                            answer = "";
                        }
                        if (numberOfEnter != 1) {
                            answer = answer + line;
                        }
                    }

                    answers.setContent(answer);
                    answers.setQuestionid(MaxIdQuestion);
                    answers.setQuestionblockid(MaxIdOfBlock);
                    answers.setIssolution(false);
                    questionService.insertAnswer(answers);


                }


                //Insert QuestionChapter

                questions_chaptersKey.setQuestionid(MaxIdQuestion);
                questions_chaptersKey.setQuestionblockid(MaxIdOfBlock);
                questions_chaptersKey.setChapterid(chapters.getId());
                questions_chaptersKey.setSubjectid(subjects.getId());

                questionService.insertQuestionChapter(questions_chaptersKey);

                //Insert QuestionTarget
                questions_targetKey.setQuestionid(MaxIdQuestion);
                questions_targetKey.setQuestionblockid(MaxIdOfBlock);
                questions_targetKey.setSubjectid(subjects.getId());

                List<Target> targetList = targetService.getAllTarget(subjects.getId());

                boolean checktarget = false;

                for (int i = 0; i < targetList.size(); i++) {
                    if (GetTarget.equals(targetList.get(i).getContent())) {
                        checktarget = true;
                        break;
                    }
                }
                if (checktarget == false) {
                    target.setContent(GetTarget);
                    target.setSubjectid(subjects.getId());
                    targetService.insertTarget(target);
                }

                Target target2 = targetService.getIDByName(GetTarget, subjects.getId());
                questions_targetKey.setTargetid(target2.getId());
                questionService.insertQuestionTarget(questions_targetKey);


            }

            else {
                System.out.println("ERROR MISS COLUMN");
            }
        }


        else if ((Subjectname != "Chọn môn học") && (choice.equals("option2"))) {
            //List questioncontent, answer,....

            String questionBlockContent = request.getParameter("questionblock");

            if (questionBlockContent != null) {

                //Insert question_block
                String email = principal.getName();
                Users userlogin = usersService.getIdByEmail(email);
                question_blocksWithBLOBs.setTeacherid(userlogin.getId());
                question_blocksWithBLOBs.setKeyword(keyWord);
                question_blocksWithBLOBs.setContent(questionBlockContent);
                questionBlockService.insertBlock(question_blocksWithBLOBs);
                Long MaxIdOfBlock = questionBlockService.checkMaxIdBlock();

                //Insert question

                String Questioncontent = request.getParameter("questioncontent");
                String Chaptername = request.getParameter("chaptername");
                String GetTarget = request.getParameter("targetname");
                String LevelQ = request.getParameter("level");
                String GetAnswer = request.getParameter("answers");

                questions.setLevel(Integer.parseInt(LevelQ));
                questions.setContent(Questioncontent);
                questions.setQuestionblockid(MaxIdOfBlock);
                Subjects subjects = subjectService.getIDByName(Subjectname);
                Chapters chapters = chaptersService.getIDByName(Chaptername, subjects.getId(), userlogin.getId());
                questions.setChapterid(chapters.getId());
                questions.setSubjectid(subjects.getId());


                questionService.insertQuestion(questions);
                Long MaxIdQuestion = questionService.checkMaxIdQuestion();

                //Insert answer
                if (!GetAnswer.isEmpty()) {

                    BufferedReader bufReader = new BufferedReader(new StringReader(GetAnswer));
                    String answer = "";
                    String line = null;
                    int numberOfEnter = 0;

                    while ((line = bufReader.readLine()) != null) {

                        if (line.isEmpty()) {
                            numberOfEnter++;
                        }

                        if ((numberOfEnter == 1)) {
                            while (answer.endsWith("\n")) {
                                answer = answer.substring(0, answer.length() - 1);
                            }
                            numberOfEnter = 0;
                            answers.setContent(answer);
                            answers.setQuestionid(MaxIdQuestion);
                            answers.setQuestionblockid(MaxIdOfBlock);
                            answers.setIssolution(false);
                            questionService.insertAnswer(answers);
                            answer = "";
                        }
                        if (numberOfEnter != 1) {
                            answer = answer + line;
                        }
                    }

                    answers.setContent(answer);
                    answers.setQuestionid(MaxIdQuestion);
                    answers.setQuestionblockid(MaxIdOfBlock);
                    answers.setIssolution(false);
                    questionService.insertAnswer(answers);


                }


                //Insert QuestionChapter

                questions_chaptersKey.setQuestionid(MaxIdQuestion);
                questions_chaptersKey.setQuestionblockid(MaxIdOfBlock);
                questions_chaptersKey.setChapterid(chapters.getId());
                questions_chaptersKey.setSubjectid(subjects.getId());

                questionService.insertQuestionChapter(questions_chaptersKey);

                //Insert QuestionTarget
                questions_targetKey.setQuestionid(MaxIdQuestion);
                questions_targetKey.setQuestionblockid(MaxIdOfBlock);
                questions_targetKey.setSubjectid(subjects.getId());

                List<Target> targetList = targetService.getAllTarget(subjects.getId());

                boolean checktarget = false;

                for (int i = 0; i < targetList.size(); i++) {
                    if (GetTarget.equals(targetList.get(i).getContent())) {
                        checktarget = true;
                        break;
                    }
                }
                if (checktarget == false) {
                    target.setContent(GetTarget);
                    target.setSubjectid(subjects.getId());
                    targetService.insertTarget(target);
                }

                Target target2 = targetService.getIDByName(GetTarget, subjects.getId());
                questions_targetKey.setTargetid(target2.getId());
                questionService.insertQuestionTarget(questions_targetKey);


                int number = 2;
                boolean last = true;

                if ((request.getParameter("questioncontent2")) != null) {
                    while (last == true) {

                        if ((request.getParameter("questioncontent" + number)) != null) {
                            //Insert question
                            String QuestioncontentParam = request.getParameter("questioncontent" + number);
                            String ChapternameParam = request.getParameter("chaptername" + number);
                            String GetTargetParam = request.getParameter("targetname" + number);
                            String LevelQParam = request.getParameter("level" + number);
                            String GetAnswerParam = request.getParameter("answers" + number);

                            System.out.println(LevelQParam);

                            System.out.println(Integer.parseInt(LevelQParam));
                            questions.setLevel(Integer.parseInt(LevelQParam));
                            questions.setContent(QuestioncontentParam);
                            questions.setQuestionblockid(MaxIdOfBlock);
                            Chapters chapters2 = chaptersService.getIDByName(ChapternameParam, subjects.getId(), userlogin.getId());
                            questions.setChapterid(chapters2.getId());
                            questions.setSubjectid(subjects.getId());


                            questionService.insertQuestion(questions);
                            Long MaxIdQuestion2 = questionService.checkMaxIdQuestion();

                            //Insert answer
                            if (!GetAnswerParam.isEmpty()) {

                                BufferedReader bufReader = new BufferedReader(new StringReader(GetAnswerParam));
                                String answer = "";
                                String line = null;
                                int numberOfEnter = 0;

                                while ((line = bufReader.readLine()) != null) {

                                    if (line.isEmpty()) {
                                        numberOfEnter++;
                                    }

                                    if ((numberOfEnter == 1)) {
                                        while (answer.endsWith("\n")) {
                                            answer = answer.substring(0, answer.length() - 1);
                                        }
                                        numberOfEnter = 0;
                                        answers.setContent(answer);
                                        answers.setQuestionid(MaxIdQuestion2);
                                        answers.setQuestionblockid(MaxIdOfBlock);
                                        answers.setIssolution(false);
                                        questionService.insertAnswer(answers);
                                        answer = "";
                                    }
                                    if (numberOfEnter != 1) {
                                        answer = answer + line;
                                    }
                                }

                                answers.setContent(answer);
                                answers.setQuestionid(MaxIdQuestion2);
                                answers.setQuestionblockid(MaxIdOfBlock);
                                answers.setIssolution(false);
                                questionService.insertAnswer(answers);


                            }


                            //Insert QuestionChapter

                            questions_chaptersKey.setQuestionid(MaxIdQuestion2);
                            questions_chaptersKey.setQuestionblockid(MaxIdOfBlock);
                            questions_chaptersKey.setChapterid(chapters2.getId());
                            questions_chaptersKey.setSubjectid(subjects.getId());

                            questionService.insertQuestionChapter(questions_chaptersKey);

                            //Insert QuestionTarget
                            questions_targetKey.setQuestionid(MaxIdQuestion2);
                            questions_targetKey.setQuestionblockid(MaxIdOfBlock);
                            questions_targetKey.setSubjectid(subjects.getId());

                            List<Target> targetList2 = targetService.getAllTarget(subjects.getId());

                            boolean checktarget2 = false;

                            for (int i = 0; i < targetList2.size(); i++) {
                                if (GetTargetParam.equals(targetList2.get(i).getContent())) {
                                    checktarget2 = true;
                                    break;
                                }
                            }
                            if (checktarget2 == false) {
                                target.setContent(GetTargetParam);
                                target.setSubjectid(subjects.getId());
                                targetService.insertTarget(target);
                            }

                            Target target3 = targetService.getIDByName(GetTargetParam, subjects.getId());
                            questions_targetKey.setTargetid(target3.getId());
                            questionService.insertQuestionTarget(questions_targetKey);


                        }

                            else {
                                last = false;
                                break;
                            }
                            number++;

                    }

                }
            }
        }

        else {
            System.out.println("ERROR");
        }

        return "redirect:inputexam";
    }

}
