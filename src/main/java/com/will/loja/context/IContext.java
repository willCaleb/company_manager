package com.will.loja.context;

import com.will.loja.context.impl.ContextImpl;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IContext {

    IContext context = new ContextImpl();

    <T> T getBean(Class<T> clazz);

    static IContext context() {
        return context;
    }

}