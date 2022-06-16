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
    private final SectionRepository subsectionRepo;
    private final QuestionRepository questionRepo;
    private final ModelMapper mapper;
    private final OptionRepository optionRepository;

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


        Assessment assessment = assessmentRepository.findById(id)
                .orElseThrow(() -> new AssessmentException("This Assessment does not exist"));
        log.info("ASSESSMENT -> {}", assessment);
        log.info("ASSESSMENT CHAIN -> {}", assessment.getSections().get(0).getQuestions());
        return assessment;
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
        Section section = new Section();

        section.setName(sectionDto.getName());

        Assessment assessment = assessmentRepository.findById(sectionDto.getAssessmentId())
                .orElseThrow(() -> new AssessmentException("Assessment not found"));

        subsectionRepo.save(section);

        assessment.getSections().add(section);
        assessmentRepository.save(assessment);

        return section;
    }

    @Override
    public AssessmentQuestion createQuestion(QuestionDto questionDto) {
        AssessmentQuestion question = new AssessmentQuestion();

        question.setQuestionText(questionDto.getQuestionText());
        Section section = subsectionRepo.findById(questionDto.getSectionId())
                .orElseThrow(() -> new AssessmentException("Session not found"));

        questionRepo.insert(question);
        log.info("SAVED QUESTION -> {}", question);

        section.getQuestions().add(question);
        subsectionRepo.save(section);

        return question;
    }

    @Override
    public Option createOption(OptionDto optionDto) {
        log.info("OPTIONDTO -> {}",optionDto);

        Option option = new Option();

        option.setOptionType(optionDto.getOptionType());
        option.setLabels(optionDto.getLabels());
        optionRepository.insert(option);
        log.info("SAVED OPTION -> {}", option);

        AssessmentQuestion question = questionRepo.findById(optionDto.getQuestionId())
                .orElseThrow(() -> new AssessmentException("Question not found"));

//        optionRepository.insert(option);


        question.getOptions().add(option);
        log.info("QUESTION BEFORE SAVING -> {}", question);
        questionRepo.save(question);
        log.info("QUESTION AFTER SAVING -> {}", question);


        return option;
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
    public List<Section> getAllSection() {
        return subsectionRepo.findAll();
    }

    @Override
    public void deleteAllSections() {
        subsectionRepo.deleteAll();
    }

    @Override
    public void deleteAllOptions() {
        optionRepository.deleteAll();
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

        resultRepository.insert(result);
        Section section = subsectionRepo.findById(resultDto.getSectionId()).get();

        section.getResult().add(result);

        subsectionRepo.save(section);

        return result;
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

        recommendationRepo.insert(recommendation);

        AssessmentResult result = resultRepository.findById(recommendationDto.getResultId()).get();

        result.getRecommendations().add(recommendation);

        resultRepository.save(result);

        return recommendation;
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

    @Override
    public List<Option> getAllOptions() {
        return optionRepository.findAll();
    }

    @Override
    public AssessmentQuestion getQuestionById(String id) {
        return questionRepo.findById(id)
                .orElseThrow(() -> new AssessmentException("Question not found"));
    }

    @Override
    public Section getSectionById(String id) {
        return subsectionRepo.findById(id)
                .orElseThrow(() -> new AssessmentException("Section not found"));
    }
}
