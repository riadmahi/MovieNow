# MovieNow 🍿

MovieNow is a Kotlin Compose Multiplatform project that lets you discover and explore movies using TheMovieDB API. This project leverages **KConfig** for centralized configuration management.

## Table of Contents

1. [Features](#features)
2. [Prerequisites](#prerequisites)
3. [Installation and Setup](#installation-and-setup)
4. [Using BuildKConfig](#using-kconfig)
5. [Contributing](#contributing)
6. [License](#license)

## Features

- **Multiplatform:** Target Android, iOS with Kotlin Compose Multiplatform.
- **TheMovieDB Integration:** Retrieve and display popular, top-rated, and other movie categories.
- **Centralized Configuration:** Manage your configuration and API keys with BuildKConfig.
- **Modern UI:** Built with Kotlin Compose for a sleek and responsive interface.

## Prerequisites

- **JDK 11** (or later)
- **Kotlin 2.1+**
- A valid API key from [TheMovieDB](https://developer.themoviedb.org/reference/intro/getting-started)

## Installation and Setup

1. **Clone the Repository**

   ```bash
   git clone https://github.com/riadmahi/MovieNow.git
   cd MovieNow
   ```

2. **Generate Your API Key on TheMovieDB**

   - Visit [TheMovieDB](https://developer.themoviedb.org/reference/intro/getting-started).
   - Sign up or log in.
   - Navigate to the *API* section in your account and generate a new API key.

3. **Place the API Key in `local.properties`**

   In the project root, create (or update) a file named `local.properties` and add the following line:

   ```local.properties
   API_KEY=YOUR_API_KEY_HERE
   ```

4. **Build and Run the Project**

   Use Gradle to build and run the project:

   ```bash
   ./gradlew run
   ```

   For Android, open the project in Android Studio and run the application from there.

## Using BuildKConfig

MovieNow uses [BuildKConfig](https://github.com/yshrsmz/BuildKonfig) to centralize and manage the app configuration. The API key specified in `local.properties` is injected into the project via BuildKConfig plugin during build time.

- Ensure the `local.properties` file contains your API key.
- Verify that the KConfig setup in your `build.gradle.kts` or other configuration files is correctly reading the `API_KEY` property.

## Contributing

Contributions are welcome! To propose improvements or fix bugs:

1. Fork the repository.
2. Create a feature branch:
   ```bash
   git checkout -b feature/your-feature-name
   ```
3. Commit your changes:
   ```bash
   git commit -am 'Add some feature'
   ```
4. Push your branch:
   ```bash
   git push origin feature/your-feature-name
   ```
5. Open a Pull Request.

## License

This project is licensed under the **MIT License**. See the [LICENSE](LICENSE) file for details.
