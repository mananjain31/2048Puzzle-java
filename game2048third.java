import javax.swing.*;
import java.awt.*;
import java.awt.Window.*;
import java.awt.event.*;
import static java.lang.Integer.*;
class Jm extends JFrame implements ActionListener
{
	static JF jf;
	JLabel jl;
	Font f;
	JButton b[]=new JButton[4];
	ImageIcon img[]=new ImageIcon[4];
	Jm()
	{
		f=new Font("Corbel Light",Font.BOLD,40);
		setLayout(null);
		jl=new JLabel("CHOOSE DIMENSIONS");
		jl.setBounds(300,40,600,40);
		jl.setFont(f);
		add(jl);
		
		img[0]=new ImageIcon("3x3.png");
		img[1]=new ImageIcon("4x4.png");
		img[2]=new ImageIcon("5x5.png");
		img[3]=new ImageIcon("6x6.png");
		
		int x=20;
		for(int i=0;i<4;i++)
		{
			b[i]=new JButton(img[i]);
			b[i].setBounds(10+x,100,200,200);
			add(b[i]);
			x+=250;
			b[i].addActionListener(this);
		}
		
	}
	public void actionPerformed(ActionEvent e)
	{
		for(int n=0;n<4;n++)
		if(e.getSource()==b[n])
		{
			jf=new JF(n+3);
			jf.setVisible(true);
			jf.setBounds(0,0,1920,750);
			System.out.println("manan jain");
			setVisible(false);
		}
	}
}
class JF extends JFrame
{
	
	JF(int n)
	{
		JP jp=new JP(n);
		add(jp);
	}
}
class JP extends JPanel implements KeyListener
{	
//FOR BOARD'S DIMENSIONS
int xyStart=10;   //position of the game board
	int x=xyStart;
	int y=xyStart;
	final int num;   ///4> w=200 f1= ,f2=,f3=///
	int w=100;
	int fs;
	Font f,f1,f2,f3,f4,f5;
	
//USEFUL
	JButton b[][];
	boolean doIt=false;
	int score =0; 
//CONSTRUCTOR 
	JP(int n)
	{
		num=n;
		b=new JButton[num][num];
		fs=25;
		setLayout(null);
		f1=new Font("Corbel Light",Font.BOLD,w/2);
		f2=new Font("Corbel Light",Font.BOLD,w/3);
		f3=new Font("Corbel Light",Font.BOLD,w/4);
		f4=new Font("Corbel Light",Font.BOLD,w/5);
		f5=new Font("Corbel Light",Font.BOLD,w/6);
		
		// ADDING BUTTONS
		for(int i=0;i<num;i++)
		{
			for(int j=0;j<num;j++)
			{
				b[i][j]=new JButton("");
				b[i][j].setLocation(x,y);
				b[i][j].setSize(w,w);
				b[i][j].setForeground(Color.red);
				b[i][j].setBackground(Color.gray);
				b[i][j].setFont(f1);
				x+=w;
				add(b[i][j]);
				b[i][j].setFocusable(false);
			}
			y+=w;
			x=xyStart;
			
		}
		
		//ADDing test Textfield
		
		
		//ADDING KEY LISTENER
		addKeyListener(this);
		setFocusable(true);
		
		//STARTING THE GAME
		fillEmptyButton();
	}
	
// 2 4 RANDOM NUMBER	
	void fillEmptyButton()
	{
		int i=(int)Math.round(Math.random());
		int val=0;
		if(i==0)
			val=2;
		else
			val=4;
		randomButton(val);
	}
	
