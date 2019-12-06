# Monopoly

### Team Information
__Team ID__: 9

__Members__: 
* James Ward
* Ronan Corcoran

---

### Project Structure

- Source code is stored in the ``/src/jwrc`` directory
- Test code is stored in the ``/src/test`` directory
- JavaDocs are stored in the ``/doc`` directory
- Diagrams are stored in the ``/diagrams`` directory
- A compiled jar file is stored in ``/monopoly_team9.jar``

i.e. directory structure:
```
───monopoly
    ├───diagrams
    ├───doc
    └───src
        ├───jwrc
        │   ├───board
        │   ├───game
        │   ├───main
        │   ├───menus
        │   └───player
        └───test
```

---
### Running the Application
No input parameters are required. However it is important to note the ``jar`` file was compiled using ``Java 1.8``
instead of newer versions so as to support current typical JRE Version of 1.8.

__To run the application:__
- Download the jar file
- Set this download directory as the current working directory
- Run: ``java -jar monopoly_team9.jar`` in cmd/terminal
- Follow the pre-game instructions to begin the game.

#### General Instructions
The user prompts are fairly comprehensive and give a good guide as to what options you have at each stage. Should an invalid input be entered,
this will be warned.

The application is designed so that the user can take all actions relating to properties, jail etc at any point during
their turn, __not__ just before the turn.

---

### Workload Distribution
- Partners met every Monday afternoon to plan out the work for the week.
- Work was kept to a rough 50/50 throughout the course of the project, depending on availability - where one may assist
the partner in the case of a bottleneck in order to meet our schedule.
- A OneNote shared folder with 2 documents were held: one for weekly goals to tick off and another with existing and
resolved bugs that were tackled in batches. These files were very useful for collaboration and having shared
knowledge of current and future issues with the application.
- Tests were written both by the class creator and the other member so as to ensure a greater diversity
of approach to edge cases


