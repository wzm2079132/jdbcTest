package com.neuedu.test;

import java.sql.ResultSet;

public interface RowMap<T> {
    public T rowMap(ResultSet rSet);
}
