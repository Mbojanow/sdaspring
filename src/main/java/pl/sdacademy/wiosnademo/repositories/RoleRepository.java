package pl.sdacademy.wiosnademo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.sdacademy.wiosnademo.domain.Role;

public interface RoleRepository extends JpaRepository<Role, String> {
}
