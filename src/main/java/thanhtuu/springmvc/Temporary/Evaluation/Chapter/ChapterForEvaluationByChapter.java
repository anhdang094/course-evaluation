package thanhtuu.springmvc.Temporary.Evaluation.Chapter;

import thanhtuu.springmvc.Domain.Chapter;

/**
 * Created by anh.dang on 3/26/2017.
 */
public class ChapterForEvaluationByChapter {

    private Chapter chapter;
    private Integer numberOfChapter;

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    public Integer getNumberOfChapter() {
        return numberOfChapter;
    }

    public void setNumberOfChapter(Integer numberOfChapter) {
        this.numberOfChapter = numberOfChapter;
    }
}
