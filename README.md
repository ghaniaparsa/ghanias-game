# Simple Rock-Paper-Scissors Game with Java Swing, Login, and Statistics

ES234211 – Programming Fundamental | Small Project (Individual Project)

> This project uses an alternative simple game (Rock-Paper-Scissors)
> instead of the recommended Tic-Tac-Toe, as explicitly allowed by the
> guideline: *"students may use another simple game if the main
> features are still available: login, play game, update statistics,
> and show Top 5 scorers."* All required features below are present.

## Student Information
Name: Ghania Parsa Rasheeda Ramadhani Wachid
Student ID: 5026251206
Class: E

## Project Description
This project is a simple Rock-Paper-Scissors game built with Java and
Java Swing. The application requires the user to log in, lets the user
play one round at a time against the computer, records game statistics
(wins, losses, draws, score) in a database, and shows the Top 5
scorers. All data is stored in a single database table, following the
project's one-table rule.

Each round, the player picks **Rock**, **Paper**, or **Scissors**; the
computer picks one at random at the same time, and the standard rules
decide the round (Rock beats Scissors, Scissors beats Paper, Paper
beats Rock; same choice = draw).

## Features
- Login using a database (username + password check)
- Play Rock-Paper-Scissors against the computer using a Swing GUI
- Win / lose / draw detection for every round, with a short
  "computer is choosing" delay for feedback
- Recording of wins, losses, draws, and total score after every round
- "My Statistics" window showing the logged-in player's own stats
- "Top 5 Scorers" window showing the database's top players in a JTable
- Full navigation between Login, Main Menu, Game, Statistics, and Top
  Scorers windows, plus a safe Exit option

## Scoring Rule
| Result | Score Change |
|--------|--------------|
| Win    | +10 points   |
| Draw   | +3 points    |
| Lose   | +0 points    |

## Class Structure
| Class            | Responsibility |
|-------------------|----------------|
| `Main`            | Starts the program and opens the Login Window. |
| `DatabaseManager` | Handles the JDBC database connection. |
| `Player`          | Model class storing id, username, wins, losses, draws, score. |
| `PlayerService`   | Handles login, fetching player data, updating statistics, and retrieving the Top 5 scorers. |
| `GameLogic`       | Picks the computer's random choice and decides the round's winner. |
| `LoginFrame`      | Swing window for username/password input. |
| `MainMenuFrame`   | Swing window for the main menu after login. |
| `GameFrame`       | Swing window for playing Rock-Paper-Scissors. |
| `StatisticsFrame` | Swing window showing personal statistics. |
| `TopScorersFrame` | Swing window showing the Top 5 scorers using a `JTable`. |

Note: there is no `GameBoard` class in this version, because
Rock-Paper-Scissors has no board to store — each round is just one
choice compared against another. No inheritance, interfaces, or
abstract classes are used between these classes — only simple,
single-responsibility classes (apart from extending Swing's own
`JFrame`).

## Database
Database used: **MySQL** (default). PostgreSQL and SQL Server are also
supported — see the notes inside `src/DatabaseManager.java`.

The database contains **one table only**: `players`.

```sql
CREATE TABLE players (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    wins INT DEFAULT 0,
    losses INT DEFAULT 0,
    draws INT DEFAULT 0,
    score INT DEFAULT 0
);
```

## How to Create the Database
1. Install MySQL (or PostgreSQL / SQL Server) locally.
2. Run the script in `database/schema.sql`. It creates the
   `rps_project` database, the `players` table, and 5 sample users
   (`student1` ... `student5`, all with password `12345`).
3. (PostgreSQL/SQL Server users) Adjust the `CREATE DATABASE` syntax in
   `schema.sql` slightly if needed — the `players` table definition itself
   works on all three with only minor type changes (see comments inside
   `DatabaseManager.java`).

## How to Run the Program
1. Make sure you have **JDK 17+** installed.
2. Download the JDBC driver jar for your chosen database and add it to
   your classpath / project libraries:
   - MySQL: `mysql-connector-j-x.x.x.jar`
   - PostgreSQL: `postgresql-x.x.x.jar`
   - SQL Server: `mssql-jdbc-x.x.x.jar`
3. Open `src/DatabaseManager.java` and set `URL`, `USER`, and `PASSWORD`
   to match your own database configuration.
4. Compile and run:
   ```bash
   # from the project's src/ folder, with the JDBC driver jar on the classpath
   javac *.java
   java -cp ".:/path/to/jdbc-driver.jar" Main
   ```
   (On Windows, use `;` instead of `:` in the classpath.)
5. The Login Window will appear. Log in with one of the sample users
   (e.g. `student1` / `12345`), or any user you inserted yourself.

## Class Explanation
- **Main** — entry point; opens `LoginFrame` on the Swing event thread.
- **DatabaseManager** — the only class that opens a JDBC `Connection`.
- **Player** — a plain data holder for one row of the `players` table.
- **PlayerService** — all SQL lives here: `login()`, `getPlayerById()`,
  `updateStatistics()`, and `getTopFiveScorers()`.
- **GameLogic** — `computerChoice()` picks Rock/Paper/Scissors at
  random; `determineResult()` applies the standard rules to return
  `WIN`, `LOSE`, or `DRAW` from the player's point of view.
- **LoginFrame** — takes username/password, calls `PlayerService.login()`,
  opens `MainMenuFrame` on success or shows a `JOptionPane` error on
  failure.
- **MainMenuFrame** — welcomes the player and routes to Game,
  Statistics, Top Scorers, or Exit.
- **GameFrame** — three choice buttons (Rock/Paper/Scissors) wired to
  `GameLogic`; after the player picks, it shows the computer's choice,
  the round result, and calls `PlayerService.updateStatistics()`.
- **StatisticsFrame** — re-reads the current player's row from the
  database and displays wins/losses/draws/score.
- **TopScorersFrame** — runs the Top 5 query and fills a `JTable` with
  the results (never hardcoded).

## Screenshots
<img width="457" height="335" alt="Screenshot 2026-06-26 at 15 19 09" src="https://github.com/user-attachments/assets/ee86c3b8-65cf-4cd5-8962-a75f1a9d85bd" />
<img width="416" height="290" alt="Screenshot 2026-06-26 at 15 19 24" src="https://github.com/user-attachments/assets/a5ffdb15-23e0-4af1-875e-92fd5a2758e5" />
<img width="451" height="436" alt="Screenshot 2026-06-26 at 15 21 58" src="https://github.com/user-attachments/assets/f6c9161b-08f7-469d-8c6b-8c99f9ee9be5" />
<img width="469" height="438" alt="Screenshot 2026-06-26 at 15 22 04" src="https://github.com/user-attachments/assets/2c8d04d4-4a41-4b2b-a613-b336a2334967" />
<img width="408" height="348" alt="Screenshot 2026-06-26 at 15 22 11" src="https://github.com/user-attachments/assets/30540913-d416-4c83-9cca-78c6945ea885" />
<img width="466" height="372" alt="Screenshot 2026-06-26 at 15 22 18" src="https://github.com/user-attachments/assets/1dabcbe5-d929-4333-8e2c-b140f4769c58" />
<img width="416" height="303" alt="Screenshot 2026-06-26 at 15 22 37" src="https://github.com/user-attachments/assets/b6b7c1a7-4b9c-40bd-899c-908b890dd5af" />









## GitHub Repository Link
https://github.com/ghaniaparsa/ghanias-game
## YouTube Video Link
https://youtu.be/oDQ3LwhOmsE
