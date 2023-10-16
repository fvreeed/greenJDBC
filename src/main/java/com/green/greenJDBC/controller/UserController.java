package com.green.greenJDBC.controller;

import com.green.greenJDBC.dao.UserDAO;
import com.green.greenJDBC.model.User;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserDAO userDAO;

    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @PostMapping()
    User create(@RequestBody User user) throws SQLException {
        return userDAO.create(user);
    }

    @GetMapping("/pagination")
    List<User> read(@RequestParam int limit, @RequestParam int offset) throws SQLException {
        return userDAO.read(limit, offset);
    }

    @PostMapping("/{id}")
    User update(@RequestBody User user, @PathVariable int id) throws SQLException {
        return userDAO.update(id, user);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable int id) throws SQLException {
        userDAO.delete(id);
    }

    @GetMapping("/{id}")
    User findById(@PathVariable int id) throws SQLException {
        return userDAO.findById(id);
    }
}
