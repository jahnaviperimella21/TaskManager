========================================================================
                      TEAM TASK MANAGER PROJECT
========================================================================

A full-stack, role-based Task Management application built with 
Java Spring Boot and Angular.

------------------------------------------------------------------------
1. TECHNOLOGY STACK
------------------------------------------------------------------------
- Backend: Java 17, Spring Boot 3.2.5, Spring Security, JWT, JPA/Hibernate
- Frontend: Angular 17+ (Standalone Components), RxJS, Tailwind/Vanilla CSS
- Database: H2 (In-memory for development), PostgreSQL (for production)
- Infrastructure: Docker, Railway (Deployment Ready)

------------------------------------------------------------------------
2. CORE FEATURES
------------------------------------------------------------------------
* ROLE-BASED ACCESS CONTROL (RBAC):
  - MANAGER: Can create projects, create/assign tasks, and view progress.
  - DEVELOPER: Can view assigned tasks, update status, and add comments.

* TASK MANAGEMENT:
  - Create and track tasks with Priority (Low, Medium, High).
  - Deadline tracking.
  - Kanban-style status updates (TODO -> IN_PROGRESS -> DONE).
  - Commenting system for collaboration.

* SECURITY:
  - Secure login/registration with JWT (JSON Web Tokens).
  - Protected API endpoints.

------------------------------------------------------------------------
3. SETUP & INSTALLATION
------------------------------------------------------------------------

BACKEND SETUP:
1. Navigate to: /Backend/TaskManager
2. Run command: mvn spring-boot:run
3. API will be live at: http://localhost:5001

FRONTEND SETUP:
1. Navigate to: /Frontend
2. Run command: npm install
3. Run command: npm start
4. App will be live at: http://localhost:4200

------------------------------------------------------------------------
4. DEFAULT CREDENTIALS
------------------------------------------------------------------------
For initial testing, a Manager account is seeded automatically:
- Email: manager@gmail.com
- Password: Manager@123

New users can register from the home page (defaults to Developer role).

------------------------------------------------------------------------
5. DEPLOYMENT (RAILWAY)
------------------------------------------------------------------------
This project is split into two repositories for easier deployment:
- Backend: Includes Dockerfile and railway.json
- Frontend: Includes Dockerfile and Nginx configuration

Environment variables needed for production:
- DATABASE_URL: (PostgreSQL connection string)
- JWT_SECRET: (A long secure string)
- PORT: (Auto-assigned by Railway)

========================================================================
Generated on: 2026-05-07
========================================================================
