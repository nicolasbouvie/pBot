package br.com.jarvis.pbot.model;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public abstract class DAO<B> {
	private static final Session session;
	
	static {
		Configuration cfg = new Configuration().configure("/hibernate.cfg.xml");
		if (Boolean.getBoolean("test")) {
			cfg.setProperty("hibernate.connection.autocommit", "false");
			cfg.setProperty("hibernate.connection.url", "jdbc:hsqldb:mem:DeploymentMonitorDB");
		}
		StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
		session = cfg.buildSessionFactory(serviceRegistry).openSession();
	}
	
	public abstract Serializable getId();
	
	public boolean add() {
		Serializable saved = getSession().save(this);
		return getId() != null && getId().equals(saved);
	}
	
	public void update() {
		getSession().update(this);
	}
	
	public void delete() {
		getSession().delete(this);
		getSession().flush();
	}
	
	public static <T> T getByID(Class<T> type, long id) {
		String hql = new StringBuilder("from ").append(type.getSimpleName()).append(" t where t.id = :id").toString();

		Query query = getSession().createQuery(hql).setParameter("id", id);

		return type.cast(query.uniqueResult());
	}
	
	public static <T> T getSingle(Class<T> type, String by, String value) {
		String hql = new StringBuilder("from ").append(type.getSimpleName()).append(String.format(" t where t.%s = :%s", by, by)).toString();

		Query query = getSession().createQuery(hql).setParameter(by, value);

		return type.cast(query.uniqueResult());
	}

	@SuppressWarnings("unchecked")
	public static <T> List<T> getList(Class<T> type, String by, String value) {
		String hql = new StringBuilder("from ").append(type.getSimpleName()).append(String.format(" t where t.%s = :%s", by, by)).toString();

		Query query = getSession().createQuery(hql).setParameter(by, value);

		return query.list();
	}

	public static boolean delete(Class<?> type, long id) {
		String hql = new StringBuilder("delete from ").append(type.getSimpleName()).append(" t where t.id = :id").toString();

		Query query = getSession().createQuery(hql).setParameter("id", id);

		return 1 == query.executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends DAO<?>> List<T> getAll(Class<T> type) {
		return (List<T>) getSession().createCriteria(type).list();
	}
	
	public static Transaction getTransaction() {
		return getSession().beginTransaction();
	}

	public abstract B toBean();

	public static Session getSession() {
		return session;
	}
}