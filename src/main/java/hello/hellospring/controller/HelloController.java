package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "hello!!"); //키는 data, 값은 hello!!
        return "hello"; //resources의 templates의 hello 라는 파일을 찾아 랜더링, 실행시켜라
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody //http의 body부에 데이터를 직접 넣어줌
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; // "hello name에따라 그대로 나옴, view가 없음"
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello(); //객체를 넘김 JsonConverter작동
        hello.setName(name);
        return hello; //> http응답을 json으로
    }

    static class Hello {
        private String name;

        //getter/setter < alt+Insert
        //get/set > java bin 규약 / property접근방식 private라 외부에서 바로 못꺼냄
        //라이브러리 같은데나 본인이 쓸 때 메서드를 통해 접근하게됨
        //public으로 엶
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
