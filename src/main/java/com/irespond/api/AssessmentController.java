package com.irespond.api;

import com.irespond.dtos.AssessmentDto;
import com.irespond.dtos.QuestionDto;
import com.irespond.dtos.RecommendationDto;
import com.irespond.dtos.ResultDto;
import com.irespond.models.*;
import com.irespond.service.AssessmentServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;


@RestController @Slf4j @AllArgsConstructor @CrossOrigin
@RequestMapping("api")
public class AssessmentController {
    private final AssessmentServiceImpl assessmentService;


    @PostMapping("/assessment")
    public ResponseEntity<?> createAssessment(@RequestBody @Valid AssessmentDto assessmentDto){

        return ResponseEntity.status(HttpStatus.CREATED).body(assessmentService.createAssessment(assessmentDto));
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

    @PostMapping("assessment/category/{name}")
    public ResponseEntity<Section> createSubAssessment(@PathVariable @NotBlank String name){
        return ResponseEntity.status(HttpStatus.CREATED).body(assessmentService.createSubAssessment(name));
    }
    @PostMapping("/question")
    public ResponseEntity<AssessmentQuestion> createQuestion(@RequestBody @Valid QuestionDto questionDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(assessmentService.createQuestion(questionDto));
    }
    @PatchMapping("/question/{id}")
    public ResponseEntity<AssessmentQuestion> editQuestion(@PathVariable @NotBlank String id, @RequestBody @Valid QuestionDto questionDto){
        return ResponseEntity.status(HttpStatus.OK).body(assessmentService.editQuestion(id, questionDto));
    }

    @GetMapping("/questions/{assessmentName}")
    public ResponseEntity<List<AssessmentQuestion>> getAllQuestionByAssessmentType(@PathVariable @NotBlank String assessmentName){
        return ResponseEntity.status(HttpStatus.OK).body(assessmentService.getAllQuestionByAssessmentType(assessmentName));
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
    @DeleteMapping("/assessments/delete")
    public ResponseEntity<?> deleteAllAssessment(){
        assessmentService.deleteAllAssessment();
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/questions/delete")
    public ResponseEntity<?> deleteAllQuestions(){
        assessmentService.deleteAllQuestions();
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
    @PostMapping("/recommendation/add")
    public ResponseEntity<?> addRecommendationToResult(String resultId, Recommendation... recommendations){
        assessmentService.addRecommendationToResult(resultId, recommendations);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
    @GetMapping("/recommendations")
    public ResponseEntity<List<Recommendation>> getAllRecommendation(){
        return ResponseEntity.status(HttpStatus.OK).body(assessmentService.getAllRecommendation());
    }
    @DeleteMapping("/recomendation/delete/{recommendationId}")
    public ResponseEntity<?> deleteRecommendation(@PathVariable @NotBlank String recommendationId){
        assessmentService.deleteRecommendation(recommendationId);
        return ResponseEntity.noContent().build();
    }
}
