package com.library.user.domain.model;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class User {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String phoneNumber;

    public String getFullName() {
        return firstname + " " + lastname;
    }   
}
