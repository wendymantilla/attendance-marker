package co.edu.poli.attendancemarker.jpa.model;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "USER")
public class User {

    @Id
    private Long id;
    private String userName;
    private String password;
    private String email;
    private Boolean enabled;
    private LocalDateTime lastPasswordResetDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="USER_AUTHORITY",
            joinColumns = @JoinColumn(name = "USER_ID",
                    referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "AUTHORITY_ID",
                    referencedColumnName = "ID"))
    private List<Authority> authorities;

}
