package pl.sdacademy.wiosnademo;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pl.sdacademy.wiosnademo.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

  // 1? Co ta metoda powinna zwracać
  // - jeśli zwraca pojedynczy obiekt
  // Encja, Optional<Encja>

  // wiele? Set<Encje>, List<Encje>, Collection<Encje>

  // findBy - SELECT * FROM USERS WHERE username = ?
  Optional<User> findByUsername(String username);

  // SELECT * FROM USERS WHERE USERNAME LIKE "%1" AND EMAIL LIKE "2%"
  Set<User> findAllByUsernameStartsWithAndEmailEndingWith(String username, String email);

  // SELECT TOP 10 * FROM USERS ORDER BY USERNAME ASC;
  List<User> findTop10ByOrderByUsernameAsc();

  @Query("SELECT u FROM users u LEFT JOIN FETCH u.groups WHERE u.id = :id")
  Optional<User> findUserByIdWithGroups(@Param("id") Long id);
}
