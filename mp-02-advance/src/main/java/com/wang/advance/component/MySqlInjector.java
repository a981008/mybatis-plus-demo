package com.wang.advance.component;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.extension.injector.methods.AlwaysUpdateSomeColumnById;
import com.baomidou.mybatisplus.extension.injector.methods.InsertBatchSomeColumn;
import com.baomidou.mybatisplus.extension.injector.methods.LogicDeleteBatchByIds;
import com.wang.advance.method.DeleteAll;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * SQL 注入器
 *
 * @author Wang
 * @since 2022/1/22
 */
@Component
public class MySqlInjector extends DefaultSqlInjector {
    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass, TableInfo tableInfo) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass, tableInfo);

        methodList.add(new DeleteAll());
        methodList.add(new InsertBatchSomeColumn(t -> !t.isLogicDelete())); // 排除逻辑删除字段
        methodList.add(new LogicDeleteBatchByIds("testDeleteBatch"));
        methodList.add(new AlwaysUpdateSomeColumnById(t -> !t.getProperty().equals("name"))); // 排除 name 字段

        return methodList;
    }
}
