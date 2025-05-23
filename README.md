# Coronavirus Tracker Application

This is a Spring Boot web application for tracking COVID-19 data. It fetches, stores, and displays global data for confirmed cases, deaths, and recoveries.

## Features

*   Fetches COVID-19 data (confirmed cases, deaths, recovered cases) from the CSSEGISandData repository on GitHub.
*   Parses CSV data to extract relevant information.
*   Stores the processed data in a relational database.
*   Displays global statistics for confirmed cases, deaths, and recovered cases.
*   Shows the change in numbers from the previous day.
*   Provides separate views for each data category (confirmed, deaths, recovered).
*   Includes user registration and an admin panel for application management (e.g., triggering data updates).

## Technologies Used

*   **Backend:**
    *   Java (Version 11)
    *   Spring Boot
        *   Spring Web
        *   Spring Data JPA
        *   Spring Security
    *   Apache Commons CSV (for parsing data)
*   **Frontend:**
    *   Thymeleaf (for server-side templating)
*   **Database:**
    *   MySQL (or any compatible SQL database)
*   **Build Tool:**
    *   Apache Maven

## Getting Started

### Prerequisites

*   Java Development Kit (JDK) 11 or later.
*   Apache Maven.
*   A running MySQL server instance.

### Setup and Run

1.  **Clone the repository:**
    ```bash
    git clone <repository_url>
    cd <repository_directory>
    ```
2.  **Database Configuration:**
    *   Create a MySQL database (e.g., `coronavirus_tracker`).
    *   Update the database connection properties in `src/main/resources/application.properties`:
        ```properties
        spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
        spring.datasource.username=your_mysql_username
        spring.datasource.password=your_mysql_password
        spring.jpa.hibernate.ddl-auto=update
        ```
        (Note: `spring.jpa.hibernate.ddl-auto=update` will help create/update tables based on entities, but for a first run, you might need to ensure the database exists or use `create` if you want Hibernate to create it.)

3.  **Build the project:**
    ```bash
    mvn clean install
    ```
4.  **Run the application:**
    ```bash
    mvn spring-boot:run
    ```
    The application should be accessible at `http://localhost:8080`.

*(Note: The application fetches data from external URLs upon startup or via an admin trigger. Ensure you have an internet connection.)*

## Future Enhancements

*   **Data Visualization:** Integrate charts (e.g., using Chart.js or a similar library) to visualize trends.
*   **Map Integration:** Display data geographically on a world map.
*   **User-Specific Dashboards:** Allow users to customize views or track specific countries.
*   **More Granular Data:** Explore options for fetching and displaying more detailed data (e.g., regional data within countries if available).
*   **Enhanced Error Handling:** Improve error handling for data fetching and processing.
*   **API Endpoints:** Develop RESTful APIs for programmatic access to the data.

## Data Sources

This application uses COVID-19 data generously provided by the Center for Systems Science and Engineering (CSSE) at Johns Hopkins University.

The data is sourced from their GitHub repository:
[https://github.com/CSSEGISandData/COVID-19](https://github.com/CSSEGISandData/COVID-19)

We are grateful for their efforts in collecting and sharing this vital information.
