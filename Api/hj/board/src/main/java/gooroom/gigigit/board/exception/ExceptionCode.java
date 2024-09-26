package gooroom.gigigit.board.exception;

import com.nimbusds.jose.crypto.impl.PRFParams;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionCode {
    // User Exception
    USER_NOT_EXIST(HttpStatus.BAD_REQUEST, "해당 유저가 존재하지 않습니다."),

    // File Exception
    FILE_WRITE_ERROR(HttpStatus.BAD_REQUEST, "파일을 쓰던 중 문제가 발생했습니다."),
    FILE_DELETE_ERROR(HttpStatus.BAD_REQUEST, "파일을 시스템에서 삭제하던 중 문제가 발생했습니다."),
    FILE_READ_ERROR(HttpStatus.BAD_REQUEST, "파일을 시스템에서 읽어오던 중 문제가 발생했습니다."),

    // Post Exception
    POST_NOT_EXIST(HttpStatus.BAD_REQUEST,"해당 게시물이 존재하지 않습니다.");


    private final HttpStatus status;
    private final String message;

    ExceptionCode(HttpStatus status, String message){
        this.status =status;
        this.message = message;
    }
}
