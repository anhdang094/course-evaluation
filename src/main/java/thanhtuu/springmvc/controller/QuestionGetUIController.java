package thanhtuu.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import thanhtuu.springmvc.Domain.*;
import thanhtuu.springmvc.Service.SubjectServiceLocal;
import thanhtuu.springmvc.Service.SubjectsServiceLocal;
import thanhtuu.springmvc.Service.UsersService;
import thanhtuu.springmvc.Service.UsersServiceLocal;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 8/27/2016.
 */
@Controller
public class QuestionGetUIController {

    @Autowired
    private UsersServiceLocal usersService;

    @Autowired
    private SubjectsServiceLocal subjectsService;

    @Autowired
    private SubjectServiceLocal subjectService;


    @RequestMapping(value="/inputexam2",method=RequestMethod.POST)

    public  @ResponseBody ArrayList<String> getSearchUserProfiles(@RequestBody SubjectAjax subjects, Model model,HttpServletRequest request) {
        String pName = subjects.getpName();

        Subjects subjectsId = subjectService.getIDByName(pName);
        List<Chapters> chaptersList = subjectService.getAllChapterBySubjectId(subjectsId.getId());

        ArrayList<String> GetChapter = new ArrayList<String>();
        for (int i=0;i<chaptersList.size();i++){
            GetChapter.add(chaptersList.get(i).getName());
        }


        return GetChapter;

        // your logic next
    }

    @RequestMapping(value = "/inputexam", method = RequestMethod.GET)
    public String InputQuestion(Model model,
                                Users users,
                                Principal principal)
    {
        String email = principal.getName();
        Users userlogin = usersService.getIdByEmail(email);


        List<Teacher_SubjectKey> teacherSubjectList = subjectsService. getAllSubjectByTeacherId(userlogin.getId());
        ArrayList<String> GetSubject = new ArrayList<String>();

        for (int i=0; i<teacherSubjectList.size(); i++){
            Long Id = teacherSubjectList.get(i).getSubjectid();
            String subjectName = subjectService.getNameById(Id).getName();
            GetSubject.add(subjectName);
        }


        model.addAttribute("GetSubject", GetSubject);
        //List<Subjects> getsubjectsCreateExam = questionservice.getSubjectsByUserId(userlogin.getId());

        //model.addAttribute("listsubject",getsubjectsCreateExam );

        return "inputexam";
    }


}


