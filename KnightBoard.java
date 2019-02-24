public class KnightBoard {
  int[][] board;
  //2d arrays storing the moves of the board. My notes were converted basically.
  int[][] moves = {{1, 2}, {1, -2}, {-1, 2}, {-1, -2}, {2, 1}, {2, -1}, {-2, 1}, {-2, -1}};
  /*
  moving up right - board[i - 2][x + 1]
  moving up left - board[i - 2][x - 1]
  moving down left - board[i + 2][i - 1]
  moving down right - board[i + 2][i + 1]
  moving right up - board[i + 1][x - 1]
  moving right down - board[i + 1][x + 1]
  moving left up - board[i - 1][x - 1]
  moving left down - board[i - 1][x + 1]
  */

public static void main(String[] args) {
  a = new KnightBoard(5, 5);
}



//if the dimensions of the board are odd, then it means that it will not be a closed tour.
//@throws IllegalArgumentException when either parameter is <= 0.
public KnightBoard(int r, int c){
  if (r <= 0 || c <= 0){
    throw IllegalArgumentException();
  }
  board = new int[r][c];
  clear();
}

private void clear(){
  for (int i = 0; i < board.length; i++){
    for (int x = 0; x < board.length; x++){
      board[i][x] = 0;
    }
  }
}

  //Initialize the board to the correct size and make them all 0's
public String toString(){
  int count = 0;
  String result = "";
  for (int i = 0; i < board.length; i++){
    for (int x = 0; x < board.length; x++){
      if (board.length * board[i].length < 10) {
        if (board[i][x] == 0){
          result += '_';
        }
        else {
          result += " " + board[i][x] + " ";
        }
      }
      else {
        if (board[i][x] < 10){
          if (board[i][x] == 0){
            result += " _ ";
          }
          else {
            result += " " + board[i][x] + " ";
          }
        }
        else {
          result += board[i][x] + " ";
        }
      }
      }
      result += "\n";
    }
    return result;
  }

/*
see format for toString below
blank boards display 0's as underscores
you get a blank board if you never called solve or
when there is no solution

Use the following format for toString:

(THESE ARE NOT VALID SOLUTIONS, They JUST TO DEMONSTRATE FORMAT)

Single spaces between numbers, but leading spaces on single digit numbers:
 1  2  5
 3  4  6
 7  8  9

Which is equivalant to: " 1  2  5\n 3  4  6\n 7  8  9\n"

When there are two digit numbers (rows*cols >= 10) Put a leading space in front of single digit numbers:
(spaces replaced with _ to show the difference)
_1 _2 15 _6
_3 _4 _7 11
_8 _9 10 12
13 14 _5 16

So it looks like this:
 1  2 15  6
 3  4  7 11
 8  9 10 12
13 14  5 16
*/
}

/*
Modifies the board by labeling the moves from 1 (at startingRow,startingCol) up to the area of the board in proper knight move steps.
@throws IllegalStateException when the board contains non-zero values.
@throws IllegalArgumentException when either parameter is negative
 or out of bounds.
@returns true when the board is solvable from the specified starting position
*/

/*
moves for a knight in a chess piece.
0 0 0 0 0
0 0 0 0 0
0 0 0 0 0
0 0 0 0 0
0 0 0 0 0
*/

//similar fashion to queens. kinda easy.
private boolean addKnight(int r, int c, int number){
  if (r < 0 || r >= board.length || c < 0 || c >= board.length) {
    return false;
  }
  if (board[r][c) != 0){
    return false;
  }
  board[r][c] = number;
  return true;
}

//similar to addKnight except there's no number. If it can be removed, just remove.
private boolean removeKnight(int r, int c){
  if (r < 0 || r >= board.length || c < 0 || c >= board.length){
    return false;
  }
  if (board[r][c] == 0) {
    return false;
  }
  board[r][c] = 0;
  return true;
}

public boolean solve(int r, int c){
  if (r <= 0 || r >= board.length || c <= 0 || c >= board.length) {
    throw IllegalArgumentException();
  }
  for (int i = 0; i < board.length; i++){
    for(int x = 0; x < board.length; x++){
      if (board[i][x] != 0) {
        throw IllegalStateException();
      }
    }
  }
  return solveH(r, c, 1);
}

private boolean solveH(int r ,int c, int level){
  if (level == board.length * board.length + 1){
    return true;
  }
  for (int i = 0; i < board.length; i++) {
    for (int x = 0; x < board.length; x++){
      if (addKnight(r, c, level)) {
        if (solveH(r + moves[i][x], c + moves[i][x + 1], level + 1)){
          return true;
        }
      else removeKnight(r, c);
    }
  }
}
return false;
}

/*
@throws IllegalStateException when the board contains non-zero values.
@throws IllegalArgumentException when either parameter is negative
 or out of bounds.
@returns the number of solutions from the starting position specified

TOURS ARE CLOSED WHEN
m and n are both odd
m = 1, 2, or 4
m = 3 and n = 4, 6, or 8.
*/

public int countSolutions(int r, int c){
  if (r < 0 || c < 0 || r >= board.length || c >= board.length){
    throw IllegalArgumentException();
  }
  for (int i = 0; i < board.length; i++){
    for (int x = 0; x < board.length; x++){
      if (board[i][x] != 0){
        throw IllegalStateException();
      }
    }
  }
  return countHelper(r, c, 1);
}

private int countHelper(int r, int c, int number){
  int count = 0;

}
