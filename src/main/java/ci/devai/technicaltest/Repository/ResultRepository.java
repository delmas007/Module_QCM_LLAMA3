package ci.devai.technicaltest.Repository;


import ci.devai.technicaltest.domain.Result;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResultRepository extends JpaRepository<Result, Long> {
    List<Result> findByTestId(Long testId);
}
