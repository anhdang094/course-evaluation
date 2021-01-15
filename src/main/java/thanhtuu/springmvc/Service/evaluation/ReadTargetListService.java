package thanhtuu.springmvc.Service.evaluation;

import thanhtuu.springmvc.Domain.Chapter;
import thanhtuu.springmvc.Service.ChapterService;
import thanhtuu.springmvc.Service.ChapterServiceLocal;
import thanhtuu.springmvc.Temporary.Evaluation.*;
import thanhtuu.springmvc.Todo.Evaluation.ChapterEvaluation;
import thanhtuu.springmvc.Todo.Evaluation.Evaluation;
import thanhtuu.springmvc.Todo.Evaluation.LevelEvaluation;
import thanhtuu.springmvc.Todo.Evaluation.TargetEvaluation;

import java.util.*;

/**
 * Created by anh.dang on 3/19/2017.
 */

public class ReadTargetListService {

    public EvaluationDataAll fetchAllForEvaluation(Evaluation data) {

        EvaluationDataAll evaluationDataAll = new EvaluationDataAll();

        EvaluationByChapterService evaluationByChapterService = new EvaluationByChapterService();
        EvaluationByChappter evaluationByChappter = evaluationByChapterService.fetchAllForEvaluationForChapter(fetchAllChapterForEvaluation(data), data);
        evaluationDataAll.setEvaluationByChappter(evaluationByChappter);

        EvaluationByOutcomeService evaluationByOutcomeService = new EvaluationByOutcomeService();
        EvaluationByOutcomes evaluationByOutcomes = evaluationByOutcomeService.fetchAllForEvaluationForOutcome(fetchAllChapterForEvaluation(data), data);
        evaluationDataAll.setEvaluationByOutcomes(evaluationByOutcomes);

        EvaluationByDisperSalService evaluationByDisperSalService = new EvaluationByDisperSalService();
        EvaluationByDispersal evaluationByDispersal = evaluationByDisperSalService.fetchAllForEvaluationDispersal(fetchAllChapterForEvaluation(data), data);
        evaluationDataAll.setEvaluationByDispersal(evaluationByDispersal);

        EvaluationByLevelService evaluationByLevelService = new EvaluationByLevelService();
        EvaluationByLevel evaluationByLevel = evaluationByLevelService.fetchAllForEvaluationForLevel(fetchAllChapterForEvaluation(data), data);
        evaluationDataAll.setEvaluationByLevel(evaluationByLevel);

        return evaluationDataAll;
    }

