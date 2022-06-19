package com.example.storeeverything.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Objects;


@Getter
@Setter
public class Error {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;

    private int code;

    private String status;

    private String message;

    private String stackTrace;


    public Error() {
        timestamp = LocalDateTime.now();
    }

    public Error(HttpStatus httpStatus, String message) {
        this();
        this.code = httpStatus.value();
        this.status = httpStatus.name();
        this.message = message;
    }

    public Error(HttpStatus httpStatus, String message, String stackTrace) {
        this(httpStatus, message);
        this.stackTrace = stackTrace;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Error error = (Error) o;
        return code == error.code &&
                Objects.equals(timestamp, error.timestamp) &&
                Objects.equals(status, error.status) &&
                Objects.equals(message, error.message) &&
                Objects.equals(stackTrace, error.stackTrace);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, code, status, message, stackTrace);
    }

    @Override
    public String toString() {
        return "Error{" +
                "timestamp=" + timestamp +
                ", code=" + code +
                ", status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", stackTrace='" + stackTrace + '\'' +
                '}';
    }
}
