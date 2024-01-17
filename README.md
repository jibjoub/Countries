# Countries

Countries is a project to practice CLEAN Android development with focus on the best practices. This project will fetch data from a remote source and will handle persistence in a local database. 

## Tech Stack
- Kotlin
  - Coroutines
  - Flow + Livedata
- Koin for Dependency Injection
- Jetpack Compose for UI building
- Architecture
  - CLEAN architecture
  - MVVM (View - Model - ViewModel)
  - Repository pattern
- Retrofit for consuming the API
- Junit for unit tests

## Current state of the project

The app is fetching all the countries from the https://restcountries.com/ API and displays them in a list. The user can click on the items and will be redirected to a detailed screen about the selected country.

<div align="center">
  <video src="https://private-user-images.githubusercontent.com/46657670/297451389-64d17928-ac5d-4a93-9d3e-6fc4c2cafd29.mp4?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3MDU1MDk4ODgsIm5iZiI6MTcwNTUwOTU4OCwicGF0aCI6Ii80NjY1NzY3MC8yOTc0NTEzODktNjRkMTc5MjgtYWM1ZC00YTkzLTlkM2UtNmZjNGMyY2FmZDI5Lm1wND9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFWQ09EWUxTQTUzUFFLNFpBJTJGMjAyNDAxMTclMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjQwMTE3VDE2Mzk0OFomWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPTIyNDY4NWVhNGNkZDQ5YjZmNGJkMGFmY2VkN2YxMjZkZjUwMjhiMzM0MjllMjUxZWVjYmI3ZTcxYzY0YzM2MGImWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0JmFjdG9yX2lkPTAma2V5X2lkPTAmcmVwb19pZD0wIn0.puO52P03FtbAkVBsh4XgExi_h1nLIRVx9w5sAOKf5N0" width="400" />
</div>



