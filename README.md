# MovieRepoComposeApp
A sample project for Android that shows a list of movies fetched from remote API.


![Movie Product Details Screen](/assets/movie_product_details_screen.jpg)
![Movies Catalog Screen](/assets/movies_catalog_screen.jpg)

## Development patterns, kits, languages & APIs:
- Kotlin, Kotlin coroutines, Kotlin Flows for asynchronous programming.
- Jetpack compose : (UI,State management,Coil,Lottie,Navigation...)
- Hilt for dependency management.
- MVVM design pattern & clean architecture.
- Gradle KTS, BuildSrc , Gradle extension to ease the project dependencies management and upgrades.
- Interface driven development, loose coupling between layers and isolating domain from frameworks and thrid parties.

## Things to consider  :

- Design-system theming & components design for scalability
- Dark mode
- TDD

## Features to improve:
- Populating the UI with more data
- Make the image base url fetching more configurable.


#  Notice : 
### The API Key should be provided once you clone the repository in a file named: 'env.properties' for safety reasons, by default it will provide an empty string to make sure the build doesn't fail and shows error screen when the app is launched.



 

