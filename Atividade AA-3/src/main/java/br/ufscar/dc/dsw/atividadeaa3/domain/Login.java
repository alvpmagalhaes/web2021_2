package br.ufscar.dc.dsw.atividadeaa3.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Login")
@Inheritance(strategy = InheritanceType.JOINED)
public class Login extends AbstractEntity<Long> {

    @Column(nullable = false, length = 45)
    private String username;

    @Column(nullable = false, length = 64)
    private String password;

    @Column(nullable = false, length = 45)
    private String role;

    @Column(nullable = false)
    private boolean enabled;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
