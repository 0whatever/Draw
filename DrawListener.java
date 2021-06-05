package draw;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class DrawListener implements MouseListener,ActionListener,MouseMotionListener{
	Graphics2D g;
	Graphics2D g_save;
	//°´Å¥ÄÚÈÝ
	String btnstr,btndefault="painting";
	Color bkcolor=Color.WHITE,pcolor=Color.BLACK,scolor=Color.black;
	JButton btn;
	int x1,y1,x2,y2;
	int psize=1;
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		g.setColor(pcolor);
		g_save.setColor(pcolor);
		x2=e.getX();
		y2=e.getY()+70;
		if(btndefault.equals("painting")) {
			g.setStroke(new BasicStroke(psize));
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_DEFAULT);
			g.drawLine(x1, y1, x2, y2);
			g_save.setStroke(new BasicStroke(psize));
			g_save.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_DEFAULT);
			g_save.drawLine(x1, y1, x2, y2);
			x1=x2;
			y1=y2;
		}
		else if(btndefault.equals("rubber")) {
			g.setColor(bkcolor);
			g_save.setColor(bkcolor);
			g.setStroke(new BasicStroke(30));
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g.drawLine(x1, y1, x2, y2);
			g_save.setStroke(new BasicStroke(30));
			g_save.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g_save.drawLine(x1, y1, x2, y2);
			x1=x2;
			y1=y2;
			g.setColor(pcolor);
			g_save.setColor(pcolor);
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		btnstr=e.getActionCommand();
		btndefault=btnstr;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		x1=e.getX();
		y1=e.getY()+70;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		g.setColor(scolor);
		g_save.setColor(scolor);
		x2=e.getX();
		y2=e.getY()+70;
		if(btndefault.equals("line")) {
			g.setStroke(new BasicStroke(psize));
			g.drawLine(x1, y1, x2, y2);
			g_save.setStroke(new BasicStroke(psize));
			g_save.drawLine(x1, y1, x2, y2);
		}
		else if(btndefault.equals("rec")) {
			g.setStroke(new BasicStroke(psize));
			g.fillRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2-x1), Math.abs(y2-y1));
			g_save.setStroke(new BasicStroke(psize));
			g_save.fillRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2-x1), Math.abs(y2-y1));
		}
		else if(btndefault.equals("cir")) {
			g.setStroke(new BasicStroke(psize));
			g.fillOval(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2-x1), Math.abs(y2-y1));
			g_save.setStroke(new BasicStroke(psize));
			g_save.fillOval(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2-x1), Math.abs(y2-y1));
		}
		else if(btndefault.equals("arc")) {
			g.setStroke(new BasicStroke(psize));
			g.drawArc(x1, y1, 100, 60, 0, 180);
			g_save.setStroke(new BasicStroke(psize));
			g_save.drawArc(x1, y1, 100, 60, 0, 180);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
