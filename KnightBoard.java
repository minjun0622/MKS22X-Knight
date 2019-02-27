public class KnightBoard {
  private int[][] board;
  //array storing the moves of the board. My notes were converted basically.
  private int[] moves{2,1, 1,2, -2,-1, -1,-2, -2,1, 2,-1, -1,2, 1,-2};
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

//if the dimensions of the board are odd, then it means that it will not be a closed tour.
//@throws IllegalArgumentException when either parameter is <= 0.
public KnightBoard(int r, int c){
  if (r <= 0 || c <= 0){
    throw new IllegalArgumentException();
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
  public String toString() {
     String result = "";
     for (int r = 0; r < board.length; r++) {
       for (int c = 0; c < board[0].length; c++) {
         if (board[r][c] < 10) {
           result += " " + board[r][c] + " ";
         }
         else result += board[r][c] + " ";
       }
       result += "\n";
     }
     return result;
   }


/*
Modifies the board by labeling the moves from 1 (at startingRow,startingCol) up to the area of the board in proper knight move steps.
@throws IllegalStateException when the board contains non-zero values.
@throws IllegalArgumentException when either parameter is negative
 or out of bounds.
@returns true when the board is solvable from the specified starting position
*/

//similar fashion to queens. kinda easy.
private boolean addKnight(int r, int c, int number){
  if (r < 0 || r >= board.length || c < 0 || c >= board.length) {
    return false;
  }
  if (board[r][c] != 0){
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
    throw new IllegalArgumentException();
  }
  for (int i = 0; i < board.length; i++){
    for(int x = 0; x < board.length; x++){
      if (board[i][x] != 0) {
        throw new IllegalStateException();
      }
    }
  }
  return solveH(r, c, 1);
}

private boolean solveH(int r ,int c, int level){
  if (level == board.length * board[0].length){
    return true;
  }
  for (int i = 0; i < board.length; i++) {
      if (addKnight(r + moves[i], c + moves[i + 1], level + 1)) {
        if (solveH(r + moves[i], c + moves[i + 1], level + 1)) {
          return true;
        }
      else {
      removeKnight(r + moves[i], c + moves[i + 1], level + 1);
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
    throw new IllegalArgumentException();
  }
  for (int i = 0; i < board.length; i++){
    for (int x = 0; x < board[i].length; x++){
      if (board[i][x] != 0){
        throw new IllegalStateException();
      }
    }
  }
  return countHelper(r, c, 1);
}

private int countHelper(int r, int c, int number){
  int count = 0;
  if (number == board.length * board.length + 1){
    return 1;
  }
  for (int i = 0; i < board.length; i++){
    for (int x = 0; x < board.length; x++){
      if (addKnight(r, c, number)) {
        count += countHelper(r + moves[i][x], c + moves[i][x + 1], number + 1);
        removeKnight(r, c);
      }
    }
  }
  return count;
}

}
