package pl.sdacademy.wiosnademo.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

public class BlockingHandlerInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {
    return true;
  }
}
