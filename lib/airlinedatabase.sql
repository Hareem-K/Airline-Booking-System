-- Drop the database if it exists
DROP DATABASE IF EXISTS ARS;

-- Create the database
CREATE DATABASE ARS;

-- Switch to the newly created database
USE ARS;

-- Drop the tables if they exist
DROP TABLE IF EXISTS Seats;
DROP TABLE IF EXISTS Flights;
DROP TABLE IF EXISTS RegisteredUsers;



-- Create the Users table for Registered Users
CREATE TABLE RegisteredUsers (
                                 FullName VARCHAR(255) PRIMARY KEY,
                                 Password VARCHAR(255) NOT NULL,
                                 Email VARCHAR(255) NOT NULL
);

CREATE TABLE CreditCard (
                            CardNumber BIGINT PRIMARY KEY,
                            ExpiryDate VARCHAR(255) NOT NULL,
                            CardholderName VARCHAR(255) NOT NULL,
                            FOREIGN KEY (CardholderName) REFERENCES RegisteredUsers(FullName)
);




-- Create the Flights table
CREATE TABLE Flights (
                         FlightNumber INT PRIMARY KEY,
                         DepartureTime DATETIME,
                         ArrivalTime DATETIME,
                         Destination VARCHAR(255),
                         CrewID INT
);

CREATE TABLE Payments (
                          CardNumber BIGINT,
                          PaymentType VARCHAR(50),
                          FlightID INT,
                          Price DOUBLE,
                          FOREIGN KEY (CardNumber) REFERENCES CreditCard(CardNumber),
                          FOREIGN KEY (FlightID) REFERENCES Flights(FlightNumber)
);

-- Create the Seats table
CREATE TABLE Seats (
                       FlightNumber INT,
                       SeatID VARCHAR(2),
                       SeatType VARCHAR(20),
                       PassengerName VARCHAR(50),
                       FOREIGN KEY (FlightNumber) REFERENCES Flights(FlightNumber)
);

INSERT INTO Flights (FlightNumber, DepartureTime, ArrivalTime, Destination, CrewID)
VALUES
    (124, '2023-12-12 08:00:00', '2023-12-12 11:00:00', 'New York', 789),
    (125, '2023-11-01 15:30:00', '2023-11-20 18:30:00', 'London', 234),
    (126, '2024-01-05 09:45:00', '2024-01-05 13:45:00', 'Tokyo', 567);

INSERT INTO Seats (FlightNumber, SeatID, SeatType, PassengerName)
VALUES
    (124, 'A1', 'First Class', "John Doe"),
    (124, 'A2', 'First Class', NULL),
    (124, 'A3', 'First Class', NULL),
    (124, 'A4', 'First Class', NULL),
    (124, 'B1', 'Business Class', NULL),
    (124, 'B2', 'Business Class', NULL),
    (124, 'B3', 'Business Class', NULL),
    (124, 'B4', 'Business Class', "Alice Smith"),
    (124, 'C1', 'Economy', NULL),
    (124, 'C2', 'Economy', NULL),
    (124, 'C3', 'Economy', NULL),
    (124, 'C4', 'Economy', NULL),
    (124, 'C5', 'Economy', "Sophia Wilson"),
    (124, 'C6', 'Economy', NULL),
    (124, 'C7', 'Economy', NULL),
    (124, 'C8', 'Economy', NULL),

    (125, 'A1', 'First Class', NULL),
    (125, 'A2', 'First Class', NULL),
    (125, 'A3', 'First Class', NULL),
    (125, 'A4', 'First Class', NULL),
    (125, 'B1', 'Business Class', NULL),
    (125, 'B2', 'Business Class', NULL),
    (125, 'B3', 'Business Class', NULL),
    (125, 'B4', 'Business Class', "Emma Davis"),
    (125, 'C1', 'Economy', NULL),
    (125, 'C2', 'Economy', NULL),
    (125, 'C3', 'Economy', NULL),
    (125, 'C4', 'Economy', NULL),
    (125, 'C5', 'Economy', NULL),
    (125, 'C6', 'Economy', NULL),
    (125, 'C7', 'Economy', NULL),
    (125, 'C8', 'Economy', NULL),

    (126, 'A1', 'First Class', 'Bob Johnson'),
    (126, 'A2', 'First Class', NULL),
    (126, 'A3', 'First Class', NULL),
    (126, 'A4', 'First Class', NULL),
    (126, 'B1', 'Business Class', NULL),
    (126, 'B2', 'Business Class', NULL),
    (126, 'B3', 'Business Class', NULL),
    (126, 'B4', 'Business Class', NULL),
    (126, 'C1', 'Economy', NULL),
    (126, 'C2', 'Economy', NULL),
    (126, 'C3', 'Economy', NULL),
    (126, 'C4', 'Economy', NULL),
    (126, 'C5', 'Economy', NULL),
    (126, 'C6', 'Economy', NULL),
    (126, 'C7', 'Economy', NULL),
    (126, 'C8', 'Economy', NULL);

INSERT INTO RegisteredUsers (FullName, Password, Email)
VALUES
    ('John Doe', 'pass123', 'john@example.com'),
    ('Alice Smith', 'pass456', 'alice@example.com'),
    ('Bob Johnson', 'pass789', 'bob@example.com'),
    ('Emma Davis', 'pass246', 'emma@example.com'),
    -- Add more fictional users as needed
    ('Sophia Wilson', 'pass789', 'sophia@example.com');

INSERT INTO CreditCard (CardholderName, CardNumber, ExpiryDate) VALUES
                                                                    ('John Doe', '1111222233334444', '12/25'),
                                                                    ('Alice Smith', '5555666677778888', '09/24'),
                                                                    ('Bob Johnson', '9999888877776666', '06/23'),
                                                                    ('Emma Davis', '4444333322221111', '03/26'),
                                                                    -- Add more credit card information for other users
                                                                    ('Sophia Wilson', '1234123412341234', '08/27');

INSERT INTO Payments (CardNumber, PaymentType, FlightID, Price) VALUES
                                                                    ('1111222233334444', 'Flight Purchase', 124, 100.00),
                                                                    ('5555666677778888', 'Flight Purchase', 124, 80.00),
                                                                    ('1234123412341234', 'Flight Purchase', 124, 50.00),
                                                                    ('4444333322221111', 'Flight Purchase', 125, 72.00),
                                                                    ('9999888877776666', 'Flight Purchase', 126, 100.00);
-- Add more payment information for other flights



-- Optionally, switch back to a different database if needed
-- USE AnotherDatabase;

-- Drop the database (if you need to clean up)
-- Ensure you are not using the database you intend to drop
-- USE AnotherDatabase; -- Uncomment this line if you switch to another database first
-- DROP DATABASE IF EXISTS YourDatabaseName;