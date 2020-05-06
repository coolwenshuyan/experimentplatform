package com.coolwen.experimentplatform.dao.basedao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * @author CoolWen
 * @version 2018-10-24 18:08
 */
//这个表示该接口不会创建这个接口的实例
@NoRepositoryBean
@Transactional(readOnly = true)
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {
    public List<Object[]> listBySQL(String sql);

    //批量操作使用SQL方法
    @Transactional
    public void updateBySql(String sql, Object... args);

    //批量操作使用HQL方法
    @Transactional
    public void updateByHql(String hql, Object... args);

}
