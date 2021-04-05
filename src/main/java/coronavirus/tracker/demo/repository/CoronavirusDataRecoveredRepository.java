package coronavirus.tracker.demo.repository;


import coronavirus.tracker.demo.entity.CoronavirusDataRecovered;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoronavirusDataRecoveredRepository extends JpaRepository<CoronavirusDataRecovered, Integer> {
}
