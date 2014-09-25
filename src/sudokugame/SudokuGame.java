package sudokugame;

import javax.swing.*;

import playingfield.PlayingField;

public class SudokuGame extends JApplet
 {
  private final int WIDTH = 640;
  private final int HEIGHT = 480;

  public void init()
   {
	  PlayingField field = new PlayingField();
	  getContentPane().add(field);
      setSize(WIDTH,HEIGHT);       
   }
 }