	void randomButton(int val)
	{
		byte i=(byte)Math.round(Math.random()*(num-1));
		byte j=(byte)Math.round(Math.random()*(num-1));
		
		if(b[i][j].getLabel().equals(""))
		{
			b[i][j].setLabel(""+val);
			score=score+val;
			repaint();
		}
		else
			randomButton(val);	
	}
	
	
//KEY PRESSED
	public void keyPressed(KeyEvent e)
	{
		doIt=false;
		JButton b1,b2;
		int a;
		if(e.getKeyCode()==KeyEvent.VK_UP)
		{
		for(int c=0;c<=1;c++)
		{	
		//MOVING UP	
			for(int i=1;i<num;i++)
				for(int j=0;j<num;j++)
				{
					if(b[i][j].getLabel().equals(""))
						continue;	
					else
						for(int k=i;k>0;k--)
							while(b[k-1][j].getLabel().equals(""))
							{	
								b[k-1][j].setLabel(b[k][j].getLabel());
								b[k][j].setLabel("");
								doIt=true;
							}	
				}
		//ADDING 	
		if (c==1) break;
			for(int i=1;i<num;i++)
				for(int j=0;j<num;j++)
				{	b1=b[i][j];
					b2=b[i-1][j];
					String s1=b1.getLabel() , s2=b2.getLabel();
					if(s1.equals(""))
						continue;
					else if(s1.equals(s2))
					{
						a=parseInt(s1);
						a*=2;
						b1.setLabel("");
						b2.setLabel(""+a+"a");
					}
				}
		}	
		}
		if(e.getKeyCode()==KeyEvent.VK_DOWN)
		{
		for(int c=0;c<=1;c++)
		{		
			//MOVING DOWN	
			for(int i=num-2;i>=0;i--)
				for(int j=0;j<num;j++)		
					if(b[i][j].getLabel().equals(""))
						continue;
					else
						for(int k=i;k<num-1;k++)
							while(b[k+1][j].getLabel().equals(""))
							{	
								b[k+1][j].setLabel(b[k][j].getLabel());
								b[k][j].setLabel("");
								doIt=true;
							}
			if(c==1)break;					
			//ADDING	
			for(int i=num-2;i>=0;i--)
				for(int j=0;j<num;j++)
				{
					b1=b[i][j];
					b2=b[i+1][j];
					String s1=b1.getLabel() , s2=b2.getLabel();
					if(s1.equals(""))
						continue;
					else if(s1.equals(s2))
					{
						a=parseInt(s1);
						a*=2;
						b1.setLabel("");
						b2.setLabel(""+a+"a");

					}
				}
		}		
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT)
		{
		for(int c=0;c<=1;c++)
		{		
		//MOVING LEFT
		for(int i=0;i<num;i++)
				for(int j=1;j<num;j++)
					if(b[i][j].getLabel().equals(""))
						continue;
					else
						for(int k=j;k>0;k--)
							while(b[i][k-1].getLabel().equals(""))
							{
								b[i][k-1].setLabel(b[i][k].getLabel());
								b[i][k].setLabel("");
								doIt=true;
							}
			if(c==1) break;
			//ADDING
			for(int i=0;i<num;i++)
				for(int j=1;j<num;j++)
				{
					b1=b[i][j];
					b2=b[i][j-1];
					String s1=b1.getLabel();
					String s2=b2.getLabel();
					if(s1.equals(""))
						continue;
					else if( s1.equals(s2))
					{
						a=parseInt(s1);
						a*=2;
						b2.setLabel(""+a+"a");
						b1.setLabel("");
					}
				}
		}
		}
		
		if(e.getKeyCode()==KeyEvent.VK_RIGHT)
		{
		for(int c=0;c<=1;c++)
		{	
		//MOVING LEFT
			for(int i=0;i<num;i++)
				for(int j=num-2;j>=0;j--)
					if(b[i][j].getLabel().equals(""))
						continue;
					else
						for(int k=j;k<num-1;k++)
							while(b[i][k+1].getLabel().equals(""))
							{
								b[i][k+1].setLabel(b[i][k].getLabel());
								b[i][k].setLabel("");
								doIt=true;
							}
			if(c==1)break;
			//ADDING
			for(int i=0;i<num;i++)
				for(int j=num-2;j>=0;j--)
				{
					b1=b[i][j];
					b2=b[i][j+1];
					String s1=b1.getLabel() , s2=b2.getLabel();
					if(s1.equals(""))
						continue;
					else if( s1.equals( s2 ) )
					{
						a=parseInt(s1);
						a*=2;
						b1.setLabel("");
						b2.setLabel(""+a+"a");
					}
				}
		}		
		}
		if(e.getKeyCode()==KeyEvent.VK_ESCAPE)
		{	
			// score=0;
			// repaint();
			// for(int i=0;i<num;i++)
				// for(int j=0;j<num;j++)
					// b[i][j].setLabel("");
				// doIt=true;
				Jm.jf.setVisible(false);
			Jm jm=new Jm();
				jm.setVisible(true);
				jm.setBounds(50,20,1100,500);
			setVisible(false);
		}
		
		for(int i=0;i<num;i++)
			for(int j=0;j<num;j++)
			{
				JButton btn=b[i][j];
				String s=btn.getLabel();
				if(s.endsWith("a"))
				{
					btn.setLabel(s.substring(0,s.length()-1));
					doIt=true;
				}
				
			}
			
		if(doIt==true)	
		fillEmptyButton();
		
		for(int i=0;i<num;i++)
			for(int j=0;j<num;j++)
			{
				JButton btn=b[i][j];
				String s=btn.getLabel();
				int bl;
				if(s.equals(""))
				{
					btn.setForeground(Color.white);
					btn.setBackground(Color.white);
					btn.setFont(f1);
					continue;
				}
				bl=parseInt(s);
				//COLORING
				switch(bl)
				{
					case 2:
						btn.setFont(f1);
					case 4:	
						btn.setFont(f1);
						btn.setForeground(Color.white);
						btn.setBackground(Color.gray);
						break;
					case 8:	
						btn.setFont(f1);
					case 16:
						btn.setFont(f1);
					case 32:
						btn.setFont(f1);
						btn.setForeground(Color.black);
						btn.setBackground(Color.yellow);
						break;
					case 64:
						btn.setFont(f2);
					case 128:
						btn.setFont(f3);
						btn.setForeground(Color.black);
						btn.setBackground(Color.green);
						break;
					case 256:
						btn.setFont(f3);
					case 512:
						btn.setFont(f3);
						btn.setForeground(Color.white);
						btn.setBackground(Color.red);
						break;
					case 1024:
						btn.setFont(f4);
						btn.setForeground(Color.yellow);
						btn.setBackground(Color.black);
						break;
					case 2048:
					case 4096:
					case 8192:
						btn.setFont(f3);
						btn.setForeground(Color.gray);
						btn.setBackground(Color.black);
						break;
					case 16384:
						btn.setFont(f4);
						btn.setForeground(Color.gray);
						btn.setBackground(Color.black);
						break;
				}	
			}	
	}
	public void keyReleased(KeyEvent e){}
	public void keyTyped(KeyEvent e){}
	public void paintComponent(Graphics g)
	{
		g.setFont(f2);
		g.drawString("Use ARROW keys to play",700,30);
		g.drawString("Use ESC to reset",700,70);
		g.setColor(Color.black);
		g.fillRoundRect(650,120,250,50,20,20);
		g.setColor(Color.white);
		
		g.drawString("Score = "+score,700,150);
		
	}
}
class game2048third
{
	static Jm jm;
	public static void main(String...args)
		{
			
			// f=new JF();
			// f.setVisible(false);
			// f.setBounds(0,0,1920,750);
			// System.out.println("manan jain");
			
				jm=new Jm();
				jm.setVisible(true);
				jm.setBounds(50,20,1100,500);
		}
}