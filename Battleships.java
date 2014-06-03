import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Toolkit;
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

public class Battleships extends JFrame implements ActionListener{

  JButton fireB = new JButton("Fire!");
  JButton nextB = new JButton("End Turn");
  
  JLabel xCoord = new JLabel("Missle Launch X Coordinate (0-9): ");
  JLabel yCoord = new JLabel("Missle Launch Y Coordinate (0-9): ");
  
  JLabel userTitle = new JLabel("User Info");
  JLabel opponentTitle = new JLabel("Opponent Info");
  
  JLabel HorizontalSpacer = new JLabel("_________________________________");
  JLabel HorizontalSpacer2 = new JLabel("_________________________________");
  
  JLabel userAircraftCarrier = new JLabel("My Aircraft Carrier Life:  5/5");
  JLabel userBattleship =      new JLabel("My Battleship Life:          4/4");
  JLabel userSubmarine =       new JLabel("My Submarine Life:         3/3");
  JLabel userDestroyer =       new JLabel("My Destroyer Life:         3/3");
  JLabel userRowBoat =         new JLabel("My Row Boat Life:           2/2");

  JLabel opponentAircraftCarrier = new JLabel("Opponent Aircraft Life:       5/5");
  JLabel opponentBattleship =      new JLabel("Opponent Battleship Life:   4/4");
  JLabel opponentSubmarine =       new JLabel("Opponent Submarine Life:  3/3");
  JLabel opponentDestroyer =       new JLabel("Opponent Destroyer Life:  3/3");
  JLabel opponentRowBoat =         new JLabel("Opponent Row Boat Life:    2/2");

  JTextArea gameBoard = new JTextArea(12, 12);
  JTextArea hitBoard = new JTextArea(12, 12);
  
  JTextField enterX = new JTextField(2);
  JTextField enterY = new JTextField(2);

  JPanel userInfo = new JPanel();
  
  Board board = new Board();

//  JViewport viewport = new JViewport();

  public Battleships() {
    
    super("Battleships!");
    
    setSize(875, 375);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    userTitle.setFont(new Font("Sans Serif", Font.BOLD, 30));
    gameBoard.setFont(new Font("Courier", Font.PLAIN, 25));
    hitBoard.setFont(new Font("Courier", Font.PLAIN, 25));
    opponentTitle.setFont(new Font("Sans Serif", Font.BOLD, 30));
    
    userInfo.setLayout(new GridLayout(9, 2));

    opponentTitle.setHorizontalAlignment(SwingConstants.CENTER);
    userTitle.setHorizontalAlignment(SwingConstants.CENTER);
    userAircraftCarrier.setHorizontalAlignment(SwingConstants.CENTER);
    opponentAircraftCarrier.setHorizontalAlignment(SwingConstants.CENTER);    
    userBattleship.setHorizontalAlignment(SwingConstants.CENTER);
    opponentBattleship.setHorizontalAlignment(SwingConstants.CENTER);    
    userSubmarine.setHorizontalAlignment(SwingConstants.CENTER);
    opponentSubmarine.setHorizontalAlignment(SwingConstants.CENTER);    
    userRowBoat.setHorizontalAlignment(SwingConstants.CENTER);
    opponentRowBoat.setHorizontalAlignment(SwingConstants.CENTER);
    HorizontalSpacer.setHorizontalAlignment(SwingConstants.CENTER);
    HorizontalSpacer2.setHorizontalAlignment(SwingConstants.CENTER);
    xCoord.setHorizontalAlignment(SwingConstants.CENTER);
    yCoord.setHorizontalAlignment(SwingConstants.CENTER);
    opponentDestroyer.setHorizontalAlignment(SwingConstants.CENTER);
    userDestroyer.setHorizontalAlignment(SwingConstants.CENTER);
    
    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (int) ((dimension.getWidth() - getWidth()) / 2);
    int y = (int) ((dimension.getHeight() - getHeight()) / 2);
    setLocation(x, y);
    
    userInfo.add(userTitle);
    userInfo.add(opponentTitle);
    userInfo.add(userAircraftCarrier);
    userInfo.add(opponentAircraftCarrier);    
    userInfo.add(userBattleship);
    userInfo.add(opponentBattleship);    
    userInfo.add(userSubmarine);
    userInfo.add(opponentSubmarine);     
    userInfo.add(userDestroyer);
    userInfo.add(opponentDestroyer);    
    userInfo.add(userRowBoat);
    userInfo.add(opponentRowBoat);
    userInfo.add(HorizontalSpacer);
    userInfo.add(HorizontalSpacer2);

    userInfo.add(xCoord);
    userInfo.add(enterX);
    userInfo.add(yCoord);   
    userInfo.add(enterY);
    
    Container c = getContentPane();
    c.setLayout(new BorderLayout());
    c.add(hitBoard, BorderLayout.EAST);
    c.add(fireB, BorderLayout.SOUTH);
    c.add(userInfo, BorderLayout.CENTER);
    c.add(gameBoard, BorderLayout.WEST);
    gameBoard.setEditable(false);
    hitBoard.setEditable(false);

    fireB.addActionListener(this);
    nextB.addActionListener(this);
    gameBoard.setText(board.boardToText());
    hitBoard.setText(board.hitBoardToText());
    setVisible(true);
    board.printOpponentBoard();
  }

