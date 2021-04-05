package coronavirus.tracker.demo.repository;


import coronavirus.tracker.demo.entity.CoronavirusDataCases;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoronavirusDataCasesRepository extends JpaRepository<CoronavirusDataCases, Integer> {
}
