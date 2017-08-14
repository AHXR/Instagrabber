/*
 * Instagrabber: Download Instagram Pictures
 * Copyright (C) 2017 AR.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

// Imports.
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;

import instagrabber.global.GlobalMessages;
import javax.swing.JFileChooser;
import java.awt.Toolkit;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;

public class SwingGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtURL;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SwingGUI frame = new SwingGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SwingGUI() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(SwingGUI.class.getResource("/instagrabber/images/icon.jpg")));
		setTitle("InstaGrabber");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 518, 114);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtURL = new JTextField();
		txtURL.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				//Clearing the example.
				txtURL.setText("");
			}
		});
		txtURL.setFont(new Font("Trebuchet MS", Font.ITALIC, 13));
		txtURL.setText("Example: https://www.instagram.com/p/BXlplv-F3t1" );
		txtURL.setBounds(10, 11, 425, 37);
		contentPane.add(txtURL);
		txtURL.setColumns(10);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(10, 52, 490, 24);
		contentPane.add(progressBar);
		
		JButton btnGO = new JButton("GO");
		btnGO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				progressBar.setValue( 5 );
			
				/*
				 * Checking if the URL is valid before doing anything. I wouldn't
				 * consider this a slow process, despite everything that is going on.
				 * 
				 * I haven't had any freezing or lag. Everything runs smoothly.
				 */
				
				if( URLControl.isValidURL( txtURL.getText( ) ) ) {
					
					progressBar.setValue( 15 );
					
					/*
					 * Note to self: Don't use JFileChooser with the Eclipse Swing design page. It is useless and confusing.
					 * Here is where the save dialog is created. GlobalMessages.java contains all messages.
					 */
					JFileChooser saveDialog = new JFileChooser( FileSystemView.getFileSystemView().getHomeDirectory() );
					saveDialog.setDialogTitle( GlobalMessages.DIALOGSAVE.value( ) );
					saveDialog.setFileSelectionMode( JFileChooser.FILES_ONLY);
					
					progressBar.setValue( 30 );
					
					int
						i_ret = saveDialog.showSaveDialog( null );
					
					progressBar.setValue( 50 );
					
					// User decided to not save the image.
					if( i_ret == JFileChooser.CANCEL_OPTION ) 
						JOptionPane.showMessageDialog( null, GlobalMessages.CANCEL.value( ), "Instagrabber", JOptionPane.WARNING_MESSAGE );
					
					// User is saving the imagen ow.
					else if( i_ret == JFileChooser.APPROVE_OPTION ){
						
						File
							f_loc = saveDialog.getSelectedFile();
						
						progressBar.setValue( 80 );
						
						
						// This is where downloadImage is used. Runs smoothly.
						if( ImageDownload.downloadImage( txtURL.getText( ), f_loc.getAbsolutePath( ) ) ) {
							progressBar.setValue( 100 );
							JOptionPane.showMessageDialog( null, GlobalMessages.SUCCESS.value( ), "Instagrabber", JOptionPane.PLAIN_MESSAGE );
						}
						else 
							JOptionPane.showMessageDialog( null, GlobalMessages.FAILURE.value( ), "Instagrabber", JOptionPane.PLAIN_MESSAGE );
					}
				}
				else
					JOptionPane.showMessageDialog( null, GlobalMessages.INVALID_URL.value( ), "Instagrabber", JOptionPane.PLAIN_MESSAGE );	
			}
		});
		btnGO.setFont(new Font("Trebuchet MS", Font.ITALIC, 15));
		btnGO.setBounds(444, 11, 56, 37);
		contentPane.add(btnGO);
	
		JLabel lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(SwingGUI.class.getResource("/instagrabber/images/bg.png")));
		lblBackground.setBounds(0, 0, 516, 168);
		contentPane.add(lblBackground);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{contentPane}));
		
	}
}
