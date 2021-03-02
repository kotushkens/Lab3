package controller;

import model.Point;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;
// класс для датабазы
public class DatabaseManager {
	public static DatabaseManager instance; //объявление
	
	private final EntityManager entityManager;
	
	private DatabaseManager() {
		entityManager = Persistence.createEntityManagerFactory("Point").createEntityManager();
	}//создает фабрику точек
	
	public static DatabaseManager getInstance() {// просто геттер
		if (instance == null)
			instance = new DatabaseManager();
		return instance;
	}
	
	public List<Point> getCollectionFromDataBase() {// геттер для инфы из бд
		return entityManager.createQuery("SELECT point FROM points point", Point.class).getResultList();
	}
	
	public void addPoint(Point point) {// добавляет точку в датабазу
		if (point.getX() == null || point.getY() == null || point.getR() == null || point.getResult() == null)
			return;
		entityManager.getTransaction().begin();
		entityManager.persist(point);
		entityManager.getTransaction().commit();
	}

}
