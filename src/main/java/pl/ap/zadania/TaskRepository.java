package pl.ap.zadania;


import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class TaskRepository {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public TaskRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
        entityManager = entityManagerFactory.createEntityManager();
    }

    public List<Task> notReadyTasks() {
        TypedQuery<Task> query = entityManager.createQuery("Select t from Task t WHERE t.taskStatus = false", Task.class);
        List<Task> resultList = query.getResultList();
        return resultList;
    }

    public List<Task> readyTask() {
        TypedQuery<Task> query = entityManager.createQuery("Select t from Task t WHERE t.taskStatus = true ", Task.class);
        List<Task> resultList = query.getResultList();
        return resultList;
    }




    public Task findById(Long id) {
        return entityManager.find(Task.class, id);

    }

    public void save(Task task) {
        entityManager.getTransaction().begin();
        entityManager.persist(task);
        entityManager.getTransaction().commit();

    }
}
