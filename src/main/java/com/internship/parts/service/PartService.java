package com.internship.parts.service;

import com.internship.parts.model.Part;

import java.util.List;

public interface PartService {
    List<Part> allParts(int page);
    void add(Part part);
    void delete(Part part);
    void edit(Part part);
    Part getById(int id);
    int partsCount();
    List<Part> search(String search, int page);
    int getSortPartCount();
    List<Part> getNecessaryParts(boolean necessary, int page);
    int getAssembledComputers();
}
