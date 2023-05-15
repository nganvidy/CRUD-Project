package com.example.demo.model.respone;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class FileResponse {
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private Long size;
}
