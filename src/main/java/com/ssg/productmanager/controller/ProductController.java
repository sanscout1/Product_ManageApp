package com.ssg.productmanager.controller;

import com.ssg.productmanager.DTO.PageRequestDTO;
import com.ssg.productmanager.DTO.ProductDTO;
import com.ssg.productmanager.service.ProductSerive;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Log4j2
@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductSerive productSerive;

    @GetMapping("/register")
    public void register() {
        log.info("register get");
    }

    @PostMapping("/register")
    public String registerPost(@Valid ProductDTO dto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        log.info("register post");
        if (bindingResult.hasErrors()) {
            log.info("error");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/product/register";
        }
        log.info(dto);
        productSerive.register(dto);
        return "redirect:/product/list";
    }


    //    @GetMapping("/list")
//    public void list(Model model){
//        model.addAttribute("dtoList",productSerive.listAll());
//    }
    @GetMapping("/list")
    public void list(@Valid PageRequestDTO pageRequestDTO, BindingResult bindingResult, Model model) {
        log.info("todo list......");
        if (bindingResult.hasErrors()) {
            pageRequestDTO = PageRequestDTO.builder().build();
        }
        model.addAttribute(("responseDTO"), productSerive.getList(pageRequestDTO));
    }

    @GetMapping({"/read", "/modify"})
    public void listOne(Model model, Long pno) {
        log.info("read one list");
        ProductDTO dto = productSerive.getOne(pno);
        model.addAttribute("dto", dto);
    }

    @PostMapping("/modify")
    public String modify(@Valid ProductDTO dto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            log.info("error");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addAttribute("pno", dto.getPno());
            return "redirect:/product/modify";
        }
        productSerive.modify(dto);
        return "redirect:/product/list";
    }

    @PostMapping("/remove")
    public String remove(Long pno, RedirectAttributes redirectAttributes) {
        log.info("-------------remove------------------");
        log.info("pno: " + pno);
        productSerive.remove(pno);
        return "redirect:/product/list";
    }
}
