package com.wang.advance.method;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

/**
 * @author Wang
 * @since 2022/1/22
 */
public class DeleteAll extends AbstractMethod {
    public DeleteAll() {
        super("deleteAll");
    }
    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        String sql;
        String method = "deleteAll";
        String tableName = tableInfo.getTableName();
        if (tableInfo.isWithLogicDelete()) { // 逻辑删除
            // update user set deleted = 1 where delete = 0
            sql = "UPDATE " + tableName  + " " + sqlLogicSet(tableInfo)
                    + " WHERE " + tableInfo.getLogicDeleteSql(false, true);
        } else {
            sql = "DELETE FROM " + tableName; // 真删除
        }
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
        return addDeleteMappedStatement(mapperClass, method, sqlSource);
    }
}
