package ci.devai.technicaltest.Record;

import ci.devai.technicaltest.domain.enumeration.Level;
import ci.devai.technicaltest.domain.enumeration.Sector;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public record Test(Long id,LocalDateTime startDate, LocalDateTime endDate, Duration duration, Level level, Sector sector, String mail, List<Result> results) {
}
