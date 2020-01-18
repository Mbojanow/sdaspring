package pl.sdacademy.wiosnademo.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.sdacademy.wiosnademo.domain.Reservation;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class ReservationRepositoryTest {

    @Autowired
    private ReservationRepository reservationRepository;

    @Test
    void test() {
        final long base =  new Date().getTime() + 5000;

        Reservation reservationA = Reservation.builder()
                .carPlate("asd")
                .surname("qwe")
                .from(new Date(base + 99))
                .to(new Date(base + 101))
                .build();

        Reservation reservationB = Reservation.builder()
                .carPlate("asd")
                .surname("qwe")
                .from(new Date(base + 199))
                .to(new Date(base + 201))
                .build();

        Reservation reservationC = Reservation.builder()
                .carPlate("asd")
                .surname("qwe")
                .from(new Date(base + 101))
                .to(new Date(base + 199))
                .build();

        Reservation reservationD = Reservation.builder()
                .carPlate("asd")
                .surname("qwe")
                .from(new Date(base + 99))
                .to(new Date(base + 201))
                .build();

        Reservation reservationE = Reservation.builder()
                .carPlate("asd")
                .surname("qwe")
                .from(new Date(base + 50))
                .to(new Date(base + 99))
                .build();

        Reservation reservationF = Reservation.builder()
                .carPlate("asd")
                .surname("qwe")
                .from(new Date(base + 201))
                .to(new Date(base + 250))
                .build();

        reservationRepository.saveAll(List.of(reservationA, reservationB, reservationC, reservationD, reservationE, reservationF));

        final Date from = new Date(base + 100);
        final Date to = new Date(base + 200);

        Long value1 = reservationRepository.countReservationsWithTimesBetweenFrom(from, to);
        Long value2 = reservationRepository.countReservationsWithTimesBetweenTo(from, to);
        Long value3 = reservationRepository.countReservationWithTimesInsideOfPeriod(from, to);
        Long value4 = reservationRepository.countReservationWithTimesOutsideOfPeriod(from, to);
        assertTrue(Stream.of(value1, value2, value3, value4).allMatch(x -> x == 1));
    }
}