  public static void main(String args[]) {
    new Battleships();
  }
  
  
  public void actionPerformed(ActionEvent e) { 
    if(e.getSource() == fireB){
       board.fire(Integer.parseInt(enterX.getText()), Integer.parseInt(enterY.getText()));
       refresh();
    }
    board.computerTurn();
    refresh();
  } 
  
  public void refresh(){
    gameBoard.setText(board.boardToText());
    hitBoard.setText(board.hitBoardToText());
      
    userAircraftCarrier.setText("My Aircraft Carrier Life:  " + board.playerAircraft.getHealth() + "/" + board.playerAircraft.getHealthCapacity());
    userBattleship.setText("My Battleship Life:          " + board.playerBattleship.getHealth() + "/" + board.playerBattleship.getHealthCapacity());
    userSubmarine.setText("My Submarine Life:         " + board.playerSubmarine.getHealth() + "/" + board.playerSubmarine.getHealthCapacity());
    userDestroyer.setText("My Destroyer Life:        " + board.playerDestroyer.getHealth() + "/" + board.playerDestroyer.getHealthCapacity());
    userRowBoat.setText("My Row Boat Life:           " + board.playerRow.getHealth() + "/" + board.playerRow.getHealthCapacity());

    opponentAircraftCarrier.setText("Opponent Aircraft Life:       " + board.opponentAircraft.getHealth() + "/" + board.opponentAircraft.getHealthCapacity());
    opponentBattleship.setText("Opponent Battleship Life:   " + board.opponentBattleship.getHealth() + "/" + board.opponentBattleship.getHealthCapacity());
    opponentSubmarine.setText("Opponent Submarine Life:  " + board.opponentSubmarine.getHealth() + "/" + board.opponentSubmarine.getHealthCapacity());
    opponentDestroyer.setText("Opponent Destroyer Life:  " + board.opponentDestroyer.getHealth() + "/" + board.opponentDestroyer.getHealthCapacity());
    opponentRowBoat.setText("Opponent Row Boat Life:    " + board.opponentRow.getHealth() + "/" + board.opponentRow.getHealthCapacity());
    
    if(board.playerAircraft.getHealth() + 
       board.playerBattleship.getHealth() + 
       board.playerSubmarine.getHealth() + 
       board.playerDestroyer.getHealth() + 
       board.playerRow.getHealth() == 0){
       JOptionPane.showMessageDialog(null, "You Lose!\nAll your ships have sunk.");
       System.exit(0);
    }else if(board.opponentAircraft.getHealth() + 
       board.opponentBattleship.getHealth() + 
       board.opponentSubmarine.getHealth() + 
       board.opponentDestroyer.getHealth() + 
       board.opponentRow.getHealth() == 0){
       JOptionPane.showMessageDialog(null, "You Win!\nYou have sunk all your opponents ships.");
       System.exit(0);
    }
    
    
    repaint();
  }
  
}




           
         
    
    
    
    
    