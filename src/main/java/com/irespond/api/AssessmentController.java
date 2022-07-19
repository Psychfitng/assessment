package com.irespond.api;

import com.irespond.dtos.*;
import com.irespond.exceptions.AssessmentException;
import com.irespond.models.*;
import com.irespond.service.AssessmentServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;


@RestController @Slf4j @AllArgsConstructor @CrossOrigin
@RequestMapping("api")
public class AssessmentController {
    private final AssessmentServiceImpl assessmentService;


    @PostMapping("/assessment")
    public ResponseEntity<?> createAssessment(@RequestBody @Valid AssessmentDto assessmentDto){

        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(assessmentService.createAssessment(assessmentDto));
        }catch (AssessmentException | DataIntegrityViolationException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/assessment/{id}")
    public ResponseEntity<Assessment> getAssessment(@PathVariable @NotEmpty String id){
        return ResponseEntity.ok(assessmentService.getAssessment(id));
    }
    @GetMapping("/assessments")
    public ResponseEntity<List<Assessment>> getAllAssessment(){
        return ResponseEntity.ok(assessmentService.getAllAssessment());
    }

    @PatchMapping("/assessment-update/{id}")
    public ResponseEntity<Assessment> updateAssessment(@PathVariable @NotBlank String id, @RequestBody @Valid AssessmentDto assessmentDto){
        return ResponseEntity.status(HttpStatus.OK).body(assessmentService.updateAssessment(id, assessmentDto));
    }
    @DeleteMapping("/assessment/{id}")
    public ResponseEntity<?> deleteAssessment(@PathVariable @NotEmpty String id){
        assessmentService.deleteAssessment(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("assessment/section")
    public ResponseEntity<Section> createSection(@RequestBody @Valid SectionDto sectionDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(assessmentService.createSubAssessment(sectionDto));
    }
    @PostMapping("/question")
    public ResponseEntity<AssessmentQuestion> createQuestion(@RequestBody @Valid QuestionDto questionDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(assessmentService.createQuestion(questionDto));
    }

    @PostMapping("/option")
    public ResponseEntity<Option> createOption(@RequestBody @Valid OptionDto option){
        return ResponseEntity.status(HttpStatus.CREATED).body(assessmentService.createOption(option));
    }
    @PutMapping("/question/{id}")
    public ResponseEntity<AssessmentQuestion> editQuestion(@PathVariable @NotBlank String id, @RequestBody @Valid QuestionDto questionDto){
        return ResponseEntity.status(HttpStatus.OK).body(assessmentService.editQuestion(id, questionDto));
    }
    @PutMapping("/option-update/{id}")
    public ResponseEntity<Option> updateOption(@PathVariable @NotNull String id, @RequestBody @Valid OptionDto optionDto){
        return ResponseEntity.status(HttpStatus.OK).body(assessmentService.updateOption(id, optionDto));
    }
    @PutMapping("/result-update/{id}")
    public ResponseEntity<AssessmentResult> updateResult(@PathVariable @NotBlank String id, @RequestBody @Valid ResultDto resultDto){
        return ResponseEntity.status(HttpStatus.OK).body(assessmentService.updateResult(id, resultDto));
    }

    @PutMapping("/section-update/{id}")
    public ResponseEntity<Section> updateSection(@PathVariable @NotNull String id, @RequestBody @Valid SectionDto sectionDto){
        return ResponseEntity.status(HttpStatus.OK).body(assessmentService.updateSection(id, sectionDto));
    }

    @PutMapping("/recommendation-update/{id}")
    public ResponseEntity<Recommendation> updateRecommendation(@PathVariable @NotNull String id, @RequestBody @Valid RecommendationDto recommendationDto){
        return ResponseEntity.status(HttpStatus.OK).body(assessmentService.editRecommendation(id, recommendationDto));
    }

    @GetMapping("/questions/{assessmentName}")
    public ResponseEntity<List<AssessmentQuestion>> getAllQuestionByAssessmentType(@PathVariable @NotNull String assessmentName){
        return ResponseEntity.status(HttpStatus.OK).body(assessmentService.getAllQuestionByAssessmentType(assessmentName));
    }


    @GetMapping("/question/{id}")
    public ResponseEntity<AssessmentQuestion> getQuestionById(@PathVariable @NotBlank String id){

        return ResponseEntity.status(HttpStatus.OK).body(assessmentService.getQuestionById(id));
    }

    @DeleteMapping("/question/delete/{id}")
    public ResponseEntity<?> deleteQuestion(@PathVariable @NotBlank String id){
        assessmentService.deleteQuestion(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/questions")
    public ResponseEntity<List<AssessmentQuestion>> getAllQuestion(){
        return ResponseEntity.status(HttpStatus.OK).body(assessmentService.getAllQuestion());
    }
    @GetMapping("/options")
    public ResponseEntity<List<Option>> getAllOptions(){
        return ResponseEntity.status(HttpStatus.OK).body(assessmentService.getAllOptions());
    }
    @DeleteMapping("/assessments/delete")
    public ResponseEntity<?> deleteAllAssessment(){
        assessmentService.deleteAllAssessment();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/sections")
    public ResponseEntity<List<Section>> getAllSections(){
        return ResponseEntity.status(HttpStatus.OK).body(assessmentService.getAllSection());
    }
    @GetMapping("/section/{id}")
    public ResponseEntity<Section> getSectionById(@PathVariable @NotBlank String id){

        return ResponseEntity.ok(assessmentService.getSectionById(id));
    }
    @DeleteMapping("/sections/delete")
    public ResponseEntity<?> deleteAllSections(){
        assessmentService.deleteAllSections();
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/questions/delete")
    public ResponseEntity<?> deleteAllQuestions(){
        assessmentService.deleteAllQuestions();
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/options/delete")
    public ResponseEntity<?> deleteAllOptions(){
        assessmentService.deleteAllOptions();
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/result")
    public ResponseEntity<AssessmentResult> createResult(@RequestBody @Valid ResultDto resultDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(assessmentService.createResult(resultDto));
    }
    @PostMapping("/recommendation")
    public ResponseEntity<Recommendation> createRecommendation(@RequestBody @Valid RecommendationDto recommendationDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(assessmentService.createRecommendation(recommendationDto));
    }
    @GetMapping("/recommendations")
    public ResponseEntity<List<Recommendation>> getAllRecommendation(){
        return ResponseEntity.status(HttpStatus.OK).body(assessmentService.getAllRecommendation());
    }
    @DeleteMapping("/recommendations/delete-all")
    public ResponseEntity<?> deleteAllRecommendations(){
        assessmentService.deleteRecommendations();
        return ResponseEntity.status(HttpStatus.OK).body("Successfully Deleted");
    }
    @DeleteMapping("/recommendation/delete/{recommendationId}")
    public ResponseEntity<?> deleteRecommendation(@PathVariable @NotBlank String recommendationId){
        assessmentService.deleteRecommendation(recommendationId);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/feedback")
    public ResponseEntity<Feedback> createFeedback(@RequestBody FeedbackDto feedbackDto){
        return ResponseEntity.ok(assessmentService.createFeedback(feedbackDto));
    }

    @GetMapping("feedbacks")
    public ResponseEntity<List<Feedback>> getFeedbacks(){
        return ResponseEntity.ok(assessmentService.getFeedbacks());
    }
}
