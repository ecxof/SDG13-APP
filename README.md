<h1 align="center"> SDG-13 Climate Action</h1>

<p align="center">
  <strong>A JavaFX Desktop Application for Climate Change Education & Awareness</strong>
</p>

<p align="center">
  <a href="#about"><img src="https://img.shields.io/badge/UN-SDG%2013-3F7E44?style=for-the-badge&logo=united-nations&logoColor=white" alt="SDG 13"/></a>
  <img src="https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java 21"/>
  <img src="https://img.shields.io/badge/JavaFX-21--ea-007396?style=for-the-badge&logo=java&logoColor=white" alt="JavaFX 21-ea"/>
  <img src="https://img.shields.io/badge/Maven-3.9+-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white" alt="Maven"/>
  <img src="https://img.shields.io/badge/License-MIT-blue?style=for-the-badge" alt="License"/>
</p>

<p align="center">
  <a href="#features">Features</a> •
  <a href="#tech-stack">Tech Stack</a> •
  <a href="#getting-started">Getting Started</a> •
  <a href="#project-structure">Project Structure</a> •
  <a href="#architecture">Architecture</a> •
  <a href="#educational-modules">Educational Modules</a> •
  <a href="#configuration">Configuration</a>
</p>

---

## About

**SDG-13 Climate Action** is a desktop application built with **JavaFX** that serves as an interactive educational platform aligned with the **United Nations Sustainable Development Goal 13 — Climate Action**. The application aims to raise awareness about climate change by providing comprehensive educational content on key topics such as mitigation, adaptation, impact reduction, and early warning systems.

The platform features a role-based system with both **user** and **admin** portals, enabling content management and dynamic information delivery.

> *"Take urgent action to combat climate change and its impacts."*  
> — United Nations SDG 13

---

## Features

<table>
  <tr>
    <td align="center" width="33%">
      <h3> Authentication</h3>
      <p>Secure login system with separate User & Admin access portals</p>
    </td>
    <td align="center" width="33%">
      <h3> 6 Educational Modules</h3>
      <p>Covers Education, Awareness, Human Impact, Adaptation, Impact Reduction & Early Warning</p>
    </td>
    <td align="center" width="33%">
      <h3> Smart Search</h3>
      <p>Real-time search across all climate topics with keyword matching</p>
    </td>
  </tr>
  <tr>
    <td align="center" width="33%">
      <h3> Admin Dashboard</h3>
      <p>Full content management — create, edit & update educational material</p>
    </td>
    <td align="center" width="33%">
      <h3> Feedback System</h3>
      <p>Built-in user feedback form for suggestions and engagement</p>
    </td>
    <td align="center" width="33%">
      <h3> Share & Copy</h3>
      <p>One-click sharing with clipboard copy of SDG 13 UN resource link</p>
    </td>
  </tr>
</table>

---

## Tech Stack

| Component          | Technology                                                                    |
| :----------------- | :---------------------------------------------------------------------------- |
| **Language**        | Java 21                                                                       |
| **UI Framework**    | JavaFX 21-ea+24 (early-access build, set in `pom.xml`)                        |
| **UI Extensions**   | ControlsFX 11.1.2                                                             |
| **Build Tool**      | Apache Maven 3.9+                                                             |
| **Architecture**    | MVC (Model-View-Controller)                                                   |
| **View Layer**      | FXML (20 view files)                                                          |
| **Data Storage**    | File I/O (Text-based persistence)                                             |
| **Testing**         | JUnit 5 (Jupiter 5.9.2) declared in `pom.xml`; no test classes written yet     |

---

## Getting Started

### Prerequisites

Ensure you have the following installed:

