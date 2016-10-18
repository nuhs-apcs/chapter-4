import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.awt.image.VolatileImage;
import java.io.File;
import java.io.IOException;
import java.awt.*;
import java.awt.Rectangle;

import javax.imageio.ImageIO;
import javax.swing.*;

public class FractalPanel extends JPanel implements MouseListener, MouseMotionListener {
	
	public static final Double ORIGINAL_VIEW_RECT = new Rectangle2D.Double(-2.25, -1.5, 3.0, 3.0);

	public static final int MAX_ITERATIONS = 200;
	
	private Rectangle2D.Double viewRect = null;
	private int[][] set = null;
	private GraphicsConfiguration configuration;
	private VolatileImage canvas;
	
	private boolean dragging = false;
	private Point startPoint = null;
	private Point dragPoint = null;
	
	public FractalPanel() { 
		this.viewRect = ORIGINAL_VIEW_RECT;
		this.configuration = GraphicsEnvironment.
				getLocalGraphicsEnvironment().getDefaultScreenDevice().
				getDefaultConfiguration();
	}
	
	public void resetView() {
		setViewRect(ORIGINAL_VIEW_RECT);
	}
	
	public void createCanvas() {
		int w = getWidth();
		int h = getHeight();
		this.canvas = configuration.createCompatibleVolatileImage(w, h);
		
		set = new int[w][h];
		setViewRect(this.viewRect);
	}
	
	public void setViewRect(Rectangle2D.Double rect) {
		this.viewRect = rect;		
		computeSet(set);
		paintCanvas();
		repaint();
	}
	
	public void paintCanvas() {
		int w = getWidth();
		int h = getHeight();
		
		Graphics2D g2d = this.canvas.createGraphics();
		for(int i = 0; i < w; i++) {
			for(int j = 0; j < h; j++) {
				int k = set[i][j];
				if(k == -1) {
					g2d.setColor(Color.black);
				} else {
					g2d.setColor(Color.getHSBColor(k / (float) MAX_ITERATIONS, 1f, 1f));
				}
				g2d.fillRect(i, j, 1, 1);
			}
		}
	}

	public void paint(Graphics g) {
		super.paint(g);
		
		int w = getWidth();
		int h = getHeight();
		if (set == null || set.length != w || set[0].length != h) {
			createCanvas();
		}
		
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.drawImage(this.canvas, 0, 0, null);	
		
		if (dragging) {
			g2d.setColor(Color.white);
			g2d.setStroke(new BasicStroke(3));
			g2d.draw(getRectangleFromPoints(startPoint, dragPoint));
		}
	}
	
	public void computeSet(int[][] set) {
		int width = set.length;
		int height = set[0].length;
		double x = viewRect.x;
		double y = viewRect.y;
		double hSpacing = viewRect.width / width;
		double vSpacing = viewRect.height / height;
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				set[i][j] = MandelbrotTest.testPoint(
						x + (i + 0.5) * hSpacing, 
						y + (j + 0.5) * vSpacing, 
						MAX_ITERATIONS);
			}
		}
	}
	
	public void customZoom(double zoom) {
		double zoomFactor = 1.0 / zoom;
		setViewRect(new Rectangle2D.Double(
				viewRect.x + 0.5 * viewRect.width * (1.0 - zoomFactor),
				viewRect.y + 0.5 * viewRect.height * (1.0 - zoomFactor),
				viewRect.width * zoomFactor,
				viewRect.height * zoomFactor));
	}
	
	public Point getRelativeMousePoint(MouseEvent e) {
		Point origin = getLocationOnScreen();
		Point pt = e.getLocationOnScreen();
		return new Point(pt.x - origin.x, pt.y - origin.y);
	}
	
	public Rectangle getRectangleFromPoints(Point pt1, Point pt2) {
		if (pt1.x < pt2.x && pt1.y < pt2.y) {
			return new Rectangle(pt1.x, pt1.y, pt2.x - pt1.x, pt2.y - pt1.y);
		} else {
			return new Rectangle(pt2.x, pt2.y, pt1.x - pt2.x, pt1.y - pt2.y);
		}
	}
	
	public void adjustViewRect(Point pt1, Point pt2) {
		Rectangle newRect = getRectangleFromPoints(pt1, pt2);
		if (newRect.width == 0 || newRect.height == 0) {
			customZoom(2);
		} else {
			int width = getWidth();
			int height = getHeight();
			double x = newRect.getX() / width;
			double y = newRect.getY() / height;
			double w = newRect.getWidth() / width;
			double h = newRect.getHeight() / height;
			setViewRect(new Rectangle2D.Double(
					viewRect.x + x * viewRect.width, 
					viewRect.y + y * viewRect.height, 
					w * viewRect.width, 
					h * viewRect.height));
		}
	}
	
	public void saveImage(File output) throws IOException {
		ImageIO.write(canvas.getSnapshot(), "png", output);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON3) {
			FractalPanel.this.customZoom(0.5);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			dragging = true;
			startPoint = FractalPanel.this.getRelativeMousePoint(e);
			dragPoint = startPoint;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (dragging) {
			dragging = false;
			adjustViewRect(startPoint, FractalPanel.this.getRelativeMousePoint(e));
			dragPoint = null;
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) { }

	@Override
	public void mouseExited(MouseEvent e) {
		if (dragging) {
			dragging = false;
			adjustViewRect(startPoint, FractalPanel.this.getRelativeMousePoint(e));
			dragPoint = null;
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		dragPoint = FractalPanel.this.getRelativeMousePoint(e);
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) { }
	
}
