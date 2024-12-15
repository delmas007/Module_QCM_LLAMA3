package ci.devai.technicaltest.service.mapping;



import ci.devai.technicaltest.domain.Test;
import ci.devai.technicaltest.service.dto.TestDTO;

import java.util.stream.Collectors;

public class TestMapping {

    public static TestDTO toDto(Test test) {
        if (test == null) {
            return null;
        }

        TestDTO testDTO = new TestDTO();
        testDTO.setId(test.getId());
        testDTO.setStartDate(test.getStartDate());
        testDTO.setEndDate(test.getEndDate());
        testDTO.setDuration(test.getDuration());
        testDTO.setLevel(test.getLevel());
        testDTO.setSector(test.getSector());
        testDTO.setMail(test.getMail());

        if (test.getResults() != null) {
            testDTO.setResults(test.getResults().stream().map(ResultMapping::toDto).collect(Collectors.toList()));
        }

        return testDTO;
    }

    public static TestDTO toDtoRecord(ci.devai.technicaltest.Record.Test test) {
        if (test == null) {
            return null;
        }

        TestDTO testDTO = new TestDTO();
        testDTO.setId(test.id());
        testDTO.setStartDate(test.startDate());
        testDTO.setEndDate(test.endDate());
        testDTO.setDuration(test.duration());
        testDTO.setLevel(test.level());
        testDTO.setSector(test.sector());
        testDTO.setMail(test.mail());

        if (test.results() != null) {
            testDTO.setResults(test.results().stream().map(ResultMapping::toDtoRecord).collect(Collectors.toList()));
        }

        return testDTO;
    }

    public static Test toEntity(TestDTO testDTO) {
        if (testDTO == null) {
            return null;
        }

        Test test = new Test();
        test.setId(testDTO.getId());
        test.setStartDate(testDTO.getStartDate());
        test.setEndDate(testDTO.getEndDate());
        test.setDuration(testDTO.getDuration());
        test.setLevel(testDTO.getLevel());
        test.setSector(testDTO.getSector());
        test.setMail(testDTO.getMail());

        if (testDTO.getResults() != null) {
            test.setResults(testDTO.getResults().stream().map(ResultMapping::toEntity).collect(Collectors.toList()));
        }

        return test;
    }
}
