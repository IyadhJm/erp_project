package org.oga.gestioncollaborator.repository;

import org.oga.gestioncollaborator.Entity.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserDTO,String> {
}
