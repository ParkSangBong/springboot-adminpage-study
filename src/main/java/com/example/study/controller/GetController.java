package com.example.study.controller;

import com.example.study.model.SearchParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api") //Localhost:8080/api 에 매칭된다.
public class GetController {

    @RequestMapping(method = RequestMethod.GET, path = "/getMethod")    //Localhost:8080/api/getMethod
    public String getRequest(){

        return "Hi getMethod";

    }

    @GetMapping("/getParameter")    //Localhost:8080/api/getParameter?id=1234&password=abcd
    public String getParameter(@RequestParam String id, @RequestParam(name = "password") String pwd){

        String password = "bbbb";

        System.out.println("id : " + id);
        System.out.println("pwd : " + pwd);

        return id + pwd;

    }

    @GetMapping("/getMultiParameter")//Localhost:8080/api/getMultiParameter?account=abcd&email=study@gmail.com&page=10
    public SearchParam getMultiParameter(SearchParam searchParam){
        System.out.println(searchParam.getAccount());
        System.out.println(searchParam.getEmail());
        System.out.println(searchParam.getPage());

        //{"account : "", "email" : "", "page" : 0}
        // (json 형태) 스프링부트에선 어떻게 처리하느냐 리턴할때 스트링이 아닌
        // SearchParam을 그대로 리턴 시켜주고 받았던 내용을 그대로 리턴시킨다
        // GetMethod를 매칭시켜주고 리퀘스트에 대한 파라미터를 받아오고 데이터를 처리하고 JSON 형태로 내리는것이 핵심이다. 스프링부트에서 겟 메서드를 어떻게 처리하고 겟 메서드에 대한 이 쿼리 파라미터
        // 즉 검색 파라미터에 대해서 어떻게 처리할것인지에 대해서 공부했다.
        // 주소창에 파라미터가 노출된다. 브라우저에서 주소에 대한 캐시가 이루어지고, 정보를 얻을 때 사용한다.
        return searchParam;
    }

}
