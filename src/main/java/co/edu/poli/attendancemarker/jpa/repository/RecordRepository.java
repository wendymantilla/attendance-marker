package co.edu.poli.attendancemarker.jpa.repository;

import co.edu.poli.attendancemarker.jpa.model.Record;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface RecordRepository extends JpaRepository<Record, Long> {

    List<Record> findByUserId(Long userId);

    List<Record> findByUserIdAndEventTimeStampBetween(Long userId, LocalDateTime localDateTime1, LocalDateTime localDateTime2);
}
