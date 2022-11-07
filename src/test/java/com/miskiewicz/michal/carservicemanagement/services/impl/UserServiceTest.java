package com.miskiewicz.michal.carservicemanagement.services.impl;

import com.miskiewicz.michal.carservicemanagement.DTOs.UserDTO;
import com.miskiewicz.michal.carservicemanagement.entities.Address;
import com.miskiewicz.michal.carservicemanagement.entities.Car;
import com.miskiewicz.michal.carservicemanagement.entities.User;
import com.miskiewicz.michal.carservicemanagement.entities.enums.UserType;
import com.miskiewicz.michal.carservicemanagement.repositories.AddressRepository;
import com.miskiewicz.michal.carservicemanagement.repositories.CarRepository;
import com.miskiewicz.michal.carservicemanagement.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private AddressRepository addressRepository;
    @Mock
    private CarRepository carRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private UserService userService;
    private User user;
    private Car car;


    @BeforeEach
    void setUp() {
        user = new User("firstName",
                "lastName",
                "email@email.com",
                "password",
                UserType.CLIENT,
                new Address("streetExample", "00", "00-000", "cityExample"),
                List.of(new Car()));
        car = new Car("type",
                "brand",
                "model",
                "registrationNumber",
                "EXAMPLEVINNUMBER");
    }

    @Test
    @Disabled
    void canAddUser() throws Exception {
        userRepository = mock(UserRepository.class);
        userService = mock(UserService.class);
        ArgumentCaptor<User> userArgumentCaptor =
                ArgumentCaptor.forClass(User.class);

        userService.addUser(user);
        verify(userRepository).save(userArgumentCaptor.capture());


        User capturedUser = userArgumentCaptor.getValue();
        assertThat(capturedUser).isEqualTo(user);
    }

    @Test
    void willThrowWhenUserExists() throws Exception {
        given(userRepository.getUserByEmail(anyString()))
                .willReturn(Optional.of(user));


        assertThatThrownBy(() -> userService.addUser(user))
                .isInstanceOf(Exception.class)
                .hasMessageContaining("This user already exists!");
    }

    @Test
    void getUserById() throws Exception {
        userService = mock(UserService.class);
        modelMapper = mock(ModelMapper.class);
        given(userService.getUserById(UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d"))).willReturn(
                new UserDTO("m", "m", "m@m.pl", List.of(car), "krakow",
                        UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d"), "CLIENT"));


        UserDTO userDTO = userService.getUserById(UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d"));


        assertThat(userDTO.getEmail()).isEqualTo("m@m.pl");
    }

    @Test
    void willThrowWhenUserWithThatIdDoesNotExists() throws Exception {
        given(userRepository.findById(any()))
                .willReturn(Optional.empty());


        assertThatThrownBy(() -> userService.getUserById(any()))
                .isInstanceOf(Exception.class)
                .hasMessageContaining("There is no user with that ID");
    }

    @Test
    void canGetAllUsers() {
        userService.getAllUsers();

        verify(userRepository).findAll();
    }

    @AfterEach
    void tearDown() {
        userRepository.delete(user);
    }
}