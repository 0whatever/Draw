package draw;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

class myMenuBar extends JMenuBar{
	myMenuBar(BufferedImage img){
		JButton savebtn=new JButton("save");
		//È¥½¹µã¿ò
		savebtn.setFocusPainted(false);
		this.setBackground(new Color(218,230,242));
		savebtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser f=new JFileChooser("save");
				int value=f.showSaveDialog(null);
				File file;
				if(value==JFileChooser.APPROVE_OPTION) {
					file=f.getSelectedFile();
					String name=f.getName(file);
					try {
						ImageIO.write(img, "PNG", new File(f.getCurrentDirectory(),name+".png"));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			
		});
		this.add(savebtn);
	}
	
}