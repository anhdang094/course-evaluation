package thanhtuu.springmvc.Service.evaluation;

import thanhtuu.springmvc.Domain.Chapter;
import thanhtuu.springmvc.Temporary.Evaluation.Base.NumberPercent;
import thanhtuu.springmvc.Temporary.Evaluation.EvaluationByLevel;
import thanhtuu.springmvc.Temporary.Evaluation.EvaluationByOneObjectModel;
import thanhtuu.springmvc.Temporary.Evaluation.Level.QuestionEasyDiff;
import thanhtuu.springmvc.Temporary.Evaluation.Level.QuestionLevelChange;
import thanhtuu.springmvc.Todo.Evaluation.ChapterEvaluation;
import thanhtuu.springmvc.Todo.Evaluation.Evaluation;
import thanhtuu.springmvc.Todo.Evaluation.LevelEvaluation;
import thanhtuu.springmvc.Todo.Evaluation.TargetEvaluation;
import thanhtuu.springmvc.Todo.ResultXLS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by anh.dang on 04/22/2017.
 */
public class EvaluationByLevelService {

    public EvaluationByLevel fetchAllForEvaluationForLevel(List<ChapterEvaluation> chapterEvaluationList, Evaluation data) {
        EvaluationByLevel evaluationByLevel = new EvaluationByLevel();
        List<ResultXLS> resultXLSList = data.getResultArray();
        List<Chapter> chapterList = data.getChapterList();

        List<Integer> questionList = new ArrayList<>();
        for (ChapterEvaluation chapterEvaluation: chapterEvaluationList) {
            List<TargetEvaluation> targetEvaluationList = chapterEvaluation.getTargetEvaluationList();
            for (TargetEvaluation targetEvaluation : targetEvaluationList) {
                LevelEvaluation levelEvaluation = targetEvaluation.getLevelEvaluationList();
                List<Integer> Lv1_Question_List = levelEvaluation.getLv1_Question_List();
                for (int i = 0; i < Lv1_Question_List.size(); i++) {
                    questionList.add(Lv1_Question_List.get(i));
                }
                List<Integer> Lv2_Question_List = levelEvaluation.getLv2_Question_List();
                for (int i = 0; i < Lv2_Question_List.size(); i++) {
                    questionList.add(Lv2_Question_List.get(i));
                }
                List<Integer> Lv3_Question_List = levelEvaluation.getLv3_Question_List();
                for (int i = 0; i < Lv3_Question_List.size(); i++) {
                    questionList.add(Lv3_Question_List.get(i));
                }
                List<Integer> Lv4_Question_List = levelEvaluation.getLv4_Question_List();
                for (int i = 0; i < Lv4_Question_List.size(); i++) {
                    questionList.add(Lv4_Question_List.get(i));
                }
                List<Integer> Lv5_Question_List = levelEvaluation.getLv5_Question_List();
                for (int i = 0; i < Lv5_Question_List.size(); i++) {
                    questionList.add(Lv5_Question_List.get(i));
                }

            }
        }

        List<NumberPercent> numberPercentList = new ArrayList<>();
        List<Float> floatList = new ArrayList<>();

        for (Integer i: questionList){
            List<Integer> questionListByOne =new ArrayList<>();
            questionListByOne.add(i);
            EvaluationByOneObjectModel evaluationByOneObjectModel = new EvaluationByOneObjectModel();
            EvaluationBaseModelService evaluationBaseModelService = new EvaluationBaseModelService();
            evaluationBaseModelService.fetchAllTrueFalseNotChooseOfQuestion(questionListByOne, evaluationByOneObjectModel, resultXLSList);
            NumberPercent numberPercent = new NumberPercent();
            numberPercent.setNumber(i);
            numberPercent.setPercent(evaluationByOneObjectModel.getNumberPercentListByTrueFalse().get(0).getPercent());
            floatList.add(evaluationByOneObjectModel.getNumberPercentListByTrueFalse().get(0).getPercent());
            numberPercentList.add(numberPercent);
        }

        List<Float> getMinMaxSumPercentListTemplatesForMax = floatList;
        List<Float> getMaxSumPercentListTemplates = new ArrayList<>();
        int numberListMax = 0;

        List<Float> getMinMaxSumPercentListTemplatesForMin = floatList;
        List<Float> getMinSumPercentListTemplates = new ArrayList<>();
        int numberListMin = 0;

        if (numberPercentList.size() >=8) {
            while (numberListMax < 4) {
                float maxSumpercentTemplates = Collections.max(getMinMaxSumPercentListTemplatesForMax);
                getMaxSumPercentListTemplates.add(maxSumpercentTemplates);
                getMinMaxSumPercentListTemplatesForMax.remove(maxSumpercentTemplates);
                numberListMax++;
            }
            while (numberListMin < 4) {
                float minSumpercentTemplates = Collections.min(getMinMaxSumPercentListTemplatesForMin);
                getMinSumPercentListTemplates.add(minSumpercentTemplates);
                getMinMaxSumPercentListTemplatesForMin.remove(minSumpercentTemplates);
                numberListMin++;
            }
        }
        else {
            int numberAverage = numberPercentList.size()/2;
            while (numberListMax < numberAverage) {
                float maxSumpercentTemplates = Collections.max(getMinMaxSumPercentListTemplatesForMax);
                getMaxSumPercentListTemplates.add(maxSumpercentTemplates);
                getMinMaxSumPercentListTemplatesForMax.remove(maxSumpercentTemplates);
                numberListMax++;
            }
            while (numberListMin < numberAverage) {
                float minSumpercentTemplates = Collections.min(getMinMaxSumPercentListTemplatesForMin);
                getMinSumPercentListTemplates.add(minSumpercentTemplates);
                getMinMaxSumPercentListTemplatesForMin.remove(minSumpercentTemplates);
                numberListMin++;
            }
        }

        List<NumberPercent> numberPercentListForListMin = new ArrayList<>();
        List<NumberPercent> numberPercentListForListMax = new ArrayList<>();

        List<NumberPercent> numberPercentListMaxTemp = new ArrayList<>();
        List<NumberPercent> numberPercentListMinTemp = new ArrayList<>();
        for (NumberPercent numberPercent: numberPercentList){
            numberPercentListMaxTemp.add(numberPercent);
            numberPercentListMinTemp.add(numberPercent);
        }

        for (Float maxPercent: getMaxSumPercentListTemplates){
            for (NumberPercent numberPercent : numberPercentListMaxTemp){
                if (maxPercent.equals(numberPercent.getPercent())){
                    numberPercentListForListMax.add(numberPercent);
                    numberPercentListMaxTemp.remove(numberPercent);
                    break;
                }
            }
        }

        for (Float minPercent: getMinSumPercentListTemplates){
            for (NumberPercent numberPercent : numberPercentListMinTemp){
                if (minPercent.equals(numberPercent.getPercent())){
                    numberPercentListForListMin.add(numberPercent);
                    numberPercentListMinTemp.remove(numberPercent);
                    break;
                }
            }
        }

        List<QuestionEasyDiff> questionEasyDiffMaxList = new ArrayList<>();
        List<QuestionEasyDiff> questionEasyDiffMinList = new ArrayList<>();
        for (ChapterEvaluation chapterEvaluation: chapterEvaluationList) {
            List<TargetEvaluation> targetEvaluationList = chapterEvaluation.getTargetEvaluationList();
            for (TargetEvaluation targetEvaluation : targetEvaluationList) {
                LevelEvaluation levelEvaluation = targetEvaluation.getLevelEvaluationList();
                List<Integer> Lv1_Question_List = levelEvaluation.getLv1_Question_List();
                for (int i = 0; i < Lv1_Question_List.size(); i++) {
                    for (NumberPercent numberPercent : numberPercentListForListMax){
                        if (Lv1_Question_List.get(i).equals(numberPercent.getNumber())){
                            QuestionEasyDiff questionEasyDiff = new QuestionEasyDiff();
                            questionEasyDiff.setLevel(1);
                            questionEasyDiff.setNumberQuestion(numberPercent.getNumber());
                            questionEasyDiff.setTrueValue(numberPercent.getPercent());
                            questionEasyDiffMaxList.add(questionEasyDiff);
                        }
                    }

                    for (NumberPercent numberPercent : numberPercentListForListMin){
                        if (Lv1_Question_List.get(i).equals(numberPercent.getNumber())){
                            QuestionEasyDiff questionEasyDiff = new QuestionEasyDiff();
                            questionEasyDiff.setLevel(1);
                            questionEasyDiff.setNumberQuestion(numberPercent.getNumber());
                            questionEasyDiff.setTrueValue(numberPercent.getPercent());
                            questionEasyDiffMinList.add(questionEasyDiff);
                        }
                    }
                }
                List<Integer> Lv2_Question_List = levelEvaluation.getLv2_Question_List();
                for (int i = 0; i < Lv2_Question_List.size(); i++) {
                    for (NumberPercent numberPercent : numberPercentListForListMax){
                        if (Lv2_Question_List.get(i).equals(numberPercent.getNumber())){
                            QuestionEasyDiff questionEasyDiff = new QuestionEasyDiff();
                            questionEasyDiff.setLevel(2);
                            questionEasyDiff.setNumberQuestion(numberPercent.getNumber());
                            questionEasyDiff.setTrueValue(numberPercent.getPercent());
                            questionEasyDiffMaxList.add(questionEasyDiff);
                        }
                    }

                    for (NumberPercent numberPercent : numberPercentListForListMin){
                        if (Lv2_Question_List.get(i).equals(numberPercent.getNumber())){
                            QuestionEasyDiff questionEasyDiff = new QuestionEasyDiff();
                            questionEasyDiff.setLevel(2);
                            questionEasyDiff.setNumberQuestion(numberPercent.getNumber());
                            questionEasyDiff.setTrueValue(numberPercent.getPercent());
                            questionEasyDiffMinList.add(questionEasyDiff);
                        }
                    }
                }
                List<Integer> Lv3_Question_List = levelEvaluation.getLv3_Question_List();
                for (int i = 0; i < Lv3_Question_List.size(); i++) {
                    for (NumberPercent numberPercent : numberPercentListForListMax){
                        if (Lv3_Question_List.get(i).equals(numberPercent.getNumber())){
                            QuestionEasyDiff questionEasyDiff = new QuestionEasyDiff();
                            questionEasyDiff.setLevel(3);
                            questionEasyDiff.setNumberQuestion(numberPercent.getNumber());
                            questionEasyDiff.setTrueValue(numberPercent.getPercent());
                            questionEasyDiffMaxList.add(questionEasyDiff);
                        }
                    }

                    for (NumberPercent numberPercent : numberPercentListForListMin){
                        if (Lv3_Question_List.get(i).equals(numberPercent.getNumber())){
                            QuestionEasyDiff questionEasyDiff = new QuestionEasyDiff();
                            questionEasyDiff.setLevel(3);
                            questionEasyDiff.setNumberQuestion(numberPercent.getNumber());
                            questionEasyDiff.setTrueValue(numberPercent.getPercent());
                            questionEasyDiffMinList.add(questionEasyDiff);
                        }
                    }
                }
                List<Integer> Lv4_Question_List = levelEvaluation.getLv4_Question_List();
                for (int i = 0; i < Lv4_Question_List.size(); i++) {
                    for (NumberPercent numberPercent : numberPercentListForListMax){
                        if (Lv4_Question_List.get(i).equals(numberPercent.getNumber())){
                            QuestionEasyDiff questionEasyDiff = new QuestionEasyDiff();
                            questionEasyDiff.setLevel(4);
                            questionEasyDiff.setNumberQuestion(numberPercent.getNumber());
                            questionEasyDiff.setTrueValue(numberPercent.getPercent());
                            questionEasyDiffMaxList.add(questionEasyDiff);
                        }
                    }

                    for (NumberPercent numberPercent : numberPercentListForListMin){
                        if (Lv4_Question_List.get(i).equals(numberPercent.getNumber())){
                            QuestionEasyDiff questionEasyDiff = new QuestionEasyDiff();
                            questionEasyDiff.setLevel(4);
                            questionEasyDiff.setNumberQuestion(numberPercent.getNumber());
                            questionEasyDiff.setTrueValue(numberPercent.getPercent());
                            questionEasyDiffMinList.add(questionEasyDiff);
                        }
                    }
                }
                List<Integer> Lv5_Question_List = levelEvaluation.getLv5_Question_List();
                for (int i = 0; i < Lv5_Question_List.size(); i++) {
                    for (NumberPercent numberPercent : numberPercentListForListMax){
                        if (Lv5_Question_List.get(i).equals(numberPercent.getNumber())){
                            QuestionEasyDiff questionEasyDiff = new QuestionEasyDiff();
                            questionEasyDiff.setLevel(5);
                            questionEasyDiff.setNumberQuestion(numberPercent.getNumber());
                            questionEasyDiff.setTrueValue(numberPercent.getPercent());
                            questionEasyDiffMaxList.add(questionEasyDiff);
                        }
                    }

                    for (NumberPercent numberPercent : numberPercentListForListMin){
                        if (Lv5_Question_List.get(i).equals(numberPercent.getNumber())){
                            QuestionEasyDiff questionEasyDiff = new QuestionEasyDiff();
                            questionEasyDiff.setLevel(5);
                            questionEasyDiff.setNumberQuestion(numberPercent.getNumber());
                            questionEasyDiff.setTrueValue(numberPercent.getPercent());
                            questionEasyDiffMinList.add(questionEasyDiff);
                        }
                    }
                }

            }
        }

        List<QuestionEasyDiff> questionEasyDiffListAll = new ArrayList<>();

        List<Integer> Lv1_Question_List = new ArrayList<>();
        List<Integer> Lv2_Question_List = new ArrayList<>();
        List<Integer> Lv3_Question_List = new ArrayList<>();
        List<Integer> Lv4_Question_List = new ArrayList<>();
        List<Integer> Lv5_Question_List = new ArrayList<>();


        for (ChapterEvaluation chapterEvaluation: chapterEvaluationList) {
            List<TargetEvaluation> targetEvaluationList = chapterEvaluation.getTargetEvaluationList();
            for (TargetEvaluation targetEvaluation : targetEvaluationList) {
                LevelEvaluation levelEvaluation = targetEvaluation.getLevelEvaluationList();
                for (Integer lv1 : levelEvaluation.getLv1_Question_List()){
                    Lv1_Question_List.add(lv1);
                }

                for (Integer lv2 : levelEvaluation.getLv2_Question_List()){
                    Lv2_Question_List.add(lv2);
                }

                for (Integer lv3 : levelEvaluation.getLv3_Question_List()){
                    Lv3_Question_List.add(lv3);
                }

                for (Integer lv4 : levelEvaluation.getLv4_Question_List()){
                    Lv4_Question_List.add(lv4);
                }

                for (Integer lv5 : levelEvaluation.getLv5_Question_List()){
                    Lv5_Question_List.add(lv5);
                }


            }
        }

        for (int i = 0; i < Lv1_Question_List.size(); i++) {
            for (NumberPercent numberPercent : numberPercentList){
                if (Lv1_Question_List.get(i).equals(numberPercent.getNumber())){
                    QuestionEasyDiff questionEasyDiff = new QuestionEasyDiff();
                    questionEasyDiff.setLevel(1);
                    questionEasyDiff.setNumberQuestion(numberPercent.getNumber());
                    questionEasyDiff.setTrueValue(numberPercent.getPercent());
                    questionEasyDiffListAll.add(questionEasyDiff);
                }
            }
        }

        for (int i = 0; i < Lv2_Question_List.size(); i++) {
            for (NumberPercent numberPercent : numberPercentList){
                if (Lv2_Question_List.get(i).equals(numberPercent.getNumber())){
                    QuestionEasyDiff questionEasyDiff = new QuestionEasyDiff();
                    questionEasyDiff.setLevel(2);
                    questionEasyDiff.setNumberQuestion(numberPercent.getNumber());
                    questionEasyDiff.setTrueValue(numberPercent.getPercent());
                    questionEasyDiffListAll.add(questionEasyDiff);
                }
            }
        }

        for (int i = 0; i < Lv3_Question_List.size(); i++) {
            for (NumberPercent numberPercent : numberPercentList){
                if (Lv3_Question_List.get(i).equals(numberPercent.getNumber())){
                    QuestionEasyDiff questionEasyDiff = new QuestionEasyDiff();
                    questionEasyDiff.setLevel(3);
                    questionEasyDiff.setNumberQuestion(numberPercent.getNumber());
                    questionEasyDiff.setTrueValue(numberPercent.getPercent());
                    questionEasyDiffListAll.add(questionEasyDiff);
                }
            }
        }

        for (int i = 0; i < Lv4_Question_List.size(); i++) {
            for (NumberPercent numberPercent : numberPercentList){
                if (Lv4_Question_List.get(i).equals(numberPercent.getNumber())){
                    QuestionEasyDiff questionEasyDiff = new QuestionEasyDiff();
                    questionEasyDiff.setLevel(4);
                    questionEasyDiff.setNumberQuestion(numberPercent.getNumber());
                    questionEasyDiff.setTrueValue(numberPercent.getPercent());
                    questionEasyDiffListAll.add(questionEasyDiff);
                }
            }
        }

        for (int i = 0; i < Lv5_Question_List.size(); i++) {
            for (NumberPercent numberPercent : numberPercentList){
                if (Lv5_Question_List.get(i).equals(numberPercent.getNumber())){
                    QuestionEasyDiff questionEasyDiff = new QuestionEasyDiff();
                    questionEasyDiff.setLevel(5);
                    questionEasyDiff.setNumberQuestion(numberPercent.getNumber());
                    questionEasyDiff.setTrueValue(numberPercent.getPercent());
                    questionEasyDiffListAll.add(questionEasyDiff);
                }
            }
        }

        List<Float> percentOfLevel_1 = new ArrayList<>();
        List<Float> percentOfLevel_2 = new ArrayList<>();
        List<Float> percentOfLevel_3 = new ArrayList<>();
        List<Float> percentOfLevel_4 = new ArrayList<>();
        List<Float> percentOfLevel_5 = new ArrayList<>();

        for (QuestionEasyDiff questionEasyDiff: questionEasyDiffListAll){
            if (questionEasyDiff.getLevel().equals(1)){
                percentOfLevel_1.add(questionEasyDiff.getTrueValue());
            }
            if (questionEasyDiff.getLevel().equals(2)){
                percentOfLevel_2.add(questionEasyDiff.getTrueValue());
            }
            if (questionEasyDiff.getLevel().equals(3)){
                percentOfLevel_3.add(questionEasyDiff.getTrueValue());
            }
            if (questionEasyDiff.getLevel().equals(4)){
                percentOfLevel_4.add(questionEasyDiff.getTrueValue());
            }
            if (questionEasyDiff.getLevel().equals(5)){
                percentOfLevel_5.add(questionEasyDiff.getTrueValue());
            }
        }

        Float sumOfLevel_1 = (float) 0;
        Float sumOfLevel_2 = (float) 0;
        Float sumOfLevel_3 = (float) 0;
        Float sumOfLevel_4 = (float) 0;
        Float sumOfLevel_5 = (float) 0;

        for (Float percent: percentOfLevel_1){
            sumOfLevel_1 = sumOfLevel_1 + percent;
        }
        for (Float percent: percentOfLevel_2){
            sumOfLevel_2 = sumOfLevel_2 + percent;
        }
        for (Float percent: percentOfLevel_3){
            sumOfLevel_3 = sumOfLevel_3 + percent;
        }
        for (Float percent: percentOfLevel_4){
            sumOfLevel_4 = sumOfLevel_4 + percent;
        }
        for (Float percent: percentOfLevel_5){
            sumOfLevel_5 = sumOfLevel_5 + percent;
        }


        Float averageOfLevel_1 = (float) sumOfLevel_1/percentOfLevel_1.size();
        Float averageOfLevel_2 = (float) sumOfLevel_2/percentOfLevel_2.size();
        Float averageOfLevel_3 = (float) sumOfLevel_3/percentOfLevel_3.size();
        Float averageOfLevel_4 = (float) sumOfLevel_4/percentOfLevel_4.size();
        Float averageOfLevel_5 = (float) sumOfLevel_5/percentOfLevel_5.size();

        List<QuestionLevelChange> questionLevelChangeList= new ArrayList<>();
        for (Float percent: percentOfLevel_1) {
            QuestionLevelChange questionLevelChange = new QuestionLevelChange();
            Integer levelNumber = 0;
            Float levelFloat = (float) 0;
            if (percent < averageOfLevel_2){
                levelNumber = 2;
                levelFloat = averageOfLevel_2;
            }
            if (percent < averageOfLevel_3){
                levelNumber = 3;
                levelFloat = averageOfLevel_3;
            }
            if (percent < averageOfLevel_4){
                levelNumber = 4;
                levelFloat = averageOfLevel_4;
            }
            if (percent < averageOfLevel_5){
                levelNumber = 5;
                levelFloat = averageOfLevel_5;
            }


            if (!levelNumber.equals(0)){
                questionLevelChange.setLevelBase(1);
                questionLevelChange.setLevelTarget(levelNumber);
                questionLevelChange.setAverageLevelTarget(levelFloat);
                questionLevelChange.setPercentOfQuestion(percent);
                List<Integer> questionNumberList = new ArrayList<>();
                for (QuestionEasyDiff questionEasyDiff: questionEasyDiffListAll){
                    if (questionEasyDiff.getTrueValue().equals(percent)){
                        questionNumberList.add(questionEasyDiff.getNumberQuestion());
                    }
                }
                questionLevelChange.setNumberQuestion(questionNumberList);
                questionLevelChangeList.add(questionLevelChange);
            }
        }

        for (Float percent: percentOfLevel_2) {
            QuestionLevelChange questionLevelChange = new QuestionLevelChange();
            Integer levelNumber = 0;
            Float levelFloat = (float) 0;
            if (percent > averageOfLevel_1){
                levelNumber = 1;
                levelFloat = averageOfLevel_1;
            }
            if (percent < averageOfLevel_3){
                levelNumber = 3;
                levelFloat = averageOfLevel_3;
            }
            if (percent < averageOfLevel_4){
                levelNumber = 4;
                levelFloat = averageOfLevel_4;
            }
            if (percent < averageOfLevel_5){
                levelNumber = 5;
                levelFloat = averageOfLevel_5;
            }
            if (!levelNumber.equals(0)) {
                questionLevelChange.setLevelBase(2);
                questionLevelChange.setLevelTarget(levelNumber);
                questionLevelChange.setAverageLevelTarget(levelFloat);
                questionLevelChange.setPercentOfQuestion(percent);
                List<Integer> questionNumberList = new ArrayList<>();
                for (QuestionEasyDiff questionEasyDiff : questionEasyDiffListAll) {
                    if (questionEasyDiff.getTrueValue().equals(percent)) {
                        questionNumberList.add(questionEasyDiff.getNumberQuestion());
                    }
                }
                questionLevelChange.setNumberQuestion(questionNumberList);
                questionLevelChangeList.add(questionLevelChange);
            }
        }

        for (Float percent: percentOfLevel_3) {
            QuestionLevelChange questionLevelChange = new QuestionLevelChange();
            Integer levelNumber = 0;
            Float levelFloat = (float) 0;
            if (percent > averageOfLevel_2){
                levelNumber = 2;
                levelFloat = averageOfLevel_2;
            }
            if (percent > averageOfLevel_1){
                levelNumber = 1;
                levelFloat = averageOfLevel_1;
            }
            if (percent < averageOfLevel_4){
                levelNumber = 4;
                levelFloat = averageOfLevel_4;
            }
            if (percent < averageOfLevel_5){
                levelNumber = 5;
                levelFloat = averageOfLevel_5;
            }
            if (!levelNumber.equals(0)) {
                questionLevelChange.setLevelBase(3);
                questionLevelChange.setLevelTarget(levelNumber);
                questionLevelChange.setAverageLevelTarget(levelFloat);
                questionLevelChange.setPercentOfQuestion(percent);
                List<Integer> questionNumberList = new ArrayList<>();
                for (QuestionEasyDiff questionEasyDiff : questionEasyDiffListAll) {
                    if (questionEasyDiff.getTrueValue().equals(percent)) {
                        questionNumberList.add(questionEasyDiff.getNumberQuestion());
                    }
                }
                questionLevelChange.setNumberQuestion(questionNumberList);
                questionLevelChangeList.add(questionLevelChange);
            }
        }

        for (Float percent: percentOfLevel_4) {
            QuestionLevelChange questionLevelChange = new QuestionLevelChange();
            Integer levelNumber = 0;
            Float levelFloat = (float) 0;
            if (percent > averageOfLevel_3){
                levelNumber = 3;
                levelFloat = averageOfLevel_3;
            }
            if (percent > averageOfLevel_2){
                levelNumber = 2;
                levelFloat = averageOfLevel_2;
            }
            if (percent > averageOfLevel_1){
                levelNumber = 1;
                levelFloat = averageOfLevel_1;
            }
            if (percent < averageOfLevel_5){
                levelNumber = 5;
                levelFloat = averageOfLevel_5;
            }
            if (!levelNumber.equals(0)) {
                questionLevelChange.setLevelBase(4);
                questionLevelChange.setLevelTarget(levelNumber);
                questionLevelChange.setAverageLevelTarget(levelFloat);
                questionLevelChange.setPercentOfQuestion(percent);
                List<Integer> questionNumberList = new ArrayList<>();
                for (QuestionEasyDiff questionEasyDiff : questionEasyDiffListAll) {
                    if (questionEasyDiff.getTrueValue().equals(percent)) {
                        questionNumberList.add(questionEasyDiff.getNumberQuestion());
                    }
                }
                questionLevelChange.setNumberQuestion(questionNumberList);
                questionLevelChangeList.add(questionLevelChange);
            }
        }

        for (Float percent: percentOfLevel_5) {
            QuestionLevelChange questionLevelChange = new QuestionLevelChange();
            Integer levelNumber = 0;
            Float levelFloat = (float) 0;
            if (percent > averageOfLevel_4){
                levelNumber = 4;
                levelFloat = averageOfLevel_4;
            }
            if (percent > averageOfLevel_3){
                levelNumber = 3;
                levelFloat = averageOfLevel_3;
            }
            if (percent > averageOfLevel_2){
                levelNumber = 2;
                levelFloat = averageOfLevel_2;
            }
            if (percent > averageOfLevel_1){
                levelNumber = 1;
                levelFloat = averageOfLevel_1;
            }
            if (!levelNumber.equals(0)) {
                questionLevelChange.setLevelBase(5);
                questionLevelChange.setLevelTarget(levelNumber);
                questionLevelChange.setAverageLevelTarget(levelFloat);
                questionLevelChange.setPercentOfQuestion(percent);
                List<Integer> questionNumberList = new ArrayList<>();
                for (QuestionEasyDiff questionEasyDiff : questionEasyDiffListAll) {
                    if (questionEasyDiff.getTrueValue().equals(percent)) {
                        questionNumberList.add(questionEasyDiff.getNumberQuestion());
                    }
                }
                questionLevelChange.setNumberQuestion(questionNumberList);
                questionLevelChangeList.add(questionLevelChange);
            }
        }


        EvaluationBaseModelService evaluationBaseModelServiceForTrueFalse = new EvaluationBaseModelService();

        EvaluationByOneObjectModel evaluationByOneObjectModelForTrueFalseLevel_1 = new EvaluationByOneObjectModel();
        EvaluationByOneObjectModel evaluationByOneObjectModelForTrueFalseLevel_2 = new EvaluationByOneObjectModel();
        EvaluationByOneObjectModel evaluationByOneObjectModelForTrueFalseLevel_3 = new EvaluationByOneObjectModel();
        EvaluationByOneObjectModel evaluationByOneObjectModelForTrueFalseLevel_4 = new EvaluationByOneObjectModel();
        EvaluationByOneObjectModel evaluationByOneObjectModelForTrueFalseLevel_5 = new EvaluationByOneObjectModel();

        evaluationBaseModelServiceForTrueFalse.fetchAllTrueFalseNotChooseOfQuestion(Lv1_Question_List, evaluationByOneObjectModelForTrueFalseLevel_1, resultXLSList);
        evaluationBaseModelServiceForTrueFalse.fetchAllTrueFalseNotChooseOfQuestion(Lv2_Question_List, evaluationByOneObjectModelForTrueFalseLevel_2, resultXLSList);
        evaluationBaseModelServiceForTrueFalse.fetchAllTrueFalseNotChooseOfQuestion(Lv3_Question_List, evaluationByOneObjectModelForTrueFalseLevel_3, resultXLSList);
        evaluationBaseModelServiceForTrueFalse.fetchAllTrueFalseNotChooseOfQuestion(Lv4_Question_List, evaluationByOneObjectModelForTrueFalseLevel_4, resultXLSList);
        evaluationBaseModelServiceForTrueFalse.fetchAllTrueFalseNotChooseOfQuestion(Lv5_Question_List, evaluationByOneObjectModelForTrueFalseLevel_5, resultXLSList);

        evaluationByLevel.setQuestionLevelChangeList(questionLevelChangeList);
        evaluationByLevel.setQuestionEasyDiffListAll(questionEasyDiffListAll);
        evaluationByLevel.setQuestionEasyDiffListMax(questionEasyDiffMaxList);
        evaluationByLevel.setQuestionEasyDiffListMin(questionEasyDiffMinList);

        evaluationByLevel.setTrueFalseOfLevel_1(evaluationByOneObjectModelForTrueFalseLevel_1);
        evaluationByLevel.setTrueFalseOfLevel_2(evaluationByOneObjectModelForTrueFalseLevel_2);
        evaluationByLevel.setTrueFalseOfLevel_3(evaluationByOneObjectModelForTrueFalseLevel_3);
        evaluationByLevel.setTrueFalseOfLevel_4(evaluationByOneObjectModelForTrueFalseLevel_4);
        evaluationByLevel.setTrueFalseOfLevel_5(evaluationByOneObjectModelForTrueFalseLevel_5);

        return evaluationByLevel;
    }
}
