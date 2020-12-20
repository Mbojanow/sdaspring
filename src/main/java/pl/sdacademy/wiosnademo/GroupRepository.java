package pl.sdacademy.wiosnademo;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.sdacademy.wiosnademo.domain.Group;

public interface GroupRepository extends JpaRepository<Group, Long> {
}
