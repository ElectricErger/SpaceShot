/*package spaceShot;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

public class Controller {
	
	Controller(){
		
	}
	
	view.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "Move Up");
	view.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "Move Down");
	view.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "Move Left");
	view.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "Move Right");
	view.getRootPane().getActionMap().put("EXIT", new AbstractAction(){ 
		        public void actionPerformed(ActionEvent e){
		            window.dispose();
		        }
		    }
	);
	view.getActionMap().put("Move Up", new AbstractAction(){
		public void actionPerformed(ActionEvent e){
			ship.updatePosition(0, -sensitive); ship.repaint();
		}
	}
	);		
	view.getActionMap().put("Move Down", new AbstractAction(){
		public void actionPerformed(ActionEvent e){
			ship.updatePosition(0, sensitive); ship.repaint();
		}
	}
	);		
	view.getActionMap().put("Move Left", new AbstractAction(){
		public void actionPerformed(ActionEvent e){
			ship.updatePosition(-sensitive, 0); ship.repaint();
		}
	}
	);		
	v.getActionMap().put("Move Right", new AbstractAction(){
		public void actionPerformed(ActionEvent e){
			ship.updatePosition(sensitive, 0); ship.repaint();
		}
	}
	);
}
*/