package org.dam2.taller;

import java.util.Optional;

public interface DAOInterface<T, K> {
	
	
	public Iterable<T> findAll();
	
	public T delete (T ov);
	
	public T save (T ov);
	
	public T update (T ov);

	public Optional<T> findById(K key);
	
}
