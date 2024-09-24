package gooroom.gigigit.board.common;

public record CommonResponseDto<T>(
        String msg,
        T result
) {
}
