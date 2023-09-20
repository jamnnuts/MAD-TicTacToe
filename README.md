# MAD-TicTacToe

This Android app is a feature-rich Tic-Tac-Toe game developed as part of the "Mobile Application Development (COMP2008)" course, Assignment 1. This assignment 
aims to create a fully functional and customizable Tic-Tac-Toe game for Android. This game demonstrates our understanding of various aspects of mobile app 
development, including user interface design, fragmentation and game logic.

# Table of Contents: 
1. Introduction / Game Description
2. Contributions
3. Features
4. Getting started
5. Usage

# Introduction / Game Description
As part of the assignment, This is a Tic-Tac-Toe game, a classic 2-player game played on a game board, either 3x3, 4x4 or 5x5 grid. In this game, each player
takes turns putting their respective markers on an empty grid cell. The goal of the game is to be the first player to get three/four/five of their markers in a 
row, either horizontally, vertically, or diagonally. The game will be a draw if all cells are filled, and no player has formed a winning line.

Despite its simplicity, Tic-Tac-Toe is a game that offers insights into game theory, mathematical properties, and strategic possibilities. It is often used to
teach concepts such as dominance, strategy, and decision-making, making it a foundational game for understanding more complex games and algorithms, including
those in the field of artificial intelligence.

# Contributions

We're proud to acknowledge that this Tic-Tac-Toe Android app results from collaborative efforts by four dedicated team members: Jacob Arvino, Robin Fu, Alex 
Chan, and Malaika Noor. Each member has made valuable contributions to the development of this app, ensuring that all required features for the Mobile 
Application Development (MAD) assignments were successfully implemented. Each member has contributed equally and demonstrated their understanding of 
implementing the required features for the MAD assignment. Our collaborative efforts have resulted in a feature-rich Tic-Tac-Toe app that we proudly present. 
We hope you enjoy using it as much as we enjoyed developing it.

# Features

Multiple Game Modes

Our Tic-Tac-Toe Android app offers multiple game modes to cater to different player preferences:
- 2-Player Mode: In this mode, two human players take turns playing against each other on the same device. It provides a classic and engaging head-to-head
gaming experience.
- Vs. AI Mode: Our app includes a vs. AI mode for solo players or those seeking a challenge. In this mode, a human player can test their skills against a
simple AI opponent. The AI makes random moves, making it a suitable opponent for players of all levels.

Personalization

We understand that every player has unique preferences. That's why we offer extensive personalization options:
- Board Size Customization: Our app allows users to adjust the game board's size, providing options for 3x3, 4x4, and 5x5 grids. The user interface adapts 
seamlessly to the selected board size, ensuring a consistent gaming experience.
- Win Condition Selection: Players can specify the number of consecutive markers required to win the game, with options ranging from three to five. This
empowers users to tailor the game's challenge level to their liking.
- Player Marker Choice: We let users choose their preferred markers, whether the classic "X" and "O" or custom icons. The game logic dynamically adapts to 
the selected markers, ensuring a personalized gaming experience.

User Profiles and Avatar Selection
  
Our app enhances user engagement and identity with the following features:
- User Profiles: Users can create their profiles by providing their names. This personalizes their gaming experience and allows them to track their progress.
- Avatar Selection: We offer a collection of avatars for users. This adds a touch of personality to their profiles. Users can select avatars that resonate with 
them and represent their gaming identity.
- Profile Editing: Users can edit their profile settings, including avatar selection, ensuring their profiles evolve with their preferences.

Gameplay Statistics Tracking
  
To add competitiveness and motivation to the game, our app includes gameplay statistics tracking:
- Leaderboards: We incorporate leaderboards to keep track of high scores and achievements. Players can compete for the top spots and strive to improve their 
Rankings.
- Comprehensive Statistics: Our app records detailed gameplay statistics, including total games played, wins, losses, draws, and win percentages for each user. 
This data offers insights into their performance and progress.

In-Game Information
  
We provide players with a wealth of in-game information to enhance their gaming experience:
- Game Board Visualization: The game board is visually presented, clearly indicating where each player has placed their markers (whether "X," "O," or custom 
icons).
- Player Indicators: Player names or labels are displayed, making it easy to identify whose turn it is. Clear indicators ensure a smooth gaming flow.
- Game Progress Tracking: Players can monitor the number of moves made and the total available moves. This information helps them assess their chances of
winning or achieving a draw.
- Countdown Timer: Each player's turn is timed with a countdown timer, adding an element of strategy and excitement to the game.
- Notifications and Messages: Our app provides informative messages and notifications for significant game events, such as declaring a win, a draw, or an
invalid move. Players receive timely feedback on the game's outcome.
- Undo and Reset Options: Players can undo their last move or reset the game board to correct a mistake or start over.
- Settings and Menu: Easy access to settings allows players to adjust customizations or return to the main menu at any point during the game. It ensures a 
seamless user experience.
- User Profile Display: Users' profiles, including avatars and usernames, are visible, fostering a sense of identity and community within the app.

Special Technical Requirements

To ensure a smooth and responsive user experience, we've implemented the following technical enhancements:
- Adaptive UI Layout: Our app's user interface seamlessly adapts to both landscape and portrait orientations and functions flawlessly on various devices, 
including phones and tablets.
- Orientation Preservation: In-game information is retained even when the device orientation changes, preventing data loss and disruptions during gameplay.
- Fragment-Based Architecture: We've implemented a fragment-based architecture to efficiently manage different UI components, such as the main menu, settings 
screen, user profile, and game board. This promotes modularity and maintainability.
- Avatar Selection with RecyclerView: We utilize RecyclerView to provide a scrollable grid or list of avatar options, ensuring a user-friendly selection
process.

