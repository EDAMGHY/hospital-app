package ma.enset.hospital.repository;

import ma.enset.hospital.entities.Role;
import ma.enset.hospital.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleName(String roleName);

}
