# 🎮 Brick Breaker Game

A simple 2D Brick Breaker game developed using **Java Swing**. The player controls a paddle to bounce the ball and break all the bricks while earning points and managing limited lives.

## 📌 Project Overview

The Brick Breaker game is a desktop-based Java application that demonstrates important concepts of **Object-Oriented Programming (OOP)**, GUI development, keyboard event handling, game loops, and collision detection.

The objective of the game is to destroy all the bricks using the bouncing ball without losing all available lives.

## ✨ Features

- 🎮 Paddle movement using keyboard
- ⚽ Automatic ball movement
- 🧱 Multiple rows and columns of bricks
- 💥 Ball and brick collision detection
- 🏓 Ball and paddle collision detection
- 🧱 Wall collision detection
- ⭐ Score system
- ❤️ Three lives
- 🏆 Winning condition
- 💀 Game Over condition
- 🔄 Restart functionality
- 🖥️ Java Swing graphical interface

## 🛠️ Technologies Used

- **Java**
- **Java Swing**
- **AWT Graphics**
- **Object-Oriented Programming**
- **Event Handling**
- **Collision Detection**

## 📂 Project Structure

```text
BrickBreaker/
│
├── Main.java
├── GameFrame.java
├── GamePanel.java
├── Ball.java
├── Paddle.java
├── Brick.java
├── .gitignore
└── README.md
🧩 Class Description
Main.java

Entry point of the application. Starts the Brick Breaker game.

GameFrame.java

Creates and configures the main game window using JFrame.

GamePanel.java

Contains the main game logic, including:

Game loop
Keyboard input
Collision detection
Score
Lives
Game Over
Winning condition
Ball.java

Controls the ball's:

Position
Movement
Direction
Collision behavior
Reset functionality
Paddle.java

Controls the player's paddle and its left/right movement.

Brick.java

Represents individual bricks and manages their destroyed state.

🎮 Controls
Key	Action
←	Move paddle left
→	Move paddle right
ENTER	Restart after Game Over or Win
⚙️ Requirements

Before running the project, make sure you have:

Java Development Kit (JDK) installed
Java version 17 or later
VS Code, IntelliJ IDEA, or Eclipse

Check your Java installation:

java -version
javac -version
▶️ How to Run
1. Clone the repository
git clone https://github.com/pavaniswarna-stack/BrickBreaker-Java.git
2. Open the project

Open the BrickBreaker folder in VS Code or another Java IDE.

3. Compile the project

Open the terminal inside the project folder and run:

javac *.java
4. Run the game
java Main

The Brick Breaker game window will open.

🧠 Java Concepts Demonstrated

This project demonstrates:

Classes and Objects
Encapsulation
Constructors
Methods
ArrayList
Inheritance concepts
Event Handling
JFrame
JPanel
Graphics
Graphics2D
Timer
Keyboard Events
Collision Detection
🎯 Game Logic

The game continuously performs the following operations:

Start Game
    ↓
Move Paddle
    ↓
Move Ball
    ↓
Check Wall Collision
    ↓
Check Paddle Collision
    ↓
Check Brick Collision
    ↓
Update Score
    ↓
Check Lives
    ↓
Check Remaining Bricks
    ↓
Win / Game Over
🔮 Future Enhancements

The project can be further improved by adding:

Multiple game levels
Different brick types
Power-ups
Increasing ball speed
Sound effects
Background music
High-score system
Difficulty levels
Start menu
Pause and resume functionality
Database-based score storage
Improved graphics and animations
👩‍💻 Author

Pavani Thulasi Swarna

B.Tech – Computer Science and Engineering

📄 License

This project is created for educational and academic purposes.


### One important change

Since your actual repository URL is being used in the README, if your repository name or GitHub username is different, change this line:

```bash
git clone https://github.com/pavaniswarna-stack/BrickBreaker-Java.git
