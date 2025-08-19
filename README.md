<<<<<<< HEAD
=======
# 🎮 Gamepedia — Kotlin Multiplatform Project

![Kotlin](https://img.shields.io/badge/Kotlin-1.x-blue) ![KMP](https://img.shields.io/badge/Kotlin%20Multiplatform-KMP-success) ![Compose](https://img.shields.io/badge/Compose-Multiplatform-brightgreen) ![SQLDelight](https://img.shields.io/badge/SQLDelight-db-orange) ![Ktor](https://img.shields.io/badge/Ktor-client-informational) ![License](https://img.shields.io/badge/License-MIT-lightgrey)

Welcome to **Gamepedia** — a Kotlin Multiplatform (KMP) project that ships **Android**, **iOS**, and **Desktop** apps using **Jetpack Compose Multiplatform** 🚀; the codebase follows **Clean Architecture** and **modularization** to keep features independent, testable, and scalable across platforms.

---

## 📂 Project Structure

```text
Gamepedia/
├── composeApp/       # 📱 Main application module (entry point)
├── core_database/    # 🗄️ Database layer (SQLDelight)
├── core_network/     # 🌐 Networking layer (Ktor + Serialization)
├── game/             # 🎮 Game feature module (data, domain, ui)
├── favorite/         # ⭐ Favorite feature module (data, domain, ui)
├── search/           # 🔍 Search feature module (data, domain, ui)
├── common/           # 🛠️ Shared utilities & common code
├── iosApp/           # 🍏 iOS target app
└── ...               # 🚀 More coming soon!
```

---

## ⚙️ Core Modules

### 📡 core_network
- HTTP client with **Ktor**
- JSON parsing via **kotlinx.serialization**
- Interceptors and centralized logging with **ktor-client-logging**
- Multiplatform engine configuration (Android, iOS, Desktop)

### 🗄️ core_database
- Local storage powered by **SQLDelight**
- Shared schema for all targets (Android/iOS/Desktop)
- Provides `AppDatabase` for caching and offline support

### 🔗 common
- Shared business logic, constants, and utility functions
- Foundation for other modules with minimal dependencies

---

## 🎯 Feature Modules

All features follow three clean layers: **data**, **domain**, **ui**.

### 🔎 Search
- Search games with pagination-ready data sources
- Integrates with `core_network` and `core_database`

### ⭐ Favorite
- Manage user favorites
- Persists locally via SQLDelight

### 🎮 Game
- Game list and detail screens
- Fetches from API with local caching and mapping

---

## 🛠️ Tech Stack

- **Kotlin Multiplatform (KMP)**
- **Compose Multiplatform** (Android, iOS, Desktop UI)
- **SQLDelight** (Database & caching)
- **Ktor Client** (Networking)
- **Koin** (Dependency Injection)
- **Napier** (Logging)

---

Key goals: **separation of concerns**, **testability**, **independent feature delivery**, **clear dependencies**.

---


## 📦 Dependency Injection (Koin)

- DI modules per feature (`dataModule`, `domainModule`, `uiModule`).
- Core DI provides network client, database, and common utilities.

---

## 📐 Code Style

- **Kotlin DSL** for Gradle.
- Detekt/Ktlint (optional) for static checks.
- Use **sealed interfaces** and **data classes** in domain.
- Keep **platform code** behind expect/actual when needed.

---

## 🌍 Platforms

- 🤖 Android (Jetpack Compose + AndroidX)
- 🍎 iOS (Compose Multiplatform + Swift interop)
- 💻 Desktop (Windows, macOS, Linux)

---

>>>>>>> fe1bce7f2c69baad8fb87f793333e1a5a9565aff
This is a Kotlin Multiplatform project targeting Android, iOS, Desktop (JVM).

* [/composeApp](./composeApp/src) is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - [commonMain](./composeApp/src/commonMain/kotlin) is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    the [iosMain](./composeApp/src/iosMain/kotlin) folder would be the right place for such calls.
    Similarly, if you want to edit the Desktop (JVM) specific part, the [jvmMain](./composeApp/src/jvmMain/kotlin)
    folder is the appropriate location.

* [/iosApp](./iosApp/iosApp) contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform,
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.


<<<<<<< HEAD
Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…
=======
Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…
>>>>>>> fe1bce7f2c69baad8fb87f793333e1a5a9565aff
