**Tummoc**

Tummoc Metro-Bus Route Finder is an android application that helps users find the best routes
between different places in a city. The application is designed using the Model-View-ViewModel (
MVVM) architecture and follows the Single Activity principle. The application has two fragments, one
for the home screen and the other for the map view.

## Features

- Search for routes between two places
- Select between metro and bus routes
- View the selected route on a map
- Save the searched route for later use

## Architecture

The application follows the MVVM architecture, which separates the application into three parts:
Model, View, and ViewModel. The Model represents the data and the business logic, while the View is
responsible for the user interface. The ViewModel acts as an intermediary between the Model and the
View, providing data to the View and handling user input.

The Single Activity principle is used in the application, where all the fragments are displayed
within a single activity. This helps to reduce the number of activities in the application, making
it more manageable and easier to maintain.

## Screenshots

<img src="https://user-images.githubusercontent.com/84988691/230418123-7e43f63e-cdb8-4f47-bcdd-8e2dc935c86f.png" width="250" height="450">
## Home Screen
The Home Screen shows two search fields for the source and destination, along with a tab to select
between metro and bus routes. Below the tab, there is a recycler view that displays the routes
between the source and destination.


<img src="https://user-images.githubusercontent.com/84988691/230418101-6ad6e3d7-5eff-4ad2-8bdb-211b962cd45d.png" width="250" height="450">

## MapView 
The Map View displays the selected route on a map. The top half of the screen shows the map, while
the bottom half displays the route.

## Libraries and Tools Used

- ViewModel
- LiveData
- Retrofit
- Google Maps API
- RecyclerView
- Material Design

## Installation

Clone the repository and open the project in Android Studio. Run the application on an emulator or a
physical device.
