package connect4;


/* MouseMotionListener will enable you to performactions when the mouse moves over 
a hotspot. You can also use this to drag things,that will require MouseListener and 
MouseMotionListener at the same time. 
This applet will show a rectangle that will changecolor when the mouse moves over it. 
It's basically the same as the previous one. 
*/
import java.awt.*; 
import java.applet.*; 
// import an extra class for the MouseMotionListener 
import java.awt.event.*;

// Tells the applet you will be using the MouseMotionListenermethods.

public class MouseMotionExample extends Applet implements MouseMotionListener 
{ 
 // The X-coordinate and Y-coordinate ofthe last Mouse Position. 
     int xpos; 
     int ypos;

     int rect1xco,rect1yco,rect1width,rect1height;

 // wll be true when the Mouse is in theRectangle 
     boolean rect1Active;

     public void init()  
     {

          rect1xco = 20; 
          rect1yco = 20; 
          rect1width =100; 
          rect1height= 50;

  // Add the MouseMotionListener to yourapplet 
      addMouseMotionListener(this); 
     }

     public void paint(Graphics g)  
     { 
  // Rectangle's color 
  // If mouse is in the rectangle then setColor to green 
  // else to red. 
          if (rect1Active)g.setColor(Color.green); 
          else g.setColor(Color.red);

          g.fillRect(rect1xco,rect1yco,rect1width,rect1height);

          g.setColor(Color.blue);

  // This will show the coordinates of themouse 
  // at the place of the mouse. 
          g.drawString("("+xpos+","+ypos+")",xpos,ypos);

     }

/* If you use MouseMotionListener then these methodshave to be here 
 public void mouseMoved(MouseEvent me); 
 public void mouseDragged(MouseEvent me); 
*/

 // This will be excuted whenever the mousemoves in the applet 
     public void mouseMoved(MouseEvent me)  
     {  
          xpos = me.getX(); 
          ypos = me.getY(); 
  // Check if the mouse is in the rectangle 
         if (xpos > rect1xco&& xpos < rect1xco+rect1width && ypos > rect1yco  
        && ypos < rect1yco+rect1height)  
               rect1Active = true; 
          else  
              rect1Active = false; 
  //show the results of the motion 
          repaint();

     }

 // This is works like mouseMoved but onlywhen the mouse is being pressed 
 // at the same time. 
 // To use this for drawing rectangles likein Paint programs 
 // you will have to use mousePressed toremember the first coordinates. 
     public void mouseDragged(MouseEvent me)  
     { 
     }

/* It's quite easy, but this isn't a good exampleof programming. 
You can notice that the applet flickers, thisis because it is not double-buffered 
You will see this later. 
*/

  } 
