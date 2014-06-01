import java.util.Random;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.*;
import javax.swing.JPanel;
import javax.swing.JViewport;
import javax.swing.Spring;
import javax.swing.SpringLayout;
import java.awt.BorderLayout;
public class Board{

  private char[][] board = {
    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},  
  };
  
  private char[][] opponentBoard = {
    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},  
  };
  
  private char[][] hitBoard = {
    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},  
  };
  
  private Random rand = new Random();
  public int[] shipLength = {5, 4, 4, 3, 2};
  
  public playerDestroyer playerDestroyer = new playerDestroyer();
  public playerBattleship playerBattleship = new playerBattleship();
  public playerAircraft playerAircraft = new playerAircraft();
  public playerRow playerRow = new playerRow();
  public playerSubmarine playerSubmarine = new playerSubmarine();
  
  public opponentDestroyer opponentDestroyer = new opponentDestroyer();
  public opponentBattleship opponentBattleship = new opponentBattleship();
  public opponentAircraft opponentAircraft = new opponentAircraft();
  public opponentRow opponentRow = new opponentRow();
  public opponentSubmarine opponentSubmarine = new opponentSubmarine();

  
  public Board(){
     makeBoard();
     makeOpponentBoard();
  }
    
  public void makeBoard(){

    //Creating player aircraft

       if(Math.random() >= .5){
         int xC = rand.nextInt(10);
         int xR = rand.nextInt(10 - 5);
         int[] xRs = new int[5];
         int[] xCs = {xC, xC, xC, xC, xC};
         for(int y = 0; y < 5; y++){
           board[xR+y][xC] = 'X';
           xRs[y] = xR+y;
         }
         playerAircraft.coords(xRs, xCs);
       }else{
         int xC = rand.nextInt(10 - 5);
         int xR = rand.nextInt(10);
         int[] xRs = {xR, xR, xR, xR, xR};
         int[] xCs = new int[5];
         for(int y = 0; y < 5; y++){
           board[xR][xC+y] = 'X';
           xCs[y] = xC+y;
         }
         playerAircraft.coords(xRs, xCs);
       }
    
       
       //creating player battleship
       if(Math.random() >= .5){
         int xC = rand.nextInt(10);
         int xR = rand.nextInt(10 - 4); 
         while(taken(xC, xR)){
            xC = rand.nextInt(10);
            xR = rand.nextInt(10 - 4);
         }
            int[] xRs = new int[4];
            int[] xCs = {xC, xC, xC, xC};
         for(int y = 0; y < 4; y++){
           board[xR+y][xC] = 'X';
           xRs[y] = xR+y;
         }
         playerBattleship.coords(xRs, xCs);
       }else{
             int xC = rand.nextInt(10 - 4);
             int xR = rand.nextInt(10);
         
         while(taken(xC, xR)){
            xC = rand.nextInt(10 - 4);
            xR = rand.nextInt(10);
         }

         int[] xRs = {xR, xR, xR, xR};
         int[] xCs = new int[4];
         for(int y = 0; y < 4; y++){
           board[xR][xC+y] = 'X';
           xCs[y] = xC+y;
         }
         playerBattleship.coords(xRs, xCs);
       }    
       
       //creating player destroyer
       if(Math.random() >= .5){
         int xC = rand.nextInt(10);
         int xR = rand.nextInt(10 - 3); 
         while(taken(xC, xR)){
            xC = rand.nextInt(10);
            xR = rand.nextInt(10 - 3);
         }
            int[] xRs = new int[3];
            int[] xCs = {xC, xC, xC};
         for(int y = 0; y < 3; y++){
           board[xR+y][xC] = 'X';
           xRs[y] = xR+y;
         }
         playerDestroyer.coords(xRs, xCs);
       }else{
             int xC = rand.nextInt(10 - 3);
             int xR = rand.nextInt(10);
         
         while(taken(xC, xR)){
            xC = rand.nextInt(10 - 3);
            xR = rand.nextInt(10);
         }

         int[] xRs = {xR, xR, xR};
         int[] xCs = new int[3];
         for(int y = 0; y < 3; y++){
           board[xR][xC+y] = 'X';
           xCs[y] = xC+y;
         }
         playerDestroyer.coords(xRs, xCs);
       }    
       
       //creating player rowboat
       if(Math.random() >= .5){
         int xC = rand.nextInt(10);
         int xR = rand.nextInt(10 - 2); 
         while(taken(xC, xR)){
            xC = rand.nextInt(10);
            xR = rand.nextInt(10 - 2);
         }
            int[] xRs = new int[2];
            int[] xCs = {xC, xC};
         for(int y = 0; y < 2; y++){
           board[xR+y][xC] = 'X';
           xRs[y] = xR+y;
         }
         playerRow.coords(xRs, xCs);
       }else{
             int xC = rand.nextInt(10 - 2);
             int xR = rand.nextInt(10);
         
         while(taken(xC, xR)){
            xC = rand.nextInt(10 - 2);
            xR = rand.nextInt(10);
         }

         int[] xRs = {xR, xR};
         int[] xCs = new int[2];
         for(int y = 0; y < 2; y++){
           board[xR][xC+y] = 'X';
           xCs[y] = xC+y;
         }
         playerRow.coords(xRs, xCs);
       }    
       
       //creating player submarine
       if(Math.random() >= .5){
         int xC = rand.nextInt(10);
         int xR = rand.nextInt(10 - 3); 
         while(taken(xC, xR)){
            xC = rand.nextInt(10);
            xR = rand.nextInt(10 - 3);
         }
            int[] xRs = new int[3];
            int[] xCs = {xC, xC, xC};
         for(int y = 0; y < 3; y++){
           board[xR+y][xC] = 'X';
           xRs[y] = xR+y;
         }
         playerSubmarine.coords(xRs, xCs);
       }else{
             int xC = rand.nextInt(10 - 3);
             int xR = rand.nextInt(10);
         
         while(taken(xC, xR)){
            xC = rand.nextInt(10 - 3);
            xR = rand.nextInt(10);
         }

         int[] xRs = {xR, xR, xR};
         int[] xCs = new int[3];
         for(int y = 0; y < 3; y++){
           board[xR][xC+y] = 'X';
           xCs[y] = xC+y;
         }
         playerSubmarine.coords(xRs, xCs);
       }
  }
   public void makeOpponentBoard(){

    //Creating opponent aircraft

       if(Math.random() >= .5){
         int xC = rand.nextInt(10);
         int xR = rand.nextInt(10 - 5);
         int[] xRs = new int[5];
         int[] xCs = {xC, xC, xC, xC, xC};
         for(int y = 0; y < 5; y++){
           opponentBoard[xR+y][xC] = 'X';
           xRs[y] = xR+y;
         }
         opponentAircraft.coords(xRs, xCs);
       }else{
         int xC = rand.nextInt(10 - 5);
         int xR = rand.nextInt(10);
         int[] xRs = {xR, xR, xR, xR, xR};
         int[] xCs = new int[5];
         for(int y = 0; y < 5; y++){
           opponentBoard[xR][xC+y] = 'X';
           xCs[y] = xC+y;
         }
         opponentAircraft.coords(xRs, xCs);
       }
    
       
       //creating opponent battleship
       if(Math.random() >= .5){
         int xC = rand.nextInt(10);
         int xR = rand.nextInt(10 - 4); 
         while(opponentTaken(xC, xR)){
            xC = rand.nextInt(10);
            xR = rand.nextInt(10 - 4);
         }
            int[] xRs = new int[4];
            int[] xCs = {xC, xC, xC, xC};
         for(int y = 0; y < 4; y++){
           opponentBoard[xR+y][xC] = 'X';
           xRs[y] = xR+y;
         }
         opponentBattleship.coords(xRs, xCs);
       }else{
             int xC = rand.nextInt(10 - 4);
             int xR = rand.nextInt(10);
         
         while(opponentTaken(xC, xR)){
            xC = rand.nextInt(10 - 4);
            xR = rand.nextInt(10);
         }

         int[] xRs = {xR, xR, xR, xR};
         int[] xCs = new int[4];
         for(int y = 0; y < 4; y++){
           opponentBoard[xR][xC+y] = 'X';
           xCs[y] = xC+y;
         }
         opponentBattleship.coords(xRs, xCs);
       }    
       
       //creating opponent destroyer
       if(Math.random() >= .5){
         int xC = rand.nextInt(10);
         int xR = rand.nextInt(10 - 3); 
         while(opponentTaken(xC, xR)){
            xC = rand.nextInt(10);
            xR = rand.nextInt(10 - 3);
         }
            int[] xRs = new int[3];
            int[] xCs = {xC, xC, xC};
         for(int y = 0; y < 3; y++){
           opponentBoard[xR+y][xC] = 'X';
           xRs[y] = xR+y;
         }
         opponentDestroyer.coords(xRs, xCs);
       }else{
             int xC = rand.nextInt(10 - 3);
             int xR = rand.nextInt(10);
         
         while(opponentTaken(xC, xR)){
            xC = rand.nextInt(10 - 3);
            xR = rand.nextInt(10);
         }

         int[] xRs = {xR, xR, xR};
         int[] xCs = new int[3];
         for(int y = 0; y < 3; y++){
           opponentBoard[xR][xC+y] = 'X';
           xCs[y] = xC+y;
         }
         opponentDestroyer.coords(xRs, xCs);
       }    
       
       //creating opponent rowboat
       if(Math.random() >= .5){
         int xC = rand.nextInt(10);
         int xR = rand.nextInt(10 - 2); 
         while(opponentTaken(xC, xR)){
            xC = rand.nextInt(10);
            xR = rand.nextInt(10 - 2);
         }
            int[] xRs = new int[2];
            int[] xCs = {xC, xC};
         for(int y = 0; y < 2; y++){
           opponentBoard[xR+y][xC] = 'X';
           xRs[y] = xR+y;
         }
         opponentRow.coords(xRs, xCs);
       }else{
             int xC = rand.nextInt(10 - 2);
             int xR = rand.nextInt(10);
         
         while(opponentTaken(xC, xR)){
            xC = rand.nextInt(10 - 2);
            xR = rand.nextInt(10);
         }

         int[] xRs = {xR, xR};
         int[] xCs = new int[2];
         for(int y = 0; y < 2; y++){
           opponentBoard[xR][xC+y] = 'X';
           xCs[y] = xC+y;
         }
         opponentRow.coords(xRs, xCs);
       }    
       
       //creating opponent submarine
       if(Math.random() >= .5){
         int xC = rand.nextInt(10);
         int xR = rand.nextInt(10 - 3); 
         while(opponentTaken(xC, xR)){
            xC = rand.nextInt(10);
            xR = rand.nextInt(10 - 3);
         }
            int[] xRs = new int[3];
            int[] xCs = {xC, xC, xC};
         for(int y = 0; y < 3; y++){
           opponentBoard[xR+y][xC] = 'X';
           xRs[y] = xR+y;
         }
         opponentSubmarine.coords(xRs, xCs);
       }else{
             int xC = rand.nextInt(10 - 3);
             int xR = rand.nextInt(10);
         
         while(opponentTaken(xC, xR)){
            xC = rand.nextInt(10 - 3);
            xR = rand.nextInt(10);
         }

         int[] xRs = {xR, xR, xR};
         int[] xCs = new int[3];
         for(int y = 0; y < 3; y++){
           opponentBoard[xR][xC+y] = 'X';
           xCs[y] = xC+y;
         }
         opponentSubmarine.coords(xRs, xCs);
       }
  }
  
  public void printBoard(){
     System.out.println(boardToText());
  } 
  
  public void printOpponentBoard(){
  
    for(int i = 0; i < opponentBoard.length; i++){
      for(int j = 0; j < opponentBoard[i].length; j++){
        System.out.print(opponentBoard[i][j]);
        if(j < opponentBoard[i].length - 1) System.out.print(" ");
      }
      System.out.println();
    }
  
  }
  
  public boolean taken(int x, int y){
     if(board[x][y] == 'X')
       return true;
     else
       return false;
  }  
  public boolean opponentTaken(int x, int y){
     if(opponentBoard[x][y] == 'X')
       return true;
     else
       return false;
  }

  public String boardToText(){
    String toReturn = "";
    toReturn += ("   MY BOARD  \n");
    toReturn += ("  0123456789\n");
    for(int i = 0; i < board.length; i++){
      for(int j = 0; j < board[i].length; j++){
        if(j == 0)
          toReturn += (i+"|");
        toReturn += (board[i][j]);
        if(j < board[i].length - 1) toReturn += ("");
      }
      toReturn += ("\n");
    }
    return toReturn;
  }
  public String hitBoardToText(){
    String toReturn = "";
    toReturn += ("  HIT BOARD  \n");
    toReturn += ("  0123456789\n");
    for(int i = 0; i < hitBoard.length; i++){
      for(int j = 0; j < hitBoard[i].length; j++){
        if(j == 0)
          toReturn += (i+"|");
        toReturn += (hitBoard[i][j]);
        if(j < hitBoard[i].length - 1) toReturn += ("");
      }
      toReturn += ("\n");
    }
    return toReturn;
  }
  
  public void fire(int y, int x){
    if(opponentBoard[x][y] == 'O'){
       JOptionPane.showMessageDialog(null, "You have already fired at this location");
    }else if(opponentTaken(x, y)){
       hitBoard[x][y] = 'X';
       opponentBoard[x][y] = 'O';
       String errorMessage = "Hit!\n";
       printOpponentBoard();
       if(opponentAircraft.ifHit(x, y)){
         errorMessage += "You hit an Aircraft Carrier!";
       }else if(opponentBattleship.ifHit(x, y)){
         errorMessage += "You hit a Battleship!";
       }else if(opponentDestroyer.ifHit(x, y)){
         errorMessage += "You hit a Destroyer!";
       }else if(opponentRow.ifHit(x, y)){
         errorMessage += "You hit a Row Boat!";
       }else if(opponentSubmarine.ifHit(x, y)){
         errorMessage += "You hit a Submarine!";
       }
       JOptionPane.showMessageDialog(null, errorMessage);
    }else{
       opponentBoard[x][y] = 'O';
       hitBoard[x][y] = 'O';
       JOptionPane.showMessageDialog(null, "Miss!");
    }
  }

}