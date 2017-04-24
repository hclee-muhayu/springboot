package com.muhayu.controller;

import com.muhayu.MyData;
import com.muhayu.repositories.MyDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by hyecheon on 2017. 4. 16..
 */
@Controller
public class HomeController {

    @Qualifier("myDataRepository")
    @Autowired
    private MyDataRepository repository;


    @GetMapping("/")
    public String index(@ModelAttribute("formModel") MyData myData, Model model) {
        model.addAttribute("msg", "this is sample content.");
        final List<MyData> list = repository.findAll();
        model.addAttribute("dataList", list);
        return "index";
    }

    @PostMapping("/")
    @Transactional(readOnly = false)
    public String form(@ModelAttribute("formModel") @Validated MyData myData, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            repository.saveAndFlush(myData);
            return "redirect:/";
        } else {
            model.addAttribute("msg", "sorry, error is occurred...");
            final List<MyData> list = repository.findAll();
            model.addAttribute("dataList", list);
            return "index";
        }
    }

    @GetMapping("/edit/{id}")
    public String edit(@ModelAttribute MyData myData, @PathVariable int id, Model model) {
        model.addAttribute("title", "edit myData.");
        final MyData data = repository.findById((long) id);
        model.addAttribute("formModel", data);
        return "edit";
    }

    @PostMapping("/edit")
    @Transactional(readOnly = false)
    public String update(@ModelAttribute MyData myData, Model model) {
        repository.saveAndFlush(myData);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id, Model model) {
        model.addAttribute("title", "delete myData.");
        final MyData myData = repository.findById((long) id);
        model.addAttribute("formModel", myData);
        return "delete";
    }

    @PostMapping("/delete")
    @Transactional(readOnly = false)
    public String delete(@RequestParam long id) {
        repository.delete(id);
        return "redirect:/";
    }


    @PostConstruct
    public void init() {
        final MyData data1 = new MyData();
        data1.setName("kim");
        data1.setAge(123);
        data1.setMail("kim@gilbut.co.kr");
        data1.setMemo("this is my data!");
        final MyData data2 = new MyData();
        data2.setName("lee");
        data2.setAge(15);
        data2.setMail("lee@gilbut.co.kr");
        data2.setMemo("my girl friend.");
        final MyData data3 = new MyData();
        data3.setName("choi");
        data3.setAge(37);
        data3.setMail("choi@gilbut.co.kr");
        data3.setMemo("my work friend.");

        repository.saveAndFlush(data1);
        repository.saveAndFlush(data2);
        repository.saveAndFlush(data3);
    }
}
