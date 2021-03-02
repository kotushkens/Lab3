package controller;

import model.Point;
// класс для проверки попадания точки

public class AreaResultChecker {
	private AreaResultChecker() {
	}

	public static boolean isPointInArea(Point point) {
		return isCoordinatesInArea(point.getX(), point.getY(), point.getR());
	}

	public static boolean isCoordinatesInArea(double x, double y, double r) {
		return inSecondQuad(x, y, r) ||
				inThirdQuad(x, y, r) ||
				inFourthQuad(x, y, r)
				;
	}

	private static boolean inSecondQuad(double x, double y, double r) {
		return x <= 0 &&
				y >= 0 &&
				y <= -r/2 &&
				x <= r
				;
	}

	private static boolean inThirdQuad(double x, double y, double r) {
		return  (x<=0 && y<=0 && (y>=-x-r));

	}

	private static boolean inFourthQuad(double x, double y, double r) {
		return x >= 0 &&
				y >= 0 &&
				x * x + y * y <= r * r
				;
	}
}
