package com.wang.advance.component;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.wang.advance.method.DeleteAll;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Wang
 * @since 2022/1/22
 */
@Component
public class MySqlInjector extends DefaultSqlInjector {
    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass, TableInfo tableInfo) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass, tableInfo);
        methodList.add(new DeleteAll());
        return methodList;
    }
}