    public List<ChapterEvaluation> fetchAllChapterForEvaluation(Evaluation data) {
        List<ChapterEvaluation> chapterEvaluationList = new ArrayList<>();

        List<ElementTargetList> elementTargetListList = data.getTargetList();

        List<Long> chapterIdList = new ArrayList<>();

        for (ElementTargetList elementTarget : elementTargetListList) {

            int chapterId = elementTarget.getTarget().getChapterid();
            int subjectId = elementTarget.getTarget().getSubjectid();
            ChapterServiceLocal chapterService = new ChapterService();
            Chapter chapter = chapterService.findChapterById(chapterId, subjectId);

            String[] level1Array = null;
            String[] level2Array = null;
            String[] level3Array = null;
            String[] level4Array = null;
            String[] level5Array = null;

            if (elementTarget.getLv1() != null) {
                level1Array = elementTarget.getLv1().split(",");
            }
            if (elementTarget.getLv2() != null) {
                level2Array = elementTarget.getLv2().split(",");
            }
            if (elementTarget.getLv3() != null) {
                level3Array = elementTarget.getLv3().split(",");
            }
            if (elementTarget.getLv4() != null) {
                level4Array = elementTarget.getLv4().split(",");
            }
            if (elementTarget.getLv5() != null) {
                level5Array = elementTarget.getLv5().split(",");
            }

            if (!chapterIdList.contains(chapter.getId())) {

                chapterIdList.add(chapter.getId());
                ChapterEvaluation chapterEvaluation = new ChapterEvaluation();
                List<TargetEvaluation> targetEvaluationList = new ArrayList<>();
                TargetEvaluation targetEvaluation = new TargetEvaluation();

                LevelEvaluation levelEvaluation = new LevelEvaluation();

                List<Integer> level1List = new ArrayList<>();
                List<Integer> level2List = new ArrayList<>();
                List<Integer> level3List = new ArrayList<>();
                List<Integer> level4List = new ArrayList<>();
                List<Integer> level5List = new ArrayList<>();

                if (level1Array != null) {
                    for (int i = 0; i < level1Array.length; i++) {
                        level1List.add(Integer.parseInt(level1Array[i]));
                    }
                }
                if (level2Array != null) {
                    for (int i = 0; i < level2Array.length; i++) {
                        level2List.add(Integer.parseInt(level2Array[i]));
                    }
                }
                if (level3Array != null) {
                    for (int i = 0; i < level3Array.length; i++) {
                        level3List.add(Integer.parseInt(level3Array[i]));
                    }
                }
                if (level4Array != null) {
                    for (int i = 0; i < level4Array.length; i++) {
                        level4List.add(Integer.parseInt(level4Array[i]));
                    }
                }
                if (level5Array != null) {
                    for (int i = 0; i < level5Array.length; i++) {
                        level5List.add(Integer.parseInt(level5Array[i]));
                    }
                }

                levelEvaluation.setLv1_Question_List(level1List);
                levelEvaluation.setLv2_Question_List(level2List);
                levelEvaluation.setLv3_Question_List(level3List);
                levelEvaluation.setLv4_Question_List(level4List);
                levelEvaluation.setLv5_Question_List(level5List);


                targetEvaluation.setLevelEvaluationList(levelEvaluation);

                targetEvaluation.setTarget(elementTarget.getTarget());

                targetEvaluationList.add(targetEvaluation);

                chapterEvaluation.setTargetEvaluationList(targetEvaluationList);
                chapterEvaluation.setChapter(chapter);
                chapterEvaluationList.add(chapterEvaluation);
            } else {

                for (ChapterEvaluation chapterEvaluation : chapterEvaluationList) {
                    if (chapterEvaluation.getChapter().getId() == chapter.getId()) {

                        List<TargetEvaluation> targetEvaluationList = chapterEvaluation.getTargetEvaluationList();
                        TargetEvaluation targetEvaluation = new TargetEvaluation();

                        LevelEvaluation levelEvaluation = new LevelEvaluation();

                        List<Integer> level1List = new ArrayList<>();
                        List<Integer> level2List = new ArrayList<>();
                        List<Integer> level3List = new ArrayList<>();
                        List<Integer> level4List = new ArrayList<>();
                        List<Integer> level5List = new ArrayList<>();

                        if (level1Array != null) {
                            for (int i = 0; i < level1Array.length; i++) {
                                level1List.add(Integer.parseInt(level1Array[i]));
                            }
                        }
                        if (level2Array != null) {
                            for (int i = 0; i < level2Array.length; i++) {
                                level2List.add(Integer.parseInt(level2Array[i]));
                            }
                        }
                        if (level3Array != null) {
                            for (int i = 0; i < level3Array.length; i++) {
                                level3List.add(Integer.parseInt(level3Array[i]));
                            }
                        }
                        if (level4Array != null) {
                            for (int i = 0; i < level4Array.length; i++) {
                                level4List.add(Integer.parseInt(level4Array[i]));
                            }
                        }
                        if (level5Array != null) {
                            for (int i = 0; i < level5Array.length; i++) {
                                level5List.add(Integer.parseInt(level5Array[i]));
                            }
                        }

                        levelEvaluation.setLv1_Question_List(level1List);
                        levelEvaluation.setLv2_Question_List(level2List);
                        levelEvaluation.setLv3_Question_List(level3List);
                        levelEvaluation.setLv4_Question_List(level4List);
                        levelEvaluation.setLv5_Question_List(level5List);

                        targetEvaluation.setLevelEvaluationList(levelEvaluation);

                        targetEvaluation.setTarget(elementTarget.getTarget());

                        targetEvaluationList.add(targetEvaluation);

                        chapterEvaluation.setTargetEvaluationList(targetEvaluationList);

                        break;
                    }
                }
            }
        }

        return chapterEvaluationList;

    }
}



