package com.wang.project.component;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.wang.project.entity.Account;
import com.wang.project.entity.BaseEntity;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 自动填充
 *
 * @author Wang
 * @since 2022/1/23
 */
@Component
public class BaseEntityMetaObjectHandler implements MetaObjectHandler {


    @Override
    public void insertFill(MetaObject metaObject) {
        // 创建时间自动填充
        if (metaObject.hasSetter(BaseEntity.CREATE_TIME) && getFieldValByName(BaseEntity.CREATE_TIME, metaObject) == null) {
            this.strictInsertFill(metaObject, BaseEntity.CREATE_TIME, LocalDateTime.class, LocalDateTime.now());
        }
        /// 创建人自动填充
        if (metaObject.hasSetter(BaseEntity.CREATE_ACCOUNT_ID)
                && ObjectUtil.isNotNull(getFieldValByName(BaseEntity.CREATE_ACCOUNT_ID, metaObject))) {

            Account account = (Account) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())
                    .getAttribute("account", RequestAttributes.SCOPE_SESSION);

            if (ObjectUtil.isNotNull(account)) {
                this.strictInsertFill(metaObject, BaseEntity.CREATE_TIME, Long.class, account.getCreateAccountId());
            }

        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 修改时间自动填充
        if (metaObject.hasSetter(BaseEntity.MODIFIED_TIME) && getFieldValByName(BaseEntity.MODIFIED_TIME, metaObject) == null) {
            this.strictUpdateFill(metaObject, BaseEntity.MODIFIED_TIME, LocalDateTime.class, LocalDateTime.now());
        }
        /// 修改人自动填充
        if (metaObject.hasSetter(BaseEntity.MODIFIED_ACCOUNT_ID)
                && ObjectUtil.isNotNull(getFieldValByName(BaseEntity.MODIFIED_ACCOUNT_ID, metaObject))) {

            Account account = (Account) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())
                    .getAttribute("account", RequestAttributes.SCOPE_SESSION);

            if (ObjectUtil.isNotNull(account)) {
                this.strictUpdateFill(metaObject, BaseEntity.MODIFIED_ACCOUNT_ID, Long.class, account.getModifiedAccountId());
            }
        }
    }
}
