package ma.enset.hospital.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import ma.enset.hospital.entities.Role;
import ma.enset.hospital.entities.User;
import ma.enset.hospital.repository.RoleRepository;
import ma.enset.hospital.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class UserService implements IUserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Override
    public User addUser(User user) {
        user.setUserId(UUID.randomUUID().toString());
        return userRepository.save(user);
    }

    @Override
    public Role addRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public User findUserByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Role findRoleByRoleName(String roleName) {

        return roleRepository.findByRoleName(roleName);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        User user = this.findUserByUserName(username);
        Role role = this.findRoleByRoleName(roleName);
        if (user.getRoles() != null) user.getRoles().add(role);
    }

    @Override
    public User authenticate(String username, String password) {
        User user = this.findUserByUserName(username);
        if (user == null) throw new RuntimeException("Bad credentials");
        if (user.getPassword().equals(password)) return user;
        throw new RuntimeException("Bad credentials");
    }
}
