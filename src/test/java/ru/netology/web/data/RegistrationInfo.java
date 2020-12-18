package ru.netology.web.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegistrationInfo {
    public final String login;
    public final String password;
    public final String status;
}
