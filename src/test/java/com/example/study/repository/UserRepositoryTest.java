package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Item;
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

        String account = "Test01";
        String password = "Test01";
        String status = "REGISTERED";
        String email = "Test01@gmail.com";
        String phoneNumber = "010-1111-2222";
        LocalDateTime registeredAt = LocalDateTime.now();
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";

        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setStatus(status);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setRegisteredAt(registeredAt);

//        //앞으로 객체를 생성할때에는 빌더 패턴
//        User u = User.builder()
//                .account(account)
//                .password(password)
//                .status(status)
//                .email(email)
//                .build();

//        user.setCreatedAt(createdAt);
//        user.setCreatedBy(createdBy);

        User newUser = userRepository.save(user);

        Assertions.assertNotNull(newUser);

    }

    @Test
    @Transactional
    public void read(){

        User user = userRepository.findFirstByPhoneNumberOrderByIdDesc("010-1111-2222");

//        //앞으로 업데이트 시에는 체인패턴
//        user.setEmail("").setPhoneNumber("").setStatus("");//chain 패턴
//        User u = new User().setAccount("").setEmail("").setPassword("");//chain 패턴

        if (user != null) {
            user.getOrderGroupList().stream().forEach(orderGroup -> {
                System.out.println("-----------------주문묶음-----------------");
                System.out.println("수령인 : " + orderGroup.getRevName());
                System.out.println("수령지 : " + orderGroup.getRevAddress());
                System.out.println("총금액 : " + orderGroup.getTotalPrice());
                System.out.println("총수량 : " + orderGroup.getTotalQuantity());

                System.out.println("-----------------주문상세-----------------");

                orderGroup.getOrderDetailList().forEach(orderDetail -> {
                    System.out.println("파트너사 이름 : " + orderDetail.getItem().getPartner().getName());
                    System.out.println("파트너사 카테고리 : " + orderDetail.getItem().getPartner().getCategory().getTitle());
                    System.out.println("주문 상품 : " + orderDetail.getItem().getName());
                    System.out.println("고객센터 번호 : " + orderDetail.getItem().getPartner().getCallCenter());
                    System.out.println("주문의 상태 : " + orderDetail.getStatus());
                    System.out.println("도착예정일자 : " + orderDetail.getArrivalDate());
                });

            });
        }

        Assertions.assertNotNull(user);

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
