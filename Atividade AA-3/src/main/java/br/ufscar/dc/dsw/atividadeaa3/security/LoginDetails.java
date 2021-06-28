package br.ufscar.dc.dsw.atividadeaa3.security;

import br.ufscar.dc.dsw.atividadeaa3.domain.Login;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

public class LoginDetails implements UserDetails {
 
    private Login login;
     
    public LoginDetails(Login login) {
        this.login = login;
    }
 
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(login.getRole());
        return Arrays.asList(authority);
    }
 
    @Override
    public String getPassword() {
        return login.getPassword();
    }
 
    @Override
    public String getUsername() {
        return login.getUsername();
    }
    
    public long getId() {
        return login.getId();
    }
 
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
 
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
 
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
 
    @Override
    public boolean isEnabled() {
        return true;
    }
}