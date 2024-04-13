package io.github.zhengchalei.common.exception;

import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.language.SourceLocation;
import io.smallrye.graphql.api.ErrorCode;

import java.util.List;

@ErrorCode("Service_Exception")
public class ServiceException extends RuntimeException implements GraphQLError {

    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    @Override
    public List<SourceLocation> getLocations() {
        return List.of();
    }

    @Override
    public ErrorClassification getErrorType() {
        return null;
    }
}
