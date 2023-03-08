package vista;


import javax.swing.JFrame;
import javax.swing.JPanel;

import controlador.GameInputManager;

import java.awt.BorderLayout;

public class Ventana extends JFrame{
	public JPanelHandler panel;
	
	
	
	public Ventana() {
		this.setBounds(100, 100, 800, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setResizable(false);

		panel = new JPanelHandler();
		this.addKeyListener(panel.inputs);
		this.getContentPane().add(panel, BorderLayout.CENTER);
	}
	
}
