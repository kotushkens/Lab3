package model;

import controller.AreaResultChecker;
import controller.DatabaseManager;
import controller.InputValidator;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.*;
import java.util.*;

@Getter
@Setter
@ManagedBean(name = "pointsBean")
@SessionScoped
public class PointsBean implements Serializable {
	private List<Point> pointsCollection = new ArrayList<>();
	
	private Point pointField = new Point();
	private Point pointGraphic = new Point();
	
	
	public void uploadPoints() {
		pointsCollection = DatabaseManager.getInstance().getCollectionFromDataBase();
	}
	
	public void submitFieldPoints() {
		if (InputValidator.isPointValid(pointField))
			addPointWithCalculatedResultToDatabase(pointField);
	}
	
	public void submitGraphicPoints() {// нужно чтобы точки на графике добавлялись только с радиусом
		if (InputValidator.isRValid(pointGraphic.getR()))
			addPointWithCalculatedResultToDatabase(pointGraphic);
	}
	

	
	@SneakyThrows
	private void addPointWithCalculatedResultToDatabase(Point point) {
		point.setResult(AreaResultChecker.isPointInArea(point));
		DatabaseManager.getInstance().addPoint((Point) point.clone());
	}

}
