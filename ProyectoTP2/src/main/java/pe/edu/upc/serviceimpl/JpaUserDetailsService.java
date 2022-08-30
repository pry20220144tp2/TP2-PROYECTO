package pe.edu.upc.serviceimpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import pe.edu.upc.entity.Account;
import pe.edu.upc.repository.IAccountRepository;

@Service
public class JpaUserDetailsService implements UserDetailsService {

	@Autowired
	private IAccountRepository accountRepository;

	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account user = accountRepository.findByCorreoAccount(username);

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		// GrantedAuthority authority = new SimpleGrantedAuthority(user.getRoleAccount().getNameRole());
		/*for (Role role : user.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getRol()));
		}*/
		authorities.add(new SimpleGrantedAuthority(user.getRoleAccount().getNameRole()));

		
		
		
		return new User(user.getCorreoAccount(),user.getPasswordAccount(), true, true, true, true, authorities);
		/*return new User(user.getCorreoAccount(),
				user.getPasswordAccount(), Arrays.asList(authority));
	    */

		
		/*Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		grantedAuthorities.add(new SimpleGrantedAuthority(user.getRoleAccount().getNameRole()));


		return new org.springframework.security.core.userdetails.User(user.getCorreoAccount(),
				user.getPasswordAccount(),                        grantedAuthorities);*/
	}

}
