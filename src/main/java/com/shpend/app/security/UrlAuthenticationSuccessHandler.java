package com.shpend.app.security;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.shpend.app.domain.User;
import com.shpend.app.repository.UserRepository;


public class UrlAuthenticationSuccessHandler
implements AuthenticationSuccessHandler {
	
	@Autowired
	private UserRepository userRepo;

  protected Log logger = LogFactory.getLog(this.getClass());

  private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, 
    HttpServletResponse response, Authentication authentication)
    throws IOException {

      handle(request, response, authentication);
      clearAuthenticationAttributes(request);
  }

  protected void handle(HttpServletRequest request, 
    HttpServletResponse response, Authentication authentication)
    throws IOException {

      String targetUrl = determineTargetUrl(authentication);

      if (response.isCommitted()) {
          logger.debug(
            "Response has already been committed. Unable to redirect to "
            + targetUrl);
          return;
      }

      redirectStrategy.sendRedirect(request, response, targetUrl);
  }

  protected String determineTargetUrl(Authentication authentication) {
      boolean isUser = false;
      boolean isAdmin = false;
      boolean isTeacher = false;
     
      Collection<? extends GrantedAuthority> authorities
       = authentication.getAuthorities();
      for (GrantedAuthority grantedAuthority : authorities) {
          if (grantedAuthority.getAuthority().equals("ROLE_USER")) {
              isUser = true;
              break;
          } else if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
              isAdmin = true;
              break;
          }else if (grantedAuthority.getAuthority().equals("ROLE_TEACHER")) {
              isTeacher = true;
              break;
          }
      }
      User u = (User) authentication.getPrincipal();
      Optional<User> ifUser = userRepo.findById(u.getId());
      User user = ifUser.get();
      System.out.println(user.getUsername());
      System.out.println(user.getName());
      System.out.println(user.getId());
      System.out.println(user.getCompletedInfo());
      if (isUser) {
    	  
    	  try {
    		  if(user.getCompletedInfo()==1) 
    			  return "/dashboard/" + user.getId();
    		  return "/register_student/" + user.getId();
    	  }catch(NullPointerException e) {
        		  return "/register_student/" + user.getId();
    	  }
      } else if (isAdmin) {
    	  
    	  try {
    		  if(user.getCompletedInfo()==1)
    			  return "/admin/" + user.getId();
    		  return "/register_admin/" + user.getId();
    	  }catch(NullPointerException e) {
        		  return "/register_admin/" + user.getId();
    	  }
      } else if(isTeacher) {
    	  try {
    		  if(user.getCompletedInfo()==1)
    			  return "/teacher/" + user.getId();
    			return "/register_teacher/" + user.getId();
    	  }catch(NullPointerException e) {
        		  return "/register_teacher/" + user.getId();
    	  }
      } else{
          throw new IllegalStateException();
      }
  }

  protected void clearAuthenticationAttributes(HttpServletRequest request) {
      HttpSession session = request.getSession(false);
      if (session == null) {
          return;
      }
      session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
  }

  public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
      this.redirectStrategy = redirectStrategy;
  }
  protected RedirectStrategy getRedirectStrategy() {
      return redirectStrategy;
  }
}
