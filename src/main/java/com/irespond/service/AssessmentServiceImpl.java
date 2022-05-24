package com.irespond.service;

import com.irespond.dtos.*;
import com.irespond.exceptions.AssessmentException;
import com.irespond.models.*;
import com.irespond.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service @Slf4j @RequiredArgsConstructor
public class AssessmentServiceImpl implements AssessmentService{

    private final ResultRepository resultRepository;
    private final AssessmentRepository assessmentRepository;
    private final RecommendationRepo recommendationRepo;
    private final SubsectionRepo subsectionRepo;
    private final QuestionRepository questionRepo;
    private final ModelMapper mapper;

    @Override
    public Assessment createAssessment(AssessmentDto assessmentDto) {

        Assessment assessment = new Assessment();

        assessment.setTitle(assessmentDto.getTitle());
        assessment.setCategory(assessmentDto.getCategory());
        assessment.setStandardName(assessmentDto.getStandardName());
        assessment.setDescription(assessmentDto.getDescription());
        assessment.setCreatedBy(LocalDateTime.now());

        return assessmentRepository.insert(assessment);
    }

    @Override
    public Assessment getAssessment(String id) {
        return assessmentRepository.findById(id).orElseThrow(() -> new AssessmentException("This Assessment does not exist"));
    }

    @Override
    public List<Assessment> getAllAssessment() {
        return assessmentRepository.findAll();
    }

    @Override
    public Assessment updateAssessment(String id, AssessmentDto assessmentDto) {
        Assessment assessment = assessmentRepository.findById(id)
                .orElseThrow(() -> new AssessmentException("This Assessment does not exist"));
        mapper.map(assessmentDto, assessment);
        return assessmentRepository.save(assessment);
    }

    @Override
    public void deleteAssessment(String id) {
        assessmentRepository.deleteById(id);
    }

    @Override
    public Section createSubAssessment(SectionDto sectionDto) {

        return subsectionRepo.insert(new Section(sectionDto.getName(), assessmentRepository.findById(sectionDto.getAssessmentId()).get()));
    }

    @Override
    public AssessmentQuestion createQuestion(QuestionDto questionDto) {
        AssessmentQuestion question = new AssessmentQuestion();

        question.setQuestionText(questionDto.getQuestionText());
        question.setOptions(questionDto.getOptions());
        question.setCategory(subsectionRepo.findById(questionDto.getSectionId()).orElse(null));
        return questionRepo.insert(question);
    }

    @Override
    public AssessmentQuestion editQuestion(String id, QuestionDto questionDto) {
        AssessmentQuestion question = new AssessmentQuestion();
        mapper.map(questionDto, question);
        return questionRepo.save(question);
    }

    @Override
    public List<AssessmentQuestion> getAllQuestionByAssessmentType(String assessmentName) {
        return null;
    }

    @Override
    public void deleteQuestion(String id) {
        questionRepo.deleteById(id);
    }

    @Override
    public List<AssessmentQuestion> getAllQuestion() {
        return questionRepo.findAll();
    }

    @Override
    public void deleteAllAssessment() {
        assessmentRepository.deleteAll();
    }

    @Override
    public void deleteAllQuestions() {
        questionRepo.deleteAll();
    }

    @Override
    public AssessmentResult createResult(ResultDto resultDto) {
        AssessmentResult result = new AssessmentResult();
        result.setDescription(resultDto.getDescription());
        result.setResultType(resultDto.getResultType());
        result.setImageUrl(resultDto.getImageUrl());
        result.setMaxRange(resultDto.getMaxRange());

        return resultRepository.insert(result);
    }

    @Override
    public AssessmentResult updateResult(String resultId, ResultDto resultDto) {
        AssessmentResult result = resultRepository.findById(resultId)
                .orElseThrow(() -> new AssessmentException("This result does not exist"));

        mapper.map(resultDto, result);
        return resultRepository.save(result);
    }

    @Override
    public Recommendation createRecommendation(RecommendationDto recommendationDto) {
        Recommendation recommendation = new Recommendation();
        recommendation.setTitle(recommendationDto.getTitle());
        recommendation.setImageLink(recommendationDto.getImageLink());
        recommendation.setMessage(recommendationDto.getMessage());

        return recommendationRepo.insert(recommendation);
    }

    @Override
    public Recommendation editRecommendation(String recommendationId, RecommendationDto recommendationDto) {

        Recommendation recommendation = recommendationRepo.findById(recommendationId).
                orElseThrow(() -> new AssessmentException("This recommendation does not exist"));
        mapper.map(recommendationDto, recommendation);
        return recommendationRepo.save(recommendation);
    }

    @Override
    public void addRecommendationToResult(String resultId, Recommendation... recommendations) {
            AssessmentResult result = resultRepository.findById(resultId).
                    orElseThrow(() -> new AssessmentException("This result does not exist"));

        for (Recommendation recommendation: recommendations
             ) {
            result.getRecommendations().add(recommendation);
        }
        resultRepository.save(result);
    }
    @Override
    public List<Recommendation> getAllRecommendation() {
        return recommendationRepo.findAll();
    }

    @Override
    public void deleteRecommendation(String recommendationId) {
        recommendationRepo.deleteById(recommendationId);
    }
}
