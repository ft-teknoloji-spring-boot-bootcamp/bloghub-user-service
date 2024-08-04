package com.patika.bloghubuserservice.producer.dto;

import com.patika.bloghubuserservice.client.email.dto.request.enums.EmailTemplate;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SendEmailMessage {

    private String to;
    private EmailTemplate emailTemplate;

}
