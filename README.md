## What is the Water Sort Game?
The water sort game consists of a number of bottles (one plus the number of colors in the game to be precise) where after you the user give the
game the colors that you want to play with and the capacity of your bottles and then the game randomly distributes the colors among the bottles in a way that
there are as many instances of each as the bottle capacity that we set for the game.
## Playing the game
Your goal in this game is to organize the bottles such that each bottle is full and only contains a single color(and one bottle will end up being empty).

There are some rules and commands that you're gonna need to use to achive this goal, first let's list off the commands and the explain the rules of the game

- `select $` This command selects the bottle numbered $.
- `selectNext` This command selects the next bottle after the selected bottle and if the final bottle is selected it selects the first bottle.
- `selectPrevious` Works in similar fashion to "selectNext" except that it selects the bottle before the selected bottle.
- `deselect` If a bottle is selected, this command deselects that bottle.
- `swap $` Swaps the current selected bottle with the bottle numbered $.
- `pour $` From the current selected bottle, pours onto the bottle numbered $.(the conditions of this command are explained in rules section).
- `undo` This command undoes your last action, however it can only undo up to the last 10 actions and no more.
- `finish` finishes the game.

## Rules
There are some rules that you have to follow alongside the commands that you use for playing.
The first rule is a condition for selecting the bottles, you are not allowed to choose a bottle if it is compeletly empty or it is compeletly full of one color.

Second is the rule for pouring onto bottles. in order to pour from a selected bottle onto your destination bottle some conditions must be fulfilled :

- 1- the top color of the selected bottle must match the top color of the destination bottle or the destination must be compeletly empty.

- 2-

















The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).
