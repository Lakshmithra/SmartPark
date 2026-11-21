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
3. **Abstraction:** Abstract
