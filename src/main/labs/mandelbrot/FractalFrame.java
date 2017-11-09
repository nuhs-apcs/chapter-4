package labs.mandelbrot;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class FractalFrame extends JFrame {
	
	final FractalPanel fractalPanel;
	
	public FractalFrame() {
		setSize(1000, 1000);
		setTitle("Mandelbrot Set");
		
		fractalPanel = new FractalPanel();
		add(fractalPanel);
		addMouseListener(fractalPanel);
		addMouseMotionListener(fractalPanel);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenu viewMenu = new JMenu("View");
		
		final JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setMultiSelectionEnabled(false);
		chooser.setAcceptAllFileFilterUsed(false);
		FileFilter pngFile = new FileNameExtensionFilter("PNG file", "png");
		chooser.setFileFilter(pngFile);
		
		JMenuItem saveItem = new JMenuItem("Save As...");
		saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
		saveItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int retVal = chooser.showSaveDialog(FractalFrame.this);
				if (retVal == JFileChooser.APPROVE_OPTION) {
					File file = chooser.getSelectedFile();
					if (file.exists()) {
						int overwrite = JOptionPane.showConfirmDialog(FractalFrame.this, "That file already exists. Are you sure you want to overwrite?");
						if (overwrite != JOptionPane.OK_OPTION) {
							return;
						}
					}
					try {
						fractalPanel.saveImage(file);
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(FractalFrame.this, "An error occured while saving", "File Save Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			
		});
		fileMenu.add(saveItem);

		JMenuItem zoomInItem = new JMenuItem("Zoom In");
		zoomInItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_DOWN_MASK));
		zoomInItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fractalPanel.customZoom(2);
			}
			
		});		
		viewMenu.add(zoomInItem);
		
		JMenuItem zoomOutItem = new JMenuItem("Zoom Out");
		zoomOutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
		zoomOutItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fractalPanel.customZoom(0.5);
			}
			
		});
		viewMenu.add(zoomOutItem);
		
		JMenuItem customZoomItem = new JMenuItem("Custom Zoom...");
		customZoomItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					double zoom = Double.parseDouble(JOptionPane.showInputDialog(FractalFrame.this, "Enter a custom zoom level", "Custom Zoom", JOptionPane.PLAIN_MESSAGE));
					fractalPanel.customZoom(zoom);
				} catch (Exception exception) {
					JOptionPane.showMessageDialog(FractalFrame.this, "Custom zoom levels must be decimal numbers", "Invalid Zoom Level", JOptionPane.ERROR_MESSAGE);
				}
			}
			
		});
		viewMenu.add(customZoomItem);
		
		viewMenu.addSeparator();
		
		JMenuItem resetItem = new JMenuItem("Reset");
		resetItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fractalPanel.resetView();
			}
			
		});
		viewMenu.add(resetItem);
		
		menuBar.add(fileMenu);
		menuBar.add(viewMenu);
		
		setJMenuBar(menuBar);
	}
	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				FractalFrame window = new FractalFrame();
			}
		});
	}

}
