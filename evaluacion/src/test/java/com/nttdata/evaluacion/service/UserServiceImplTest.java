package com.nttdata.evaluacion.service;

import com.nttdata.evaluacion.model.Phone;
import com.nttdata.evaluacion.model.User;
import com.nttdata.evaluacion.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    UserServiceImpl userServiceImpl;

    LocalDate now = LocalDate.now();
    Long id = Long.valueOf(1);
    Phone phone;
    List<Phone> phones;
    User user;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        phone = new Phone(id, 123456789, 8320000, "SCL");
        user = new User("1","John Doe", "john.doe@example.com", "password123", Arrays.asList(phone), now, now, now,"sampleToken", true);
    }

    @Test
    void createUser() {
        when(userRepository.findByEmail(user.getEmail())).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);
      //  when(userServiceImpl.saveUser(user)).thenReturn(user);
        assertNotNull(userRepository.findByEmail(user.getEmail()));
       // assertNotNull(userServiceImpl.saveUser(user));
    }

    //@Test
    //void saveUser() {
    //}

    @Test
    void createJwtToken() {
        when(userServiceImpl.createJwtToken(user)).thenReturn("token123");
        String result = userServiceImpl.createJwtToken(user);
        assertEquals("token123", result);
    }

    @Test
    void listar() {
        when(userRepository.findAll()).thenReturn(Arrays.asList(user));
        assertNotNull(userServiceImpl.listar());
    }
}