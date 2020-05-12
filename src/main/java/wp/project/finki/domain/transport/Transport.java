package wp.project.finki.domain.transport;

import wp.project.finki.domain.TravelingPoint;

import wp.project.finki.exception.FailedInitializationException;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

import static wp.project.finki.utility.Constants.HOUR;

/**
 * Abstract class that represents the means of transport
 */
@Entity
@Table(name = "transport")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
@JsonSubTypes(
        {@JsonSubTypes.Type(value = Airplane.class, name = "Airplane"),
                @JsonSubTypes.Type(value = Bus.class, name = "Bus")
        })
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING, name = "transport_type")
public abstract class Transport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected long id;
    @NotNull(message = "Transport class can not be null")
    @Enumerated(EnumType.STRING)
    @Column(name = "transport_class", nullable = false, length = 12)
    protected TransportClass transportClass;

    /**
     * Constructor
     *
     * @param transportClass transport's class
     */
    public Transport(TransportClass transportClass) {
        this.transportClass = transportClass;
    }

    /**
     * Constructor
     *
     * @param transport object contains transport's data
     */
    public Transport(Transport transport) {
        this(transport.id, transport.transportClass);
    }

    /**
     * Constructor
     *
     * @param id             transport's id
     * @param transportClass transport's class
     */
    public Transport(long id, TransportClass transportClass) {
        this(transportClass);
        this.id = id;
    }

    /**
     * Parses duration in a specific format
     *
     * @param duration the duration of a traveling
     * @return the duration in appropriate format
     */
    public LocalTime parseToLocalTime(Double duration) {
        String durationRound = String.format("%.2f", duration);

        return generateProperTime(durationRound);
    }

    private LocalTime generateProperTime(String durationString) {
        String minutesString = durationString.substring(durationString.indexOf(',') + 1, durationString.length());
        String hoursString = durationString.substring(0, durationString.indexOf(','));
        int hours = Integer.parseInt(hoursString);
        int minutes = Integer.parseInt(minutesString);

        if (hours >= 24) {
            throw new FailedInitializationException("Improper transport");
        }

        while (minutes >= HOUR) {
            hours += 1;
            minutes -= 60;
        }

        return LocalTime.of(hours, minutes);
    }

    /**
     * Calculates the duration between two traveling points
     * based on the type of transport
     *
     * @param departurePoint   departure traveling point
     * @param destinationPoint destination traveling point
     * @return the duration
     */
    public abstract LocalTime calculateDuration(TravelingPoint departurePoint, TravelingPoint destinationPoint);
}