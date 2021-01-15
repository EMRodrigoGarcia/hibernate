package org.dam2.taller;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.dam2.hibernate.Alumno;
public class GenericJPADAO <T,K> implements DAOInterface <T,K>{
	
	private  final static String PERSITENCEUNITNAME = "hibernate";
	
	private Class <T> entityClass;
	private String persitenceUnitName;
	

	public GenericJPADAO(Class<T> entityClass) {
		this.entityClass = entityClass;
		persitenceUnitName = PERSITENCEUNITNAME;
	}
	
	public GenericJPADAO(Class<T> entityClass, String persitenceUnitName) {
		this.entityClass = entityClass;
		this.persitenceUnitName = persitenceUnitName;
	}

	@Override
	public Iterable<T> findAll() {
		// TODO Auto-generated method stub
		List<T> result;
		String jpaQuery;
		
		EntityManagerFactory emFactory = EntityManagerFactorySingleton.getInstance(persitenceUnitName).getEmf();
		
		EntityManager em = emFactory.createEntityManager();

		jpaQuery = "SELECT o FROM " + entityClass.getSimpleName() + " o";
		Query query= em.createQuery(jpaQuery);
		
				
		result = query.getResultList();

		em.close();
		
		return result;
	}

	@Override
	public T delete(T ov) {
		// TODO Auto-generated method stub
		EntityManagerFactory emFactory = EntityManagerFactorySingleton.getInstance(persitenceUnitName).getEmf();
		
		EntityManager em = emFactory.createEntityManager();	
		
		
		Object key = getKey (ov);
		
		if (key != null)
		{
			try {
				em.getTransaction().begin();
				ov = em.find(entityClass, key);
				
				if (ov != null)
					em.remove(ov);
				else
					ov = null;
				em.getTransaction().commit();
			
			}
			catch (Exception e)
			{
				ov = null;
			}
			
			finally {
				em.close();
			}
		}
		else
			ov = null;
		
		return ov;

	}

	@Override
	public T save(T ov) {
		// TODO Auto-generated method stub
		EntityManagerFactory emFactory = EntityManagerFactorySingleton.getInstance(persitenceUnitName).getEmf();
		
		EntityManager em = emFactory.createEntityManager();	
		
		try {
			em.getTransaction().begin();
		
			em.persist(ov);
			
			em.getTransaction().commit();
		
		}
		catch (Exception e)
		{
			ov = null;
		}
		
		finally {
			em.close();
		}
		
		return ov;
	
	}

	@Override
	public T update(T ov) {
		// TODO Auto-generated method stub
		EntityManagerFactory emFactory = EntityManagerFactorySingleton.getInstance(persitenceUnitName).getEmf();
		
		EntityManager em = emFactory.createEntityManager();	
		
		try {
			em.getTransaction().begin();
		
			ov = em.merge(ov);
			
			em.getTransaction().commit();
		
		}
		catch (Exception e)
		{
			ov = null;
		}
		
		finally {
			em.close();
		}
		
		return ov;
	}
	
	
	private Object getKey (Object object)  
	{
		
		String nameGet;
		Object valor = null;
		boolean key;
		
		
		// Comprobar si el atributo tiene una anotación Id
		Predicate<Field> isKey = f -> Arrays.stream(f.getAnnotations()).
										anyMatch(a ->a.annotationType().getSimpleName().equals("Id"));
		
		// Obtener atributo clave
		Optional<Field> field = Arrays.stream(entityClass.getDeclaredFields()).filter(isKey).findFirst();
		
		if (field.isPresent())
		{
			// Crear método get de la clave
			Field f = field.get();
			nameGet = "get" + f.getName().substring(0, 1).toUpperCase()+ f.getName().substring(1);
			
			// Obtener el valor de la clave
			try {
				valor = entityClass.getDeclaredMethod(nameGet, null).invoke(object, null);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException e) {
				// TODO Auto-generated catch block
				valor = null;
			}
		}		

		
		return valor;
	}

	@Override
	public Optional<T> findById(K key) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
