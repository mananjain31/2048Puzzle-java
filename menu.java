import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
class Jm extends JFrame implements ActionListener
{
	JButton b;
	Jm()
	{
		setLayout(new FlowLayout());
		b=new JButton("start");
		add(b);
		b.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e)
	{
		game2048 g=new game2048();
		g.main();
		remove(this);
	}
}
class menu
{
	public static void main(String args[])
	{
		Jm jm=new Jm();
		jm.setVisible(true);
		jm.setBounds(100,500,500,300);
		
	}
}