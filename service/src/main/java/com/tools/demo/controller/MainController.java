package com.tools.demo.controller;

import com.tools.cache.annotation.UseEncache;
import com.tools.log.annotation.Log;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
//    @RequestMapping("")
//    public String index(){
//        return "examples/index";
//    }
      @RequestMapping("/")
      @ResponseBody
      String home() {
        return "Hello World!";
      }

    @RequestMapping("/hello")
    @Log
    @UseEncache
    public String hello() {
        return getStrings("sdasdsa");
    }



    public String getStrings(String param){
          return "Hello Spring Boot!" + param;
     }
}