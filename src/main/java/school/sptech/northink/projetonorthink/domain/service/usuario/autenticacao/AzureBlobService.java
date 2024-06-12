package school.sptech.northink.projetonorthink.domain.service.usuario.autenticacao;


import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;
import com.microsoft.azure.storage.blob.ListBlobItem;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class AzureBlobService {

    private final CloudBlobClient cloudClient;
    private CloudBlobContainer cloudContainer;
    private Environment env;

    public AzureBlobService(BeanFactory beanFactory, Environment env) {
        this.env = env;
        this.cloudClient = beanFactory.getBean(CloudBlobClient.class);
    }

    public void uploadFile(MultipartFile file) throws IOException, URISyntaxException, StorageException {
        this.cloudContainer = this.cloudClient.getContainerReference("imagens");
        CloudBlockBlob blob = this.cloudContainer.getBlockBlobReference(Objects.requireNonNull(file.getOriginalFilename()));
        blob.upload(file.getInputStream(), file.getSize());
    }

    public byte[] downloadFile(String fileName) throws URISyntaxException, StorageException {
        this.cloudContainer = this.cloudClient.getContainerReference("imagens");

        CloudBlockBlob blob = this.cloudContainer.getBlockBlobReference(fileName);

        if (!blob.exists()) {
            return null;
        }

        byte[] fileContent = new byte[(int) blob.getProperties().getLength()];
        blob.downloadToByteArray(fileContent, 0);
        return fileContent;
    }

    public List<String> listFiles() throws URISyntaxException, StorageException {
        this.cloudContainer = this.cloudClient.getContainerReference("imagens");
        List<String> files = new ArrayList<>();
        for (ListBlobItem blobItem : this.cloudContainer.listBlobs()) {
            files.add(blobItem.getUri().toString());
        }
        return files;
    }
}
