# UserGeneratorChallenge

Android application that pulls users from the RandomUser API (https://randomuser.me/) and displays them as a list. Tap a user to view additional information.

## Features
- MVVM architecture
- Jetpack Navigation
- Kotlin Flow, Coroutines
- Network calls with Retrofit
- Manual DI
- Unit testing with MockK

## Future Improvements / For Scale
- Features
  - Pull-to-refresh for a new set of users
  - More advanced error handling (spinners, network unavailable, etc)
  - Buttons for tap-to-email, tap-to-call (User Details Screen)
  - More advanced visual design / UX
  - More advanced animations
  - Caching in repository layer for a better offline experience
- DI with Hilt (Dagger)
- Build configs/variants in build.gradle for hypothetical production/debug/staging 
- App obfuscation (ProGuard)
- Further unit and UI testing
- CI integrations
