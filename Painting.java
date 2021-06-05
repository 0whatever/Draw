package draw;


import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Painting extends JFrame{
	Color bkcolor=Color.WHITE,pcolor=Color.BLACK,scolor=Color.black,tcolor=Color.BLACK;
	JTextArea text = new JTextArea();
	int click_text=0,psize=1,s_font;
	public void init() {	
		BufferedImage img=new BufferedImage(1100,1000,BufferedImage.TYPE_INT_ARGB);
		Graphics2D g=img.createGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0,0, 1100, 1000);
		Container container=this.getContentPane();
		DrawListener draw=new DrawListener();
		//工具栏部分
		JPanel tool=new JPanel();
		tool.setLayout(new GridLayout());
		String[] name= {"painting","shape","bk_color","text","rubber"};
		JButton[]btn=new JButton[5];
		
		Label label=new Label("choose mode");
		Font font=new Font("Calibri",Font.BOLD,22);
		label.setFont(font);
		tool.setBackground(new Color(218,230,242));
		tool.add(label);
		for(int i=0;i<5;i++) {
			btn[i]=new JButton(name[i]);
			btn[i].setFocusable(false);
			btn[i].setBackground(new Color(218,230,242));
			btn[i].setFont(new Font("Calibri",30,25));
			tool.add(btn[i]);
		}
		
		//画板
		JPanel drawpanel=new JPanel();
		drawpanel.setBackground(Color.WHITE);
		drawpanel.setLayout(null);
		drawpanel.addMouseListener(draw);
		drawpanel.addMouseMotionListener(draw);
		bkcolor=Color.WHITE;
		drawpanel.setPreferredSize(new Dimension(1100,1000));
		container.add(drawpanel, BorderLayout.CENTER);
		drawpanel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(click_text==1) {
					//预备组件→不可
					text=new JTextArea();
					text.setLineWrap(true);
					text.setForeground(tcolor);
					JScrollPane scroll=new JScrollPane( text,
			                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
			                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
					drawpanel.add(scroll);
					scroll.setBorder(BorderFactory.createDashedBorder(Color.BLACK));
					scroll.setBackground(Color.WHITE);
					scroll.setBounds(e.getX(),e.getY(),200,150);
					click_text=0;
				}
			}
		});
		
		//右侧弹窗工具栏→3个   按钮点击设置可见
		JTextField s=new JTextField(String.valueOf(psize));
		s.setPreferredSize(new Dimension(40,25));
		s.setEditable(false);
		s.setFont(font);
		JButton pbtn=new JButton("choose color");
		pbtn.setBackground(new Color(218,230,242));
		pbtn.setFocusable(false);
		pbtn.setFont(font);
		pbtn.addActionListener(new ActionListener() {
				@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				pcolor=JColorChooser.showDialog(drawpanel, "", null);
				if(pcolor==null)
					return;
				//设置画笔颜色
				draw.pcolor=pcolor;
			}
			
		});	
		//画笔粗细
		JSlider size=new JSlider(1,100,3);
		size.setMajorTickSpacing(5);
		size.setMinorTickSpacing(1);
		size.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				psize=size.getValue();
				draw.psize=psize;
				s.setText(String.valueOf(psize));
			}
			
		});
		//形状颜色
		JButton scbtn=new JButton("choose shape color");
		scbtn.setBackground(new Color(218,230,242));
		scbtn.setFocusable(false);
		scbtn.setFont(font);
		scbtn.addActionListener(new ActionListener() {
				@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				scolor=JColorChooser.showDialog(drawpanel, "", null);
				if(scolor==null)
					return;
				draw.scolor=scolor;
			}
			
		});	
	//text
		JButton tbtn=new JButton("choose color");
		tbtn.setBackground(new Color(218,230,242));
		tbtn.setFocusable(false);
		tbtn.setFont(font);
		tbtn.addActionListener(new ActionListener() {
				@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				tcolor=JColorChooser.showDialog(drawpanel, "", null);
				if(tcolor==null)
					return;
			}
			
		});	
		Integer[] num=new Integer[20];
		num[0]=10;
		for(int i=0;i<19;i++)
			num[i+1]=num[i]+6;
		JComboBox<Integer> fsize=new JComboBox<>(num);
		fsize.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange()==ItemEvent.SELECTED)
					s_font=(int) fsize.getSelectedItem();
				text.setFont(new Font("宋体",Font.ITALIC,s_font));
			}
			
		});
		fsize.setSelectedIndex(1);
		
				//painting: color 和粗细
		JPanel p_tool =new JPanel();
		p_tool.setPreferredSize(new Dimension(400,1000));
		JPanel up=new JPanel();
		up.add(pbtn);
		JPanel ne=new JPanel();
		JLabel labl=new JLabel("thin&thick");
		labl.setFont(font);
		ne.add(labl);
		ne.add(size);
		ne.add(s);
		p_tool.add(up);
		p_tool.add(ne);
		
		
				//shape: content and border color
		JPanel s_tool =new JPanel();
		s_tool.setPreferredSize(new Dimension(400,1000));
		JButton[]ss=new JButton[4];
		String []s_name= {"rec","cir","line","arc"};
		JPanel top=new JPanel();
		JPanel next=new JPanel();
		for(int i=0;i<4;i++) {
			ss[i]=new JButton(s_name[i]);
			ss[i].setBackground(new Color(218,230,242));
			ss[i].setFocusable(false);
			ss[i].setFont(font);
			top.add(ss[i]);
			ss[i].addActionListener(draw);
		}
		next.add(scbtn);
		s_tool.add(top);
		s_tool.add(next);
				//text: color size
		JPanel t_tool =new JPanel();
		t_tool.setPreferredSize(new Dimension(400,1000));
		t_tool.add(tbtn);
		t_tool.add(fsize);
		
		//底部背景色显示
		JPanel bkpanel=new JPanel();
		bkpanel.setPreferredSize(new Dimension(50,50));
		bkpanel.setBackground(Color.LIGHT_GRAY);
		container.add(bkpanel,BorderLayout.SOUTH);
		JLabel bklabel=new JLabel("Current background color: ");
		bklabel.setFont(font);
		bkpanel.setLayout(null);//往父组件中插入指定位置的组件→将父组件的布局设置为null
		bklabel.setBounds(10, 0,300,50);
		JPanel bk_color=new JPanel();
		bk_color.setBounds(270, 10,30,30);
		bk_color.setBackground(bkcolor);
		bkpanel.add(bk_color);
		bkpanel.add(bklabel);		
		
		//背景颜色选择→一旦改变了颜色：先得到颜色→立刻修改
		btn[2].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				bkcolor=JColorChooser.showDialog(drawpanel, "", null);
				if(bkcolor==null)
					return;
				bk_color.setBackground(bkcolor);
				drawpanel.setBackground(bkcolor);
			}
			
		});
		
		//文本框
		btn[3].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				click_text=1;
			}
		});
		btn[0].addActionListener(draw);
		btn[0].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				container.remove(s_tool);
				container.remove(t_tool);
				container.add(p_tool,BorderLayout.EAST);
				p_tool.setVisible(true);
				//container.repaint();
			}
			
		});
		btn[1].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				container.remove(p_tool);
				container.remove(t_tool);
				container.add(s_tool,BorderLayout.EAST);
				//container.repaint();
			}
			
		});
		btn[3].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				container.remove(s_tool);
				container.remove(p_tool);
				container.add(t_tool,BorderLayout.EAST);
				//container.repaint();
			}
			
		});
		btn[4].addActionListener(draw);
		//菜单栏和工具panel设置
		//整体设置
		this.setSize(1500,1000);
		this.setTitle("Draw board");
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		myMenuBar menu=new myMenuBar(img);
		this.setJMenuBar(menu);
		this.setLocationRelativeTo(null);
		container.add(tool,BorderLayout.NORTH);
		Graphics2D g1=(Graphics2D) drawpanel.getGraphics();
		draw.g=g1;
		draw.g_save=g;
	}
	public static void main(String[]arg) {
		Painting p = new Painting();
		p.init();
	}
}