Our Tic-Tac-Toe Android app is designed to provide an engaging, customizable, and technically robust gaming experience that caters to a wide range of players 
and preferences.

# Getting started

Before you dive into playing our Tic-Tac-Toe Android app, getting familiar with its XML files, which control various user interface aspects, is helpful. 
Here's an overview of the XML files included in the project, located in the MAD-TicTacToe/app/src/main/res/layout directory:

- activity_main.xml: This XML file is the main layout for the app's activity. It serves as the container for displaying different fragments and UI
components as users navigate through the app.
- avatar_layout.xml: This XML file displays avatar options to users during player profile creation. Users can choose their preferred avatars from this
selection to represent themselves in the game.
- fragment_game_board3x3.xml: This XML file defines the layout for the 3x3 game board, the central element of the Tic-Tac-Toe game. It specifies how the grid
cells are arranged, and player markers ("X," "O," or custom icons) are displayed within these cells.
- fragment_game_board_frag4x4.xml: Similar to the 3x3 board, this XML file defines the layout for the 4x4 game board, expanding the game's grid for those who
prefer a larger challenge.
- fragment_game_board_frag5x5.xml: This XML file defines the layout for the 5x5 game board, offering an even larger grid for players who seek an extended and
strategic gaming experience.
- fragment_main_menu.xml: The app's main menu is defined in this XML file. It serves as the entry point for users and provides options for selecting game
modes, accessing settings, and navigating to other parts of the app.
- fragment_player_creation.xml: When users create or edit their profiles and choose avatars, this XML file controls the layout and options.
It's where users can personalize their gaming identity.
- fragment_settings.xml: This XML file is responsible for the layout and options presented in the settings screen. It allows users to customize various aspects
of the game, such as board size, win conditions, and player markers, to match their preferences.
- fragment_stat.xml: This XML file is responsible for the layout of the statistics screen, where users can view detailed gameplay statistics, including their
total games played, wins, losses, draws, and win percentages.

To provide a deeper understanding of this Tic-Tac-Toe Android app's functionality, here's an overview of the Java files included in the project, located in the 
app/src/main/java/com/example/mad_tictactoe directory:

- AvatarAdapter.java: his Java file corresponds to an adapter responsible for managing and displaying avatars within the app's user interface. It plays
a crucial role in presenting avatar options to users during player profile creation.
- AvatarVH.java: The AvatarVH.java file represents an Avatar View Holder class designed to efficiently manage and display avatar images. It's an integral
part of the avatar selection process, ensuring a smooth and user-friendly experience.
- GameBoardFrag.java, GameBoardFrag4x4.java, and GameBoardFrag5x5.java: These Java files correspond to different gameboard fragments. Each implements a
specific board size (3x3, 4x4, or 5x5) and handles the logic and user interface for the respective game board. These classes manage the placement of markers,
track game progress, and handle player interactions for their respective board sizes.
- MainActivity.java: This Java file represents the main activity of the app. It controls the overall flow and navigation within the app, including the
transition between different fragments, such as the game board, main menu, leaderboard, and player creation screens. It serves as the entry point for the app.
- MainMenuFrag.java: This class is responsible for the logic and user interface of the main menu fragment. It integrates player tracking into the game logic,
possibly providing features such as player history, achievements, or access to different game modes.
- Player.java: The Player class handles player-related information and functionality. It may manage player names, avatars, and their respective game progress
and statistics. It's a key component for personalizing the gaming experience.
- PlayerCreationFrag.java: This class likely corresponds to the player creation fragment. It enables users to create and edit their player profiles, including
choosing avatars and customizing their names. It ensures that player identity is integrated into the game.
- SessionDataViewModel.java: This Java file possibly serves as a ViewModel component. It may be responsible for managing and sharing data between different
fragments and activities within the app. This class could be crucial in maintaining data consistency and providing a centralized data source.
- StatFrag.java: StatFrag is likely associated with the statistics fragment. It may handle the display of detailed gameplay statistics, such as wins, losses,
draws, and win percentages. The mention of fixing an issue with win percentage suggests that this class ensures accurate stat tracking.
- settings.java: This java file is associated with the app's settings screen, where users can customize various aspects of their gaming experience. It could
handle user preferences related to the game, such as board size, win conditions, and player markers.


# Usage

Our Tic-Tac-Toe Android app is designed for use with Android Studio, a powerful development environment for building Android applications. To get started with 
our app, you can follow these steps:

1. Download/ clone the Project:
Android Studio lets you download/ clone the app project directly from GitHub. Here's how:

- Open Android Studio.
- Click on "File" in the top menu.
- Select "New" and then choose "Project from Version Control."
- Click on "GitHub."
- Enter your GitHub credentials if prompted.
- In the "Repository URL" field, paste the URL of our app's GitHub repository.
- Click the "Clone" button to download the project to your local machine.

2. Open the Project in Android Studio:
Once you have the project files locally, open Android Studio.

- Click on "File" in the top menu.
- Select "Open" and navigate to the directory where you downloaded/cloned the project.
- Select the project's root directory and click "OK."

3. Configure Android Emulator (AVD):
You need an Android emulator or a physical Android device to run the app. If you don't have a device, configure an Android Virtual Device (AVD) within Android
Studio:

- Click on "Tools" in the top menu.
- Select "AVD Manager."
- Click "Create Virtual Device" and follow the wizard to create a virtual device with the desired specifications.

4. Run the App:
You're ready to run the app with the project open and an emulator (or physical device) configured.

- Click the "Run" button (a green play icon) in the Android Studio toolbar.
- Select the target device (either your AVD or connected physical device).
- Click "OK" to build and launch the app.

Now, the app is running on your device. You can explore the app and enjoy it.
