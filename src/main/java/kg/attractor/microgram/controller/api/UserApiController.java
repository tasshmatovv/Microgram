package kg.attractor.microgram.controller.api;

import kg.attractor.microgram.dto.UserDto;
import kg.attractor.microgram.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserApiController {
    private final UserService userService;

    @GetMapping("/search")
    public List<UserDto> findUserByNameOrNicName(@RequestParam String query) {

        log.info("Запрос пришел: {}", query);
        List<UserDto> users = userService.findUserByNickNameOrName(query);
        log.info("Нашли: {}", users);
        return users;
    }
}
