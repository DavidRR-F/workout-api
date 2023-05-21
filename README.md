# Workout Tracker API

This is the documentation for the Workout Tracker API. The API is built using Java Spring Boot and is designed to provide a way to track workouts, exercises, and sets for individual users.

## Getting Started

To get started with the API, follow these steps:

1. Install Java (version 11 or higher) and Maven (version 3 or higher).
2. Clone the repository and navigate to the project directory.
3. Build the project using `mvn clean install`.
4. Start the server using `mvn spring-boot:run`.

The API will be available at `http://localhost:8080`.

## Postman Collection

You can find the Postman collection for this project [here](https://gist.github.com/DavidRR-F/67c4c89fb8fcfd554fc181381994520b).

To import the Postman collection, click the **Import** button in Postman and provide the URL to the raw JSON file:


## Endpoints

The following endpoints are available:

| Method | URL                                                         | Description                            |
|--------|-------------------------------------------------------------|----------------------------------------|
| GET    | /users                                                      | Get a list of all users                |
| POST   | /users                                                      | Create a user                          |
| GET    | /users/{userId}                                             | Get a user                             |
| PUT    | /users/{userId}                                             | Update a user                          |
| DELETE | /users/{userId}                                             | Delete a user                          |
| GET    | /users/{userId}/workouts                                    | Get a list of all workouts for a user  |
| POST   | /users/{userId}/workouts                                    | Create a new workout for a user        |
| GET    | /users/{userId}/workouts/{workoutId}                        | Get a workout for a user               |
| PUT    | /users/{userId}/workouts/{workoutId}                        | Update a workout for a user            |
| DELETE | /users/{userId}/workouts/{workoutId}                        | Delete a workout for a user            |
| GET    | /users/{userId}/workouts/{workoutId}/exercises              | Get a list of all exercises for a user |
| POST   | /users/{userId}/workouts/{workoutId}/exercises              | Create a new exercises for a user        |
| GET    | /users/{userId}/workouts/{workoutId}/exercises/{exerciseId} | Get a exercises for a user               |
| PUT    | /users/{userId}/workouts/{workoutId}/exercises/{exerciseId} | Update a exercises for a user            |
| DELETE | /users/{userId}/workouts/{workoutId}/exercises/{exerciseId} | Delete a exercises for a user            |

### Examples

#### GET /users/{userId}/workouts

This endpoint retrieves all workouts for the specified user.

**Query Parameters**

None.

**Response**

```
HTTP/1.1 200 OK

[
  {
    "id": 1,
    "name": "Chest Day",
  },
  {
    "id": 2,
    "name": "Leg Day",
  }
]
```

#### POST /users/{userId}/workouts

This endpoint creates a new workout for the specified user.

**Request**

```
POST /api/users/1/workouts

{
  "name": "Back Day"
}
```

**Response**

```
HTTP/1.1 201 Created

{
  "id": 3,
  "name": "Back Day"
}
```

#### GET /users/{userId}/workouts/{workoutId}

This endpoint retrieves all exercises for the specified workout.

**Query Parameters**

None.

**Response**

```
HTTP/1.1 200 OK

[
  {
    "id": 1,
    "name": "Leg Day",
  }
]
```

#### POST /users/{userId}/workouts/{workoutId}/exercises

This endpoint creates a new exercise for the specified workout.

**Request**

```
POST /api/workouts/1/exercises

{
  "name": "Incline Dumbbell Press",
  "sets": 3,
  "reps": 10,
  "weight": 50
}
```

**Response**

```
HTTP/1.1 201 Created

{
  "id": 3,
  "name": "Incline Dumbbell Press",
  "sets": 3,
  "reps": 10,
  "repRangeTop": 0,
  "repRangeBottom": 0,
  "weight": 50,
  "weightIncrease": 0
}
```


#### GET /users/{userId}/workouts/{workoutID}/exercises/{exerciseId}

This endpoint retrieves a specific exercise by ID.

**Query Parameters**

None.

**Response**

```
HTTP/1.1 200 OK

{
  "id": 1,
  "name": "Bench Press",
  "sets": 3,
  "reps": 10,
  "repRangeTop": 0,
  "repRangeBottom": 0,
  "weight": 135,
  "weightIncrease": 5
}
```

#### PUT /users/{userId}/workouts/{workoutId}/exercises/{exerciseId}

This endpoint updates an existing exercise.

**Request**

```
PUT /api/exercises/1

{
  "name": "Bench Press",
  "sets": 3,
  "reps": 12,
  "weight": 140
}
```

**Response**

```
HTTP/1.1 200 OK

{
  "id": 1,
  "name": "Bench Press",
  "sets": 3,
  "reps": 12,
  "repRangeTop": 0,
  "repRangeBottom": 0,
  "weight": 140,
  "weightIncrease": 5
}
```

#### DELETE /users/{userId}/workouts/{workoutId}/exercises/{exerciseId}

This endpoint deletes an existing exercise.

**Response**

```
HTTP/1.1 202 Accepted
```

## Request and Response Formats

The API accepts and returns data in JSON format. Here are some examples of the request and response formats:

### Request

```
POST /api/workouts/1/exercises

{
  "name": "Incline Dumbbell Press",
  "sets": 3,
  "reps": 10,
  "weight": 50
}
```

### Response

```
HTTP/1.1 201 Created

{
  "id": 3,
  "name": "Incline Dumbbell Press",
  "sets": 3,
  "reps": 10,
  "repRangeTop": 0,
  "repRangeBottom": 0,
  "weight": 50,
  "weightIncrease": 0
}
```

## Error Handling

The API returns standard HTTP status codes and error messages in JSON format for any errors that occur. Here are some examples of the error response formats:

### Invalid Request

```
HTTP/1.1 400 Bad Request

{
  "statusCode": 409,
  "details": "User with that email already exists"
}
```

### Resource Not Found

```
HTTP/1.1 404 Not Found

{
  "statusCode": 404,
  "details": "No exercise found with ID 123"
}
```
