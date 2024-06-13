package com.will.loja.service.impl;

import com.will.loja.service.IDatabaseService;
import jakarta.persistence.Table;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DatabaseService implements IDatabaseService {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void createTable(Class<?> clazz, String schema) {
        if (!clazz.isAnnotationPresent(Table.class)) {
            return;
        }
        String name = clazz.getAnnotation(Table.class).name();

//        if (doesTableExist(schema, name)) {
//            return;
//        }
        jdbcTemplate.execute(createBaseQuery(name, schema));
    }

    private String createBaseQuery( String name, String schema) {
        String query = "CREATE TABLE IF NOT EXISTS " + schema + "." + name + "()";
        query = query.concat(" inherits (public." + name + ")");
        return query;
    }

    private boolean doesTableExist(String schema, String tableName) {
        String query = "SELECT COUNT(*) FROM information_schema.tables WHERE table_schema = ? AND table_name = ?";
        Integer count = jdbcTemplate.queryForObject(query, new Object[]{schema, tableName}, Integer.class);
        return count != null && count > 0;
    }
}
