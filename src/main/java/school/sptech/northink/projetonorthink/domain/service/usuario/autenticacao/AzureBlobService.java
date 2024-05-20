package school.sptech.northink.projetonorthink.domain.service.usuario.autenticacao;

import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.models.BlobItem;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AzureBlobService {

    private final BlobContainerClient blobContainerClient;

    public AzureBlobService(BlobContainerClient blobContainerClient) {
        this.blobContainerClient = blobContainerClient;
    }

    public void uploadFile(MultipartFile file) throws IOException {
        BlobClient blobClient = blobContainerClient.getBlobClient(file.getOriginalFilename());
        blobClient.upload(file.getInputStream(), file.getSize(), true);
    }

    public byte[] downloadFile(String fileName) {
        BlobClient blobClient = blobContainerClient.getBlobClient(fileName);
        return blobClient.downloadContent().toBytes();
    }

    public List<String> listFiles() {
        List<String> fileList = new ArrayList<>();
        for (BlobItem blobItem : blobContainerClient.listBlobs()) {
            fileList.add(blobItem.getName());
        }
        return fileList;
    }
}
