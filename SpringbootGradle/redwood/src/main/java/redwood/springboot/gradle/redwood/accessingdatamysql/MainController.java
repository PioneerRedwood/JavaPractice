package redwood.springboot.gradle.redwood.accessingdatamysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller                     // 해당 클래스는 컨트롤러임을 명시
@RequestMapping(path="/demo")   // URL의 시작은 /demo 임을 명시(Application path 다음으로)
public class MainController {
    @Autowired                  // bean에서 userRepository를 호출하도록 명시
    // Spring에 의해 자동생성, 데이터를 다루기 위해 사용할 예정
    private UserRepository userRepository;

    @PostMapping(path="/add")   // Map ONLY POST Requests
    public @ResponseBody String addNewUser(@RequestParam String name, @RequestParam String email) {
        // @ResponseBody: 응답으로 String 형태가 반환됨을 명시
        // @RequestParam: GET or POST 요청의 매개변수임을 명시
        User n = new User();
        n.setName(name);
        n.setEmail(email);
        userRepository.save(n);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        // JSON or XML 형태로 User를 반환
        return userRepository.findAll();
    }
}