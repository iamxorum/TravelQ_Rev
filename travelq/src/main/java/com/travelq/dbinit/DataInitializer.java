package com.travelq.dbinit;

import com.travelq.model.*;
import com.travelq.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
@Component
public class DataInitializer implements ApplicationRunner {

    private final UserRepository userRepository;
    private final FlightRepository flightRepository;
    private final TicketRepository ticketRepository;
    private final TravelOptionRepository travelOptionRepository;
    private final TravelHistoryRepository travelHistoryRepository;
    private final NotificationRepository notificationRepository;
    private final FlightComparisonRepository flightComparisonRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        if (userRepository.count() > 0) {
            System.out.println("Datele există deja. Initializarea bazei de date a fost omisă.");
            return;
        }

        // === Create Users ===
        List<UserEntity> users = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            UserEntity user = new UserEntity();
            user.setUsername("user" + i);
            user.setPassword("pass" + i);
            user.setEmail("user" + i + "@example.com");
            user.setFirstName("First" + i);
            user.setLastName("Last" + i);
            users.add(user);
        }
        userRepository.saveAll(users);

        // === Create TravelHistory for each User and save ===
        for (UserEntity user : users) {
            TravelHistoryEntity history = new TravelHistoryEntity();
            history.setUser(user);
            history.setFlightsCount(2 + new Random().nextInt(4));
            history.setTotalSpent(500 + new Random().nextInt(1500));
            travelHistoryRepository.save(history);

            user.setTravelHistory(history); // link back to user
        }
        userRepository.saveAll(users); // update user with travel history

        // === Create Flights ===
        List<FlightEntity> flights = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            FlightEntity flight = new FlightEntity();
            flight.setOrigin("City" + i);
            flight.setDestination("City" + (i + 1));
            flight.setDepartureTime(LocalDateTime.now().plusDays(i));
            flight.setArrivalTime(LocalDateTime.now().plusDays(i).plusHours(2));
            flight.setPrice(100 + i * 50);
            flight.setStopovers(i % 2);
            flights.add(flight);
        }
        flightRepository.saveAll(flights);

        // === Create Tickets and TravelOptions ===
        for (int i = 0; i < 5; i++) {
            TicketEntity ticket = new TicketEntity();
            ticket.setUser(users.get(i));
            ticket.setFlight(flights.get(i));
            ticket.setPurchaseDate(LocalDateTime.now().minusDays(i));

            ticket = ticketRepository.save(ticket); // must save before assigning travelOption (due to FK)

            TravelOptionEntity option = new TravelOptionEntity();
            option.setTicket(ticket);
            option.setCheckIn(i % 2 == 0);
            option.setSeatSelection(i % 2 == 0 ? TravelOptionSeatSelectionEnum.ECONOMY : TravelOptionSeatSelectionEnum.BUSINESS);
            option.setExtraBaggage(TravelOptionExtraBaggageEnum.CHECKED_BAGGAGE);

            travelOptionRepository.save(option);

            ticket.setTravelOption(option); // set bidirectional (optional)
            ticketRepository.save(ticket);  // update ticket
        }

        // === Create Notifications ===
        List<NotificationEntity> notifications = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            NotificationEntity notification = new NotificationEntity();
            notification.setUser(users.get(i));
            notification.setMessage("Notificare nr. " + (i + 1));
            notification.setRead(i % 2 == 0);
            notifications.add(notification);
        }
        notificationRepository.saveAll(notifications);

        // === Create Flight Comparisons ===
        for (int i = 0; i < 5; i++) {
            FlightComparisonEntity comparison = new FlightComparisonEntity();
            comparison.setFlights(Arrays.asList(flights.get(i), flights.get((i + 1) % 5)));
            flightComparisonRepository.save(comparison);
        }

        System.out.println("Baza de date a fost populată complet și corect.");
    }
}
