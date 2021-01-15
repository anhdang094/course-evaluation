package thanhtuu.springmvc.Temporary.Evaluation;

import thanhtuu.springmvc.Domain.Chapter;
import thanhtuu.springmvc.Temporary.Evaluation.Chapter.ChapterEasyDiff;
import thanhtuu.springmvc.Temporary.Evaluation.Chapter.ChapterForEvaluationByChapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anh.dang on 3/19/2017.
 */
public class EvaluationByChappter {

    private List<ChapterForEvaluationByChapter> chapterList = new ArrayList<>();
    private List<Chapter> chapterListNotHave = new ArrayList<>();
    private List<ChapterEasyDiff> chapterEasyMost = new ArrayList<>();
    private List<ChapterEasyDiff> chapterDifficult = new ArrayList<>();

    public List<ChapterForEvaluationByChapter> getChapterList() {
        return chapterList;
    }

    public void setChapterList(List<ChapterForEvaluationByChapter> chapterList) {
        this.chapterList = chapterList;
    }

    public List<Chapter> getChapterListNotHave() {
        return chapterListNotHave;
    }

    public void setChapterListNotHave(List<Chapter> chapterListNotHave) {
        this.chapterListNotHave = chapterListNotHave;
    }

    public List<ChapterEasyDiff> getChapterEasyMost() {
        return chapterEasyMost;
    }

    public void setChapterEasyMost(List<ChapterEasyDiff> chapterEasyMost) {
        this.chapterEasyMost = chapterEasyMost;
    }

    public List<ChapterEasyDiff> getChapterDifficult() {
        return chapterDifficult;
    }

    public void setChapterDifficult(List<ChapterEasyDiff> chapterDifficult) {
        this.chapterDifficult = chapterDifficult;
    }
}
