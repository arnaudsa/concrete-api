package com.concrete.mock;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.apache.commons.beanutils.converters.BooleanConverter;
import org.apache.commons.beanutils.converters.DoubleConverter;
import org.apache.commons.beanutils.converters.FloatConverter;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.commons.beanutils.converters.LongConverter;

/**
 * Classe abstrata para geração de Mocks para test de JUnit.
 * 
 * @author Arnaud Santana Alves
 * @since 17/07/2016
 * 
 * @param <T>
 *            Entidade ao qual será criada o objeto Mock.
 * 
 * 
 */
public abstract class MockGenerator<T> {

	static {
		// Adicionando os register para caso algum campo seja null.
		ConvertUtils.register(new BigDecimalConverter(null), BigDecimal.class);
		ConvertUtils.register(new LongConverter(null), Long.class);
		ConvertUtils.register(new BooleanConverter(null), Boolean.class);
		ConvertUtils.register(new DoubleConverter(null), Double.class);
		ConvertUtils.register(new IntegerConverter(null), Integer.class);
		ConvertUtils.register(new FloatConverter(null), Float.class);
	}

	/**
	 * M�todo para cria��o de mock para {@link T}.
	 * 
	 * @return Mock criado para {@link T}.
	 */
	public abstract T createMock();

	/**
	 * M�todo para criar uma lista de {@link T}.
	 * 
	 * @return Lista com os arquivos mock para {@link T}.
	 */
	public List<T> createMockList() {
		final List<T> objs = new ArrayList<T>();
		final T mock = createMock();
		if (mock != null) {
			objs.add(mock);
		}
		return objs;
	}

	/**
	 * M�todo para criar uma lista de {@link T}.
	 * 
	 * @param numeroDeOcorrencias
	 *            N�mero de ocorr�ncias de {@link T} a serem devolvidas na Lista
	 * @return Lista com os arquivos mock para {@link T}.
	 */
	public List<T> createMockList(final int numeroDeOcorrencias) {
		final List<T> objs = new ArrayList<T>(numeroDeOcorrencias);
		for (int i = 0; i < numeroDeOcorrencias; i++) {
			objs.add(createMock());
		}
		return objs;
	}

	/**
	 * M�todo para criar uma lista de mock personalizados para a entidade
	 * {@link T} .
	 * 
	 * @param valores
	 *            Field e Valor que dever� ser alterado na cria��o dos Mock.
	 * @return Lista de mock personalizados para a entidade {@link T}.
	 * 
	 * @throws MockGeneratorException
	 *             MockGeneratorException
	 */
	public List<T> createMockList(final Map<String, Object> valores) throws MockGeneratorException {
		final List<T> objs = new ArrayList<T>();
		objs.add(createMock(valores));
		return objs;
	}

	/**
	 * M�todo para criar uma lista de mock personalizados para a entidade
	 * {@link T} .
	 * 
	 * @param valores
	 *            Field e Valor que dever� ser alterado na cria��o dos Mock.
	 * @param numeroDeOcorrencias
	 *            N�mero de ocorr�ncias de {@link T} a serem devolvidas na Lista
	 * @return Lista de mock personalizados para a entidade {@link T}.
	 * 
	 * @throws MockGeneratorException
	 *             MockGeneratorException
	 */
	public List<T> createMockList(final Map<String, Object> valores, final int numeroDeOcorrencias)
			throws MockGeneratorException {
		final List<T> objs = new ArrayList<T>();
		for (int i = 0; i < numeroDeOcorrencias; i++) {
			objs.add(createMock(valores));
		}
		return objs;
	}

	/**
	 * M�todo para criar mock personalizado para a entidade {@link T} .
	 * 
	 * @param valores
	 *            Field e Valor que dever� ser alterado na cria��o dos Mock.
	 * @return Objeto mock personalizado para a entidade {@link T}.
	 * 
	 * @throws MockGeneratorException
	 *             MockGeneratorException
	 */
	public T createMock(final Map<String, Object> valores) throws MockGeneratorException {

		try {
			final T obj = createMock();
			BeanUtils.populate(obj, valores);
			return obj;
		} catch (final IllegalAccessException e) {
			throw new MockGeneratorException(e.getMessage(), e);
		} catch (final InvocationTargetException e) {
			throw new MockGeneratorException(e.getMessage(), e);
		}

	}

	/**
	 * Cria o Mock adicionando espa�os ao inicio e ao final dos atributos
	 * String. �til para realizar testes de trim.
	 * 
	 * @return Mock
	 */
	public T createMockForTrimTest() {
		final T t = createMock();
		modifyMockForTrimTest(t);
		return t;
	}

	/**
	 * Modifica os atributos String adicionando espa�os ao inicio e ao final dos
	 * valores dos strings. �til para realizar testes de trim.
	 * 
	 * @param obj
	 *            Objeto
	 * @return O pr�prio objeto
	 */
	public T modifyMockForTrimTest(final T obj) {
		final PropertyDescriptor[] propertyDescriptors = PropertyUtils.getPropertyDescriptors(obj);
		try {
			for (final PropertyDescriptor p : propertyDescriptors) {
				if (String.class.equals(p.getPropertyType())) {
					final String pName = p.getName();
					final String actualValue = BeanUtils.getProperty(obj, pName);
					final StringBuilder newValue = new StringBuilder();
					newValue.append(" ").append(actualValue).append(" ");
					BeanUtils.setProperty(obj, pName, newValue.toString());
				}
			}
		} catch (final IllegalAccessException e) {
			throw new MockGeneratorException(e.getMessage(), e);
		} catch (final InvocationTargetException e) {
			throw new MockGeneratorException(e.getMessage(), e);
		} catch (final NoSuchMethodException e) {
			throw new MockGeneratorException(e.getMessage(), e);
		}
		return obj;
	}

	/**
	 * M�todo para executar os assert do mock, por�m esse m�todo somente �
	 * aplicado para casos onde os valores a serem validados, sejam os mesmos da
	 * cria��o normal do Mock, ou seja, sem a "personaliza��o" dos valores.
	 * 
	 * @param mock
	 *            Objeto que ser� testado.
	 */
	public abstract void assertMock(T mock);

	/**
	 * Gera uma data para teste, adicionando a quantidade de dias informados na
	 * data inicial.
	 * 
	 * @param datBase
	 *            Data inicial.
	 * @param qtdDias
	 *            quantidade de dias antes da data de hoje.
	 * @return Date
	 */
	public static Calendar createData(final Date datBase, final int qtdDias) {
		final Calendar cal = Calendar.getInstance();
		cal.setTime(datBase);
		cal.add(Calendar.DAY_OF_MONTH, qtdDias);
		return cal;
	}

}
