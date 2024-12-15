package ci.devai.technicaltest.Repository;

import ci.devai.technicaltest.domain.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, Long> {}
