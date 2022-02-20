package org.formation.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.formation.dao.MovieDAO;
import org.formation.model.Movie;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import lombok.extern.java.Log;


@Service
@Log
public class MovieLister implements BeanNameAware, BeanClassLoaderAware, BeanFactoryAware, ResourceLoaderAware, ApplicationEventPublisherAware,
MessageSourceAware, ApplicationContextAware, BeanPostProcessor, InitializingBean
{

	@Autowired
	private MovieDAO movieDao;

	public List<Movie> moviesDirectedBy(String director) {
		
		return movieDao.findAll().stream().filter(m -> m.getDirector().equalsIgnoreCase(director)).collect(Collectors.toList());
	}
	
	@PostConstruct
	public void init() {
		StackTraceElement[] stackTrace = Thread.currentThread()
			      .getStackTrace();
		log.info(stackTrace[1].getMethodName());
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		StackTraceElement[] stackTrace = Thread.currentThread()
			      .getStackTrace();
		log.info(stackTrace[1].getMethodName());		
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		StackTraceElement[] stackTrace = Thread.currentThread()
			      .getStackTrace();
		log.info(stackTrace[1].getMethodName());		
	}

	@Override
	public void setMessageSource(MessageSource messageSource) {
		StackTraceElement[] stackTrace = Thread.currentThread()
			      .getStackTrace();
		log.info(stackTrace[1].getMethodName());		
	}

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		StackTraceElement[] stackTrace = Thread.currentThread()
			      .getStackTrace();
		log.info(stackTrace[1].getMethodName());		
	}

	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		StackTraceElement[] stackTrace = Thread.currentThread()
			      .getStackTrace();
		log.info(stackTrace[1].getMethodName());		
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		StackTraceElement[] stackTrace = Thread.currentThread()
			      .getStackTrace();
		log.info(stackTrace[1].getMethodName());		
	}

	@Override
	public void setBeanClassLoader(ClassLoader classLoader) {
		StackTraceElement[] stackTrace = Thread.currentThread()
			      .getStackTrace();
		log.info(stackTrace[1].getMethodName());		
	}

	@Override
	public void setBeanName(String name) {
		StackTraceElement[] stackTrace = Thread.currentThread()
			      .getStackTrace();
		log.info(stackTrace[1].getMethodName());		
	}
	
}
