package com.laura.universidad.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.laura.universidad.models.Profesor;
import com.laura.universidad.services.ProfesorServices;

@Controller
@RequestMapping("/profesores")
public class ProfesorController {

    @Autowired
    ProfesorServices profesorService;

    @Value("${pagination.size}")
    int sizePage;

    @GetMapping(value = "/list")
    public ModelAndView list(Model model) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:list/1/codigo/asc");

        return modelAndView;
    }

    @GetMapping(value = "/list/{numPage}/{fieldSort}/{directionSort}")
    public ModelAndView listPage(Model model,
            @PathVariable("numPage") Integer numPage,
            @PathVariable("fieldSort") String fieldSort,
            @PathVariable("directionSort") String directionSort) {


        Pageable pageable = PageRequest.of(numPage - 1, sizePage,
            directionSort.equals("asc") ? Sort.by(fieldSort).ascending() : Sort.by(fieldSort).descending());

        Page<Profesor> page = profesorService.findAll(pageable);

        List<Profesor> proveedores = page.getContent();

        ModelAndView modelAndView = new ModelAndView("proveedores/list");
        modelAndView.addObject("proveedores", proveedores);

        modelAndView.addObject("numPage", numPage);
        modelAndView.addObject("totalPages", page.getTotalPages());
        modelAndView.addObject("totalElements", page.getTotalElements());

        modelAndView.addObject("fieldSort", fieldSort);
        modelAndView.addObject("directionSort", directionSort.equals("asc") ? "asc" : "desc");

        return modelAndView;
    }

    @GetMapping(path = { "/create" })
    public ModelAndView create(Profesor profesor) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("profesor", new Profesor());
        modelAndView.setViewName("profesores/new");
        return modelAndView;
    }

    @GetMapping(path = { "/edit/{codigo}" })
    public ModelAndView edit(
            @PathVariable(name = "codigo", required = true) String codigo) {

        Profesor profesor = profesorService.findById(codigo);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("profesor", profesor);
        modelAndView.setViewName("profesores/edit");

        return modelAndView;
    }

    @PostMapping(path = { "/save" })
    public ModelAndView save(Profesor profesor) {

        profesorService.insert(profesor);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:edit/" + profesor.getCodigo());
        return modelAndView;
    }

    @PostMapping(path = { "/update" })
    public ModelAndView update(Profesor profesor) {

        profesorService.update(profesor);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:edit/" + profesor.getCodigo());

        return modelAndView;
    }

    @GetMapping(path = { "/delete/{codigo}" })
    public ModelAndView delete(
            @PathVariable(name = "codigo", required = true) String codigo) {

        profesorService.delete(codigo);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/profesores/list");

        return modelAndView;
    }
}
