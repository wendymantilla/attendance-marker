package co.edu.poli.attendancemarker.services;

import co.edu.poli.attendancemarker.jpa.model.Record;
import co.edu.poli.attendancemarker.jpa.model.RecordType;
import co.edu.poli.attendancemarker.jpa.repository.RecordRepository;
import co.edu.poli.attendancemarker.jpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecordDetailServiceImpl implements RecordDetailService {

    private final RecordRepository recordRepository;
    private final UserRepository userRepository;

    @Override
    public List<Record> getRecords(String userName) {
        return userRepository.findFirstByUserName(userName)
                .map(user -> recordRepository.findByUserId(user.getId()))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    }

    @Override
    public void saveRecord(String userName, RecordType recordType) {
        userRepository.findFirstByUserName(userName)
                .ifPresent(user -> {
                    Record record = new Record();
                    record.setUserId(user.getId());
                    record.setEventTimeStamp(LocalDateTime.now());
                    record.setRecordType(recordType);
                    recordRepository.save(record);
                });

    }

}
