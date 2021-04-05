package coronavirus.tracker.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DataByCountry {
    private String country;
    private int latestDay;
    private int previousDay;
    private int difference;
}
