package thanhtuu.springmvc.Todo.Evaluation;

import thanhtuu.springmvc.Domain.Chapter;
import thanhtuu.springmvc.Domain.Subject;

import java.util.List;

/**
 * Created by anh.dang on 3/25/2017.
 */
public class ChapterEvaluation {

    private Subject subject;
    private Chapter chapter;
    private List<TargetEvaluation> targetEvaluationList;

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    public List<TargetEvaluation> getTargetEvaluationList() {
        return targetEvaluationList;
    }

    public void setTargetEvaluationList(List<TargetEvaluation> targetEvaluationList) {
        this.targetEvaluationList = targetEvaluationList;
    }
}
