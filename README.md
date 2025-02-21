# FinanceApp

FinanceApp is an Android application developed to assist users in managing their personal finances. With an intuitive interface and essential features, the app allows users to track income, expenses, financial goals, and visualize expense charts by category.

## Table of Contents

- [Screenshots](#-screenshots)
- [Features](#-features)
- [Technologies Used](#-technologies-used)
- [Project Architecture](#-project-architecture)
- [Setup and Installation](#-setup-and-installation)
- [How to Use](#-how-to-use)
- [Database](#-database)
- [Challenges Faced](#-challenges-faced)
- [Possible Future Improvements](#-possible-future-improvements)
- [Contributing](#-contributing)
- [License](#-license)
- [Contact](#-contact)
- [Acknowledgments](#-acknowledgments)

## Screenshots

<!-- Add images here to illustrate the main screens of the app -->
<!-- Example: -->
<p align="center">
  <img src="screenshots/login.png" alt="Login Screen" width="200">
  <img src="screenshots/main.png" alt="Main Screen" width="200">
  <img src="screenshots/add_transaction.png" alt="Add Transaction" width="200">
  <img src="screenshots/pie_chart.png" alt="Pie Chart" width="200">
</p>

## 📝 Features

- **User Authentication:**
  - Register new users with name, email, and password.
  - Login for existing users.
  - Session management using SharedPreferences.

- **Transaction Management:**
  - Add new financial transactions (income and expenses).
  - Edit and delete existing transactions.
  - Sort transactions by type, category, and date.
  - Add descriptions for transactions.

- **Financial Goal Setting:**
  - Set a personal financial goal.
  - Track progress towards the set goal.
  - Store the goal using SharedPreferences.

- **Transaction Listing:**
  - View all recorded transactions in an organized list.
  - Access details for each transaction.
  - Intuitive navigation between screens.

- **Graphical Visualization:**
  - Display a pie chart of expenses by category.
  - Visual analysis of spending habits.

- **Export to PDF:**
  - Generate a PDF file of all recorded transactions.
  - Share or store the financial report.

- **Intuitive Navigation:**
  - "Back" button on secondary activities to improve usability.
  - Consistent navigation flow between screens.

## Technologies Used

- **Programming Language:** Kotlin
- **Platform:** Android SDK
- **Local Database:** SQLite
- **User Interface:** XML Layouts, native Android Views
- **External Libraries:**
  - [MPAndroidChart](https://github.com/PhilJay/MPAndroidChart) for charts
- **Simple Data Persistence:** SharedPreferences
- **Dependency Management:** Gradle with Kotlin DSL

## Project Architecture

The project follows an organized architecture to facilitate maintenance and scalability.

- **MVC (Model-View-Controller):**
  - **Models:** Represent the app’s data, such as `User` and `Transaction`.
  - **Views:** XML layouts that define the user interface.
  - **Controllers:** Activities that contain business logic and user interactions.

- **Packages:**
  - `activities`: Contains all app activities.
  - `adapters`: Custom adapters for lists.
  - `models`: Classes representing data models.
  - `database`: `DatabaseHelper` class for database management.

## Setup and Installation

### Prerequisites

- **Android Studio Flamingo** or higher.
- **JDK 8** or higher.
- **Gradle 8.0** or higher.
- Android device or emulator with **API Level 21** or higher.

### Setup Steps

1. **Clone the Repository:**

   ```bash
   git clone https://github.com/your-username/FinanceApp.git

