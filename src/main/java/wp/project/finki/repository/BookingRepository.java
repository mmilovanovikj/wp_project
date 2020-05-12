package wp.project.finki.repository;

import wp.project.finki.domain.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Represents repository for booking
 */
@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    /**
     * Searches for all user's bookings by username
     *
     * @param username user's username
     * @return list of all user's bookings
     */
    @Query(value = "SELECT * " +
            "FROM booking, user, traveling_data " +
            "WHERE user_id IN (" +
            "SELECT id FROM user WHERE username = ?1) " +
            "AND traveling_data_id = traveling_data.id",
            nativeQuery = true)
    List<Booking> findAllUserBookings(String username);

    /**
     * Updates booking by reserved tickets count
     *
     * @param reservedTicketsCount reserved tickets count
     * @param id                   booking's id
     */
    @Modifying
    @Query(value = "UPDATE booking " +
            "SET reserved_tickets_count = ?1 " +
            "WHERE id = ?2 ",
            nativeQuery = true)
    void updateByTickets(int reservedTicketsCount, long id);
}