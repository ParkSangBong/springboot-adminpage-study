package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.User;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

public class UserRepositoryTest extends StudyApplicationTests {

    //Dependency Injection (DI) 스프링의 대표적인기능, AutoWired 라는 키워드를 통해 주입을 받게 된다.
    @Autowired
    private UserRepository userRepository;

    @Test
    public void create(){
        // String sql = insert into user (%s, %s , %d ) value (account, email, page);
        User user = new User();
        user.setAccount("TestUser03");
        user.setEmail("TestUser03@gmail.com");
        user.setPhoneNumber("010-3333-3333");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("TestUser3");

        User newUser = userRepository.save(user);
        System.out.println("newUser : " + newUser);

    }

    @Test
    public void read(){

        Optional<User> user = userRepository.findById(2L);

        user.ifPresent(selectUser ->{
            System.out.println("user : " + selectUser);
            System.out.println("email : " + selectUser.getEmail());
        });

    }

    @Test
    @Transactional
    public void update(){

        // update user set account = %?~~~~ 불편한 작업
        Optional<User> user = userRepository.findById(2L);

        user.ifPresent(selectUser ->{
            selectUser.setAccount("PPPP");
            selectUser.setUpdatedAt(LocalDateTime.now());
            selectUser.setUpdatedBy("update method()");

            userRepository.save(selectUser);

        });

    }

    @Test
    @Transactional
    public void delete(){

        Optional<User> user = userRepository.findById(3L);

        Assertions.assertTrue(user.isPresent());//true  //Assert.assertTrue(user.isPresent());

        user.ifPresent(selectUser -> {
            userRepository.delete(selectUser);
        });

        Optional<User> deleteUser = userRepository.findById(3L);

        Assertions.assertFalse(deleteUser.isPresent()); //false
    }

}
