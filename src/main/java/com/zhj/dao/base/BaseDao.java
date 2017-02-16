package com.zhj.dao.base;

import com.sun.org.apache.regexp.internal.RE;
import com.zhj.common.Bean.PageBean;
import com.zhj.vo.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

/**
 * Created by Administrator on 2017/2/11.
 */
public class BaseDao extends HibernateDaoSupport {

    /**
     * 若不要这方法则启动项目时会报错：java.lang.IllegalArgumentException: 'sessionFactory' or 'hibernateTemplate' is required
     *
     * @param sessionFactory
     */
    /*@Autowired
    protected void setSessionFactoryOverride(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }*/
    @Autowired
    protected void setTemplate(HibernateTemplate hibernateTemplate) {
        super.setHibernateTemplate(hibernateTemplate);
    }


    public List findByHql(final String hql, final Object[] values, final PageBean pageBean) {
        Object execute = super.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            public Object doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery(hql);
                if (null != values && values.length > 0) {
                    for (int i = 0, size = values.length; i < size; i++) {
                        query.setParameter(i, values[i]);
                    }
                }
                if (null != pageBean && null != pageBean.getPageNum() && null != pageBean.getPageSize()) {
                    int start = (pageBean.getPageNum() - 1) * pageBean.getPageSize();
                    int end = pageBean.getPageSize();

                    return query.setFirstResult(start).setMaxResults(end).list();
                } else {
                    return query.list();
                }
            }
        });
        return (List) execute;
    }

    public List findByHQL(final String hql, final Object[] params, PageBean pageBean) {
        List list = null;
        Session session = super.currentSession();
        Query query = session.createQuery(hql);
        if (null != params && params.length > 0) {
            for (int i = 0, size = params.length; i < size; i++) {
                query.setParameter(i, params[i]);
            }
        }
        list = query.list();
        /*if (null != session) {
            session.close();
        }*/
        return list;
    }
}
