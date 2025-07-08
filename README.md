
# 🏋️‍♀️ Fitness Tracker Backend

---

## 🎯 Core Features Implementation

### 1. User Management

- Registration with validation  
- Login with session management  
- User listing (admin feature)  

### 2. Fitness Tracking

- Workout logging with calories burned  
- Meal logging with calorie consumption  
- Goal setting and progress tracking  

### 3. Analytics

- Weekly progress reports  
- Daily breakdowns  
- Calorie balance calculations  
- Exercise statistics  

### 4. Data Persistence

- H2 in-memory database  
- JPA/Hibernate ORM  
- Automatic table creation  
- Foreign key relationships  

---

This architecture provides a clean, scalable, and maintainable fitness tracking application with clear separation of concerns and RESTful API design.

---

## 📁 Project Structure Overview

```
Fitness_tracker/
├── pom.xml                                    # Maven configuration
├── src/main/resources/
│   └── application.properties                # Database & server config
└── src/main/java/com/fitness/
    ├── FitnessTrackerApplication.java        # Main Spring Boot app
    ├── entity/                               # Database models
    ├── repository/                           # Data access layer
    ├── dto/                                  # Data transfer objects
    ├── service/                              # Business logic
    └── controller/                           # REST API endpoints
```

---

## 🛠️ 1. Configuration Layer

### `pom.xml` - Project Dependencies

- Spring Boot 3.2.0 - Main framework
- Spring Data JPA - Database operations
- H2 Database - In-memory database
- Spring Validation - Input validation
- Java 21 - Programming language

### `application.properties` - Application Settings

- H2 Database configuration (in-memory)
- JPA/Hibernate settings
- Server port: `8080`
- H2 Console enabled for development

---

## 🧾️ 2. Entity Layer (Database Models)

### `User.java` - User Management

- Fields: `id`, `username`, `password`, `email`, `createdAt`
- Relationships: One-to-Many with `Workout`, `Meal`, `Goal`
- Validation: `@NotBlank`, `@Email` constraints

### `Workout.java` - Exercise Tracking

- Fields: `id`, `type`, `description`, `duration`, `caloriesBurned`, `workoutDate`
- Relationships: Many-to-One with `User`
- Purpose: Track workout sessions

### `Meal.java` - Nutrition Tracking

- Fields: `id`, `name`, `description`, `calories`, `mealType`, `mealDate`
- Relationships: Many-to-One with `User`
- Purpose: Track food consumption

### `Goal.java` - Fitness Goals

- Fields: `id`, `title`, `description`, `goalType`, `targetValue`, `currentValue`, `unit`, `completed`
- Relationships: Many-to-One with `User`
- Purpose: Set and track fitness objectives

---

## 📊 3. Repository Layer (Data Access)

### `UserRepository.java`

- Methods: `findByUsername`, `findByEmail`, `existsByUsername`, `existsByEmail`
- Purpose: User authentication and validation

### `WorkoutRepository.java`

- Methods: `findByUserId`, `sumDurationByUserIdAndDateRange`, `sumCaloriesBurnedByUserIdAndDateRange`
- Purpose: Workout data retrieval and analytics

### `MealRepository.java`

- Methods: `findByUserId`, `sumCaloriesByUserIdAndDateRange`
- Purpose: Meal data retrieval and calorie tracking

### `GoalRepository.java`

- Methods: `findByUserId`, `findByUserIdAndCompleted`
- Purpose: Goal management and progress tracking

---

## 📦 4. DTO Layer (Data Transfer Objects)

### `UserDTO.java`

- Purpose: User registration/login requests
- Validation: Username, password, email validation

### `WorkoutDTO.java`

- Purpose: Workout creation requests
- Validation: Type, description, duration validation

### `GoalDTO.java`

- Purpose: Goal creation requests
- Validation: Title, description, target value validation

### `WeeklyAnalyticsDTO.java`

- Purpose: Weekly progress response
- Contains: Daily summaries, totals, statistics

---

## 🧠 5. Service Layer (Business Logic)

### `UserService.java` - User Management

- `registerUser()` - Create new user account  
- `loginUser()` - Authenticate and generate session token  
- `getUserFromSession()` - Validate session token  
- `logoutUser()` - End user session  
- `getAllUsers()` - Retrieve all registered users  

### `WorkoutService.java` - Workout Management

- `createWorkout()` - Log new workout session  
- `getUserWorkouts()` - Get user's workout history  
- `getTotalWorkoutMinutes()` - Calculate total exercise time  
- `getTotalCaloriesBurned()` - Calculate total calories burned  

### `MealService.java` - Nutrition Management

- `createMeal()` - Log new meal  
- `getUserMeals()` - Get user's meal history  
- `getTotalCaloriesConsumed()` - Calculate total calories consumed  

### `GoalService.java` - Goal Management

- `createGoal()` - Set new fitness goal  
- `getUserGoals()` - Get user's goals  
- `updateGoalProgress()` - Update goal progress and check completion  

### `AnalyticsService.java` - Progress Analytics

- `getWeeklyAnalytics()` - Generate weekly progress report  
- `createDailySummaries()` - Create day-by-day breakdown  
- Purpose: Calculate totals, net calories, workout/meal counts  

---

## 🌐 6. Controller Layer (REST API)

### `UserController.java` - User Endpoints

- `POST /api/users/register` - User registration  
- `POST /api/users/login` - User authentication  
- `POST /api/users/logout` - User logout  
- `GET /api/users` - Get all registered users  

### `WorkoutController.java` - Workout Endpoints

- `POST /api/workouts` - Create workout log  
- `GET /api/workouts` - Get user's workouts  

### `AnalyticsController.java` - Analytics Endpoints

- `GET /api/analytics/weekly` - Get weekly progress analytics  

---

## 🔄 Application Flow (Step-by-Step)

### 1. Application Startup

```
FitnessTrackerApplication.java → Spring Boot starts → Database initialization → Controllers registered
```

### 2. User Registration Flow

```
POST /api/users/register → UserController → UserService.registerUser() → UserRepository.save() → Database
```

### 3. User Login Flow

```
POST /api/users/login → UserController → UserService.loginUser() → UserRepository.findByUsername() → Session token generated
```

### 4. Workout Creation Flow

```
POST /api/workouts → WorkoutController → WorkoutService.createWorkout() → WorkoutRepository.save() → Database
```

### 5. Analytics Generation Flow

```
GET /api/analytics/weekly → AnalyticsController → AnalyticsService.getWeeklyAnalytics() → 
WorkoutService + MealService → Calculate totals → Return WeeklyAnalyticsDTO
```

---

## 🔐 Security & Session Management

### Current Implementation (Simple)

- In-memory session storage (`Map<String, Long>`)
- Session tokens generated on login
- Authorization header required for protected endpoints
- No password hashing (for simplicity)

### Production Considerations

- Use **JWT tokens** for stateless authentication
- Use **BCrypt** for password hashing
- Use **Database sessions** instead of in-memory
- Implement **Role-based access control**

---

## 📈 Data Flow Architecture

```
Client Request → Controller → Service → Repository → Database
                ↓
            Response ← DTO ← Service ← Repository ← Database
```

### Key Design Patterns Used

- **MVC Pattern** - Separation of concerns  
- **Repository Pattern** - Data access abstraction  
- **DTO Pattern** - Clean request/response model  
- **Service Layer** - Business logic encapsulation  
- **Dependency Injection** - Powered by Spring IoC container  

