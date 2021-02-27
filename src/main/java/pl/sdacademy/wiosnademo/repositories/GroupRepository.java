package pl.sdacademy.wiosnademo.repositories;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pl.sdacademy.wiosnademo.domain.Group;

public interface GroupRepository extends JpaRepository<Group, String> {

  @Query("SELECT g FROM groups g left join fetch g.users WHERE g.name = :name")
  Optional<Group> findByGroupNameWithUsers(@Param("name") String name);

//  default void asd() {
//    EntityManager entityManager;
//
//    entityManager.createQuery("SELECT g FROM groups g left join fetch g.users WHERE g.name = :name", Group.class)
//        .setParameter("name", name)
//        .getFirstResult();
//  }
}
