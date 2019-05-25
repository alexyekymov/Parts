package com.internship.parts.service;

import com.internship.parts.dao.PartDAO;
import com.internship.parts.model.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PartServiceImpl implements PartService {
    private final PartDAO partDAO;

    @Autowired
    public PartServiceImpl(PartDAO partDAO) {
        this.partDAO = partDAO;
    }

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<Part> allParts(int page) {
        return partDAO.allParts(page);
    }

    @Override
    @Transactional
    public void add(Part part) {
        partDAO.add(part);
    }

    @Override
    @Transactional
    public void delete(Part part) {
        partDAO.delete(part);
    }

    @Override
    @Transactional
    public void edit(Part part) {
        partDAO.edit(part);
    }

    @Override
    @Transactional
    public Part getById(int id) {
        return partDAO.getById(id);
    }

    @Override
    @Transactional
    public int partsCount() {
        return partDAO.partsCount();
    }

    @Override
    @Transactional
    public List<Part> search(String search, int page) {
        return partDAO.search(search, page);
    }

    public int getSortPartCount() {
        return partDAO.getSortPartCount();
    }

    @Override
    @Transactional
    public List<Part> getNecessaryParts(boolean necessary, int page) {
        return partDAO.getNecessaryParts(necessary, page);
    }

    @Override
    @Transactional
    public int getAssembledComputers() {
        return partDAO.getAssembledComputers();
    }
}
