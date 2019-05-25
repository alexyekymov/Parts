package com.internship.parts.dao;

import com.internship.parts.model.Part;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PartDAOImpl implements PartDAO {
    private SessionFactory sessionFactory;
    private int sortPartCount;

    public int getSortPartCount() {
        return sortPartCount;
    }

    @Autowired
    public void setSession(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Part> allParts(int page) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Part");
        return query.setFirstResult(10 * (page - 1)).setMaxResults(10).list();
    }

    @Override
    public void add(Part part) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(part);
    }

    @Override
    public void delete(Part part) {
        Session session = sessionFactory.getCurrentSession();
        if (part != null)
            session.delete(part);
    }

    @Override
    public void edit(Part part) {
        Session session = sessionFactory.getCurrentSession();
        session.update(part);
    }

    @Override
    public Part getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Part.class, id);
    }

    @Override
    public int partsCount() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select count(*) from Part", Number.class).getSingleResult().intValue();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Part> search(String search, int page) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Part where lower(name) like :search");
        query.setParameter("search", "%" + search + "%");
        sortPartCount = query.list().size();
        return query.setFirstResult(10 * (page - 1)).setMaxResults(10).list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Part> getNecessaryParts(boolean necessary, int page) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Part where necessary = :necessary");
        query.setParameter("necessary", necessary);
        sortPartCount = query.list().size();
        return query.setFirstResult(10 * (page - 1)).setMaxResults(10).list();
    }

    @Override
    public int getAssembledComputers() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select min(quantity) from Part where necessary = true", Number.class).getSingleResult().intValue();
    }
}
