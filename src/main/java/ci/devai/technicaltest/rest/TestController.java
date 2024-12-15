package ci.devai.technicaltest.rest;

import ci.devai.technicaltest.Record.ListResponse;
import ci.devai.technicaltest.service.TestService;
import ci.devai.technicaltest.service.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequiredArgsConstructor
@RequestMapping("/api/v1/test")
public class TestController {

    public TestController(TestService testService) {
        this.testService = testService;
    }

    private final TestService testService;

    @GetMapping("/{level}/{sector}")
    public List<QuestionDTO> generateQuestions(@PathVariable String level,@PathVariable String sector) {
        return testService.generateQuestions(level, sector);
    }

    @PostMapping
    List<ResultDTO> responses(@RequestBody ListResponse responses) {
        return testService.responses(responses);
    }
}
