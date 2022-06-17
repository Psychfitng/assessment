package com.irespond.service;

import com.irespond.dtos.*;
import com.irespond.models.*;

import java.util.List;


public interface AssessmentService {

    Assessment createAssessment(AssessmentDto assessmentDto);

    Assessment getAssessment(String id);


    List<Assessment> getAllAssessment();

    Assessment updateAssessment(String id, AssessmentDto assessmentDto);


    void deleteAssessment(String id);

    Section createSubAssessment(SectionDto sectionDto);


    AssessmentQuestion createQuestion(QuestionDto questionDto);

    Option createOption(OptionDto option);

    AssessmentQuestion editQuestion(String id, QuestionDto questionDto);

    List<AssessmentQuestion> getAllQuestionByAssessmentType(String assessmentName);

    void deleteQuestion(String id);

    List<AssessmentQuestion> getAllQuestion();

    void deleteAllAssessment();

    List<Section> getAllSection();

    void deleteAllSections();

    void deleteAllOptions();

    void deleteAllQuestions();

    AssessmentResult createResult(ResultDto resultDto);

    AssessmentResult updateResult(String resultId, ResultDto resultDto);

    Recommendation createRecommendation(RecommendationDto recommendationDto);

    Recommendation editRecommendation(String recommendationId, RecommendationDto recommendationDto);

    List<Recommendation> getAllRecommendation();

    void deleteRecommendation(String recommendationId);

    List<Option> getAllOptions();

    AssessmentQuestion getQuestionById(String id);

    Section getSectionById(String id);

    void deleteRecommendations();
}
