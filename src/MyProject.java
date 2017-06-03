import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JInternalFrame;
import javax.swing.JButton;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.*;

public class MyProject {

	private JFrame frame;
	private JTextField x;
	private JTextField y;
	private JTextField XB;
	private JTextField YB;
	private JTextField h;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyProject window = new MyProject();
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
	public MyProject() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		x = new JTextField();
		x.setBounds(48, 41, 114, 19);
		frame.getContentPane().add(x);
		x.setColumns(10);
		
		y = new JTextField();
		y.setBounds(216, 41, 114, 19);
		frame.getContentPane().add(y);
		y.setColumns(10);
		JButton btnNewButton = new JButton("Resolution");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Resolution resolution = new Resolution();
				String chx =x.getText() ;
				int X=Integer.parseInt(chx);
				
				String chy =y.getText() ;
				int Y=Integer.parseInt(chy);
				
				Etat EtatInitial=new Etat(X,Y);
				
			    try {
			    	//JOptionPane.showMessageDialog(null,resolution.genereOperateursApplicables(EtatInitial) );			    	
			    	 JOptionPane.showMessageDialog(frame,
			    			 resolution.genereOperateursApplicables(EtatInitial),
			    		        "Resultat",
			    		        JOptionPane.INFORMATION_MESSAGE);			    	
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(101, 147, 117, 25);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblEtat = new JLabel("Etat");
		lblEtat.setBounds(12, 0, 70, 15);
		frame.getContentPane().add(lblEtat);
		
		JLabel lblX_1 = new JLabel("X ");
		lblX_1.setBounds(33, 43, 70, 15);
		frame.getContentPane().add(lblX_1);
		
		JLabel lblY = new JLabel("Y");
		lblY.setBounds(203, 43, 70, 15);
		frame.getContentPane().add(lblY);
		
		JButton btnA = new JButton("A*");
		btnA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Resolution resolution = new Resolution();
				String chx =x.getText() ;
				int X=Integer.parseInt(chx);
			
				String chy =y.getText() ;
				int Y=Integer.parseInt(chy);
				
				Etat EtatInitial=new Etat(X,Y);
				//But
				String chb =XB.getText() ;
				int XB=Integer.parseInt(chb);
				
				String chby =YB.getText() ;
				int YB=Integer.parseInt(chby);
				
				Etat but=new Etat(XB,YB);
				ArrayList<String> L =new ArrayList<String>();
				String H =h.getText() ;
				int h=Integer.parseInt(H);
				try {
					if(h==2)
					resolution.AAlgorithm(EtatInitial, but,L,2);
					else if(h==1)
						resolution.AAlgorithm(EtatInitial, but,L,1);
					else 
						JOptionPane.showMessageDialog(null,"Choix invalide Saisir 1 ou 2");
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JTextArea text=new JTextArea(6,25);
				String ch=" " ;
				for(int i=0 ; i<L.size();i++)
				{
					
					ch=ch+L.get(i)+" \n" ;
				
				}
				text.setText(ch);
				text.setEditable(false);
				JScrollPane s = new JScrollPane(text);
				JOptionPane.showMessageDialog(frame,s);
			    		    	
//				 JOptionPane.showMessageDialog(frame,L
//						 ,
//					        "Resultat",
//					        JOptionPane.INFORMATION_MESSAGE);
				 L.clear();
				
			}
		});
		btnA.setBounds(268, 147, 117, 25);
		frame.getContentPane().add(btnA);
		
		XB = new JTextField();
		XB.setBounds(48, 88, 114, 19);
		frame.getContentPane().add(XB);
		XB.setColumns(10);
		
		YB = new JTextField();
		YB.setBounds(216, 88, 114, 19);
		frame.getContentPane().add(YB);
		YB.setColumns(10);
		
		JLabel label = new JLabel("Y");
		label.setBounds(203, 90, 70, 15);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("X ");
		label_1.setBounds(33, 90, 70, 15);
		frame.getContentPane().add(label_1);
		
		JLabel lblBut = new JLabel("But");
		lblBut.setBounds(12, 72, 70, 15);
		frame.getContentPane().add(lblBut);
		
		JLabel lblChoisirL = new JLabel("Choisir l’heuristique 1 ou 2");
		lblChoisirL.setBounds(12, 192, 228, 15);
		frame.getContentPane().add(lblChoisirL);
		
		h = new JTextField();
		h.setBounds(216, 190, 114, 19);
		frame.getContentPane().add(h);
		h.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("1/if ?x=2 alors h=0, si ?x+?y <2 alors h=7, si ?y>2 alors h=3 sinon h=1");
		lblNewLabel.setBounds(12, 219, 424, 15);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblhAbsx = new JLabel("2/h = abs(?x - 2)");
		lblhAbsx.setBounds(12, 247, 137, 15);
		frame.getContentPane().add(lblhAbsx);
	}
}
