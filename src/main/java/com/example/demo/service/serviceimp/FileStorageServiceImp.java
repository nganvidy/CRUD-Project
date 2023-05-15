package com.example.demo.service.serviceimp;

import com.example.demo.service.FileStorageService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;
@Service
public class FileStorageServiceImp implements FileStorageService {
    private final String serviceLocation="src/main/resources/images/";

    //upload file
    //delete file by name
    //delete all
    Path fileLocationStorage;
    public FileStorageServiceImp(){
        fileLocationStorage= Paths.get(serviceLocation);
        try {
            if (!Files.exists(fileLocationStorage)) {
                Files.createDirectories(fileLocationStorage);
            } else {
                System.out.println("Directory is already existed!");
            }
        }catch (Exception ex){
            System.out.println("Error creating directory"+ex.getMessage());
        }
    }

    @Override
    public String uplaodFile(MultipartFile file){
        //format file name
        //cute-pic.png
        String filename=file.getOriginalFilename();
        //String[]
        //[0]=cute.pic
        //[1]=png
        //check to see if file is empty
        String[] fileCompartment=filename.split("\\.");
        filename= UUID.randomUUID()+"."+fileCompartment[1];
        //erfuerhuf-erfjrje.jpg
        //put it to directory we resolve
        //src/main/resources/images/erfuerhuf-erfjrje.jpg
        Path resolvePath=fileLocationStorage.resolve(filename);
        //copy to my computer
        //replace ah jas jol
        try {
            Files.copy(file.getInputStream(),resolvePath, StandardCopyOption.REPLACE_EXISTING);
        }catch (Exception e){
            e.getStackTrace();
        }
        return filename;
    }

    @Override
    public String deleteByFileName(String filename) {
        Path imageLocation=Paths.get(serviceLocation);
      List<File> allFile= List.of(imageLocation.toFile().listFiles());

      //filter file that going to delete
        File deleteFile=allFile.stream().filter(file->file.getName().equals(filename))
                .findFirst().orElse(null);
        try {
            if(deleteFile!=null){
                Files.delete(deleteFile.toPath());
                return "Delete file successfully";
            }else {
                return "File with "+filename+" doesn't exist";
            }
        }catch (Exception ex){
            System.out.println("Error delete "+ex.getMessage());
            return "Exception occurred ! fail to delete file";
        }
    }

    @Override
    public String deleteAllFiles() {
        Path imageLocation=Paths.get(serviceLocation);
        File[] files= imageLocation.toFile().listFiles();
       try {
           if(files==null || files.length==0){
               return "There is no files to delete !!";
           }
           for(File file:files){
               Files.delete(file.toPath());
           }
           return "successfully delete all files";
       }catch (Exception ex){
           ex.getStackTrace();
           System.out.println("Exception delete all files" + ex.getMessage());
           return "Failed to delete all files ! Exception occurred..";
       }

    }

    @Override
    public Resource loadFileResource(String filename)throws Exception {
        Path filepath=this.fileLocationStorage.resolve(filename).normalize();
        try {
            Resource resource =new UrlResource(filepath.toUri());
            if(resource.exists()){
                return resource;
            }else {
                throw new Exception("Resource don't exist !");
            }
        }catch (Exception ex){
            throw new Exception("Exception occurred ! failed to download in image.");
        }

    }
}
