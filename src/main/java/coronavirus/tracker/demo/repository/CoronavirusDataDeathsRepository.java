package coronavirus.tracker.demo.repository;


import coronavirus.tracker.demo.entity.CoronavirusDataDeaths;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoronavirusDataDeathsRepository extends JpaRepository<CoronavirusDataDeaths, Integer> {

}
