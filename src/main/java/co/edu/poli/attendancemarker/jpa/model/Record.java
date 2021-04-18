package co.edu.poli.attendancemarker.jpa.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "RECORD")
@Data
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated(EnumType.STRING)
    private RecordType recordType;
    private Long userId;
    private LocalDateTime eventTimeStamp;

}
