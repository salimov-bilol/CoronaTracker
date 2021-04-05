package coronavirus.tracker.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoronavirusDataRecovered {
    @Id
    private int id;
    private String country;
    private String state;
    private int latestDayRecovered;
    private int previousDayRecovered;
    private int difference;
}
