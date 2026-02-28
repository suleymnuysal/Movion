# 🎬 Movion
Movion is a modern Android application allows users to watch movie trailers, explore details, and create their own watchlist.


## 📱 Screenshots
<img width="975" height="740" alt="movion_1" src="https://github.com/user-attachments/assets/3ec19abf-a956-47f4-9f2e-c8640d660855" />
<img width="968" height="740" alt="movion_2" src="https://github.com/user-attachments/assets/1bc1daac-b543-48d3-a8c1-12490dbf3ec7" />


## 🚀 Features

- 🎥 Browse currently released movies and TV series  
- 🔥 Discover popular content by categories (Action, Thriller, Drama, etc.)  
- 🆕 Stay informed about upcoming movies and TV series  
- 📄 Detailed content screen with overview, genres, ratings and more  
- 👥 View cast information for each movie/series  
- 🔗 Get related content recommendations  
- ▶️ Watch official trailers directly inside the app  
- ⭐ Save content to a personal watchlist stored locally
- 💾 Offline watchlist support with local database  


## 🛠 Tech Stack

### 🏗 Architecture & Design

- MVVM (Model–View–ViewModel) architecture  
- Model-Based Clean Architecture (Data / Domain / Presentation separation)  
- Feature-based multi-module structure  
- Clear separation of concerns across layers  
- Repository pattern for data abstraction  
- Dependency Injection with Hilt for loose coupling  
- Scalable and maintainable codebase 

### 🌐 Networking

- Retrofit
- REST API integration

### 💾 Local Storage

- Room Database

### 🔄 Asynchronous Programming

- Kotlin Coroutines
- Flow 

### 🎨 UI

- Modern Android UI practices
- XML-based layouts 

## ⚙️ Setup

1. Create an account from TMDB and get your API key
2. Add your API key in Constants.kt: API_KEY=YOUR_KEY_HERE 
3. Sync Gradle and build the project  

API keys are intentionally excluded from version control for security reasons.

## Note
This product uses the TMDB API but is not endorsed or certified by TMDB.
