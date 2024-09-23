package com.pe.botica.configuration.authorization;

import com.pe.botica.model.security.GrantedPermission;
import com.pe.botica.model.security.Operation;
import com.pe.botica.model.security.User;
import com.pe.botica.repository.OperationRepository;
import com.pe.botica.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class CustomAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {

    @Autowired
    private OperationRepository operationRepository;
    @Autowired
    private UserService userService;

    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication,
                                       RequestAuthorizationContext requestContext) {
        HttpServletRequest request = requestContext.getRequest();
        String url = extractUrl(request);
        String httpMethod = request.getMethod();
        boolean isPublic = isPublic(url, httpMethod);
        if(isPublic){
            return new AuthorizationDecision(true);
        }

        boolean isGranted = isGranted(url, httpMethod, authentication.get());
        return new AuthorizationDecision(isGranted);
    }

    private boolean isGranted(String url, String httpMethod, Authentication authentication) {
        if(!(authentication instanceof UsernamePasswordAuthenticationToken)){
            throw new AuthenticationCredentialsNotFoundException("User not logged in");
        }


        if (url.matches("/drugstoreService/deleteById/[0-9a-fA-F\\-]+/[0-9a-fA-F\\-]+") && httpMethod.equals("DELETE")) {
            return true;
        }

        if (url.matches("/drugstoreService/edit/[0-9a-fA-F\\-]+/[0-9a-fA-F\\-]+") && httpMethod.equals("PUT")) {
            return true;
        }


        List<Operation> operations = obtainOperations(authentication);

        return operations.stream().anyMatch(getOperationPredicate(url, httpMethod));
    }

    private static Predicate<Operation> getOperationPredicate(String url, String httpMethod) {
        return operation -> {
            String basePath = operation.getModule().getBasePath();

            Pattern pattern = Pattern.compile(basePath.concat(operation.getPath()));
            Matcher matcher = pattern.matcher(url);
            return matcher.matches() && operation.getHttpMethod().equals(httpMethod);
        };
    }

    private List<Operation> obtainOperations(Authentication authentication) {
        UsernamePasswordAuthenticationToken authToken = (UsernamePasswordAuthenticationToken) authentication;
        String username = (String) authToken.getPrincipal();
        User user = userService.findByEmail(username)
                .orElseThrow(()-> new RuntimeException("User not found with username: "+ username));
        return user.getRole().getPermissions().stream()
                .map(GrantedPermission::getOperation)
                .collect(Collectors.toList());
    }

    private boolean isPublic(String url, String httpMethod) {
        List<Operation> publicAccessEndpoints = operationRepository
                .findByPublicAccess();

        return publicAccessEndpoints.stream().anyMatch(getOperationPredicate(url, httpMethod));
    }

    private String extractUrl(HttpServletRequest request) {
        String contextPath = request.getContextPath();
        String url = request.getRequestURI();
        url = url.replace(contextPath, "");
        return url;
    }
}
