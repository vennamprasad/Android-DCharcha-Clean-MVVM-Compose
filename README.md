# DCharcha

<img src="/project.svg" alt="DCharcha"/>

🚀 **DCharcha** is a modern Android application scaffold built with **Kotlin, Jetpack Compose, and Clean Architecture**.  
It is designed to be modular, scalable, and follow Android best practices while making it easy to plug in real APIs.  

Currently, the project integrates with [JSONPlaceholder](https://jsonplaceholder.typicode.com/) as a sample backend for demonstrating:
- **Retrofit + Kotlinx Serialization** network layer
- **Room** local database caching
- **Paging 3 + RemoteMediator** for infinite scrolling
- **Multi-module clean architecture** with `domain`, `data`, `core`, and `feature` modules
- **Hilt DI** setup across modules
- **Material 3** theme with brand colors and typography
- **Dynamic UI scaling** for mobile, foldables, and tablets

---

## 🔑 Key Features

- **Clean Architecture + MVVM**
- **Multi-module setup** (feature isolation, core libraries, data/domain separation)
- **Kotlin Coroutines & Flows** everywhere
- **Hilt for Dependency Injection**
- **Jetpack Compose Navigation** (per-feature nav graph with dynamic includes)
- **Room + Paging 3** with proper caching
- **Material 3 Theme**
  - Brand colors & typography
  - Gradient wallpaper backgrounds
  - Adaptive sizes for phones, foldables, tablets
- **Lint & CI Ready** (Detekt baseline included)

---

## 📱 Sample API Integration (JSONPlaceholder)

The `feature/home` module integrates with [JSONPlaceholder](https://jsonplaceholder.typicode.com/) using `PostsApi`.

- **RemoteMediator** fetches posts with `_page` & `_limit` params
- **Room** caches posts locally
- **PagingData Flow** exposed to `PostsViewModel`
- **UI** renders a scrollable list with retry/load indicators

---

## 🛠 Tech Stack

- **Language**: Kotlin (2.2.x)
- **UI**: Jetpack Compose + Material 3
- **Navigation**: Compose Navigation
- **DI**: Hilt
- **Networking**: Retrofit + Kotlinx Serialization
- **Database**: Room
- **Async**: Coroutines + Flows
- **Paging**: Paging 3 + RemoteMediator
- **Linting**: Detekt

---

## ▶️ Getting Started

1. Clone the repo:
   ```bash
   git clone https://github.com/vennamprasad/DCharcha.git
   cd DCharcha

