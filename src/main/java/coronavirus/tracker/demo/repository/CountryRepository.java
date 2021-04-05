package coronavirus.tracker.demo.repository;


import coronavirus.tracker.demo.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Integer> {
}
