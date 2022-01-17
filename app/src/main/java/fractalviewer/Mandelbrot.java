package fractalviewer;

import javafx.scene.paint.Color;
import javafx.scene.image.WritableImage;
import javafx.scene.image.PixelWriter;

public class Mandelbrot {
    WritableImage getImage(int width, int height) {
	WritableImage img = new WritableImage(width, height);
	PixelWriter writer = img.getPixelWriter();
	for (int x = 0; x < width; x++) {
	    for (int y = 0; y < height; y++) {
		Color c = pixelColor(x, y, width, height);
		writer.setColor(x, y, c);
	    }
	}
	return img;
    }
    
    private Color pixelColor(int x, int y, int width, int height) {
	//(x,y) = (mLeft + (x / 1920) * (mRight - mLeft), mUp + (y / 1017) * (mDown - mUp))
	double minX = -2.0;
	double maxX = 0.47;
	double minY = -1.12;
	double maxY = 1.12;
	
	// These have to be changed for zoom to work
	double realX = minX + (x / (double)width) * (maxX - minX);
	double realY = minY + (y / (double)height) * (maxY - minY);
	
	double rl = 0;
	double im = 0;
	int iter = 0;
	int max_iter = 1000;
	while (rl*rl + im*im <= 2*2 && iter < max_iter) {
	    double tempReal = rl * rl - im * im + realX;
	    im = 2 * rl * im + realY;
	    rl = tempReal;
	    iter++;
	}
	double red = iter / max_iter;
	if (x == 1919 && y == 1016) {
	    System.out.println("realX: " + realX + " realY: " + realY);
	    System.out.println(minX + "+" + "(" + x + "/" + width + ")" + "* (" + maxX + "-" + minX + ") = " + realX + " = " + (minX + (x / width) * (maxX - minX)));
	}
	/*
	if (red > 0.5 && red < 0.6) {
	    System.out.println("red: " + red);
	}
	*/
	return Color.color(iter % 4 * 0.25, iter % 8 * 0.125, iter % 16 * 0.0625);
    }
}