-  **Java Development Kit (JDK) 21** or later — [Download](https://adoptium.net/)
-  **Apache Maven 3.9+** — [Download](https://maven.apache.org/download.cgi)
-  **Git** — [Download](https://git-scm.com/)

### Installation

```bash
# 1. Clone the repository
git clone https://github.com/ecxof/SDG13-APP.git
cd SDG13-APP

# 2. Build the project
mvn clean install

# 3. Run the application
mvn clean javafx:run
```

> [!TIP]
> If you're using **IntelliJ IDEA**, simply open the project as a Maven project and run the `Application.java` main class directly.

> [!IMPORTANT]
> Run the application from the **project root**. The six `Page_*_Data.txt` files are
> opened by relative path, so they resolve against the current working directory. If you
> launch from a different directory the pages load but display no content.

### Default Credentials

| Role    | Username | Password |
| :------ | :------- | :------- |
| **User**  | `123`      | `123`      |
| **Admin** | —        | `123`      |

---

## Project Structure

```
SDG13-APP/
├──  pom.xml                          # Maven project configuration
├──  mvnw / mvnw.cmd                  # Maven wrapper scripts
├──  Page_One_Data.txt                 # Education & Awareness content
├──  Page_Two_Data.txt                 # Awareness-Raising content
├──  Page_Three_Data.txt               # Human Impact content
├──  Page_Four_Data.txt                # Adaptation Planning content
├──  Page_Five_Data.txt                # Impact Reduction content
├──  Page_Six_Data.txt                 # Early Warning content
│
└── src/main/
    ├── java/
    │   ├── module-info.java             # Java module descriptor
    │   └── com/example/sdg13ver5/
    │       ├── Application.java         #  Main entry point (JavaFX Application)
    │       ├── Controller.java          #  Primary controller (login, pages, nav)
    │       ├── Controller2.java         #  Search controller (keyword filtering)
    │       ├── Controller3.java         #  Admin navigation controller
    │       └── Controller4.java         #  Admin content editor controller
    │
    └── resources/com/example/sdg13ver5/
        ├── start.fxml                   # Login screen
        ├── mainpage.fxml                # Main dashboard
        ├── page1.fxml — page6.fxml      # Educational content pages
        ├── search.fxml                  # Search interface
        ├── feedbackpage.fxml            # Feedback form
        ├── postfeedback.fxml            # Feedback confirmation
        ├── Copylink.fxml                # Share/copy link dialog
        ├── adminlogin.fxml              # Admin login screen
        ├── adminpage.fxml               # Admin dashboard
        ├── adminpage1.fxml — adminpage6.fxml  # Admin content editors
        ├── styles.css                   # Application stylesheet
        ├── hello-view.fxml              # Unused JavaFX archetype leftover
        └── image/                       # Application assets & icons
```

---

## Architecture

The application follows the **MVC (Model-View-Controller)** design pattern:

```mermaid
graph TD
    A[Application.java<br/>Entry Point] --> B[start.fxml<br/>Login View]
    B --> C{Authentication}
    C -->|User Login| D[mainpage.fxml<br/>Main Dashboard]
    C -->|Admin Login| E[adminpage.fxml<br/>Admin Dashboard]
    
    D --> F[Controller.java]
    F --> G[ Page 1-6<br/>Educational Content]
    F --> H[ Search<br/>Controller2.java]
    F --> I[ Feedback]
    F --> J[ Share Link]
    
    E --> K[Controller3.java<br/>Admin Navigation]
    K --> L[Controller4.java<br/>Content Editor]
    L --> M[ Text Files<br/>Data Persistence]
    M --> G

    style A fill:#3F7E44,color:#fff
    style D fill:#4A90D9,color:#fff
    style E fill:#E8A838,color:#fff
    style M fill:#D9534F,color:#fff
```

### Controllers Overview

| Controller           | Responsibility                                                   |
| :------------------- | :--------------------------------------------------------------- |
| `Controller.java`    | User authentication, page navigation, feedback, sharing          |
| `Controller2.java`   | Search functionality with real-time keyword filtering            |
| `Controller3.java`   | Admin panel navigation between content editor pages              |
| `Controller4.java`   | Admin content management — writes educational data to text files |

---

## Educational Modules

The application covers **6 key targets** of SDG 13:

| #  | Module                  | Description                                                                              |
| :- | :---------------------- | :--------------------------------------------------------------------------------------- |
| 1  | **Improve Education**   | Climate Change Education (CCE) across early childhood, primary & secondary levels         |
| 2  | **Awareness-Raising**   | Strategies for building public awareness on climate issues                                |
| 3  | **Human Impact**        | Human and institutional capacity building for climate action                              |
| 4  | **Adaptation**          | Planning and implementing climate adaptation strategies                                  |
| 5  | **Impact Reduction**    | Reducing the adverse impact of climate-related hazards                                   |
| 6  | **Early Warning**       | Strengthening early warning systems and disaster risk management                          |

---

## Configuration

### Customizing Content

Educational content is stored in plain text files at the project root. Admins can edit
content either through the **Admin Dashboard** in the application or by directly editing
the files below.

> [!NOTE]
> The Admin Dashboard editor caps each page at **300 characters**. Longer text is
> rejected with a "Too long!" message and is not saved. Editing the `.txt` files
> directly bypasses that limit.


```
Page_One_Data.txt     →  Improve Education
Page_Two_Data.txt     →  Awareness-Raising
Page_Three_Data.txt   →  Human Impact
Page_Four_Data.txt    →  Adaptation
Page_Five_Data.txt    →  Impact Reduction
Page_Six_Data.txt     →  Early Warning
```

## Known Limitations

- **Search matches topic keywords, not page content.** `Controller2` filters a fixed
  list of six SDG-13.3 terms; words that appear inside the `Page_*_Data.txt` content are
  not searchable
- **Two search terms do not match their destination page.** `human and institutional
  capacity` opens page 2 (*Awareness-Raising*) and `mitigation planning` opens page 3
  (*Human Impact*). There is no search entry for *Awareness-Raising* and no page for
  mitigation
- **Authentication is a hardcoded demo.** Credentials are compared as literal strings;
  there is no user store, password hashing, or session handling
- **No automated tests.** JUnit 5 is configured in `pom.xml` but no test classes exist

---

## Acknowledgements

-  [United Nations — SDG 13](https://sdgs.un.org/goals/goal13) — Climate Action goal reference
-  [OpenJFX](https://openjfx.io/) — JavaFX open-source framework
-  [ControlsFX](https://controlsfx.github.io/) — Custom JavaFX UI controls
