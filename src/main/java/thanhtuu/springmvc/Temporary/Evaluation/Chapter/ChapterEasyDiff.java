package thanhtuu.springmvc.Temporary.Evaluation.Chapter;

import thanhtuu.springmvc.Domain.Chapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anh.dang on 4/2/2017.
 */
public class ChapterEasyDiff {
    private Chapter chapterElement = new Chapter();
    private List<Integer> listTrueFalse = new ArrayList<>();
    private Float percentTrue;

    public Chapter getChapterElement() {
        return chapterElement;
    }

    public void setChapterElement(Chapter chapterElement) {
        this.chapterElement = chapterElement;
    }

    public List<Integer> getListTrueFalse() {
        return listTrueFalse;
    }

    public void setListTrueFalse(List<Integer> listTrueFalse) {
        this.listTrueFalse = listTrueFalse;
    }

    public Float getPercentTrue() {
        return percentTrue;
    }

    public void setPercentTrue(Float percentTrue) {
        this.percentTrue = percentTrue;
    }
}
