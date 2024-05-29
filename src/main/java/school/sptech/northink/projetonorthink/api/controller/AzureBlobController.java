package school.sptech.northink.projetonorthink.api.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import school.sptech.northink.projetonorthink.domain.service.usuario.autenticacao.AzureBlobService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/blobs")
public class AzureBlobController {

    private final AzureBlobService azureBlobService;

    public AzureBlobController(AzureBlobService azureBlobService) {
        this.azureBlobService = azureBlobService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty.");
        }
        azureBlobService.uploadFile(file);
        return ResponseEntity.ok("File uploaded successfully.");
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String fileName) {
        byte[] fileData = azureBlobService.downloadFile(fileName);
        if (fileData == null) {
            return ResponseEntity.notFound().build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", fileName);

        return ResponseEntity.ok()
                .headers(headers)
                .body(fileData);
    }

    @GetMapping("/list")
    public ResponseEntity<List<String>> listFiles() {
        List<String> fileList = azureBlobService.listFiles();
        return ResponseEntity.ok(fileList);
    }
}
