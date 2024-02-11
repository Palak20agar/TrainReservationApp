# Train Reservation System

The Train Reservation System is a Java application designed to manage seat reservations on a train. It provides functionalities for users to reserve seats, retrieve booking receipts, cancel bookings, and modify existing bookings.

## Features

- **Seat Reservation**: Users can reserve seats on a train for a specific source and destination.
- **Booking Receipt**: Users can retrieve booking receipts containing information about their reservations.
- **Cancellation**: Users can cancel their bookings if needed.
- **Modification**: Users can modify their existing bookings by changing their seats.
- **Seat Availability**: The system checks seat availability before making a reservation.

## Installation

To run the Train Reservation System locally, follow these steps:

1. Clone the repository:

    ```bash
    git clone https://github.com/your-username/train-reservation-system.git
    ```

2. Navigate to the project directory:

    ```bash
    cd train-reservation-system
    ```

3. Build the project using Maven:


## Usage

To use the Train Reservation System in your Java application, follow these steps:

1. Import the `TrainReservationService` class into your project.

2. Create an instance of `TrainReservationService`.

3. Use the methods provided by `TrainReservationService` to interact with the reservation system:
   
   - `reserveSeat`: Reserve a seat for a user.
   - `getBookingReceipt`: Retrieve a booking receipt for a user.
   - `getUserSeatMapping`: Retrieve user seat mapping for a section
   - `cancelBooking`: Cancel a booking for a user.
   - `modifyBooking`: Modify an existing booking for a user.

## Testing

Unit tests for the Train Reservation System are included in the project. 




