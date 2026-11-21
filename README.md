# SmartPark – Campus Parking Management System

## Project Overview

SmartPark is a Java-based, console-driven Campus Parking Management System developed using Object-Oriented Programming (OOP) concepts.

The system is designed to manage vehicle parking within a campus by allowing students and staff to register their vehicles, park them in available slots, and retrieve their parking details.

It provides automatic parking slot allocation, vehicle tracking, parking history, and availability monitoring. The system also uses file handling to maintain records even after the program is closed and restarted.

## Features

- **User Registration:** Register students and staff members.
- **Vehicle Registration:** Register one vehicle per user.
- **Vehicle Categories:** Supports two-wheelers and four-wheelers.
- **Automatic Slot Allocation:** Automatically assigns the first available compatible parking slot.
- **Parking Management:** Allows users to park and remove their vehicles.
- **Slot Availability:** Displays available and occupied parking slots.
- **Vehicle Search:** Search for registered vehicles and their parking details.
- **Parking History:** Maintains records of previous parking activities.
- **Dashboard:** Displays an overview of parking information.
- **Data Persistence:** Stores user, vehicle, and parking records using text files.

## Parking Configuration

The system contains a total of **50 parking slots**:

| Vehicle Type | Number of Slots | Slot IDs |
|---|---:|---|
| Two-Wheeler | 30 | T01 – T30 |
| Four-Wheeler | 20 | F01 – F20 |
| **Total** | **50** | |

Parking is free for all registered users.

## Object-Oriented Programming Concepts

The project demonstrates the following OOP concepts:

1. **Encapsulation:** Data members are protected using appropriate access modifiers, with methods controlling access to them.
2. **Inheritance:** Student and Staff classes inherit from the User class. TwoWheeler and FourWheeler classes inherit from the Vehicle class.
3. **Abstraction:** Abstract User and Vehicle classes define common properties and behaviours.
4. **Polymorphism:** Common methods are implemented and used through parent-class references, supporting runtime polymorphism.

## Technologies Used

- **Programming Language:** Java
- **IDE:** Visual Studio Code
- **JDK:** Java Development Kit 21
- **Data Storage:** Text File Handling
- **Collections:** ArrayList
- **Date and Time:** LocalDateTime
- **Input Handling:** Scanner
- **Exception Handling:** Java Exception Handling

## Project Structure

The project consists of the following Java source files:

| File Name | Description |
|---|---|
| Main.java | Main program and menu-driven interface |
| User.java | Abstract base class for users |
| Student.java | Student user implementation |
| Staff.java | Staff user implementation |
| Vehicle.java | Abstract base class for vehicles |
| TwoWheeler.java | Two-wheeler vehicle implementation |
| FourWheeler.java | Four-wheeler vehicle implementation |
| ParkingSlot.java | Manages parking slot information |
| ParkingRecord.java | Stores parking transaction details |
| ParkingManager.java | Handles parking operations and management |
| FileManager.java | Handles file storage and retrieval |
| ConsoleColors.java | Provides console text formatting |

## Data Storage

The application uses text files to preserve information between program executions.

- `users.txt` – Stores registered user details.
- `vehicles.txt` – Stores registered vehicle details.
- `parking_records.txt` – Stores parking transaction records.

These files allow the system to retrieve previously stored information when the application is restarted.

## How to Run the Project

### Prerequisites

- Install Java JDK 21 or a compatible version.
- Install Visual Studio Code.
- Open the SmartPark project folder in VS Code.

### Execution Steps

1. Open the project folder in Visual Studio Code.
2. Open the integrated terminal.
3. Compile the Java files:

   ```bash
   javac *.java
   ```

4. Run the application:

   ```bash
   java Main
   ```

5. The SmartPark main menu will appear in the console.

## Future Enhancements

- Graphical User Interface (GUI).
- Database integration.
- Online parking reservation.
- Payment and billing facilities.
- Real-time parking slot monitoring.

## Project Purpose

The primary objective of SmartPark is to demonstrate the practical implementation of Object-Oriented Programming concepts through a real-world campus parking management application. It combines Java programming, inheritance, abstraction, polymorphism, encapsulation, collections, and file handling in one integrated project.

---

**SmartPark – Campus Parking Management System**

*Developed as an Object-Oriented Programming Lab Project.*
