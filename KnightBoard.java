public class KnightBoard {
  private int[][] board;
  //array storing the moves of the board. My notes were converted basically.
  private int[][] moves = {{1, 2}, {1, -2}, {-1, 2}, {-1, -2}, {2, 1}, {2, -1}, {-2, 1}, {-2, -1}};
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
  if (r < 0 || r >= board.length || c < 0 || c >= board[0].length) {
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
  if (r < 0 || r > board.length || c < 0 || c > board[0].length){
    return false;
  }
  if (board[r][c] == 0) {
    return false;
  }
  board[r][c] = 0;
  return true;
}

public boolean solve(int r, int c){
  for (int i = 0; i < board.length; i++){
    for(int x = 0; x < board[0].length; x++){
      if (board[i][x] != 0) {
        throw new IllegalStateException();
      }
    }
  }
  if (r < 0 || r >= board.length || c < 0 || c >= board[0].length) {
    throw new IllegalArgumentException();
  }
  addKnight(r,c,1);
  return solveH(r, c, 2);
}

private boolean solveH(int r ,int c, int level){
  if (level == board.length * board[0].length + 1)
      return true;
    for (int i = 0; i < moves.length; i++) {
      if (addKnight(r+moves[i][0], c+moves[i][1], level)) {
        if (solveH(r+moves[i][0], c+moves[i][1], level+1)) {
          return true;
        }
        else removeKnight(r+moves[i][0], c+moves[i][1]);
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
  for (int i = 0; i < board.length; i++){
    for (int x = 0; x < board[0].length; x++){
      if (board[i][x] != 0){
        throw new IllegalStateException();
      }
    }
  }
  if (r < 0 || c < 0 || r >= board.length || c >= board[0].length){
    throw new IllegalArgumentException();
  }
  addKnight(r, c, 1);
  return countHelper(r, c, 2);
}

private int countHelper(int r, int c, int number){
  int count = 0;
  if (number == board.length * board[0].length + 1) {
    return 1;
  }

  for (int i = 0; i < moves.length; i++){
      if (addKnight(r + moves[i][0], c + moves[i][1], number)) {
        count += countHelper(r + moves[i][0], c + moves[i][1], number + 1);
        removeKnight(r + moves[i][0], c + moves[i][1]);
      }
    }
  return count;
}

public static void runTest(int i){

  KnightBoard b;
  int[]m =   {4,5,5,5,5};
  int[]n =   {4,5,4,5,5};
  int[]startx = {0,0,0,1,2};
  int[]starty = {0,0,0,1,2};
  int[]answers = {0,304,32,56,64};
  if(i >= 0 ){
    try{
      int correct = answers[i];
      b = new KnightBoard(m[i%m.length],n[i%m.length]);

      int ans  = b.countSolutions(startx[i],starty[i]);

      if(correct==ans){
        System.out.println("PASS board size: "+m[i%m.length]+"x"+n[i%m.length]+" "+ans);
      }else{
        System.out.println("FAIL board size: "+m[i%m.length]+"x"+n[i%m.length]+" "+ans+" vs "+correct);
      }
    }catch(Exception e){
      System.out.println("FAIL Exception case: "+i);

    }
  }
}

public static void main(String[] args) {
  runTest(0);
  runTest(1);
  runTest(2);
  runTest(3);
  runTest(4);
  runTest(5);
}
}
