package com.example.userservice.BankUser;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("BankUserService Unit Tests")
class BankUserServiceTest {

    @Mock   //tells mockito we want to mock this dependency
    private BankUserRepository bankUserRepository;  //mocked dependency

    @InjectMocks    //Tells mockito to inject mocked dependencies into this class
    private BankUserService bankUserService; //the class we are testing

    private Long userId;
    private BankUserRequest bankUserRequest;
    private BankUserDto bankUserDto;
    private BankUser bankUser;

    @BeforeEach
    void setUp(){
        /*
            Can initialize params here...
         */
        userId = 1L;
        bankUser = BankUser.builder()
                .id(userId)
                .nameF("John")
                .nameM("Doe")
                .nameL("Smith")
                .addrStreet("123 Example St.")
                .addrCity("Chicago")
                .addrSt("IL")
                .addrZip("61620")
                .phone("333-111-2222")
                .email("jdsmith@example.com")
                .creationTs(null)
                .build();
        bankUserRequest = BankUserRequest.builder()
                .nameF("John")
                .nameM("Doe")
                .nameL("Smith")
                .addrStreet("123 Example St.")
                .addrCity("Chicago")
                .addrSt("IL")
                .addrZip("61620")
                .phone("333-111-2222")
                .email("jdsmith@example.com")
                .password("pass123")
                .build();

        bankUserDto = new BankUserDto(bankUser);
    }



    @Nested //Allows JUnit to execute nested test methods
    @DisplayName("Get User From Id Tests")
    class GetUserFromIdTests {
        @Test
        @DisplayName("Should return a user when given a valid user id")
        void shouldGetUserFromId(){
            // Given
            //...
            // When
            final BankUserDto result = bankUserService.getUserFromId(userId);

            // Then
            assertNotNull(result);

        }

        @Test
        @Disabled
        @DisplayName("Test name 2")
        void doSomething(){

        }
    }

    @Nested
    @DisplayName("Create New User Tests")
    class CreateNewBankUserTests {

        @Captor
        private ArgumentCaptor<BankUser> bankUserArgumentCaptor;

        @Test
        @DisplayName("Should create a new user when given a valid user request")
        void shouldCreateNewUser(){
            //Given
            when(bankUserRepository.save(any(BankUser.class))).thenReturn(bankUser);
            //When
            final BankUserDto result = bankUserService.createNewBankUser(bankUserRequest);
            //Then
            assertNotNull(result);
            assertEquals(bankUserDto, result);
            //verify that the repository's save() method was called exactly once and save the argument
            verify(bankUserRepository, times(1)).save(bankUserArgumentCaptor.capture());
            //verify that the argument given to the repository's save() method matches the argument the service tried to save
            BankUser savedUser = bankUserArgumentCaptor.getValue();
            assertThat(savedUser)
                    .usingRecursiveComparison()
                    .ignoringFields("id", "creationTs")
                    .isEqualTo(bankUser);
            /*
                TODO: Add testing logic for password storage.
             */
        }

        /*
            TODO: Add tests for bad requests and exceptions
         */


    }

    @Nested
    @DisplayName("Update Bank User Tests")
    class UpdateBankUserTests {


    }












}