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

    private final CompletedResultRepository completedResultRepository;

    private final FeedbackRepository feedbackRepository;

    @Override
    public Assessment createAssessment(AssessmentDto assessmentDto) {

        Assessment assessment = new Assessment();
        if (assessmentRepository.existsByTitleIgnoreCase(assessmentDto.getTitle())){
            throw new AssessmentException("Assessment title already exist");
        }

        assessment.setTitle(assessmentDto.getTitle());
        assessment.setCategory(assessmentDto.getCategory());
        assessment.setStandardName(assessmentDto.getStandardName());
        assessment.setDescription(assessmentDto.getDescription());
        assessment.setImageUrl(assessmentDto.getImageUrl());
        assessment.setCreatedBy(LocalDateTime.now());
        return assessmentRepository.insert(assessment);
    }

    @Override
    public Assessment getAssessment(String id) {

        return assessmentRepository.findById(id)
                .orElseThrow(() -> new AssessmentException("This Assessment does not exist"));
    }

    @Override
    public List<Assessment> getAllAssessment() {
        return assessmentRepository.findAll();
    }
    @Override
    public Assessment updateAssessment(String id, AssessmentDto assessmentDto) {
        Assessment assessment = assessmentRepository.findById(id)
                .orElseThrow(() -> new AssessmentException("This Assessment does not exist"));
        assessment.setTitle(assessmentDto.getTitle());
        assessment.setDescription(assessmentDto.getDescription());
        assessment.setStandardName(assessmentDto.getStandardName());
        assessment.setTitle(assessmentDto.getTitle());
        assessment.setImageUrl(assessmentDto.getImageUrl());
//        mapper.map(assessmentDto, assessment);
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
                .orElseThrow(() -> new AssessmentException("Section not found"));

        questionRepo.insert(question);

        section.getQuestions().add(question);
        subsectionRepo.save(section);

        return question;
    }

    @Override
    public Option createOption(OptionDto optionDto) {
        Option option = new Option();

        option.setOptionType(optionDto.getOptionType());
        option.setLabels(optionDto.getLabels());
        optionRepository.insert(option);

        AssessmentQuestion question = questionRepo.findById(optionDto.getQuestionId())
                .orElseThrow(() -> new AssessmentException("Question not found"));

        question.setOption(option);
        questionRepo.save(question);

        return option;
    }

    @Override
    public AssessmentQuestion editQuestion(String id, QuestionDto questionDto) {
        AssessmentQuestion question = questionRepo.findById(id)
                .orElseThrow(() -> new AssessmentException("question does not exist"));
        question.setQuestionText(questionDto.getQuestionText());
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
        Section section = subsectionRepo.findById(resultDto.getSectionId())
                .orElseThrow(() -> new AssessmentException("Section not found"));

        section.getResults().add(result);

        subsectionRepo.save(section);

        return result;
    }

    @Override
    public AssessmentResult updateResult(String resultId, ResultDto resultDto) {
        AssessmentResult result = resultRepository.findById(resultId)
                .orElseThrow(() -> new AssessmentException("This result does not exist"));

        result.setImageUrl(resultDto.getImageUrl());
        result.setResultType(resultDto.getResultType());
        result.setDescription(resultDto.getDescription());
        result.setMaxRange(resultDto.getMaxRange());

        return resultRepository.save(result);
    }

    @Override
    public Recommendation createRecommendation(RecommendationDto recommendationDto) {
        Recommendation recommendation = new Recommendation();
        recommendation.setTitle(recommendationDto.getTitle());
        recommendation.setImageLink(recommendationDto.getImageLink());
        recommendation.setMessage(recommendationDto.getMessage());

        recommendationRepo.insert(recommendation);

        AssessmentResult result = resultRepository.findById(recommendationDto.getResultId())
                        .orElseThrow(() -> new AssessmentException("This result already exist"));

        result.getRecommendations().add(recommendation);

        resultRepository.save(result);

        return recommendation;
    }
    @Override
    public Recommendation editRecommendation(String recommendationId, RecommendationDto recommendationDto) {

        Recommendation recommendation = recommendationRepo.findById(recommendationId).
                orElseThrow(() -> new AssessmentException("This recommendation does not exist"));

        recommendation.setMessage(recommendationDto.getMessage());
        recommendation.setTitle(recommendationDto.getTitle());
        recommendation.setImageLink(recommendationDto.getImageLink());
        return recommendationRepo.save(recommendation);
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

    @Override
    public void deleteRecommendations() {
        recommendationRepo.deleteAll();
    }

    public Section updateSection(String id, SectionDto sectionDto) {
        Section section = subsectionRepo.findById(id).
                orElseThrow(() -> new AssessmentException("This section does not exist"));
        section.setName(sectionDto.getName());
        return subsectionRepo.save(section);
    }
    @Override
    public Option updateOption(String id, OptionDto optionDto) {
        Option option = optionRepository.findById(id)
                .orElseThrow(() -> new AssessmentException("Option does not exist"));
        option.setOptionType(optionDto.getOptionType());
        option.setLabels(optionDto.getLabels());
        return optionRepository.save(option);
    }

    @Override
    public Feedback createFeedback(FeedbackDto feedbackDto) {
        Feedback feedback = new Feedback();
        feedback.setBody(feedbackDto.getBody());
        feedback.setEmail(feedbackDto.getEmail());
        feedback.setScale(feedbackDto.getScale());

        return feedbackRepository.save(feedback);
    }
    @Override
    public List<Feedback> getFeedbacks(){
        return feedbackRepository.findAll();
    }

    public List<Feedback> getFeedbacksByEmail(String email){
        return feedbackRepository.findByEmailIgnoreCase(email);
    }

    public void deleteFeedbacksByEmail(String email){
        feedbackRepository.deleteAllByEmailIgnoreCase(email);
    }

    @Override
    public CompletedResult saveCompletedResult(CompletedResultDto completedResultDto) {
        CompletedResult completedResult = CompletedResult.builder()
                .age(completedResultDto.getAge())
                .resultType(completedResultDto.getResultType())
                .gender(completedResultDto.getGender())
                .aggValue(completedResultDto.getAggValue())
                .location(completedResultDto.getLocation())
                .occupation(completedResultDto.getOccupation())
                .percentValue(completedResultDto.getPercentValue())
                .questionsWithValue(completedResultDto.getQuestionsWithValue())
                .build();

        return completedResultRepository.save(completedResult);
    }

    @Override
    public List<CompletedResult> resultsCompleted() {
        return completedResultRepository.findAll();
    }

    @Override
    public CompletedResult getCompletedResultById(String id) {
        return completedResultRepository.findById(id)
                .orElseThrow(() -> new AssessmentException("This entity does not exist"));
    }

    @Override
    public void deleteCompletedResultById(String id) {
        if (completedResultRepository.existsById(id)){
            completedResultRepository.deleteById(id);
        }
    }

    @Override
    public void deleteAllCompletedResult() {
        completedResultRepository.deleteAll();;
    }

}
