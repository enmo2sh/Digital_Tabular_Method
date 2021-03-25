package digital;
import java.util.*;
import java.awt.EventQueue;
import java.awt.FlowLayout;
 
import javax.swing.JFrame;
import javax.swing.JTextPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
 
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import java.awt.Window.Type;
import javax.swing.JToggleButton;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
 
public class oops {
 
	private JFrame frame;
	private JTextField vars;
	private JTextField txtMinTerms;
	private JTextField term;
	private JTextField txtDontCare;
	private JTextField txtNoOfVariables;
	private JTextField care;
 
 
 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					oops window = new oops();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
 
	/**
	 * Create the application.
	 */
	public oops() {
		initialize();
	}
 
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
 
		frame = new JFrame("Tabular Method");
		frame.setForeground(new Color(173, 255, 47));
		frame.getContentPane().setBackground(new Color(0, 0, 0));
		frame.setBounds(100, 100, 796, 504);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setLayout(null);
 
 
 
		vars = new JTextField();
		vars.setBounds(353, 90, 276, 36);
		frame.getContentPane().add(vars);
		vars.setColumns(10);
 
 
		txtMinTerms = new JTextField();
		txtMinTerms.setBounds(157, 157, 150, 36);
		txtMinTerms.setHorizontalAlignment(SwingConstants.CENTER);
		txtMinTerms.setToolTipText("");
		txtMinTerms.setFont(new Font("Arial", Font.PLAIN, 25));
		txtMinTerms.setBackground(new Color(175, 238, 238));
		txtMinTerms.setText("Min terms");
		frame.getContentPane().add(txtMinTerms);
		txtMinTerms.setColumns(10);
 
		term = new JTextField();
		term.setBounds(353, 160, 276, 36);
		frame.getContentPane().add(term);
		term.setColumns(10);
 
 
		txtDontCare = new JTextField();
		txtDontCare.setBounds(157, 217, 150, 36);
		txtDontCare.setHorizontalAlignment(SwingConstants.CENTER);
		txtDontCare.setFont(new Font("Arial", Font.PLAIN, 20));
		txtDontCare.setBackground(new Color(175, 238, 238));
		txtDontCare.setText("Don't care");
		frame.getContentPane().add(txtDontCare);
		txtDontCare.setColumns(10);
 
		txtNoOfVariables = new JTextField();
		txtNoOfVariables.setBounds(157, 88, 150, 46);
		txtNoOfVariables.setHorizontalAlignment(SwingConstants.CENTER);
		txtNoOfVariables.setFont(new Font("Arial", Font.PLAIN, 20));
		txtNoOfVariables.setBackground(new Color(175, 238, 238));
		txtNoOfVariables.setText("no. of variables");
		frame.getContentPane().add(txtNoOfVariables);
		txtNoOfVariables.setColumns(10);
 
		care = new JTextField();
		care.setBounds(353, 219, 276, 36);
		frame.getContentPane().add(care);
		care.setColumns(10);
 
		JButton btnEnter = new JButton("Enter");
		btnEnter.setBounds(259, 309, 175, 54);
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n;
				try {
					n=Integer.parseInt(vars.getText().toString());
					String text1 = term.getText().toString();
					String[] parts1=text1.split(" ");
					LinkedList<Integer>Min=new LinkedList <Integer>();
					for (int i=0;i<parts1.length;i++) { 
						if (Integer.parseInt(parts1[i])>=Math.pow(2, n)) 
							JOptionPane.showMessageDialog(null, "OUT OF Bounds of Min terms!!"); 
						else
						Min.add(Integer.parseInt(parts1[i]));
					}
					String text2 = care.getText().toString();
					String[] parts2=text2.split(" ");
					LinkedList<Integer>DC=new LinkedList <Integer>();
					for (int i=0;i<parts2.length;i++) {
						if(Integer.parseInt(parts2[i])<0) {
							break;
						}
						if (Integer.parseInt(parts2[i])>=Math.pow(2, n)) 
							JOptionPane.showMessageDialog(null, "OUT OF Bounds of don't care!!"); 
						DC.add(Integer.parseInt(parts2[i]));
					}
					for(int i=0;i<DC.size();i++) {
						System.out.println(DC.get(i));
					}
					LinkedList <String> repeat=new LinkedList <String>();
					LinkedList <String> repeat2=new LinkedList <String>();
					LinkedList<String> essential = new LinkedList <String>();
					LinkedList<String> essential2 = new LinkedList <String>();
					one call=new one();
					call.program(n, Min, DC, essential, essential2, repeat, repeat2);
				}catch(Exception f) {
				JOptionPane.showMessageDialog(null, "please enter a vaild number"); 
				}
			}
		});
		btnEnter.setFont(new Font("Monotype Corsiva", Font.BOLD, 40));
		btnEnter.setBackground(new Color(175, 238, 238));
		btnEnter.setForeground(new Color(0, 0, 0));
		frame.getContentPane().add(btnEnter);
 
		JLabel lblNewLabel = new JLabel("IF there isnt enter -ve ");
		lblNewLabel.setForeground(new Color(154, 205, 50));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel.setBounds(143, 266, 164, 17);
		frame.getContentPane().add(lblNewLabel);
	}
}