# CheckMate: Task Management Application

## Table of Contents
- [Introduction](#introduction)
  - [Inspiration](#inspiration)
  - [Objective](#objective)
- [Methodology](#methodology)
  - [Development Approach](#development-approach)
  - [Tools and Technologies](#tools-and-technologies)
- [Implementation](#implementation)
  - [Project Structure](#project-structure)
  - [Class Overview](#class-overview)
- [Features](#features)
- [Results](#results)
- [Conclusion](#conclusion)
- [Repository Link](#repository-link)

---

## Introduction
### Inspiration
Over the past two years, I have used various task management applications that significantly improved my productivity and organizational skills. Inspired by these experiences, I decided to create my own task management application, which I plan to extend to Android, iOS, and web-based platforms in the future. This project is the first step towards achieving that goal.

### Objective
CheckMate is a Java-based task management system that allows users to perform task operations such as adding, editing, removing, and prioritizing tasks. It also incorporates user authentication to ensure secure access and task modification.

---

## Methodology
### Development Approach
I followed the Agile methodology due to its flexibility. The project was divided into several phases: researching user needs, planning and modeling the system, implementing features, and conducting testing.

### Tools and Technologies
- **Programming Language:** Java  
- **Development Platform:** Visual Studio Code  
- **Libraries:** `java.util` (Collection Framework, Regex), `java.io` (File Operations)  
- **Testing:** Command Prompt/Terminal  

---

## Implementation
### Project Structure
The project consists of five primary classes:
```
CheckMate
│── src/
│   ├── Registration.java
│   ├── Login.java
│   ├── Priority.java (enum)
│   ├── Task.java
│   ├── CheckMate.java (Main class)
│── resources/
│   ├── [User task files]
│── README.md
```

### Class Overview
1. **Registration Class**
   - Handles new user registrations.
   - Ensures unique usernames and valid password formats.
   - Saves user credentials to a file.

2. **Login Class**
   - Authenticates users and ensures access security.
   - Creates user-specific task storage files.

3. **Priority Enum**
   - Defines task priority levels: High, Medium, Low, and No Priority.
   - Associates each priority with a storage file.

4. **Task Class**
   - Manages tasks (adding, editing, removing, marking as completed, etc.).
   - Uses a TreeMap to store tasks by priority.
   - Ensures persistence through file storage.

5. **CheckMate Class (Main)**
   - Provides the main interface for user interaction.
   - Handles task operations and user authentication.

---

## Features
- **User Authentication:** Registration and login with secure credential storage.
- **Task Management:** Add, remove, edit, and view tasks.
- **Priority-Based Organization:** Tasks categorized into different priority levels.
- **Persistence:** Data is stored in files to maintain tasks across sessions.
- **Command-Line Interface:** Simple and efficient text-based interaction.

---

## Results
### User Operations
- **New Registration:** Ensures unique usernames and secure password validation.
- **User Login:** Validates credentials and loads user-specific tasks.
- **Task Operations:**
  - Adding, editing, and removing tasks.
  - Changing task priorities.
  - Viewing prioritized and completed tasks.
  - Logging out securely.

---

## Conclusion
CheckMate is a command-line task management application that I built using Java. By leveraging Object-Oriented Programming principles, I created a system that efficiently manages tasks and ensures secure user authentication. This project serves as a solid foundation for future enhancements, such as a graphical interface and mobile app development.

---


