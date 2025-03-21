## API Endpoints

| Method | Endpoint        | Description                     | Example Request Body |
|--------|---------------|---------------------------------|----------------------|
| GET    | api/dogs         | Retrieve all dogs              | N/A                  |
| GET    | api/dogs/{id}    | Retrieve a specific dog by ID  | N/A                  |
| POST   | api/dogs         | Add a new dog                  | `{ "name": "Buddy", "breedType": "Poodle", "color": "red", "age": 3 }` |
| PUT    | api/dogs/{id}    | Update a dog                   | `{ "name": "Max", "breedType": "Goldendoodle", "color": "brown" "age": 4 }` |
| DELETE | api/dogs/{id}    | Delete a dog                   | N/A                  |
