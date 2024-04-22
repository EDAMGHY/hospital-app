package ma.enset.hospital.services;

import ma.enset.hospital.entities.Role;
import ma.enset.hospital.entities.User;

public interface IUserService {
    User addUser(User user);

    Role addRole(Role role);

    User findUserByUserName(String username);

    Role findRoleByRoleName(String roleName);

    void addRoleToUser(String username, String roleName);

    User authenticate(String username, String password);

}
