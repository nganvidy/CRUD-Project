package com.example.demo.controller;

import com.example.demo.model.respone.FileResponse;
import com.example.demo.service.FileStorageService;
import com.example.demo.utils.Response;
import jakarta.servlet.http.HttpServletRequest;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/file-service")
public class FileRestController {
    @Autowired
    FileStorageService fileStorageService;

    //allowed extension(jpg,png,...)

    private final List<String> ALLOWED_EXTENSION=List.of("jpg","png","jpeg");
    private final long MAX_FILE_SIZE=1024*1024*5;//5MB


    //size profile image
    @PostMapping("/file-upload")
    public Response<FileResponse> fileUpload(@RequestParam("file") MultipartFile file)throws Exception{
//        String filename=fileStorageService.uplaodFile(file);
//        return Response.<FileResponse>ok().setPayload(filename).setMessage("this is your data");

            FileResponse response=uploadFile(file);
            return Response.<FileResponse>ok().setPayload(response).setMessage("Successfully upload file");
    }
    @PostMapping("/multiple-file-upload")
    public Response<List<FileResponse>> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files){
//      List<String> filename= Arrays.stream(files)
//                .map(file-> {
//                    try {
//                        return fileStorageService.uplaodFile(file);
//                    } catch (Exception e) {
//                        throw new RuntimeException(e);
//                    }
//                })
//                .toList();
//      return Response.<Object>createSuccess().setPayload(filename).setMessage("You are successfully upload file!");

                  List<FileResponse> response= Arrays.stream(files)
                          .map(file-> {
                              try {
                                  return uploadFile(file);
                              } catch (Exception e) {
                                  throw new RuntimeException(e);
                              }
                          })
                          .toList();
                  return Response.<List<FileResponse>>createSuccess().setPayload(response).setMessage("your file upload successfully");

    }

    //for delete single and multiple
    @DeleteMapping("/delete-file/{filename}")
    public Response<String> deleteSingleFile(@PathVariable String filename){
        String filenames=fileStorageService.deleteByFileName(filename);
        return Response.<String>createSuccess().setPayload(filenames);
    }
    @DeleteMapping("/delete-file-all")
    public Response<String> deleteAllFile(){
       String filename= fileStorageService.deleteAllFiles();
        return Response.<String>deleteSuccess().setPayload(filename);
    }
    //for download file handling
    @GetMapping("/download-file/{filename}")
    public ResponseEntity<?> downloadFile(@PathVariable String filename, HttpServletRequest request) throws Exception {
        Resource resource =fileStorageService.loadFileResource(filename);
        String contentType=null;
        try{
            contentType=request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        }catch (IOException ex){
            System.out.println("Error getting content type is :"+ex.getMessage());
        }
        if(contentType==null){
            contentType="application/octet-stream";
        }
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename\""+resource.getFilename()+"\"")
                .body(resource);
        }
    private FileResponse uploadFile(MultipartFile file) throws Exception{
        //file empty
        if(file.isEmpty()){
            throw new IllegalArgumentException("File cannot be empty");
        }
        //file size
        if(file.getSize()>MAX_FILE_SIZE){
            throw new MaxUploadSizeExceededException(MAX_FILE_SIZE);
        }

        String extension= StringUtils.getFilenameExtension(file.getOriginalFilename());
        if(!ALLOWED_EXTENSION.contains(extension.toLowerCase())){
            throw new IllegalArgumentException("Allowed extension are 'jpg','jpeg','png'");
        }
        String filename=fileStorageService.uplaodFile(file);

        String fileDownloadUri= ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/file-service/download-file/")
                .path(filename)
                .toUriString();
        return new FileResponse().setFileName(filename)
                .setFileDownloadUri(fileDownloadUri)
                .setFileType(file.getContentType())
                .setSize(file.getSize());
    }



}
