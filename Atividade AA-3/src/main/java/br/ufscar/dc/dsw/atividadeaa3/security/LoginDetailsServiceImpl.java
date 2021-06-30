package br.ufscar.dc.dsw.atividadeaa3.security;

import br.ufscar.dc.dsw.atividadeaa3.dao.ILoginDAO;
import br.ufscar.dc.dsw.atividadeaa3.domain.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class LoginDetailsServiceImpl implements UserDetailsService {
 
    @Autowired
    private ILoginDAO dao;
     
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Login login = dao.getByUsername(username);
         
        if (login == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
         
        return new LoginDetails(login);
    }
 
}