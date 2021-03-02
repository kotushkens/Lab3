package controller;

import model.Point;

import java.util.Arrays;
import java.util.List;
//валидация инпута, ничего сложного
public class InputValidator {
	private static final List<Double> yValues = Arrays.asList(-5.0, -4.0, -3.0, -2.0, -1.0, 0.0, 1.0, 2.0, 3.0, 4.0, 5.0);

	public static boolean isPointValid(Point point) {
		return isXValid(point.getX()) &&
				isYValid(point.getY()) &&
				isRValid(point.getR())
				;
	}

	public static boolean isXValid(double x) {
		return x >= -3 &&
				x <= 3;
	}

	public static boolean isYValid(double x) {
		return x >= -5 &&
				x <= 5;
	}
	public static boolean isRValid(double x) {
		return x >= 1 &&
				x <= 3;
	}

}

