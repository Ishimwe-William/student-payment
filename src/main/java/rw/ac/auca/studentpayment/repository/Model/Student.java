package rw.ac.auca.studentpayment.repository.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;
    @Column(unique = true)
    private String regNo;
    private String firstName;
    private String lastName;
    private LocalDateTime created;
    private LocalDateTime modified;
    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "student")
    private List<StudentPayment> studentPayments;

    @PrePersist
    void onCreate() {
        this.setCreated(LocalDateTime.now());
        this.setModified(LocalDateTime.now());
    }
    @PreUpdate
    void onUpdate() {
        this.setModified(LocalDateTime.now());
    }
}