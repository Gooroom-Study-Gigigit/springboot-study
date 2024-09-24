package gooroom.gigigit.board.component.file;

import org.springframework.web.multipart.MultipartFile;

public interface FileComponent {

    byte[] downloadImgFromFileSystem(String filePath);
    byte[] downloadProfileFromFileSystem(String filePath);
    void deleteImg(String filePath);

    void updateFileSystemImg(String filePath, byte[] fileBytes);
    void updateFileSystemProfileImg(String oldFilePath,String updateFilePath, MultipartFile updateFile);

}
