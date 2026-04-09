# Quiz App – SIT305 Task 3.1

## Overview
This project is an Android Quiz Application developed for SIT305 Task 3.1.  
The app allows users to enter their name, answer multiple-choice quiz questions, track their progress in real time, and view their final score at the end of the quiz.

The project was built in Android Studio using Kotlin and XML layouts.

---

## Features
- Enter username before starting the quiz
- Multiple-choice quiz questions
- Submit answer for each question
- Visual feedback after submission:
  - Correct answer turns green
  - Incorrect selected answer turns red
  - Correct answer is also shown in green
- Prevents changing the answer after submission
- Progress bar to show quiz progress
- Final result screen with total score
- "Take New Quiz" button to restart the quiz
- Username is retained when returning to the main screen
- "Finish" button to close the application

---

## Screens
### 1. Main Screen
- User enters their name
- Starts the quiz

### 2. Quiz Screen
- Displays one question at a time
- Shows four answer options
- Allows user to submit and move to the next question
- Displays progress with a progress bar

### 3. Result Screen
- Shows final score
- Allows user to start a new quiz
- Allows user to finish the app

---

## Technologies Used
- **Kotlin**
- **Android Studio**
- **XML Layouts**
- **SharedPreferences** for saving the username during the session


