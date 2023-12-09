package com.example.adapter.user.resolver;

import com.example.common.exception.BusinessException;
import com.example.common.rest.BaseController;
import com.example.usertoken.model.RetrieveUserId;
import com.example.usertoken.usecase.RetrieveUserIdFromAccessTokenUseCase;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Objects;

@Service
public class UserIdResolver extends BaseController implements HandlerMethodArgumentResolver {

    private static final String ACCESS_TOKEN = "Access-Token";

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return Objects.nonNull(parameter.getParameterAnnotation(RequestUserIdFromAccessToken.class))
                && parameter.getParameterType().equals(Long.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {
        String accessToken = webRequest.getHeader(ACCESS_TOKEN);
        if (Boolean.FALSE.equals(supportsParameter(parameter)) && StringUtils.isBlank(accessToken)) {
            throw new BusinessException("user.not.found");
        }

        return getUserIdFromToken(accessToken);
    }

    private Long getUserIdFromToken(String accessToken) {
        RetrieveUserId userId = publish(RetrieveUserId.class, new RetrieveUserIdFromAccessTokenUseCase(accessToken));
        return userId.getUserId();
    }
}
