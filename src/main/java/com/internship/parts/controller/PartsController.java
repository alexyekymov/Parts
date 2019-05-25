package com.internship.parts.controller;

import com.internship.parts.model.Part;
import com.internship.parts.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class PartsController {
    private final PartService partService;
    private int page;


    @Autowired
    public PartsController(PartService partService) {
        this.partService = partService;
    }

    @GetMapping("/")
    public ModelAndView allParts(@RequestParam(defaultValue = "1") int page) {
        this.page = page;
        List<Part> parts = partService.allParts(page);
        int partsCount = partService.partsCount();
        int pagesCount = (partsCount + 9) / 10;
        int assembledPC = partService.getAssembledComputers();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("parts");
        modelAndView.addObject("page", page);
        modelAndView.addObject("partsList", parts);
        modelAndView.addObject("partsCount", partsCount);
        modelAndView.addObject("pagesCount", pagesCount);
        modelAndView.addObject("assembledPC", assembledPC);
        return modelAndView;
    }

    @GetMapping("/search")
    public ModelAndView search(@RequestParam(defaultValue = "1") int page,
                               @RequestParam(value = "search", required = false) String search) {
        List<Part> searchList = partService.search(search, page);
        this.page = page;
        int partsCount = partService.getSortPartCount();
        int pagesCount = (partsCount + 9) / 10;
        int assembledPC = partService.getAssembledComputers();
        ModelAndView modelAndView = new ModelAndView("search");
        modelAndView.addObject("page", page);
        modelAndView.addObject("search", search);
        modelAndView.addObject("searchList", searchList);
        modelAndView.addObject("partsCount", partsCount);
        modelAndView.addObject("pagesCount", pagesCount);
        modelAndView.addObject("assembledPC", assembledPC);
        return modelAndView;
    }

    @GetMapping("/filter")
    public ModelAndView necessaryParts(@RequestParam(defaultValue = "1") int page,
                                       @RequestParam(required = false) boolean necessary){
        List<Part> necessaryParts = partService.getNecessaryParts(necessary, page);
        this.page = page;
        int partsCount = partService.getSortPartCount();
        int pagesCount = (partsCount + 9) / 10;
        int assembledPC = partService.getAssembledComputers();
        ModelAndView modelAndView = new ModelAndView("filter");
        modelAndView.addObject("page", page);
        modelAndView.addObject("necessary", necessary);
        modelAndView.addObject("necessaryParts", necessaryParts);
        modelAndView.addObject("partsCount", partsCount);
        modelAndView.addObject("pagesCount", pagesCount);
        modelAndView.addObject("assembledPC", assembledPC);
        return modelAndView;
        }

    @GetMapping("/add")
    public ModelAndView addPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("edit");
        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView addPart(@ModelAttribute("part") Part part) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/?page=");
        if (part.getId() == 0) {
            this.partService.add(part);
        } else {
            this.partService.edit(part);
        }
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editPage(@PathVariable("id") int id) {
        Part part = partService.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("edit");
        modelAndView.addObject("part", part);
        return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView editPart(@ModelAttribute("part") Part part) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/?page=" + this.page);
        partService.edit(part);
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public String deletePart(@PathVariable("id") int id) {
        Part part = partService.getById(id);
        this.partService.delete(part);
        return "redirect:/?page=" + this.page;
    }
}
