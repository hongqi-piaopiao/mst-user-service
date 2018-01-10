package com.thoughtworks.mstorderservice;

import com.thoughtworks.mstorderservice.Repository.UserRepository;
import com.thoughtworks.mstorderservice.entity.Role;
import com.thoughtworks.mstorderservice.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
//@Disabled
class UserControllerTests {
    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    void should_return_user_address_by_user_id() throws Exception {
        User user = User.builder().id("0001")
                .name("Jane")
                .role(Role.builder()
                .symbol(Role.Symbol.SYSTEM_ADMIN).name("形同").build())
                .password("1234")
                .address("123 Main Street").build();
        userRepository.save(user);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(String.format("/api/user/%s/address", user.getId()))
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("123 Main Street"));
    }

}
