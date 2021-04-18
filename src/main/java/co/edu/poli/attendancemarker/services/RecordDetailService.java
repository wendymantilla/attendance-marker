package co.edu.poli.attendancemarker.services;

import co.edu.poli.attendancemarker.jpa.model.Record;
import co.edu.poli.attendancemarker.jpa.model.RecordType;

import java.util.List;

public interface RecordDetailService {

    List<Record> getRecords(String userName);

    void saveRecord(String userName, RecordType recordType);
}
