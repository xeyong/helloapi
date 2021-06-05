package com.example.helloapi.controller;

import com.example.helloapi.dto.UserRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/get")
public class GetApiController {

    @GetMapping("/hello")//Get방식의 주소를 할당해주는
    public String hello(){
        return"get Hello";
    }
    /*
    @GetMapping(path = "/hello") path를 명시적으로 지정해줘도 된다 안해줘도 알아서 해주긴함
    public String hello(){
        return"get Hello";
    }*/

    @RequestMapping(path = "/hi", method = RequestMethod.GET)//옛날방식으로 RequestMapping을 사용하고 method를 지정해주는 방식도있다.
    public String hi(){
        return "get Hi";
    }

    @GetMapping("/path-variables/{name}")//pathvariable을 설정할때는 {}안의 변수명과 실제 파라미터의 변수명을 맞춰줘야지 스프링에서 처리해준다.
    public String pathVariable(@PathVariable String name){
        System.out.println("pathVariable: "+ name);
        return name;
    }
    /*
    @GetMapping("/path-variables/{name}")//만약 메서드안에서 다른 변수명을 선언하고싶을때는 @PathVariable(name = "name") 디폴트값으로 설정을해준다
    public String pathVariable2(@PathVariable(name = "name") String pathName){
        System.out.println("pathVariable: "+ pathName);
        return pathName;
    }*/
    @GetMapping("/query-param")
    public String queryParam(@RequestParam Map<String,String> queryParam){

        StringBuilder sb = new StringBuilder();

        queryParam.entrySet().forEach(entry ->{
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
            System.out.println("\n");

            sb.append(entry.getKey()+" = "+entry.getValue());
        });
        return sb.toString();
    }

    @GetMapping("/query-param02")
    public String queryParam02(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam int age)//이런식으로 query파라미터를 받으면 파라미터의 수가 늘어나면 존나 귀찮아짐 그래서 스프링에서는 dto(bean)형식으로 매핑을 제공함
    {
        System.out.println(name);
        System.out.println(email);
        System.out.println(age);
        return name+" "+email+" "+age;
    }

    @GetMapping("/query-param03")
    public String queryParam03(UserRequest userRequest){//객체형식으로 파라미터가 들어오게되면 ?이후의 값을 스프링에서 해당 객체의 변수와 매칭을 해준다
        System.out.println(userRequest.getName());
        System.out.println(userRequest.getEmail());
        System.out.println(userRequest.getAge());

        return userRequest.toString();

    }

}
