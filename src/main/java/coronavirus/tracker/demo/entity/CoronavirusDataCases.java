package coronavirus.tracker.demo.entity;

import lombok.Data;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class CoronavirusDataCases {

    @Id
    private int id;
    private String country;
    private String state;
    private int latestDayCases;
    private int previousDayCases;
    private int difference;

}
