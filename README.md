🧱 Brick Breaker Game in Java

A feature-rich Brick Breaker game developed using Java and Java Swing. This project demonstrates Object-Oriented Programming, event handling, collision detection, game loops, collections, and file handling through an interactive arcade-style game.

🎮 About the Game

Brick Breaker is a classic arcade game where the player controls a paddle to bounce a ball and destroy bricks.

The game includes 3 levels, multiple brick types, power-ups, lives, scoring, increasing difficulty, pause/resume functionality, and high-score storage.

The objective is to destroy all breakable bricks while keeping the ball from falling below the paddle.

✨ Features
🎮 Interactive keyboard controls
🏓 Paddle movement
⚪ Ball movement and collision detection
🧱 Multiple brick types
⚡ Random power-ups
❤️ Multiple lives
🏆 Score and high-score system
📈 Increasing ball speed
🎯 3 levels with increasing difficulty
⏸️ Pause and resume
🔄 Restart functionality
🏁 Game Over screen
🎉 Win screen
💾 High-score persistence using file handling
🧱 Brick Types
Brick	Points	Description
Normal	10	Destroyed with one hit
Strong	20	Requires two hits
Bonus	30	Higher score and chance of power-up
Unbreakable	0	Cannot be destroyed
⚡ Power-Ups

Power-ups randomly appear when certain bricks are destroyed.

Power-Up	Effect
Big Paddle	Increases paddle size
Extra Life	Adds one life
Slow Ball	Reduces ball speed
Multi Ball	Creates an additional ball
🎯 Levels
Level 1
Basic brick layout
Normal and bonus bricks
Standard ball speed
Level 2
Strong bricks introduced
Increased ball speed
Higher difficulty
Level 3
Unbreakable bricks introduced
Higher ball speed
More challenging gameplay

A level is completed when all breakable bricks are destroyed.

🎮 Controls
Key	Action
ENTER	Start game
LEFT ARROW	Move paddle left
RIGHT ARROW	Move paddle right
P	Pause / Resume
ENTER	Next level
ENTER	Restart after Game Over
ENTER	Play again after winning
🛠️ Technologies Used
Java 17
Java Swing
AWT
Object-Oriented Programming
ArrayList
File Handling
Keyboard Event Handling
Collision Detection
Timer-based Game Loop
📂 Project Structure
BrickBreaker/
│
├── Main.java
├── GameFrame.java
├── GamePanel.java
├── Ball.java
├── Paddle.java
├── Brick.java
├── PowerUp.java
├── ScoreManager.java
├── .gitignore
└── README.md
🧩 Class Responsibilities
Main.java

The entry point of the application.

GameFrame.java

Creates and configures the main game window using JFrame.

GamePanel.java

The main game controller responsible for:

Game loop
Rendering
Keyboard input
Ball movement
Paddle movement
Collision detection
Brick management
Power-ups
Levels
Score
Lives
Game states
Ball.java

Handles:

Ball position
Movement
Speed
Direction
Collision boundaries
Paddle.java

Handles:

Paddle movement
Paddle size
Paddle collision
Paddle rendering
Brick.java

Represents the different types of bricks and manages their health, collision, and score values.

PowerUp.java

Manages falling power-ups and their effects.

ScoreManager.java

Loads and saves the player's high score using a local file.

🏗️ OOP Concepts Demonstrated

This project applies several important Java concepts.

Encapsulation

Game properties are stored as private variables and accessed through methods.

private double x;
private double y;
Classes and Objects

The game consists of objects such as:

Ball
Paddle
Brick
PowerUp
Enums

Enums are used for brick types, power-up types, and game states.

NORMAL
STRONG
BONUS
UNBREAKABLE
Collections

ArrayList is used to manage multiple game objects.

ArrayList<Ball> balls;
ArrayList<Brick> bricks;
ArrayList<PowerUp> powerUps;
File Handling

The high score is stored locally and loaded when the game starts.

🔄 Game Flow
              START
                |
                v
           Main Menu
                |
         Press ENTER
                |
                v
             Level 1
                |
                v
          Play the Game
                |
       +--------+--------+
       |                 |
       v                 v
   Lose Ball        Destroy Bricks
       |                 |
       v                 v
   Lose Life        Level Complete
       |                 |
       |                 v
       |              Level 2
       |                 |
       |                 v
       |              Level 3
       |                 |
       |                 v
       |              YOU WIN
       |
       v
  Lives = 0
       |
       v
   GAME OVER
▶️ How to Run
Prerequisites

Install Java Development Kit (JDK 17 or later).

Check Java:

java -version

Check the compiler:

javac -version
Clone the Repository
git clone https://github.com/pavaniswarna-stack/BrickBreaker-Java.git

Navigate into the project:

cd BrickBreaker-Java
Compile
javac *.java
Run
java Main
🧹 Clean Build

Compiled .class files are excluded from GitHub using .gitignore.

*.class
.vscode/
highscore.txt

To remove existing compiled files on Windows:

del *.class

Then compile again:

javac *.java
📈 Learning Outcomes

Through this project, the following concepts are practiced:

Java Object-Oriented Programming
GUI development using Swing
Event-driven programming
Keyboard event handling
Game loops
Collision detection
Collections
Enums
File handling
State management
Problem solving
Modular class design
🚀 Future Enhancements

Possible future improvements include:

🔊 Sound effects and background music
🎨 Advanced graphics and animations
✨ Particle effects
🏅 More game levels
🎯 Difficulty selection
🛡️ Additional power-ups
🎮 Mouse and gamepad controls
📊 Detailed game statistics
🥇 Online leaderboard
💾 Complete game progress saving
👩‍💻 Author

Pavani Thulasi Swarna

B.Tech – Computer Science & Engineering

📜 License

This project was developed for educational and academic purposes.
