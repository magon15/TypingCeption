import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class Typing{

ArrayList<Character> typedLetters;
ArrayList<Long> typedTime;
Calendar cal;
long time = 0;
boolean typedAlready = false;
long currentTime;
char letter;
int counter;
JTextField text; 

public static void main(String[] args){
Typing type = new Typing();
type.setGui();
type.startOrReset();
}

public void startOrReset(){
System.out.println("");
time = 0;
typedAlready = false;
typedLetters = new ArrayList<Character>();
typedTime = new ArrayList<Long>();
}

public void countTime(){

cal = Calendar.getInstance();	
if (typedAlready == false){
		typedLetters.add(letter);
		typedTime.add(time);
		time = cal.getTimeInMillis();
	     currentTime=time;
		typedAlready = true;

	}
	else{
	currentTime = cal.getTimeInMillis() - currentTime;
      typedLetters.add(letter);
	typedTime.add(currentTime);
     currentTime = cal.getTimeInMillis();
	}


}

public void setGui(){
JFrame frame = new JFrame();
frame.setSize(500,500);

text = new JTextField(20);
text.addKeyListener(new KeyPressListener());

JButton stop = new JButton("Start!");
stop.addActionListener(new StartListener());

frame.getContentPane().add(BorderLayout.CENTER, text);
frame.getContentPane().add(BorderLayout.SOUTH, stop);
frame.setVisible(true);
}

public class KeyPressListener implements KeyListener{
public void keyPressed(KeyEvent e){
if (e.getKeyCode() == 20 || e.getKeyCode() == 16){
}
else{
letter = e.getKeyChar();
countTime();
}   
}
public void keyReleased(KeyEvent e){}
public void keyTyped(KeyEvent e){}
}

public class StartListener implements ActionListener{
public void actionPerformed(ActionEvent e){
text.setText("");
for(int i = 0; i < typedLetters.size(); i++){
try{
Thread.sleep(typedTime.get(i));
}catch(InterruptedException ex){ex.printStackTrace();}
System.out.print(typedLetters.get(i));
}
System.out.println("");
startOrReset();
}
}

}