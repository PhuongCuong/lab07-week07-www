package fit.iuh.edu.vn.lab07week07.frontend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class LoginAccount {
    private String email;
    private String soDt;

    @Override
    public String toString() {
        return "LoginAccount{" +
                "email='" + email + '\'' +
                ", soDt='" + soDt + '\'' +
                '}';
    }
}
