# ENSF-480-Final-Project-Airline-Booking-System
This is the final project for ENSF480, It is an airline booking system with a fully implemented front end and back end.

## How do I Run the Application
1. Once you clone the repository, in srv/controllers/DatabaseConnectionManager.java, adjust the SQL login info. Then, run the SQL database in MySQL
2. Simply run the program by running src/main.java, to use the GUI interaction to browse flights, view/cancel flights, and view payments. Sample screenshots are below!

## Group 26 Team Members
Zach Pelkey, 30156783

Hareem Khan, 30140959

Houssem Zaggar, 30163978

Vishnu Dhanda, 30145576

## Application Screenshots
Starting up the application after running src/main.java
![screenshot](https://i.postimg.cc/4yHd2Gcb/Welcome.png)

Registering and loging in as a customer
![screenshot](https://i.postimg.cc/mZVfJJj7/User-Selection.png)

Once the user has registered and logged in, they see the user hub screen where they can select different flight options
![screenshot](https://i.postimg.cc/mDKspHBh/UserHub.png)

From the user hub, when the user selects "Browse Flights", the user can browse the existing flights, and select a seat using the seat map
![screenshot](https://i.postimg.cc/5NTC5PhM/Browse-Flights.png)

![screenshot](https://i.postimg.cc/T3j3yCG0/SeatMap.png)

From the user hub, when the user selects "View and Cancel Flights", if the user has already made a booking, they will be able to view and cancel it for a $20 cancellation fee. The cancellation fee will then be added to the "View Payments" tab.

![screenshot](https://i.postimg.cc/wMc9tBYv/Cancel-Flight.png)

From the user hub, when the user selects "View Payments", they can view all of the payments made to their credit card. If they have reserved a seat on the flight, they will be able to see their purchase. If they have cancelled their flight, they will only see the $20 cancellation fee.

![screenshot](https://i.postimg.cc/VL8j2Z7B/View-Payments.png)

![screenshot](https://i.postimg.cc/wjV8DrrW/Cancelled-Flight.png)
