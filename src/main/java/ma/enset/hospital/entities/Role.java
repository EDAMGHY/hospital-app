package ma.enset.hospital.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "ROLE_NAME", unique = true, length = 20)
    private String roleName;
    private String desc;
    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "USERS_ROLES")
    private List<User> users = new ArrayList<>();
}
