package com.will.loja.context.impl;

import com.will.loja.context.IContext;
import com.will.loja.exception.CustomException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.support.Repositories;

@Configuration
public class ContextImpl  implements ApplicationContextAware, IContext {
    private ApplicationContext context;

    private Repositories repositories;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
        this.repositories = new Repositories(context);
    }

    public <S> S getBean(Class<S> clazz) {
        try {
            return context.getAutowireCapableBeanFactory().getBean(clazz);
        }catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }
}