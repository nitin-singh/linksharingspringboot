package com.ls.controller;

import com.ls.dto.DatatableResponseVO;
import com.ls.dto.DatatableSearchCO;
import com.ls.dto.SampleDTO;
import com.ls.dto.SampleSearchDTO;
import com.ls.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DefaultController {
    @GetMapping("/")
    public String homeBlank() {
        return "/home";
    }

    @GetMapping("/home")
    public String home() {
        return "/home";
    }

    @GetMapping("/admin")
    public String admin() {
        return "/admin";
    }

    @GetMapping("/user")
    public String user() {
        return "/user";
    }

    @GetMapping("/about")
    public String about() {
        return "/about";
    }

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @GetMapping("/403")
    public String error403() {
        return "/error/403";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("user", new User());
        return "/registration";
    }

    @RequestMapping(value = "/showSampleListPage")
    public String showSampleList() {
        return "/sampleList";
    }

    @RequestMapping(value = "/fetchSampleList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    ResponseEntity<DatatableResponseVO<SampleDTO>> fetchList(SampleSearchDTO datatableSearchCO) {
        if (datatableSearchCO != null) {
            System.out.println(datatableSearchCO.getSortColumn());
            System.out.println(datatableSearchCO.getMax());
            System.out.println(datatableSearchCO.getOffset());
            System.out.println(datatableSearchCO.getOrder());
            System.out.println(datatableSearchCO.firstName);
            System.out.println(datatableSearchCO.middleName);
            System.out.println(datatableSearchCO.lastName);
            System.out.println(datatableSearchCO.address);
            System.out.println(datatableSearchCO.phoneNumber);
        }
        List<SampleDTO> list = new ArrayList<SampleDTO>();
        for (int i = 1; i <= 10; i++) {
            list.add(new SampleDTO("nitin", "kumar", "singh", "9953801744" + i, "address" + i));
        }
        DatatableResponseVO<SampleDTO> dtoDatatableResponseVO = new DatatableResponseVO<SampleDTO>(10L, 10L, list);
        return new ResponseEntity<DatatableResponseVO<SampleDTO>>(dtoDatatableResponseVO, HttpStatus.OK);
    }
}
