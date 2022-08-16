package com.br.myfood.login.message;

import com.br.myfood.login.dto.LoginDto;
import com.br.myfood.login.entity.Login;
import com.br.myfood.login.repository.LoginRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class LoginReceiveMessage {

    private final LoginRepository loginRepository;

    @Autowired
    public LoginReceiveMessage(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @RabbitListener(queues = {"${cadastro.login.rabbitmq.queu}"})
    public void receiveMessage(@Payload LoginDto loginDto){
        System.out.println(loginDto);
        loginRepository.save(new Login(null, loginDto.getEmail(), loginDto.getPassword()));

    }
}
