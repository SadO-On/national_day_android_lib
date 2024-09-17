# Watan’s Letters (حروف الوطن)

Watan’s Letters is a **Jetpack Compose-based word finder game** where players must find 3 Saudi words before time runs out. This game is packaged as an Android library for easy integration into your Android app.

## 📸 Screenshots

<p align="center">
  <img src="01.png" alt="Screenshot 1" width="250"/>
  <img src="02.png" alt="Screenshot 2" width="250"/>
  <img src="03.png" alt="Screenshot 3" width="250"/>
</p>

## 📦 Installing the Package:

### Step 1: Add the JitPack repository to your build file
Add the following in your `settings.gradle` or `settings.gradle.kts` file:

#### For `settings.gradle.kts`
```kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://www.jitpack.io") }
    }
}
```
### For `settings.gradle`
```gradle
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```
### Step 2: Add the dependency
In your `build.gradle` file, add the following dependency:

```gradle
dependencies {
    implementation 'com.github.SadO-On:Android-Tab-demo:1.1.0'
}
```
## 🚀 Using Our Package in Your App
After successfully adding the package to your project, you can present the game screen within your app. Make sure to import the necessary package module at the top of the file where you intend to present the game.

To launch the game activity from any UI in your app, use the following code:

```kotlin
startActivity(Intent(this, GameActivity::class.java))
```
## 🎮 Accessing Player Level and XP

To access the player’s level and XP, you can use `SharedPreferences`. The key names for retrieving this information are `level` and `xp`. Here’s how you can do it:

```kotlin
applicationContext.getSharedPreferences("", MODE_PRIVATE).getInt("level", -1)
applicationContext.getSharedPreferences("", MODE_PRIVATE).getInt("xp", -1)
```

## ⚙️ Compatibility

| Platform      | Version      |
|---------------|--------------|
| **Android SDK** | 24+          |

This package supports **Jetpack Compose**.

## 🔄 Dependency Resolution:

### 1. Versioning:
Our package relies on specific dependencies. Ensure that you are using the recommended versions mentioned in the table below:

| Android Dependency                    | Version     |
|---------------------------------------|-------------|
| `androidx.media3:media3-exoplayer`    | 1.2.0       |
| `androidx.media3:media3-ui`           | 1.3.1       |
| `androidx.compose:compose-bom`        | 2024.04.01  |
| `com.airbnb.android:lottie-compose`   | 6.4.0       |

### 2. Conflict Resolution:
In case of version conflicts with existing dependencies in your project, follow these steps:

1. Identify conflicting dependencies in your project.
2. Check our documentation for the recommended versions of dependencies.
3. Update the conflicting dependencies in your project accordingly.

If issues persist, reach out to us for personalized assistance. [Contact us here](https://www.98s.studio/).
