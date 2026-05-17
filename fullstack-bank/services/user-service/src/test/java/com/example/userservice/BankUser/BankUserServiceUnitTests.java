package com.example.userservice.BankUser;

import com.example.userservice.common.exception.NotFoundException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("BankUserService Unit Tests")
class BankUserServiceUnitTests {

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
            when(bankUserRepository.getUserById(any(Long.class))).thenReturn(bankUserDto);
            // When
            final BankUserDto result = bankUserService.getUserFromId(userId);
            // Then
            //assert result not null
            assertNotNull(result);
            //assert result dto fields equal to expected
            assertThat(result)
                    .usingRecursiveComparison()
                    .isEqualTo(bankUserDto);
            //assert mock repo findUserById() called once with argument
            verify(bankUserRepository, times(1)).getUserById(userId);
        }

        @Test
        @DisplayName("Should throw NotFoundException with message when user not found by repository")
        void shouldThrowNotFoundException(){
            // Given
            when(bankUserRepository.getUserById(any(Long.class))).thenReturn(null);
            // When / then
            assertThatThrownBy(() -> bankUserService.getUserFromId(userId))
                    .isInstanceOf(NotFoundException.class)
                    .hasMessage("User with id: " + userId + " not found.");
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

        @Captor
        private ArgumentCaptor<BankUser> bankUserArgumentCaptor;

        @Test
        @DisplayName("Should update user when provided valid user request")
        void shouldUpdateUser(){
            //Given
            //----------
            when(bankUserRepository.findBankUserById(any(Long.class))).thenReturn(bankUser);
            when(bankUserRepository.save(any(BankUser.class))).thenReturn(bankUser);
            //When
            //----------
            final BankUserDto result = bankUserService.updateBankUser(bankUserRequest, userId);
            //Then
            //----------
            //Assert: results not null
            assertNotNull(result);
            //Assert: result BankUserDto values match expected
            assertThat(result)
                    .usingRecursiveComparison()
                    .isEqualTo(bankUserDto);
            //Assert: bankUserRepository.getUserById() called once with expected argument
            verify(bankUserRepository, times(1)).findBankUserById(userId);
            //Assert: bankUserRepository.save() called once, capture argument
            verify(bankUserRepository, times(1)).save(bankUserArgumentCaptor.capture());
            //Assert: bankUserRepository.save() argument matches expected
            assertThat(bankUserArgumentCaptor.getValue())
                    .usingRecursiveComparison()
                    .ignoringFields("id", "creationTs")
                    .isEqualTo(bankUser);
        }
    }












}