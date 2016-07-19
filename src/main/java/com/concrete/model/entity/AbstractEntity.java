/**
 * 
 */
package com.concrete.model.entity;

import java.io.Serializable;

/**
 * @author Arnaud Santana Alves
 * @since 17/07/2016
 *
 */
public abstract class AbstractEntity implements Serializable {

	private static final long serialVersionUID = -3337211707202934587L;

	@Override
	public abstract int hashCode();

	@Override
	public abstract boolean equals(Object obj);

}
