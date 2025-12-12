        package com.example.demo.controller;

        import com.example.demo.domain.user.User;
        import com.example.demo.domain.user.UserDeletedDTO;
        import com.example.demo.domain.user.UserRequestDTO;
        import com.example.demo.domain.user.UserResponseDTO;
        import com.example.demo.service.UserService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;

        import java.util.List;
        import java.util.UUID;

        @RestController
        @RequestMapping("/user")
        public class UserController {

            @Autowired
            private UserService userService;

            @PostMapping
            public ResponseEntity<UserResponseDTO> create(@RequestBody UserRequestDTO userBody){
                UserResponseDTO userResponse = this.userService.create(userBody);
                return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
            }

            @GetMapping
            public List<UserResponseDTO> findAllUsers() {
                return userService.findAllUsers();
            }

            @GetMapping("/{id}")
            public ResponseEntity<User> getUserById(@PathVariable UUID id){
                User user = userService.findById(id);
                return ResponseEntity.ok(user);
            }


            @GetMapping("/deleted/{deleted}")
            public ResponseEntity<List<User>> getUserByDeleted(@PathVariable Boolean deleted){
                return ResponseEntity.ok().body(userService.findByDeleted(deleted));
            }

            @PutMapping("/{id}")
            public ResponseEntity<User> update(@PathVariable UUID id, @RequestBody UserRequestDTO userBody) {
                try {
                    User updatedUser = userService.updateUser(id, userBody);

                    return ResponseEntity.ok(updatedUser);

                } catch (RuntimeException e) {
                    return ResponseEntity.notFound().build();
                }
            }

            @DeleteMapping("deleted/{id}")
            public ResponseEntity<User> delete(@PathVariable UUID id) {
                try {
                    User deletedUser = userService.deletedUser(id);

                    return ResponseEntity.ok(deletedUser);

                } catch (RuntimeException e) {
                    return ResponseEntity.notFound().build();
                }
            }

            @GetMapping("/count")
            public ResponseEntity<UserDeletedDTO> countStatusTask() {
                UserDeletedDTO counts = userService.countUser();
                return ResponseEntity.ok(counts);
            }

        }
