package digital;
 
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JOptionPane;
import javax.swing.*;
import java.awt.*;
import java.awt.Container;
import javax.swing.JLabel;
import javax.swing.JPanel;
 
public class GUIP extends JFrame {
 
	JLabel l1;
	public  GUIP() {
 
		setSize(1800,1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(Color.black);
 
		setLayout(new BorderLayout());
		setContentPane(new JLabel(new ImageIcon("C:\\Users\\Public\\this2.jpg")));
		setLayout(new FlowLayout());
		l1 = new JLabel();
		add(l1);
		setVisible(true);
	}
 
	int i=0;
	int j=0;
	int k;
	int l;
	int cost1;
	int cost2;
	LinkedList<String> var=new LinkedList<String>();
	LinkedList<String> ess=new LinkedList<String>();
	LinkedList<String> ess2=new LinkedList<String>();
 
	public void setProps(int and,int lines,LinkedList<String>cirLines,LinkedList<String>essential2,LinkedList<String>essential,int totalCost1,int totalCost2) {
		this.k=and;
		this.l=lines;
		this.var=cirLines;
		this.ess2=essential;
		this.ess=essential2;
		this.cost1= totalCost1;
		this.cost2= totalCost2;
	}
	public void paint (Graphics g) {
		super.paint(g);
		g.setColor(Color.white);
		g.setFont(new Font("Tahoma", Font.PLAIN, 20));
		g.drawString("Essential SOP implicants :", 1100,100);
		for(int i=0;i<ess2.size();i++){
			g.drawString(ess2.get(i),1100,130+i*25);
			g.drawString(" ",1100,150+i*25);
		}
		g.drawString("SOP cost = "+ Integer.toString(cost1),1100,150+25*(ess2.size()-1));
		int h=150+25*(ess2.size()-1)+25;
		int o=1120;
		g.drawString("Essential POS implicants :", 1100,h+25);
		for(int i=0;i<ess.size();i++){
			String[]temp=ess.get(i).split(" ");
			g.drawString(temp[0],1100,h+50);
			for(int j=1;j<temp.length;j++) {
				g.drawString("  +   ",o,h+50);
				g.drawString(temp[j],o+30,h+50);
				o+=50;
 
			}
			o=1120;
			g.drawString(" ", 1100, h+(j+2)*25);
			h+=25;
 
		}
		if(ess.size()==1)
			h+=25;
		g.drawString("POS cost = "+ Integer.toString(cost2),1100,h+25*(ess.size()));
 
			 g.setFont(new Font("Tahoma", Font.PLAIN, 30));
 
			 g.drawString("the minimization of the circuit  is:",700,800);
				 int r=825;
				 int v=700;
 
				 g.setFont(new Font("Tahoma", Font.PLAIN, 15));
				 for (int i=0;i<ess.size();i++) {
					 int u=0;
					 String[] parts=(ess.get(i)).split(" ");
					 g.drawString("( "+parts[0],v,r);
					 for ( u=1;u<parts.length;u++) {
						 v+=50;
						 g.drawString("+ "+parts[u],v,r);	 
					 }
					 v+=50;
					 g.drawString(Character.toString(')'),v,r) ;
					 v+=2*parts.length*10;
 
					 if(v>1300) {
							r=r+25;
							v=700;
						}
				 }
				 g.setFont(new Font("Tahoma", Font.PLAIN, 30));
					g.drawString("the total cost of the new circuit: "+cost2,700,950);
 
					if(cost2==cost1) {
					g.drawString("OR", 1000, r+25);
						 int u=700;
						  r=800+75;
 
						 g.setFont(new Font("Tahoma", Font.PLAIN, 15));
						 g.drawString('('+ess.get(0)+')',700,r+25);
 
						for (int i=1;i<ess2.size();i++) {
							String[]temp= ess2.get(i-1).split(" ");
							u+=(temp.length*23)+3*20;
							if(u>1300) {
								r=r+25;
								u=700;
							}
							g.drawString("+"+ '('+ess2.get(i)+')',u,r+25);
						}
					}
 
		int y3=0;
		int y4=0;
		int x=750;
		int y=390;
		 int height =120;
		 int width = 120;
		 int startAngle=257;
		 int  arcAngle = 205;
 
		 g.setColor(new Color(0,100,130));
		g.drawArc(x, y, width, height, startAngle, arcAngle);
 
		 int x1=798;
		 int y1=y;
		 int y2=y1+120;
		g.drawLine(x1, 390, x1, 510);
		g.drawLine(870, 450, 970, 450);
		g.drawLine(798,450,690,450);
 
		int [][]Colors = new int [l][4];
		while (i<l) {
 
			  x1=400/(l+1);
			  y1=70;
			  y2=900;
			 x1=x1+i*400/(l+1);
 
			 int R = (i*15)%255;
			 int G= (i*30)%255+(i*10)%25;
			 int B=255-((i*20)%255)+(i*10)%25;
			 g.setColor(new Color(R,G,B));
			 Colors[i][0]=i;
			 Colors[i][1]=R;
			 Colors[i][2]=G;
			 Colors[i][3]=B;
 
			 g.drawLine(x1, y1, x1, y2);
			 char [] a= var.get(i).toCharArray();
			 if(a[a.length-1]=='`') {
				 g.drawLine(x1-300/(l+1), 90,x1-400/(l+1) ,90);
				 g.drawPolygon(new int [] {x1-300/(l+1),x1-300/(l+1),x1-100/(l+1)},new int [] {80,100,90}, 3);
				 g.drawOval(x1-100/(l+1)+1, 90-2, 5, 5);
				 g.drawLine(x1-100/(l+1)+6, 90, x1, 90);
 
			 }
			 g.setFont(new Font("Tahoma", Font.BOLD, 15));
			 g.drawString(var.get(i), x1-6, 65);
 
			i++;
		}
 
		 while (j<k) {
			 int R = 0;
			 int G= 100;
			 int B=80;
			g.setColor(new Color(R,G,B));
			 int n=0;
			 int no=0;
				 x=450;
				 y=100+(700-(100*k))/(k+1);
				 int z =y;
				  height =100;
				  width = 150;
				 startAngle=257;
				  arcAngle = 200;
				  y=y+j*(100+(700-(100*k))/(k+1));
				g.drawArc(x, y, width, height, startAngle, arcAngle+5);
 
				g.drawArc(x+5, y, 90, height, startAngle+10, arcAngle-15);
 
 
				  x1=490;
				  y1=y;
				  y2=y1+100;
				g.drawLine(490, y1+50, 545, y1+50);
 
 
				String[]parts = ess.get(j).split(" ");
				while(n<parts.length) {
					for(int i=0;i<var.size();i++) {
						if(parts[n].equals(var.get(i))) {
							no=i;
							break;
						}
					}
 
					for(int q=0;q<Colors.length;q++){
						if(Colors[q][0]==no){
							 g.setColor(new Color(Colors[q][1],Colors[q][2],Colors[q][3]));
							 g.drawLine(x1, y1+(n+1)*(y2-y1)/(parts.length+1), x1-490+(no+1)*400/(l+1),y1+(n+1)*(y2-y1)/(parts.length+1));
							 break;
						}
					}
					g.drawLine(x1, y1+(n+1)*(y2-y1)/(parts.length+1), x1-490+(no+1)*400/(l+1),y1+(n+1)*(y2-y1)/(parts.length+1));
 
					if(n==0&&parts.length!=1) {
						y3=y1+(n+1)*(y2-y1)/(parts.length+1);
					}
					else if(n==parts.length-1&&parts.length!=1) {
						y4=y1+(n+1)*(y2-y1)/(parts.length+1);
					}
					n++;
				}
 
				g.setColor(new Color(R,G,B));
				g.drawLine(490, y3, 490, y4);
				g.drawLine(600, y+50,690,y+50);
				if(j==k-1) {
					 g.setColor(new Color(0,100,80));
					g.drawLine(690, z+50, 690, y+50);
				}
				j++;
		 }
 
	}
 
}