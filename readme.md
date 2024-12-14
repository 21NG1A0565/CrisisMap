
# **CrisisMap**

CrisisMap is a web application designed to streamline crisis management by enabling efficient communication and collaboration between users, volunteers, government officials, NGOs, and administrators. The platform allows for real-time crisis reporting, resource management, task assignment, and notifications.

---

## **Features**

### General Users
- **Signup and Signin**:
  - Securely register and log in without Spring Security.
- **Report Crises**:
  - Submit crisis reports, including location, description, and date.
- **Emergency Reporting**:
  - Report urgent emergencies and view status updates.
- **View Submitted Reports**:
  - Track the status of your crisis reports and emergencies.

### Volunteers
- **Manage Emergencies**:
  - Update the status of emergencies (`Pending`, `In Progress`, `Resolved`).
- **Chat System**:
  - Communicate with other volunteers using WebSocket chat.
- **View Chat History**:
  - Access past conversations stored in the database.

### NGOs
- **Manage Tasks**:
  - View and update assigned tasks.
- **Request Resources**:
  - Request food, water, medicine, and other resources for crises.
- **View Notifications**:
  - Receive updates from government officials or admins.

### Government Officials
- **Resource Management**:
  - Add, edit, and delete available resources.
- **Task Assignment**:
  - Assign specific tasks to NGOs for efficient crisis resolution.

### Administrators
- **User Management**:
  - View, edit, and delete registered users.
  - Change user roles (`user`, `volunteer`, `ngo`, `government`, `admin`).

---

## **Tech Stack**

### Backend
- **Java Spring Boot**: 
  - For building the RESTful APIs and managing business logic.
- **Hibernate & JPA**: 
  - For ORM and database management.
- **MySQL**:
  - Relational database to store application data.

### Frontend
- **HTML5, CSS3, JavaScript**:
  - For building the UI.
- **Bootstrap 5**:
  - For responsive design and styling.

### Notifications
- **ZeptoMail**:
  - For email notifications.

### Real-Time Communication
- **WebSocket**:
  - For real-time volunteer chat functionality.

---

## **Setup and Installation**

### Prerequisites
- **Java 17** or later
- **Maven 3.8** or later
- **MySQL** installed and running
- **Node.js** (optional, for frontend builds)

### Steps
1. **Clone the repository**:
   ```bash
   git clone https://github.com/your-username/CrisisMap.git
   cd CrisisMap
   ```

2. **Configure Database**:
   - Create a database named `crisismap` in MySQL.
   - Update the `application.properties` file with your database credentials:
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/crisismap
     spring.datasource.username=your-username
     spring.datasource.password=your-password
     ```

3. **Install Dependencies**:
   ```bash
   mvn clean install
   ```

4. **Run the Application**:
   ```bash
   mvn spring-boot:run
   ```

5. **Access the Application**:
   - Open your browser and go to: [http://localhost:8094](http://localhost:8094)


---

## **API Endpoints**

### General Users
| Method | Endpoint                      | Description                   |
|--------|-------------------------------|-------------------------------|
| POST   | `/api/reports`                | Submit a crisis report        |
| GET    | `/api/myreports`              | View user-submitted reports   |

### Volunteers
| Method | Endpoint                      | Description                   |
|--------|-------------------------------|-------------------------------|
| POST   | `/api/emergencies/update`     | Update emergency status       |
| GET    | `/api/chat/history`           | Fetch chat history            |

### NGOs
| Method | Endpoint                      | Description                   |
|--------|-------------------------------|-------------------------------|
| POST   | `/api/tasks/update`           | Update assigned task status   |
| POST   | `/api/resources/request`      | Request resources             |

### Government
| Method | Endpoint                      | Description                   |
|--------|-------------------------------|-------------------------------|
| POST   | `/api/resources/add`          | Add a new resource            |
| POST   | `/api/tasks/assign`           | Assign tasks to NGOs          |

### Admin
| Method | Endpoint                      | Description                   |
|--------|-------------------------------|-------------------------------|
| POST   | `/admin/users/updateRole`     | Update user roles             |
| POST   | `/admin/users/delete`         | Delete a user                 |

---

## **Database Schema**

### Tables
- **Users**:
  - `id`, `username`, `email`, `password`, `role`
- **Reports**:
  - `id`, `title`, `location`, `description`, `status`, `remarks`, `submittedBy`
- **Emergencies**:
  - `id`, `title`, `location`, `description`, `latitude`, `longitude`, `status`, `submittedBy`
- **Notifications**:
  - `id`, `title`, `message`, `postedBy`, `postedTo`, `postedType`

---

## **Contributing**

1. Fork the repository.
2. Create a feature branch:
   ```bash
   git checkout -b feature-name
   ```
3. Commit your changes:
   ```bash
   git commit -m "Add feature"
   ```
4. Push to the branch:
   ```bash
   git push origin feature-name
   ```
5. Open a Pull Request.

---

## **License**

This project is licensed under the MIT License.

"# CrisisMap" 
"# CrisisMap" 
